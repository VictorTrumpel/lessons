# Отчет о проделанной работе по "Антипаттерн "Самодокументирующийся код"

### Пример 1.

```javascript
/*
  Класс для привязки комментария к 3D модели.

  Все комментарии в проекте создаются через класс CommentInfo.
  Если мы хотим, что бы этот комментарий отобразился на 3D модели, то мы должны использовать методы класса CommentModelConnector.
  Нельзя обращаться к АПИ 3D модели напрямую, только через этот класс!
*/
export class CommentModelConnector {
  private _clipperPlanes: IfcPlane[] = [];

  constructor(private apiClipper: IfcClipperApi) {}

  mountCommentInModel(comment: CommentInfo, model: IFCModel) {
    if (!comment.cssPoint) return;
    comment.cssPoint.center;
    comment.cssPoint.layers.set(0);

    const position = new Vector3(...comment.position);
    comment.cssPoint.position.copy(position);

    model.add(comment.cssPoint);
  }

  unmountCommentInModel(comment: CommentInfo, model: IFCModel) {
    if (!comment.cssPoint) return;
    model.remove(comment.cssPoint);
  }

  mountCommentClipperInModel(comment: CommentInfo) {
    comment.positionClipperPlanes?.forEach((planePosition) => {
      const normal = new Vector3(
        planePosition.normal.x,
        planePosition.normal.y,
        planePosition.normal.z
      );
      const point = new Vector3(
        planePosition.point.x,
        planePosition.point.y,
        planePosition.point.z
      );
      const newPlane = this.apiClipper.createClipperPlaneByPosition(normal, point);
      this._clipperPlanes.push(newPlane);

      this.apiClipper.hideClipperPlaneControls(newPlane);
    });
  }

  unmountCommentClipperInModel() {
    this._clipperPlanes.forEach((plane) => this.apiClipper.deletePlane(plane));
    this._clipperPlanes = [];
  }
}
```

---

Рефлексия:

Когда начал описывать верхнеуровнено что делает этот класс, понял, что класс стоило назввать по-другому.  
Например: _ModelCommentPrinter_ или _ModelCommentMounter_

---

### Пример 2.

```javascript
/*
  Наш фронт является встаиваемым через <iframe/> и мы реализовываем возможность получать сообщения от родительского приложения.
  MessageServiceSMWeb - является прослойкой между родительским окном и нашем приложением.
  MessageServiceSMWeb - инстанциируется один раз и ведет прослушку на наличие сообщений от окна-родителя.
  Используя АПИ MessageServiceSMWeb мы можем подписаться на сообщения от родительского окна.
  Все взаимодействие с сообщениями родительского окна должно начинаться с MessageServiceSMWeb.
*/
export class MessageServiceSMWeb implements IMessageService {
  private _postMessage: ((data: any) => void) | null = null;

  constructor(private readonly root: IRootStore) {
    makeAutoObservable(this);
  }

  private modelContainer?: ModelContainer;
  private project?: Project;

  receiveMessage = (message: MessageEvent) => {
    /* ... */
  };

  setPostMessageCallback = (callback: (data: any) => void) => {
    this._postMessage = callback;
  };

  postList<T>(list: T[], type: string) {
    if (!this._postMessage) return;
    this._postMessage({ type: type, data: list });
  }

  private createLocalModelContainer(container: any): void {
    const containerId = container['id'];
    const containerUuid = container['uuid'];
    const containerName = container['name'];
    this.modelContainer = new ModelContainer(containerId, containerUuid, containerName);
  }

  private createProject(container: any): void {
    const projectUuid = container['uuid'];
    const projectName = container['name'];
    this.project = new Project(projectUuid, projectName);
  }
}
```

---

Рефлексия:

Мы пишем плагин, который встраивается в отображение других окон через <iframe>. Каким бы красноречивым не было имя класса, человек со стороны, который читает код не смог бы понять, для чего и зачем этот класс нужен. Такой комментарий сразу прояснит очень много моментов для внешнего читателя и он сможет быстрее войти в курс дела.

Кстати, когда я пришел на проект, мне никто не сказал, что мы пишем встраиваемое приложение и я затер код, который отвечал за прием внешних сообщений т.к. посчитал, что там какое-то неиспользуемое легаси.

После этой ситуации я вынес все общение с внешним окном в этот сервис.

---

### Пример 3.

```javascript
/*
  Иногда нам приходится работать с очень большими массивами (более 10-ти тысяч элементов).
  Если мы будем обрабатывать этот массив данных за раз, то это будет одна большая макротаска, которая заблокирует работу интерфейса.
  Для этого нужно использовать этот класс. Он разбивает обработку одного большого массива (одной большой макротаски) на много мелких макротасок. Таким образом мы можем добиваться эффекта многопоточности, оставаясь в одном потоке.

  gap - шаг, по сколько элементов обрабатывать
  gapDelay - задержка в миллисекундах между "шагами", в которую интерфейс должен успевать перерисоваться

  on() - подписываем колбэк, который получает чанки данных
  stream() - начать обрабатывать чанки данных
*/
export class ExpressIdsStream {
  private ifcStruct: IfcStructStore;

  private gap = 100;

  private gapDelay = 100;

  private _cb: (map: Map<number, number[]>) => void = () => null;

  constructor(ifcStruct: IfcStructStore) {
    this.ifcStruct = ifcStruct;
  }

  private async wait() {
    return new Promise((res) => {
      setTimeout(res, this.gapDelay);
    });
  }

  async stream() {
    const elementsModelMap = this.ifcStruct.elementsModelMap;

    const modelMap = new Map<number, number[]>();

    let itemsProcessed = 0;

    for (const [_, { modelId, expressId }] of elementsModelMap) {
      if (!modelMap.has(modelId)) {
        modelMap.set(modelId, []);
      }

      modelMap.get(modelId)?.push(expressId);

      itemsProcessed += 1;

      if (itemsProcessed === this.gap) {
        this._cb(modelMap);
        await this.wait();
        modelMap.clear;
        itemsProcessed = 0;
      }
    }

    if (modelMap.size) {
      this._cb(modelMap);
    }
  }

  on(cb: (map: Map<number, number[]>) => void) {
    this._cb = cb;
  }
}
```

---

Рефлексия:

Когда написал этот коммент тоже понял, что лучше немного переименовать класс. Этот комментарий описывает особенности работы браузера (из-за этих особенностей сам класс и появился).

Мы, конечно, можем уповать на то, что все фронтендеры знают особенности работы браузера - но это наивно)

Но, даже если человек знает особенности работы браузера, не факт, что он догадается для чего этот класс нужен. А таким комментарием, мы во-первых подсвечиваем цель использования класса и на какие особенности среды этот код опирается.

---

---

Общая рефлексия:

Комментарии полезно писать, даже просто так. Они позволяют держать в голове общий дизайн системы.
Существует действительно много кода, который опирается на условия среды в которой он выполняется и эти внешние условия очень сложно описать самим кодом.

Но тут главное не перебарщивать. Понятно, что не нужно документировать каждую строчку кода.

Я вывел для себя следующие поводы для написания комментариев:

- код опирается на внешние условия среды (браузер, cli-приложение, десктоп) и эти условия вызвали написание специфического кода
- я хочу подчеркнуть как данный код вписывается в общий дизайн приложения (если дизайн просматривается плохо)

---
