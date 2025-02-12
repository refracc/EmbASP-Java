package it.unical.mat.parsers.asp.clingo;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ClingoLexer extends Lexer {
  public static final int
      START = 1, ANY = 2, COMMA = 3, INTEGER_CONSTANT = 4, NEW_LINE = 5, IDENTIFIER = 6,
      STRING_CONSTANT = 7, TERMS_BEGIN = 8, TERMS_END = 9, WHITE_SPACE = 10;
  public static final int
      SIGNIFICANT = 1;
  public static final String[] ruleNames = {
      "START", "ANY", "COMMA", "INTEGER_CONSTANT", "NEW_LINE", "IDENTIFIER",
      "STRING_CONSTANT", "TERMS_BEGIN", "TERMS_END", "WHITE_SPACE", "INT", "NL",
      "WS"
  };
  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  public static final String _serializedATN =
      "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\f\177\b\1\b\1\4\2" +
          "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4" +
          "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2" +
          "\3\2\3\2\3\2\3\2\3\2\3\3\7\3.\n\3\f\3\16\3\61\13\3\3\3\3\3\3\3\3\3\3\4" +
          "\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3" +
          "\6\3\6\3\6\3\6\7\6M\n\6\f\6\16\6P\13\6\3\6\5\6S\n\6\3\6\3\6\3\7\3\7\7" +
          "\7Y\n\7\f\7\16\7\\\13\7\3\b\3\b\7\b`\n\b\f\b\16\bc\13\b\3\b\3\b\3\t\3" +
          "\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\7\fr\n\f\f\f\16\fu\13\f\5\f" +
          "w\n\f\3\r\3\r\3\r\5\r|\n\r\3\16\3\16\3/\2\17\4\3\6\4\b\5\n\6\f\7\16\b" +
          "\20\t\22\n\24\13\26\f\30\2\32\2\34\2\4\2\3\t\4\2C\\c|\6\2\62;C\\aac|\3" +
          "\2$$\3\2\63;\3\2\62;\4\2\f\f\17\17\4\2\13\13\"\"\2\u0082\2\4\3\2\2\2\2" +
          "\6\3\2\2\2\3\b\3\2\2\2\3\n\3\2\2\2\3\f\3\2\2\2\3\16\3\2\2\2\3\20\3\2\2" +
          "\2\3\22\3\2\2\2\3\24\3\2\2\2\3\26\3\2\2\2\4\36\3\2\2\2\6/\3\2\2\2\b\66" +
          "\3\2\2\2\n8\3\2\2\2\f:\3\2\2\2\16V\3\2\2\2\20]\3\2\2\2\22f\3\2\2\2\24" +
          "h\3\2\2\2\26j\3\2\2\2\30v\3\2\2\2\32{\3\2\2\2\34}\3\2\2\2\36\37\7C\2\2" +
          "\37 \7p\2\2 !\7u\2\2!\"\7y\2\2\"#\7g\2\2#$\7t\2\2$%\7<\2\2%&\7\"\2\2&" +
          "\'\3\2\2\2\'(\5\30\f\2()\5\32\r\2)*\3\2\2\2*+\b\2\2\2+\5\3\2\2\2,.\13" +
          "\2\2\2-,\3\2\2\2.\61\3\2\2\2/\60\3\2\2\2/-\3\2\2\2\60\62\3\2\2\2\61/\3" +
          "\2\2\2\62\63\5\32\r\2\63\64\3\2\2\2\64\65\b\3\3\2\65\7\3\2\2\2\66\67\7" +
          ".\2\2\67\t\3\2\2\289\5\30\f\29\13\3\2\2\2:R\5\32\r\2;<\7Q\2\2<=\7r\2\2" +
          "=>\7v\2\2>?\7k\2\2?@\7o\2\2@A\7k\2\2AB\7|\2\2BC\7c\2\2CD\7v\2\2DE\7k\2" +
          "\2EF\7q\2\2FG\7p\2\2GH\7<\2\2HN\3\2\2\2IJ\5\34\16\2JK\5\30\f\2KM\3\2\2" +
          "\2LI\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QS\5\32" +
          "\r\2R;\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\b\6\4\2U\r\3\2\2\2VZ\t\2\2\2WY\t" +
          "\3\2\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\17\3\2\2\2\\Z\3\2\2\2" +
          "]a\7$\2\2^`\n\4\2\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2" +
          "ca\3\2\2\2de\7$\2\2e\21\3\2\2\2fg\7*\2\2g\23\3\2\2\2hi\7+\2\2i\25\3\2" +
          "\2\2jk\5\34\16\2kl\3\2\2\2lm\b\13\3\2m\27\3\2\2\2nw\7\62\2\2os\t\5\2\2" +
          "pr\t\6\2\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tw\3\2\2\2us\3\2\2\2" +
          "vn\3\2\2\2vo\3\2\2\2w\31\3\2\2\2x|\t\7\2\2yz\7\17\2\2z|\7\f\2\2{x\3\2" +
          "\2\2{y\3\2\2\2|\33\3\2\2\2}~\t\b\2\2~\35\3\2\2\2\f\2\3/NRZasv{\5\4\3\2" +
          "\b\2\2\4\2\2";
  public static final ATN _ATN =
      new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
      new PredictionContextCache();
  private static final String[] _LITERAL_NAMES = {
      null, null, null, "','", null, null, null, null, "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
      null, "START", "ANY", "COMMA", "INTEGER_CONSTANT", "NEW_LINE", "IDENTIFIER",
      "STRING_CONSTANT", "TERMS_BEGIN", "TERMS_END", "WHITE_SPACE"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
  public static String[] channelNames = {
      "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
  };
  public static String[] modeNames = {
      "DEFAULT_MODE", "SIGNIFICANT"
  };

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
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }

  public ClingoLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
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
    return "ClingoLexer.g4";
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