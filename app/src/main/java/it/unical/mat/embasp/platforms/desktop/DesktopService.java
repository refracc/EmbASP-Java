/*
 * August 25, 2020 Fixed hanging solver due to buffer not read
 * (Copyright for this change in method startSync (c) Siemens Aktiengesellschaft Oesterreich, 2020)
 *
 * SPDX-License-Identifier: MIT
 */
package it.unical.mat.embasp.platforms.desktop;

import it.unical.mat.embasp.base.*;

import java.io.*;
import java.util.List;

/**
 * is a specialization for a Desktop platform
 *
 * @see Service
 */

public abstract class DesktopService implements Service {
  /**
   * Stores solver's executable path
   */
  protected String exe_path;
  protected String load_from_STDIN_option;

  public DesktopService(final String exe_path) {
    this.exe_path = exe_path;
  }

  public String getExePath() {
    return exe_path;
  }

  /**
   * set {@link #exe_path} to a new path*
   *
   * @param exe_path a string representing the path for the new solver
   */
  public void setExePath(final String exe_path) {
    this.exe_path = exe_path;
  }

  abstract protected Output getOutput(String output, String error);

  /**
   * Start a new process for the {@link #exe_path} and starts solving
   *
   * @see it.unical.mat.embasp.base.Service#startAsync(Callback, List, List)
   */
  @Override
  public void startAsync(final Callback callback, final List<InputProgram> programs, final List<OptionDescriptor> options) {

    new Thread(() -> callback.callback(startSync(programs, options))).start();

  }

  /**
   * Start a new process for the {@link #exe_path} and starts solving
   *
   * @see it.unical.mat.embasp.base.Service#startSync(List, List)
   */
  @Override
  public Output startSync(final List<InputProgram> programs, final List<OptionDescriptor> options) {

    StringBuilder option = new StringBuilder();
    for (final OptionDescriptor o : options)
      if (o != null) {
        option.append(o.getOptions());
        option.append(o.getSeparator());
      } else
        System.err.println("Warning : wrong " + OptionDescriptor.class.getName());

    StringBuilder files_paths = new StringBuilder();
    StringBuilder final_program = new StringBuilder();

    for (final InputProgram p : programs)
      if (p != null) {
        final_program.append(p.getPrograms());
        for (final String program_file : p.getFilesPaths()) {
          File f = new File(program_file);
          if (f.exists() && !f.isDirectory()) {
            files_paths.append(program_file);
            files_paths.append(" ");
          } else
            System.err.println("Warning : the file " + f.getAbsolutePath() + " does not exists.");
        }
      } else
        System.err.println("Warning : wrong " + InputProgram.class.getName());

    final StringBuffer solverOutput = new StringBuffer();
    final StringBuffer solverError = new StringBuffer();

    try {

      final long startTime = System.nanoTime();

      if (exe_path == null)
        return new Output("", "Error: executable not found");

      final StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append(exe_path).append(" ").append(option).append(" ").append(files_paths);

      if (final_program.length() > 0) {
        stringBuffer.append(this.load_from_STDIN_option);
      }

      System.err.println(stringBuffer);

      final Process solver_process = Runtime.getRuntime().exec(stringBuffer.toString());

      Thread threadOutput = new Thread(() -> {
        try {

          final BufferedReader bufferedReaderOutput = new BufferedReader(new InputStreamReader(solver_process.getInputStream()));

          // Read output of the solver and store in solverOutput
          String currentLine;
          while ((currentLine = bufferedReaderOutput.readLine()) != null)
            solverOutput.append(currentLine).append("\n");
        } catch (final IOException e) {
          e.printStackTrace();
        }

      });
      threadOutput.start();

      Thread threadError = new Thread(() -> {
        try {

          final BufferedReader bufferedReaderError = new BufferedReader(new InputStreamReader(solver_process.getErrorStream()));

          String currentErrLine;
          while ((currentErrLine = bufferedReaderError.readLine()) != null)
            solverError.append(currentErrLine).append("\n");

        } catch (final IOException e) {
          e.printStackTrace();
        }

      });
      threadError.start();

      if (final_program.length() > 0) {
        final PrintWriter writer = new PrintWriter(solver_process.getOutputStream());
        writer.println(final_program);
        writer.close();
        solver_process.waitFor();
      }

      threadOutput.join();
      threadError.join();

      final long stopTime = System.nanoTime();
      System.err.println("Total time : " + (stopTime - startTime));

      return getOutput(solverOutput.toString(), solverError.toString());

    } catch (final IOException | InterruptedException e) {
      e.printStackTrace();
    }

    return getOutput("", "");

  }

}
