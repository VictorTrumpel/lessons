# Отчет о проделанной работе по Божественной линии кода

### Пример 1

---

Было: в одной строчке идет фильтрация и изменение переменной _allCompanies_

---

```typescript
const companies = isUserAuthorized
  ? (allCompanies = allCompanies.filter((company) => company.isPublic))
  : [];
```

---

Стало: вначале отфильтровал получил нужную переменную. Уже потом изменил _allComapnies_
Дополнительно переменовал переменную _companies_ в более говорящую _availableCompanies_

---

```typescript
const availableCompanies = isUserAuthorized
  ? allCompanies.filter((company) => company.isPublic)
  : [];

allCompanies = availableCompanies;
```

### Пример 2

---

В одной строчке кода происходит и сортивока и добавление в Set

---

```typescript
const revitElementsSet = new Set();

revitElements.sort((a, b) => {
  revitElementsSet.add(a);
  revitElementsSet.add(b);

  return a.cost - b.cost;
});
```

---

Разбил эти операции. С одной стороны мы получили +1 проход по массиву. Но в данном случае читаемость куда важнее. + не совсем корректно то, что мы добавляем элементы именно в функции _sort_ т.к. там не линейный проход. Структура _Set_ это конечно нивелирует, но это совсем не логично.

---

```typescript
const revitElementsSet = new Set();

revitElements.forEach((element) => revitElementsSet.add(element));

const revitElementsSortByCost = revitElements.sort((a, b) => a.cost - b.cost);
```

### Пример 3

---

В одну строчку написано получение данных + преобразование их к _json()_.
Не удобно читать + теряется возможность посмотреть код ответа.
Если код ответа 500 то к преобразование к _json_ не требуется.

---

```typescript
const data = await(await fetch("http://")).json();
```

---

Разбил операцию на 2 действия. Есть возможность посмотреть код ответа через переменную _response_

---

```typescript
const response = await fetch("http://");

let data;

if (response.ok) {
  data = response.json();
}
```

### Пример 4

---

В функции map идет возвращение классных и при этом собирается массив вообще всех символов

---

```typescript
const allChars: string[] = [];

const vowelsChars = userName
  .split("")
  .map((char) => {
    allChars.push(char);
    return isVowel(char) ? char : "";
  })
  .filter(Boolean);
```

---

Отдельно получаю массив символов.
Отдельно получаю только глассные + делаю это сразу через _filter_

---

```typescript
const allChars: string[] = userName.split("");

const vowelsChars = allChars.filter((char) => isVowel(char));
```

### Пример 5

---

Добавился новый пользователь. Нужно вывести новое количество пользователей в консоли. _push_ - возвращает новую длинну массива. Это конечно смотрится не очень читабельно. Изменяем массив прямо в момент передачи аргумента

---

```typescript
printUserCount(userList.push(newUser));
```

---

Стало:

---

```typescript
userList.push(newUser);

printUserCount(userList.length);
```
