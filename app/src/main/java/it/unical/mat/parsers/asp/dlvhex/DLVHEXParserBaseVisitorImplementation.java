package it.unical.mat.parsers.asp.dlvhex;

import it.unical.mat.parsers.asp.ASPDataCollection;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.jetbrains.annotations.NotNull;

public class DLVHEXParserBaseVisitorImplementation extends DLVHEXParserBaseVisitor<Void> {
  private final ASPDataCollection answerSets;

  private DLVHEXParserBaseVisitorImplementation(final ASPDataCollection answerSets) {
    this.answerSets = answerSets;
  }

  public static void parse(final ASPDataCollection answerSets, final String atomsList, final boolean two_stageParsing) {
    final CommonTokenStream tokens = new CommonTokenStream(new DLVHEXLexer(CharStreams.fromString(atomsList)));
    final DLVHEXParser parser = new DLVHEXParser(tokens);
    final DLVHEXParserBaseVisitorImplementation visitor = new DLVHEXParserBaseVisitorImplementation(answerSets);

    if (!two_stageParsing) {
      visitor.visit(parser.output());
      return;
    }

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
  }

  @Override
  public Void visitModel(DLVHEXParser.ModelContext ctx) {
    answerSets.addAnswerSet();

    return visitChildren(ctx);
  }

  @Override
  public Void visitLevel(DLVHEXParser.@NotNull LevelContext ctx) {
    answerSets.storeCost(Integer.parseInt(ctx.INTEGER(1).getText()), Integer.parseInt(ctx.INTEGER(0).getText()));

    return null;
  }

  @Override
  public Void visitPredicate_atom(DLVHEXParser.@NotNull Predicate_atomContext ctx) {
    answerSets.storeAtom(ctx.getText());

    return null;
  }

  @Override
  public Void visitWitness(DLVHEXParser.WitnessContext ctx) {
    answerSets.addAnswerSet();

    return visitChildren(ctx);
  }
}
