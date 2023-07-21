package it.unical.mat.embasp.base;

/**
 * Represents options for a generic ASP programs
 */
public class OptionDescriptor {
  /**
   * used as option separator
   */
  protected String separator;
  /**
   * where options are stored
   */
  protected String options;

  public OptionDescriptor() {
    options = "";
    separator = "";
  }

  public OptionDescriptor(final String initial_option) {
    this();
    options = initial_option;
  }

  /**
   * concatenate a new option in a String format to the current {@link #options}
   *
   * @param option String to be concatenated
   */
  public void addOption(final String option) {
    if (option.isEmpty())
      options = option;
    else
      options += separator + option;

  }

  /**
   * After using this method the {@link #options} variable will be empty
   */
  public void clear() {

    options = "";
  }

  /**
   * Returns values stored in {@link #options}
   *
   * @return {@link #options}'s data in a String format
   */
  public String getOptions() {
    return options;
  }

  public void setOptions(final String option) {
    options = option;
  }

  /**
   * get separator character
   */
  public String getSeparator() {
    return separator;
  }

  /**
   * Set {@link #separator} character with new separator
   */
  public void setSeparator(final String separator) {
    this.separator = separator;
  }
}
