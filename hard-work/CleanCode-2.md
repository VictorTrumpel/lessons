# Отчет о проделанной работе Ясный код-2

### 2.1 Нарушение SRP

---

Данный класс нарушает принцип SRP. В нем есть методы которые логически не связаны между собой, например такие как: `highlightRoom` и `setPassportSystem`

Так произошло из-за того, что класс получил слишком общее имя - ModelStore. Это имя ни к чему не обязывает и по сути, в этот класс можно засунуть все, что связано с моделью.

Так же не было написано над классом никаких поясняющих комментариев по поводу того, что он из себя представляет.

---

```typescript
export class ModelsStore {
  ifcStructStore: IfcStructStore;

  propertiesMap: Map<string, JSONLayers> = new Map();

  flatLayers: Element[] = [];
  jsonProperties: JSONProperties[] = [];
  jsonPassports: JSONPassport[] = [];
  currentProperty: Properties | Element | Room | null = null;
  currentPassport: RoomPassport | null = null;
  currentPassportSystem: JSONSystem | null = null;
  currentSystemProperty: Properties | Element | Room | null = null;

  // CAMERA
  positionScene!: Sphere;
  sceneBoundingBox: SceneBoundingBox = { minY: 0, maxY: 0 };
  is2DView = false;

  // ROOM
  rooms: Room[] = [];
  roomPointsMap: Map<number, CSS2DObject[]> = new Map();
  isHasRoomsPoint = false;

  clipperPlane: IfcPlane | null = null;
  maxRoomCordY: number | null = null;

  // FILTER
  currentFilter: Filter = Filter.None;
  // SETTINGS
  alwaysShowComment = false;

  modelFileLoader: ModelFileLoader;
  systemsStruct: SystemsStruct;

  isLoadingStruct: null | "PENDING" | "SUCCESS" = null;

  constructor(private readonly root: IRootStore) {
    makeAutoObservable(this);
    this.ifcStructStore = new IfcStructStore(root);
    this.modelFileLoader = new ModelFileLoader(root);
    this.systemsStruct = new SystemsStruct(root);
    this.modelFileLoader.onLoadFiles = this.handleLoadFiles;
  }

  private handleLoadFiles = async (
    models: { modelName: string; model: IFCModel }[],
    jsonPassports: JSONPassportFile[],
    jsonProperties: JSONLayers[],
    jsonSystemPassports: JSONSystemPassportFile[]
  ) => {};

  get isHasInteraction(): boolean {}

  searchLayers({ query }: { query: string }): Element[] {}

  searchRooms({
    byName,
    byId,
    query,
  }: {
    byName: boolean;
    byId: boolean;
    query: string;
  }): Room[] {}

  get isRoomPointInScene(): boolean {}

  setCurrentFilter = (type: Filter) => {};

  loadModelFileBlobs = async (blobs: []) => {};

  setAlwaysShowComment(showComment: boolean) {}

  deleteAllToolsMutations = () => {};

  loadCustomIFCTypes = async (types: File) => {};

  clickInModel = async () => {};

  cleanUpModels = () => {};

  onClickElement = async () => {};

  setPassportSystem = async (elementId: number) => {};

  setPropertyElement = async (modelID: number, expressID: number) => {};

  highlightRoom = (modelIdOfRoom: number, roomExpressID: number) => {};

  sliceRoomAtHigherPoint = (modelID: number, expressID: number) => {};

  selectRoom = async (modelID: number, expressID: number) => {};

  hideRoomsPoint = (modelId: number) => {};

  showRoomsPoint = async (modelId: number) => {};

  highlightByID = (
    modelID: number,
    expressID: number,
    childrenExpressIds: number[] = []
  ) => {};

  hidePropertySection = () => {};

  extractBoundingBox = (model: IFCModel) => {};

  checkValidJSON = <T extends object>(data: T[], keys: string[]) => {};

  resetPositionCamera = () => {};

  switchCameraView = () => {};

  setCameraTo3D() {}

  resetInteraction = () => {};

  unpickIfcItems = () => {};

  getExpressIDsByRevitTag(ids: number[]) {}
}
```

### 2.2 Класс слишком маленький или делает слишком мало.

---

Этот класс слишком маленький. Он управляет только текущим открытым меню.

Идея класса была того, что будут отдельные классы, отвечающие за UI и отдельные классы отвечающие за бизнес-логику.

Но по итогу, этот класс называется не слишком выразительно что бы понимать к какому именно ui он относится и поэтому про него все забыли.

Так же нужно понимать, что состояние UI класса есть следсвтие состояния класса, отвечающего за бизнес-лоигку. И непонятно следствием каких бизнес-процессов является UiStore

---

```typescript
export class UiStore implements IUiStore {
  currentMenu: MenuType = MenuType.None;

  constructor(private readonly root: IRootStore) {
    makeAutoObservable(this);
  }

  setCurrentMenu = (type: MenuType) => {
    if (type === this.currentMenu) {
      this.currentMenu = MenuType.None;
    } else {
      this.currentMenu = type;
    }
  };
}
```

### 2.3 В классе есть метод, который выглядит более подходящим для другого класса.

---

метод `queryRoomsBy` не очень подходит для класса `IfcStructStore` т.к. Такой метод больше подходил бы для класса `RoomQuery`. В нем можно было бы описать специализированные методы для поиска каждого типа комнат.

В данном случае метод был расположен тут, т.к. это было просто удобно. Класс в своем состоянии хранит список комнат.

---

