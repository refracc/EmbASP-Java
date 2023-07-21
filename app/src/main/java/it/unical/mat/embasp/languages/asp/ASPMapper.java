package it.unical.mat.embasp.languages.asp;

import it.unical.mat.embasp.languages.Mapper;
import it.unical.mat.parsers.asp.ASPParser;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Contains methods used to transform Objects into {@link it.unical.mat.embasp.base.InputProgram}
 */

public class ASPMapper extends Mapper {
  private static ASPMapper mapper;

  private ASPMapper() {
    super();
  }

  public static ASPMapper getInstance() {
    if (ASPMapper.mapper == null)
      ASPMapper.mapper = new ASPMapper();
    return ASPMapper.mapper;
  }

  @Override
  protected String getActualString(final String predicate, final @NotNull HashMap<Integer, Object> parametersMap) throws IllegalTermException {
    if (parametersMap.isEmpty())
      return predicate;

    StringBuilder atom = new StringBuilder(predicate + "(");
    for (int i = 0; i < parametersMap.size(); i++) {
      if (i != 0)
        atom.append(",");
      final Object objectTerm = parametersMap.get(i);
      if (objectTerm == null)
        throw new IllegalTermException("Wrong term number of predicate " + predicate);
      if (objectTerm instanceof Integer)
        atom.append(objectTerm);
      else if (objectTerm instanceof SymbolicConstant)
        atom.append(objectTerm);
      else
        atom.append("\"").append(objectTerm).append("\"");
    }
    atom.append(")");
    return atom.toString();

  }

  @Override
  protected String getId(final @NotNull String atom) {
    final int openBracketIndex = atom.indexOf("(");

    if (openBracketIndex == -1)
      return atom;

    return atom.substring(0, openBracketIndex);
  }

  @Override
  protected String[] getParam(final String atom) {
    return ASPParser.parse(atom).getParameters();
  }
}
