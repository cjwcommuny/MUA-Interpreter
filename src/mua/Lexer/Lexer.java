package mua.Lexer;

import java.util.*;
import mua.exception.MuaBraceNotCompatibleException;
import mua.exception.MuaSymbolNotResolvableException;
import mua.exception.MuaException;
import mua.object.MuaObject;

public class Lexer {
    private String rawInstruction;
    private static TypeHandler[] typeHandlers = {
            new NumericTypeHandler(), new WordTypeHandler(), new BoolTypeHandler(), new ListTypeHandler(),

    };

    public Lexer(String rawInstruction) {
        this.rawInstruction = rawInstruction;
    }

    static String[] instructionToTokenList(String instruction) throws MuaBraceNotCompatibleException {
        //return array of tokens, a list is a single element
        //test case:
        // word1 word2 word3 [element1 element2 [element3 element4] element5] [element6 element7 element8]
        //convert to ==>
        // {word1, word2, word3, [element1 element2 [element3 element4] element5], [element6 element7 element8]}
        //TODO: not satisfied
        error







        String[] strOuterListSplit = splitList(instruction); // TODO: bad variable name
        boolean strHasNoList = strOuterListSplit.length == 1;
        if (strHasNoList) {
            return instruction.trim().split("\\s+");
        }
        String[] leftInstruction = strOuterListSplit[0].trim().split("\\s+");
        String[] rightInstruction = strOuterListSplit[2].trim().split("\\s+");
        String[] result = concatStrArray(new String[][]{leftInstruction,
                new String[]{strOuterListSplit[1]},
                rightInstruction});
        return Arrays.stream(result).filter(x -> !x.isEmpty()).toArray(String[]::new);
    }

    private static String[] splitList(String instruction) throws MuaBraceNotCompatibleException {
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

    public List<MuaObject> scan() throws MuaException {
        String instructionStr = removeComment(rawInstruction);
        String[] instructionTokenArr = instructionToTokenList(instructionStr);
        return evaluateTokenList(instructionTokenArr);
    }

    static List<MuaObject> evaluateTokenList(String[] instructionTokenArr) throws MuaException {
        List<MuaObject> objectList = new LinkedList<>();
        for (String token: instructionTokenArr) {
            MuaObject object = evaluateToken(token);
            objectList.add(object);
        }
        return objectList;
    }

    private static MuaObject evaluateToken(String token) throws MuaException {
        for (TypeHandler typeHandler: typeHandlers) {
            if (typeHandler.isThisType(token)) {
                return typeHandler.returnObjectOfThisType(token);
            }
        }
        throw new MuaSymbolNotResolvableException();
    }
}
