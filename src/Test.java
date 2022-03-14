import edu.princeton.cs.algs4.*;
import java.util.concurrent.Callable;
import java.io.File;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

class Test {

    //////////////////////////////////////////////////////////// EXERCISE 1 TEST
    static private Void testEx1 () throws TestException {
    	
    	/*Grammar gram= new Grammar();
    	String[]ex={"zza"};
    	
    	
    	gram.addRule("C","z");
    	gram.addRule("B", "zCa");
    	
    	Set<String> rest =
    	        ProgrammingAssignment3.allProductions (gram, new Grammar.Token ("B", false));
    	
    	if (! (rest.equals (new TreeSet<String> (Arrays.asList(ex)))))
            throw new TestException ("unexpected answer: " + rest);
    	
    	*/
    	
      String[] expres = {"azzab", "azab", "azb", "attb"};
      Grammar g = new Grammar ();

      g.addRule ("S", "aCb");
      g.addRule ("S", "aBb");
      g.addRule ("C", "z");
      g.addRule (new Grammar.Token ("B", false),
                 Collections.singletonList (new Grammar.Token ("tt", true)));
      g.addRule ("B", "za");
      g.addRule ("B", "zCa");

      Set<String> res =
        ProgrammingAssignment3.allProductions (g, new Grammar.Token ("S", false));
      
      if (! (res.equals (new TreeSet<String> (Arrays.asList(expres)))))
        throw new TestException ("unexpected answer: " + res);

      res = ProgrammingAssignment3.allProductions (g, new Grammar.Token ("Z", false));
      if (! (res.equals (Collections.EMPTY_SET)))
        throw new TestException ("unexpected answer:" + res);

      // This is a test that would show how good are the performances of allProductions.
      /*g = new Grammar ();
      g.addRule ("S", "AAAA");
      g.addRule ("A", "BBBBBBBB");
      g.addRule ("B", "CCCCCCCC");
      g.addRule ("C", "DDDDDDDD");
      g.addRule ("D", "EEEEEEEE");
      g.addRule ("E", "FFFFFFFF");
      g.addRule ("F", "GGGGGGGG");
      g.addRule ("G", "HHHHHHHH");
      g.addRule ("H", "IIIIIIII");
      g.addRule ("I", "x");

      res = ProgrammingAssignment3.allProductions (g, new Grammar.Token ("S", false));

      if (!res.iterator ().hasNext() || res.iterator ().next ().length () != 67108864)
        throw new TestException ("unexpected answer, wrong length.");*/
      
      
      
      
      /////////EXTRA//////////////
      g=new Grammar();
      g.addRule("S", "AB");
      g.addRule("A", "aza");
      g.addRule("B", "bbb");
      
      res = ProgrammingAssignment3.allProductions (g, new Grammar.Token ("S", false));
      //StdOut.print(res+"\n");
      
      g = new Grammar ();
      g.addRule (new Grammar.Token ("B", false),
                 Collections.singletonList (new Grammar.Token ("B", true)));
      g.addRule ("B", "z");
      res=ProgrammingAssignment3.allProductions (g, new Grammar.Token ("B", false)); // Should return {B, z}.
      
      //StdOut.print(res+"\n");

      return null;
    }

    //////////////////////////////////////////////////////////// EXERCISE 2 TEST
    static private Void testEx2 () throws TestException {
    	
    	
    	Digraph dagless=DigraphGenerator.dag(38, 36),
    			dagmore=DigraphGenerator.dag(38,39),
    			tournament=DigraphGenerator.tournament(34),
    			dcycle=DigraphGenerator.cycle(38),
    			daglotsofedges=DigraphGenerator.dag(43, 43*(43-1)/2-1),
    			simple=DigraphGenerator.simple(42,43*(43-1)/2+1);
    	Graph complete=GraphGenerator.complete(43),
    			bipartite=GraphGenerator.bipartite(12, 39, 37),
    			ubintree=GraphGenerator.binaryTree(38),
    			ucycle=GraphGenerator.cycle(34);
    			
    	
    	

    	
      Digraph tree = DigraphGenerator.rootedOutTree (47);

      if (!ProgrammingAssignment3.isDirectedTree (tree))
        throw new TestException ("tree recognized as not tree.");

      tree.addEdge (0, 0);
      if (ProgrammingAssignment3.isDirectedTree (tree)||
    		  ProgrammingAssignment3.isDirectedTree (dagless)||
    		  ProgrammingAssignment3.isDirectedTree (dagmore) ||
    		  ProgrammingAssignment3.isDirectedTree (tournament)||
    		  ProgrammingAssignment3.isDirectedTree (dcycle))
        throw new TestException ("nontree recognized as tree.");

      Digraph tour = DigraphGenerator.tournament (37);
      if (!ProgrammingAssignment3.isTournament (tour))
        throw new TestException ("tournament recognized as not tournament.");

      
      List<Integer> succ=new ArrayList<>();
      tour.adj(0).forEach(succ::add);
      if(succ.contains(1))
    	  tour.addEdge(1, 0);
      else
    	  tour.addEdge(0, 1);
      
      //tour.addEdge (0, 0);
      if (ProgrammingAssignment3.isTournament (tour) ||
    		  ProgrammingAssignment3.isTournament (daglotsofedges)||
    		  ProgrammingAssignment3.isTournament (simple))
        throw new TestException ("nontournament recognized as tournament.");

      Graph comp = GraphGenerator.complete (37);
      if (!ProgrammingAssignment3.isComplete (comp))
        throw new TestException ("complete recognized as not complete.");

      comp.addEdge (0, 0);
      if (ProgrammingAssignment3.isComplete (comp)||
    		  ProgrammingAssignment3.isComplete (bipartite) ||
    		  ProgrammingAssignment3.isComplete (ubintree) ||
    		  ProgrammingAssignment3.isComplete (ucycle))
        throw new TestException ("noncomplete recognized as complete.");

      Graph wheel = GraphGenerator.wheel (39);
      if (!ProgrammingAssignment3.isWheel (wheel))
        throw new TestException ("wheel recognized as not wheel.");

      wheel.addEdge (0, 0);
      if (ProgrammingAssignment3.isWheel (wheel)||
    		  ProgrammingAssignment3.isWheel (complete) ||
    		  ProgrammingAssignment3.isWheel (bipartite))
        throw new TestException ("nonwheel recognized as wheel.");
      


      return null;
    }

