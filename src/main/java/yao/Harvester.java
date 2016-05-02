package yao;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Harvester {
    public static void main(String[] args) {
        System.out.printf("regexp: %s\n", args[0]);
        System.out.printf("in: %s\n", args[1]);
        String regexp = args[0];
        In in = new In(args[1]);
        String input = in.readAll();
        harvest(regexp, input);
    }

    public static void harvest(String regexp, String input) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            StdOut.println(matcher.group());
        }
    }
}
