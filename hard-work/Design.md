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
<img width="1008" alt="Screenshot 2024-02-19 at 00 16 24" src="https://github.com/VictorTrumpel/lessons/assets/59663275/3858719b-56bc-48f1-b336-f0521f22400a">

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

### Пример 2.

```typescript
const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

export class FinishingController {
  headers: Headers = new Headers();
  method: "POST" | "GET" | "DELETE" | "PUT" = "GET";
  url: URL | null = null;

  async create(createFinishingDTO: CreateFinishingDTO): Promise<Finishing> {
    const getFinishingDTO = await fetch(SERVER_URL, {
      ...createFinishingDTO,
      body: JSON.stringify(SERVER_URL),
    });

    return this.convertToFinishing(getFinishingDTO);
  }

  async getByRoomUuid(roomUuid: string): Promise<Finishing | null> {
    const page = await await fetch(`${SERVER_URL}/${roomUuid}`, {
      body: JSON.stringify(SERVER_URL),
    });

    const [getFinishingDTO] = page.content;

    if (!getFinishingDTO) return null;

    return this.convertToFinishing(getFinishingDTO);
  }

  async updateFinishingItem(
    updateFinishingItemDTO: UpdateFinishingItemDTO
  ): Promise<GetFinishingItemDTO> {
    return await fetch(SERVER_URL, {
      ...updateFinishingItemDTO,
      method: "PUT",
      body: JSON.stringify(SERVER_URL),
    });
  }

  private convertToFinishing(getFinishingDTO: GetFinishingDTO) {
    const finishing = new Finishing();

    finishing.id = getFinishingDTO.id;
    finishing.uuid = getFinishingDTO.uuid;
    finishing.roomName = getFinishingDTO.roomName;
    finishing.roomUuid = getFinishingDTO.roomUuid;
    finishing.projectId = getFinishingDTO.projectId;
    finishing.containerId = getFinishingDTO.containerId;
    finishing.modelFile = getFinishingDTO.modelFile;
    finishing.dateCreate = this.convertToDate(getFinishingDTO.dateCreate);
    finishing.finishingList = [];

    getFinishingDTO.finishingList.forEach((item) => {
      const finishingItem = this.convertToFinishingItem(item);
      finishing.finishingList.push(finishingItem);
    });

    return finishing;
  }

  private convertToDate(possibleDate: string | null): Date | null {
    if (!possibleDate) return null;
    return new Date(possibleDate);
  }

  private convertToFinishingItem(getFinishingItemDTO: GetFinishingItemDTO) {
    const finishingItem = new FinishingItem();

    finishingItem.id = getFinishingItemDTO.id;
    finishingItem.uuid = getFinishingItemDTO.uuid;
    finishingItem.type = getFinishingItemDTO.type;
    finishingItem.name = getFinishingItemDTO.name;
    finishingItem.mark = getFinishingItemDTO.mark;
    finishingItem.units = getFinishingItemDTO.units;
    finishingItem.factUnits = getFinishingItemDTO.factUnits;
    finishingItem.status = getFinishingItemDTO.status;
    finishingItem.comment = getFinishingItemDTO.comment;
    finishingItem.finishingId = getFinishingItemDTO.finishingId;

    return finishingItem;
  }
}
```

---

Суть кода:

- Делать запросы на бэкенд, связанные с отделкой

Проблема кода:

- Не гибкая система запросов и обработок ошибок с бэкенда и трансформации данных.

---

#### Дизайн:

---

Сущности: Network, FinishingService, FinishingApi

Network -

- сущность для взаимодействия с сетью. Каждый раз, когда неоходимо сделать запрос по сети - нужно создать инстанс Network.
- может выбрасывать определенный пулл ошибок

FinishingService

- cущность, которая создает небходимые инстансы Network именно для работы с Finishing(отделкой)
- настроен на то, что бы обрабатывать ошибки от Network
- предоставляет методы для сетевых запросов с Finihsing, но запросы осуществляет через Network

FinishingApi -

- делает запросы на FinishingService и преобразовывает ответы от FinishingService в нужный формат данных.
- к FinishingApi обращаются компоненты отображения. Они не знают откуда получает FinishingApi получает ответы

---

#### Реализация:

