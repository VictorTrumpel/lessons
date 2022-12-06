public class DynArray<T> {
  public T[] array;
  public int count;
  public int capacity;
  Class clazz;

  public DynArray(Class clz) {
    clazz = clz;

    count = 0;
    makeArray(16);
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

  public void checkIndexRange(int index) throws IOException {
    if (index > this.count || index < 0)
      throw new IOException("out of range");
  }

  public T getItem(int index) throws IOException {
    this.checkIndexRange(index);

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
    if (index > this.count || index < 0)
      throw new IOException("out of range");

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
    if (index > this.count - 1 || index < 0)
      throw new IOException("out of range");

    this.array[index] = null;

    for (int i = index; i < this.count; i++) {
      this.array[i] = this.array[i + 1];
    }

    if (this.count > 0)
      this.count -= 1;

    float fillPercent = (float) this.count / (float) this.capacity;

    if (fillPercent >= 0.5)
      return;

    int new_capacity = (int) (this.capacity / 1.5);

    if (this.count < this.capacity && new_capacity > 16) {
      this.capacity = new_capacity;
    }
  }
}
