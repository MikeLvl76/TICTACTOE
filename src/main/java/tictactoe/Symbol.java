package tictactoe;
public enum Symbol {

    WHITESPACE(' '),
    X('X'),
    O('O');

    private char value;

    Symbol(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static boolean isInEnum(char c) {
        for (Symbol symbol : Symbol.values()) {
            if (symbol.getValue() == c) {
                return true;
            }
        }
        return false;
    }

}
