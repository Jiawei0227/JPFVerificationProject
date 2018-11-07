package edu.ut.verify.core;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class Predicate {

    private String expression;

    public Predicate(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
