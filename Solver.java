/*
 * Joe O'Regan
 * K00203642
 * 
 * Works if the 0 or blank tile is up to 4 positions out of place, 
 * but otherwise it's fairly broken, and gets stuck in a loop
 */
import java.util.Comparator;
import java.util.*;

public class Solver {
	MinPQ<State> statePQ;
	Queue<State> doneQ = new Queue<>();
	int minNumMoves = 0;
	State solutionState;
	int test = 0;
	int statesEnqueued = 0;
	
	/*
	 * State with board layout, the number of moves, and the previous state (initial state has null for prevState)
	 */
	private class State implements Comparator<State>, Comparable<State>{
		Board board;
		int moves;
		State prevState;
		//boolean visited = false;
		
		public State(Board b, State s) {
			board = b;
			//moves = moves();
			moves = 0;
			prevState = s;
		}

		/*
		 *  Overriding the compareTo method (https://www.tutorialspoint.com/java/java_using_comparator.htm)(non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object) // I don't know where this came from
		 */
		public int compareTo(State s1) {
		      //return (this.prevState.board.manhattan()).compareTo(s1.prevState.board.manhattan());	// gives dereferenced error
		      //return (this.prevState).compareTo(s1.prevState);	// gives dereferenced error
		      return 0;
		}
		
		// Overriding the compare method to sort by manhattan() function
		public int compare(State s1, State s2) {
			return s1.board.manhattan() - s2.board.manhattan();
		}
	}
	
    public Solver(Board initial) {
    	State tempState;    	
    	statePQ = new MinPQ<State>();
    	boolean foundSolution = false;
    	    	
    	State initialState = new State(initial, null);   
    	initialState.moves = 0;

    	doneQ.enqueue(initialState);    	
    	statePQ.insert(initialState);
    	
        while(!foundSolution) {
        	tempState = statePQ.delMin();														// Get a state from the queue
        	
        	if (tempState.board.boardIsSolution()) {											// if the state is the solution
        		solutionState = tempState;														// set the solution state to as the current state
        		foundSolution = true;															// finished / exit function
        	}
			//System.out.println("1 doneQ size: " + doneQ.size()); 
        	if (!foundSolution){
		        //for(Board b : tempState.board.neighbours(allBoards)) {  
			    for(Board b : tempState.board.neighbours()) {        							// for each board that's a neighbour of the current board		
		        	if(tempState.prevState == null || !b.equals(tempState.prevState.board) || !checkStateBoard(b)) {	// board isn't equal to the board for the previous state
		        		State s = new State(b, tempState);										// create new state	
		        				        		  			    	    	
		        		if(checkStateBoard(s.board)) {
		        			statePQ.insert(s);
		        	    	statesEnqueued++;													// Number of states on the queue
		        			doneQ.enqueue(s);
		        		}
		        				        		
		        		if(s.board.boardIsSolution()) {
		            		solutionState = s;													// set the solution state to as the current state
		            		foundSolution = true;												// finished / exit function
		        		}	
		        		//System.out.println("test " + ++test);
		        		//System.out.println("DoneQ :" + doneQ.size());	        		
		        	}
		        }
        	}
    	}
    } 

    /*
     * Check if the board has been added to the visited boards queue already
     */
    //public boolean checkStateBoard(Board b, Queue<Board> allBoards1){
    public boolean checkStateBoard(Board b){
		//if (allBoardsQ.isEmpty()) {			
		if (doneQ.isEmpty()) {											// If the queue of all previous boards is empty, board can be added
			return true;
		}
		
    	Queue<State> checkBoards = new Queue<>();					// Copy the visited states queue
	    Iterator<State> it = doneQ.iterator();
	    while(it.hasNext()){
	    	checkBoards.enqueue(it.next());
	    }
    	
    	while (!checkBoards.isEmpty()){
    		Board checkThis = checkBoards.dequeue().board;			// check each board on the queue
    		
    		if(checkThis.boardsAreEqual(checkThis.board, b.board)) {// if they are equal (already on the queue)
    			//System.out.println("Board is on queue already");
    			
    			return false;										// return false, and don't bother adding it	    			
    		}
    	}			

		//System.out.println("Board is NOT on queue already");
		
    	return true;										// if the board is not already on the boards list, return true to add it
    }
    
    
    public boolean isSolvable() {
        // YOUR CODE HERE
    	// Don't need
    	//return false;
    	return true; // needs to be solvable
    }
        
    /*
     * Get the number of moves from initial
 state to current state
     */
    public int moves() {
        // YOUR CODE HERE
    	/*
    	int moves;
    	State s = solutionState;
    	if(s.prevState == null) moves = 0;
    	else moves = s.prevState.moves + 1;
    	 */
		State s1 = solutionState;			// Temporary state	
		//minNumMoves = -1;
		minNumMoves = 0;					// Reset moves made
		
		if (s1.prevState == null){
			return 0;						// if it's the initial state, moves are 0
		}
		else {
			while(s1.prevState != null) {	// Count back previous states as far as initial null state
				minNumMoves++;				// Increment moves made
				s1 = s1.prevState;			// Set to previous state
			}
		}
		
		return minNumMoves;
    }
    
