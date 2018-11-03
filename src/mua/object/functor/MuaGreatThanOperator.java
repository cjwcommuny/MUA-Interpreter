package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.object.*;

public class MuaGreatThanOperator extends MuaFunctor {
    public static final String FUNC_NAME = "gt";
    private static final int ARGUMENT_NUM = 2;
    public MuaGreatThanOperator() {
        super(FUNC_NAME, true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException {
        if (argumentList.size() != getArgumentNum()) {
            throw new MuaArgumentNumNotCompatibleException();
        }
        MuaObject op1 = argumentList.get(0);
        MuaObject op2 = argumentList.get(1);

        if (op1.getMuaType() == MuaType.number && op2.getMuaType() == MuaType.number) {
            return new MuaBool(((MuaNumber) op1).compareTo((MuaNumber) op2) > 0);
        } else if (op1.getMuaType() == MuaType.word && op2.getMuaType() == MuaType.word) {
            return new MuaBool(((MuaWord) op1).compareTo((MuaWord) op2) > 0);
        } else {
            throw new MuaArgumentTypeNotCompatibleException();
        }
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}
