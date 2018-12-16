package mua.namespace;

import mua.object.MuaObject;
import mua.object.operator.*;

import java.util.Stack;

//TODO: singlton?
public class NamespaceStack {
    private Stack<Namespace> namespaceStack = new Stack<>();
    private Namespace generalNamespace = new Namespace();
    private static MuaOperator[] muaOperators = new MuaOperator[]{
            new MuaAddOperator(), new MuaAndOperator(), new MuaDivideOperator(), new MuaEqualOperator(),
            new MuaEraseOperator(), new MuaExitOperator(), new MuaGreatThanOperator(), new MuaIsNameOperator(),
            new MuaLessThanOperator(), new MuaMakeOperator(), new MuaModuloOperator(), new MuaMultiplyOperator(),
            new MuaNotOperator(), new MuaOrOperator(), new MuaPrintOperator(), new MuaReadListOperator(),
            new MuaReadOperator(), new MuaSubOperator(), new MuaThingOperator()
    };
    private static NamespaceStack namespaceStackSingleton = new NamespaceStack();

    private NamespaceStack() {
        addGlobalSpace();
        loadFunctor();
    }

    private void addGlobalSpace() {
        namespaceStack.push(new Namespace());
    }

    public static NamespaceStack getInstance() {
        return namespaceStackSingleton;
    }

    private void loadFunctor() {
        Namespace namespace = generalNamespace;
        for (MuaOperator functor: muaOperators) {
            namespace.put(functor.toString(), functor);
        }
    }

    public MuaObject getObject(String name) {
        Namespace topNamespace = peek();
        MuaObject object = topNamespace.get(name);
        if (object == null) {
            object = generalNamespace.get(name);
        }
        return object;
    }

    private boolean empty() {
        return namespaceStack.empty();
    }

    private Namespace peek() {
        return (Namespace) namespaceStack.peek();
    }

    private Namespace pop() {
        return (Namespace) namespaceStack.pop();
    }

    private void push(Namespace namespace) {
        namespaceStack.push(namespace);
    }

    public MuaObject remove(String name) {
        return peek().remove(name);
    }

    public void put(String name, MuaObject object) {
        peek().put(name, object);
    }

    public void namespaceBegin() {
        push(new Namespace());
    }

    public void namespaceEnd() {
        pop();
    }
}
