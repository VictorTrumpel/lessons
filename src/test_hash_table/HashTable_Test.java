package test_hash_table;

import static org.junit.Assert.*;

import org.junit.Test;

import hash_table.*;

public class HashTable_Test {

  @Test
  public void test_1() {
    HashTable table1 = new HashTable(19, 3);

    for (Integer i = 0; i < 1000; i++) {
      String c = i.toString();
      Integer hash = table1.hashFun(c);
      assertEquals(hash < table1.size, true);
      assertEquals(hash >= 0, true);
    }

    HashTable table2 = new HashTable(0, 3);

    for (Integer i = 0; i < 1000; i++) {
      String c = i.toString();
      Integer hash = table2.hashFun(c);
      assertEquals(hash == 0, true);
    }
  }

  @Test
  public void test_2() {
    HashTable table1 = new HashTable(19, 3);

    int hashIdx1 = table1.put("test");

    Boolean hasValue = false;
    for (int i = 0; i < table1.size; i++) {
      if (table1.slots[i] == "test") {
        hasValue = true;
        break;
      }
    }

    int hashIdx2 = table1.put("test");

    assertEquals(hashIdx1, hashIdx2);
    assertEquals(hasValue, true);
  }

  @Test
  public void test_3() {
    HashTable table1 = new HashTable(19, 1);
    TestCases test = new TestCases();
    String[] testCases = test.getTestCase();

    for (Integer i = 0; i < table1.size; i++) {
      String value = testCases[i];
      int findSlot = table1.seekSlot(testCases[i]);
      table1.slots[findSlot] = value;
    }

    for (Integer i = 0; i < table1.size; i++) {
      System.out.println(table1.slots[i]);
    }

    System.out.print("find: ");

    System.out.println(table1.find(testCases[0]));

  }
}

class TestCases {
  public String[] getTestCase() {
    String[] testCases = new String[19];

    testCases[0] = "gdsgdsg2trowepitpowiposdgpow";
    testCases[1] = "gtweq";
    testCases[2] = "kkpoop";
    testCases[3] = "t3kogpgq";
    testCases[4] = "iopiopznjgasgs;ew";
    testCases[5] = "iopipopombklg";
    testCases[6] = "itopi[vxaq";
    testCases[7] = "m,.m,m,.m[vxaq";
    testCases[8] = "m532vkjl;q[";
    testCases[9] = "ipp[[p[p]]];q[";
    testCases[10] = "m532vkjl;ffasswi[";
    testCases[11] = "ioioipo;q[";
    testCases[12] = "ggccaqw;q[";
    testCases[13] = "lll;l;lllqew;q[";
    testCases[14] = "''qwrqzsafgwrq;q[";
    testCases[15] = "''rqzm,xmnqwfaqwewqrwq;q[";
    testCases[16] = "''rqzm,m,,.v,x.,asdqwrq;q[";
    testCases[17] = "''opiopipoiopz,m,,.v,x.,asdqwrq;q[";
    testCases[17] = "241jkl,.vz002-31";
    testCases[18] = ".,.,.,.,.,4141kasfsp[a],.vz002-31";

    return testCases;

  }
}