package aofa;

import edu.princeton.cs.algs4.StdDraw;

public class Values {

    public static void show(Sequence f, int maxN) {
        double max = 0;
        for (int N = 0; N <= maxN; N++)
            if (f.eval(N)>max) max = f.eval(N);

        for (int N = 0; N <= maxN; N++) {
            double x = 1.0*N/maxN;
            double y = 1.0*f.eval(N)/max;
            StdDraw.filledCircle(x, y, .002);
        }
        StdDraw.show();
    }
}
