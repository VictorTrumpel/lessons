**1**
```javascript
let i = 0
while (i < length) {
  // операция
  i++
}

// локализовал область через :
for (let i = 0; i < length; i++) {
  // операция
}
```

**2**
```javascript

const renderDocument = () => {
  let header = null
  let body = null
  let footer = null

  // код по рендеру документа, который изменяет header, body, footer
}

// локализую при помощи разбиения кода на под функции
// каждая функция работает отдельно со своей переменной
const renderDocument = () => {
  renderHeader()
  renderBody()
  renderFooter()
}
```

**3**
```typescript
class UserProfile {
  public name: string
  public post: string

}

// сузил доступ к полям через private и геттеры
class UserProfile {
  private _name: string
  private _post: string

  get name() {
    return this._name
  }

  get post() {
    return this._post
  }
}

// из-за легаси, я уже не могу просто обрубить доступ к полям name, но я могу сделать их только читаемыми, и изменяемыми только в классе
```

**4**
```javascript
class TableNodeRenderer {
  private cols: number
  private rows: number

  renderTableNode() {
    // использум поля cols и rows
  }
}

// сужаю только до функциональной области видимости 
class TableNodeRenderer {
  renderTableNode(cols: number, rows: number) {
    // использум cols и rows
  }
}
```

**5**
```typescript

// Базовый класс, которые делает запросы на бэк, от него наследуются другие классы,
// которые делают более специализированные запросы
class BaseApi {
  // эта переменная должна быть видна классам наследникам
  public baseUrl: string
}

// сужаю область видимости:
class BaseApi {
  // эта переменная должна быть видна классам наследникам
  protected baseUrl: string
  // но "внешнему" миру её видеть не обязательно
}
```

**6**
```typescript
const divWrapper = document.createElement('div')

divWrapper.classList.add('wrapper')
// далее используем эту обертку

// сужаю область видимоти - заключаю её в функцию
const wrap = (node: HTMLElement) => {
  const divWrapper = document.createElement('div')

  divWrapper.classList.add('wrapper')

  // исполняю логику по оборачиванию ноды в обертку
}
```

**7**
```typescript
// глобальные переменные
let backgroundColor = 'ggsgsd'
let themeModes = ['dark' | 'light']

// сужаем их область видимости до класса, внутри которого они подконтрольно изменяются
class StyleBook {
  backgroundColor: string
  themeModes: [string, string]
}
```

**8**
```typescript
// глабальная переменная, которая при инициализации берет свои данные из локал-стора
let localTemplateData = localStorage.getItem('id')

// сужаем область видимости
class LocalTemplate {
  localTemplateData: Record<string, unknown>
}
```

**9**
```typescript
const userProfile = new UserProfile()

// много кода...

sendUserProfile(userProfile)

// сужаю область видимости до функции

function sendUserProfile() {
  const userProfile = new UserProfile()
  sendUserProfile(userProfile)
}

sendUserProfile()

// много кода...
```

**10**
```javascript
const activeTemplate = document.template
activeTemplate.formData.forEach((formItem) => {
  // операции по рендеру
}) 

// много кода...

// сужаю область видимости до функции

function renderActiveTemplate() {
  const activeTemplate = document.template
  activeTemplate.formData.forEach((formItem) => {
    // операции по рендеру
  }) 
}

renderActiveTemplate()

// много кода...
```

**11**
```javascript

function moveHero() {

  const heroVectors = getHeroVectors()

  for (let i = 0; i < heroVectors.length; i++) {
    multiplyVector(heroVectors[i])
  }

  // много кода по остальной логике, связанной с пермещением персонажа по экрану
}

// сужаю область видимости

function multiplyHeroVectors() {
  const heroVectors = getHeroVectors()

  for (let i = 0; i < heroVectors.length; i++) {
    multiplyVector(heroVectors[i])
  }
}

function moveHero() {
  multiplyHeroVectors()
  // много кода по остальной логике, связанной с пермещением персонажа по экрану
}
```

**12**
```javascript
const owners = []

document.owners.forEach((docOwner) => {
  owners.push(/* добавляем пользователя docOwner на основе чего-то */)
})

sendOwners(owners)

// остальной код...

// сужаем область видимости:

function sendOwners() {
  // задаем значение массиву owners сразу при инициализации -> 
  const owners = document.owners.map((docOwner) => {
    return /* добавляем пользователя docOwner на основе чего-то */
  })
  // отправляем пользователей на бэк
}

sendOwners(owners)

// остальной код...
```

**13**
```typescript
class GoodsCounter {
  goodsBook = {
    'apples': 0,
    'magazines': 0
  }
}

// выставляю идентификатор доступа private
class GoodsCounter {
  private goodsBook = {
    'apples': 0,
    'magazines': 0
  }
}
```

**14**
```typescript
class BaseApi {
  // эта переменная должна быть видна классам наследникам
  protected baseUrl: string

  send(data: Record<string, unknown>) {
    const stringRequest = this.stringyfyRequest()
  }

  stringyfyRequest() {

  }
}

// ограничиваем доступ к методу stringyfyRequest
class BaseApi {
  // эта переменная должна быть видна классам наследникам
  protected baseUrl: string

  send(data: Record<string, unknown>) {
    if (!this.isRequestValid(data))
      return
    const stringRequest = this.stringyfyRequest()
  }

  private stringyfyRequest() {

  }

  isRequestValid() {

  }
}
```

**15**
```typescript
// ограничиваем доступ к методу isRequestValid
class BaseApi {
  // эта переменная должна быть видна классам наследникам
  protected baseUrl: string

  send(data: Record<string, unknown>) {
    if (!this.isRequestValid(data))
      return
    const stringRequest = this.stringyfyRequest()
  }

  private stringyfyRequest() {

  }

  private isRequestValid() {

  }
}
```