// сужение

// есть общий класс игрового объекта с широким функционалом
class Sprite {
  moveX() {}
  moveY() {}
  attack() {}
  hurt() {}
}

interface IDecorSprite {
  moveX: Sprite['moveX']
  moveY: Sprite['moveY']
}
// класс спрайт-окружение (задний фон). Птице не нужны методы attack() и hurt() т.к. это лишь декорация
// я получаю функциональность moveX() и moveY через наследование класса Sprite
// но при этом, я ограничиваю её через интерфейс
class BirdSprite extends Sprite {
  // что бы TS понял, что я ограничиваю тип, приходится делать такой трюк.
  create() {
    return this as IDecorSprite
  }
}

const birdSprite = new BirdSprite().create()

// расширение

// Мы можем создать общий класс и определить в нем только базовые методы
class Sprite {
  moveX() {}
  moveY() {}
}

// класс волка наследует фунциональность спрайта и расширяет её дополнительно
class Wolf extends Sprite {
  attack() {}
  hurt() {}
}