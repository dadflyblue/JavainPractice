package dadflyblue;

import java.util.AbstractList;
import java.util.List;

public class PayloadList<E, V> extends AbstractList<E> {

  private final V payload;

  public PayloadList(V payload) {
    super();
    this.payload = payload;
  }

  @Override
  public E get(int index) {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  public V getPayload() {
    return payload;
  }

  public static void main(String[] args) {
    List<Integer> list = new PayloadList<>("hello");
    list = new PayloadList<>(new byte[8]);
  }
}
