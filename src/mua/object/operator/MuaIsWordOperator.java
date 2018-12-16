package mua.object.operator;

import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaWord;

public class MuaIsWordOperator extends MuaOperator {
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        boolean isWord = argumentList.get(0).getClass() == MuaWord.class;
        return new MuaBool(isWord);
    }

    public MuaIsWordOperator() {
        super("isword", 1, true);
    }
}