# Отчет о проделанной работе по Три правила простого проектного дизайна

## Правило 1.

---

избавляться от точек генерации исключений, запрещая соответствующее ошибочное поведение на уровне интерфейса класса

---

```typescript
// Пример 1

// если *clipperPlane* не создана и вызван метод moveClipperToY, выбрасывается ошибка.

class Clipper {
  private clipperPlane;

  moveClipperToY(y: number) {
    if (!this.clipperPlane) {
      throw new Error("clipperPlane does not exist!");
    }
  }

  moveClipperToX(x: number) {
    if (!this.clipperPlane) {
      throw new Error("clipperPlane does not exist!");
    }
  }
}

/*
  Когда мы вызываем методы движения clipperPlane, мы итак подразумеваем, что она существует.
*/
class Clipper {
  private clipperPlane;

  moveClipperToY(y: number) {
    if (!this.clipperPlane) {
      this.createClipperPlane();
    }
  }

  moveClipperToX(x: number) {
    if (!this.clipperPlane) {
      this.createClipperPlane();
    }
  }

  private createClipperPlane() {}
}
```

```typescript
// Пример 2
// Герой и враг должны отаковать друг друга поочередно.
// Джун может ошибиться и 2 раза написать: hero.attack(enemy);

class Sprite {}

class Hero extends Sprite {
  attack(sprite: Sprite) {}
}

class Enemy extends Sprite {
  attack(sprite: Sprite) {}
}

const hero = new Hero();
const enemy = new Enemy();

while (true) {
  hero.attack(enemy);
  enemy.attack(hero);
}
```

```typescript
// Пример 2
// Герой и враг должны отаковать друг друга поочередно.
// Но всю эту логику реализовывает класс Battle.
// просто вызываем всегда battle.attack() и не ошибаемся.

class Sprite {}

class Battle {
  constructor(hero: Hero, enemy: Enemy) {}

  attack() {}
}

class Hero extends Sprite {
  attack(sprite: Sprite) {}
}

class Enemy extends Sprite {
  attack(sprite: Sprite) {}
}

const hero = new Hero();
const enemy = new Enemy();

const battle = new Battle(hero, enemy);

while (true) {
  battle.attack();
}
```

## Правило 2.

---

отказаться от дефолтных конструкторов без параметров, и передавать конструктору обязательные аргументы

---

```typescript
// Пример 1.

// Прежде чем класс Battle мог бы реализовать логику атаки спрайтов.
// ему эти спрайты нужно добавить через метод addSprite()
// можно забыть предварительно добавить эти спрайты и ошибиться.

class Battle {
  sprites: Sprite[] = [];

  constructor() {}

  attack() {}

  addSprite() {}
}

class Hero extends Sprite {
  attack(sprite: Sprite) {}
}

class Enemy extends Sprite {
  attack(sprite: Sprite) {}
}

const hero = new Hero();
const enemy = new Enemy();

const battle = new Battle();
battle.addSprite(hero);
battle.addSprite(enemy);

while (true) {
  battle.attack();
}
```

```typescript
// Делаем конструктор Battle обязательным и он сразу создается с нужными спрайтами.
// когда вызовем attack() - больше не ошибемся.

class Battle {
  sprites: Sprite[] = [];

  constructor(hero: Hero, enemy: Enemy) {}

  attack() {}
}

class Hero extends Sprite {
  attack(sprite: Sprite) {}
}

class Enemy extends Sprite {
  attack(sprite: Sprite) {}
}

const battle = new Battle(hero, enemy);
```

```typescript
// Пример 2.
// Оповещатель админов. Добавляем админов и только потом оповещаем.
// Но можем и забыть кого-то добавить. И какой-то админ не оповестится.

class Admin {
  emit() {}
}

class AdminEmitter {
  addAdmin(admin: Admin) {}
}

const adminEmitter = new AdminEmitter();
adminEmitter.addAdmin(chatAdmin);
adminEmitter.addAdmin(documentAdmin);

// ...
adminEmitter.emit();
```

```typescript
// Пример 2.
// Делаем конструктор AdminEmitter обязательным

class Admin {
  emit() {}
}

class AdminEmitter {
  constructor(adminList: Admin[]) {}
}

const adminEmitter = new AdminEmitter([chatAdmin, documentAdmin]);

// ...

adminEmitter.emit();
```

## Правило 3.

---

избегать увлечения примитивными типами данных, разрабатывать прикладную систему типов, на смысловом уровне моделирующую предметную область (используйте типы данных Клетка и Фигура, а не строки или числа).

---

```typescript
// Пример 1.
// Передается тип спрайта (enemy | hero), который нужно атаковать. Вместо самого объекта.
// Можно передать "nothing"
// + приходится делать дополнительное сопоставления типа с реальным объектом

class Hero extends Sprite {
  attack(spriteType: string) {}
}

class Enemy extends Sprite {
  attack(spriteType: string) {}
}

const hero = new Hero();
const enemy = new Enemy();

while (true) {
  hero.attack("enemy");
  enemy.attack("hero");
}
```

```typescript
// Указываем настоящий тип
// Уже внутри метода можно работать с реальным объектом без дополнительного сопоставления.

class Hero extends Sprite {
  attack(spriteType: Enemy) {}
}

class Enemy extends Sprite {
  attack(spriteType: Hero) {}
}

const hero = new Hero();
const enemy = new Enemy();

while (true) {
  hero.attack(enemy);
  enemy.attack(hero);
}
```

```typescript
// Пример 2.
// что бы отправить сообщение какому-либо пользователю мы указываем id этого пользователя.
// Внутри метода делаем сопоставление.
// Можно передать не валидный id и ошибиться.

class Messager {
  sendMessage(userId: number) {}
}
```

```typescript
// передаем реальный объект. Так уже точно не ошибемся
class Messager {
  sendMessage(user: User) {}
}
```
