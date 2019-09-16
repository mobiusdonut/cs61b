public class Dog {
  public Dog(){ /* D1 */ }
  public void bark(Dog d) {
    System.out.println("woof");
  }
  public static void main(String[] args) {
    Dog d = new Corgi();
    // (Compile-Error)   Runtime-Error  C1  D1
    Corgi c = new Corgi();
    // Compile-Error   Runtime-Error  C1  D1
    Dog d2 = new Dog();
    // Compile-Error   Runtime-Error  C1  D1
    Corgi c2 = new Corgi();
    // (Compile-Error)   Runtime-Error  C1  D1
    Corgi c3 = (Corgi) new Dog();
    // Compile-Error   Runtime-Error  C1  D1
    //d.play(d);
    //(Compile-Error)   Runtime-Error   A   B   C   D   E
    //d.play(c);
    //(Compile-Error)   Runtime-Error   A   B   C   D   E
    c.play(d);
    //Compile-Error   Runtime-Error   A   B   C   D   E
    c.play(c);
    //Compile-Error   Runtime-Error   A   B   C   D   E
    c.bark(d);
    //Compile-Error   Runtime-Error   A   B   C   D   E
    c.bark(c);
    //Compile-Error   Runtime-Error   A   B   C   D   E
    d.bark(d);
    //Compile-Error   Runtime-Error   A   B   C   D   E
    //d.bark((int)c);
    //(Compile-Error)   Runtime-Error   A   B   C   D   E
    c.bark((Corgi)d2);
    //Compile-Error   Runtime-Error   A   B   C   D   E
  }
}

class Corgi extends Dog {
  public Corgi(){ /* C1 */ }
  public void bark(Corgi c) {
    System.out.println("bork");
  }
  @Override
  public void bark(Dog d) {
    System.out.println("björk");
  }
  public void play(Dog d) {
    System.out.println("*excited borking*");
  }
  public void play(Corgi c) {
    System.out.println("*REALLY excited borking*");
  }
}
