package yao;

import edu.princeton.cs.algs4.StdOut;

public class Validate {
    public static void main(String[] args) {
        String regexp = args[0];
        String input = args[1];
        StdOut.println(validate(regexp, input));
    }

    public static boolean validate(String regexp, String input) {
        StdOut.printf("regexp: %s\n", regexp);
        StdOut.printf("input: %s\n", input);
        return input.matches(regexp);
    }
}
