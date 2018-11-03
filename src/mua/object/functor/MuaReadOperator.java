package mua.object.functor;

import mua.Interpreter;
import mua.exception.MuaException;
import mua.exception.MuaValidInputException;
import mua.object.*;

import java.util.Scanner;

public class MuaReadOperator extends MuaFunctor {
    public static final String FUNC_NAME = "read";
    private static final int ARGUMENT_NUM = 0;

    public MuaReadOperator() {
        super(FUNC_NAME, ARGUMENT_NUM, true);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }

    @Override
    public MuaObject operate(ArgumentList argumentList) throws MuaException {
        checkArgumentNum(argumentList);
        Scanner s = new Scanner(Interpreter.getInputStream());
        String token = s.next();
        //todo: not good practise, handle token in two different places
        if (Interpreter.isWord(token)) {
            return new MuaWord(token.substring(1));
        } else if (Interpreter.isNumeric(token)) {
            return new MuaNumber(Double.parseDouble(token));
        } else {
            throw new MuaValidInputException();
        }
    }
}
