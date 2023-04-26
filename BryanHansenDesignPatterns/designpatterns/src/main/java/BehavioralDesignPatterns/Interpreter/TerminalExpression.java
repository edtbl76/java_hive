package BehavioralDesignPatterns.Interpreter;

import BehavioralDesignPatterns.Interpreter.InterpreterExample_1.Expression;

import java.util.StringTokenizer;

public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        String[] tokenizer = context.split(" ");
        for (String string : tokenizer) {
            if (string.equals(data))
                return true;
        }
        return false;
    }
}
