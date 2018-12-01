package mua;

import mua.exception.*;
import mua.object.*;
import mua.object.functor.*;
import mua.InterpreterRunningCommand.*;

import java.util.*;
import java.io.*;

public class Interpreter {
    //static public DataTable dataTable = new DataTable();TODO: move to another class
    //private static int instructionIndex = 0; //when a new inst come, it is set to zero TODO: use?
    private static InputStream inputStream = System.in;
    private static Scanner s = new Scanner(inputStream);
    private static PrintStream outpuStream = System.out;

    private static String rawInstruction;
    private static List<String> returnResults;
    private static InterpreterRunningCommand runningCommand;
    private static String parseMessage;



//    public static InputStream getInputStream() {
//        return inputStream;
//    }
//
//    public static PrintStream getOutpuStream() {
//        return outpuStream;
//    }
//
//    public static void incrementInstructionIndex() {
//        ++instructionIndex;
//    }
//
//    private static void setInstructionIndex(int instructionIndex) {
//        Interpreter.instructionIndex = instructionIndex;
//    }
//
//    static private List<String> interpret(String instructionStr) throws MuaException {
//        instructionStr = removeComment(instructionStr);
//        String[] instructionArr = strToList(instructionStr);
//        //todo: check whether the instruction has a illegal word
//
//        List<String> resultList = new ArrayList<>();
//        while (instructionIndex < instructionArr.length) {
//            resultList.add(handleToken(instructionArr).toString());
//        }
//        return resultList;
//    }
//
//    static private MuaObject handleToken(final String[] instructionArr) throws MuaException {//todo
//        String token = instructionArr[instructionIndex];
//        ++instructionIndex;
//        if (isNumeric(token)) {
//            return new MuaNumber(Double.parseDouble(token));
//        } else if (isWord(token)) {
//            return new MuaWord(token.substring(1));
//        } else if (isBool(token)) {
//            return new MuaBool(Boolean.parseBoolean(token));
//        } else if (isList(token)) {
//            return constructMuaList(token);
//        } else if  (isUnbounding(token)) {
//            ArgumentList argumentList = new ArgumentList(1);
//            argumentList.add(new MuaWord(token.substring(1)));
//            return (new MuaThingOperator()).operate(argumentList);
//        }
//        else {
//            MuaObject tableFindResult = dataTable.getObject(token);
//            if (tableFindResult == null) {
//                throw new MuaSymbolNotResolvableException();
//            }
//            if (tableFindResult.getMuaType() == MuaType.functor) {
//                MuaFunctor operator = (MuaFunctor) tableFindResult;
//                int argumentNum = operator.getArgumentNum();
//                ArgumentList argumentList = new ArgumentList(argumentNum);
//
//                for (int i = 0; i < argumentNum; ++i) {
//                    argumentList.add(handleToken(instructionArr));
//                }
//                return operator.operate(argumentList);
//            } else {
//                return tableFindResult;
//            }
//        }
//    }
//
//
//    public static MuaList constructMuaList(String listStr) throws MuaException {
//        String listStrWithoutBrace = listStr.substring(1, listStr.length() - 1);
//        String[] subListArr = strToList(listStrWithoutBrace);
//        List<MuaObject> listToConstructMuaList = new ArrayList<>();
//        for (String token: subListArr) {
//            if (isNumeric(token)) {
//                listToConstructMuaList.add(new MuaNumber(Double.parseDouble(token)));
//            } else if (isWord(token)) {
//                listToConstructMuaList.add(new MuaWord(token.substring(1)));
//            } else if (isBool(token)) {
//                listToConstructMuaList.add(new MuaBool(Boolean.parseBoolean(token)));
//            } else if (isList(token)) {
//                listToConstructMuaList.add(constructMuaList(token));
//            } else if  (isUnbounding(token)) {
//                ArgumentList argumentList = new ArgumentList(1);
//                argumentList.add(new MuaWord(token.substring(1)));
//                listToConstructMuaList.add((new MuaThingOperator()).operate(argumentList));
//            }
//        }
//        return new MuaList(listToConstructMuaList);
//    }
//
//
//
//    static private void initInterpreter() {
//        //TODO
//        //register built-in function
//        dataTable.updateObject(MuaAddOperator.FUNC_NAME, new MuaAddOperator());
//        dataTable.updateObject(MuaAndOperator.FUNC_NAME, new MuaAndOperator());
//        dataTable.updateObject(MuaDivideOperator.FUNC_NAME, new MuaDivideOperator());
//        dataTable.updateObject(MuaEqualOperator.FUNC_NAME, new MuaEqualOperator());
//        dataTable.updateObject(MuaGreatThanOperator.FUNC_NAME, new MuaGreatThanOperator());
//        dataTable.updateObject(MuaLessThanOperator.FUNC_NAME, new MuaLessThanOperator());
//        dataTable.updateObject(MuaModuloOperator.FUNC_NAME, new MuaModuloOperator());
//        dataTable.updateObject(MuaMultiplyOperator.FUNC_NAME, new MuaMultiplyOperator());
//        dataTable.updateObject(MuaNotOperator.FUNC_NAME, new MuaNotOperator());
//        dataTable.updateObject(MuaOrOperator.FUNC_NAME, new MuaOrOperator());
//        dataTable.updateObject(MuaSubOperator.FUNC_NAME, new MuaSubOperator());
//        dataTable.updateObject(MuaMakeOperator.FUNC_NAME, new MuaMakeOperator());
//        dataTable.updateObject(MuaThingOperator.FUNC_NAME, new MuaThingOperator());
//        dataTable.updateObject(MuaEraseOperator.FUNC_NAME, new MuaEraseOperator());
//        dataTable.updateObject(MuaIsNameOperator.FUNC_NAME, new MuaIsNameOperator());
//        dataTable.updateObject(MuaPrintOperator.FUNC_NAME, new MuaPrintOperator());
//        dataTable.updateObject(MuaReadOperator.FUNC_NAME, new MuaReadOperator());
//        dataTable.updateObject(MuaReadListOperator.FUNC_NAME, new MuaReadListOperator());
//    }

