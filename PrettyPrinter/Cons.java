class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special form;
  
    // parseList() `parses' special forms, constructs an appropriate
    // object of a subclass of Special, and stores a pointer to that
    // object in variable form.  It would be possible to fully parse
    // special forms at this point.  Since this causes complications
    // when using (incorrect) programs as data, it is easiest to let
    // parseList only look at the car for selecting the appropriate
    // object from the Special hierarchy and to leave the rest of
    // parsing up to the interpreter.
    void parseList() { 
        if (car instanceof Ident) {
            Ident dent = (Ident)car;
            String foo = dent.getName();
            if (foo.equals("let"))
                form = new Let();
            else if (foo.equals("cond"))
                form = new Cond();
            else if (foo.equals("define"))
                form = new Define();
            else if (foo.equals("set!"))
                form = new Set();
            else if (foo.equals("if"))
                form = new If();
            else if (foo.equals("begin"))
                form = new Begin();
            else if (foo.equals("lambda"))
                form = new Lambda();
            else if (foo.equals("'") || foo.equals("quote"))
                form = new Quote();
            else 
                form = new Regular();
        }
        else
            form = new Regular();
    }
    // TODO: Add any helper functions for parseList as appropriate.

    public Cons(Node a, Node d) {
	car = a;
	cdr = d;
	parseList();
    }

    @Override
    public boolean isPair() {
        return true;
    }

    @Override
    public Node getCar() {
        return car;
    }

    @Override
    public void setCar(Node c) {
        car = c;
    }

    @Override
    public Node getCdr() {
        return cdr;
    }

    @Override 
    public void setCdr(Node c) {
        cdr = c;
    }


    void print(int n) {
	form.print(this, n, false);
    }

    void print(int n, boolean p) {
	form.print(this, n, p);
    }

}
