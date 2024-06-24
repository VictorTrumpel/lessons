package hash_table;

// Предыдущие задания по очередям выполнил правильно

public class HashTableOOAP {
  public int size;
  public int step = 2;
  public String[] slots;

  private Integer REMOVE_OK = 1;
  private Integer REMOVE_ERR = 2;

  private Integer PUT_OK = 1;
  private Integer PUT_ERR = 2;

  private Integer removeStatus = REMOVE_OK;
  private Integer putStatus = PUT_OK;

  public HashTableOOAP(int sz) {
    size = sz;
    slots = new String[size];
    for (int i = 0; i < size; i++)
      slots[i] = null;
  }

  public void put(String value) {
    int hashIdx = hashFun(value);

    if (slots[hashIdx] == null) {
      slots[hashIdx] = value;
      
      return;
    }

    if (value == slots[hashIdx]) {
      putStatus = PUT_OK;
      return;
    }

    putStatus = PUT_ERR;

    return;
  }

  public void remove(String value) {
    int hashIdx = hashFun(value);

    if (value == slots[hashIdx]) {
      slots[hashIdx] = null;
      removeStatus = REMOVE_OK;
      return;
    };

    int findIdx = hashIdx;
    Boolean isCircle = false;

    while (findIdx < slots.length) {
      findIdx += step;

      if (findIdx >= slots.length) {
        isCircle = true;
        int newIdx = findIdx - slots.length - 1;
        findIdx = newIdx < 0 ? 0 : newIdx;
      }

      if (slots[findIdx] == value) {
        slots[findIdx] = null;
        removeStatus = REMOVE_OK;
        return;
      }

      if (findIdx > hashIdx && isCircle)
        break;
    }

    removeStatus = REMOVE_ERR;
  }

  public Boolean hasValue(String value) {
    int hashIdx = hashFun(value);

    if (value == slots[hashIdx]) return true;
  
    int findIdx = hashIdx;
    Boolean isCircle = false;

    while (findIdx < slots.length) {
      findIdx += step;

      if (findIdx >= slots.length) {
        isCircle = true;
        int newIdx = findIdx - slots.length - 1;
        findIdx = newIdx < 0 ? 0 : newIdx;
      }
  
      if (slots[findIdx] == value) {
        return true;
      }

      if (findIdx > hashIdx && isCircle)
        break;
    }

    return false;
  }

  private int hashFun(String value) {
    if (size == 0)
      return 0;

    int hash = value.hashCode();
    int idx = hash % size;
    return idx < 0 ? idx * -1 : idx;
  }

  public Integer getPutStatus() {
    return putStatus;
  }

  public Integer getRemoveStatus() {
    return removeStatus;
  }
}
