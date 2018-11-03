package mua;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.TreeMap;

import mua.exception.*;
import mua.object.*;
import mua.object.functor.*;

import java.util.*;
import java.io.*;

public class Interpreter {
    static public DataTable dataTable = new DataTable();
    private static int instructionIndex = 0; //when a new inst come, it is set to zero
    private static InputStream inputStream = System.in;
    /*
    public static Scanner getScanner() {
        return s;
    }
    */
    private static Scanner s = new Scanner(inputStream);

    public static InputStream getInputStream() {
        return inputStream;
    }

    public static PrintStream getOutpuStream() {
        return outpuStream;
    }

    private static PrintStream outpuStream = System.out;

    public static void incrementInstructionIndex() {
        ++instructionIndex;
    }

    private static void setInstructionIndex(int instructionIndex) {
        Interpreter.instructionIndex = instructionIndex;
    }

    static private List<String> interpret(String instructionStr) throws MuaException {
        instructionStr = removeComment(instructionStr);
        String[] instructionArr = strToList(instructionStr);
        //todo: check whether the instruction has a illegal word

        List<String> resultList = new ArrayList<>();
        while (instructionIndex < instructionArr.length) {
            resultList.add(handleToken(instructionArr).toString());
        }
        return resultList;
    }

    static private MuaObject handleToken(final String[] instructionArr) throws MuaException {//todo
        String token = instructionArr[instructionIndex];
        ++instructionIndex;
        if (isNumeric(token)) {
            return new MuaNumber(Double.parseDouble(token));
        } else if (isWord(token)) {
            return new MuaWord(token.substring(1));
        } else if (isBool(token)) {
            return new MuaBool(Boolean.parseBoolean(token));
        } else if (isList(token)) {
            return constructMuaList(token);
        } else if  (isUnbounding(token)) {
            ArgumentList argumentList = new ArgumentList(1);
            argumentList.add(new MuaWord(token.substring(1)));
            return (new MuaThingOperator()).operate(argumentList);
        }
        else {
            MuaObject tableFindResult = dataTable.getObject(token);
            if (tableFindResult == null) {
                throw new MuaSymbolNotResolvableException();
            }
            if (tableFindResult.getMuaType() == MuaType.functor) {
                MuaFunctor operator = (MuaFunctor) tableFindResult;
                int argumentNum = operator.getArgumentNum();
                ArgumentList argumentList = new ArgumentList(argumentNum);

                for (int i = 0; i < argumentNum; ++i) {
                    argumentList.add(handleToken(instructionArr));
                }
                return operator.operate(argumentList);
            } else {
                return tableFindResult;
            }
        }
    }

    static private String[] strToList(String instruction)
            throws MuaBraceNotCompatibleException {
        String[] strRemoveOuterBrace = splitList(instruction);
        if (strRemoveOuterBrace.length == 1) {
            //no brace
            return instruction.trim().split("\\s+");
        }
        String[] leftInst = strRemoveOuterBrace[0].trim().split("\\s+");
        String[] rightInst = strRemoveOuterBrace[2].trim().split("\\s+");
        String[] result = concatStrArray(new String[][]{leftInst,
                new String[]{strRemoveOuterBrace[1]},
                rightInst});
        return Arrays.stream(result).filter(x -> !x.isEmpty()).toArray(String[]::new);
    }

    public static MuaList constructMuaList(String listStr) throws MuaException {
        String listStrWithoutBrace = listStr.substring(1, listStr.length() - 1);
        String[] subListArr = strToList(listStrWithoutBrace);
        List<MuaObject> listToConstructMuaList = new ArrayList<>();
        for (String token: subListArr) {
            if (isNumeric(token)) {
                listToConstructMuaList.add(new MuaNumber(Double.parseDouble(token)));
            } else if (isWord(token)) {
                listToConstructMuaList.add(new MuaWord(token.substring(1)));
            } else if (isBool(token)) {
                listToConstructMuaList.add(new MuaBool(Boolean.parseBoolean(token)));
            } else if (isList(token)) {
                listToConstructMuaList.add(constructMuaList(token));
            } else if  (isUnbounding(token)) {
                ArgumentList argumentList = new ArgumentList(1);
                argumentList.add(new MuaWord(token.substring(1)));
                listToConstructMuaList.add((new MuaThingOperator()).operate(argumentList));
            }
        }
        return new MuaList(listToConstructMuaList);
    }

