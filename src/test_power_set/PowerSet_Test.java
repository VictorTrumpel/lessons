package test_power_set;

import static org.junit.Assert.*;

import org.junit.Test;

import power_set.*;

public class PowerSet_Test {

  @Test
  public void test_1() {
    PowerSet set = new PowerSet();

    assertEquals(set.size(), 0);

    assertEquals(set.get("Victor"), false);
    assertEquals(set.get("Leonid"), false);
    assertEquals(set.get("Putin"), false);

    set.put("Victor");
    set.put("Leonid");
    set.put("Putin");

    assertEquals(set.size(), 3);

    assertEquals(set.get("Victor"), true);
    assertEquals(set.get("Leonid"), true);
    assertEquals(set.get("Putin"), true);

    set.remove("Leonid");

    assertEquals(set.size(), 2);

    assertEquals(set.get("Victor"), true);
    assertEquals(set.get("Leonid"), false);
    assertEquals(set.get("Putin"), true);

    set.remove("Victor");

    assertEquals(set.size(), 1);

    assertEquals(set.get("Victor"), false);
    assertEquals(set.get("Leonid"), false);
    assertEquals(set.get("Putin"), true);

    set.remove("Putin");

    assertEquals(set.get("Victor"), false);
    assertEquals(set.get("Leonid"), false);
    assertEquals(set.get("Putin"), false);

    assertEquals(set.size(), 0);

    set.remove("Putin");

    assertEquals(set.size(), 0);

    assertEquals(set.get("Victor"), false);
    assertEquals(set.get("Leonid"), false);
    assertEquals(set.get("Putin"), false);

    set.put(null);

    assertEquals(set.size(), 0);

    assertEquals(set.get("Victor"), false);
    assertEquals(set.get("Leonid"), false);
    assertEquals(set.get("Putin"), false);

    set.put("Victor");
    set.put("Leonid");
    set.put("Putin");

    assertEquals(set.size(), 3);

    assertEquals(set.get("Victor"), true);
    assertEquals(set.get("Leonid"), true);
    assertEquals(set.get("Putin"), true);

    set.remove(null);

    assertEquals(set.size(), 3);

    assertEquals(set.get("Victor"), true);
    assertEquals(set.get("Leonid"), true);
    assertEquals(set.get("Putin"), true);

    set.put("Obama");
    set.put("Shekspir");
    set.put("Zelensky");
    set.put("Kail");

    assertEquals(set.size(), 7);

    assertEquals(set.get("Victor"), true);
    assertEquals(set.get("Leonid"), true);
    assertEquals(set.get("Putin"), true);
    assertEquals(set.get("Obama"), true);
    assertEquals(set.get("Shekspir"), true);
    assertEquals(set.get("Zelensky"), true);
    assertEquals(set.get("Kail"), true);

    PowerSet vlp_h = new PowerSet();

    vlp_h.put("Victor");
    vlp_h.put("Leonid");
    vlp_h.put("Putin");
    vlp_h.put("Hodor");

    PowerSet vlp_only = set.intersection(vlp_h);

    assertEquals(vlp_only.size(), 3);

    assertEquals(vlp_only.get("Victor"), true);
    assertEquals(vlp_only.get("Leonid"), true);
    assertEquals(vlp_only.get("Putin"), true);

    assertEquals(set.size(), 7);

    vlp_h.remove("Hodor");

    assertEquals(vlp_h.get("Hodor"), false);

    assertEquals(set.isSubset(vlp_h), true);

    vlp_h.put("Hodor");

    assertEquals(set.isSubset(vlp_h), false);

    PowerSet empty_intersect = set.intersection(new PowerSet());

    assertEquals(empty_intersect.size(), 0);

    assertEquals(empty_intersect.get("Victor"), false);
    assertEquals(empty_intersect.get("Leonid"), false);
    assertEquals(empty_intersect.get("Putin"), false);

    PowerSet union_set = set.union(vlp_h);

    assertEquals(union_set.size(), 8);

    assertEquals(union_set.get("Victor"), true);
    assertEquals(union_set.get("Leonid"), true);
    assertEquals(union_set.get("Putin"), true);
    assertEquals(union_set.get("Obama"), true);
    assertEquals(union_set.get("Shekspir"), true);
    assertEquals(union_set.get("Zelensky"), true);
    assertEquals(union_set.get("Kail"), true);
    assertEquals(union_set.get("Hodor"), true);

    PowerSet union_with_empty = set.union(new PowerSet());

    assertEquals(union_with_empty.size(), 7);

    assertEquals(union_set.get("Victor"), true);
    assertEquals(union_set.get("Leonid"), true);
    assertEquals(union_set.get("Putin"), true);
    assertEquals(union_set.get("Obama"), true);
    assertEquals(union_set.get("Shekspir"), true);
    assertEquals(union_set.get("Zelensky"), true);
    assertEquals(union_set.get("Kail"), true);

    PowerSet empty_union = new PowerSet().union(new PowerSet());

    assertEquals(empty_union.size(), 0);

    boolean is_empty_subset = new PowerSet().isSubset(new PowerSet());

    assertEquals(is_empty_subset, true);
  }

