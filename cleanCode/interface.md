```javascript
const heroWithSword = new Hero({ sword: true }) // не очень хорошо
const heroWithSword = Hero.createWithSword() // фабрика, которая указывает, что герой должен быть создан с мечом

const date = new Date() 
const date = Date.todayDate() // фабрика наглядно указывает на то, что нужно сгенерировать текущую дату

const signer = new User({ isSigner: true })
const signer = User.createSigner() // фабрика наглядно указывает, что нужно создать нового пользователя, который будет являться подписантом
```

```typescript
// мы можем описать такой интерфейс, который будут имплементировать все игровые объекты
interface InteractiveObject {
  onClick(): void
}

// класс, который создает героя, который реагирует на клик
class Hero implements InteractiveObject {

  constructor() {
    //...
  }

  onClick() {

  }
}

// класс, который создает врага, который реагирует на клик
class Enemy implements InteractiveObject {

  constructor() {
    //...
  }

  onClick() {
    
  }
}

// в дальнейшем можно указывать InteractiveObject в качестве типа.
// Например, наш герой (Hero) и враг (Enemy) находятся на этой сцене.
class Scene {

  // указываем, что сюда попадает интерактивный объект, при этом на не важно что это и какой это класс
  onClick = (gameObject: InteractiveObject) => {

    // вызываем метод onClick на интерактивном объекте. Такой код обрабатывает клики по Hero и по Enemy 
    // без надобности написания if-ов
    gameObject.onClick()
  }

  constructor() {

    // сцена обрабатывает клики по всем игровым объектам
    this.input.on('gameobjectdown', this.onClick)
  }
}

```