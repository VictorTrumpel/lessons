/*
  Работа над ошибками:
  ошибок не совершил. Построил правильную иерархию. Добавил все необходимые статусы.
  Реализовал все необходимые методы.
 */


public class DynArray<T> {
  public T[] array;
  public int count;
  public int capacity;
  Class clazz;

  private Integer GET_ITEM_OK = 1;
  private Integer GET_ITEM_ERR = 2;

  private Integer INSERT_OK = 1;
  private Integer INSERT_ERR = 2; 

  private Integer REMOVE_OK = 1;
  private Integer REMOVE_ERR = 2;

  private Integer getItemStatus = GET_ITEM_OK;
  private Integer insertStatus = INSERT_OK;
  private Integer removeStatus = REMOVE_OK;

  public DynArray(Class clz) {
    clazz = clz;

    count = 0;
    makeArray(16);
  }

  public Integer getOfGetItemStatus() {
    return this.getItemStatus;
  }

  public Integer getInsertStatus() {
    return insertStatus;
  }

  public Integer getRemoveStatus() {
    return removeStatus;
  }

  public void makeArray(int new_capacity) {
    this.capacity = new_capacity;

    T[] newArray = (T[]) Array.newInstance(this.clazz, new_capacity);

    if (this.array == null) {
      this.array = newArray;
      return;
    }

    for (int i = 0; i < count; i++) {
      newArray[i] = this.array[i];
    }

    this.array = newArray;
  }

  public T getItem(int index) throws IOException {
    if (index > this.count - 1 || index < 0) {
      getItemStatus = GET_ITEM_ERR;
      throw new IOException("out of range");
    }

    getItemStatus = GET_ITEM_OK;

    return this.array[index];
  }

  public void append(T itm) {
    if (this.count == this.capacity) {
      this.makeArray(this.capacity * 2);
    }

    this.array[this.count] = itm;
    this.count += 1;
  }

  public void insert(T itm, int index) throws IOException {
    if (index > this.count || index < 0) {
      insertStatus = INSERT_ERR;
      return;
    }

    insertStatus = INSERT_OK;

    if (this.count == this.capacity) {
      this.makeArray(this.capacity * 2);
    }

    T temp;
    T currItem = itm;
    for (int i = index; i < this.count + 1; i++) {
      temp = this.array[i];
      this.array[i] = currItem;
      currItem = temp;
    }

    this.count += 1;
  }

  public void remove(int index) throws IOException {
    if (index > this.count - 1 || index < 0) {
      removeStatus = REMOVE_ERR;
      return;
    }

    removeStatus = REMOVE_OK;

    this.array[index] = null;

    for (int i = index; i < this.count; i++) {
      if (i == this.count - 1) {
        this.array[i] = null;
      } else {
        this.array[i] = this.array[i + 1];
      }
    }

    if (this.count > 0)
      this.count -= 1;

    float fillPercent = (float) this.count / (float) this.capacity;

    int new_capacity = (int) (this.capacity / 1.5);

    if (fillPercent >= 0.5 || this.count == this.capacity)
      return;

    if (new_capacity > 16)
      this.capacity = new_capacity;
    else
      this.capacity = 16;
  }
}
