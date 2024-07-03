package bloom_filter;
import java.util.HashSet;

/*
  Отчет по предудущей работе:
  Я добавил все методы, что необходимы в эталонном разборе.
  Но также я добавил статусы на добавление и удаление элемента.
  Я считаю это правильно т.к. в PowerSet мы не можем добавлять бесконечно элементов.
  Поэтому метод put может вызывать ошибку.
  В HashTable put ошибку не вызывает. Поэтому put в PowerSet пришлось переопределить.
 */

public class BloomFilterOOAP<T> {
  public HashSet<T> hashSet = new HashSet<T>();

  private Integer REMOVE_OK = 1;
  private Integer REMOVE_ERR = 2;

  private Integer removeStatus = REMOVE_OK;

  public BloomFilterOOAP(int f_len) {
  }

  public void add(T value) {
    hashSet.add(value);
  }

  public void remove(T value) {
    if (hashSet.contains(value)) {
      removeStatus = REMOVE_OK;
      hashSet.remove(value);
      return;
    }
    removeStatus = REMOVE_ERR;
  }

  public boolean isValue(T value) {
    return hashSet.contains(value);
  }

  public Integer getRemoveStatus() {
    return removeStatus;
  }
}
