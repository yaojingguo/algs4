package aofa;

import edu.princeton.cs.algs4.StdOut;

public class Fib implements Sequence {
    private final double[] F;

    public Fib(int maxN) {
        F = new double[maxN+1];
        F[0] = 0; F[1]= 1;
        for (int N = 2; N <= maxN; N++)
            F[N] = F[N-1] + F[N-2];
    }

    public double eval(int N) {
        return F[N];
    }

    public static void main(String[] args) {
        int maxN = Integer.parseInt(args[0]);
        Fib F = new Fib(maxN);
        for (int i = 0; i < maxN; i++)
            StdOut.println(F.eval(i));
    }
}
