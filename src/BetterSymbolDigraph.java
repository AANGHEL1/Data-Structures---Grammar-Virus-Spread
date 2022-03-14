import edu.princeton.cs.algs4.*;
import java.util.ArrayList;

public class BetterSymbolDigraph<T> {
    private ArrayList<T> keys;           // index  -> T
    private Digraph graph;           // the underlying digraph

    /**  
     * Initializes a digraph from a set of vertex names.
     * @param vertices the list of vertices.
     */
    public BetterSymbolDigraph(Iterable<T> vertices) {
      keys = new ArrayList<> ();
      int i = 0;

      for (T t : vertices) {
        keys.add (t);
        ++i;
      }
      graph = new Digraph (i);
    }

    /**
     * Does the digraph contain the vertex named {@code t}?
     * @param t the name of a vertex
     * @return {@code true} if {@code t} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(T t) {
        return keys.contains (t);
    }

    /**
     * Returns the integer associated with the vertex named {@code t}.
     * @param t the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code t}
     */
    public int indexOf(T t) {
        return keys.indexOf (t);
    }
    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public T nameOf(int v) {
      return keys.get (v);
    }

    /**
     * Returns the digraph associated with the symbol graph. It is the client's responsibility
     * not to mutate the digraph.
     *
     * @return the digraph associated with the symbol digraph
     */
    public Digraph digraph() {
        return graph;
    }
}
