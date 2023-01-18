**1**

```javascript

if (
  activeUsers.length > 0 && 
  Date.compareDate(document.date, newDate) === 1 &&
  Boolean(activeUsers.find((user) => user.id === currentUser.id))
)

// можно заменить на:
const hasCurrentUser = Boolean(activeUsers.find((user) => user.id === currentUser.id))
const isDocumentFresh = Date.compareDate(document.date, newDate) === 1

if (hasCurrentUser && isDocumentFresh)

```

**2**
```javascript

const docLoadFactor = 1 / users.length

// нужно добавить проверку:

const docLoadFactor = users.length === 0 
  ? 0
  : 1 / users.length

```

**3**
```javascript
const sendAction = 1 // код типа действия - константа

// переделал в
const SEND_ACTION = 'SEND_ACTION'
```

**4**
```javascript
const adminCode = 0 // код админа для проверки в коде внутри системы
// переделал в
const ADMIN_CODE = 'ADMIN'
```

**5**
```javascript

if (docLoadFactor === 1 && users.length >= 100)
// переделал в

const MAX_USERS_ABLE_SIGN = 100
const MAX_LOAD_FACTOR = 1

const isMaxUsersCount = users.length >= MAX_USERS_ABLE_SIGN
const isMaxLoadedFactor = docLoadFactor === MAX_LOAD_FACTOR

const isDocOverload = isMaxUsersCount && isMaxLoadedFactor

if (isDocOverload) {}

```

**6**
```javascript

if (user.status === 'USER_LOAD_SUCCESS') {}

// переделал в
const USER_LOAD_SUCCESS = 'USER_LOAD_SUCCESS'

const isUserLoadSuccess = user.status === USER_LOAD_SUCCESS

if (isUserLoadSuccess) {

}

```

**7**
```typescript
// баг, который я нашел и который связан с преобразованием типов

const getUserValue = (value: unknown) => String(value) 

// все преобразовывается к строке. В случае с числами - это ок, но при null | undefined 
// возвращается строка 'null' | 'undefined' соответственно. Это не ок.

// фикс
const getUserValue = (value: unknown) => {
  if (typeof value === 'number' || typeof value === 'string') {
    return String(value)
  }
  return ''
}
```

**8**
```typescript
// улучшение предыдущего примера
if (typeof value === 'number' || typeof value === 'string') {
  return String(value)
}

// изменил на 

const isValueNumber = typeof value === 'number'
const isValueString = typeof value === 'string'
const isValueValid = isValueNumber || isValueString

if (isValueValid) {
  return String(value)
}
```

**9**
```javascript

const docUsabilityFactor = (users.length / signers.length + documents.length / signers.length) / signers.length

// переписать в 

let docUsabilityFactor = 0

if (signers.length !== 0) {
  docUsabilityFactor = (users.length / signers.length + documents.length / signers.length) / signers.length
}
```

**10**
```javascript
const importantRenderCode = 10
// переписал в 
const IMPORTANT_RENDER = 'IMPORTANT_RENDER'
// константа используется внутри системы
```

**11**
```javascript
const finishedRenderCode = 0
// переписал 
const FINISHED_REDER_CODE = 'FINISHED_REDER_CODE'
```

**12**
```javascript
// на основе предыдущего улучшения
if (printProcess === 0)

// переделал в 

const isPrintProcessFinished = printProcess === FINISHED_REDER_CODE

if (isPrintProcessFinished) {}
```