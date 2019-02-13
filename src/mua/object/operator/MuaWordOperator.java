package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaNumber;
import mua.object.primitive.MuaWord;

public class MuaWordOperator extends MuaOperator {
    static final String FUNC_NAME = "word";
    public MuaWordOperator() {
        super(FUNC_NAME, 2, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object1 = argumentList.get(0);
        MuaObject object2 = argumentList.get(1);
        String word1 = getString(object1);
        String word2 = getString(object2);
        return new MuaWord(word1 + word2);
    }

    private String getString(MuaObject object) throws MuaArgumentTypeNotCompatibleException {
        if (object.getClass() == MuaWord.class) {
            return ((MuaWord) object).getValue();
        } else if (object.getClass() == MuaNumber.class) {
            return Double.toString(((MuaNumber) object).getValue());
        } else if (object.getClass() == MuaBool.class) {
            return Boolean.toString(((MuaBool) object).getValue());
        }
        throw new MuaArgumentTypeNotCompatibleException(FUNC_NAME);
    }
}
