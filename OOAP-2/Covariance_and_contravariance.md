# Ковариантность и контравариантность

---

Пример с ковариантностью

---

```typescript
class Animal {
  name: string;

  constructor(name: string) {
    this.name = name;
  }
}

class Cat extends Animal {
  constructor(name: string) {
    super(name);
  }
}

class Dog extends Animal {
  constructor(name: string) {
    super(name);
  }
}

// можем передать любого наследника от Animal
class Container<T extends Animal> {
  private content: T;

  constructor(content: T) {
    this.content = content;
  }

  getContent(): T {
    return this.content;
  }
}

let animalContainer: Container<Animal> = new Container<Animal>(
  new Animal("Generic Animal")
);
let catContainer: Container<Cat> = new Container<Cat>(new Cat("Kitty"));
```

---

Пример с контравариантность

---

```typescript
class Printer {
  print(animal: Animal): void {
    console.log(`Printing animal: ${animal.name}`);
  }
}

class CatPrinter extends Printer {
  print(cat: Cat): void {
    console.log(`Printing cat: ${cat.name}`);
  }
}

function usePrinter(printer: (animal: Animal) => void) {
  const animal = new Animal("Generic Animal");
  printer(animal);
}

const catPrinter: (cat: Cat) => void = (cat) =>
  console.log(`Printing only cats: ${cat.name}`);

// Контравариантность: (cat: Cat) => void может быть использован там, где ожидается (animal: Animal) => void
usePrinter(catPrinter);
```

---

Контравариантность в делегатах

---

```typescript
// ограничиваем, что принимает только наследников Animal
type Action<T extends Animal> = (item: T) => void;

function performActionOnAnimals(action: Action<Animal>) {
  const dog = new Dog("Rex");
  action(dog);
}

const printCatName: Action<Cat> = (cat: Cat) => console.log(cat.name);

// Здесь мы передаем функцию, которая работает с `Cat`, вместо функции, которая работает с `Animal`
performActionOnAnimals(printCatName);
// Контравариантность: функция, которая принимает `Cat`, может использоваться вместо функции, которая принимает `Animal`
```
