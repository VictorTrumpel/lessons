// Динамическое связывание

// Базовый класс "Животное"
class Animal {
  // Метод speak будет переопределён в классах-наследниках
  speak(): string {
    return "";
  }
}

// Класс "Собака", наследует "Животное"
class Dog extends Animal {
  // Переопределение метода speak для класса Dog
  speak(): string {
    return "Woof!";
  }
}

// Класс "Кошка", наследует "Животное"
class Cat extends Animal {
  // Переопределение метода speak для класса Cat
  speak(): string {
    return "Meow!";
  }
}

// Функция, которая вызывает метод speak для любого животного
function makeAnimalSpeak(animal: Animal): string {
  // Здесь используется динамическое связывание:
  // метод speak вызывается на основе фактического типа объекта.
  return animal.speak();
}

// Пример использования
let dog: Animal = new Dog();
let cat: Animal = new Cat();

// Вызов метода speak через функцию makeAnimalSpeak
console.log(makeAnimalSpeak(dog)); // Выведет "Woof!"
console.log(makeAnimalSpeak(cat)); // Выведет "Meow!"

// таким образом, мы работаем с базовым типом, но фактически, используем методы производных классов.
