package it.unical.mat.parsers.asp;

import it.unical.mat.parsers.asp.asp_parser_base.ASPGrammarBaseVisitor;
import it.unical.mat.parsers.asp.asp_parser_base.ASPGrammarLexer;
import it.unical.mat.parsers.asp.asp_parser_base.ASPGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ASPParser extends ASPGrammarBaseVisitor<Void> {
  private final ArrayList<String> parameters = new ArrayList<>();

  private ASPParser() {

  }

  public static @NotNull ASPParser parse(final String atom) {
    final CommonTokenStream tokens = new CommonTokenStream(new ASPGrammarLexer(CharStreams.fromString(atom)));
    final ASPGrammarParser parser = new ASPGrammarParser(tokens);
    final ASPParser visitor = new ASPParser();

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
  public Void visitTerm(ASPGrammarParser.@NotNull TermContext ctx) {
    parameters.add(ctx.getText());

    return null;
  }
}
