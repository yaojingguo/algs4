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
