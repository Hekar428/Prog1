import java.io.*;

class Quote extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.

    public Quote() { }



    void print(Node t, int n, boolean p) {
    		System.out.print("'");
    	t.getCdr().print(0, true);
    }
}
