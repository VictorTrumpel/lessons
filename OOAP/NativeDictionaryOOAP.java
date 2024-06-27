/*
Отчет по предыдущей работе:
По какой-то причине я не добавил метод get в мою имплементацию HashTable. В этом и была моя ошибка.
В остальном все правильно.
*/
package native_dictionary;

import java.util.HashMap;

public class NativeDictionaryOOAP<T> {
  private HashMap<String, T> hashMap = new HashMap<String, T>();

  private Integer GET_OK = 1;
  private Integer GET_ERR = 2;

  private Integer getStatus = GET_OK;

  public void set(String key, T value) {
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

  public Boolean hasKey(String key) {
    return hashMap.containsKey(key);
  }

  public Integer getGetStatus() {
    return getStatus;
  }
}
