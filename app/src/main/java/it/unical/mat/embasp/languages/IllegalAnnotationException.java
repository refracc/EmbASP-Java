package it.unical.mat.embasp.languages;

import java.io.Serial;

public class IllegalAnnotationException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public IllegalAnnotationException() {
    super("bad annotation");
  }

}
