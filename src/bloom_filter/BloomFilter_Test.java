package bloom_filter;

import bloom_filter.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class BloomFilter_Test {

  @Test
  public void test_1() {
    BloomFilter bloomFilter = new BloomFilter(32);

    bloomFilter.add("0123456789");

    assertEquals(bloomFilter.isValue("0123456789"), true);

    bloomFilter.add("1123456789");

    assertEquals(bloomFilter.isValue("1123456789"), true);
  }

}
