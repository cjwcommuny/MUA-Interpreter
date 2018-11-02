package mua;

import java.util.Map;
import java.util.TreeMap;
import mua.object.*;

public class Interpreter {
    DataTable dataTable = new DataTable();

    static String interpret(String instructionStr) {
        instructionStr = removeComment(instructionStr);
        String[] instrcutionArr = strToList(instructionStr);
        for (String token: instrcutionArr) {

        }
        //TODO
    }

    static private String[] strToList(String instruction) {
        return instruction.trim().split("\\s+");
    }

    static private int findCommentBeginner(String instruction) {
        int firstSlashIndex = instruction.indexOf('/');
        if (firstSlashIndex == -1) {
            return -1;
        }
        if (instruction.charAt(firstSlashIndex + 1) == '/') {
            return firstSlashIndex;
        } else {
            return -1;
        }
    }

    static private String removeComment(String instructionWithComment) {
        int commentBeginnerIndex = findCommentBeginner(instructionWithComment);
        if (commentBeginnerIndex == -1) {
            return instructionWithComment;
        }
        return instructionWithComment.substring(0, commentBeginnerIndex);
    }
}

//TODO:Singlton
class DataTable {
    private Map<String, MuaObject> dataTable = new TreeMap<>();

    MuaObject getObject(String name) {
        return dataTable.get(name);
    }

    void updateObject(String name, MuaObject value) {
        dataTable.put(name, value);
    }
}