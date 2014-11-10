import java.io.*;

class Regular extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.

    public Regular() { }

    void print(Node t, int n, boolean p) {
    	if (!p)
    		System.out.print("(");
    	t.getCar().print(0);
    	if (!(t.getCdr().isNull())) {
    		//for (int i = 0; i < n; i++)
    			System.out.print(" ");
        }
    	t.getCdr().print(0, true);
    }
}
