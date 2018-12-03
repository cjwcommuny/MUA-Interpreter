package mua;

import mua.lexer.Lexer;
import mua.exception.*;
import mua.object.*;
import mua.object.functor.ArgumentList;

import java.util.*;

public class Interpreter {
    private static boolean shouldInterpreterContinueLoop = true;
    private static String lexerMessage;
    private static String runningMessage;

    public static void main(String[] args) {
        interpret();
    }

    private static void interpret() {
        initInterpreter();
        while (shouldContinue()) {
            try {
                readyForReadingInstruction();
                String rawInstruction = readInstruction();
                List<MuaObject> operationList = scanInstruction(rawInstruction);
                runOperations(operationList);
            } catch (MuaException e) {
                //exception has been handled
            } finally {
                printMessage();
                clearAfterAnInstruction();
            }
        }
        quitInterpreter();
    }

    private static void runOperations(List<MuaObject> operationList) throws MuaException {
        try {
            InstructionRunner instructionRunner = new InstructionRunner(operationList);
            instructionRunner.run();
        } catch (QuitInterpreterException e) {
            shouldInterpreterContinueLoop = false;
        } catch (MuaException e) {
            runningMessage = e.getMessage();
        }
    }

    private static void quitInterpreter() {
        InteractiveInterface.quitInterpreter();
    }

    private static void initInterpreter() {
        //TODO
    }

    private static boolean shouldContinue() {
        return shouldInterpreterContinueLoop;
    }

    private static void readyForReadingInstruction() {
        //empty for now
    }

    private static String readInstruction() {
        return InteractiveInterface.getNextInstruction();
    }

    private static List<MuaObject> scanInstruction(String rawInstruction) throws MuaException {
        try {
            Lexer lexer = new Lexer(rawInstruction);
            return lexer.scan();
        } catch (MuaException e) {
            lexerMessage = e.getMessage();
            throw e;
        }
    }

    private static void printMessage() {
        InteractiveInterface.print(lexerMessage);
        InteractiveInterface.print(runningMessage);
    }

    static private void clearAfterAnInstruction() {
        lexerMessage = null;
        runningMessage = null;
    }

    public static void printOnConsole(MuaObject object) {
        InteractiveInterface.print(object);
    }

    public static ArgumentList readALineAsList() throws MuaException{
        String line = InteractiveInterface.getNextLine();
        Lexer lexer = new Lexer(line);
        List<MuaObject> objectList = lexer.scan();
        return new ArgumentList(objectList);
    }

    public static MuaObject readToken() throws MuaException {
        String token = InteractiveInterface.getNextToken();
        Lexer lexer = new Lexer(token);
        return lexer.scan().get(0); //TODO: error handling
    }
}
