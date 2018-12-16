package mua.lexer;

import java.util.*;
import mua.exception.MuaBraceNotCompatibleException;
import mua.exception.MuaIllegalExpressionException;
import mua.exception.MuaSymbolNotResolvableException;
import mua.exception.MuaException;
import mua.object.MuaObject;

public class Lexer {
    private String rawInstruction;
    private static TypeHandler[] typeHandlers = {
            new NumericTypeHandler(), new WordTypeHandler(), new BoolTypeHandler(), new ListTypeHandler(),
            new DereferenceTypeHandler(), new OperatorTypeHandler(), new FunctionTypeHandler()
    };

    public Lexer(String rawInstruction) {
        this.rawInstruction = rawInstruction;
    }

    static List<String> instructionToTokenList(String instruction)
            throws MuaBraceNotCompatibleException, MuaIllegalExpressionException {
        List<String> tokenList = new LinkedList<>();
        //meet `[` counter++, meet `]` counter--
        int bracketMatchingCounter = 0;
        int currentTokenStart = 0, currentTokenEnd;
        for (int i = 0; i < instruction.length(); ++i) {
            char currentChar = instruction.charAt(i);
            currentTokenEnd = i;

            boolean notInAPairOfBrackets = (bracketMatchingCounter == 0);
            if (currentChar == ' ' && notInAPairOfBrackets) {
                addTokenList(tokenList, instruction, currentTokenStart, currentTokenEnd);
                currentTokenStart = currentTokenEnd + 1;
            } else if (currentChar == '[') {
                if (currentTokenStart != i && instruction.charAt(i-1) != ' ') {
                    if (instruction.charAt(currentTokenStart) == '\"') {
                        //allow word "has[ and word "has"
                        continue;
                    }
                }
                if (notInAPairOfBrackets) {
                    currentTokenStart = i;
                }
                ++bracketMatchingCounter;
            }  else if (currentChar == ']') {
                if (currentTokenStart != i
                        && instruction.charAt(i-1) != ' '
                        && instruction.charAt(currentTokenStart) == '\"') {
                    continue;
                }
                --bracketMatchingCounter;
                if (notInAPairOfBrackets) {
                    addTokenList(tokenList, instruction, currentTokenStart, currentTokenEnd);
                    currentTokenStart = currentTokenEnd + 1;
                }
            }
        }
        //handle the last word
        currentTokenEnd = instruction.length();
        addTokenList(tokenList, instruction, currentTokenStart, currentTokenEnd);
        if (bracketMatchingCounter != 0) {
            throw new MuaBraceNotCompatibleException();
        }
        return tokenList;
    }

    private static void addTokenList(List<String> tokenList, String instruction, int start, int end) {
        if (start != end) {
            tokenList.add(instruction.substring(start, end));
        }
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
        List<String> instructionTokenArr = instructionToTokenList(instructionStr);
        return evaluateTokenList(instructionTokenArr);
    }

    static List<MuaObject> evaluateTokenList(List<String> instructionTokenArr) throws MuaException {
        List<MuaObject> objectList = new LinkedList<>();
        for (String token: instructionTokenArr) {
            List<MuaObject> currentObjectList = evaluateToken(token);
            objectList.addAll(currentObjectList);
        }
        return objectList;
    }

    private static List<MuaObject> evaluateToken(String token) throws MuaException {
        for (TypeHandler typeHandler: typeHandlers) {
            if (typeHandler.isThisType(token)) {
                return typeHandler.returnObjectOfThisType(token);
            }
        }
        throw new MuaSymbolNotResolvableException(token);
    }
}
