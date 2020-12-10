package dfa;

import java.util.Objects;

public class FinishStep {

    int nextState;
    String pushToStack;

    public FinishStep(int nextState, String pushToStack) {
        this.nextState = nextState;
        this.pushToStack = pushToStack;
    }

    public boolean isFinishState(int state){
        return state == 0 || state == 3 || state == 6;
    }

    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public String getPushToStack() {
        return pushToStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinishStep that = (FinishStep) o;
        return nextState == that.nextState && Objects.equals(pushToStack, that.pushToStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextState, pushToStack);
    }

    public void setPushToStack(String pushToStack) {

        this.pushToStack = pushToStack;
    }
}
