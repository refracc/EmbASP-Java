package it.unical.mat.embasp.platforms.android;


import android.content.Context;
import it.unical.mat.embasp.base.Callback;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * is a specialization of {@link Handler} for Android platform
 *
 * @see Context
 */

public class AndroidHandler extends Handler {


  private AndroidService service;


  public AndroidHandler(Context c, Class<?> b) {
    try {

      service = (AndroidService) Class.forName(b.getName()).getDeclaredConstructor(Context.class).newInstance(c);
    } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException |
             InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void startAsync(Callback c) {
    startAsync(c, null, null);
  }

  /**
   * @see Handler#startAsync(Callback, List, List)
   */
  @Override
  public void startAsync(Callback c, List<Integer> program_index, List<Integer> option_index) {

    List<InputProgram> input_programs = collect_programs(program_index);
    List<OptionDescriptor> input_options = collect_options(option_index);
    service.startAsync(c, input_programs, input_options);

  }
}
