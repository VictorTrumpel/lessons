package bloom_filter;

public class BloomFilter {
  public int filter_len;
  public int[] bitArray;

  public BloomFilter(int f_len) {
    filter_len = f_len;
    bitArray = new int[f_len];

    for (int i = 0; i < f_len; i++)
      bitArray[i] = 0;
  }

  public int hash1(String str1) {
    int seed = 17;
    int result = 0;

    for (int i = 0; i < str1.length(); i++) {
      int code = (int) str1.charAt(i);
      result = (seed * result + code) % filter_len;
    }

    return result;
  }

  public int hash2(String str1) {
    int seed = 223;
    int result = 0;

    for (int i = 0; i < str1.length(); i++) {
      int code = (int) str1.charAt(i);
      result = (seed * result + code) % filter_len;
    }

    return result;
  }

  public void add(String str1) {
    int idx1 = hash1(str1);
    int idx2 = hash2(str1);
    bitArray[idx1] = 1;
    bitArray[idx2] = 1;
  }

  public boolean isValue(String str1) {
    int idx1 = hash1(str1);
    int idx2 = hash2(str1);

    return bitArray[idx1] == 1 && bitArray[idx2] == 1;
  }
}