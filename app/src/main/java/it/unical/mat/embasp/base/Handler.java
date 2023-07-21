package it.unical.mat.embasp.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A collection of {@link InputProgram} and {@link OptionDescriptor}.
 * The subclasses have to implement {@link #startAsync(Callback, List, List)} and {@link #startSync(List, List)} methods.
 * Each instance inside {@link #programs} and {@link #options} are represented by an integer (id) , respectively.
 *
 * @see ArrayList
 * @see HashMap
 * @see List
 * @see Map
 */

public abstract class Handler {

  /**
   * Where {@link InputProgram} elements are stored.
   */
  protected Map<Integer, InputProgram> programs;
  /**
   * Where {@link OptionDescriptor} elements are stored
   */
  protected Map<Integer, OptionDescriptor> options;

  public Handler() {
    programs = new HashMap<>();
    options = new HashMap<>();
  }

  /**
   * Add a new element inside {@link #options} set.
   *
   * @param o The new {@link OptionDescriptor} instance
   * @return the id associate to the new added {@link OptionDescriptor} instance
   */
  public int addOption(final OptionDescriptor o) {
    final int current_value = options.size();
    options.put(options.size() + 1, o);
    return current_value;
  }

  /**
   * Add a new element inside {@link #programs} set.
   *
   * @param program is the {@link InputProgram} instance added to the collection.
   * @return the id associate to the new added {@link InputProgram} instance.
   */

  public int addProgram(final InputProgram program) {
    final int current_value = programs.size();
    programs.put(programs.size() + 1, program);
    return current_value;
  }

  protected List<OptionDescriptor> collect_options(final List<Integer> option_index) {
    final List<OptionDescriptor> input_option = new ArrayList<>();

    if (option_index == null)
      for (final Map.Entry<Integer, OptionDescriptor> option : options.entrySet())
        input_option.add(options.get(option.getKey()));
    else
      for (final int index : option_index)
        input_option.add(options.get(index));
    return input_option;
  }

  protected List<InputProgram> collect_programs(final List<Integer> program_index) {

    final List<InputProgram> input_programs = new ArrayList<>();

    if (program_index == null)
      for (final Map.Entry<Integer, InputProgram> program : programs.entrySet())
        input_programs.add(programs.get(program.getKey()));
    else
      for (final int index : program_index)
        input_programs.add(programs.get(index));
    return input_programs;
  }

  /**
   * Returns the specified {@link InputProgram} element
   *
   * @param key the id
   * @return {@link InputProgram} element associate with the given key
   */
  public InputProgram getInputProgram(final int key) {
    return programs.get(key);
  }

  /**
   * Returns the specified {@link OptionDescriptor} element
   *
   * @param key the id
   * @return {@link OptionDescriptor} element associate with the given key
   */
  public OptionDescriptor getOptionDescriptor(final int key) {
    return options.get(key);
  }

  /**
   * Removes all the elements from {@link #programs} and {@link #options}.Both of the collections will be empty after this method returns
   */
  public void removeAll() {

    programs.clear();
    options.clear();

  }

  /**
   * Removes the element associate within the given id from {@link #options} set.
   *
   * @param option_id the id associate within an element
   */
  public void removeOption(final int option_id) {

    options.remove(option_id);

  }

  /**
   * Removes every occurrence of a specified {@link OptionDescriptor} element from {@link #options} set.
   *
   * @param o element to be removed
   * @return true if one or more elements are removed , false otherwise
   */
  public boolean removeOption(final OptionDescriptor o) {

    boolean result = false;
    for (final Map.Entry<Integer, OptionDescriptor> entry : options.entrySet())
      if (entry.getValue().equals(o)) {
        options.remove(entry.getKey());
        result = true;
      }

    return result;

  }

  /**
   * Removes every occurrence of a specified {@link InputProgram} element from {@link #programs} set.
   *
   * @param p element to be removed
   * @return true if one or more elements are removed , false otherwise
   */

  public boolean removeProgram(final InputProgram p) {

    boolean result = false;
    for (final Map.Entry<Integer, InputProgram> entry : programs.entrySet())
      if (entry.getValue().equals(p)) {
        programs.remove(entry.getKey());
        result = true;
      }
    return result;
  }

  /**
   * Removes the element associate within the given id from {@link #programs} set.
   *
   * @param program_id the id associate within an element
   */

  public void removeProgram(final int program_id) {

    programs.remove(program_id);
  }

  /**
   * @see Service
   */
  public void startAsync(final Callback c) {
    startAsync(c, null, null);
  }

  /**
   * This method have to be implemented by subclasses to execute solver in an asynchronous way, if no parameters are given
   * the entire sets of programs and option are used
   */
  public void startAsync(final Callback c, final List<Integer> program_index, final List<Integer> option_index) {
  }

  /**
   * @see #startSync(List, List)
   */
  public Output startSync() {

    return startSync(null, null);
  }

  /**
   * This method have to be implemented by subclasses to execute solver in a synchronous way, if no parameters are given
   * the entire sets of programs and option are used
   *
   * @see Service
   */
  public Output startSync(final List<Integer> program_index, final List<Integer> option_index) {
    return null;
  }
}