  @Test
  public void test_2() {
    PowerSet set1 = new PowerSet();
    set1.put("Victor");
    set1.put("Putin");
    set1.put("Ilya");
    set1.put("Emil");

    PowerSet set2 = new PowerSet();
    set2.put("Victor");
    set2.put("Putin");
    set2.put("Ilya");
    set2.put("Emil");

    PowerSet set3 = set1.difference(set2);

    assertEquals(set3.size(), 0);

    set2.put("Hanza");

    PowerSet set4 = set1.difference(set2);

    assertEquals(set4.size(), 1);

    assertEquals(set4.get("Hanza"), true);
  }

  @Test
  public void test_3() {
    PowerSet set1 = new PowerSet();

    int ELEM_COUNT = 20000;

    for (int i = 0; i < ELEM_COUNT; i++) {
      set1.put(String.valueOf(i));
    }

    for (int i = 0; i < ELEM_COUNT; i++) {
      assertEquals(set1.get(String.valueOf(i)), true);
      set1.remove(String.valueOf(i));
    }

    assertEquals(set1.size(), 0);
  }

  @Test
  public void test_4() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = new PowerSet();

    set1.put("Victor");
    set1.put("Kart");
    set1.put("King");

    set2.put("Victor");
    set2.put("Kart");
    set2.put("King");

    PowerSet set3 = set1.union(set2);

    assertEquals(set3.size(), 3);

    assertEquals(set3.get("Victor"), true);
    assertEquals(set3.get("Kart"), true);
    assertEquals(set3.get("King"), true);

    int victor_instance = 0;
    int kart_instance = 0;
    int king_instance = 0;

    for (int i = 0; i < set3.slots.length; i++) {
      if (set3.slots[i] != null && set3.slots[i].equals("Victor")) {
        victor_instance += 1;
      }
      if (set3.slots[i] != null && set3.slots[i].equals("Kart")) {
        kart_instance += 1;
      }
      if (set3.slots[i] != null && set3.slots[i].equals("King")) {
        king_instance += 1;
      }
    }

