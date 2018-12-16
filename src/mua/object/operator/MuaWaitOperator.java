package mua.object.operator;

import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaInterruptSleepException;
import mua.object.MuaObject;
import mua.object.primitive.MuaNone;
import mua.object.primitive.MuaNumber;

import java.util.concurrent.TimeUnit;

public class MuaWaitOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "wait";
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() != MuaNumber.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
        }
        int time = (int) ((MuaNumber) object).getValue();
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            throw new MuaInterruptSleepException();
        }
        return new MuaNone();
    }

    public MuaWaitOperator() {
        super(FUNCTION_NAME, 1, true);
    }
}
