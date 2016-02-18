package aofa;

import edu.princeton.cs.algs4.StdOut;

public class QuickSeq implements Sequence {
    private final double[] c;

    public QuickSeq(int maxN) {
        c = new double[maxN+1];
        c[0] = 0;
        for (int N = 1; N <= maxN; N++)
            c[N] = (N+1)*c[N-1]/N + 2;
    }

    public double eval(int N) {
        return c[N];
    }

    private static void info(int maxN) {
        QuickSeq F = new QuickSeq(maxN);
        for (int i = 0; i < maxN; i++)
            StdOut.println(F.eval(i));
    }

    public static void main(String[] args)
    {
        int maxN = Integer.parseInt(args[0]);
        QuickSeq q = new QuickSeq(maxN);
        Values.show(q, maxN);
    }
}
