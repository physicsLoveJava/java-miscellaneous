package com.lujian.tech.libs.antlr4.arrayinit;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;

public class ArrayInitListenerTest {

    public static class ArrayInitBizListener extends arrayinitBaseListener {
        @Override
        public void enterInit(arrayinitParser.InitContext ctx) {
            super.enterInit(ctx);
            System.out.println("ArrayInitBizListener.enterInit");
            System.out.println("ctx = [" + ctx + "]");
        }

        @Override
        public void exitInit(arrayinitParser.InitContext ctx) {
            super.exitInit(ctx);
            System.out.println("ArrayInitBizListener.exitInit");
            System.out.println("ctx = [" + ctx + "]");
        }

        @Override
        public void enterValue(arrayinitParser.ValueContext ctx) {
            super.enterValue(ctx);
            System.out.println("ArrayInitBizListener.enterValue");
            System.out.println("ctx = [" + ctx + "]");
        }

        @Override
        public void exitValue(arrayinitParser.ValueContext ctx) {
            super.exitValue(ctx);
            System.out.println("ArrayInitBizListener.exitValue");
            System.out.println("ctx = [" + ctx + "]");
            System.out.println(ctx.INT().getText());
        }

        @Override
        public void enterEveryRule(ParserRuleContext ctx) {
            super.enterEveryRule(ctx);
            System.out.println("ArrayInitBizListener.enterEveryRule");
            System.out.println("ctx = [" + ctx + "]");
        }

        @Override
        public void exitEveryRule(ParserRuleContext ctx) {
            super.exitEveryRule(ctx);
            System.out.println("ArrayInitBizListener.exitEveryRule");
            System.out.println("ctx = [" + ctx + "]");
        }

        @Override
        public void visitTerminal(TerminalNode node) {
            super.visitTerminal(node);
            System.out.println("ArrayInitBizListener.visitTerminal");
            System.out.println("node = [" + node + "]");
        }

        @Override
        public void visitErrorNode(ErrorNode node) {
            super.visitErrorNode(node);
            System.out.println("ArrayInitBizListener.visitErrorNode");
            System.out.println("node = [" + node + "]");
        }
    }

    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromStream(System.in);
        arrayinitLexer lexer = new arrayinitLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        arrayinitParser parser = new arrayinitParser(tokenStream);
        arrayinitParser.InitContext context = parser.init();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ArrayInitBizListener(), context);
    }

}
