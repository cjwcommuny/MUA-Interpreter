package mua;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        PrintStream outpuStream = System.out;
        Scanner s = new Scanner(inputStream);
        while (true) {
            outpuStream.print(InteractiveInterface.promptStr); //TODO: 重定向至其他流
            String instruction = s.nextLine();
            if (instruction.equals(InteractiveInterface.exitCommand)) { //TODO
                outpuStream.println(InteractiveInterface.exitPrompt);
                break;
            }
            outpuStream.println(Interpreter.interpret(instruction));
        }
    }
}
