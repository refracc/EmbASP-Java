package it.unical.mat.parsers.asp.dlv2;

import it.unical.mat.parsers.asp.ASPDataCollection;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class DLV2ParserBaseVisitorImplementation extends DLV2ParserBaseVisitor<Void> {
  private final ASPDataCollection answerSets;
  private HashMap<Integer, Integer> costs;

  private DLV2ParserBaseVisitorImplementation(final ASPDataCollection answerSets) {
    this.answerSets = answerSets;
  }

  public static void parse(final ASPDataCollection answerSets, final String atomsList, final boolean two_stageParsing) {
    final CommonTokenStream tokens = new CommonTokenStream(new DLV2Lexer(CharStreams.fromString(atomsList)));
    final DLV2Parser parser = new DLV2Parser(tokens);
    final DLV2ParserBaseVisitorImplementation visitor = new DLV2ParserBaseVisitorImplementation(answerSets);

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
  public Void visitAnswer_set(DLV2Parser.@NotNull Answer_setContext ctx) {
    answerSets.addAnswerSet();

    if (ctx.cost() != null && !ctx.cost().isEmpty()) {
      costs = new HashMap<>();
      final String[] firstCost = ctx.cost().COST_LABEL().getText().split(" ")[1].split("@");

      costs.put(Integer.parseInt(firstCost[1]), Integer.parseInt(firstCost[0]));
    }

    if (costs != null)
      costs.forEach((level, cost) -> answerSets.storeCost(level, cost));

    return visitChildren(ctx);
  }

  @Override
  public Void visitLevel(DLV2Parser.@NotNull LevelContext ctx) {
    final int level = Integer.parseInt(ctx.INTEGER(1).getText()), cost = Integer.parseInt(ctx.INTEGER(0).getText());

    costs.put(level, cost);
    answerSets.storeCost(level, cost);

    return null;
  }

  @Override
  public Void visitPredicate_atom(DLV2Parser.@NotNull Predicate_atomContext ctx) {
    answerSets.storeAtom(ctx.getText());

    return null;
  }
}