```typescript
export class IfcStructStore {
  getAllRooms() {
    /* */
  }

  queryRoomsBy() {
    /* */
  }
}
```

### 2.4 Класс хранит данные, которые загоняются в него в множестве разных мест в программе.

---

Состояние класса `SceneInfo` переопределяется во многих местах программы. Это плохо, потому что эти изменения сложно контролировать.

По идее, должен быть специальный сервис, через который можно было бы изменять `SceneInfo`

---

```typescript
export class SceneInfo {
  cameraPosition: IssuePosition;
  cameraRotation: Euler;
  positionClipperPlanes: PositionClipperPlane[] = [];

  constructor() {}

  private getPositionClipperPlanes = () => {
    /**/
  };
}
```

### 2.5 Класс зависит от деталей реализации других классов.

---

Методы `createClipperPlane` и `moveClipperPlaneByCordY` зависят от IFC библиотеки, которая рендерит 3D модели. Это плохо, потому что например сейчас, когда мы хотим переехать на новый движок у нас сложности с тем, что много кода завязано на старый. По идее, перед библиотекой должен быть фасад к которому обращаются по АПИ. В таком случае, пришлось бы менять только фасад и не трогать сам класс.

---

```typescript
export class ClipperRangeStore {
  private _clipperPlane: IfcPlane | null = null;

  rememberedPercents: number | null = null;

  constructor(private readonly root: IRootStore) {
    makeAutoObservable(this);
  }

  createClipperPlane() {
    if (this._clipperPlane) {
      this.root.apiClipper.deletePlane(this._clipperPlane);
    }

    const normal = new Vector3(0, -1, 0);
    const modelCenter = new Vector3(
      0,
      this.root.modelsStore.sceneBoundingBox.maxY,
      0
    );
    const plane = this.root.apiClipper.createClipperPlaneByPosition(
      normal,
      modelCenter
    );

    plane.planeMesh.geometry = plane.planeMesh.geometry.clone();

    this._clipperPlane = plane;
    this._clipperPlane.controls.enabled = false;

    this.makeClipperHidden();
  }

  moveClipperPlaneByCordY(cordY: number) {
    if (!this._clipperPlane) return;

    const normalVector = new Vector3(0, -1, 0);
    const pointVector = new Vector3(0, cordY, 0);

    this.percents = this.cordToPercent(cordY);

    this._clipperPlane.plane.setFromNormalAndCoplanarPoint(
      normalVector,
      pointVector
    );

    this._clipperPlane.planeMesh.position.z =
      (cordY - this._clipperPlane.origin.y) * -1;
  }
}
```

### 2.6 Приведение типов вниз по иерархии (родительские классы приводятся к дочерним).

---

Т.к. метод `fetchPosts` был добавлен нестандартно к `user`, по факту примешан, то TS не трекает такую примесь. Что бы вызывать `fetchPosts` даункаст к `Admin`.

---

```typescript
class User {}

class Admin extends User {
  fetchPosts();
}

function AdminProfile(admin: Admin) {
  return <></>;
}

const user = new User();

user.fetchPosts = function () {
  /* */
};

(user as Admin).fetchPosts();
```

### 2.7 Когда создаётся класс-наследник для какого-то класса, приходится создавать классы-наследники и для некоторых других классов.

---

класс `UserNameFormatter` форматирует имя. Но т.к. у класса `Admin` другая структура имени (+ 2 поля), то `UserNameFormatter` подходит не очень и созадется тоже наследник.

Правильно решать такую проблему через паттерн `Visitor`.

---

```typescript
class User {
  firstName: string;
}

class Admin extends Users {
  lastName: string;
  middleName: string;
}

class UserNameFormatter {}

class AdminNameFormatter extends UserNameFormatter {}
```

### 2.8 Дочерние классы не используют методы и атрибуты родительских классов, или переопределяют родительские методы.

---

класс `GetNetwork` не использует метод `setHeaders` потому что ему этот метод не нужен.
В данной ситуации было бы лучше создать просто интерфейс `Network` с необязательными методами.
Или абстрактный класс с дефолтными реализациями.

---

```typescript
class Network {
  method: "POST" | "PUT" | "GET" | "DELETE";

  setHeaders() {}
}

class GetNetwork extends Network {}
```

### 3.1 Одна модификация требует внесения изменений в несколько классов.

---

Связано с примером `2.5` где класс `ClipperRangeStore` обращается к АПИ библиотеки напрямую, через слабенький фасад. Если я внесу изменения в класс `IfcApi` , то придется изменять и `ClipperRangeStore`, а так же `IfcHighlighter` и много других классов, которые обращаются к `IfcApi`.

Нужно отделять логику через паттерн `Visitor`.

---

```typescript
export class IfcApi {
  createClipperPlaneByPosition() {}

  setFromNormalAndCoplanarPoint();

  /* другие методы по работе с 3D моделью */
}
```

### 3.2 Использование сложных паттернов проектирования там, где можно использовать более простой и незамысловатый дизайн.

---

Тут `AdminButton` использует паттерн `EventEmitter` для того, что бы испусть событие того, что по ней кликнули.

И в другом месте на это событие подписываются.

Но в данном случае, было бы проще пробросить колбэк этой кнопке, который бы она вызвала при клике.

---

```typescript
class EventEmitter {}

const ee = new EventEmitter();

class AdminButton {
  onClick() {
    ee.emit("click-admin-btn");
  }
}

ee.on("click-admin-btn", () => {
  /* реакция */
});
```
