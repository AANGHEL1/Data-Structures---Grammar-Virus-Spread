import edu.princeton.cs.algs4.*;


import java.util.Set;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class ProgrammingAssignment3 {
    //////////////////////////////////////////// EXERCISE 1: GRAMMAR PRODUCTIONS
	
	
	static public int countVertex(Grammar g, Grammar.Token token, int c)
	{
		for(List<Grammar.Token> tokens: g.rulesFor(token))
			for( int j=0;j<tokens.size();j++)
				if(tokens.get(j).isTerminal()==false)
				{
					c++;
					c=countVertex(g,tokens.get(j),c);
				}
		
		return c;
	}

	static public Set<String> allProductions2 (Grammar g, Grammar.Token token) {
		
		int c=0;
		c=countVertex(g,token,c);
		BetterSymbolDigraph<Grammar.Token> G=g.dependencyGraph();
		TopologicalX top=new TopologicalX (G.digraph());
		StdOut.print(top.order()+"\n");
		ListIterator<Integer> i=(ListIterator<Integer>) top.order().iterator();
		return null;
	}

	static public Set<String> concat(Set<String> s1, Set<String> s2)
	{
		
		Set<String> c=new HashSet<String>();
		if(s1.isEmpty()==true)
		{
			c.addAll(s2);
			return c;
		}
		String con=new String();
 		for(String i: s1)
 		{
			for(String j: s2)
			{
				con=i+j;
				c.add(con);
				
			}	
 		}
 		return c;
	}
	
	
	
	
	
    static public Set<String> allProductions (Grammar g, Grammar.Token token) {
    	Set<String> setOfTerminals= new HashSet<String>();
    	if(token.getValue()==null)
    		return setOfTerminals;
    	if(token.isTerminal())
    		setOfTerminals.add(token.getValue());	
    	else
    	{
    		for(List<Grammar.Token> tokens: g.rulesFor(token))
    		{
    			if(tokens.size()==1)
				{
					setOfTerminals.addAll(allProductions(g,tokens.get(0)));
				}
    			else
    			{
    				Set<String> count=new HashSet<String>();
    			for( int j=0;j<tokens.size();j++)
    				count=concat(count,allProductions(g,tokens.get(j)));
    			setOfTerminals.addAll(count);
    			}
    		}
    	}
    	
      return setOfTerminals;
    }

    //////////////////////////////////////////////////// EXERCISE 2: GRAPH TESTS
    static public boolean isDirectedTree (Digraph graph) {
    	DirectedCycle inGraph=new DirectedCycle(graph);
    	int c=0;
    	if(inGraph.hasCycle())
    		return false;
    	else
    	{
    		for(int i=0;i<graph.V();i++)
    			if(graph.indegree(i)>1) return false;
    			else
    				if(graph.indegree(i)==1) c++;
    	}
    	if(c==graph.V()-1)
    		return true;
    	else
    		return false;
    }

    static public boolean isTournament (Digraph graph) {
    	int c=0;
    	int c2=0;
    	int N=graph.V();
    	int[] counter= {};
    	counter=new int[N];
    	boolean is=false;
    	for(int i=0;i<graph.V();i++)
    	{
    			for(int j:graph.adj(i))
    			{
    				if(j==i) return false;
    			}	
    			if(graph.indegree(i)+graph.outdegree(i)==N-1)
					c++;
    				
    	}
    	if(c==N)
			return true;
		else
			return false;
    }

    static public boolean isComplete (Graph graph) {
    	int c=0;
    	int N=graph.V();
    	for(int i=0;i<graph.V();i++)
    	{
    			for(int j:graph.adj(i))
    			{
    				if(j==i) return false;
    				else
    					c++;
    			}		
    	}
    	if(c==N*(N-1))/////number of edges in a graph
			return true;
		else
			return false;
    }
    

    static public boolean isWheel (Graph graph) {
    	int c=0,c2=0,v=0;
    	//////case graph has 1 vertex
    	if(graph.V()==1) return false;
    	//////case graph has 2 vertices
    	if(graph.V()==2)
    	{
    		while(v<graph.V())
    		{
    			if(graph.degree(v)==1)
    				c++;
    			if(graph.degree(v)==3)
    				c2++;
    			v++;
    		}
    		if(c==1 && c2==1)
    			return true;
    		else 
    			return false;
    	}
    	
    	////General Case/////
    	if(graph.E()==2*(graph.V()-1))
    	{

    		for(v=0;v<graph.V();v++)
    		{
    			for(int i: graph.adj(v))
    				if(v==i) return false;
    			if(graph.degree(v)==3)
    				c++;
    			if(graph.degree(v)==graph.V()-1)
    				c2++;
    		}
    		if(graph.V()==4)
    			if(c==4)
    				return true;
    		if(c==graph.V()-1 && c2==1)
    			return true;
    		return false;
    	}
    	else 
    		return false;
    }

    /////////////////////////////////////////////////// EXERCISE 3: VIRUS SPREAD
    
    static public List<Integer> listOfNeigh(int[][] network, List<Integer> adj, int i)
    {
    	adj.clear();
    	for(int j=0;j<network[i].length;j++)
    	{
    		if(network[i][j]==1 || network[i][j]==2)
    			adj.add(j);
    	}
    	return adj;
    }
    
    

    static public int virusSpread (int[][] network) {
    	List<Integer> sources=new ArrayList<Integer>();
    	int v=0;
    	List<Integer> V=new ArrayList<Integer>();
    	int n=0;
    	int c=0;
    	for(int i=0;i<network.length;i++)
    		for(int j=0;j<network[i].length;j++)
    		{
    			if(network[i][j]==2)
    				sources.add(v);/////sources
    			V.add(v,n);////////number of vertices
    			if(network[i][j]==2 || network[i][j]==1)
    				c++;
    			n++;
    			v++;
    		}
    	///////////////Only one vertex that might be infected/////////////
    	
    	if(c==1)
    		return 0;
    	///////////////Only one vertex that is not infected///////////////
    	
    	if(V.size()==1) 
    		return -1;
    	
    	
    	//////////////////Building Graph/////////////////////
    	Graph graph=new Graph(V.size());
    	int w=0;
    	v=0;
    	int count=0;
    	for(int i=0;i<network.length;i++)
    		for(int j=0;j<network[i].length;j++)
    		{
    			if(network.length!=1)
    			{
    				if(network[i][j]==1 || network[i][j]==2)
    				{
    					if(i==0)
    					{
    						if(j==0)
    						{
    							if(network[i].length!=1)
    							{
    								if(network[i][j+1]==1 || network[i][j+1]==2)
    									graph.addEdge(v, V.get(count+1));
    								if(network[i+1][j]==1 || network[i+1][j]==2)
    									graph.addEdge(v, V.get(count+network[i].length));
    								if(network[i][j]==1)
    									if(network[i][j+1]==0 && network[i+1][j]==0)
    										return -1;
    							}
    							else
    								if(network[i+1][j]==1 || network[i+1][j]==2)
    									graph.addEdge(v, V.get(count+network[i].length));

    						}

    						else if(j==network[i].length-1)
    						{
    							if(network[i][j-1]==1 || network[i][j-1]==2)
    								graph.addEdge(v, V.get(count-1));
    							if(network[i+1][j]==1 || network[i+1][j]==2)
    								graph.addEdge(v, V.get(count+network[i].length));
    							if(network[i][j]==1)
    								if(network[i][j-1]==0 && network[i+1][j]==0)
    									return -1;
    						}
    						else {
    							if(network[i][j-1]==1 || network[i][j-1]==2)
    								graph.addEdge(v, V.get(count-1));
    							if(network[i][j+1]==1 || network[i][j+1]==2)
    								graph.addEdge(v, V.get(count+1));
    							if(network[i+1][j]==1 || network[i+1][j]==2)
    								graph.addEdge(v, V.get(count+network[i].length));
    							if(network[i][j]==1)
    								if(network[i][j-1]==0 && network[i+1][j]==0 && network[i][j+1]==0)
    									return -1;
    						}
    					}
    					else if(i==network.length-1)
    					{
    						if(j==0)
    						{
    							if(network[i].length!=1) {
    								if(network[i][j+1]==1 || network[i][j+1]==2)
    									graph.addEdge(v, V.get(count+1));
    								if(network[i-1][j]==1 || network[i-1][j]==2)
    									graph.addEdge(v, V.get(count-network[i].length));
    								if(network[i][j]==1)
    									if(network[i][j+1]==0 && network[i-1][j]==0)
    										return -1;
    							}
    							else
    								if(network[i-1][j]==1 || network[i-1][j]==2)
    									graph.addEdge(v, V.get(count-network[i].length));
    						}
    						else if(j==network[i].length-1)
    						{
    							if(network[i][j-1]==1 || network[i][j-1]==2)
    								graph.addEdge(v, V.get(count-1));
    							if(network[i-1][j]==1 || network[i-1][j]==2)
    								graph.addEdge(v, V.get(count-network[i].length));
    							if(network[i][j]==1)
    								if(network[i][j-1]==0 && network[i-1][j]==0)
    									return -1;
    						}
    						else
    						{
    							if(network[i][j-1]==1 || network[i][j-1]==2)
    								graph.addEdge(v, V.get(count-1));
    							if(network[i][j+1]==1 || network[i][j+1]==2)
    								graph.addEdge(v, V.get(count+1));
    							if(network[i-1][j]==1 || network[i-1][j]==2)
    								graph.addEdge(v, V.get(count-network[i].length));
    							if(network[i][j]==1)
    								if(network[i][j-1]==0 && network[i-1][j]==0 && network[i][j+1]==0)
    									return -1;
    						}
    					}
    					else
    					{
    						if(j==0)
    						{
    							if(network[i].length!=1) {
    								if(network[i][j+1]==1 || network[i][j+1]==2)
    									graph.addEdge(v, V.get(count+1));
    								if(network[i-1][j]==1 || network[i-1][j]==2)
    									graph.addEdge(v, V.get(count-network[i].length));
    								if(network[i+1][j]==1 || network[i+1][j]==2)
    									graph.addEdge(v, V.get(count+network[i].length));
    								if(network[i][j]==1)
    									if(network[i][j+1]==0 && network[i+1][j]==0 && network[i-1][j]==0)
    										return -1;
    							}
    							else
    							{
    								if(network[i-1][j]==1 || network[i-1][j]==2)
    									graph.addEdge(v, V.get(count-network[i].length));
    								if(network[i+1][j]==1 || network[i+1][j]==2)
    									graph.addEdge(v, V.get(count+network[i].length));
    							}

    						}
    						else if(j==network[i].length-1)
    						{
    							if(network[i][j-1]==1 || network[i][j-1]==2)
    								graph.addEdge(v, V.get(count-1));
    							if(network[i-1][j]==1 || network[i-1][j]==2)
    								graph.addEdge(v, V.get(count-network[i].length));
    							if(network[i+1][j]==1 || network[i+1][j]==2)
    								graph.addEdge(v, V.get(count+network[i].length));
    							if(network[i][j]==1)
    								if(network[i][j-1]==0 && network[i+1][j]==0 && network[i-1][j]==0)
    									return -1;
    						}
    						else
    						{
    							if(network[i][j-1]==1 || network[i][j-1]==2)
    								graph.addEdge(v, V.get(count-1));
    							if(network[i][j+1]==1 || network[i][j+1]==2)
    								graph.addEdge(v, V.get(count+1));
    							if(network[i-1][j]==1 || network[i-1][j]==2)
    								graph.addEdge(v, V.get(count-network[i].length));
    							if(network[i+1][j]==1 || network[i+1][j]==2)
    								graph.addEdge(v, V.get(count+network[i].length));
    							if(network[i][j]==1)
    								if(network[i][j-1]==0 && network[i+1][j]==0 && network[i][j+1]==0 && network[i-1][j]==0)
    									return -1;
    						}
    					}
    				}
    			}
    			else
    			{
    				if(j==0)
    				{
    					if(network[i][j+1]==1 || network[i][j+1]==2)
    						graph.addEdge(v, V.get(count+1));
    					if(network[i][j+1]==0)
    						return -1;
    				}
    				else if(j==network[i].length-1)
    				{
    					if(network[i][j-1]==1 || network[i][j-1]==2)
    						graph.addEdge(v, V.get(count-1));
    					if(network[i][j-1]==0)
    						return -1;
    				}
    				else
    				{
    					if(network[i][j-1]==1 || network[i][j-1]==2)
    						graph.addEdge(v, V.get(count-1));
    					if(network[i][j+1]==1 || network[i][j+1]==2)
    						graph.addEdge(v, V.get(count+1));
    					if(network[i][j-1]==0 || network[i][j+1]==0)
    						return -1;
    				}
    			}
    			count++;
    			v++;
    			w++;
    		}
    	
    	///////////BFS////////

    	BreadthFirstPaths bfs=new BreadthFirstPaths(graph,sources);
    	int max=0;
    	for(int i=0;i<sources.size();i++)
    	{
    		for(int j=0;j<graph.V();j++)
    		{
    			if(bfs.distTo(j)!=2147483647)
    				if(max<bfs.distTo(j))
    					max=bfs.distTo(j);
    		}

    	}
    	return max;

    }
}
