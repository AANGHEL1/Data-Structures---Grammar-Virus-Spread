import edu.princeton.cs.algs4.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.HashMap;


public class Grammar {
    /**
     * Implements a single token (terminal or nonterminal, with {@code String} value).
     */
    public static class Token {
        private final String value;
        private final boolean terminal;
        /**
         * Create a new token with value {@code value} that's terminal if {@code terminal} is true.
         * @param value value of the token
         * @param terminal whether the token is terminal or not
         */
        public Token (String value, boolean terminal) { this.value = value; this.terminal = terminal; }

        /**
         * Returns whether the token is terminal.
         *
         * @return {@code true} if terminal, {@code false} otherwise.
         */
        public boolean isTerminal() { return terminal; }

        /**
         * Returns the value of the token.
         *
         * @return The value of the token.
         */
        public String getValue() { return value; }

        public boolean equals (Object o) {
          if (o == null) return false;
          if (o == this) return true;
          if (!(o instanceof Token)) return false;
          Token tok = (Token) o;
          return tok.value.equals (value) && tok.terminal == terminal;
        }

        public int hashCode () {
          return value.hashCode () ^ (terminal ? 1 : 0);
        }
    }

    private Map<Token, Set<List<Token>> > rules;
    private Set<Token> nonterminals;

    /**
     * Initializes an empty grammar.
     */
    public Grammar () {
      rules = new HashMap<> ();
      nonterminals = new HashSet<> ();
    }

    /**
     * Adds a new rule  
     *    {@code nonterm} → {@code rule}  
     * to the grammar.
     *
     * @param nonterm the nonterminal
     * @param rule the rule
     */
    public void addRule (Token nonterm, List<Token> rule) {
      if (nonterm.isTerminal ())
        throw new IllegalArgumentException ("nonterm should be nonterminal");

      Set<List<Token>> rulesForNonterm = rules.get (nonterm);

      if (rulesForNonterm == null) {
        rulesForNonterm = new HashSet<List<Token>> ();
        rules.put (nonterm, rulesForNonterm);
      }

      rulesForNonterm.add (rule);
      nonterminals.add (nonterm);
    }

    /**
     * Adds a new rule to the grammar by making a new nonterminal token using
     * {@code nonterm}, and interpreting {@code srule} as a list of tokens.  The
     * nonterminal is provided as {@code nonterm} and the rule is a {@code
     * String} where each letter is translated into a {@link Grammar.Token},
     * lowercases to terminals and uppercases to nonterminals.
     *
     * @param nonterm the nonterminal
     * @param srule the rule
     */
    public void addRule (String nonterm, String srule) {
      ArrayList<Token> rule = new ArrayList<>();
      for (int i = 0; i < srule.length (); ++i) {
        char c = srule.charAt (i);
        if (c >= 'a' && c <= 'z')
          rule.add (new Token (Character.toString (c), true));
        else if (c >= 'A' && c <= 'Z')
          rule.add (new Token (Character.toString (c), false));
        else
          throw new IllegalArgumentException ("character " + c + " invalid.");
      }
      addRule (new Token (nonterm, false), rule);
    }

    /**
     * Returns the rules associated with {@code token} which has to be a nonterminal token.
     *
     * @param token the nonterminal token
     * @return the lists of tokens
     */
    public Iterable<List<Token>> rulesFor (Token token) {
      if (token.isTerminal())
        throw new IllegalArgumentException ("token is terminal");

      Set<List<Token>> rulesForNonterm = rules.get (token);
      if (rulesForNonterm == null)
        return Collections.<List<Token>>emptySet ();
      return rulesForNonterm;
    }

    /**
     * Returns a directed graph of dependencies between nonterminals (for bonus
     * exercise!).  The set of vertices is the set of all nonterminals appearing
     * in the grammar, and there is an edge between two tokens {@code t1} and
     * {@code t2} if there is a rule for {@code t1} where {@code t2} appears.
     *
     * @return a digraph with tokens as vertex names.
     */
    public BetterSymbolDigraph<Token> dependencyGraph () {
      BetterSymbolDigraph<Token> res = new BetterSymbolDigraph<> (nonterminals);

      for (Map.Entry<Token, Set<List<Token>> > rule : rules.entrySet ()) {
        HashSet<Token> neighbors = new HashSet<> ();
        for (List<Token> r : rule.getValue ())
          for (Token t : r)
            if (!t.isTerminal ())
              neighbors.add (t);
        for (Token t : neighbors)
          res.digraph ().addEdge (res.indexOf (rule.getKey ()), res.indexOf (t));
      }
      return res;
    }
}
