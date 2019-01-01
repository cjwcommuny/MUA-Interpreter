package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

public class MuaButLastOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "butlast";
    public MuaButLastOperator() {
        super(FUNCTION_NAME, 1, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() == MuaList.class) {
            return ((MuaList) object).butLast();
        } else if (object.getClass() == MuaWord.class) {
            return ((MuaWord) object).butLast();
        } else if (object.getClass() == MuaNumber.class) {
            return new MuaWord((MuaNumber) object).butLast();
        } else if (object.getClass() == MuaBool.class) {
            return new MuaWord((MuaBool) object).butLast();
        }
        throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
    }
}
