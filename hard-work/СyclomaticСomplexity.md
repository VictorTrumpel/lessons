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

  private convertToOption(data: User | Company): Option {
    if (data instanceof User) {
      return {
        id: data.userId,
        label: name,
      };
    }
    return {
      id: data.companyId,
      label: title,
    };
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

***
какие-то объясгения
пвыпвып
вы
пвы
п
ыв
пв
ыпывпывпыв
***