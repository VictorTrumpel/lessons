import hash_table.HashTableOOAP;

/*
  Отчет по предыдущему заданию:
  Не учел довольно таки много методов. Проработал задание, дописал недостающие методы.
  Ссылка на выполнение - https://github.com/VictorTrumpel/lessons/blob/main/OOAP/NativeDictionaryOOAP.java
 */

public class PowerSetOOAP extends HashTableOOAP {
  private int size;
  private int length = 0;


  private Integer REMOVE_OK = 1;
  private Integer REMOVE_ERR = 2;

  private Integer PUT_OK = 1;
  private Integer PUT_ERR = 2;

  private Integer removeStatus = REMOVE_OK;
  private Integer putStatus = PUT_OK;

  public PowerSetOOAP(int size) {
    super(size);
    this.size = size;
  }

  @Override
  public void put(String value) {
    if (length == size) {
      putStatus = PUT_ERR;
      return;
    }

    putStatus = PUT_OK;

    length += 1;

    super.put(value);
  }

  @Override
  public void remove(String value) {
    if (length == 0) {
      removeStatus = REMOVE_ERR;
      return;
    }

    removeStatus = REMOVE_OK;

    length -= 1;

    super.remove(value);
  }

  public boolean get(String value) {
    int hashIdx = find(value);
    return hashIdx != -1;
  }

  public PowerSet intersection(PowerSet set2) {
    PowerSet intersectPowerSet = new PowerSet();

    String[] set2Values = set2.slots;

    for (int i = 0; i < set2Values.length; i++) {
      String value = set2Values[i];
      if (value != null && get(value)) {
        intersectPowerSet.put(value);
      }
    }

    return intersectPowerSet;
  }

  public PowerSet union(PowerSet set2) {
    PowerSet unionPowerSet = new PowerSet();

    String[] set2Values = set2.slots;

    for (int i = 0; i < slots.length; i++) {
      unionPowerSet.put(slots[i]);
    }

    for (int i = 0; i < set2Values.length; i++) {
      unionPowerSet.put(set2Values[i]);
    }

    return unionPowerSet;
  }

  public PowerSet difference(PowerSet set2) {
    PowerSet diffPowerSet = new PowerSet();

    for (int i = 0; i < slots.length; i++) {
      String value = slots[i];

      if (value != null && !set2.get(value)) {
        diffPowerSet.put(value);
      }
    }

    return diffPowerSet;
  }

  public boolean isSubset(PowerSet set2) {
    String[] set2Values = set2.slots;

    for (int i = 0; i < set2Values.length; i++) {
      String value = set2Values[i];
      if (value != null && !get(value))
        return false;
    }

    return true;
  }

  public Integer getPutStatus() {
    return putStatus;
  }
  
  public Integer getRemoveStatus() {
    return removeStatus;
  }

  private int find(String value) {
    int idx = 0;

    while (idx < slots.length) {
      if (slots[idx] != null && slots[idx].equals(value)) {
        return idx;
      }

      idx += 1;
    }

    return -1;
  }
}
