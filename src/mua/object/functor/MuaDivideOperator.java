package mua.object.functor;

import mua.exception.MuaArgumentNumNotCompatibleException;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaDivideOrModuleZeroException;
import mua.object.*;

public class MuaDivideOperator extends MuaFunctor {
    public static final String FUNC_NAME = "div";
    private static final int ARGUMENT_NUM = 2;
    private static final double epsilon = 0.0001; //todo: the location should be changed
    public MuaDivideOperator() {
        super(FUNC_NAME, ARGUMENT_NUM,true);
    }

    @Override
    public MuaObject operate(ArgumentList argumentList)
            throws MuaArgumentNumNotCompatibleException, MuaArgumentTypeNotCompatibleException, MuaDivideOrModuleZeroException {
        checkArgumentNum(argumentList);
        double op1, op2;
        try {
            op1 = ((MuaNumber) argumentList.get(0)).getValue();
            op2 = ((MuaNumber) argumentList.get(1)).getValue();
        } catch (ClassCastException e) {
            throw new MuaArgumentTypeNotCompatibleException();
        }
        if (op2 < epsilon && op2 > -epsilon) {
            throw new MuaDivideOrModuleZeroException();
        }
        return new MuaNumber(op1 / op2);
    }

    @Override
    public int getArgumentNum() {
        return ARGUMENT_NUM;
    }
}

