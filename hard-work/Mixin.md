# Отчет по миксинам

---

Пример 1

---

```javascript
const g1 = {};
const g2 = {};
const g3 = { area: 300 };

// функция миксин, которая домешивает в объект необходимое поле и метод
const mixinCalculateCost = (obj) => {
  obj.area = obj.area || 0;
  obj.calculateCost = function (price) {
    return this.area * price;
  };
};

// вот так можно домешать ко всем объектам нужные поля и методы
[g1, g2, g3].forEach(mixinCalculateCost);
```

---

Пример 2

---

```javascript
// функция, которая примешивает к объекту mixin
const extend = (obj, mixin) => {
  const keys = Object.keys(mixin);

  for (const key of keys) {
    obj[key] = mixin[key];
  }

  return obj;
};

// Usage
const user = {
  name: "Markus Aurelius",
  city: "Rome",
};

const mixin = {
  printName() {
    console.log("name :>> ", this.name);
  },

  toString() {
    return `Name: ${this.name}, city: ${this.city}`;
  },
};

// вот так можем добавить необходимый функционал к инстансу user при помощи миксина
extend(user, mixin);

user.printName();

console.log(user.toString());
```

---

Пример 3

---

```javascript
const obj1 = {
  name: "Markus Aurelius",
  city: "Rome",
};

const mixin1 = {
  printName() {
    console.log("name :>> ", this.name);
  },
};

const mixin2 = {
  printCity() {
    console.log("city :>> ", this.city);
  },
};

// Так же JS поддерживает возможность добавления миксинов "из коробки"
const res = Object.assign(obj1, mixin1, mixin2);

res.printCity();
res.printName();
```

---

Миксины очень опасная техника программирования.

В React еще используют HOC - функция высшего порядка.

Аналог миксина - можно добавить компоненту дополнительное поведение обернув его в функцию.  
Но это усложняет и читаемость и понимание кода в 100 раз.

Я думаю миксины можно использовать, если кейс их использования такой же прозрачный как в примерах выше (по сути в учебных примерах).

---
