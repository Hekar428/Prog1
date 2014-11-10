// Scanner.java -- the implementation of class Scanner

import java.io.*;
import java.lang.StringBuilder;

class Scanner {
  private PushbackInputStream in;
  private byte[] buf = new byte[1000];

  public Scanner(InputStream i) { in = new PushbackInputStream(i); }
    
  public boolean whiteSpace(char ch) {
    if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r' || ch == '\f')
      return true;
    else
      return false;
  }

  public boolean isIdent(char ch) {
    if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '<' && ch <= '@' )
        || ch == '!' ||ch == '%' ||ch == '&' ||ch == '*' ||ch == '+' ||ch == '-' ||ch == '.' ||ch == '/' 
        || ch == ':' ||ch == '^' ||ch == '_' ||ch == '~' )
      return true;
    else
      return false;
  }

  public Token getNextToken() {
    int bite = -1;
	
    // It would be more efficient if we'd maintain our own input buffer
    // and read characters out of that buffer, but reading individual
    // characters from the input stream is easier.
    try {
      bite = in.read();
    } catch (IOException e) {
      System.err.println("We fail: " + e.getMessage());
    }

    // TODO: skip white space and comments

    char ch = (char)bite;
    if (whiteSpace(ch))
      return getNextToken();

    else if (ch == ';') {
      do {
        try {
          bite = in.read();
        } catch (IOException e) {
          System.err.println("We fail: " + e.getMessage());
        }
        ch = (char)bite;
      } while ((ch != '\n'));
      return getNextToken();
    }
	
    if (bite == -1)
      return null;

    //ch = (char)bite;
	
    // Special characters
    if (ch == '\'')
      return new Token(Token.QUOTE);
    else if (ch == '(')
      return new Token(Token.LPAREN);
    else if (ch == ')')
      return new Token(Token.RPAREN);
    else if (ch == '.')
      // We ignore the special identifier `...'.
      return new Token(Token.DOT);

    // Boolean constants
    else if (ch == '#') {
      try {
	bite = in.read();
      } catch (IOException e) {
	System.err.println("We fail: " + e.getMessage());
      }

      if (bite == -1) {
	System.err.println("Unexpected EOF following #");
	return null;
      }
      ch = (char) bite;
      if (ch == 't')
	return new Token(Token.TRUE);
      else if (ch == 'f')
	return new Token(Token.FALSE);
      else {
	System.err.println("Illegal character '" + (char) ch + "' following #");
	return getNextToken();
      }
    }

    // String constants
    // TODO: scan a string into the buffer variable buf

    else if (ch == '"') {
      StringBuilder sb = new StringBuilder();

      try {
        bite = in.read();
      } catch (IOException e) {
        System.err.println("We fail: " + e.getMessage());
      }
      ch = (char)bite;

      while (ch != '"') {
        sb.append(ch);
        try {
          bite = in.read();
        } catch (IOException e) {
          System.err.println("We fail: " + e.getMessage());
        }
        ch = (char)bite;
      }
      return new StrToken(sb.toString());
    }

    // Integer constants
    else if (ch >= '0' && ch <= '9') {
      int i = ch - '0';
      // try {
      //   bite = in.read();
      //   while (ch >= '0' && ch <= '9') {
      //     ch = (char)bite;
      //     bite = in.read();
      //   } 
      // } catch (IOException e) {
      //     System.err.println("We fail: " + e.getMessage());
      //   }
      //   try {
      //     in.unread(ch);
      //   } catch (IOException e) {
      //     System.err.println("We fail: " + e.getMessage());
      //   }
      // TODO: scan the number and convert it to an integer

      // put the character after the integer back into the input
      // in->putback(ch);
      return new IntToken(i);
    }

    // Identifiers
    else if (isIdent(ch)) {
      StringBuilder sb = new StringBuilder();
      sb.append(ch);
      try {
        bite = in.read();
      } catch (IOException e) {
        System.err.println("We fail: " + e.getMessage());
      }
      ch = (char)bite;

      while (isIdent(ch)) {
        sb.append(ch);
        try {
          bite = in.read();
        } catch (IOException e) {
          System.err.println("We fail: " + e.getMessage());
        }
        ch = (char)bite;
      }

      try {
        in.unread(ch);
      } catch (IOException e) {
        System.err.println("We fail: " + e.getMessage());
      }
      // TODO: scan an identifier into the buffer

      // put the character after the identifier back into the input
      // in->putback(ch);
      return new IdentToken(sb.toString());
    }

    // Illegal character
    else {
      System.err.println("Illegal input character '" + (char) ch + '\'');
      return getNextToken();
    }
  };
}