    public static void main(String[] args) {
        initInterpreter();
        while (shouldContinue()) {
            readyForReadingInstruction();
            readInstruction();
            handleInstruction();
            handleOutput();
            clearAfterInstruction();
        }
    }

    private static void initInterpreter() {
        //TODO
    }

    private static boolean shouldContinue() {
        return runningCommand == InterpreterRunningCommand.CONTINUE;
    }

    private static void readyForReadingInstruction() {
        printPrompt();
    }

    private static void printPrompt() {
        outpuStream.print(InteractiveInterface.promptStr);
    }

    private static void readInstruction() {
        rawInstruction = s.nextLine();
    }

    private static void handleInstruction() {
        try {
            parseInstruction();
        } catch (MuaException e) {
            parseMessage = e.getMessage();
        }
    }

    private static void parseInstruction() throws MuaException {
        ReturnValueFromParser returnValueFromParser = Parser.parse(rawInstruction);
        runningCommand = returnValueFromParser.getCommand();
        returnResults = returnValueFromParser.getResults();
    }

    private static void handleOutput() {
        printResults();
        printMessage();
    }

    private static void printResults() {
        for (String result: returnResults) {
            outpuStream.println(result);
        }
    }

    private static void printMessage() {
        outpuStream.println(parseMessage);
    }

    static private void clearAfterInstruction() {
        returnResults = null;
        parseMessage = null;
        runningCommand = null;
    }

//    public static boolean isNumeric(String str)
//    {
//        return str.matches("-?\\d+(\\.\\d+)?");
//    }
//
//    public static boolean isWord(String str) {
//        return str.length() >= 2 && str.charAt(0) == '\"';
//    }
//
//    private static boolean isList(String str) {//todo: need parse more
//        return str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']';
//    }
//
//    private static boolean isBool(String str) {
//        return "true".equals(str) || "false".equals(str);
//    }
//
//    private static boolean isUnbounding(String str) {
//        return str.length() >= 2 && str.charAt(0) == ':';
//    }

}
