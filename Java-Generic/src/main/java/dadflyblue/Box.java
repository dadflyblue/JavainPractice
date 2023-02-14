package dadflyblue;

public class Box<T extends Comparable<T>> {
  private final T t;

  public Box(T t) {
    this.t = t;
  }

  T get() {
    return this.t;
  }

  public <U extends CharSequence> void inspect(U u) {
    System.out.println("T: " + t.getClass().getName());
    System.out.println("U: " + u.getClass().getName());
  }

  public <V extends Comparable<V>> void inspectBox(Box<V> box) {
    System.out.println("T: " + t.getClass().getName());
    System.out.println("V: " + box.t.getClass().getName());
  }

  public void compareBox(Box<T> other) {
    System.out.println("this compares other :" + this.t.compareTo(other.t));
  }

  public static void main(String[] args) {
    new Box<>(11).inspect("hello");
    new Box<>("hello").inspectBox(new Box<>(9.22));
    //new Box<>(11).compareBox(new Box<>("s")); // parameterized types incompatible error
    new Box<>(11).compareBox(new Box<>(9));
  }
}
