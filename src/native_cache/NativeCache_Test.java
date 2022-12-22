package native_cache;

import static org.junit.Assert.*;

import org.junit.Test;

public class NativeCache_Test {

  @Test
  public void test_1() {
    int SIZE = 20000;

    NativeCache<String> native_cache = new NativeCache<String>(20000, String.class);

    assertEquals(native_cache.size(), 0);

    for (int i = 0; i < SIZE; i++) {
      native_cache.put(String.valueOf(i), String.valueOf(i));
    }

    assertEquals(native_cache.size(), 20000);

    for (int i = 0; i < SIZE; i++) {
      String value = native_cache.get(String.valueOf(i));
      assertEquals(String.valueOf(i), value);
    }

    assertEquals(native_cache.size(), 20000);
  }

  @Test
  public void test_2() {
    int SIZE = 20000;

    NativeCache<String> native_cache = new NativeCache<String>(20000, String.class);

    assertEquals(native_cache.size(), 0);

    for (int i = 0; i < SIZE; i++) {
      native_cache.put(String.valueOf(i), String.valueOf(i));
    }

    assertEquals(native_cache.size(), 20000);

    for (int i = 0; i < SIZE; i++) {
      if (i != 1) {
        String value = native_cache.get(String.valueOf(i));
        assertEquals(String.valueOf(i), value);
      }
    }

    assertEquals(native_cache.size(), 20000);

    native_cache.put("TEST", "TEST");

    assertEquals(native_cache.get("1"), null);

    assertEquals(native_cache.get("TEST"), "TEST");

    assertEquals(native_cache.size(), 20000);

    native_cache.put("TEST_2", "TEST_2");

    assertEquals(native_cache.get("TEST"), "TEST");

    assertEquals(native_cache.get("TEST_2"), "TEST_2");

    assertEquals(native_cache.size(), 20000);
  }
}
