// Generated from LuaParser.g4 by ANTLR 4.13.1
package org.orient;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LuaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMI=1, EQ=2, BREAK=3, GOTO=4, DO=5, END=6, WHILE=7, REPEAT=8, UNTIL=9, 
		IF=10, THEN=11, ELSEIF=12, ELSE=13, FOR=14, COMMA=15, IN=16, FUNCTION=17, 
		LOCAL=18, LT=19, GT=20, RETURN=21, CONTINUE=22, CC=23, NIL=24, FALSE=25, 
		TRUE=26, DOT=27, SQUIG=28, MINUS=29, POUND=30, OP=31, CP=32, NOT=33, LL=34, 
		GG=35, AMP=36, SS=37, PER=38, COL=39, LE=40, GE=41, AND=42, OR=43, PLUS=44, 
		STAR=45, OCU=46, CCU=47, OB=48, CB=49, EE=50, DD=51, PIPE=52, CARET=53, 
		SLASH=54, DDD=55, SQEQ=56, NAME=57, NORMALSTRING=58, CHARSTRING=59, LONGSTRING=60, 
		INT=61, HEX=62, FLOAT=63, HEX_FLOAT=64, COMMENT=65, WS=66, NL=67, SHEBANG=68;
	public static final int
		RULE_start_ = 0, RULE_chunk = 1, RULE_block = 2, RULE_stat = 3, RULE_attnamelist = 4, 
		RULE_attrib = 5, RULE_retstat = 6, RULE_label = 7, RULE_funcname = 8, 
		RULE_varlist = 9, RULE_namelist = 10, RULE_explist = 11, RULE_exp = 12, 
		RULE_var = 13, RULE_prefixexp = 14, RULE_functioncall = 15, RULE_args = 16, 
		RULE_functiondef = 17, RULE_funcbody = 18, RULE_parlist = 19, RULE_tableconstructor = 20, 
		RULE_fieldlist = 21, RULE_field = 22, RULE_fieldsep = 23, RULE_number = 24, 
		RULE_string = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"start_", "chunk", "block", "stat", "attnamelist", "attrib", "retstat", 
			"label", "funcname", "varlist", "namelist", "explist", "exp", "var", 
			"prefixexp", "functioncall", "args", "functiondef", "funcbody", "parlist", 
			"tableconstructor", "fieldlist", "field", "fieldsep", "number", "string"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'break'", "'goto'", "'do'", "'end'", "'while'", 
			"'repeat'", "'until'", "'if'", "'then'", "'elseif'", "'else'", "'for'", 
			"','", "'in'", "'function'", "'local'", "'<'", "'>'", "'return'", "'continue'", 
			"'::'", "'nil'", "'false'", "'true'", "'.'", "'~'", "'-'", "'#'", "'('", 
			"')'", "'not'", "'<<'", "'>>'", "'&'", "'//'", "'%'", "':'", "'<='", 
			"'>='", "'and'", "'or'", "'+'", "'*'", "'{'", "'}'", "'['", "']'", "'=='", 
			"'..'", "'|'", "'^'", "'/'", "'...'", "'~='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEMI", "EQ", "BREAK", "GOTO", "DO", "END", "WHILE", "REPEAT", 
			"UNTIL", "IF", "THEN", "ELSEIF", "ELSE", "FOR", "COMMA", "IN", "FUNCTION", 
			"LOCAL", "LT", "GT", "RETURN", "CONTINUE", "CC", "NIL", "FALSE", "TRUE", 
			"DOT", "SQUIG", "MINUS", "POUND", "OP", "CP", "NOT", "LL", "GG", "AMP", 
			"SS", "PER", "COL", "LE", "GE", "AND", "OR", "PLUS", "STAR", "OCU", "CCU", 
			"OB", "CB", "EE", "DD", "PIPE", "CARET", "SLASH", "DDD", "SQEQ", "NAME", 
			"NORMALSTRING", "CHARSTRING", "LONGSTRING", "INT", "HEX", "FLOAT", "HEX_FLOAT", 
			"COMMENT", "WS", "NL", "SHEBANG"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
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
	public String getGrammarFileName() { return "LuaParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LuaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Start_Context extends ParserRuleContext {
		public ChunkContext chunk() {
			return getRuleContext(ChunkContext.class,0);
		}
		public TerminalNode EOF() { return getToken(LuaParser.EOF, 0); }
		public Start_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterStart_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitStart_(this);
		}
	}

	public final Start_Context start_() throws RecognitionException {
		Start_Context _localctx = new Start_Context(_ctx, getState());
		enterRule(_localctx, 0, RULE_start_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			chunk();
			setState(53);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ChunkContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ChunkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chunk; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterChunk(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitChunk(this);
		}
	}

	public final ChunkContext chunk() throws RecognitionException {
		ChunkContext _localctx = new ChunkContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_chunk);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public RetstatContext retstat() {
			return getRuleContext(RetstatContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(57);
					stat();
					}
					} 
				}
				setState(62);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6291464L) != 0)) {
				{
				setState(63);
				retstat();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(LuaParser.SEMI, 0); }
		public VarlistContext varlist() {
			return getRuleContext(VarlistContext.class,0);
		}
		public TerminalNode EQ() { return getToken(LuaParser.EQ, 0); }
		public ExplistContext explist() {
			return getRuleContext(ExplistContext.class,0);
		}
		public FunctioncallContext functioncall() {
			return getRuleContext(FunctioncallContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode BREAK() { return getToken(LuaParser.BREAK, 0); }
		public TerminalNode GOTO() { return getToken(LuaParser.GOTO, 0); }
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public TerminalNode DO() { return getToken(LuaParser.DO, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode END() { return getToken(LuaParser.END, 0); }
		public TerminalNode WHILE() { return getToken(LuaParser.WHILE, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode REPEAT() { return getToken(LuaParser.REPEAT, 0); }
		public TerminalNode UNTIL() { return getToken(LuaParser.UNTIL, 0); }
		public TerminalNode IF() { return getToken(LuaParser.IF, 0); }
		public List<TerminalNode> THEN() { return getTokens(LuaParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(LuaParser.THEN, i);
		}
		public List<TerminalNode> ELSEIF() { return getTokens(LuaParser.ELSEIF); }
		public TerminalNode ELSEIF(int i) {
			return getToken(LuaParser.ELSEIF, i);
		}
		public TerminalNode ELSE() { return getToken(LuaParser.ELSE, 0); }
		public TerminalNode FOR() { return getToken(LuaParser.FOR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public NamelistContext namelist() {
			return getRuleContext(NamelistContext.class,0);
		}
		public TerminalNode IN() { return getToken(LuaParser.IN, 0); }
		public TerminalNode FUNCTION() { return getToken(LuaParser.FUNCTION, 0); }
		public FuncnameContext funcname() {
			return getRuleContext(FuncnameContext.class,0);
		}
		public FuncbodyContext funcbody() {
			return getRuleContext(FuncbodyContext.class,0);
		}
		public TerminalNode LOCAL() { return getToken(LuaParser.LOCAL, 0); }
		public AttnamelistContext attnamelist() {
			return getRuleContext(AttnamelistContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitStat(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stat);
		int _la;
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				varlist();
				setState(68);
				match(EQ);
				setState(69);
				explist();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				functioncall(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
				label();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				match(BREAK);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(74);
				match(GOTO);
				setState(75);
				match(NAME);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(76);
				match(DO);
				setState(77);
				block();
				setState(78);
				match(END);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(80);
				match(WHILE);
				setState(81);
				exp(0);
				setState(82);
				match(DO);
				setState(83);
				block();
				setState(84);
				match(END);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(86);
				match(REPEAT);
				setState(87);
				block();
				setState(88);
				match(UNTIL);
				setState(89);
				exp(0);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(91);
				match(IF);
				setState(92);
				exp(0);
				setState(93);
				match(THEN);
				setState(94);
				block();
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ELSEIF) {
					{
					{
					setState(95);
					match(ELSEIF);
					setState(96);
					exp(0);
					setState(97);
					match(THEN);
					setState(98);
					block();
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(105);
					match(ELSE);
					setState(106);
					block();
					}
				}

				setState(109);
				match(END);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(111);
				match(FOR);
				setState(112);
				match(NAME);
				setState(113);
				match(EQ);
				setState(114);
				exp(0);
				setState(115);
				match(COMMA);
				setState(116);
				exp(0);
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(117);
					match(COMMA);
					setState(118);
					exp(0);
					}
				}

				setState(121);
				match(DO);
				setState(122);
				block();
				setState(123);
				match(END);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(125);
				match(FOR);
				setState(126);
				namelist();
				setState(127);
				match(IN);
				setState(128);
				explist();
				setState(129);
				match(DO);
				setState(130);
				block();
				setState(131);
				match(END);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(133);
				match(FUNCTION);
				setState(134);
				funcname();
				setState(135);
				funcbody();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(137);
				match(LOCAL);
				setState(138);
				match(FUNCTION);
				setState(139);
				match(NAME);
				setState(140);
				funcbody();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(141);
				match(LOCAL);
				setState(142);
				attnamelist();
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(143);
					match(EQ);
					setState(144);
					explist();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttnamelistContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public List<AttribContext> attrib() {
			return getRuleContexts(AttribContext.class);
		}
		public AttribContext attrib(int i) {
			return getRuleContext(AttribContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public AttnamelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attnamelist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterAttnamelist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitAttnamelist(this);
		}
	}

	public final AttnamelistContext attnamelist() throws RecognitionException {
		AttnamelistContext _localctx = new AttnamelistContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attnamelist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(NAME);
			setState(150);
			attrib();
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(151);
				match(COMMA);
				setState(152);
				match(NAME);
				setState(153);
				attrib();
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttribContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(LuaParser.LT, 0); }
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public TerminalNode GT() { return getToken(LuaParser.GT, 0); }
		public AttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitAttrib(this);
		}
	}

	public final AttribContext attrib() throws RecognitionException {
		AttribContext _localctx = new AttribContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_attrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(159);
				match(LT);
				setState(160);
				match(NAME);
				setState(161);
				match(GT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RetstatContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(LuaParser.RETURN, 0); }
		public TerminalNode BREAK() { return getToken(LuaParser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(LuaParser.CONTINUE, 0); }
		public TerminalNode SEMI() { return getToken(LuaParser.SEMI, 0); }
		public ExplistContext explist() {
			return getRuleContext(ExplistContext.class,0);
		}
		public RetstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_retstat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterRetstat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitRetstat(this);
		}
	}

	public final RetstatContext retstat() throws RecognitionException {
		RetstatContext _localctx = new RetstatContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_retstat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
				{
				setState(164);
				match(RETURN);
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 17)) & ~0x3f) == 0 && ((1L << (_la - 17)) & 280650879957889L) != 0)) {
					{
					setState(165);
					explist();
					}
				}

				}
				break;
			case BREAK:
				{
				setState(168);
				match(BREAK);
				}
				break;
			case CONTINUE:
				{
				setState(169);
				match(CONTINUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(172);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelContext extends ParserRuleContext {
		public List<TerminalNode> CC() { return getTokens(LuaParser.CC); }
		public TerminalNode CC(int i) {
			return getToken(LuaParser.CC, i);
		}
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(CC);
			setState(176);
			match(NAME);
			setState(177);
			match(CC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncnameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public List<TerminalNode> DOT() { return getTokens(LuaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(LuaParser.DOT, i);
		}
		public TerminalNode COL() { return getToken(LuaParser.COL, 0); }
		public FuncnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFuncname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFuncname(this);
		}
	}

	public final FuncnameContext funcname() throws RecognitionException {
		FuncnameContext _localctx = new FuncnameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_funcname);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(NAME);
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(180);
				match(DOT);
				setState(181);
				match(NAME);
				}
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COL) {
				{
				setState(187);
				match(COL);
				setState(188);
				match(NAME);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarlistContext extends ParserRuleContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public VarlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterVarlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitVarlist(this);
		}
	}

	public final VarlistContext varlist() throws RecognitionException {
		VarlistContext _localctx = new VarlistContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			var();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(192);
				match(COMMA);
				setState(193);
				var();
				}
				}
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NamelistContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public NamelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namelist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterNamelist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitNamelist(this);
		}
	}

	public final NamelistContext namelist() throws RecognitionException {
		NamelistContext _localctx = new NamelistContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_namelist);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(NAME);
			setState(204);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(200);
					match(COMMA);
					setState(201);
					match(NAME);
					}
					} 
				}
				setState(206);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExplistContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public ExplistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterExplist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitExplist(this);
		}
	}

	public final ExplistContext explist() throws RecognitionException {
		ExplistContext _localctx = new ExplistContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_explist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			exp(0);
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(208);
				match(COMMA);
				setState(209);
				exp(0);
				}
				}
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpContext extends ParserRuleContext {
		public TerminalNode NIL() { return getToken(LuaParser.NIL, 0); }
		public TerminalNode FALSE() { return getToken(LuaParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(LuaParser.TRUE, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode DDD() { return getToken(LuaParser.DDD, 0); }
		public FunctiondefContext functiondef() {
			return getRuleContext(FunctiondefContext.class,0);
		}
		public PrefixexpContext prefixexp() {
			return getRuleContext(PrefixexpContext.class,0);
		}
		public TableconstructorContext tableconstructor() {
			return getRuleContext(TableconstructorContext.class,0);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode NOT() { return getToken(LuaParser.NOT, 0); }
		public TerminalNode POUND() { return getToken(LuaParser.POUND, 0); }
		public TerminalNode MINUS() { return getToken(LuaParser.MINUS, 0); }
		public TerminalNode SQUIG() { return getToken(LuaParser.SQUIG, 0); }
		public TerminalNode CARET() { return getToken(LuaParser.CARET, 0); }
		public TerminalNode STAR() { return getToken(LuaParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(LuaParser.SLASH, 0); }
		public TerminalNode PER() { return getToken(LuaParser.PER, 0); }
		public TerminalNode SS() { return getToken(LuaParser.SS, 0); }
		public TerminalNode PLUS() { return getToken(LuaParser.PLUS, 0); }
		public TerminalNode DD() { return getToken(LuaParser.DD, 0); }
		public TerminalNode LT() { return getToken(LuaParser.LT, 0); }
		public TerminalNode GT() { return getToken(LuaParser.GT, 0); }
		public TerminalNode LE() { return getToken(LuaParser.LE, 0); }
		public TerminalNode GE() { return getToken(LuaParser.GE, 0); }
		public TerminalNode SQEQ() { return getToken(LuaParser.SQEQ, 0); }
		public TerminalNode EE() { return getToken(LuaParser.EE, 0); }
		public TerminalNode AND() { return getToken(LuaParser.AND, 0); }
		public TerminalNode OR() { return getToken(LuaParser.OR, 0); }
		public TerminalNode AMP() { return getToken(LuaParser.AMP, 0); }
		public TerminalNode PIPE() { return getToken(LuaParser.PIPE, 0); }
		public TerminalNode LL() { return getToken(LuaParser.LL, 0); }
		public TerminalNode GG() { return getToken(LuaParser.GG, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NIL:
				{
				setState(216);
				match(NIL);
				}
				break;
			case FALSE:
				{
				setState(217);
				match(FALSE);
				}
				break;
			case TRUE:
				{
				setState(218);
				match(TRUE);
				}
				break;
			case INT:
			case HEX:
			case FLOAT:
			case HEX_FLOAT:
				{
				setState(219);
				number();
				}
				break;
			case NORMALSTRING:
			case CHARSTRING:
			case LONGSTRING:
				{
				setState(220);
				string();
				}
				break;
			case DDD:
				{
				setState(221);
				match(DDD);
				}
				break;
			case FUNCTION:
				{
				setState(222);
				functiondef();
				}
				break;
			case OP:
			case NAME:
				{
				setState(223);
				prefixexp();
				}
				break;
			case OCU:
				{
				setState(224);
				tableconstructor();
				}
				break;
			case SQUIG:
			case MINUS:
			case POUND:
			case NOT:
				{
				setState(225);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 10468982784L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(226);
				exp(8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(253);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(229);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						{
						setState(230);
						match(CARET);
						}
						setState(231);
						exp(9);
						}
						break;
					case 2:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(232);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(233);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 18049995198431232L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(234);
						exp(8);
						}
						break;
					case 3:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(235);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(236);
						_la = _input.LA(1);
						if ( !(_la==MINUS || _la==PLUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(237);
						exp(7);
						}
						break;
					case 4:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(238);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						{
						setState(239);
						match(DD);
						}
						setState(240);
						exp(5);
						}
						break;
					case 5:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(241);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(242);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 73186792481226752L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(243);
						exp(5);
						}
						break;
					case 6:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(244);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						{
						setState(245);
						match(AND);
						}
						setState(246);
						exp(4);
						}
						break;
					case 7:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(247);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						{
						setState(248);
						match(OR);
						}
						setState(249);
						exp(3);
						}
						break;
					case 8:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(250);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(251);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4503720154890240L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(252);
						exp(2);
						}
						break;
					}
					} 
				}
				setState(257);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public PrefixexpContext prefixexp() {
			return getRuleContext(PrefixexpContext.class,0);
		}
		public TerminalNode OB() { return getToken(LuaParser.OB, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CB() { return getToken(LuaParser.CB, 0); }
		public TerminalNode DOT() { return getToken(LuaParser.DOT, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitVar(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_var);
		try {
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				match(NAME);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				prefixexp();
				setState(266);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OB:
					{
					setState(260);
					match(OB);
					setState(261);
					exp(0);
					setState(262);
					match(CB);
					}
					break;
				case DOT:
					{
					setState(264);
					match(DOT);
					setState(265);
					match(NAME);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrefixexpContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public List<TerminalNode> OB() { return getTokens(LuaParser.OB); }
		public TerminalNode OB(int i) {
			return getToken(LuaParser.OB, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> CB() { return getTokens(LuaParser.CB); }
		public TerminalNode CB(int i) {
			return getToken(LuaParser.CB, i);
		}
		public List<TerminalNode> DOT() { return getTokens(LuaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(LuaParser.DOT, i);
		}
		public FunctioncallContext functioncall() {
			return getRuleContext(FunctioncallContext.class,0);
		}
		public TerminalNode OP() { return getToken(LuaParser.OP, 0); }
		public TerminalNode CP() { return getToken(LuaParser.CP, 0); }
		public PrefixexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterPrefixexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitPrefixexp(this);
		}
	}

	public final PrefixexpContext prefixexp() throws RecognitionException {
		PrefixexpContext _localctx = new PrefixexpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_prefixexp);
		try {
			int _alt;
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				match(NAME);
				setState(279);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(277);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case OB:
							{
							setState(271);
							match(OB);
							setState(272);
							exp(0);
							setState(273);
							match(CB);
							}
							break;
						case DOT:
							{
							setState(275);
							match(DOT);
							setState(276);
							match(NAME);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(281);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
				functioncall(0);
				setState(291);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(289);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case OB:
							{
							setState(283);
							match(OB);
							setState(284);
							exp(0);
							setState(285);
							match(CB);
							}
							break;
						case DOT:
							{
							setState(287);
							match(DOT);
							setState(288);
							match(NAME);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(293);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(294);
				match(OP);
				setState(295);
				exp(0);
				setState(296);
				match(CP);
				setState(305);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(303);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case OB:
							{
							setState(297);
							match(OB);
							setState(298);
							exp(0);
							setState(299);
							match(CB);
							}
							break;
						case DOT:
							{
							setState(301);
							match(DOT);
							setState(302);
							match(NAME);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(307);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctioncallContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public List<TerminalNode> OB() { return getTokens(LuaParser.OB); }
		public TerminalNode OB(int i) {
			return getToken(LuaParser.OB, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> CB() { return getTokens(LuaParser.CB); }
		public TerminalNode CB(int i) {
			return getToken(LuaParser.CB, i);
		}
		public List<TerminalNode> DOT() { return getTokens(LuaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(LuaParser.DOT, i);
		}
		public TerminalNode OP() { return getToken(LuaParser.OP, 0); }
		public TerminalNode CP() { return getToken(LuaParser.CP, 0); }
		public TerminalNode COL() { return getToken(LuaParser.COL, 0); }
		public FunctioncallContext functioncall() {
			return getRuleContext(FunctioncallContext.class,0);
		}
		public FunctioncallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functioncall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFunctioncall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFunctioncall(this);
		}
	}

	public final FunctioncallContext functioncall() throws RecognitionException {
		return functioncall(0);
	}

	private FunctioncallContext functioncall(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FunctioncallContext _localctx = new FunctioncallContext(_ctx, _parentState);
		FunctioncallContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_functioncall, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(311);
				match(NAME);
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(318);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(312);
						match(OB);
						setState(313);
						exp(0);
						setState(314);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(316);
						match(DOT);
						setState(317);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(322);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(323);
				args();
				}
				break;
			case 2:
				{
				setState(324);
				match(OP);
				setState(325);
				exp(0);
				setState(326);
				match(CP);
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(333);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(327);
						match(OB);
						setState(328);
						exp(0);
						setState(329);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(331);
						match(DOT);
						setState(332);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(337);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(338);
				args();
				}
				break;
			case 3:
				{
				setState(340);
				match(NAME);
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(347);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(341);
						match(OB);
						setState(342);
						exp(0);
						setState(343);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(345);
						match(DOT);
						setState(346);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(351);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(352);
				match(COL);
				setState(353);
				match(NAME);
				setState(354);
				args();
				}
				break;
			case 4:
				{
				setState(355);
				match(OP);
				setState(356);
				exp(0);
				setState(357);
				match(CP);
				setState(366);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(364);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(358);
						match(OB);
						setState(359);
						exp(0);
						setState(360);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(362);
						match(DOT);
						setState(363);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(368);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(369);
				match(COL);
				setState(370);
				match(NAME);
				setState(371);
				args();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(405);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(403);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
					case 1:
						{
						_localctx = new FunctioncallContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_functioncall);
						setState(375);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(384);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==DOT || _la==OB) {
							{
							setState(382);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case OB:
								{
								setState(376);
								match(OB);
								setState(377);
								exp(0);
								setState(378);
								match(CB);
								}
								break;
							case DOT:
								{
								setState(380);
								match(DOT);
								setState(381);
								match(NAME);
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							setState(386);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(387);
						args();
						}
						break;
					case 2:
						{
						_localctx = new FunctioncallContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_functioncall);
						setState(388);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(397);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==DOT || _la==OB) {
							{
							setState(395);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case OB:
								{
								setState(389);
								match(OB);
								setState(390);
								exp(0);
								setState(391);
								match(CB);
								}
								break;
							case DOT:
								{
								setState(393);
								match(DOT);
								setState(394);
								match(NAME);
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							setState(399);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(400);
						match(COL);
						setState(401);
						match(NAME);
						setState(402);
						args();
						}
						break;
					}
					} 
				}
				setState(407);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public TerminalNode OP() { return getToken(LuaParser.OP, 0); }
		public TerminalNode CP() { return getToken(LuaParser.CP, 0); }
		public ExplistContext explist() {
			return getRuleContext(ExplistContext.class,0);
		}
		public TableconstructorContext tableconstructor() {
			return getRuleContext(TableconstructorContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_args);
		int _la;
		try {
			setState(415);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP:
				enterOuterAlt(_localctx, 1);
				{
				setState(408);
				match(OP);
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 17)) & ~0x3f) == 0 && ((1L << (_la - 17)) & 280650879957889L) != 0)) {
					{
					setState(409);
					explist();
					}
				}

				setState(412);
				match(CP);
				}
				break;
			case OCU:
				enterOuterAlt(_localctx, 2);
				{
				setState(413);
				tableconstructor();
				}
				break;
			case NORMALSTRING:
			case CHARSTRING:
			case LONGSTRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(414);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctiondefContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(LuaParser.FUNCTION, 0); }
		public FuncbodyContext funcbody() {
			return getRuleContext(FuncbodyContext.class,0);
		}
		public FunctiondefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functiondef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFunctiondef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFunctiondef(this);
		}
	}

	public final FunctiondefContext functiondef() throws RecognitionException {
		FunctiondefContext _localctx = new FunctiondefContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_functiondef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			match(FUNCTION);
			setState(418);
			funcbody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncbodyContext extends ParserRuleContext {
		public TerminalNode OP() { return getToken(LuaParser.OP, 0); }
		public ParlistContext parlist() {
			return getRuleContext(ParlistContext.class,0);
		}
		public TerminalNode CP() { return getToken(LuaParser.CP, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(LuaParser.END, 0); }
		public FuncbodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcbody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFuncbody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFuncbody(this);
		}
	}

	public final FuncbodyContext funcbody() throws RecognitionException {
		FuncbodyContext _localctx = new FuncbodyContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_funcbody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(OP);
			setState(421);
			parlist();
			setState(422);
			match(CP);
			setState(423);
			block();
			setState(424);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParlistContext extends ParserRuleContext {
		public NamelistContext namelist() {
			return getRuleContext(NamelistContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(LuaParser.COMMA, 0); }
		public TerminalNode DDD() { return getToken(LuaParser.DDD, 0); }
		public ParlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterParlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitParlist(this);
		}
	}

	public final ParlistContext parlist() throws RecognitionException {
		ParlistContext _localctx = new ParlistContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_parlist);
		int _la;
		try {
			setState(433);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(426);
				namelist();
				setState(429);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(427);
					match(COMMA);
					setState(428);
					match(DDD);
					}
				}

				}
				break;
			case DDD:
				enterOuterAlt(_localctx, 2);
				{
				setState(431);
				match(DDD);
				}
				break;
			case CP:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableconstructorContext extends ParserRuleContext {
		public TerminalNode OCU() { return getToken(LuaParser.OCU, 0); }
		public TerminalNode CCU() { return getToken(LuaParser.CCU, 0); }
		public FieldlistContext fieldlist() {
			return getRuleContext(FieldlistContext.class,0);
		}
		public TableconstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableconstructor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterTableconstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitTableconstructor(this);
		}
	}

	public final TableconstructorContext tableconstructor() throws RecognitionException {
		TableconstructorContext _localctx = new TableconstructorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_tableconstructor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(OCU);
			setState(437);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 17)) & ~0x3f) == 0 && ((1L << (_la - 17)) & 280653027441537L) != 0)) {
				{
				setState(436);
				fieldlist();
				}
			}

			setState(439);
			match(CCU);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldlistContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<FieldsepContext> fieldsep() {
			return getRuleContexts(FieldsepContext.class);
		}
		public FieldsepContext fieldsep(int i) {
			return getRuleContext(FieldsepContext.class,i);
		}
		public FieldlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFieldlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFieldlist(this);
		}
	}

	public final FieldlistContext fieldlist() throws RecognitionException {
		FieldlistContext _localctx = new FieldlistContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_fieldlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			field();
			setState(447);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(442);
					fieldsep();
					setState(443);
					field();
					}
					} 
				}
				setState(449);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			}
			setState(451);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI || _la==COMMA) {
				{
				setState(450);
				fieldsep();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldContext extends ParserRuleContext {
		public TerminalNode OB() { return getToken(LuaParser.OB, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode CB() { return getToken(LuaParser.CB, 0); }
		public TerminalNode EQ() { return getToken(LuaParser.EQ, 0); }
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_field);
		try {
			setState(463);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(453);
				match(OB);
				setState(454);
				exp(0);
				setState(455);
				match(CB);
				setState(456);
				match(EQ);
				setState(457);
				exp(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(459);
				match(NAME);
				setState(460);
				match(EQ);
				setState(461);
				exp(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(462);
				exp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldsepContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(LuaParser.COMMA, 0); }
		public TerminalNode SEMI() { return getToken(LuaParser.SEMI, 0); }
		public FieldsepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldsep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFieldsep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFieldsep(this);
		}
	}

	public final FieldsepContext fieldsep() throws RecognitionException {
		FieldsepContext _localctx = new FieldsepContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_fieldsep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			_la = _input.LA(1);
			if ( !(_la==SEMI || _la==COMMA) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(LuaParser.INT, 0); }
		public TerminalNode HEX() { return getToken(LuaParser.HEX, 0); }
		public TerminalNode FLOAT() { return getToken(LuaParser.FLOAT, 0); }
		public TerminalNode HEX_FLOAT() { return getToken(LuaParser.HEX_FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			_la = _input.LA(1);
			if ( !(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public TerminalNode NORMALSTRING() { return getToken(LuaParser.NORMALSTRING, 0); }
		public TerminalNode CHARSTRING() { return getToken(LuaParser.CHARSTRING, 0); }
		public TerminalNode LONGSTRING() { return getToken(LuaParser.LONGSTRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2017612633061982208L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 15:
			return functioncall_sempred((FunctioncallContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean functioncall_sempred(FunctioncallContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 5);
		case 9:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001D\u01d8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0005\u0002;\b\u0002\n\u0002\f\u0002>\t\u0002"+
		"\u0001\u0002\u0003\u0002A\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003e\b\u0003\n\u0003\f\u0003h\t\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003l\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003x\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0092\b\u0003\u0003\u0003"+
		"\u0094\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u0004\u009b\b\u0004\n\u0004\f\u0004\u009e\t\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u00a3\b\u0005\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u00a7\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00ab\b\u0006"+
		"\u0001\u0006\u0003\u0006\u00ae\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u00b7\b\b\n\b\f\b\u00ba\t"+
		"\b\u0001\b\u0001\b\u0003\b\u00be\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u00c3"+
		"\b\t\n\t\f\t\u00c6\t\t\u0001\n\u0001\n\u0001\n\u0005\n\u00cb\b\n\n\n\f"+
		"\n\u00ce\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00d3\b\u000b"+
		"\n\u000b\f\u000b\u00d6\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00e4"+
		"\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00fe\b\f\n"+
		"\f\f\f\u0101\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u010b\b\r\u0003\r\u010d\b\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e"+
		"\u0116\b\u000e\n\u000e\f\u000e\u0119\t\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0122"+
		"\b\u000e\n\u000e\f\u000e\u0125\t\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0005\u000e\u0130\b\u000e\n\u000e\f\u000e\u0133\t\u000e\u0003\u000e\u0135"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u013f\b\u000f\n\u000f\f\u000f"+
		"\u0142\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f"+
		"\u014e\b\u000f\n\u000f\f\u000f\u0151\t\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0005\u000f\u015c\b\u000f\n\u000f\f\u000f\u015f\t\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f"+
		"\u016d\b\u000f\n\u000f\f\u000f\u0170\t\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u0176\b\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u017f"+
		"\b\u000f\n\u000f\f\u000f\u0182\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f"+
		"\u018c\b\u000f\n\u000f\f\u000f\u018f\t\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0005\u000f\u0194\b\u000f\n\u000f\f\u000f\u0197\t\u000f\u0001\u0010"+
		"\u0001\u0010\u0003\u0010\u019b\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u01a0\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0003\u0013\u01ae\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0003\u0013\u01b2\b\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u01b6\b"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0005\u0015\u01be\b\u0015\n\u0015\f\u0015\u01c1\t\u0015\u0001\u0015"+
		"\u0003\u0015\u01c4\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u01d0\b\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0000\u0002\u0018\u001e\u001a\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02\u0000\b\u0002\u0000\u001c\u001e!!\u0003\u0000%&--66"+
		"\u0002\u0000\u001d\u001d,,\u0004\u0000\u0013\u0014()2288\u0003\u0000\u001c"+
		"\u001c\"$44\u0002\u0000\u0001\u0001\u000f\u000f\u0001\u0000=@\u0001\u0000"+
		":<\u0213\u00004\u0001\u0000\u0000\u0000\u00027\u0001\u0000\u0000\u0000"+
		"\u0004<\u0001\u0000\u0000\u0000\u0006\u0093\u0001\u0000\u0000\u0000\b"+
		"\u0095\u0001\u0000\u0000\u0000\n\u00a2\u0001\u0000\u0000\u0000\f\u00aa"+
		"\u0001\u0000\u0000\u0000\u000e\u00af\u0001\u0000\u0000\u0000\u0010\u00b3"+
		"\u0001\u0000\u0000\u0000\u0012\u00bf\u0001\u0000\u0000\u0000\u0014\u00c7"+
		"\u0001\u0000\u0000\u0000\u0016\u00cf\u0001\u0000\u0000\u0000\u0018\u00e3"+
		"\u0001\u0000\u0000\u0000\u001a\u010c\u0001\u0000\u0000\u0000\u001c\u0134"+
		"\u0001\u0000\u0000\u0000\u001e\u0175\u0001\u0000\u0000\u0000 \u019f\u0001"+
		"\u0000\u0000\u0000\"\u01a1\u0001\u0000\u0000\u0000$\u01a4\u0001\u0000"+
		"\u0000\u0000&\u01b1\u0001\u0000\u0000\u0000(\u01b3\u0001\u0000\u0000\u0000"+
		"*\u01b9\u0001\u0000\u0000\u0000,\u01cf\u0001\u0000\u0000\u0000.\u01d1"+
		"\u0001\u0000\u0000\u00000\u01d3\u0001\u0000\u0000\u00002\u01d5\u0001\u0000"+
		"\u0000\u000045\u0003\u0002\u0001\u000056\u0005\u0000\u0000\u00016\u0001"+
		"\u0001\u0000\u0000\u000078\u0003\u0004\u0002\u00008\u0003\u0001\u0000"+
		"\u0000\u00009;\u0003\u0006\u0003\u0000:9\u0001\u0000\u0000\u0000;>\u0001"+
		"\u0000\u0000\u0000<:\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000"+
		"=@\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000?A\u0003\f\u0006\u0000"+
		"@?\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A\u0005\u0001\u0000"+
		"\u0000\u0000B\u0094\u0005\u0001\u0000\u0000CD\u0003\u0012\t\u0000DE\u0005"+
		"\u0002\u0000\u0000EF\u0003\u0016\u000b\u0000F\u0094\u0001\u0000\u0000"+
		"\u0000G\u0094\u0003\u001e\u000f\u0000H\u0094\u0003\u000e\u0007\u0000I"+
		"\u0094\u0005\u0003\u0000\u0000JK\u0005\u0004\u0000\u0000K\u0094\u0005"+
		"9\u0000\u0000LM\u0005\u0005\u0000\u0000MN\u0003\u0004\u0002\u0000NO\u0005"+
		"\u0006\u0000\u0000O\u0094\u0001\u0000\u0000\u0000PQ\u0005\u0007\u0000"+
		"\u0000QR\u0003\u0018\f\u0000RS\u0005\u0005\u0000\u0000ST\u0003\u0004\u0002"+
		"\u0000TU\u0005\u0006\u0000\u0000U\u0094\u0001\u0000\u0000\u0000VW\u0005"+
		"\b\u0000\u0000WX\u0003\u0004\u0002\u0000XY\u0005\t\u0000\u0000YZ\u0003"+
		"\u0018\f\u0000Z\u0094\u0001\u0000\u0000\u0000[\\\u0005\n\u0000\u0000\\"+
		"]\u0003\u0018\f\u0000]^\u0005\u000b\u0000\u0000^f\u0003\u0004\u0002\u0000"+
		"_`\u0005\f\u0000\u0000`a\u0003\u0018\f\u0000ab\u0005\u000b\u0000\u0000"+
		"bc\u0003\u0004\u0002\u0000ce\u0001\u0000\u0000\u0000d_\u0001\u0000\u0000"+
		"\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000"+
		"\u0000\u0000gk\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000ij\u0005"+
		"\r\u0000\u0000jl\u0003\u0004\u0002\u0000ki\u0001\u0000\u0000\u0000kl\u0001"+
		"\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0005\u0006\u0000\u0000"+
		"n\u0094\u0001\u0000\u0000\u0000op\u0005\u000e\u0000\u0000pq\u00059\u0000"+
		"\u0000qr\u0005\u0002\u0000\u0000rs\u0003\u0018\f\u0000st\u0005\u000f\u0000"+
		"\u0000tw\u0003\u0018\f\u0000uv\u0005\u000f\u0000\u0000vx\u0003\u0018\f"+
		"\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xy\u0001\u0000"+
		"\u0000\u0000yz\u0005\u0005\u0000\u0000z{\u0003\u0004\u0002\u0000{|\u0005"+
		"\u0006\u0000\u0000|\u0094\u0001\u0000\u0000\u0000}~\u0005\u000e\u0000"+
		"\u0000~\u007f\u0003\u0014\n\u0000\u007f\u0080\u0005\u0010\u0000\u0000"+
		"\u0080\u0081\u0003\u0016\u000b\u0000\u0081\u0082\u0005\u0005\u0000\u0000"+
		"\u0082\u0083\u0003\u0004\u0002\u0000\u0083\u0084\u0005\u0006\u0000\u0000"+
		"\u0084\u0094\u0001\u0000\u0000\u0000\u0085\u0086\u0005\u0011\u0000\u0000"+
		"\u0086\u0087\u0003\u0010\b\u0000\u0087\u0088\u0003$\u0012\u0000\u0088"+
		"\u0094\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u0012\u0000\u0000\u008a"+
		"\u008b\u0005\u0011\u0000\u0000\u008b\u008c\u00059\u0000\u0000\u008c\u0094"+
		"\u0003$\u0012\u0000\u008d\u008e\u0005\u0012\u0000\u0000\u008e\u0091\u0003"+
		"\b\u0004\u0000\u008f\u0090\u0005\u0002\u0000\u0000\u0090\u0092\u0003\u0016"+
		"\u000b\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000"+
		"\u0000\u0000\u0092\u0094\u0001\u0000\u0000\u0000\u0093B\u0001\u0000\u0000"+
		"\u0000\u0093C\u0001\u0000\u0000\u0000\u0093G\u0001\u0000\u0000\u0000\u0093"+
		"H\u0001\u0000\u0000\u0000\u0093I\u0001\u0000\u0000\u0000\u0093J\u0001"+
		"\u0000\u0000\u0000\u0093L\u0001\u0000\u0000\u0000\u0093P\u0001\u0000\u0000"+
		"\u0000\u0093V\u0001\u0000\u0000\u0000\u0093[\u0001\u0000\u0000\u0000\u0093"+
		"o\u0001\u0000\u0000\u0000\u0093}\u0001\u0000\u0000\u0000\u0093\u0085\u0001"+
		"\u0000\u0000\u0000\u0093\u0089\u0001\u0000\u0000\u0000\u0093\u008d\u0001"+
		"\u0000\u0000\u0000\u0094\u0007\u0001\u0000\u0000\u0000\u0095\u0096\u0005"+
		"9\u0000\u0000\u0096\u009c\u0003\n\u0005\u0000\u0097\u0098\u0005\u000f"+
		"\u0000\u0000\u0098\u0099\u00059\u0000\u0000\u0099\u009b\u0003\n\u0005"+
		"\u0000\u009a\u0097\u0001\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000"+
		"\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000"+
		"\u0000\u009d\t\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000"+
		"\u009f\u00a0\u0005\u0013\u0000\u0000\u00a0\u00a1\u00059\u0000\u0000\u00a1"+
		"\u00a3\u0005\u0014\u0000\u0000\u00a2\u009f\u0001\u0000\u0000\u0000\u00a2"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a3\u000b\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a6\u0005\u0015\u0000\u0000\u00a5\u00a7\u0003\u0016\u000b\u0000\u00a6"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7"+
		"\u00ab\u0001\u0000\u0000\u0000\u00a8\u00ab\u0005\u0003\u0000\u0000\u00a9"+
		"\u00ab\u0005\u0016\u0000\u0000\u00aa\u00a4\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00ab"+
		"\u00ad\u0001\u0000\u0000\u0000\u00ac\u00ae\u0005\u0001\u0000\u0000\u00ad"+
		"\u00ac\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae"+
		"\r\u0001\u0000\u0000\u0000\u00af\u00b0\u0005\u0017\u0000\u0000\u00b0\u00b1"+
		"\u00059\u0000\u0000\u00b1\u00b2\u0005\u0017\u0000\u0000\u00b2\u000f\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b8\u00059\u0000\u0000\u00b4\u00b5\u0005\u001b"+
		"\u0000\u0000\u00b5\u00b7\u00059\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00bd\u0001\u0000\u0000"+
		"\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\'\u0000\u0000"+
		"\u00bc\u00be\u00059\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd"+
		"\u00be\u0001\u0000\u0000\u0000\u00be\u0011\u0001\u0000\u0000\u0000\u00bf"+
		"\u00c4\u0003\u001a\r\u0000\u00c0\u00c1\u0005\u000f\u0000\u0000\u00c1\u00c3"+
		"\u0003\u001a\r\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c5\u0013\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001"+
		"\u0000\u0000\u0000\u00c7\u00cc\u00059\u0000\u0000\u00c8\u00c9\u0005\u000f"+
		"\u0000\u0000\u00c9\u00cb\u00059\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000"+
		"\u0000\u00cb\u00ce\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000"+
		"\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u0015\u0001\u0000\u0000"+
		"\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d4\u0003\u0018\f\u0000"+
		"\u00d0\u00d1\u0005\u000f\u0000\u0000\u00d1\u00d3\u0003\u0018\f\u0000\u00d2"+
		"\u00d0\u0001\u0000\u0000\u0000\u00d3\u00d6\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5"+
		"\u0017\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7"+
		"\u00d8\u0006\f\uffff\uffff\u0000\u00d8\u00e4\u0005\u0018\u0000\u0000\u00d9"+
		"\u00e4\u0005\u0019\u0000\u0000\u00da\u00e4\u0005\u001a\u0000\u0000\u00db"+
		"\u00e4\u00030\u0018\u0000\u00dc\u00e4\u00032\u0019\u0000\u00dd\u00e4\u0005"+
		"7\u0000\u0000\u00de\u00e4\u0003\"\u0011\u0000\u00df\u00e4\u0003\u001c"+
		"\u000e\u0000\u00e0\u00e4\u0003(\u0014\u0000\u00e1\u00e2\u0007\u0000\u0000"+
		"\u0000\u00e2\u00e4\u0003\u0018\f\b\u00e3\u00d7\u0001\u0000\u0000\u0000"+
		"\u00e3\u00d9\u0001\u0000\u0000\u0000\u00e3\u00da\u0001\u0000\u0000\u0000"+
		"\u00e3\u00db\u0001\u0000\u0000\u0000\u00e3\u00dc\u0001\u0000\u0000\u0000"+
		"\u00e3\u00dd\u0001\u0000\u0000\u0000\u00e3\u00de\u0001\u0000\u0000\u0000"+
		"\u00e3\u00df\u0001\u0000\u0000\u0000\u00e3\u00e0\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e4\u00ff\u0001\u0000\u0000\u0000"+
		"\u00e5\u00e6\n\t\u0000\u0000\u00e6\u00e7\u00055\u0000\u0000\u00e7\u00fe"+
		"\u0003\u0018\f\t\u00e8\u00e9\n\u0007\u0000\u0000\u00e9\u00ea\u0007\u0001"+
		"\u0000\u0000\u00ea\u00fe\u0003\u0018\f\b\u00eb\u00ec\n\u0006\u0000\u0000"+
		"\u00ec\u00ed\u0007\u0002\u0000\u0000\u00ed\u00fe\u0003\u0018\f\u0007\u00ee"+
		"\u00ef\n\u0005\u0000\u0000\u00ef\u00f0\u00053\u0000\u0000\u00f0\u00fe"+
		"\u0003\u0018\f\u0005\u00f1\u00f2\n\u0004\u0000\u0000\u00f2\u00f3\u0007"+
		"\u0003\u0000\u0000\u00f3\u00fe\u0003\u0018\f\u0005\u00f4\u00f5\n\u0003"+
		"\u0000\u0000\u00f5\u00f6\u0005*\u0000\u0000\u00f6\u00fe\u0003\u0018\f"+
		"\u0004\u00f7\u00f8\n\u0002\u0000\u0000\u00f8\u00f9\u0005+\u0000\u0000"+
		"\u00f9\u00fe\u0003\u0018\f\u0003\u00fa\u00fb\n\u0001\u0000\u0000\u00fb"+
		"\u00fc\u0007\u0004\u0000\u0000\u00fc\u00fe\u0003\u0018\f\u0002\u00fd\u00e5"+
		"\u0001\u0000\u0000\u0000\u00fd\u00e8\u0001\u0000\u0000\u0000\u00fd\u00eb"+
		"\u0001\u0000\u0000\u0000\u00fd\u00ee\u0001\u0000\u0000\u0000\u00fd\u00f1"+
		"\u0001\u0000\u0000\u0000\u00fd\u00f4\u0001\u0000\u0000\u0000\u00fd\u00f7"+
		"\u0001\u0000\u0000\u0000\u00fd\u00fa\u0001\u0000\u0000\u0000\u00fe\u0101"+
		"\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u00ff\u0100"+
		"\u0001\u0000\u0000\u0000\u0100\u0019\u0001\u0000\u0000\u0000\u0101\u00ff"+
		"\u0001\u0000\u0000\u0000\u0102\u010d\u00059\u0000\u0000\u0103\u010a\u0003"+
		"\u001c\u000e\u0000\u0104\u0105\u00050\u0000\u0000\u0105\u0106\u0003\u0018"+
		"\f\u0000\u0106\u0107\u00051\u0000\u0000\u0107\u010b\u0001\u0000\u0000"+
		"\u0000\u0108\u0109\u0005\u001b\u0000\u0000\u0109\u010b\u00059\u0000\u0000"+
		"\u010a\u0104\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000\u0000"+
		"\u010b\u010d\u0001\u0000\u0000\u0000\u010c\u0102\u0001\u0000\u0000\u0000"+
		"\u010c\u0103\u0001\u0000\u0000\u0000\u010d\u001b\u0001\u0000\u0000\u0000"+
		"\u010e\u0117\u00059\u0000\u0000\u010f\u0110\u00050\u0000\u0000\u0110\u0111"+
		"\u0003\u0018\f\u0000\u0111\u0112\u00051\u0000\u0000\u0112\u0116\u0001"+
		"\u0000\u0000\u0000\u0113\u0114\u0005\u001b\u0000\u0000\u0114\u0116\u0005"+
		"9\u0000\u0000\u0115\u010f\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000"+
		"\u0000\u0000\u0116\u0119\u0001\u0000\u0000\u0000\u0117\u0115\u0001\u0000"+
		"\u0000\u0000\u0117\u0118\u0001\u0000\u0000\u0000\u0118\u0135\u0001\u0000"+
		"\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u011a\u0123\u0003\u001e"+
		"\u000f\u0000\u011b\u011c\u00050\u0000\u0000\u011c\u011d\u0003\u0018\f"+
		"\u0000\u011d\u011e\u00051\u0000\u0000\u011e\u0122\u0001\u0000\u0000\u0000"+
		"\u011f\u0120\u0005\u001b\u0000\u0000\u0120\u0122\u00059\u0000\u0000\u0121"+
		"\u011b\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0122"+
		"\u0125\u0001\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123"+
		"\u0124\u0001\u0000\u0000\u0000\u0124\u0135\u0001\u0000\u0000\u0000\u0125"+
		"\u0123\u0001\u0000\u0000\u0000\u0126\u0127\u0005\u001f\u0000\u0000\u0127"+
		"\u0128\u0003\u0018\f\u0000\u0128\u0131\u0005 \u0000\u0000\u0129\u012a"+
		"\u00050\u0000\u0000\u012a\u012b\u0003\u0018\f\u0000\u012b\u012c\u0005"+
		"1\u0000\u0000\u012c\u0130\u0001\u0000\u0000\u0000\u012d\u012e\u0005\u001b"+
		"\u0000\u0000\u012e\u0130\u00059\u0000\u0000\u012f\u0129\u0001\u0000\u0000"+
		"\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u0130\u0133\u0001\u0000\u0000"+
		"\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000"+
		"\u0000\u0132\u0135\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000"+
		"\u0000\u0134\u010e\u0001\u0000\u0000\u0000\u0134\u011a\u0001\u0000\u0000"+
		"\u0000\u0134\u0126\u0001\u0000\u0000\u0000\u0135\u001d\u0001\u0000\u0000"+
		"\u0000\u0136\u0137\u0006\u000f\uffff\uffff\u0000\u0137\u0140\u00059\u0000"+
		"\u0000\u0138\u0139\u00050\u0000\u0000\u0139\u013a\u0003\u0018\f\u0000"+
		"\u013a\u013b\u00051\u0000\u0000\u013b\u013f\u0001\u0000\u0000\u0000\u013c"+
		"\u013d\u0005\u001b\u0000\u0000\u013d\u013f\u00059\u0000\u0000\u013e\u0138"+
		"\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013f\u0142"+
		"\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u0141"+
		"\u0001\u0000\u0000\u0000\u0141\u0143\u0001\u0000\u0000\u0000\u0142\u0140"+
		"\u0001\u0000\u0000\u0000\u0143\u0176\u0003 \u0010\u0000\u0144\u0145\u0005"+
		"\u001f\u0000\u0000\u0145\u0146\u0003\u0018\f\u0000\u0146\u014f\u0005 "+
		"\u0000\u0000\u0147\u0148\u00050\u0000\u0000\u0148\u0149\u0003\u0018\f"+
		"\u0000\u0149\u014a\u00051\u0000\u0000\u014a\u014e\u0001\u0000\u0000\u0000"+
		"\u014b\u014c\u0005\u001b\u0000\u0000\u014c\u014e\u00059\u0000\u0000\u014d"+
		"\u0147\u0001\u0000\u0000\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014e"+
		"\u0151\u0001\u0000\u0000\u0000\u014f\u014d\u0001\u0000\u0000\u0000\u014f"+
		"\u0150\u0001\u0000\u0000\u0000\u0150\u0152\u0001\u0000\u0000\u0000\u0151"+
		"\u014f\u0001\u0000\u0000\u0000\u0152\u0153\u0003 \u0010\u0000\u0153\u0176"+
		"\u0001\u0000\u0000\u0000\u0154\u015d\u00059\u0000\u0000\u0155\u0156\u0005"+
		"0\u0000\u0000\u0156\u0157\u0003\u0018\f\u0000\u0157\u0158\u00051\u0000"+
		"\u0000\u0158\u015c\u0001\u0000\u0000\u0000\u0159\u015a\u0005\u001b\u0000"+
		"\u0000\u015a\u015c\u00059\u0000\u0000\u015b\u0155\u0001\u0000\u0000\u0000"+
		"\u015b\u0159\u0001\u0000\u0000\u0000\u015c\u015f\u0001\u0000\u0000\u0000"+
		"\u015d\u015b\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000"+
		"\u015e\u0160\u0001\u0000\u0000\u0000\u015f\u015d\u0001\u0000\u0000\u0000"+
		"\u0160\u0161\u0005\'\u0000\u0000\u0161\u0162\u00059\u0000\u0000\u0162"+
		"\u0176\u0003 \u0010\u0000\u0163\u0164\u0005\u001f\u0000\u0000\u0164\u0165"+
		"\u0003\u0018\f\u0000\u0165\u016e\u0005 \u0000\u0000\u0166\u0167\u0005"+
		"0\u0000\u0000\u0167\u0168\u0003\u0018\f\u0000\u0168\u0169\u00051\u0000"+
		"\u0000\u0169\u016d\u0001\u0000\u0000\u0000\u016a\u016b\u0005\u001b\u0000"+
		"\u0000\u016b\u016d\u00059\u0000\u0000\u016c\u0166\u0001\u0000\u0000\u0000"+
		"\u016c\u016a\u0001\u0000\u0000\u0000\u016d\u0170\u0001\u0000\u0000\u0000"+
		"\u016e\u016c\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000\u0000\u0000"+
		"\u016f\u0171\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000\u0000\u0000"+
		"\u0171\u0172\u0005\'\u0000\u0000\u0172\u0173\u00059\u0000\u0000\u0173"+
		"\u0174\u0003 \u0010\u0000\u0174\u0176\u0001\u0000\u0000\u0000\u0175\u0136"+
		"\u0001\u0000\u0000\u0000\u0175\u0144\u0001\u0000\u0000\u0000\u0175\u0154"+
		"\u0001\u0000\u0000\u0000\u0175\u0163\u0001\u0000\u0000\u0000\u0176\u0195"+
		"\u0001\u0000\u0000\u0000\u0177\u0180\n\u0005\u0000\u0000\u0178\u0179\u0005"+
		"0\u0000\u0000\u0179\u017a\u0003\u0018\f\u0000\u017a\u017b\u00051\u0000"+
		"\u0000\u017b\u017f\u0001\u0000\u0000\u0000\u017c\u017d\u0005\u001b\u0000"+
		"\u0000\u017d\u017f\u00059\u0000\u0000\u017e\u0178\u0001\u0000\u0000\u0000"+
		"\u017e\u017c\u0001\u0000\u0000\u0000\u017f\u0182\u0001\u0000\u0000\u0000"+
		"\u0180\u017e\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000"+
		"\u0181\u0183\u0001\u0000\u0000\u0000\u0182\u0180\u0001\u0000\u0000\u0000"+
		"\u0183\u0194\u0003 \u0010\u0000\u0184\u018d\n\u0002\u0000\u0000\u0185"+
		"\u0186\u00050\u0000\u0000\u0186\u0187\u0003\u0018\f\u0000\u0187\u0188"+
		"\u00051\u0000\u0000\u0188\u018c\u0001\u0000\u0000\u0000\u0189\u018a\u0005"+
		"\u001b\u0000\u0000\u018a\u018c\u00059\u0000\u0000\u018b\u0185\u0001\u0000"+
		"\u0000\u0000\u018b\u0189\u0001\u0000\u0000\u0000\u018c\u018f\u0001\u0000"+
		"\u0000\u0000\u018d\u018b\u0001\u0000\u0000\u0000\u018d\u018e\u0001\u0000"+
		"\u0000\u0000\u018e\u0190\u0001\u0000\u0000\u0000\u018f\u018d\u0001\u0000"+
		"\u0000\u0000\u0190\u0191\u0005\'\u0000\u0000\u0191\u0192\u00059\u0000"+
		"\u0000\u0192\u0194\u0003 \u0010\u0000\u0193\u0177\u0001\u0000\u0000\u0000"+
		"\u0193\u0184\u0001\u0000\u0000\u0000\u0194\u0197\u0001\u0000\u0000\u0000"+
		"\u0195\u0193\u0001\u0000\u0000\u0000\u0195\u0196\u0001\u0000\u0000\u0000"+
		"\u0196\u001f\u0001\u0000\u0000\u0000\u0197\u0195\u0001\u0000\u0000\u0000"+
		"\u0198\u019a\u0005\u001f\u0000\u0000\u0199\u019b\u0003\u0016\u000b\u0000"+
		"\u019a\u0199\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000"+
		"\u019b\u019c\u0001\u0000\u0000\u0000\u019c\u01a0\u0005 \u0000\u0000\u019d"+
		"\u01a0\u0003(\u0014\u0000\u019e\u01a0\u00032\u0019\u0000\u019f\u0198\u0001"+
		"\u0000\u0000\u0000\u019f\u019d\u0001\u0000\u0000\u0000\u019f\u019e\u0001"+
		"\u0000\u0000\u0000\u01a0!\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005\u0011"+
		"\u0000\u0000\u01a2\u01a3\u0003$\u0012\u0000\u01a3#\u0001\u0000\u0000\u0000"+
		"\u01a4\u01a5\u0005\u001f\u0000\u0000\u01a5\u01a6\u0003&\u0013\u0000\u01a6"+
		"\u01a7\u0005 \u0000\u0000\u01a7\u01a8\u0003\u0004\u0002\u0000\u01a8\u01a9"+
		"\u0005\u0006\u0000\u0000\u01a9%\u0001\u0000\u0000\u0000\u01aa\u01ad\u0003"+
		"\u0014\n\u0000\u01ab\u01ac\u0005\u000f\u0000\u0000\u01ac\u01ae\u00057"+
		"\u0000\u0000\u01ad\u01ab\u0001\u0000\u0000\u0000\u01ad\u01ae\u0001\u0000"+
		"\u0000\u0000\u01ae\u01b2\u0001\u0000\u0000\u0000\u01af\u01b2\u00057\u0000"+
		"\u0000\u01b0\u01b2\u0001\u0000\u0000\u0000\u01b1\u01aa\u0001\u0000\u0000"+
		"\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b1\u01b0\u0001\u0000\u0000"+
		"\u0000\u01b2\'\u0001\u0000\u0000\u0000\u01b3\u01b5\u0005.\u0000\u0000"+
		"\u01b4\u01b6\u0003*\u0015\u0000\u01b5\u01b4\u0001\u0000\u0000\u0000\u01b5"+
		"\u01b6\u0001\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7"+
		"\u01b8\u0005/\u0000\u0000\u01b8)\u0001\u0000\u0000\u0000\u01b9\u01bf\u0003"+
		",\u0016\u0000\u01ba\u01bb\u0003.\u0017\u0000\u01bb\u01bc\u0003,\u0016"+
		"\u0000\u01bc\u01be\u0001\u0000\u0000\u0000\u01bd\u01ba\u0001\u0000\u0000"+
		"\u0000\u01be\u01c1\u0001\u0000\u0000\u0000\u01bf\u01bd\u0001\u0000\u0000"+
		"\u0000\u01bf\u01c0\u0001\u0000\u0000\u0000\u01c0\u01c3\u0001\u0000\u0000"+
		"\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c2\u01c4\u0003.\u0017\u0000"+
		"\u01c3\u01c2\u0001\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000\u0000\u0000"+
		"\u01c4+\u0001\u0000\u0000\u0000\u01c5\u01c6\u00050\u0000\u0000\u01c6\u01c7"+
		"\u0003\u0018\f\u0000\u01c7\u01c8\u00051\u0000\u0000\u01c8\u01c9\u0005"+
		"\u0002\u0000\u0000\u01c9\u01ca\u0003\u0018\f\u0000\u01ca\u01d0\u0001\u0000"+
		"\u0000\u0000\u01cb\u01cc\u00059\u0000\u0000\u01cc\u01cd\u0005\u0002\u0000"+
		"\u0000\u01cd\u01d0\u0003\u0018\f\u0000\u01ce\u01d0\u0003\u0018\f\u0000"+
		"\u01cf\u01c5\u0001\u0000\u0000\u0000\u01cf\u01cb\u0001\u0000\u0000\u0000"+
		"\u01cf\u01ce\u0001\u0000\u0000\u0000\u01d0-\u0001\u0000\u0000\u0000\u01d1"+
		"\u01d2\u0007\u0005\u0000\u0000\u01d2/\u0001\u0000\u0000\u0000\u01d3\u01d4"+
		"\u0007\u0006\u0000\u0000\u01d41\u0001\u0000\u0000\u0000\u01d5\u01d6\u0007"+
		"\u0007\u0000\u0000\u01d63\u0001\u0000\u0000\u00004<@fkw\u0091\u0093\u009c"+
		"\u00a2\u00a6\u00aa\u00ad\u00b8\u00bd\u00c4\u00cc\u00d4\u00e3\u00fd\u00ff"+
		"\u010a\u010c\u0115\u0117\u0121\u0123\u012f\u0131\u0134\u013e\u0140\u014d"+
		"\u014f\u015b\u015d\u016c\u016e\u0175\u017e\u0180\u018b\u018d\u0193\u0195"+
		"\u019a\u019f\u01ad\u01b1\u01b5\u01bf\u01c3\u01cf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}