    assertEquals(victor_instance, 1);
    assertEquals(kart_instance, 1);
    assertEquals(king_instance, 1);
  }

  @Test
  public void test_5() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = new PowerSet();

    set1.put("Victor");
    set1.put("Kart");
    set1.put("King");

    set2.put("Master");

    PowerSet set3 = set1.union(set2);

    assertEquals(set3.size(), 4);

    assertEquals(set3.get("Victor"), true);
    assertEquals(set3.get("Kart"), true);
    assertEquals(set3.get("King"), true);
    assertEquals(set3.get("Master"), true);
  }

  @Test
  public void test_6() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = new PowerSet();

    for (int i = 0; i < 10000; i++) {
      set1.put(String.valueOf(i));
    }

    for (int i = 10000; i < 20000; i++) {
      set2.put(String.valueOf(i));
    }

    PowerSet set3 = set1.union(set2);

    assertEquals(set3.size(), 20000);

    for (int i = 0; i < 20000; i++) {
      assertEquals(set3.get(String.valueOf(i)), true);
    }

    for (int i = 0; i < 20000; i++) {
      set3.put(String.valueOf(i));
    }

    assertEquals(set3.size(), 20000);

    for (int i = 0; i < 20000; i++) {
      assertEquals(set3.get(String.valueOf(i)), true);
    }

    for (int i = 0; i < 20000; i++) {
      assertEquals(set3.remove(String.valueOf(i)), true);
    }

    assertEquals(set3.size(), 0);
  }

  @Test
  public void test_7() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = new PowerSet();

    set2.put("Nose");
    set2.put("Kart");
    set2.put("King");

    PowerSet set3 = set1.union(set2);

    assertEquals(set3.size(), 3);

    assertEquals(set3.get("Nose"), true);
    assertEquals(set3.get("Kart"), true);
    assertEquals(set3.get("King"), true);

    set1.put("Galla");
    set1.put("Master");
    set1.put("Nose");
    set1.put("Kart");
    set1.put("King");

    PowerSet set4 = set3.union(set1);

    assertEquals(set4.size(), 5);
    assertEquals(set4.get("Nose"), true);
    assertEquals(set4.get("Kart"), true);
    assertEquals(set4.get("King"), true);
    assertEquals(set4.get("Galla"), true);
    assertEquals(set4.get("Master"), true);
  }

  @Test
  public void test_8() {
    PowerSet set1 = new PowerSet();

    set1.put("Nose");
    set1.put("Kart");
    set1.put("King");

    PowerSet set2 = set1.union(set1);

    assertEquals(set2.get("Nose"), true);
    assertEquals(set2.get("Kart"), true);
    assertEquals(set2.get("King"), true);
  }

  @Test
  public void test_9() {
    PowerSet set1 = new PowerSet();

    set1.put("Nose");
    set1.put("Kart");
    set1.put("King");

    PowerSet set2 = set1.union(set1);

    assertEquals(set2.get("Nose"), true);
    assertEquals(set2.get("Kart"), true);
    assertEquals(set2.get("King"), true);
  }

  @Test
  public void test_10() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = set1.union(set1);

    for (int i = 0; i < 20000; i++) {
      if (i % 2 == 0)
        set1.put(String.valueOf(i));
      else
        set2.put(String.valueOf(i));
    }

    assertEquals(set1.size(), 10000);
    assertEquals(set2.size(), 10000);

    PowerSet set3 = set1.union(set2);

    for (int i = 0; i < 20000; i++) {
      assertEquals(set3.get(String.valueOf(i)), true);
    }
  }

  @Test
  public void test_11() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = set1.union(set1);

    for (int i = 1; i < 101; i++) {
      set1.put(String.valueOf(i));
    }

    assertEquals(set1.size(), 100);

    for (int i = 50; i < 151; i++) {
      set2.put(String.valueOf(i));
    }

    assertEquals(set2.size(), 101);

    PowerSet set3 = set1.union(set2);

    assertEquals(set3.size(), 150);

    for (int i = 1; i < 151; i++) {
      assertEquals(set3.get(String.valueOf(i)), true);
    }
  }

  @Test
  public void test_12() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = set1.union(set1);

    for (int i = 0; i < 20000; i++) {
      set1.put(String.valueOf(i));
    }

    for (int i = 0; i < 10; i++) {
      set2.put(String.valueOf(i));
    }

    PowerSet set3 = set1.intersection(set2);

    assertEquals(set3.size(), 10);

    for (int i = 0; i < 10; i++) {
      assertEquals(set3.get(String.valueOf(i)), true);
    }
  }

  @Test
  public void test_13() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = set1.union(set1);

    for (int i = 1; i < 101; i++) {
      set1.put(String.valueOf(i));
    }

    for (int i = 50; i < 151; i++) {
      set2.put(String.valueOf(i));
    }

    PowerSet set3 = set1.intersection(set2);

    assertEquals(set3.size(), 51);

    for (int i = 50; i < 101; i++) {
      assertEquals(set3.get(String.valueOf(i)), true);
    }
  }

  @Test
  public void test_14() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = set1.union(set1);

    for (int i = 1; i < 101; i++) {
      set1.put(String.valueOf(i));
    }

    for (int i = 50; i < 151; i++) {
      set2.put(String.valueOf(i));
    }

    assertEquals(set1.isSubset(set2), false);
    assertEquals(set2.isSubset(set1), false);
  }

  @Test
  public void test_15() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = set1.union(set1);

    for (int i = 1; i < 101; i++) {
      set1.put(String.valueOf(i));
    }

    for (int i = 1; i < 151; i++) {
      set2.put(String.valueOf(i));
    }

    assertEquals(set1.isSubset(set2), false);
    assertEquals(set2.isSubset(set1), true);
  }

  @Test
  public void test_16() {
    PowerSet set1 = new PowerSet();
    PowerSet set2 = set1.union(set1);

    for (int i = 1; i < 101; i++) {
      set1.put(String.valueOf(i));
    }

    for (int i = 50; i < 151; i++) {
      set2.put(String.valueOf(i));
    }

    PowerSet set3 = set1.difference(set2);

    assertEquals(set3.size(), 49);

    for (int i = 1; i < 50; i++) {
      assertEquals(set3.get(String.valueOf(i)), true);
    }

    PowerSet set4 = set2.difference(set1);

    assertEquals(set4.size(), 50);

    for (int i = 101; i < 151; i++) {
      assertEquals(set4.get(String.valueOf(i)), true);
    }
  }

}
