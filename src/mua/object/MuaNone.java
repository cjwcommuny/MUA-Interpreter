package mua.object;

public class MuaNone extends MuaObject {
    public MuaNone() {

    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && getClass() == obj.getClass());
    }

    @Override
    public String toString() {
        return "";
    }
}