    static private String[] concatStrArray(String[][] strArr) {
        int totalStrNum = 0;
        for (String[] subArr: strArr) {
            totalStrNum += subArr.length;
        }
        String[] result = new String[totalStrNum];
        int currentCopyIndex = 0;
        for (int i = 0; i < strArr.length; ++i) {
            System.arraycopy(strArr[i], 0, result, currentCopyIndex, strArr[i].length);
            currentCopyIndex += strArr[i].length;
        }
        return result;
    }

    static private String[] splitList(String instruction) throws MuaBraceNotCompatibleException {
        int leftBraceIndex = instruction.indexOf('[');
        int rightBraceIndex = instruction.lastIndexOf(']');
        if (leftBraceIndex == -1 && rightBraceIndex == -1) {
            //not found list
            return new String[]{instruction};
        } else if (leftBraceIndex == -1 || rightBraceIndex == -1) {
            throw new MuaBraceNotCompatibleException();
        }
        //the list in the return array contains brace
        return new String[]{instruction.substring(0, leftBraceIndex),
                instruction.substring(leftBraceIndex, rightBraceIndex + 1),
                instruction.substring(rightBraceIndex+1)};
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
        //register built-in function
        dataTable.updateObject(MuaAddOperator.FUNC_NAME, new MuaAddOperator());
        dataTable.updateObject(MuaAndOperator.FUNC_NAME, new MuaAndOperator());
        dataTable.updateObject(MuaDivideOperator.FUNC_NAME, new MuaDivideOperator());
        dataTable.updateObject(MuaEqualOperator.FUNC_NAME, new MuaEqualOperator());
        dataTable.updateObject(MuaGreatThanOperator.FUNC_NAME, new MuaGreatThanOperator());
        dataTable.updateObject(MuaLessThanOperator.FUNC_NAME, new MuaLessThanOperator());
        dataTable.updateObject(MuaModuloOperator.FUNC_NAME, new MuaModuloOperator());
        dataTable.updateObject(MuaMultiplyOperator.FUNC_NAME, new MuaMultiplyOperator());
        dataTable.updateObject(MuaNotOperator.FUNC_NAME, new MuaNotOperator());
        dataTable.updateObject(MuaOrOperator.FUNC_NAME, new MuaOrOperator());
        dataTable.updateObject(MuaSubOperator.FUNC_NAME, new MuaSubOperator());
        dataTable.updateObject(MuaMakeOperator.FUNC_NAME, new MuaMakeOperator());
        dataTable.updateObject(MuaThingOperator.FUNC_NAME, new MuaThingOperator());
        dataTable.updateObject(MuaEraseOperator.FUNC_NAME, new MuaEraseOperator());
        dataTable.updateObject(MuaIsNameOperator.FUNC_NAME, new MuaIsNameOperator());
        dataTable.updateObject(MuaPrintOperator.FUNC_NAME, new MuaPrintOperator());
        dataTable.updateObject(MuaReadOperator.FUNC_NAME, new MuaReadOperator());
        dataTable.updateObject(MuaReadListOperator.FUNC_NAME, new MuaReadListOperator());
    }

    public static void main(String[] args) {
        initInterpreter();
        while (true) {
            outpuStream.print(InteractiveInterface.promptStr);
            String instruction = s.nextLine();
            if (instruction.equals(InteractiveInterface.exitCommand)) {
                outpuStream.println(InteractiveInterface.exitPrompt);
                break;
            }
            List<String> results;
            try {
                results = interpret(instruction);
            } catch (MuaException e) {
                results = new ArrayList<>();
                results.add(e.getMessage());
            }
            for (String result: results) {
                if (result != "") {
                    outpuStream.println(result);
                }
            }
            clearAfterInstruction();
        }
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isWord(String str) {
        return str.length() >= 2 && str.charAt(0) == '\"';
    }

    private static boolean isList(String str) {//todo: need parse more
        return str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']';
    }

    private static boolean isBool(String str) {
        return "true".equals(str) || "false".equals(str);
    }

    private static boolean isUnbounding(String str) {
        return str.length() >= 2 && str.charAt(0) == ':';
    }
}