```typescript
export class Network<PayloadBody, ResponseBody> {
  url: URL | null = null;
  headers: Headers = new Headers();
  method: "POST" | "GET" | "DELETE" | "PUT" = "GET";

  async request(payloadBody: PayloadBody): Promise<ResponseBody> {
    const urlStr = this.url?.href || "";

    const response = await fetch(urlStr, this.getFetchConfig(payloadBody));

    const responseJson = await response.json();

    return responseJson as ResponseBody;
  }

  private getFetchConfig(payloadBody: PayloadBody) {
    const baseFetchConfig = {
      method: this.method,
      headers: this.headers,
    };

    if (this.method === "GET") {
      return baseFetchConfig;
    }

    return {
      ...baseFetchConfig,
      body: JSON.stringify(payloadBody),
    };
  }
}

const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

export class FinishingService {
  private baseURL = new URL(SERVER_URL);

  async create(
    createFinishingDTO: CreateFinishingDTO
  ): Promise<GetFinishingDTO> {
    const network = this.createPostFinishingNetwork();

    return await network.request(createFinishingDTO);
  }

  async getAll(queryParams: {
    roomUuid?: string;
    page?: number;
    size?: number;
  }): Promise<Page<GetFinishingDTO>> {
    const network = this.createGetFinishingNetwork<
      null,
      Page<GetFinishingDTO>
    >();

    const { page, size, roomUuid } = queryParams;

    if (page) {
      network.url?.searchParams.set("page", `${page}`);
    }
    if (size) {
      network.url?.searchParams.set("size", `${size}`);
    }
    if (roomUuid) {
      network.url?.searchParams.set("roomUuid", `${roomUuid}`);
    }

    return await network.request(null);
  }

  async updateFinishingItem(updateFinishingItemDTO: UpdateFinishingItemDTO) {
    const network = this.createPutFinishingItemNetwork<
      UpdateFinishingItemDTO,
      GetFinishingItemDTO
    >();

    return await network.request(updateFinishingItemDTO);
  }

  private createPostFinishingNetwork() {
    const createFinishingUrl = new URL("finishing", this.baseURL);

    const headers = this.createHeaders();

    const network = new Network<CreateFinishingDTO, GetFinishingDTO>();
    network.url = createFinishingUrl;
    network.method = "POST";
    network.headers = headers;

    return network;
  }

  private createGetFinishingNetwork<PayloadBody, ResponseBody>() {
    const getFinishingUrl = new URL("finishing", this.baseURL);

    const headers = this.createHeaders();

    const network = new Network<PayloadBody, ResponseBody>();
    network.url = getFinishingUrl;
    network.method = "GET";
    network.headers = headers;

    return network;
  }

  private createPutFinishingItemNetwork<PayloadBody, ResponseBody>() {
    const getFinishingUrl = new URL("finishing/item", this.baseURL);

    const headers = this.createHeaders();

    const network = new Network<PayloadBody, ResponseBody>();
    network.url = getFinishingUrl;
    network.method = "PUT";
    network.headers = headers;

    return network;
  }

  private createHeaders(): Headers {
    const headers = new Headers();
    headers.append("Content-Type", "application/json");
    return headers;
  }
}

export class FinishingApi {
  private readonly finishingService = new FinishingService();

  async create(createFinishingDTO: CreateFinishingDTO): Promise<Finishing> {
    const getFinishingDTO = await this.finishingService.create(
      createFinishingDTO
    );

    return this.convertToFinishing(getFinishingDTO);
  }

  async getByRoomUuid(roomUuid: string): Promise<Finishing | null> {
    const page = await this.finishingService.getAll({ roomUuid });
    const [getFinishingDTO] = page.content;

    if (!getFinishingDTO) return null;

    return this.convertToFinishing(getFinishingDTO);
  }

  async updateFinishingItem(
    updateFinishingItemDTO: UpdateFinishingItemDTO
  ): Promise<GetFinishingItemDTO> {
    return await this.finishingService.updateFinishingItem(
      updateFinishingItemDTO
    );
  }

  private convertToFinishing(getFinishingDTO: GetFinishingDTO) {
    const finishing = new Finishing();

    finishing.id = getFinishingDTO.id;
    finishing.uuid = getFinishingDTO.uuid;
    finishing.roomName = getFinishingDTO.roomName;
    finishing.roomUuid = getFinishingDTO.roomUuid;
    finishing.projectId = getFinishingDTO.projectId;
    finishing.containerId = getFinishingDTO.containerId;
    finishing.modelFile = getFinishingDTO.modelFile;
    finishing.dateCreate = this.convertToDate(getFinishingDTO.dateCreate);
    finishing.finishingList = [];

    getFinishingDTO.finishingList.forEach((item) => {
      const finishingItem = this.convertToFinishingItem(item);
      finishing.finishingList.push(finishingItem);
    });

    return finishing;
  }

  private convertToDate(possibleDate: string | null): Date | null {
    if (!possibleDate) return null;
    return new Date(possibleDate);
  }

  private convertToFinishingItem(getFinishingItemDTO: GetFinishingItemDTO) {
    const finishingItem = new FinishingItem();

    finishingItem.id = getFinishingItemDTO.id;
    finishingItem.uuid = getFinishingItemDTO.uuid;
    finishingItem.type = getFinishingItemDTO.type;
    finishingItem.name = getFinishingItemDTO.name;
    finishingItem.mark = getFinishingItemDTO.mark;
    finishingItem.units = getFinishingItemDTO.units;
    finishingItem.factUnits = getFinishingItemDTO.factUnits;
    finishingItem.status = getFinishingItemDTO.status;
    finishingItem.comment = getFinishingItemDTO.comment;
    finishingItem.finishingId = getFinishingItemDTO.finishingId;

    return finishingItem;
  }
}
```

#### Рефлексия:

---

При описании дизайна я понял, что сетевая логика программы не может находится в одном классе.  
Поэтому получилось 3 сущности.

Network - интерфейс взаимодействия с сетью. Под капотом он может использовать браузерное апи для работы с сетью, или любое другое. Мы не зависим от платформы.

FinishingService - знает только что такое Network и как его настроить таким образом, что бы получать нужные Finishing (нужную отделку помещения). Он тоже становится независимым от платформы.

Network и FinishingService мы можем переносить на любое приложение без изменений. К конкретному клиенту их привязывает FinishingApi.

FinishingApi - ничего не знает о сети. Он знает только как у FinishingService попросить нужную отделку и как преобразовать эту отделку в формат, понятный текущему клиенту.

---
