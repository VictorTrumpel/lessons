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
