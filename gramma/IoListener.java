// Generated from C:/Users/Vasily/IdeaProjects/javalabs/src/main/java/com/company/lusnikov/nsu/lab5\Io.g4 by ANTLR 4.8
package gramma;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IoParser}.
 */
public interface IoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IoParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IoParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IoParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IoParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IoParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#declvar}.
	 * @param ctx the parse tree
	 */
	void enterDeclvar(IoParser.DeclvarContext ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#declvar}.
	 * @param ctx the parse tree
	 */
	void exitDeclvar(IoParser.DeclvarContext ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(IoParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(IoParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#goto_}.
	 * @param ctx the parse tree
	 */
	void enterGoto_(IoParser.Goto_Context ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#goto_}.
	 * @param ctx the parse tree
	 */
	void exitGoto_(IoParser.Goto_Context ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(IoParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(IoParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(IoParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(IoParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#if_}.
	 * @param ctx the parse tree
	 */
	void enterIf_(IoParser.If_Context ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#if_}.
	 * @param ctx the parse tree
	 */
	void exitIf_(IoParser.If_Context ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(IoParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(IoParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#orderable}.
	 * @param ctx the parse tree
	 */
	void enterOrderable(IoParser.OrderableContext ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#orderable}.
	 * @param ctx the parse tree
	 */
	void exitOrderable(IoParser.OrderableContext ctx);
	/**
	 * Enter a parse tree produced by {@link IoParser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(IoParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link IoParser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(IoParser.NotContext ctx);
}