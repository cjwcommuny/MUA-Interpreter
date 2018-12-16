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
        if (object1.getClass() != MuaWord.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNC_NAME);
        }
        String word1 = ((MuaWord) object1).getValue();
        if (object2.getClass() == MuaWord.class) {
            String word2 = ((MuaWord) object2).getValue();
            return new MuaWord(word1 + word2);
        } else if (object2.getClass() == MuaNumber.class) {
            double number = ((MuaNumber) object2).getValue();
            return new MuaWord(word1 + number);
        } else if (object2.getClass() == MuaBool.class) {
            boolean bool = ((MuaBool) object2).getValue();
            return new MuaWord(word1 + bool);
        }
        throw new MuaArgumentTypeNotCompatibleException(FUNC_NAME);
    }
}
