__Left for reference. Use https://github.com/yaojingguo/algs4-1 instead.__

Code for Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne

## Memo
DirectedCycle uses onStack. But Cycle doesn't. The reason is that for undirected
graph, `marked = true` implies `onStack = true`. But digraph does not has such a
property. For example, here is an digraph


```
a-->b-->c-->d
```

Assume that c is the source for the first dfs invocation. This dfs marks b, c
and d. Then the second dfs takes a as source. For this dfs, none of b, c and d
is onStack.

Quick3way is unstable.

```
at the start iteration 1: 2    9     9_
                          i          
                          lt         gt
                          lo         hi


at the start iteration 2: 2    9     9_
                               i          
                          lt         gt
                          lo         hi

at the start iteration 3: 2    9_     9
                               i          
                          lt   gt
                          lo         hi

       after iteration 3: 2    9_     9
                               i          
                          lt   
                          gt
                          lo         hi
```
