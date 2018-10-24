import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        PrintStream outpuStream = System.out;
        Scanner s = new Scanner(inputStream);
        while (true) {
            System.out.print(InteractiveInterface.promptStr); //TODO: 重定向至其他流
            String instruction = s.nextLine();
            if (instruction.equals(InteractiveInterface.exitCommand)) { //TODO
                //TODO: add prompt when leave
                break;
            }
            outpuStream.println(instruction);
        }
    }
}
