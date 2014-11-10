import java.io.*;

class Lambda extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.

    public Lambda() { }

    void print(Node t, int n, boolean p) {
    	t.getCar().print(0);
    	//for (int i = 0; i < n; i++)
    		System.out.print(" ");
    	t.getCdr().print(0, true);
    }
}
