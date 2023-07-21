package it.unical.mat.embasp.base;

/**
 * represents a generic output for a solver
 */
public class Output implements Cloneable {
  /**
   * Variable in which results are stored
   */
  protected String output;
  /**
   * The errors thrown by the solver
   */
  protected String errors;

  public Output() {
    output = "";
  }

  public Output(final String initial_output) {
    output = initial_output;
  }

  public Output(final String out, final String err) {
    output = out;
    errors = err;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public String getErrors() {
    return errors;
  }

  public void setErrors(final String err) {
    errors = err;
  }

  public String getOutput() {
    return output;
  }

  public void setOutput(final String output) {
    this.output = output;
  }

  protected void parse() {
  }
}
