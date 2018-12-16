package mua.namespace;

import mua.object.MuaObject;
import mua.object.operator.*;
import mua.object.primitive.MuaNumber;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
            new MuaReadOperator(), new MuaSubOperator(), new MuaThingOperator(), new MuaRepeatOperator(),
            new MuaOutputOperator(), new MuaStopOperator(), new MuaExportOperator(), new MuaIsNumberOperator(),
            new MuaIsWordOperator(), new MuaIsListOperator(), new MuaIsBoolOperator(), new MuaIsEmptyOperator(),
            new MuaRandomOperator(), new MuaSqrtOperator(), new MuaFloorOperator(), new MuaWordOperator(),
            new MuaIfOperator(), new MuaSentenceOperator(), new MuaListOperator(), new MuaJoinOperator(),
            new MuaFirstOperator(), new MuaLastOperator(), new MuaButFirstOperator(), new MuaButLastOperator(),
            new MuaWaitOperator(), new MuaSaveOperator(), new MuaLoadOperator(), new MuaEraseOperator(),
            new MuaPostAllOperator(), new MuaRunOperator(),
    };
    private static NamespaceStack namespaceStackSingleton = new NamespaceStack();

    private NamespaceStack() {
        addGlobalSpace();
        loadFunctor();
        loadConstants();
    }

    private void loadConstants() {
        Namespace globalNamespace = namespaceStack.peek();
        globalNamespace.put("pi", new MuaNumber(3.14159));
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

    public void exportLocalName() {
        Map<String, MuaObject> currentNamespaceMap = namespaceStack.peek().getNameObjectMap();
        Namespace globalNamespace = namespaceStack.firstElement();
        for (Map.Entry<String, MuaObject> entry: currentNamespaceMap.entrySet()) {
            globalNamespace.put(entry.getKey(), entry.getValue());
        }
    }

    public Namespace getCurrentNamespace() {
        return peek();
    }

    public void mergeNamespace(Namespace namespace) {
        Map<String, MuaObject> newMap = namespace.getNameObjectMap();
        Map<String, MuaObject> originalMap = peek().getNameObjectMap();
        originalMap.putAll(newMap);
    }

    public void eraseAllNameFromCurrentNamespace() {
        Map<String, MuaObject> map = peek().getNameObjectMap();
        map.clear();
    }

    public List<String> listAllNameFromCurrentNamespace() {
        Map<String, MuaObject> map = peek().getNameObjectMap();
        List<String> returnValue = new LinkedList<>();
        for (Map.Entry<String, MuaObject> entry: map.entrySet()) {
            returnValue.add(entry.getKey());
        }
        return returnValue;
    }
}
