import java.io.*;
class Ident extends Node {
  private String name;

  public Ident(String n) { name = n; }

  public String getName() {
  	return name;
  }

  public boolean isSymbol() {
  	return true;
  }

  public void print(int n) {
    for (int i = 0; i < n; i++)
      System.out.print(" ");

    System.out.print(name);
  }

  public void print( int n, boolean p)
  {
    if (p)
      print(n);
    else
      print(n);
  }
}
