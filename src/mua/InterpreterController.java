package mua;

import mua.lexer.Lexer;
import mua.exception.*;
import mua.object.*;
import mua.object.operator.ArgumentList;

import java.util.*;

public class InterpreterController {

    public static void main(String[] args) {
        interpret();
    }

    public static void testOneInstruction(String rawInstruction) throws MuaException {
        List<MuaObject> operationList = scanInstruction(rawInstruction);
        runOperations(operationList);
    }

    private static void interpret() {
        initInterpreter();
        while (true) {
            try {
                String rawInstruction = readInstruction();
                List<MuaObject> operationList = scanInstruction(rawInstruction);
                runOperations(operationList);
            } catch (QuitInterpreterException e) {
                break;
            } catch (MuaException e) {
                printOnConsole(e.getMessage());
            }
        }
        quitInterpreter();
    }

    private static void runOperations(List<MuaObject> operationList) throws MuaException {
        InstructionRunner instructionRunner = new InstructionRunner(operationList, InstructionRunner.Mode.INTERACTIVE);
        instructionRunner.run();
    }

    private static void quitInterpreter() {
        CommandLineInterface.quitInterpreter();
    }

    private static void initInterpreter() {
        //empty for now
    }

    private static String readInstruction() {
        return CommandLineInterface.getNextInstruction();
    }

    private static List<MuaObject> scanInstruction(String rawInstruction) throws MuaException {
        Lexer lexer = new Lexer(rawInstruction);
        return lexer.scan();
    }

    public static void printOnConsole(Object object) {
        CommandLineInterface.print(object);
    }

    public static ArgumentList readALineAsList() throws MuaException{
        String line = CommandLineInterface.getNextLine();
        Lexer lexer = new Lexer(line);
        List<MuaObject> objectList = lexer.scan();
        return new ArgumentList(objectList);
    }

    public static MuaObject readToken() throws MuaException {
        String token = CommandLineInterface.getNextToken();
        Lexer lexer = new Lexer(token);
        return lexer.scan().get(0);
    }
}
