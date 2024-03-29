# Отчет по ясному коду

---

1.1. Метод используется только в тестах.

Было:

---

```typescript
class IssueSaverForIndexDB {
  private _indexDb: IndexDB;

  set indexDB(indexDb: IndexDB) {
    this._indexDb = indexDb;
  }

  get indexDB() {
    return this._indexDb;
  }

  /* методы по записыванию в БД чего-либо */
}
```

---

Стало:

---

```typescript
class IssueSaverForIndexDB {
  private _indexDb: IndexDB;

  set indexDB(indexDb: IndexDB) {
    this._indexDb = indexDb;
  }

  /* методы по записыванию в БД чего-либо */
}
```

---

Геттер indexDB использовался только в тестах. Для того, что бы получать инстанс индекс ДБ и проверять как в него что-либо записалось.

На самом деле это было лишним. Мы можем просто передать инстанс индекс ДБ через сеттер и сохранить инстанс нашей ДБ в переменной. Ссылка на объект будет одна и та же. И мы итак будем иметь доступ к индек ДБ без всяких геттеров.

---

---

1.2. Цепочка методов.

Было:

---

```javascript
class UserListBuilder {
  buildUserList(users: User[]) {
    this.setPostForUser(users);
  }

  private setPostForUser(users: User[]) {
    this.setProjectForUser(users);

    users.forEach((u) => {
      u.name = this.getName(u);
    });
  }

  private setProjectForUser(users: User[]) {
    users.forEach((u) => {
      u.name = this.getProject(u);
    });
  }
}
```

---

Стало:

---

```typescript
class UserListBuilder {
  buildUserList(users: User[]) {
    this.setPostForUser(users);
    this.setProjectForUser(users);
  }

  private setPostForUser(users: User[]) {
    users.forEach((u) => {
      u.name = this.getName(u);
    });
  }

  private setProjectForUser(users: User[]) {
    users.forEach((u) => {
      u.name = this.getProject(u);
    });
  }
}
```

---

Метод _setPostForUser_ нуждался в том, что бы у пользователя был определен проект, что бы определить его должность. И поэтму в теле метода _setPostForUser_ вызывался метод _setProjectForUser_. Это конечно же плохо, когда мы, вроде бы выставляя должность человека, оказывается ненароком выставляем ему еще и проект. Неявная логика.

---

---

1.3. У метода слишком большой список параметров.

Было:

---

```typescript
//
class IfcApi {
  highlightRoomAndSetClipper(
    idsOfRooms: number[],
    x: number,
    y: number,
    z: number
  ) {
    this.api.pickElements(idsOfRooms);

    const clipper = new Clipper();
    clipper.setClipperPositionX(x);
    clipper.setClipperPositionY(y);
    clipper.setClipperPositionZ(z);
  }
}
```

---

Стало:

---

```typescript
class IfcApi {
  setClipper(x: number, y: number, z: number) {
    const clipper = new Clipper();
    clipper.setClipperPositionX(x);
    clipper.setClipperPositionY(y);
    clipper.setClipperPositionZ(z);
  }

  highlightRoom(idsOfRooms: number[]) {
    this.api.pickElements(idsOfRooms);
  }
}
```

---

Метод _highlightRoomAndSetClipper_ имел слишком много аргументов и делал слишком много.

Разделил его на 2 метода тем самым увеличив читаемость кода и снизив количество аргументов.

---

---

1.4 и 1.5. Метод возвращает больше данных чем нужно и чрезмерный результат.

Этим примером закрою сразу 2 кейса:

---

```typescript
class IfcApi {
  pickElement(): Element {
    /* подсветка и возврат объекта элемента */
  }

  highlightElement(): Element {
    /* подсветка и возврат объекта элемента */
  }
}
```

```typescript
class IfcApi {
  pickElement(): Element {
    /* возврат объекта элемента без подсветки */
  }

  highlightElement() {
    /* только подсветка */
  }
}
```

---

Было 2 метода, которые делали абсолютно одно и то же. Подсвечивали и возвращали элемент.
Из-за этого в разных частях кода использовался то один, то другой метод.

- метод _highlightElement_ возвращал избыточные данные - сам элемент. Хотя в названии его метода нет ничего про возвращение элемента.

Таким образом, я сделал, что бы _pickElement_ ТОЛЬКО возвращал элемент, а _highlightElement_ ТОЛЬКО подсвечивал его.

Таким образом я закрыл пункт 1.4 и 1.5 задания одним кейсом.

---
