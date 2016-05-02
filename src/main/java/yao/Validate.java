package yao;

import edu.princeton.cs.algs4.StdOut;

public class Validate {
    public static void main(String[] args) {
        String regexp = args[0];
        String input = args[1];
        StdOut.println(input.matches(regexp));
    }
}
