package mua.namespace;

import mua.object.MuaObject;
import mua.object.functor.*;

import java.util.Stack;

//TODO: singlton?
public class NamespaceStack {
    private Stack<Namespace> namespaceStack = new Stack<>();
    private static MuaFunctor[] muaFunctors = new MuaFunctor[]{
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
        Namespace namespace = namespaceStack.peek();
        for (MuaFunctor functor: muaFunctors) {
            namespace.put(functor.toString(), functor);
        }
    }

    public MuaObject getObject(String name) {
        Namespace topNamespace = peek();
        return topNamespace.get(name);
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

    public void remove(String name) {
        peek().remove(name);
    }

    public void put(String name, MuaObject object) {
        peek().put(name, object);
    }
}
