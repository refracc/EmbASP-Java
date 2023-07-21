package it.unical.mat.embasp.platforms.desktop;

import it.unical.mat.embasp.base.*;

import java.util.List;

/**
 * is a specialization for a Desktop platform
 *
 * @see Handler
 */

public class DesktopHandler extends Handler {

  private final DesktopService service;

  public DesktopHandler(final DesktopService service) {
    this.service = service;
  }

  /**
   * @see it.unical.mat.embasp.base.Service#startAsync(Callback, List, List)
   */
  @Override
  public void startAsync(final Callback c, final List<Integer> program_index, final List<Integer> option_index) {

    final List<InputProgram> input_programs = collect_programs(program_index);
    final List<OptionDescriptor> input_options = collect_options(option_index);

    service.startAsync(c, input_programs, input_options);

  }

  /**
   * @see it.unical.mat.embasp.base.Service#startAsync(Callback, List, List)
   */
  @Override
  public Output startSync(final List<Integer> program_index, final List<Integer> option_index) {

    final List<InputProgram> input_programs = collect_programs(program_index);
    final List<OptionDescriptor> input_options = collect_options(option_index);

    return service.startSync(input_programs, input_options);
  }
}
