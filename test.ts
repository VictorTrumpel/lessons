interface IIssue {
  getCreateDTO(): Promise<void>;
  getUpdateDTO(): Promise<void>;
}

class ModelIssue implements IIssue {
  async getCreateDTO() {
    /* Возвращает объект DTO для создания */
  }

  async getUpdateDTO() {
    /* Возвращает объект DTO для обновления */
  }
}

class DocumentIssue implements IIssue {
  async getCreateDTO() {
    /* Обращение к бэку*/
  }

  async getUpdateDTO() {
    /* Возвращает объект DTO для обновления */
  }
}

class ModelIssueIndexDB extends ModelIssue {
  async getCreateDTO() {
    /* Переопределяем логику */
  }

  async getUpdateDTO() {
    /* Переопределяем логику */
  }
}

class DocumentIssueIndexDB extends DocumentIssue {
  async getCreateDTO() {
    /* Переопределяем логику */
  }

  async getUpdateDTO() {
    /* Переопределяем логику */
  }
}
