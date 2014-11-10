import java.io.*;

class Set extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.

    public Set() { }

    void print(Node t, int n, boolean p) {
    	if (!p)
    		System.out.print("(");
    	t.getCar().print(n);
    	//for (int i = 0; i < n; i++)
    		System.out.print(" ");
            t.getCdr().print(n, true);
    }
}
