package it.unical.mat.embasp.specializations.solver_planning_domains;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.pddl.PDDLException;
import it.unical.mat.embasp.languages.pddl.PDDLInputProgram;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class SPDUtility {

  public SPDUtility() {
  }

  private static @NotNull String escape(final @NotNull String escapable) {
    final StringBuilder builder = new StringBuilder();

    for (int i = 0; i < escapable.length(); i++) {
      final char character = escapable.charAt(i);

      switch (character) {
        case '"' -> builder.append("\\\"");
        case '\\' -> builder.append("\\\\");
        case '\b' -> builder.append("\\b");
        case '\f' -> builder.append("\\f");
        case '\n' -> builder.append("\\n");
        case '\r' -> builder.append("\\r");
        case '\t' -> builder.append("\\t");
        case '/' -> builder.append("\\/");
        default -> {
          if (character <= '\u001F' || character >= '\u007F' && character <= '\u009F' || character >= '\u2000' && character <= '\u20FF') {
            final String characterHexCode = Integer.toHexString(character);

            builder.append("\\u");

            builder.append("0".repeat(4 - characterHexCode.length()));

            builder.append(characterHexCode.toUpperCase());
          } else
            builder.append(character);
        }
      }
    }

    return builder.toString();
  }

  public String createJson(final @NotNull List<InputProgram> pddlInputProgram) throws PDDLException {

    StringBuilder problem = new StringBuilder();
    StringBuilder domain = new StringBuilder();

    for (final InputProgram ip : pddlInputProgram) {
      if (!(ip instanceof PDDLInputProgram pip))
        continue;
      switch (pip.getProgramsType()) {
        case DOMAIN -> {
          domain.append(pip.getPrograms()).append(pip.getSeparator());
          domain.append(getFromFile(pip.getFilesPaths(), pip.getSeparator()));
        }
        case PROBLEM -> {
          problem.append(pip.getPrograms()).append(pip.getSeparator());
          problem.append(getFromFile(pip.getFilesPaths(), pip.getSeparator()));
        }
        default -> throw new PDDLException("Program type : " + pip.getProgramsType() + " not valid.");
      }
    }

    if (problem.toString().equals(""))
      throw new PDDLException("Problem file not specified");
    if (domain.toString().equals(""))
      throw new PDDLException("Domain file not specified");

    return "{\"problem\":\"" + escape(problem.toString()) + "\", \"domain\":\"" + escape(domain.toString()) + "\"}";
  }

  private @NotNull String getFromFile(final @NotNull List<String> filesPaths, final String separator) {
    final StringBuilder toReturn = new StringBuilder();
    for (final String s : filesPaths)
      try {
        toReturn.append(readFile(s)).append(separator);
      } catch (final IOException e) {
        e.printStackTrace();
      }
    return toReturn.toString();
  }

  public String postJsonToURL(final String json) throws PDDLException {

    String result;
    try {
      final URL myurl = new URL("https://solver.planning.domains/solve");
      final HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
      con.setDoOutput(true);
      con.setDoInput(true);

      con.setRequestProperty("Content-Type", "application/json");
      // con.setRequestProperty("Accept", "application/json,text/plain");
      con.setRequestProperty("Method", "POST");
      final OutputStream os = con.getOutputStream();
      os.write(json.getBytes(StandardCharsets.UTF_8));
      os.flush();
      os.close();

      final StringBuilder sb = new StringBuilder();
      final int HttpResult = con.getResponseCode();
      if (HttpResult == HttpURLConnection.HTTP_OK) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null)
          sb.append(line).append("\n");
        br.close();
        result = sb.toString();
      } else
        throw new PDDLException("HTTP connection error, response code : " + con.getResponseCode() + " response message : " + con.getResponseMessage());
    } catch (final Exception e) {
      throw new PDDLException("Impossible to perform HTTP connection: " + e.getMessage());
    }
    return result;

  }

  protected abstract String readFile(String s) throws IOException;

}
