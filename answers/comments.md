**3.1**

**1**

```typescript
// Вычисляет и группирует по позициям элементы в свободном пространстве
distributeElementsByInterval<T>(elements: T[], startPosition: number, endPosition: number)
```

**2**
```typescript
// при изменении сущности колонки переводим autoNowAdd в false (базовое состояние)
newColData.autoNowAdd = false
```

**3**
```typescript
// соединяем начальные значения и, если нужно, перезаписываем их значениями из локал стораджа
return { ...INIT_VALUES_FROM_TEMPLATE, ...values }
```

**4**
```typescript
// для оптимизированого получения компании по id
const companyBook: CompanyBook = useMemo(() => getCompanyBook(allCompanies), [allCompanies])
```

**5**
```typescript
// Костыль. Т.к. в локация сеттает свои дефолтные значения при рендеренге, то она ложно аффектит свое изменение 
// и изменение таблицы. Поэтому такой костыль
const locationFirstRender = useRef(true)

// *** В данном случае ошибка проектирования одно из компонент. Комментарий указывает о ней.
```

**6**
```typescript
// Влияет на стили пикера вне дерева
const DtFilter: React.FC<PropType> = () => /* ... */
```

**7**
```typescript
// При совпадении названий начинаются проблемы с кеями и стейтом. Привязка к id решает проблему.
if (popupItem?.id) return (
  /* ... */
)
```

**3.2**

**1**
```typescript

// если пользователь не находится на странице авторизации или входа и его профиль не пустой
if (!isEmpty(userInfo) && ![location.pathname].includes('/sign-up') && ![location.pathname].includes('/auth'))

// переделать:

const isUserProfileEmpty = isEmpty(userInfo)
const isOnAuthPage = [location.pathname].includes('/auth')
const isOnSignUpPage = [location.pathname].includes('/sign-up')

if (!isUserProfileEmpty && !isOnAuthPage && !isOnSignUpPage)
```

**2**
```typescript
// Три состояния  1. null - нет иконки, 2. true - иконка успеха 3. false - иконка загрузки  
const [iconStateForInputs, setIconStateForInputs] = useState<any>(ICON_STATE_INITIAL)

// переделать:
const [iconStateForInputs, setIconStateForInputs] = useState<null | true | false>(ICON_STATE_INITIAL)
```

**3**
```typescript
// мердж массива и объекта комментариев
function mergeArrayAndObject(/* ... */) {
  /* ... */
}

// переименовать:
function mergeListAndTreeComents() {

}
```

**4**
```typescript
// триггер компонента локаций т.к. компонент локаций 
window.dispatchEvent(new Event(eventName))

// переделать:

const dispatchLocation = () => window.dispatchEvent(new Event(eventName))
dispatchLocation()
```

**5**
```typescript
// Если это событие текущего пользователя и галочка убрана то не показываем их
if (dataMeet)
  hideMeets()

// переделать в:

if (isUncheckedMeetOfCurrentUser) {
  hideMeets()
}
```