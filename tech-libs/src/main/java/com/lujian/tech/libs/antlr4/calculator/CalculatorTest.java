package com.lujian.tech.libs.antlr4.calculator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CalculatorTest {

    public static void main(String[] args) throws IOException {
        String text = "193\n" +
                "a = 5\n" +
                "b = 6\n" +
                "a + b * 2\n" +
                "(1 + 2) * 2\n";
        System.out.println(text);
        ByteArrayInputStream bais = new ByteArrayInputStream(text.getBytes());
        parseAndEval(bais);
    }

    private static void parseAndEval(InputStream in) throws IOException {
        CharStream input = CharStreams.fromStream(in);
        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokenStream);
        CalculatorParser.ProgContext context = parser.prog();
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(context);
    }

}
