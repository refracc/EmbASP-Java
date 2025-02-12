package it.unical.mat.parsers.datalog.idlv;// Generated from .\IDLVLexer.g4 by ANTLR 4.8

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IDLVLexer extends Lexer {
  public static final int
      IGNORE = 1, COMMA = 2, INTEGER_CONSTANT = 3, ATOM_END = 4, IDENTIFIER = 5, STRING_CONSTANT = 6,
      TERMS_BEGIN = 7, TERMS_END = 8;
  public static final String[] ruleNames = makeRuleNames();
  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  public static final String _serializedATN =
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\nG\b\1\4\2\t\2\4" +
          "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
          "\13\4\f\t\f\3\2\3\2\5\2\34\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6" +
          "\7\6(\n\6\f\6\16\6+\13\6\3\7\3\7\7\7/\n\7\f\7\16\7\62\13\7\3\7\3\7\3\b" +
          "\3\b\3\t\3\t\3\n\3\n\3\n\7\n=\n\n\f\n\16\n@\13\n\5\nB\n\n\3\13\3\13\3" +
          "\f\3\f\2\2\r\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\2\25\2\27\2\3\2\t\4" +
          "\2C\\c|\6\2\62;C\\aac|\3\2$$\3\2\63;\3\2\62;\4\2\f\f\17\17\4\2\13\13\"" +
          "\"\2H\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r" +
          "\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\3\33\3\2\2\2\5\37\3\2\2\2\7!\3\2\2" +
          "\2\t#\3\2\2\2\13%\3\2\2\2\r,\3\2\2\2\17\65\3\2\2\2\21\67\3\2\2\2\23A\3" +
          "\2\2\2\25C\3\2\2\2\27E\3\2\2\2\31\34\5\25\13\2\32\34\5\27\f\2\33\31\3" +
          "\2\2\2\33\32\3\2\2\2\34\35\3\2\2\2\35\36\b\2\2\2\36\4\3\2\2\2\37 \7.\2" +
          "\2 \6\3\2\2\2!\"\5\23\n\2\"\b\3\2\2\2#$\7\60\2\2$\n\3\2\2\2%)\t\2\2\2" +
          "&(\t\3\2\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\f\3\2\2\2+)\3\2" +
          "\2\2,\60\7$\2\2-/\n\4\2\2.-\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2" +
          "\2\2\61\63\3\2\2\2\62\60\3\2\2\2\63\64\7$\2\2\64\16\3\2\2\2\65\66\7*\2" +
          "\2\66\20\3\2\2\2\678\7+\2\28\22\3\2\2\29B\7\62\2\2:>\t\5\2\2;=\t\6\2\2" +
          "<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?B\3\2\2\2@>\3\2\2\2A9\3\2\2\2" +
          "A:\3\2\2\2B\24\3\2\2\2CD\t\7\2\2D\26\3\2\2\2EF\t\b\2\2F\30\3\2\2\2\b\2" +
          "\33)\60>A\3\b\2\2";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
      new PredictionContextCache();
  private static final String[] _LITERAL_NAMES = makeLiteralNames();
  private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
  public static String[] channelNames = {
      "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
  };
  public static String[] modeNames = {
      "DEFAULT_MODE"
  };

  static {
    RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION);
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
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }

  public IDLVLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  @Contract(value = " -> new", pure = true)
  private static String @NotNull [] makeRuleNames() {
    return new String[]{
        "IGNORE", "COMMA", "INTEGER_CONSTANT", "ATOM_END", "IDENTIFIER", "STRING_CONSTANT",
        "TERMS_BEGIN", "TERMS_END", "INT", "NL", "WS"
    };
  }

  @Contract(value = " -> new", pure = true)
  private static String @NotNull [] makeLiteralNames() {
    return new String[]{
        null, null, "','", null, "'.'", null, null, "'('", "')'"
    };
  }

  @Contract(value = " -> new", pure = true)
  private static String @NotNull [] makeSymbolicNames() {
    return new String[]{
        null, "IGNORE", "COMMA", "INTEGER_CONSTANT", "ATOM_END", "IDENTIFIER",
        "STRING_CONSTANT", "TERMS_BEGIN", "TERMS_END"
    };
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
    return "IDLVLexer.g4";
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public String[] getChannelNames() {
    return channelNames;
  }

  @Override
  public String[] getModeNames() {
    return modeNames;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }
}