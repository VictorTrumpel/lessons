package native_dictionary;

import java.util.HashMap;

public class NativeDictionaryOOAP<T> {
  private HashMap<String, T> hashMap = new HashMap<String, T>();

  private Integer GET_OK = 1;
  private Integer GET_ERR = 2;

  private Integer REMOVE_OK = 1;
  private Integer REMOVE_ERR = 2;

  private Integer getStatus = GET_OK;
  private Integer removeStatus = REMOVE_OK;

  public void put(String key, T value) {
    hashMap.put(key, value);
  }

  public T get(String key, T value) {
    if (hashMap.containsKey(key)) {
      getStatus = GET_OK;
      return hashMap.get(key);
    }

    getStatus = GET_ERR;

    return null;
  }

  public void remove(String key) {
    if (hashMap.get(key) != null) {
      hashMap.remove(key);
      removeStatus = REMOVE_OK;
      return;
    }

    removeStatus = REMOVE_ERR;
  }

  public boolean isKey(String key) {
    return hashMap.keySet().contains(key);
  }

  public Integer size() {
    return hashMap.size();
  }

  public Boolean hasKey(String key) {
    return hashMap.containsKey(key);
  }

  public Integer getGetStatus() {
    return getStatus;
  }

  public Integer getRemoveStatus() {
    return removeStatus;
  }
}

