# Отчет о проделанной работе по Истинное наследование

---

На рабочем проекте есть 2 класса: _ModelIssue_ , _DocumentIssue_

Методы _getCreateDTO_ , _getUpdateDTO_ возвращяют объектные представления этих инстансов для бэкенда.

Но т.к. приложение может работать и в оффлайне, нужно настроить работу с IndexDB.

Для IndexDB тоже нужны свои DTO и они отличаются от DTO, которые нужны бэку (связанные с особенностями работы IndexDB).

---

---

Что имеем:

---

```typescript
interface IIssue {
  getCreateDTO(): Promise<void>;
  getUpdateDTO(): Promise<void>;
}

class ModelIssue implements IIssue {
  async getCreateDTO() {
    /* Возвращает объект DTO для создания */
  }

  async getUpdateDTO() {
    /* Возвращает объект DTO для обновления */
  }
}

class DocumentIssue implements IIssue {
  async getCreateDTO() {
    /* Обращение к бэку*/
  }

  async getUpdateDTO() {
    /* Возвращает объект DTO для обновления */
  }
}
```

---

Если сделать через "неправильное наследование":

---

```typescript
interface IIssue {
  getCreateDTO(): Promise<void>;
  getUpdateDTO(): Promise<void>;
}

class ModelIssue implements IIssue {
  async getCreateDTO() {
    /* Возвращает объект DTO для создания */
  }

  async getUpdateDTO() {
    /* Возвращает объект DTO для обновления */
  }
}

class DocumentIssue implements IIssue {
  async getCreateDTO() {
    /* Обращение к бэку*/
  }

  async getUpdateDTO() {
    /* Возвращает объект DTO для обновления */
  }
}

class ModelIssueIndexDB extends ModelIssue {
  async getCreateDTO() {
    /* Переопределяем логику */
  }

  async getUpdateDTO() {
    /* Переопределяем логику */
  }
}

class DocumentIssueIndexDB extends DocumentIssue {
  async getCreateDTO() {
    /* Переопределяем логику */
  }

  async getUpdateDTO() {
    /* Переопределяем логику */
  }
}
```

---

В примере выше, мы просто переопределили родительские методы. Это неправильное наследование. В конкретно этом случае, будет смотреться очень костыльно т.к. мы по факту создали 2 доп класса, для очень специфической задачи, которую в данном случае можно очевидно решить проще.

---

---

Правильное наследование:

---

```typescript
// Создаем интерфейс  визитора, описываем АПИ для создание DTO-шек
interface IVisitorDTOForServer {
  getCreateDocumentIssueDTO(documentIssue: DocumentIssue): Promise<void>;
  getUpdateDocumentIsssueDTO(documentIssue: DocumentIssue): Promise<void>;

  getCreateModelIssueDTO(modelIssue: ModelIssue): Promise<void>;
  getUpdateModelIssueDTO(modelIssue: ModelIssue): Promise<void>;
}

// Визитор для создания DTO для бэка
class VisitorDTOForServer implements IVisitorDTOForServer {
  async getCreateDocumentIssueDTO(documentIssue: DocumentIssue) {
    /* реализация */
  }
  async getUpdateDocumentIsssueDTO(documentIssue: DocumentIssue) {
    /* реализация */
  }

  async getCreateModelIssueDTO(modelIssue: ModelIssue) {
    /* реализация */
  }

  async getUpdateModelIssueDTO(modelIssue: ModelIssue) {
    /* реализация */
  }
}

// Визитор для создания DTO для IndexDB
class VisitorDTOForIndexDB implements IVisitorDTOForServer {
  async getCreateDocumentIssueDTO(documentIssue: DocumentIssue) {
    /* реализация */
  }
  async getUpdateDocumentIsssueDTO(documentIssue: DocumentIssue) {
    /* реализация */
  }

  async getCreateModelIssueDTO(modelIssue: ModelIssue) {
    /* реализация */
  }

  async getUpdateModelIssueDTO(modelIssue: ModelIssue) {
    /* реализация */
  }
}

interface IIssue {
  getCreateDTO(): Promise<void>;
  getUpdateDTO(): Promise<void>;
}

class ModelIssue implements IIssue {
  async getCreateDTO(visitor: IVisitorDTOForServer) {
    // мы просто вызываем интерфейс. При этом мы можем передать визитор как для бэка так и для IndexDB
    return visitor.getCreateModelIssueDTO(this);
  }

  async getUpdateDTO(visitor: IVisitorDTOForServer) {
    return visitor.getUpdateModelIssueDTO(this);
  }
}

class DocumentIssue implements IIssue {
  async getCreateDTO(visitor: IVisitorDTOForServer) {
    return visitor.getCreateDocumentIssueDTO(this);
  }

  async getUpdateDTO(visitor: IVisitorDTOForServer) {
    return visitor.getCreateDocumentIssueDTO(this);
  }
}
```

---

За счет двойной диспетчеризации получаем высокую абстрактность. Код становится менее связанным. Вся логика по созданию DTO находится в одном классе и не засоряет классы Issue. При том, что классы Issue больше предназначены для визуальной работы с моделями (отобразить Issue на 3D модели).

---

---

Но в некотором случае был бы уместен такой код: (просто добавить доп. методы. Без наследования и без визитора)

---

```typescript
interface IIssue {
  getCreateDTO(): Promise<void>;
  getUpdateDTO(): Promise<void>;
}

class ModelIssue implements IIssue {
  async getCreateDTOForServer() {
    /* Возвращает объект DTO для создания */
  }

  async getUpdateDTOForServer() {
    /* Возвращает объект DTO для обновления */
  }

  async getCreateDTOForIndexDB() {
    /* Возвращает объект DTO для создания */
  }

  async getUpdateDTOForIndexDB() {
    /* Возвращает объект DTO для обновления */
  }
}

class DocumentIssue implements IIssue {
  async getCreateDTOForServer() {
    /* Обращение к бэку*/
  }

  async getUpdateDTOForServer() {
    /* Возвращает объект DTO для обновления */
  }

  async getCreateDTOForIndexDB() {
    /* Обращение к бэку*/
  }

  async getUpdateDTOForIndexDB() {
    /* Возвращает объект DTO для обновления */
  }
}
```

---

Это было бы уместно, если бы сами классы _Issue_ задумывались бы как классы для работы с _DTO_.

Но в нашем случае это не так. У нас классы в первую очередь были предназачены для работы с _Issue_ на 3D модели.

Поэтому это хорошо, если мы можем вынести создание логики _DTO_ из них.

---
