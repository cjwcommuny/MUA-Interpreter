package mua;//
// mua.FrontEnd
// Created by cjw on 2018/10/24.

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

class FrontEnd {
	private static final String GET_INSTRUCTION_PROMPT = ConsoleColors.GREEN + "MUA> " + ConsoleColors.RESET;
	private static final String CONTINUE_INPUT_PROMPT = "...";
	private static final String EXIT_PROMPT = "Leave MUA InterpreterController.";

    private static InputStream inputStream = System.in;
    private static Scanner s = new Scanner(inputStream);
    private static PrintStream outputStream = System.out;

    static void quitInterpreter() {
        outputStream.println(EXIT_PROMPT);
    }

    private static void printInstructionPrompt() {
        outputStream.print(GET_INSTRUCTION_PROMPT);
    }

    private static void printContinuePrompt() {
        outputStream.print(CONTINUE_INPUT_PROMPT);
    }

    static String getNextInstruction() {
        printInstructionPrompt();
        return s.nextLine();
    }

    static void printResults(List<String> returnResults) {
        for (String result: returnResults) {
            if (!"".equals(result)) {
                outputStream.println(result);
            }
        }
    }

    static void print(Object message) {
        if (message != null && !"".equals(message.toString())) {
            outputStream.println(message);
        }
    }

    static String getNextLine() {
        printContinuePrompt();
        return s.nextLine();
    }

    static String getNextToken() {
        printContinuePrompt();

        //handle newline symbol
        return s.nextLine().trim();
    }

    class ConsoleColors {
		// Reset
		public static final String RESET = "\033[0m";  // Text Reset

		// Regular Colors
		public static final String BLACK = "\033[0;30m";   // BLACK
		public static final String RED = "\033[0;31m";     // RED
		public static final String GREEN = "\033[0;32m";   // GREEN
		public static final String YELLOW = "\033[0;33m";  // YELLOW
		public static final String BLUE = "\033[0;34m";    // BLUE
		public static final String PURPLE = "\033[0;35m";  // PURPLE
		public static final String CYAN = "\033[0;36m";    // CYAN
		public static final String WHITE = "\033[0;37m";   // WHITE

		// Bold
		public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
		public static final String RED_BOLD = "\033[1;31m";    // RED
		public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
		public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
		public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
		public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
		public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
		public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

		// Underline
		public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
		public static final String RED_UNDERLINED = "\033[4;31m";    // RED
		public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
		public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
		public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
		public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
		public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
		public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

		// Background
		public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
		public static final String RED_BACKGROUND = "\033[41m";    // RED
		public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
		public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
		public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
		public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
		public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
		public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

		// High Intensity
		public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
		public static final String RED_BRIGHT = "\033[0;91m";    // RED
		public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
		public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
		public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
		public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
		public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
		public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

		// Bold High Intensity
		public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
		public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
		public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
		public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
		public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
		public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
		public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
		public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

		// High Intensity backgrounds
		public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
		public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
		public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
		public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
		public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
		public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
		public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
		public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
	}
}