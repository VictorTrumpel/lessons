class General {
  generalMethod() {
    console.log("This is a General method.");
  }
}

class Any {
  anyMethod() {
    console.log("This is an Any method.");
  }
}

// Класс-лист, не имеющий потомков
class Leaf extends General {
  leafMethod() {
    console.log("This is a Leaf method.");
  }
}

// Миксин для множественного наследования
function mixin<T1, T2>(Base1: T1, Base2: T2): T1 & T2 {
  return class extends (Base1 as any) {
    constructor(...args: any[]) {
      super(...args);
      Object.assign(this, new (Base2 as any)());
    }
  } as unknown as T1 & T2;
}

// Класс None, наследуемый от всех классов-листьев
class None extends mixin(Leaf, Any) {
  // Пустой класс, который представляет отсутствие значения
}

const noneInstance = new None();
noneInstance.leafMethod(); // Выведет: This is a Leaf method.
noneInstance.generalMethod(); // Выведет: This is a General method.
noneInstance.anyMethod(); // Выведет: This is an Any method.

// Таким образом:

function processObject(obj: General) {
  if (obj instanceof None) {
    console.log("Received a None object, representing void.");
  } else {
    obj.generalMethod();
  }
}

const someObject = new Leaf();
const voidObject = new None();

processObject(someObject); // Выведет: This is a General method.
processObject(voidObject); // Выведет: Received a None object, representing void
