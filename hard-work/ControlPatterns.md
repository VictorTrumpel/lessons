# Отчет по работе абстрагируем управляющие паттерны

## Пример 1.

---

Было:

---

```typescript
function read() {
  let isCancel = false;

  const readableStream = fetch();

  let response;

  for (const chunk of readableStream) {
    if (isCancel) break;
    response += chunk;
  }

  // где-то внутри мог меняться isCancel
}
```

---

Данный код в императивном стиле описывает паттерн управления стримом.  
Переключение переменной isCanсel=true - это аналогично stream.close().
Декларативное управление стримом лучше читается

Стало:

---

```typescript
function read() {
  let isCancel = false;

  const stream = new Stream();

  stream.open();

  let response;

  stream.onChunk((chunk) => {
    response += chunk;
  });

  // ...

  // преждевременное закрытие стрима
  stream.close();
}
```

## Пример 2.

---

В проекте есть разные стримы, которые должны обмениваться данными друг с другом.
Для этого есть StreamMediator.

addStream() - добавляет stream в мапу, removeStream() - удаляет.

Стримов может быть много и что бы не захламлять мапу нужно не забывать удалять стримы.

---

```typescript
class StreamMediator {
  streamMap = new Map();

  addStream() {}

  removeStream() {}
}
```

---

Решение использует возможности JS-а. Вместо Map используем WeakMap. Если на стрим никто не ссылается - эта структура данных сама удаляет запись.
Это подходит для нашей бизнес логики.
Мы можем избавиться от метода и не думать над тем, что бы удалять стримы вручную. Это можно не документировать.

---

```typescript
class StreamMediator {
  streamMap = new WeakMap();

  addStream() {}
}
```

### Пример 3.

```typescript
class IfcScene {
  constructor(parameters) {
    const { needCamera, needShadows, needFog } = parameters;

    if (needCamera) {
      //...
    }

    if (needShadows) {
      //...
    }

    if (needFog) {
      //...
    }
  }
}
```

---

Классу выше передается конфиг. В зависимости от флага в конфике в контрукторе производится различная логика.

Поэтому конструктор класса очень большой.

---

---

Поэтому я избавился от конфига и сделал создание более декларативным. При помощи методов.

Преимущество в том, что мы можем вызвать только initCamera() и нам ничего дополнительно не придется передавать для Fog и Shadows.

---

```typescript
class IfcScene {
  initCamera() {}

  initShadows() {}

  initFog() {}
}

const scene = new IfcScene();
scene.initCamera();
scene.initFog();
scene.initShadows();
```
