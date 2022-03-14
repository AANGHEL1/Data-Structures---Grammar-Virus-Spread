# Data-Structures---Grammar-Virus-Spred
A programming assignment from my data structures class.

1 Grammar productions (19 LLOC/10 pts)

1.1 Vocabulary

A token is a string that is either (flagged as) terminal or nonterminal.
A rule is a pair token/list-of-tokens written
     t → t1 t2 … tn
where t is a nonterminal token, and t1, t2, …, tn are (terminal or nonterminal) tokens. We say that this is a rule for t.
A grammar is a set of rules. It is acyclic if there isn’t a list of nonterminal tokens t1, t2, …, tn such that ti appears on the right-hand side of a rule for ti - 1, for any i, and t1 = tn.
1.2 Productions

First, for two sets of strings S1 and S2, define S1 ⋅ S2 to be the set of strings that can be obtained by concatenating any string from S1 with any string from S2.

Now, let G be an acyclic grammar and t be a token that appears in it. The production π(t) of t is defined as follows:

If t is terminal, then π(t) = {t}, the singleton containing t.
If t is nonterminal, then π(t) is the union, for all rules for t
     t → t1 t2 … tn
of π(t1) ⋅ π(t2) ⋅ … ⋅ π(tn).
1.3 Examples

Consider the acyclic grammar defined by the following set of rules:
    { S → aCb, S → aBb, C → z, B → tt, B → za, B → zCa }
where uppercase letters are nonterminal tokens, and lowercase letters are terminal tokens. Then:

The production of C is {z}
The production of B is {tt, za, zza}
The production of S is {azb, attb, azab, azzab}
1.4 Implementation & Assignment

Grammars are implemented in the Grammar class, tokens in Grammar.Token (note: the method dependencyGraph() is only needed for the bonus). You will implement the function π as above:

static public Set<String> allProductions (Grammar g, Grammar.Token token);
I strongly encourage you to write a function concat(set1, set2) that returns the set of strings that are obtained by concatenating strings from set1 with strings from set2.

1.5 Bonus (19 31 LLOC/5pts)

For an added 5 points, give an efficient implementation. In Test.java, there is a test that takes about 20 seconds to execute on a DFS type of implementation, but only half a second using a different approach. For this, you may want to use the dependencyGraph() method of grammars, and the Topological class from algs4.

2 Graph tests (10 pts)

In these exercises, you will write functions that take either a Graph or a Digraph and will return true if the input graph has a certain shape. We assume that no parallel edges are allowed, but self loops may appear. All graphs provided will have at least 3 vertices.

You are allowed and encouraged to use any algorithm/structure in algs4, and to reuse your own functions.

2.1 Directed tree (13 LLOC/2 pts)

A directed tree is a DAG such that all but one node has indegree one, and one node has indegree zero. You will write a function:

static public boolean isDirectedTree (Digraph graph);
that returns true if the input graph is a directed tree.

2.2 Complete graphs (22 LLOC/3 pts)

A tournament is a digraph in which between every two distinct vertices, there is one directed edge, and no self loop. You will write the functions:

static public boolean isComplete (Graph graph);
static public boolean isTournament (Digraph graph);
the first one to check that an undirected graph is complete (with no self loop), the second one to check that a digraph is a tournament.

2.3 Wheel (27 LLOC/5 pts)

A wheel with V nodes is an undirected graph that is made of one big cycle over V-1 nodes, and one node that is connected to every vertex (with no self loop). You will write the function:

static public boolean isWheel (Graph graph);
that checks whether the input graph is a wheel.

3 Virus spread (25 LLOC/10 pts)

Some computers are arranged on a grid, each computer being connected to its neighbors (left, right, up, down, but not diagonally). At time 0, some computers are infected by a virus, and at each time step, the virus spreads from infected computers to their neighbors. You are tasked to measure, given the network topology and the infected computers, the number of steps it will take for all computers to be infected.

The network is given as a 2-dimensional array int[][] network that is guaranteed to be a rectangle (i.e., each row is of the same length). The values in network describe the state of the network at time 0:

If network[i][j] == 0 then there is no computer at position (i, j).
If network[i][j] == 1 then there is a noninfected computer at position (i, j).
If network[i][j] == 2 then there is an infected computer at position (i, j).
You will write the function:

static public int virusSpread (int[][] network);
that returns the number of steps that it will take for all computers to be infected, or -1 if there is no computer in the network, or if at least one computer will never be infected.

I highly suggest making good use of algs4.
