package dfa;

import java.util.Objects;

public class InitialStep {
    int state;
    String symbol;
    String stackSymbol;

    public InitialStep(int state, String symbol, String stackSymbol) {
        this.state = state;
        this.symbol = symbol;
        this.stackSymbol = stackSymbol;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStackSymbol() {
        return stackSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InitialStep that = (InitialStep) o;
        return state == that.state && Objects.equals(symbol, that.symbol) && Objects.equals(stackSymbol, that.stackSymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, symbol, stackSymbol);
    }

    public void setStackSymbol(String stackSymbol) {

        this.stackSymbol = stackSymbol;
    }
}
