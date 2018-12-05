package mua.exception;

public class MuaException extends Exception {
    public Level level;
    public MuaException(String message, Level level) {
        super(message);
        this.level = level;
    }
    public enum Level {
        //error
        ERROR,

        //warning
        WARNING,

        //normal message
        MESSAGE
    }
}
