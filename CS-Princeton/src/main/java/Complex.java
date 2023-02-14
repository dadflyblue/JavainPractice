public class Complex {

  private final double re, im;
  public Complex(double real, double imag) {
    this.re = real;
    this.im = imag;
  }

  public Complex plus(Complex b) {
    return new Complex(re + b.re, im + b.im);
  }

  public Complex mins(Complex b) {
    return new Complex(re - b.re, im - b.im);
  }

  public Complex multi(Complex b) {
    double real = re * b.re - im * b.im;
    double imag = re * b.im + im * b.re;
    return new Complex(real, imag);
  }

  public Complex div(Complex b) {
    double real = (re*b.re + im*b.im) / (b.re*b.re + b.im*b.im);
    double imag = (im*b.re - re*b.im) / (b.re*b.re + b.im*b.im);
    return new Complex(real, imag);
  }

  public double abs() {
    return Math.sqrt(re*re + im*im);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Complex)) {
      return false;
    }

    double EPSILON = 0.00000001;
    Complex other = (Complex) obj;
    return this.mins(other).abs() <= EPSILON;
  }

  @Override
  public String toString() {
    return re + " + " + im + "i";
  }

  public static void main(String[] args) {
    Complex a = new Complex(3.0, 4.0);
    Complex b = new Complex(-2.0, 3.0);

    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("a * b = " + a.multi(b));
  }
}
