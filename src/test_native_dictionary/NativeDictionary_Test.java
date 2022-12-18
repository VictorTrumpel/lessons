package test_native_dictionary;

import static org.junit.Assert.*;

import org.junit.Test;

import native_dictionary.*;

public class NativeDictionary_Test {

  @Test
  public void test_1() {
    NativeDictionary<String> dict = new NativeDictionary<String>(5, String.class);

    dict.put("key1", "VictorTrumpel");

    assertEquals(dict.get("key1"), "VictorTrumpel");

    assertEquals(dict.isKey("key1"), true);

    dict.put("key2", "LeonidSlotin");

    assertEquals(dict.isKey("key2"), true);

    assertEquals(dict.isKey("key3"), false);

    dict.put("key1", "VladimirPutin");

    assertEquals(dict.isKey("key1"), true);

    assertEquals(dict.get("key1"), "VladimirPutin");

    assertEquals(dict.get("key1"), "VladimirPutin");

    assertEquals(dict.get("key3"), null);

    assertEquals(dict.get("key2"), "LeonidSlotin");

    dict.put("key2", "Sonic X");

    assertEquals(dict.get("key2"), "Sonic X");

    assertEquals(dict.isKey("key2"), true);
  }
}
