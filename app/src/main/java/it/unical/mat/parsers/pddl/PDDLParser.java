package it.unical.mat.parsers.pddl;

import it.unical.mat.parsers.pddl.pddl_parser_base.PDDLGrammarBaseVisitor;
import it.unical.mat.parsers.pddl.pddl_parser_base.PDDLGrammarLexer;
import it.unical.mat.parsers.pddl.pddl_parser_base.PDDLGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;

import java.util.ArrayList;

public class PDDLParser extends PDDLGrammarBaseVisitor<Void> {
  private final ArrayList<String> parameters = new ArrayList<>();

  private PDDLParser() {

  }

  public static final PDDLParser parse(final String action) {
    final CommonTokenStream tokens = new CommonTokenStream(new PDDLGrammarLexer(CharStreams.fromString(action)));
    final PDDLGrammarParser parser = new PDDLGrammarParser(tokens);
    final PDDLParser visitor = new PDDLParser();

    parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
    parser.removeErrorListeners();
    parser.setErrorHandler(new BailErrorStrategy());

    try {
      visitor.visit(parser.output());
    } catch (final RuntimeException exception) {
      if (exception.getClass() == RuntimeException.class && exception.getCause() instanceof RecognitionException) {
        tokens.seek(0);
        parser.addErrorListener(ConsoleErrorListener.INSTANCE);
        parser.setErrorHandler(new DefaultErrorStrategy());
        parser.getInterpreter().setPredictionMode(PredictionMode.LL);
        visitor.visit(parser.output());
      }
    }

    return visitor;
  }

  public String[] getParameters() {
    return parameters.toArray(new String[0]);
  }

  @Override
  public Void visitAtom(PDDLGrammarParser.AtomContext ctx) {
    for (int index = 1; index < ctx.IDENTIFIER().size(); index++)
      parameters.add(ctx.IDENTIFIER(index).getText());

    return null;
  }
}