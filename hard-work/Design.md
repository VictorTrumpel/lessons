# Отчет о проделанной работе по ясной структуре дизайна

### Пример 1.

```typescript
/* файл ./rootStore.ts */
class RootStore {
  constructor() {
    this.api = new IfcApi(this);

    /* ...другой код */

    this.clipperToolStore = new ClipperToolStore(this);
    this.rulerToolStore = new RulerToolStore(this);

    /* ...другой код */
  }
}

/* файл ./toolsStore/ClipperToolStore.ts */
export class ClipperToolStore implements IToolStore {
  private _mode: IToolStore["mode"] = null;
  private _clipperPlanes: IfcPlane[] = [];

  constructor(private readonly root: IRootStore) {
    makeAutoObservable(this);
  }

  set mode(mode: IToolStore["mode"]) {
    this._mode = mode;
  }

  get mode() {
    return this._mode;
  }

  get mutationsCount() {
    return this._clipperPlanes.length;
  }

  get clipper() {
    return this.root.api.viewer.clipper;
  }

  deleteMutation() {
    const deletedPlane = this.root.apiClipper.deletePlane();

    if (!deletedPlane) return;

    this._clipperPlanes = this._clipperPlanes.filter(
      (plane) => plane.helper.uuid !== deletedPlane.helper.uuid
    );

    if (this.mutationsCount === 0) this.mode = "ADD";
  }

  deleteAllMutations() {
    this._clipperPlanes.forEach((plane) =>
      this.root.apiClipper.deletePlane(plane)
    );
    this._clipperPlanes = [];
  }

  addMutation() {
    const newPlane = this.root.apiClipper.createPlane();

    if (!newPlane) return;

    this._clipperPlanes.push(newPlane);
  }

  onClick() {
    if (this.mode === "ADD") {
      this.addMutation();
      return;
    }

    if (this.mode === "DELETE") {
      this.deleteMutation();
    }
  }
}

/* файл ./toolsStore/RulerToolStore.ts */
export class RulerToolStore implements IToolStore {
  private _mode: IToolStore["mode"] = null;
  private _mutationsCount = 0;

  constructor(private readonly root: IRootStore) {
    makeAutoObservable(this);
  }

  set mode(mode: IToolStore["mode"]) {
    this._mode = mode;
  }

  get mode() {
    return this._mode;
  }

  get mutationsCount() {
    return this._mutationsCount;
  }

  get dimensions() {
    return this.root.api.viewer.dimensions;
  }

  deleteUnFinishedMutation = () => {
    const currentDimension: IfcDimensionLine = this.dimensions.currentDimension;

    if (currentDimension) {
      this.dimensions.cancelDrawing();

      currentDimension.removeFromScene();
      this.dimensions.currentDimension = undefined;
      this.dimensions.dragging = false;
    }
  };

  deleteMutation() {
    this.dimensions.delete();
    this._mutationsCount = this.dimensions.getDimensionsLines.length;
    if (this.mutationsCount === 0) this._mode = "ADD";
  }

  deleteAllMutations() {
    this._mutationsCount = 0;
    this.dimensions.deleteAll();
    this._mutationsCount = this.dimensions.getDimensionsLines.length;
  }

  async addMutation() {
    const element = this.root.api.viewer.context.castRayIfc();
    if (!element) return;

    this.dimensions.create();
    this._mutationsCount = this.dimensions.getDimensionsLines.length;
  }

  onClick() {
    if (this._mode === "ADD") {
      this.addMutation();
      return;
    }

    if (this._mode === "DELETE") {
      this.deleteMutation();
    }
  }
}
```

---

Суть кода:

- есть некий класс IfcApi который выполняет некие модификации над 3-Д моделью
- есть 2 класса ClipperToolStore и RulerToolStore которые обращаются к АПИ IfcApi

Проблема кода:

- С точки зрения дизайна не очевидно, что классы _ClipperToolStore_ и _RulerToolStore_ внутри своих методов обращаются к _IfcApi_

---

#### Дизайн:

---

В программе есть следующие сущности: Тулзы, Апи (IFC), Коннектор, Создатель коннектора.

У нас есть класс _ToolsStore_ который объединяет _ClipperToolStore_ и _RulerToolStore_.

_ModelConnector_ является фасадом к IfcApi.

_ModelConnector_ пробрасывается в классы _ClipperToolStore_ и _RulerToolStore_.

_ClipperToolStore_ и _RulerToolStore_ объединены одним контрактом _IToolStore_.

_ClipperToolStore_ и _RulerToolStore_ не используются напрямую, они назначаются через класс _ToolStore_. _ToolStore_ отвечает за синхронизацию тулзов, их состояний, если это потребуется.

_ClipperToolStore_ и _RulerToolStore_ реализовывают автомат состояний:  
_AddClipperToolStor_, _DeleteClipperToolStore_, (_RulerToolStore_ аналогично).

_ClipperToolStore_ и _RulerToolStore_ взаимодействуют с _ModelConnector_, который приходит им извне.

Они ничего не знают о том, что на самом деле делает _ModelConnector_, они лишь вызывают его методы.

