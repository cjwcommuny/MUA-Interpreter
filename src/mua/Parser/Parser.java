package mua.Parser;

import java.util.*;
import mua.exception.MuaBraceNotCompatibleException;
import mua.exception.QuitInterpreterException;
import mua.exception.MuaException;
import mua.object.MuaList;
import mua.object.MuaObject;

public class Parser {
    private static TypeHandler[] typeHandlers = {
            new NumericTypeHandler(), new WordTypeHandler(), new BoolTypeHandler(), new ListTypeHandler()
    };
    private String rawInstruction;
    private String[] instructionTokenArr;
    private int currentHandledTokenIndex = 0;
    private List<String> returnResults; //TODO: should be renamed as printResult ?

    public Parser(String rawInstruction) {
        this.rawInstruction = rawInstruction;
    }

    public List<String> parse() throws MuaException {
        String instructionStr = removeComment(rawInstruction);
        instructionTokenArr = instructionToTokenList(instructionStr);
        handleTokensList();
        return returnResults;
    }

    private void handleTokensList() throws MuaException {
        while (currentHandledTokenIndex < instructionTokenArr.length) {
            String currentToken = instructionTokenArr[currentHandledTokenIndex];
            ++currentHandledTokenIndex;
            MuaObject resultObject = handleSingleToken(currentToken);
            returnResults.add(resultObject.toString());
        }
    }

    private MuaObject handleSingleToken(String currentToken) throws MuaException {
        try {
            return parseSingleToken(currentToken);
        } catch (QuitInterpreterException e) {
            e.setResults(returnResults);
            throw e;
        }
    }

    private MuaObject parseSingleToken(String currentToken) throws QuitInterpreterException {
        MuaObject objectParsed = parseDataType(currentToken);
        if (objectParsed != null) {
            return objectParsed;
        }
        return parseFunction();
    }

    private MuaObject parseFunction() {
    }

    static MuaObject parseDataType(String currentToken) throws MuaException {
        for (TypeHandler typeHandler: typeHandlers) {
            if (typeHandler.isThisType(currentToken)) {
                return typeHandler.returnObjectOfThisType(currentToken);
            }
        }
        //not match for all types
        return null;
    }

    static String[] instructionToTokenList(String instruction) throws MuaBraceNotCompatibleException {
        //TODO: need to be refactored
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

    private static String[] splitList(String instruction) throws MuaBraceNotCompatibleException {
        //TODO: need to be refactored
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

    private static String[] concatStrArray(String[][] strArr) {
        //TODO: need to be refactored
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

    private static String removeComment(String instructionWithComment) {
        int commentBeginnerIndex = findCommentBeginner(instructionWithComment);
        if (commentBeginnerIndex == -1) {
            return instructionWithComment;
        }
        return instructionWithComment.substring(0, commentBeginnerIndex);
    }

    private static int findCommentBeginner(String instruction) {
        //comment beginner is `//`
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
}
