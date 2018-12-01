package mua;

import java.util.*;
import mua.exception.MuaBraceNotCompatibleException;
import mua.exception.MuaException;

public class Parser {
    private static String[] instructionTokenArr;

    public static ReturnValueFromParser parse(String rawInstruction) throws MuaException {
        //TODO
        String instructionStr = removeComment(rawInstruction);
        instructionTokenArr = instructionToTokenList(instructionStr);
        return handleTokens();
    }

    private static ReturnValueFromParser handleTokens() throws MuaException {
        //TODO
    }

    private static String[] instructionToTokenList(String instruction) throws MuaBraceNotCompatibleException {
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

    static private String[] splitList(String instruction) throws MuaBraceNotCompatibleException {
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

    static private String[] concatStrArray(String[][] strArr) {
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

    static private int findCommentBeginner(String instruction) {
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
