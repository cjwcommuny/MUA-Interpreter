package mua.object.operator;

import mua.exception.MuaException;
import mua.lexer.NumericTypeHandler;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

public class MuaIsWordOperator extends MuaOperator {
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        Class aClass = argumentList.get(0).getClass();
        boolean isWord = aClass == MuaWord.class || aClass == MuaNumber.class || aClass == MuaBool.class;
        return new MuaBool(isWord);
    }

    public MuaIsWordOperator() {
        super("isword", 1, true);
    }
}