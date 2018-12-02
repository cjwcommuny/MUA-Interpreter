package mua.object;

public class MuaDereference extends MuaObject {
    private String word;

    public MuaDereference(String word) {
        //TODO
        super(MuaType.dereference);
        this.word = word;
    }
}
