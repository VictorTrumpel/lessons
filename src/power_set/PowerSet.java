package power_set;

public class PowerSet {
  private int size;

  public String[] slots;
  public String[] values;

  private int length = 0;

  public PowerSet() {
    size = 20000;
    slots = new String[size];
    values = new String[size];
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

  public void put(String value) {
    if (value == null)
      return;

    int hashIdx = hashFun(value);

    if (values[hashIdx] != value)
      length += 1;

    slots[hashIdx] = value;
    values[hashIdx] = value;
  }

  public boolean get(String value) {
    int hashIdx = hashFun(value);
    return values[hashIdx] == value;
  }

  public boolean remove(String value) {
    if (value == null)
      return false;

    int hashIdx = hashFun(value);

    if (values[hashIdx] != null) {
      length -= 1;
      values[hashIdx] = null;
      slots[hashIdx] = null;
      return true;
    }

    return false;
  }

  public PowerSet intersection(PowerSet set2) {
    PowerSet intersectPowerSet = new PowerSet();

    String[] set2Values = set2.values;

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

    String[] set2Values = set2.values;

    for (int i = 0; i < values.length; i++) {
      unionPowerSet.put(values[i]);
    }

    for (int i = 0; i < set2Values.length; i++) {
      unionPowerSet.put(set2Values[i]);
    }

    return unionPowerSet;
  }

  public PowerSet difference(PowerSet set2) {
    PowerSet unionPowerSet = new PowerSet();

    String[] set2Values = set2.values;

    for (int i = 0; i < set2Values.length; i++) {
      String value = set2Values[i];
      if (value != null && !get(value))
        unionPowerSet.put(set2Values[i]);
    }

    return unionPowerSet;
  }

  public boolean isSubset(PowerSet set2) {
    String[] set2Values = set2.values;

    for (int i = 0; i < set2Values.length; i++) {
      String value = set2Values[i];
      if (value != null && !get(value))
        return false;
    }

    return true;
  }
}
