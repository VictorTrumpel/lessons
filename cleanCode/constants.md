**1**
```javascript

const rejectStatus = 'reject'
const approveStatus = 'approve'

// для того, что бы не хардкодить статусы - они были вынесены в константы, но 
// стиль именования был не правильный

const REJECT_STATUS = 'reject'
const APPROVE_STATUS = 'approve'

```

**2**
```javascript

const routes = ['/home', '/company', '/document'] // неправильно
const ROUTES = ['/home', '/company', '/document'] // правильно

```

**3**
```javascript

// неправильно
document = {} // приходит с бэка

const signesCount = document.signesCount

if (signesCount < 2) {
  // что-то делать с документом
}

// правильно
const MAX_SIGNES_ON_DOC = 2

document = {} // приходит с бэка

const signesCount = document.signesCount

if (signesCount < MAX_SIGNES_ON_DOC) {
  // что-то делать с документом
}

```

**4**
```javascript

let maxUsersCount = 100 // неправильно
const MAX_USERS_COUNT = 100 // правильно

```

**5**
```javascript

const maxActive = 2 // имя константы бессмысленно и не по стилю
const MAX_ACTIVE_POPUP_COUNT = 2 // максимальное количество активных окон

```

**6**
```javascript

// неправильно
if (user.id === 0) {
  // делаем что-то для админа
}

// правильно
const ADMIN_ID = 0

if (user.id === ADMIN_ID) {
  // делаем что-то для админа
} 

```

**7**
```javascript

let duration = 200 // плохое имя константы, не говорящее, не по стилю
const ANIMATION_DURATION_MS = 200

```

**8**
```javascript

// неправильно
if (errorCode === 401) {
  // сделать что-либо
}

// правильно
const AUTH_ERROR_CODE = 401

if (errorCode === AUTH_ERROR_CODE) {
  // сделать что-либо
}

```

**9**
```javascript

// неправильно
const userLoadSucces = 'userLoadSucces' // тип события

const setLoadUserSuccess = () => ({
  type: userLoadSucces,
  payload: true
})

// правильно
const USER_LOAD_SUCCESS = 'USER_LOAD_SUCCESS' // тип события

const setLoadUserSuccess = () => ({
  type: USER_LOAD_SUCCESS,
  payload: true
})

```

**10**
```javascript

// неправильно
const usersNames = ['name1', 'name2', 'name3']

const userNameIdx = usersNames.indexOf('name4')

if (userNameIdx === -1) {
  // делать логику в случае, если не нашли имя
}

// правильно
const NOT_FOUND_ELEM_CODE = -1

const usersNames = ['name1', 'name2', 'name3']

const userNameIdx = usersNames.indexOf('name4')

if (userNameIdx === NOT_FOUND_ELEM_CODE) {
  // делать логику в случае, если не нашли имя
}
```

**11**
```javascript

let failMessageText = 'Server error' // плохое имя переменной, не правильно задана константа

const SERVER_ERROR_MESSAGE = 'Server error' // правильно

```

**12**
```javascript

// неправильно
if (document.text === 'Empty content') {
  // выполнять логику, если у документа такой текст
}

// правильно
const DOCUMENT_EMPTY_TEXT = 'Empty content'

if (document.text === DOCUMENT_EMPTY_TEXT) {
  // выполнять логику, если у документа такой текст
}

```