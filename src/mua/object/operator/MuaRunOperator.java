package mua.object.operator;

import mua.InstructionRunner;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.exception.MuaStopExecutionException;
import mua.object.MuaObject;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaNone;

public class MuaRunOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "run";
    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object = argumentList.get(0);
        if (object.getClass() != MuaList.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
        }
        MuaList instruction = (MuaList) object;
        InstructionRunner instructionRunner =
                new InstructionRunner(instruction.getList(), InstructionRunner.Mode.SCRIPT);
        try {
            instructionRunner.run();
        } catch (MuaStopExecutionException e) {
            //do nothing
        }

        return instructionRunner.getReturnValue();
    }

    public MuaRunOperator() {
        super(FUNCTION_NAME, 1, false);
    }
}
