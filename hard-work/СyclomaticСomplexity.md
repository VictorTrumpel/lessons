# Пример 1.

## Использовал полиморфизм подтипов, параметрический полиморфизм.

### Было:

```typescript
class User {
  userId: number;
  name: string;
}

class Company {
  companyId: number;
  title: string;
}

class UserApi {
  getUsers(): Promise<User[]>;
}

class CompanyApi {
  getCompanies(): Promise<Company[]>;
}

class OptionList {
  constructor(private api: UserApi | CompanyApi) {}

  async getOptionList(): Promise<User[] | Company[]> {
    if (this.api instanceof UserApi) {
      return await this.api.getUsers();
    }

    if (this.api instanceof CompanyApi) {
      return await this.api.getCompanies();
    }
  }

  async render() {
    const optionList = await this.getOptionList();

    if (this.api instanceof UserApi) {
      const listItemsTemplate = optionList
        .map(
          (user) => `
        <li data-id=${user.userId}>${user.name}</li>
      `
        )
        .join("");

      return `<ul>${listItemsTemplate}</ul>`;
    }

    if (this.api instanceof UserApi) {
      const listItemsTemplate = optionList
        .map(
          (company) => `
        <li data-id=${company.companyId}>${company.title}</li>
      `
        )
        .join("");

      return `<ul>${listItemsTemplate}</ul>`;
    }
  }
}
```

### Стало:

```typescript
class User {
  userId: number;
  name: string;
}

class Company {
  companyId: number;
  title: string;
}

class Option {
  id: number;
  label: string;
}

class UserApi {
  getItems(): Promise<User[]>;
}

class CompanyApi {
  getItems(): Promise<Company[]>;
}

class OptionList {
  constructor(private api: UserApi | CompanyApi) {}

  private createOption(id: number, label: string) {
    return { id, label };
  }

  private convertToOption(data: User | Company): Option {
    const isUser = data instanceof User;

    return isUser
      ? this.createOption(data.userId, data.name)
      : this.createOption(data.companyId, data.title);
  }

  async getOptionList(): Promise<Option[]> {
    const items = await this.api.getItems();
    return items.map(this.convertToOption);
  }

  async render() {
    const optionList = await this.getOptionList();

    const listItemsTemplate = optionList
      .map(
        (option) => `
        <li data-id=${option.id}>${option.label}</li>
      `
      )
      .join("");

    return `<ul>${listItemsTemplate}</ul>`;
  }
}
```

---

Сделал одинаковые названия методов, для получения сущностей.  
Привел сущности User и Company к одному виду.

---

# Пример 2.

## Автомат состояний.

### Было:

```typescript
class ClipperTool {
  mode: "ADD" | "REMOVE" | null;

  addClipper() {
    if (this.mode === "ADD") {
      // логика по добавления подрезки на 3D модели
    }
  }

  deleteClipper() {
    if (this.mode === "REMOVE") {
      // логика по удалению подрезки на 3D модели
    }
  }
}
```

### Стало:

```typescript
interface ClipperApi {
  addClipper(): void;
  deleteClipper(): void;
}

class AddClipperTool implements ClipperApi {
  addClipper() {
    // логика по добавления подрезки на 3D модели
  }

  deleteClipper() {}
}

class DeleteClipperTool implements ClipperApi {
  addClipper() {}

  deleteClipper() {
    // логика по удалению подрезки на 3D модели
  }
}
```

---

Создаю интрефейс, которые требует реализовать 2 метода: addClipper, deleteClipper.
Далее создаю 2 класса для 2-х состояний инструмента подрезки.  
Таким образом я могу не проверять в каком состоянии находится инструмент, а сразу писать логику добавления/удаления подрезки.

---

# Пример 3.

## Табличная логика.

### Было:

```typescript
type Values = {
  [p: string]: unknown;
}

const values: Values = {
  email: 'gds@gmail.com',
  password: '123',
  fullName: 'Ivan ivan ivan'
}

const validate(values: Values) {
  for (const key in values) {
    const value = values[key]

    let isValid: boolean = false;

    if (key === 'email') {
      isValid = isValidEmail(value)
    }

    if (key === 'password') {
      isValid = isValidPassword(value)
    }

    if (key === 'fullName') {
      isValid = isValidFullName(value)
    }

    if (!isValid) break;

    // ...
  }
}

function isValidEmail(value: unknown): boolean {}
function isValidPassword(value: unknown): boolean {}
function isValidFullName(value: unknown): boolean {}
```

### Стало:

```typescript
type Values = {
  [p: string]: unknown;
}

const values: Values = {
  email: 'gds@gmail.com',
  password: '123',
  fullName: 'Ivan ivan ivan'
}

const validSchema = {
  email: isValidEmail,
  password: isValidPassword,
  fullName: isValidFullName
}

const validate(values: Values) {
  for (const key in values) {
    const value = values[key]

    const validFunc = validSchema[key]

    if (!validFunc) break;

    const isValid = validFunc(value);

    if (!isValid) break;

    // ...
  }
}

function isValidEmail(value: unknown): boolean {}
function isValidPassword(value: unknown): boolean {}
function isValidFullName(value: unknown): boolean {}

```

---

Рефлексия:  

Полиморфизм подтипов я уже использовал в своей практике, но не знал что это он.  
Табличную логику я тоже использовал, но очень редко и вообще сомневался в том, что это "адекватно".  
Теперь эти два способа формализовались у меня в голове и я уже буду использовать их осознанно, а от того и чаще.  
Поймал инсайд насчет Typescript: этот язык со своей "утиной типизацией" очень хорошо подходит для параметрического полиморфизма.  
Например в java, параметрический полиморфизм получится реализовать, только если сущности будут обернуты во что-то (например в ArrayList), а в TS достаточно, что бы параметры были одинаково названы и одного типа.  
Но с другой стороны, в TS слабое место в том, что мы не можем реализовать полиморфизм как в Julia (несколько одинаковых методов с разными параметрами). Теоретически можем, конечно, но нам придется создать там общий метод, где через if-ы обработать каждый тип отдельно, поэтому это не считается.  
А вот в Java мы можем реализовать полиморфизм как в Julia, без всяких там if-ов. Java-машина сама будет определять какого типа сущность и закидывать в нужный метод.  

Хорошим инсайдом для меня был полиморфизм - автомат состояний. Я об этом не думал, а это очень круто и в моем текущем проекте как раз очень нужно.  
У нас много "тулзов", которые имею несколько состояний.  
Остается только вопрос - как именовать классы для каждого состояния? Стоит ли в имени класса отображать то, что он реализует такое-то состояние. Я думаю - да, стоит.  
Например у нас есть класс: *ClipperTool* у которого есть статусы - *ACTIVE*, *ADD*, *DELETE*, *NULL*  
Тогда его автомат состояний будет представлять классы с именем: *ActiveClipperTool*, *DeleteClipperTool*, *AddClipperTool*.  
Тобишь в имени класса отразили какое состояние он представляет.

---
