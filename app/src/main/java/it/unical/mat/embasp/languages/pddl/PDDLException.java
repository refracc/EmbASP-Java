package it.unical.mat.embasp.languages.pddl;

import java.io.Serial;

public class PDDLException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public PDDLException(final String error) {
    super(error);
  }

}
