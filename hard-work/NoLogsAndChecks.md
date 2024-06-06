# Отчет по работе отладка, логи, проверки – это плохой подход.

---

Пример 1

---

```typescript
// было:
const handleUploadFile = (file: File) => {
  if (file.name.endsWith(".ifc")) {
    // проверка на расширение
  }
};

<input onChange={handleUploadFile} />;

// стало:
const handleUploadFile = (file: File) => {};

<input onChange={handleUploadFile} accept=".ifc" />;
```

---

Пользователь может загружать только файл формата .ifc. Изначально проверка на уровне TS. Но если знать предметную область, то можно прописать атрибут тегу accept. И тогда ситуация, что в _handleUploadFile_ попадет НЕ .ifc невозможна.

(детально файл проверяется на бэке.)

---

---

Пример 2

---

```typescript
// было:
class CheckList {
  status: "PENDING" | "SUCCESS" | "REJECTED";

  open() {
    if (this.status === "SUCCESS") return;
    // ...
  }

  send() {
    if (this.status === "REJECTED") return;
    // ...
  }

  close() {
    if (this.status === "PENDING") return;
    // ...
  }
}

// стало:
class PendingCheckList {}
class SuccessCheckList {}
class RejectedCheckList {}
```

---

Изначально у поля _status_ множетво состояний. И в каждом методе приходится проверять, а соответствует ли логика этого метода текущему состоянию.

Но это не требуется делать, если применить автомат состояний.

Например в классе _PendingCheckList_ по определению могут быть описаны только методы, которые работают при состоянии _PENDING_

---

---

Пример 3

---

```typescript
// было:
class CheckList {
  constructor(props: any) {
    if (!props.name) return;
    if (!props.value) return;
    if (!props.factValue) return;
    // ...
  }
}

// стало:
type CheckListProps = {
  // ...
};

class CheckList {
  constructor(props: CheckListProps) {
    // ...
  }
}
```

---

Нужно типизировать пропсы при помощи TS, которые можно передать в конструктор и больше не создавать никаких проверок.

---

---

Пример 4

---

```typescript
// было:
if (user?.name) <UserLabel name={user.name} />;
if (user?.state) <UserState state={user.state} />;
// ...

// стало

if (!user) return <>Loading...</>;

<UserLabel name={user.name} />;
<UserState state={user.state} />;
```

---

Пользователь мог быть не загружен и быть undefined. И потом на отображение каждого его св-ва устанавливались проверки.

Поняв бизнес логику, что без пользователя _впринципе_ нет смысла что-то отображать - заменил только на одну проверку пользователя. Если он загружен - он должен быть загружен сразу с правильными полями.

---

---

Пример 5

---

```typescript
// было:
if (slider.positionY > 0) {
  clipperPlane.move(slider.positionY);
}

// стало:
clipperPlane.move(slider.positionY);
```

---

Плоскость подрезки управлялась слайдером. Но слайдер по какой-то причине мог опускаться на уровень ниже нуля. Поэтому поправил логику слайдера, что бы он всегда выдавал правильные значения. Соответственно можно не бояться, что _positionY_ стане отрицательной

---
