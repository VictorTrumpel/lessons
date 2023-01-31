**1**

```javascript
// очень важно возвращать время в формате HH:mm т.к. бэк не принемает HH:mm:ss
return `${moment(time, 'HH:mm').format('HH:mm')}`

// по п. 1, 5
```

**2**
```javascript
// важно отсортировать порядок подписания документа в по убыванию важности поста, занимаемого сотрудником
const signerActions = actions.sort((user1, user2) => user2.post - user1.post)

// по п. 5
```

**3**
```javascript
// TODO ПЕРЕПИСАТЬ ЭТОТ МУСОР НА НОРМАЛЬНЫЕ КАСТОМНЫЕ ВАЛИДАЦИОННЫЕ СХЕМЫ

// по п. 6
```

**4**
```javascript
// TODO внести состояние из компонента, сделать его исключительно презентационным

// по п. 6
```

**5**
```javascript
// 2 стейта для реализации autosave
const [searchValue, setSearchValue] = useState('') // значение для поиска
const [searchInputValue, setSearchInputValue] = useState('') // стеит инпута

// по п. 2
```

**6**
```javascript
const config = {
  type: AUTO // не убирайте этот параметр, иначе графика не будет рендерится на WebGL
}

// по п. 4
```

**7**
```javascript
// ОЧЕНЬ важно реализовывать методы заглушки для обратной совместимости!

// по п. 5
```

**8**
```javascript
// не нужно передавать объект formData вниз по дереву компонентов - возможна мутация объекта

// по п. 4
```

**9**
```javascript
// 1-й парамерт - информация о курсоре, 2-й объект на котором произошел клик. см. доку Phaser.js
this.inputs.on('gameobjectdown', (cursor, gameobject) => {

})

// по п.3
```

**10**
```javascript
// Перестраивает карту с учетом максимального кол-ва элементов на одной позиции
private static getMapWithOffset<E, M extends IMapElementsPosition<E>>(mapElementsRaw: M[])

// по п. 2
```

**11**
```javascript
// очень важно возвращать null, если значение не было заполнено. Иначе бэк не примет запрос.
if (!value)
  return null

// по п. 5
```

**12**
```javascript
// Не даем доступ к cellsKey
// так как нам слишком важна ссылка

(() => {
  /* ... */
})

// по п.5
```
