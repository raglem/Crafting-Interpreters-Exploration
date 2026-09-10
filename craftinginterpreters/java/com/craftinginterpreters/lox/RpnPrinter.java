package com.craftinginterpreters.lox;

public class RpnPrinter implements Expr.Visitor<String> {
  String print(Expr expr) {
    return expr.accept(this);
  }

  @Override 
  public String visitAssignExpr(Expr.Assign expr) {
    // ex. a = 1
    return expr.value.accept(this) + " = " + expr.name.lexeme;
  }

  @Override
  public String visitBinaryExpr(Expr.Binary expr) {
    // Operator goes at end
    // ex. 5 + 3 -> 5 3 + 
    return expr.left.accept(this) + " " +
           expr.right.accept(this) + " " +
           expr.operator.lexeme;
  }

  @Override
  public String visitCallExpr(Expr.Call expr) {
    // ex. method(arg1, arg2)
    StringBuilder builder = new StringBuilder(expr.callee.accept(this));
    builder.append("(");
    for (Expr argument : expr.arguments) {
      builder.append(argument.accept(this)).append(" ,");
    }
    return builder.replace(builder.length() - 1, builder.length(), " )").toString();
  }

  @Override
  public String visitGetExpr(Expr.Get expr) {
    // ex. object.name
    return expr.object.accept(this) + "." + expr.name.lexeme;
  }

  @Override
  public String visitGroupingExpr(Expr.Grouping expr) {
    // RPN does not use parentheses
    return expr.expression.accept(this);
  }

  @Override
  public String visitLiteralExpr(Expr.Literal expr) {
    // Not an operation
    return expr.value.toString();
  }

  @Override
  public String visitLogicalExpr(Expr.Logical expr) {
    // ex. a && b -> a b &&
    return expr.left.accept(this) + " " +
           expr.right.accept(this) + " " +
           expr.operator.lexeme;
  }

  @Override
  public String visitSetExpr(Expr.Set expr) {
    // ex. object.name = value
    return expr.object.accept(this) + "." + expr.name.lexeme +
           " = " +
           expr.value.accept(this);
  }

  @Override
  public String visitSuperExpr(Expr.Super expr) {
    // ex. super.method
    return "super." + expr.method.lexeme;
  }

  @Override
  public String visitThisExpr(Expr.This expr) {
    return "this";
  }

  @Override
  public String visitUnaryExpr(Expr.Unary expr) {
    String operator = expr.operator.lexeme;
    if (expr.operator.type == TokenType.MINUS) {
      // Can't use same symbol for unary and binary.
      operator = "~";
    }
    return expr.right.accept(this) + " " + operator;
  }

  @Override
  public String visitVariableExpr(Expr.Variable expr) {
    return expr.name.lexeme;
  }
}