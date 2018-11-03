package mua;

import java.util.Map;
import java.util.TreeMap;

import mua.exception.*;
import mua.object.*;
import mua.object.functor.MuaFunctor;

import java.util.*;
import java.io.*;

public class Interpreter {
    static DataTable dataTable = new DataTable();
    private static int instructionIndex = 0; //when a new inst come, it is set to zero

    public static int getInstructionIndex() {
        return instructionIndex;
    }

    private static void setInstructionIndex(int instructionIndex) {
        Interpreter.instructionIndex = instructionIndex;
    }

    static private List<String> interpret(String instructionStr) throws MuaException {
        instructionStr = removeComment(instructionStr);
        String[] instrcutionArr = strToList(instructionStr);
        //todo: check whether the instruction has a illegal word

        List<String> resultList = new ArrayList<>();
        while (instructionIndex < instrcutionArr.length) {
            resultList.add(handleToken(instrcutionArr).toString());
        }
        return resultList;
    }

    static private MuaObject handleToken(final String[] instructionArr) throws MuaException {//todo
        String token = instructionArr[instructionIndex];
        if (isNumeric(token)) {
            return new MuaNumber(Double.parseDouble(token));
        } else if (isWord(token)) {
            return new MuaWord(token.substring(1));
        } else if (isBool(token)) {
            return new MuaBool(Boolean.parseBoolean(token));
        } else if (isList(token)) {
            return new MuaNone();//todo: not supported
            //todo: need more parse
        } else {
            MuaObject tableFindResult = dataTable.getObject(token);
            if (tableFindResult == null) {
                throw new MuaSymbolNotResolvableException();
            }
            if (tableFindResult.getMuaType() == MuaType.functor) {
                MuaFunctor operator = (MuaFunctor) tableFindResult;
                int argumentNum = operator.getArgumentNum();
                MuaFunctor.ArgumentList argumentList = new MuaFunctor.ArgumentList(argumentNum);
                ++instructionIndex;
                for (int i = 0; i < argumentNum; ++i) {
                    argumentList.set(i, handleToken(instructionArr));
                }
                return operator.operate(argumentList);
            } else {
                return tableFindResult;
            }
        }
    }

    static private String[] strToList(String instruction) {
        return instruction.trim().split("\\s+");
    }

    static private int findCommentBeginner(String instruction) {
        int firstSlashIndex = instruction.indexOf('/');
        if (firstSlashIndex == -1) {
            return -1;
        }
        if (instruction.charAt(firstSlashIndex + 1) == '/') {
            return firstSlashIndex;
        } else {
            return -1;
        }
    }

    static private String removeComment(String instructionWithComment) {
        int commentBeginnerIndex = findCommentBeginner(instructionWithComment);
        if (commentBeginnerIndex == -1) {
            return instructionWithComment;
        }
        return instructionWithComment.substring(0, commentBeginnerIndex);
    }

    static private void clearAfterInstruction() {
        setInstructionIndex(0);
    }

    static private void initInterpreter() {

    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        PrintStream outpuStream = System.out;
        Scanner s = new Scanner(inputStream);
        initInterpreter();
        while (true) {
            outpuStream.print(InteractiveInterface.promptStr);
            String instruction = s.nextLine();
            if (instruction.equals(InteractiveInterface.exitCommand)) {
                outpuStream.println(InteractiveInterface.exitPrompt);
                break;
            }
            List<String> result;
            try {
                result = interpret(instruction);
            } catch (MuaException e) {
                result = new ArrayList<>();
                result.add(e.getMessage());
            }
            outpuStream.println(result);
            clearAfterInstruction();
        }
    }

    private static boolean isNumeric(String str)
    {
        //match a number with optional '-' and decimal.
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isWord(String str) {
        return str.length() >= 2 && str.charAt(0) == '\"';
    }

    private static boolean isList(String str) {//todo: need parse more
        return str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']';
    }

    private static boolean isBool(String str) {
        return "true".equals(str) || "false".equals(str);
    }
}

//TODO:Singlton
class DataTable {
    private Map<String, MuaObject> dataTable = new TreeMap<>();

    MuaObject getObject(String name) {
        return dataTable.get(name);
    }

    void updateObject(String name, MuaObject value) {
        dataTable.put(name, value);
    }
}