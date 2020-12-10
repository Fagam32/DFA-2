package dfa;

import java.util.HashMap;
import java.util.Stack;

public class Automata {
    public static final String EMPTY = "";

    private final HashMap<InitialStep, FinishStep> transitions = new HashMap<>();

    private final Stack<String> stack = new Stack<>();

    private int currentState = 0;

    public Automata() {
        transitions.put(new InitialStep(0, "x", "Z"), new FinishStep(1, "AZ"));
        transitions.put(new InitialStep(0, "z", "Z"), new FinishStep(4, "DZ"));

        transitions.put(new InitialStep(1, "x", "A"), new FinishStep(1, "B"));
        transitions.put(new InitialStep(1, "x", "B"), new FinishStep(1, "C"));
        transitions.put(new InitialStep(1, "x", "C"), new FinishStep(1, "AC"));
        transitions.put(new InitialStep(1, "b", "C"), new FinishStep(2, ""));

        transitions.put(new InitialStep(2, "b", "C"), new FinishStep(2, ""));
        transitions.put(new InitialStep(2, "", "Z"), new FinishStep(3, "Z"));

        transitions.put(new InitialStep(3, "z", "Z"), new FinishStep(4, "DZ"));

        transitions.put(new InitialStep(4, "z", "D"), new FinishStep(4, "DD"));
        transitions.put(new InitialStep(4, "a", "D"), new FinishStep(5, "E"));

        transitions.put(new InitialStep(5, "a", "D"), new FinishStep(5, "E"));
        transitions.put(new InitialStep(5, "a", "E"), new FinishStep(5, "F"));
        transitions.put(new InitialStep(5, "a", "F"), new FinishStep(5, ""));
        transitions.put(new InitialStep(5, "", "Z"), new FinishStep(6, "Z"));

        pushToStack("Z");
    }

    public void parse(String input) {
        for (int i = 0; i < input.length();) {
            State state = getNextState(String.valueOf(input.charAt(i)));
            switch (state) {
                case ERROR:
                    throw new IllegalArgumentException("Not expected symbol at " + i);
                case OK:
                    i++;
                    break;
                case EPSILON:
                    continue;
            }
        }
    }

    private State getNextState(String symbol) {
        if (stack.isEmpty())
            return State.ERROR;
        String stackSymbol = stack.pop();

        InitialStep initStep = new InitialStep(currentState, symbol, stackSymbol);
        InitialStep initStepEmpty = new InitialStep(currentState, EMPTY, stackSymbol);
        FinishStep finishStep;
        boolean wasEmpty = false;
        if (transitions.containsKey(initStep)) {
            finishStep = transitions.get(initStep);
        } else if (transitions.containsKey(initStepEmpty)) {
            finishStep = transitions.get(initStepEmpty);
            wasEmpty = true;
        } else
            return State.ERROR;

        pushToStack(finishStep.getPushToStack());
        currentState = finishStep.getNextState();

        return wasEmpty ? State.EPSILON : State.OK;
    }

    private void pushToStack(String pushToStack) {
        for (int i = pushToStack.length() - 1; i >=0; i--)
        stack.push(String.valueOf(pushToStack.charAt(i)));
    }

    private enum State {
        EPSILON, ERROR, OK
    }
}
