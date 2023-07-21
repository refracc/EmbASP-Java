package it.unical.mat.embasp.languages;

import java.io.Serial;

public class ObjectNotValidException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public ObjectNotValidException() {
    super("Value of the object is not valid");
  }

}
