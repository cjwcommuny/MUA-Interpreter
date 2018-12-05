package mua.lexer;

import mua.exception.MuaException;
import mua.object.*;
import mua.object.operator.MuaExitOperator;
import mua.object.operator.MuaMakeOperator;
import mua.object.operator.MuaReadOperator;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class LexerTest {
    @Test
    public void evaluateTokenList() {
        List<String> tokenList1 =
                new LinkedList<String>(
                        Arrays.asList("make", "\"word1", "[make \"word2 read 1 [\"word3 \"word4]]", "exit")
                );
        MuaList innerMuaList = new MuaList(new LinkedList<>(
           Arrays.asList(new MuaWord("word3"), new MuaWord("word4"))
        ));
        MuaList outerMuaList = new MuaList(new LinkedList<>(
           Arrays.asList(
                   new MuaMakeOperator(),
                   new MuaWord("word2"),
                   new MuaReadOperator(),
                   new MuaNumber(1),
                   innerMuaList
           )
        ));
        MuaObject[] correctObjectList1 = {
                new MuaMakeOperator(), new MuaWord("word1"), outerMuaList, new MuaExitOperator()
        };
        testEvaluateTokenList(tokenList1, correctObjectList1);
    }

    private void testEvaluateTokenList(List<String> tokenList, MuaObject[] expectObjectList) {
        try {
            List<MuaObject> resultObjectList = Lexer.evaluateTokenList(tokenList);
            int i = 0;
            for (MuaObject object: resultObjectList) {
                assertEquals(expectObjectList[i].getClass(), object.getClass()); //TODO: value equal, list equal
                ++i;
            }
        } catch (MuaException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            fail("every symbol should be recognized");
        }
    }

    @Test
    public void instructionToTokenList() {
        String testStr1 = " word1 word2 word3 [element1 element2 [element3 element4] element5] " +
                "[element6 element7 element8] word9";
        String[] correctResult1 = {"word1", "word2", "word3", "[element1 element2 [element3 element4] element5]",
                "[element6 element7 element8]", "word9"};
        testInstructionToTokenList(testStr1, correctResult1);

        String testStr2 = "word1 word2 word3 word4";
        String[] correctResult2 = {"word1", "word2", "word3", "word4"};
        testInstructionToTokenList(testStr2, correctResult2);

        String testStr3 = " word1 word2 word3 [element1 element2 [element3 element4] element5] " +
                "[element6 element7 element8]";
        String[] correctResult3 = {"word1", "word2", "word3", "[element1 element2 [element3 element4] element5]",
                "[element6 element7 element8]"};
        testInstructionToTokenList(testStr3, correctResult3);

        String testStr4 = "[element1 element2 [element3 element4] element5] " +
                "[element6 element7 element8] word9";
        String[] correctResult4 = {"[element1 element2 [element3 element4] element5]",
                "[element6 element7 element8]", "word9"};
        testInstructionToTokenList(testStr4, correctResult4);

        String testStr5 = "[element1 element2 [element3 element4] element5] ";
        String[] correctResult5 = {"[element1 element2 [element3 element4] element5]"};
        testInstructionToTokenList(testStr5, correctResult5);
    }

    private void testInstructionToTokenList(String testStr, String[] correctResult) {
        try {
            List<String> resultStr = Lexer.instructionToTokenList(testStr);
            testArrEqual(correctResult, resultStr);
        } catch (MuaException e) {
            //TODO
        }
    }

    private void testArrEqual(String[] correctStringArr, List<String> resultList) {
        int i = 0;
        for (String str: resultList) {
            assertEquals(correctStringArr[i], str);
            ++i;
        }
        assertEquals(correctStringArr.length, i);
    }
}