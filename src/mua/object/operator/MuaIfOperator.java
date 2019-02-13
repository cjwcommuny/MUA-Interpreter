package mua.object.operator;

import mua.InstructionRunner;
import mua.exception.MuaArgumentTypeNotCompatibleException;
import mua.exception.MuaException;
import mua.object.MuaObject;
import mua.object.primitive.MuaBool;
import mua.object.primitive.MuaList;
import mua.object.primitive.MuaNone;

public class MuaIfOperator extends MuaOperator {
    private static final String FUNCTION_NAME = "if";
    public MuaIfOperator() {
        super(FUNCTION_NAME, 3, true);
    }

    @Override
    public MuaObject operate(MuaObject instanceReturnValue, ArgumentList argumentList) throws MuaException {
        MuaObject object1 = argumentList.get(0);
        MuaObject object2 = argumentList.get(1);
        MuaObject object3 = argumentList.get(2);
        if (object1.getClass() != MuaBool.class
                || object2.getClass() != MuaList.class
                || object3.getClass() != MuaList.class) {
            throw new MuaArgumentTypeNotCompatibleException(FUNCTION_NAME);
        }
        boolean condition = ((MuaBool) object1).getValue();
        MuaList instruction1 = (MuaList) object2;
        MuaList instruction2 = (MuaList) object3;
        InstructionRunner instructionRunner;
        if (condition) {
             instructionRunner =
                    new InstructionRunner(instruction1.getList(), InstructionRunner.Mode.SCRIPT);
        } else {
            instructionRunner = new InstructionRunner(instruction2.getList(), InstructionRunner.Mode.SCRIPT);
        }
        instructionRunner.run();
        if (instructionRunner.getReturnValue() != null) {
            return instructionRunner.getReturnValue();
        }
        return new MuaNone();
    }
}
