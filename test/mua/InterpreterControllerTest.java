package mua;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaDivideOrModuleZeroException;
import mua.exception.MuaException;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static java.lang.Math.abs;
import static org.junit.Assert.*;

public class InterpreterControllerTest {
    private final int LIMIT = 1000;
    private final int TEST_NUM = 100;
    private final int MAX_WORD_LENGTH = 30;

    @Test
    public void testNumberCompare() throws MuaException {
        for (int i = 0; i < TEST_NUM; ++i) {
            double op1 = generateNotZeroRandomNumber(LIMIT);
            double op2 = generateNotZeroRandomNumber(LIMIT);
            testInstruction("gt " + op1 + " " + op2, Boolean.toString(op1 > op2));
            testInstruction("lt " + op1 + " " + op2, Boolean.toString(op1 < op2));
        }

        for (int i = 0; i < TEST_NUM; ++i) {
            double op = generateNotZeroRandomNumber(LIMIT);
            testInstruction("eq " + op + " " + op, Boolean.toString(true));
        }
    }

    @Test
    public void testWordCompare() throws MuaException {
        for (int i = 0; i < TEST_NUM; ++i) {
            String op1 = "\"" + generateRandomWord();
            String op2 = "\"" + generateRandomWord();
            testInstruction("gt " + op1 + " " + op2, Boolean.toString(op1.compareTo(op2) > 0));
            testInstruction("lt " + op1 + " " + op2, Boolean.toString(op1.compareTo(op2) < 0));
        }

        for (int i = 0; i < TEST_NUM; ++i) {
            String op = "\"" + generateRandomWord();
            testInstruction("eq " + op + " " + op, Boolean.toString(true));
        }
    }

//    @Test(expected = MuaArgumentTypeNotCompatibleException.class)
//    public void testBadCompare() throws MuaException {
//        //TODO
//    }

    private String generateRandomWord() {
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random();
        int length = abs(rand.nextInt()) % (MAX_WORD_LENGTH + 1) + 1;
        while (stringBuilder.length() < length) {
            char c = (char)(rand.nextInt(26) + 'a');
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    @Test
    public void testArithmeticOperator() throws MuaException {
        for (int i = 0; i < TEST_NUM; ++i) {
            double op1 = generateNotZeroRandomNumber(LIMIT);
            double op2 = generateNotZeroRandomNumber(LIMIT);
            testInstruction("add " + op1 + " " + op2, Double.toString(op1+op2));
            testInstruction("sub " + op1 + " " + op2, Double.toString(op1-op2));
            testInstruction("mul " + op1 + " " + op2, Double.toString(op1*op2));
            testInstruction("div " + op1 + " " + op2, Double.toString(op1/op2));
            testInstruction("mod " + op1 + " " + op2, Double.toString(op1%op2));
        }
    }

    private double generateNotZeroRandomNumber(int limit) {
        double X;
        while ((X = Math.random() * limit) == 0) {}
        boolean isOdd = (int) (Math.random() * 10 % 10) % 2 == 0;
        return (isOdd) ? -X : X;
    }

    @Test(expected = MuaDivideOrModuleZeroException.class)
    public void testDivideModuleZero() throws MuaException {
        for (int i = 0; i < TEST_NUM; ++i) {
            double op1 = generateNotZeroRandomNumber(LIMIT);
            testInstruction("div " + op1 + " " + 0, "ShouldNotDisplay");
            testInstruction("mod " + op1 + " " + 0, "ShouldNotDisplay");
        }
    }

    @Test
    public void testBoolOperator() throws MuaException {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                boolean op1 = i == 0;
                boolean op2 = j == 0;
                testInstruction("and " + op1 + " " + op2, Boolean.toString(op1 && op2));
                testInstruction("or " + op1 + " " + op2, Boolean.toString(op1 || op2));
                testInstruction("not " + op1, Boolean.toString(!op1));
            }
        }
    }

    @Test
    public void testMakeAndThingOperator() throws MuaException {
        testInstruction("make \"aWord \"this_is_a_word :aWord", "\"this_is_a_word");
        testInstruction("make \"aWord \"this_is_a_word thing \"aWord", "\"this_is_a_word");
        testInstruction("make \"aList [make \"anotherList [\"word1 5 true]] :aList",
                "[make, \"anotherList, [\"word1, 5.0, true]]");
        testInstruction("make \"aList [make \"anotherList [\"word1 5 true]] thing \"aList",
                "[make, \"anotherList, [\"word1, 5.0, true]]");
    }

    @Test
    public void testMakeAndPrintOperator() throws MuaException {
        testInstruction("make \"aWord \"this_is_a_word print thing \"aWord print \"aWord",
                "\"this_is_a_word\n\"aWord");
        testInstruction("make \"aList [make \"anotherList [\"word1 5 true]] print thing \"aList",
                "[make, \"anotherList, [\"word1, 5.0, true]]");
        testInstruction("print 3", Double.toString(3));
        testInstruction("print \"word", "\"word");
    }

    private void testInstruction(String instruction, String expected) throws MuaException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CommandLineInterface.outputStream = new PrintStream(stream);
        InterpreterController.testOneInstruction(instruction);
        System.out.flush();
        String result = stream.toString().trim();
        assertEquals(expected, result);
    }
}