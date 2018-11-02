package mua.object;

public class MuaBool extends MuaObject {
    private boolean value;

    public MuaBool(boolean value) {
        super(MuaType.bool);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        boolean anotherBool = ((MuaBool) obj).getValue();
        return value == anotherBool;
    }
}
