package it.unical.mat.embasp.languages.asp;

import java.io.Serial;

public class PredicateNotValidException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public PredicateNotValidException() {
    super("Value of predicate is not valid");
  }

}
