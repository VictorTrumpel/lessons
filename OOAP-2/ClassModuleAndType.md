# Класс как модуль 

### Класс как модуль - базовая синтаксическая единица. Как это выглядит в Typescript?

Во-первых, есть ключевое слово *class* для описания класса.

```typescript
class Vehicle {

}
```

Для наследования функциональностей мы используем слово *extends*

```typescript
class Car extends Vehicle {}
```

Если в дочернем классе мы определяем конструктор, то до того как вызвать this мы должны вызвать конструктор родительского класса

```typescript
class Car extends Vehicle {
  constructor() {
    super() // вызываем конструктор родительского класса
  }
}
```

Если внутри дочернего класса мы хотим обратиться к функциональности родительского класса, то мы делаем это через ключевое слово *super*

```typescript
class Car extends Vehicle {
  move() {
    super.move()
  }
}
```

"Под капотом" наследование в javascript работает через прототипирование.

```typescript
const vehicle {
  move() {}
}

const car = {
  __proto__: vehicle, // указываем прототип для объекта car
}

car.move() // в момент, когда вызываем move этот метод вначале ищется в car, а затем идет искать в цепочке прототипов

// соответственно, вызов класса Car extends Vehicle
new Car() // создает объект с цепочкой прототипов до Vehicle
```

### Как импортировать класс?

Импорт класса зависит от среды. Если мы работаем в браузере мы можем подключить класс как отдельный скрипт на страницу.

```html
<script src='путь до скрипта на сервере' />
```
 
Но так же, мы можем использовать сборщики, которые собирают код в один большой файл, так называемый бандл.
Если мы используем сборщик, то нам доступен синтаксис import/export.
Однако, в новых версиях браузера этот синтаксис тоже поддерживается.

```typescript
// файл-1
class Car {}
```

```typescript
// файл-2

import Car from '../'

const car = new Car()
```

Итоговый бандл будет выглядеть так:

```typescript
// код из файла "файл-1"
class Car {}

// код из файла "файл-2"
const car = new Car()
```

Сборщик выстраивает код в исходном бандле в правильной последовательности.

Если мы работаем в среде Node.js, то мы можем использовать синтаксис 
*module.export*, *require* (именно его сборщики и используют под капотом).

### Модификаторы доступа

Взаимосвязи между модулями можно контролировать с помощью модификаторов доступа.

В Typescript есть следующие модификаторы: *private*, *protected*, *readonly*, *public*

В нативном Javascript есть *private* поля, они обозначаются через *#*
