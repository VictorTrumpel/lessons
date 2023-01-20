**1**
```typescript
// запрет на неявное определение в конфиге Typescript-а
{
  "noImplicitAny": true
}
```

**2**
```typescript
// вывожу ошибку, если значение `context` не верное. Это происходит из-за того, useDocumentContext вызывается в неправильном месте

const useDocumentContext = () => {
  const context: DocumentContext = useContext(DocumentProvider)

  if (!conterxt) 
    throw new Error('useDocumentContext must be used inside <DocumentProvider />')

  return conterxt
}
```

**3**
```javascript
const signersList = document.signers

// много кода

signersList.forEach(() => {
  // ...
})

// переделал в
// определил переменную близко к моменту использования
const signersList = document.signers
signersList.forEach(() => {
  // ...
})
```

**4**
```javascript
let i = 0
while(i < documents.length) {
  // операция

  i++
}

// переделал в  
for (let i = 0; i < documents.length; i++) {
  // операция  
}
```

**5**
```javascript
// улучшение предыдущего кода. Лучшая переменная - отсутствующая перменная)
documents.forEach((document) => {
  // операция 
})
```

**6**
```javascript
// произвел проверку важного значения
const htmlToPdf = (html: HTMLElement) => {
  if (!(html instanceof HTMLElement)) 
    throw new Error('htmlToPdf need to pass HTMLElement!')
}
```

**7**
```typescript
const multiplyVector = (vector: [number, number, number], count: number) => {
  const multiplyVector = []

  let i = 0
  while (i < vector.length) {
    multiplyVector[i] = vector[i] * count

    i += 1
  }

  return multiplyVector
}

// переделал в 
const multiplyVector = (vector: [number, number, number], count: number) => {
  const multiplyVector = []

  for (let i = 0; i < vector.length, i++) {
    multiplyVector[i] = vector[i] * count
  }

  return multiplyVector
}
```

**8**
```typescript
// улучшение предыдущего примера
const multiplyVector = (vector: [number, number, number], count: number) => {
  return vector.map(coord => coord * count)
}
```

**9**
```typescript

let activeTemplate = document.template

// много кода

activeTemplate.formData.forEach((formItem) => {
  // операции по рендеру
}) 

// переделал в : 

// много кода
let activeTemplate = document.template
activeTemplate.formData.forEach((formItem) => {
  // операции по рендеру
}) 
```

**10**
```typescript
// улучшение предыдущего примера
// объявляем переменную как const т.к. она не изменяется (и не должна) в дальнейшем
const activeTemplate = document.template
activeTemplate.formData.forEach((formItem) => {
  // операции по рендеру
}) 
```

**11**
```typescript
// улучшение предыдущего примера: 
// типизируем нашу переменную
const activeTemplate: TemplateDocument = document.template
activeTemplate.formData.forEach((formItem) => {
  // операции по рендеру
}) 
```

**12**
```typescript
// улучшение предыдущего примера
let activeTemplate = document.template
activeTemplate.formData.forEach((formItem) => {
  // операции по рендеру
}) 

// завершаем работу с переменной
let activeTemplate = '**ERROR**'

if (activeTemplate instanceof TemplateDocument) {
  // код не выполнится
}
```

**13**
```typescript
class UserProfile {
  name = 'Name'
  post = 'Post'
  project = 'Project'

  constructor() {

  }
}

// переделал в 
class UserProfile {
  name: string
  post: string
  project: string

  constructor() {
    this.name = 'Name'
    this.post = 'Post'
    this.project = 'Project'
  }
}
```

**14**
```typescript
// улучшение предыдущего примера
// возможно, для того, что бы сохранить узкий тип у полей и при этом сохранять стиль кодирования
// лучше записать таким образом:
class UserProfile {
  name: 'Name'
  post: 'Post'
  project: 'Project'

  constructor() {
    this.name = 'Name'
    this.post = 'Post'
    this.project = 'Project'
  }
}

// в таком случае, поля name, post, project имеют тип 'Name' , 'Post', 'Project' 
// и стали косвенно иметь идентификатор readonly (т.к. их все-равно не возможно перезаписать на другие значения, а читать можно), но это выдуманный пример.

// на практике, если мы хотим сделать поля неизменяемыми, но читаемыми, то все-таки лучше записать так, я думаю

class UserProfile {
  readonly name: string
  readonly post: string
  readonly project: string

  constructor() {
    // интересная возможность TS в том, что в конструкторе мы можем присвоить значения полям
    this.name = 'Name'
    this.post = 'Post'
    this.project = 'Project'
  }

  print() {
    // тут мы уже не можем изменить поле name - TS подсветит ошибку
    this.name = 'gdsjlg;skl;'
  }
}
```

**15**
```typescript
const userProfile = new UserProfile()

// много кода...

sentUserProfile(userProfile)

// переделал в :
const userProfile: UserProfile = new UserProfile() // в данном случае TS автоматически вставит тип UserProfile для userProfile, но я прописал для наглядности
sentUserProfile(userProfile)

// либо можно так: 
let userProfile: UserProfile | null  = new UserProfile()
sentUserProfile(userProfile)

// производим очистку переменной
userProfile = null

// хотя, честно говоря, способ с очисткой значений кажется мне немного монструозным
```