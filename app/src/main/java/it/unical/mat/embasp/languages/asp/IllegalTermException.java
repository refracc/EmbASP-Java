package it.unical.mat.embasp.languages.asp;

import java.io.Serial;

public class IllegalTermException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public IllegalTermException(final String msg) {
    super(msg);
  }

}
