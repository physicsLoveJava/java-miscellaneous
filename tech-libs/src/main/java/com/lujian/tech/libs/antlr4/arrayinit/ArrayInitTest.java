package com.lujian.tech.libs.antlr4.arrayinit;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class ArrayInitTest {

    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromStream(System.in);
        arrayinitLexer lexer = new arrayinitLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        arrayinitParser parser = new arrayinitParser(tokenStream);
        arrayinitParser.InitContext context = parser.init();
        System.out.println(context.toStringTree(parser));
    }

}