    //////////////////////////////////////////////////////////// EXERCISE 3 TEST
    static private Void testEx3 () throws TestException {
      int[][] t1 = {{2,1,1},{1,1,0},{0,1,1}};
      int[][] t2 = {{2,1,1},{0,1,1},{1,0,1}};
      int[][] t3 = {{0, 2}};
      int[][] t4= {{0,0,0,2},{2,0,0,0},{0,2,0,0},{2,0,0,0},{0,0,0,2}} ;
      int[][] t5= {{0,0,0,1},{1,0,0,0},{0,1,0,0},{1,0,0,0},{0,0,0,1}};
     
      //};
      if (! (ProgrammingAssignment3.virusSpread (t1) == 4  &&
             ProgrammingAssignment3.virusSpread (t2) == -1  &&
             ProgrammingAssignment3.virusSpread (t3) == 0  &&
             ProgrammingAssignment3.virusSpread (t4) == 0 &&
             ProgrammingAssignment3.virusSpread (t5) == -1))
        throw new TestException ("incorrect virus spread.");
      
      
      ////////////Extra//////////////
      /*System.out.println("Extra Tests Ex 3");
      boolean failed = false;
      
      int[][][] networks = {{{0, 2, 1}, {1, 1, 2}, {2, 2, 2}, {1, 1, 1}, {1, 0, 1}, {2, 1, 2}}
      ,{{1, 2, 0, 1, 2, 1, 1, 0}, {1, 2, 1, 1, 2, 1, 1, 1}, {2, 1, 2, 0, 1, 1, 2, 1}, {2, 1, 1, 1, 2, 1, 1, 1}, {1, 2, 0, 2, 1, 1, 2, 2}, {1, 1, 1, 2, 0, 2, 2, 1}, {2, 1, 1, 1, 2, 2, 2, 2}, {2, 1, 2, 1, 1, 2, 1, 2}, {2, 1, 2, 2, 1, 1, 1, 1}}
      ,{{1, 1, 1, 1, 0}, {2, 1, 2, 1, 2}, {2, 1, 1, 2, 2}, {2, 1, 2, 2, 1}, {1, 1, 1, 1, 2}, {2, 2, 0, 1, 1}, {1, 1, 2, 2, 1}, {1, 2, 2, 1, 1}, {2, 1, 1, 0, 2}, {1, 1, 2, 2, 1}}
      ,{{2, 2}, {1, 1}, {2, 2}, {1, 0}, {1, 2}, {1, 2}, {1, 0}, {0, 2}, {2, 1}}
      ,{{2, 1, 0, 2}, {1, 1, 1, 0}, {2, 2, 1, 2}, {1, 1, 2, 1}, {2, 1, 2, 1}, {1, 2, 2, 1}, {1, 2, 2, 2}}
      ,{{0, 2, 1, 2, 1, 2, 2}, {1, 2, 1, 0, 1, 2, 2}, {2, 2, 1, 1, 2, 2, 1}, {2, 1, 1, 2, 2, 2, 1}}
      ,{{2, 2, 0, 2, 1}, {2, 1, 2, 2, 1}, {1, 2, 2, 2, 2}, {1, 2, 2, 2, 2}}
      ,{{1, 1, 1, 1}, {1, 1, 2, 1}, {1, 2, 2, 2}, {2, 2, 2, 1}, {2, 1, 1, 1}}
      ,{{1}, {2}, {2}, {2}, {2}, {1}, {2}, {2}}
      ,{{1, 2, 0, 1, 1}}
      ,{{0}}
      ,{{2}}
      };
      
      int[] steps = {1,2,2,2,2,1,1,3,1,-1,-1,0};
      
      for(int i = 0; i < steps.length; ++i) {
    	  int res = ProgrammingAssignment3.virusSpread(networks[i]);
    	  if(res != steps[i]) {
    		System.out.print("Test failed\nExpected: ");
      		System.out.print(steps[i]);
      		System.out.print("\nProduced: ");
      		System.out.println(res);
    		failed = true;
    	  }
      }
      if(!failed) {System.out.println("Success");}
    
      
      
      
      
      
      
      */
      
      
      
      return null;
    }


    /////////////////////////////////////////////////// TESTING FRAMEWORK & MAIN
    static class TestException extends Exception  {
        static final long serialVersionUID = 314;
        public TestException (String msg) {
          super (msg);
        }
    }

    static private void runTest (String name, Callable<Void> test) {
        StdOut.println ("TESTING " + name);
      try {
        test.call ();
        StdOut.println ("SUCCESS");
      }
      catch (TestException e) {
        StdOut.println ("TEST FAILED: " + e.getMessage ());
      }
      catch (Exception | Error e) {
        StdOut.print ("FATAL: ");
        e.printStackTrace ();
      }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    static public void main(String[] args) {
      runTest ("EX 1", Test::testEx1);
      runTest ("EX 2", Test::testEx2);
      runTest ("EX 3", Test::testEx3);
    }

}
