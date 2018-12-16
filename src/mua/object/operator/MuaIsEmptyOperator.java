package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaWord;

public class MuaIsEmptyOperator extends MuaOperator {
    private final static String FUNCTION_NAME = "isempty";
    public MuaIsEmptyOperator() {
        super(FUNCTION_NAME, 1, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() == MuaWord.class) {
            MuaWord word = (MuaWord) object;
            return new MuaBool(word.isEmpty());
        } else if (object.getClass() == MuaList.class) {
            MuaList list = (MuaList) object;
            return new MuaBool(list.size() == 0);
        }
        throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
    }
}
