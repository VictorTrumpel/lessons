// Принцип Открыт/Закрыт применительно к иерархии классов

class BaseClass {
  constructor() {
    if (
      new.target !== BaseClass && // тут мы смотрим от какого протитипа был создан инстанс.
      // если создан не от BaseClass - уже кое-что подозреваем
      this.finalMethod !== BaseClass.prototype.finalMethod // тут мы смотрим, есть ли у инстанса метод, который мы хотим защитить
      // если есть, значит выбрасываем ошибку
    ) {
      throw new Error("Cannot override final method");
    }
  }

  finalMethod(): void {
    console.log("This method cannot be overridden");
  }
}

class DerivedClass extends BaseClass {
  finalMethod(): void {
    // Ошибка: Cannot override final method
    console.log("Trying to override");
  } // можем добавлять любые методы, но не можем переопределять этот
}

const derivedClass = new DerivedClass();
