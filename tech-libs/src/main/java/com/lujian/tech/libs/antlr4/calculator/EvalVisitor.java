package com.lujian.tech.libs.antlr4.calculator;

import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends CalculatorBaseVisitor<Integer> {

    Map<String, Integer> memory = new HashMap<>();

    @Override
    public Integer visitProg(CalculatorParser.ProgContext ctx) {
        return super.visitProg(ctx);
    }

    @Override
    public Integer visitPrintExpr(CalculatorParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }

    @Override
    public Integer visitAssign(CalculatorParser.AssignContext ctx) {
        String var = ctx.ID().getText();
        CalculatorParser.ExprContext expr = ctx.expr();
        Integer value = visit(expr);
        memory.put(var, value);
        return value;
    }

    @Override
    public Integer visitBlank(CalculatorParser.BlankContext ctx) {
        return super.visitBlank(ctx);
    }

    @Override
    public Integer visitParensis(CalculatorParser.ParensisContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitAddSub(CalculatorParser.AddSubContext ctx) {
        CalculatorParser.ExprContext e1 = ctx.expr(0);
        CalculatorParser.ExprContext e2 = ctx.expr(1);
        Integer v1 = visit(e1);
        Integer v2 = visit(e2);
        if (ctx.op.getType() == CalculatorParser.ADD) {
            return v1 + v2;
        }
        if(ctx.op.getType() == CalculatorParser.SUB) {
            return v1 - v2;
        }
        return null;
    }

    @Override
    public Integer visitId(CalculatorParser.IdContext ctx) {
        String text = ctx.ID().getText();
        if(memory.containsKey(text)) {
            return memory.get(text);
        }
        return 0;
    }

    @Override
    public Integer visitInt(CalculatorParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitMulDiv(CalculatorParser.MulDivContext ctx) {
        CalculatorParser.ExprContext e1 = ctx.expr(0);
        CalculatorParser.ExprContext e2 = ctx.expr(1);
        Integer v1 = visit(e1);
        Integer v2 = visit(e2);
        if (ctx.op.getType() == CalculatorParser.MUL) {
            return v1 * v2;
        }
        if(ctx.op.getType() == CalculatorParser.DIV) {
            return v1 / v2;
        }
        return null;
    }

    @Override
    public Integer visitRn(CalculatorParser.RnContext ctx) {
        return super.visitRn(ctx);
    }

}
