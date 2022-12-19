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

}
