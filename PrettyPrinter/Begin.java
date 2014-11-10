import java.io.*;

class Begin extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.

    public Begin() { }

    void print(Node t, int n, boolean p) {
    	System.out.println("(begin");
    	//for (int i = 0; i < n; i++)
      		System.out.print(" ");
    	Node cdr = t.getCdr();

    	if (cdr != null)
    		cdr.print(n, true);
    }
}