    /*
     * Create a list of the board states/tile moves from initial state to goal state
     * (Probably should use a stack instead of a queue)
     */
    public Iterable<Board> solution() {
        // YOUR CODE HERE
    	// Stack/queue/priQ/linkedlist?
    	//Queue<Board> anotherQueue = new Queue<Board>();				// Hold the boards
    	Stack<Board> solutionMovesMade = new Stack<Board>();			// Hold the boards
    	
    	State trackState = solutionState;							// Make the current state the final state
    	//anotherQueue.enqueue(trackState.board);						// Add the board for the current state to the list of boards
    	solutionMovesMade.push(trackState.board);
    	/*
    	while(trackState.prevState != null){						// Unit the initial state is reached
    		anotherQueue.enqueue(trackState.prevState.board);		// Add boards to the queue for the previous state
    		trackState = trackState.prevState;						// Make the current state the previous state
    	}
    	*/
    	
    	while (trackState.prevState != null){
    		solutionMovesMade.push(trackState.prevState.board);
    		trackState = trackState.prevState;
    	}
    	
        //return anotherQueue;										// Return the list of boards used to reach the solution
    	return solutionMovesMade;
    }
    
    public static void main(String[] args) {
    	int N = StdIn.readInt();
        int [][] tiles = new int [N][N];
        
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                tiles[i][j] = StdIn.readInt();
            }
        }
        
        Board initial = new Board(tiles);
        
        //System.out.println("Board Entered: \n" + initial.toString());	// display board entered
        
        Solver solver = new Solver(initial);
        
        for (Board board : solver.solution()){
            System.out.println(board);
        }
        
        System.out.println("Number of states enqueued = " + solver.statesEnqueued);
        
        if(!solver.isSolvable())
            System.out.println("No solution possible");
        else
            System.out.println("Minimum number of moves = " + solver.moves());
    }
    /*
     //System.out.println("Board Solution: \n");
        
        int[][] test1 = {{8,1,3},{4,0,2},{7,6,5}};

        int[][] test2 = {{1,2,3},{4,5,6},{7,8,0}};
        
        int[][] test3 = {{1,2,3},{4,5,6},{7,8,0}};
        /*
        for (int i = 0; i < 3; i++){
        	for (int j = 0; j < 3; j++){
        		System.out.print(" " + test1[i][j]);
        		if (j<2) System.out.print(" |");
        	}
    		if (i<2) System.out.println("\n---|---|---");
    		//else System.out.println("\n   |   |   ");
        }
        

        //Board blah = new Board(test1);
        Board blah = new Board(test2);

        //if(blah.boardIsSolution()) System.out.println("\nyes");
        //else System.out.println("\nno");
        
        //Board blah1 = new Board(test2);
        //if(blah1.boardIsSolution()) System.out.println("\nyes");
       // else System.out.println("\nno");
        
        Solver solver1 = new Solver(blah);
        

        if (test1 == test2) System.out.println("\nTest1 & Test2 boards are equal");
        else System.out.println("\nTest1 & Test2 boards are not equal");
        
        if (test2 == test3) System.out.println("\nTest2 & Test3 boards are equal");
        else System.out.println("\nTest2 & Test3 boards are not equal");


        for (int i = 0; i < 3; i++){
        	for (int j = 0; j < 3; j++) {
        		if(test1[i][j] == test2[i][j]) System.out.println("board[" + i + "][" + j + "] equal");
        		else System.out.println("board[" + i + "][" + j + "] not equal");
        	}
        }
        
        
        //Board blah = new Board(test1);     

        //State testState = new State(blah, null);
        //testState.moves = 0;
        
        //System.out.println("\n\nHamming:\n");
        //System.out.println("blah hamming: " + blah.hamming());
        
        //System.out.println("\nManhattan:\n");
        //System.out.println("blah manhattan: " + blah.manhattan());
        

        //System.out.println("\nTest:\n");
        //blah.neighbours();

    	//System.out.println(statePQ.delMin().toString());

        
        for (int i = 0; i < 3; i++){
        	for (int j = 0; j < 3; j++) {
        		if(test3[i][j] == test2[i][j]) System.out.println("board[" + i + "][" + j + "] equal");
        		else System.out.println("board[" + i + "][" + j + "] not equal");
        	}
        }
        Vector<Board> v = new Vector<Board>();
    	//Vector<Board> v = new Vector<Board>();		// Vector of size 20, increases by 10, when out of space

        int[][] test1 = {{8,1,3},{4,0,2},{7,6,5}};
        Board initial = new Board(test1);
        
    	v.add(initial);
     */
}