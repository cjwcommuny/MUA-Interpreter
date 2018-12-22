package mua.object.operator;

import mua.InstructionRunner;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaRepeatNumberNegativeException;
import mua.exception.MuaStopExecutionException;
import mua.object.MuaObject;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaNone;
import mua.object.primitive.MuaNumber;

import java.util.List;

public class MuaRepeatOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "repeat";
    public MuaRepeatOperator() {
        super(FUNCTION_NAME, 2, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object1 = argumentList.get(0);
        MuaObject object2 = argumentList.get(1);
        if (object1.getClass() != MuaNumber.class || object2.getClass() != MuaList.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
        }
        int repeatCount = (int) ((MuaNumber) object1).getValue();
        if (repeatCount < 0) {
            throw new MuaRepeatNumberNegativeException();
        }
        List<MuaObject> instruction = ((MuaList) object2).getList();
        InstructionRunner instructionRunner = new InstructionRunner(instruction, InstructionRunner.Mode.SCRIPT);
        for (int i = 0; i < repeatCount; ++i) {
            try {
                instructionRunner.run();
            } catch (MuaStopExecutionException e) {
                break;
            }
        }
        return new MuaNone();
    }
}
