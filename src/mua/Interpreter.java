package mua;

import mua.lexer.Lexer;
import mua.exception.*;
import mua.object.*;

import java.util.*;
import java.io.*;

public class Interpreter {
    private static InputStream inputStream = System.in;
    private static Scanner s = new Scanner(inputStream);
    private static PrintStream outpuStream = System.out;

    private static String rawInstruction;
    private static List<MuaObject> operationList;
    private static List<String> returnResults;
    private static boolean previousWorkSuccessful = true;
    private static boolean shouldInterpreterContinueLoop = true;
    private static String lexerMessage;
    private static String runningMessage;

    public static void main(String[] args) {
        initInterpreter();
        while (shouldContinue()) {
            readyForReadingInstruction();
            readInstruction();
            scanInstruction();
            runOperations();
            handleOutput();
            clearAfterInstruction();
        }
        quitInterpreter();
    }

    private static void runOperations() {
        if (!previousWorkSuccessful) {
            return;
        }
        try {
            InstructionRunner instructionRunner = new InstructionRunner(operationList);
            returnResults = instructionRunner.run();
        } catch (QuitInterpreterException e) {
            returnResults = e.getResults();
            shouldInterpreterContinueLoop = false;
        } catch (MuaException e) {
            runningMessage = e.getMessage();
            previousWorkSuccessful = false;
        }
    }

    private static void quitInterpreter() {
        outpuStream.println("Quit MUA.");
    }

    private static void initInterpreter() {
        //TODO
    }

    private static boolean shouldContinue() {
        return shouldInterpreterContinueLoop;
    }

    private static void readyForReadingInstruction() {
        printPrompt();
    }

    private static void printPrompt() {
        outpuStream.print(InteractiveInterface.promptStr);
    }

    private static void readInstruction() {
        if (!previousWorkSuccessful) {
            return;
        }
        rawInstruction = s.nextLine();
    }

    private static void scanInstruction() {
        if (!previousWorkSuccessful) {
            return;
        }
        try {
            Lexer lexer = new Lexer(rawInstruction);
            operationList = lexer.scan();
        } catch (MuaException e) {
            lexerMessage = e.getMessage();
            previousWorkSuccessful = false;
        }
    }

    private static void handleOutput() {
        printResults();
        printMessage();
    }

    private static void printResults() {
        if (returnResults == null) {
            //TODO: should always allocated?
            return;
        }
        for (String result: returnResults) {
            if (!result.equals("")) {
                outpuStream.println(result);
            }
        }
    }

    private static void printMessage() {
        if (lexerMessage != null) {
            outpuStream.println(lexerMessage);
        }
        if (runningMessage != null) {
            outpuStream.println(runningMessage);
        }
    }

    static private void clearAfterInstruction() {
        returnResults = null;
        runningMessage = null;
        previousWorkSuccessful = true;
    }
    public static void printConsole(MuaObject object) {
        outpuStream.println(object);
    }
}
