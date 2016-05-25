/******************************************************************************
 *  Compilation:  javac Manber.java
 *  Execution:    java Manber < text.txt
 *  Dependencies: In.java
 *  
 *  Reads a text corpus from stdin and suffix sorts it in subquadratic
 *  time using a variant of Manber's algorithm.
 *
 *  NOTE: I THINK THIS IS CYCLIC SUFFIX SORTING
 *
 ******************************************************************************/

package edu.princeton.cs.algs4;

public class Manber {
    private int N;               // length of input string
    private String text;         // input text
    private int[] index;         // offset of ith string in order: sorted index => suffix no
    private int[] rank;          // rank of ith string: suffix no => rank
    private int[] newrank;       // rank of ith string (temporary)
    private int offset;
   
    public Manber(String s) {
        N    = s.length();
        text = s;
        index   = new int[N+1];
        rank    = new int[N+1];
        newrank = new int[N+1];

        // sentinels
        index[N] = N; 
        rank[N] = -1;

        msd();
        show();
        doit();
    }

    // do one pass of msd sorting by rank at given offset
    private void doit() {
        for (offset = 1; offset < N; offset += offset) {
            StdOut.println("offset = " + offset);

            int count = 0;
            for (int i = 1; i <= N; i++) {
                if (rank[index[i]] == rank[index[i-1]]) count++;
                else if (count > 0) {
                    // sort
                    int left = i-1-count;
                    int right = i-1;
                    quicksort(left, right);

                    // now fix up ranks
                    int r = rank[index[left]];
                    for (int j = left + 1; j <= right; j++) {
                        if (less(index[j-1], index[j]))  {
                            r = rank[index[left]] + j - left; 
                        }
                        newrank[index[j]] = r;
                    }

                    // copy back - note can't update rank too eagerly
                    for (int j = left + 1; j <= right; j++) {
                        rank[index[j]] = newrank[index[j]];
                    }

                    count = 0;
                }
            }
        }
    }

    // sort by leading char, assumes extended ASCII (256 values)
    private void msd() {
        // calculate frequencies
        int[] freq = new int[256];
        for (int i = 0; i < N; i++)
            freq[text.charAt(i)]++;

        // calculate cumulative frequencies
        int[] cumm = new int[256];
        for (int i = 1; i < 256; i++)
            cumm[i] = cumm[i-1] + freq[i-1];

        // compute ranks
        for (int i = 0; i < N; i++)
            rank[i] = cumm[text.charAt(i)];

        // sort by first char
        for (int i = 0; i < N; i++)
            index[cumm[text.charAt(i)]++] = i;
    }


    // for debugging
    public void show() {
        for (int j = 0; j < N+1; j++) StdOut.print(" ");
        StdOut.println("j, rank[index[j]], index[j]");
        for (int i = 0; i < N; i++) {
            String s = text.substring(index[i]);
            for (int j = 0; j < index[i]; j++) s += " ";
            StdOut.println(s + " " + i + " " + rank[index[i]] + " " + index[i]);
        }
        StdOut.println();
    }


    // test client
    public static void main(String[] args) {
        In in = new In();
        String s = in.readAll();
        Manber m = new Manber(s);
        m.show();
    }


/******************************************************************************
 *  Helper functions for comparing suffixes.
 ******************************************************************************/

  /**********************************************************************
   * Is the substring text[v..N] lexicographically less than the
   * substring text[w..N] ?
   **********************************************************************/
    private boolean less(int v, int w) {
        return rank[v + offset] < rank[w + offset];
    }



/******************************************************************************
 *  Quicksort code from Sedgewick 7.1, 7.2.
 ******************************************************************************/

    // swap pointer sort indices
    private void exch(int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
    }


    // SUGGEST REPLACING WITH 3-WAY QUICKSORT SINCE ELEMENTS ARE
    // RANKS AND THERE MAY BE DUPLICATES
    void quicksort(int lo, int hi) { 
        if (hi <= lo) return;
        int i = partition(lo, hi);
        quicksort(lo, i-1);
        quicksort(i+1, hi);
    }

    int partition(int lo, int hi) {
        int i = lo-1, j = hi;
        int v = index[hi];

        while (true) { 

            // find item on left to swap
            while (less(index[++i], v))
                if (i == hi) break;   // redundant

            // find item on right to swap
            while (less(v, index[--j]))
                if (j == lo) break;

            // check if pointers cross
            if (i >= j) break;

            exch(i, j);
        }

        // swap with partition element
        exch(i, hi);

        return i;
    }
}
