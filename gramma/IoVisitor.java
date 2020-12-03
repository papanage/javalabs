// Generated from C:/Users/Vasily/IdeaProjects/javalabs/src/main/java/com/company/lusnikov/nsu/lab5\Io.g4 by ANTLR 4.8
package gramma;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link IoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface IoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link IoParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(IoParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(IoParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#declvar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclvar(IoParser.DeclvarContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(IoParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#goto_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoto_(IoParser.Goto_Context ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(IoParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(IoParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#if_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_(IoParser.If_Context ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(IoParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#orderable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderable(IoParser.OrderableContext ctx);
	/**
	 * Visit a parse tree produced by {@link IoParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(IoParser.NotContext ctx);
}