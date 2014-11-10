import java.io.*;

class Cond extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.

    public Cond() { }

    void print(Node t, int n, boolean p) {
    	if (!p)
    		System.out.print("(");
    	t.getCar().print(0);
    	//for (int i = 0; i < n; i++)
      		System.out.print(" ");
      	t.getCdr().print(0, true);
    }
}
