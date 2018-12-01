package mua;

import mua.exception.MuaException;
import mua.object.MuaObject;

import java.util.List;

public class InstructionRunner {
    private List<MuaObject> operationList;

    public InstructionRunner(List<MuaObject> operationList) {
        this.operationList = operationList;
    }

    public List<String> run() throws MuaException {

    }
}
