public class NativeDictionary<T> {
  public int size;
  public String[] slots;
  public T[] values;

  public NativeDictionary(int sz, Class clazz) {
    size = sz;
    slots = new String[size];
    values = (T[]) Array.newInstance(clazz, this.size);
  }

  public int hashFun(String key) {
    if (size == 0)
      return 0;

    int hash = key.hashCode();
    int idx = hash % size;
    return idx < 0 ? idx * -1 : idx;
  }

  public boolean isKey(String key) {
    int hashIdx = hashFun(key);
    if (slots[hashIdx] == key)
      return true;
    return false;
  }

  public void put(String key, T value) {
    int hashIdx = hashFun(key);
    slots[hashIdx] = key;
    values[hashIdx] = value;
  }

  public T get(String key) {
    int hashIdx = hashFun(key);
    return values[hashIdx];
  }
}
