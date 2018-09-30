// Generated from ./arrayinit/arrayinit.g4 by ANTLR 4.7.1
package com.lujian.tech.libs.antlr4.arrayinit;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link arrayinitParser}.
 */
public interface arrayinitListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link arrayinitParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(arrayinitParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link arrayinitParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(arrayinitParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link arrayinitParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(arrayinitParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link arrayinitParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(arrayinitParser.ValueContext ctx);
}