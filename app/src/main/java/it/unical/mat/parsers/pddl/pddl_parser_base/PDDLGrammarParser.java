package it.unical.mat.parsers.pddl.pddl_parser_base;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PDDLGrammarParser extends Parser {
  public static final int
      T__0 = 1, T__1 = 2, IDENTIFIER = 3, SEPARATOR = 4;
  public static final int
      RULE_atom = 0, RULE_output = 1;
  public static final String[] ruleNames = {
      "atom", "output"
  };
  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  public static final String serialisedATN =
      """
          \3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\6\26\4\2\t\2\4\3\t\3\3\2\3\2\7\2\t
          \2\f\2\16\2\f\13\2\3\2\3\2\3\3\7\3\21
          \3\f\3\16\3\24\13\3\3\3\2\2\4\2\4\2\2\2\25\2\6\3\2\2\2\4\22\3\2\2\2\6
          \7\3\2\2\7\t\7\5\2\2\b\7\3\2\2\2\t\f\3\2\2\2
          \b\3\2\2\2
          \13\3\2\2\2\13\r\3\2\2\2\f
          \3\2\2\2\r\16\7\4\2\2\16\3\3\2\2\2\17\21\5\2\2\2\20\17\3\2\2\2\21\24\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\5\3\2\2\2\24\22\3\2\2\2\4
          \22""";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(serialisedATN.toCharArray());
  protected static final DFA[] decisionToDFA;
  protected static final PredictionContextCache sharedContextCache =
      new PredictionContextCache();
  private static final String[] _LITERAL_NAMES = {
      null, "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
      null, null, null, "IDENTIFIER", "SEPARATOR"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

  static {
    RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
  }

  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  static {
    decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }

  public PDDLGrammarParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this, _ATN, decisionToDFA, sharedContextCache);
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override

  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  @Override
  public String getGrammarFileName() {
    return "PDDLGrammar.g4";
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return serialisedATN;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }

  public final @NotNull AtomContext atom() throws RecognitionException {
    AtomContext localContext = new AtomContext(_ctx, getState());
    enterRule(localContext, 0, RULE_atom);
    int _la;
    try {
      enterOuterAlt(localContext, 1);
      {
        setState(4);
        match(T__0);
        setState(8);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == IDENTIFIER) {
          {
            {
              setState(5);
              match(IDENTIFIER);
            }
          }
          setState(10);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
        setState(11);
        match(T__1);
      }
    } catch (RecognitionException re) {
      localContext.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return localContext;
  }

  public final @NotNull OutputContext output() throws RecognitionException {
    OutputContext localContext = new OutputContext(_ctx, getState());
    enterRule(localContext, 2, RULE_output);
    int _la;
    try {
      enterOuterAlt(localContext, 1);
      {
        setState(16);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la == T__0) {
          {
            {
              setState(13);
              atom();
            }
          }
          setState(18);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
      }
    } catch (RecognitionException re) {
      localContext.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    } finally {
      exitRule();
    }
    return localContext;
  }

  public static class AtomContext extends ParserRuleContext {
    public AtomContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public List<TerminalNode> IDENTIFIER() {
      return getTokens(PDDLGrammarParser.IDENTIFIER);
    }

    public TerminalNode IDENTIFIER(int i) {
      return getToken(PDDLGrammarParser.IDENTIFIER, i);
    }

    @Override
    public int getRuleIndex() {
      return RULE_atom;
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof PDDLGrammarVisitor) return ((PDDLGrammarVisitor<? extends T>) visitor).visitAtom(this);
      else return visitor.visitChildren(this);
    }
  }

  public static class OutputContext extends ParserRuleContext {
    public OutputContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }

    public List<AtomContext> atom() {
      return getRuleContexts(AtomContext.class);
    }

    public AtomContext atom(int i) {
      return getRuleContext(AtomContext.class, i);
    }

    @Override
    public int getRuleIndex() {
      return RULE_output;
    }

    @Override
    public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
      if (visitor instanceof PDDLGrammarVisitor) return ((PDDLGrammarVisitor<? extends T>) visitor).visitOutput(this);
      else return visitor.visitChildren(this);
    }
  }
}