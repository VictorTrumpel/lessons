// Создали класс Sprite отнаследовав от библиотечного класса
class Sprite extends Phaser.Arcade.Spirte {
  setVelocityX(velocity: number) {
    this.body.setVelocityX(velocity);
  }
  setVelocityY(velocity: number) {
    this.body.setVelocityY(velocity);
  }
}

// Создал класс меча от Spite и добавил метод проигрывания анимации атаки
class Sword extends Sprite {
  playAttackAnimation() {}
}

// создал класс Волка, отнаследовав от класса Sprite
class Wolf extends Sprite {
  private sword: Sword; // использовал композицию. Волк содержит меч.

  constructor() {
    this.sword = new Sword();
  }

  attack() {
    this.sword.playAttackAnimation();
  }

  hurt() {}
}

// создал класс сцены отнаследовав от библиотечного класса сцены
class Scene extends Phaser.Scene {
  removeSpriteFromScene(sprite: Phaser.Arcade.Spirte) {
    this.remove(sprite);
  }
}

const scene = new Scene();

const wolf = new Wolf();

scene.removeSpriteFromScene(wolf); // используем полиморфизм. Передаем тип волк, который есть тип спрайт
