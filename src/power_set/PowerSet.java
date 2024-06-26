package power_set;
public class PowerSet {
  private int size;
  private int step;

  public String[] slots;

  private int length = 0;

  public PowerSet() {
    size = 20000;
    step = 1;
    slots = new String[size];
  }

  public int hashFun(String value) {
    if (size == 0)
      return 0;

    int hash = value.hashCode();
    int idx = hash % size;
    return idx < 0 ? idx * -1 : idx;
  }

  public int size() {
    return length;
  }

  private int getEmptySlotIdx(String value) {
    int hashIdx = hashFun(value);

    if (slots[hashIdx] == null || slots[hashIdx].equals(value))
      return hashIdx;

    int findIdx = hashIdx;
    Boolean isCircle = false;

    while (findIdx < slots.length) {
      findIdx += step;

      if (findIdx >= slots.length) {
        isCircle = true;
        int newIdx = findIdx - slots.length - 1;
        findIdx = newIdx < 0 ? 0 : newIdx;
      }

      if (slots[findIdx] == null)
        return findIdx;

      if (findIdx > hashIdx && isCircle)
        break;
    }

    return -1;
  }

  public void put(String value) {
    if (value == null)
      return;

    int hashIdx = getEmptySlotIdx(value);

    if (hashIdx == -1)
      return;

    if (slots[hashIdx] == null || !slots[hashIdx].equals(value))
      length += 1;

    slots[hashIdx] = value;
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

  public boolean get(String value) {
    int hashIdx = find(value);
    return hashIdx != -1;
  }

  public boolean remove(String value) {
    if (value == null)
      return false;

    int hashIdx = find(value);

    if (hashIdx == -1)
      return false;

    length -= 1;
    slots[hashIdx] = null;
    return true;
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
}