Сам _ModelConnector_ тоже ничего не знает о _IfcApi_. _ModelConnector_ позволяет связать каждый его метод с нужным методом из _IfcApi_.

Эту связку мы будем делать в специальном классе _IfcModelConnectorBuilder_. _IfcModelConnectorBuilder_ - принимает _ModelConnector_ и _IfcApi_ и связывает их нужным образом.

_RootStor_ - место входа программы.

---

#### Реализация:

```typescript
type Plane = string;
type Dimension = string;

class ModelConnector {
  deletePlane: (plane?: Plane) => Plane | null;
  createPlane: () => Plane;
  addDimension: () => Dimension;
  deleteAllDimensions: () => void;
  deleteDimension: () => void;
}

class IfcApi {
  deletePlane: () => void;
  createPlane: () => Plane;
  addDimension: () => Dimension;
  deleteDimension: (dimension: Dimension) => void;
}

class IfcModelConnectorBuilder {
  connect(modelConnector: ModelConnector, ifcApi: IfcApi) {
    modelConnector.addDimension = ifcApi.addDimension.bind(ifcApi);
    modelConnector.deleteDimension = ifcApi.deleteDimension.bind(ifcApi);
    modelConnector.createPlane = ifcApi.createPlane.bind(ifcApi);
    modelConnector.deletePlane = ifcApi.deletePlane.bind(ifcApi);
  }
}

interface IToolStore {
  addMutation(): void;
  deleteMutation(): void;
  deleteAllMutations(): void;
  onClick(): void;
}

class AddClipperTool implements IToolStore {
  private _clipperPlanes: Plane[] = [];

  constructor(private modelConnector: ModelConnector) {}

  get mutationsCount() {
    return this._clipperPlanes.length;
  }

  addMutation() {
    const newPlane = this.modelConnector.createPlane();

    if (!newPlane) return;

    this._clipperPlanes.push(newPlane);
  }

  onClick() {
    this.addMutation();
  }

  deleteMutation(): void {}
  deleteAllMutations(): void {}
}

class DeleteClipperTool implements IToolStore {
  private _clipperPlanes: Plane[] = [];

  constructor(private modelConnector: ModelConnector) {}

  deleteMutation() {
    const deletedPlane = this.modelConnector.deletePlane();

    if (!deletedPlane) return;

    this._clipperPlanes = this._clipperPlanes.filter(
      (plane) => plane.helper.uuid !== deletedPlane.helper.uuid
    );
  }

  deleteAllMutations() {
    this._clipperPlanes.forEach((plane) =>
      this.modelConnector.deletePlane(plane)
    );
    this._clipperPlanes = [];
  }

  onClick() {
    this.deleteMutation();
  }

  addMutation(): void {}
}

class AddRulerTool implements IToolStore {
  constructor(private modelConnector: ModelConnector) {}

  addMutation() {
    this.modelConnector.addDimension();
  }

  onClick(): void {
    this.addMutation();
  }

  deleteMutation() {}
  deleteAllMutations() {}
}

class DeleteRulerTool implements IToolStore {
  constructor(private modelConnector: ModelConnector) {}

  deleteMutation() {
    this.modelConnector.deleteDimension();
  }

  deleteAllMutations() {
    this.modelConnector.deleteAllDimensions();
  }

  onClick() {
    this.deleteMutation();
  }

  addMutation(): void {}
}

class ToolStore {
  private clipperTool: AddClipperTool | DeleteClipperTool;
  private rulerTool: AddRulerTool | DeleteRulerTool;

  setClipperTool(clipperTool: AddClipperTool | DeleteClipperTool) {
    this.clipperTool = clipperTool;
  }

  setRulerTool(rulerTool: AddRulerTool | DeleteRulerTool) {
    this.rulerTool = rulerTool;
  }
}

class RootStore {
  private toolStore: ToolStore;

  constructor() {
    const modelConnector = new ModelConnector();
    const ifcApi = new IfcApi();
    const ifcModelConnectorBuilder = new IfcModelConnectorBuilder();

    ifcModelConnectorBuilder.connect(modelConnector, ifcApi);

    this.toolStore = new ToolStore();
  }
}
```

#### Рефлексия:

---

При описании дизайна я старался мыслить сущностями. Мне нужно было описать сущности таким образом, что бы было очевидно, что они из себя представляют.

Таким образом, наличие сущности _IfcModelConnectorBuilder_ уже говорит о том, что во-первых: должна быть логика связывания АПИ и с чем-то, и что эта логика описыватеся в этом классе.

Само наличие _ModelConnector_ говорит о том, что тулзы не могут обращаться к 3Д модели напрямую.

На коде - эта часть дизайна реализовыватеся паттернами - _Фасад_ и _Builder_ .

Далее я внедрил концепцию _Тулзы_, которая описывается в интерфейсе _IToolStore_.

Название "тулза" т.к. вносит модификации на 3Д модель. Заменяя _ModelConnector_ мы можем подставить любой IfcApi.  
IfcApi - библиотечный класс. Если мы захотим переехать на новую библиотеку - мы сможем это сделать т.к. наши тулзы не зависят от библиотеки напрямую.

---
