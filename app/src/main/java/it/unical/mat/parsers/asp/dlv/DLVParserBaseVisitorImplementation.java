package it.unical.mat.parsers.asp.dlv;

import it.unical.mat.parsers.asp.ASPDataCollection;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.jetbrains.annotations.NotNull;

public class DLVParserBaseVisitorImplementation extends DLVParserBaseVisitor<Void> {
  private final ASPDataCollection answerSets;

  private DLVParserBaseVisitorImplementation(final ASPDataCollection answerSets) {
    this.answerSets = answerSets;
  }

  public static void parse(final ASPDataCollection answerSets, final String atomsList, final boolean two_stageParsing) {
    final CommonTokenStream tokens = new CommonTokenStream(new DLVLexer(CharStreams.fromString(atomsList)));
    final DLVParser parser = new DLVParser(tokens);
    final DLVParserBaseVisitorImplementation visitor = new DLVParserBaseVisitorImplementation(answerSets);

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
  public Void visitSimpleModel(DLVParser.SimpleModelContext ctx) {
    answerSets.addAnswerSet();

    return visitChildren(ctx);
  }

  @Override
  public Void visitWeightedModel(DLVParser.WeightedModelContext ctx) {
    answerSets.addAnswerSet();

    return visitChildren(ctx);
  }

  @Override
  public Void visitWitness(DLVParser.WitnessContext ctx) {
    answerSets.addAnswerSet();

    return visitChildren(ctx);
  }

  @Override
  public Void visitCost_level(DLVParser.@NotNull Cost_levelContext ctx) {
    answerSets.storeCost(Integer.parseInt(ctx.INTEGER_CONSTANT(1).getText()), Integer.parseInt(ctx.INTEGER_CONSTANT(0).getText()));

    return null;
  }

  @Override
  public Void visitPredicate(DLVParser.@NotNull PredicateContext ctx) {
    answerSets.storeAtom(ctx.getText());

    return null;
  }
}
