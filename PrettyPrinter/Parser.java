// Parser.java -- the implementation of class Parser
//
// Defines
//
//   class Parser;
//
// Parses the language
//
//   exp  ->  ( rest
//         |  #f
//         |  #t
//         |  ' exp
//         |  integer_constant
//         |  string_constant
//         |  identifier
//    rest -> )
//         |  exp+ [. exp] )
//
// and builds a parse tree.  Lists of the form (rest) are further
// `parsed' into regular lists and special forms in the constructor
// for the parse tree node class Cons.  See Cons.parseList() for
// more information.
//
// The parser is implemented as an LL(0) recursive descent parser.
// I.e., parseExp() expects that the first token of an exp has not
// been read yet.  If parseRest() reads the first token of an exp
// before calling parseExp(), that token must be put back so that
// it can be reread by parseExp() or an alternative version of
// parseExp() must be called.
//
// If EOF is reached (i.e., if the scanner returns a NULL) token,
// the parser returns a NULL tree.  In case of a parse error, the
// parser discards the offending token (which probably was a DOT
// or an RPAREN) and attempts to continue parsing with the next token.

class Parser {
  private Scanner scanner;

  public Parser(Scanner s) { scanner = s; }
  
  public Node parseExp() {
    // TODO: write code for parsing an exp
    return parseExp(scanner.getNextToken());
  }

  public Node parseExp(Token T) {
    if (T.getType() == T.QUOTE)
      return new Cons(new Ident("quote"), new Cons(parseExp(), new Nil()));
    else if (T.getType() == T.LPAREN)
      return parseRest();
    else if (T.getType() == T.DOT) {
      System.out.println("Illegal dot in expression.");
      return parseExp();
    }
    else if (T.getType() == T.TRUE) 
      return new BooleanLit(true);
    else if (T.getType() == T.FALSE) 
      return new BooleanLit(false);
    else if (T.getType() == T.INT)
      return new IntLit(T.getIntVal());
    else if (T.getType() == T.STRING)
      return new StrLit(T.getStrVal());
    else if (T.getType() == T.IDENT)
      return new Ident(T.getName());
    else
      return null;
  }
  
  protected Node parseRest() {
    return parseRest(scanner.getNextToken());
  }

  protected Node parseRest(Token T) {
    if (T.getType() == T.RPAREN)
      return new Nil();
    else if (T.getType() != T.DOT) {
      Node foo = parseExp(T);
      Node oof = parseRest();
      return new Cons(foo, oof);
    }
    else {
      System.out.println("Illegal dot");
      return null;
    }
  }
  
  // TODO: Add any additional methods you might need.
}
