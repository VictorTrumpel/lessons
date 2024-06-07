enum POP_STATUS {
  POP_NIL = 0,
  POP_OK = 1,
  POP_ERR = 2,
}

enum PEEK_STATUS {
  PEEK_NIL = 0,
  PEEK_OK = 1,
  PEEK_ERR = 2,
}

enum PUSH_STATUS {
  PUSH_NIL = 0,
  PUSH_OK = 1,
  PUSH_ERR = 2,
}

class Stack<T> {
  // скрытые поля
  private stack: T[] = []; // основное хранилище стека
  private peek_status: PEEK_STATUS = PEEK_STATUS.PEEK_NIL; // статус запроса peek()
  private pop_status: POP_STATUS = POP_STATUS.POP_NIL; // статус команды pop()
  private push_status: PUSH_STATUS = PUSH_STATUS.PUSH_NIL; // статус команды push()

  private max_length: number = 32;

  constructor(max_length: number) {
    this.clear();
    this.max_length = max_length;
  }

  public push(value: T) {
    if (this.size() < this.max_length) {
      this.stack.push(value);
      this.push_status = PUSH_STATUS.PUSH_OK;
    } else {
      this.push_status = PUSH_STATUS.PUSH_ERR;
    }
  }

  public pop() {
    if (this.size() > 0) {
      this.stack.pop();
      this.pop_status = POP_STATUS.POP_OK;
    } else {
      this.pop_status = POP_STATUS.POP_ERR;
    }
  }

  public clear() {
    this.stack = []; // пустой список/стек

    // начальные статусы для предусловий peek() и pop()
    this.peek_status = PEEK_STATUS.PEEK_NIL;
    this.pop_status = POP_STATUS.POP_NIL;
    this.push_status = PUSH_STATUS.PUSH_NIL;
  }

  public peek(): T {
    let result;

    if (this.size() > 0) {
      result = this.stack[-1];
      this.peek_status = PEEK_STATUS.PEEK_OK;
    } else {
      result = 0;
      this.peek_status = PEEK_STATUS.PEEK_ERR;
    }

    return result;
  }

  public size(): number {
    return this.stack.length;
  }

  // запросы статусов
  public get_pop_status() {
    return this.pop_status;
  }

  public get_peek_status() {
    return this.peek_status;
  }

  public get_push_status() {
    return this.push_status;
  }
}
