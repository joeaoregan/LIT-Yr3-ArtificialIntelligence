import java.util.Iterator;


/*
 * Joe O'Regan
 * K00203642
 */

public class Board{
	int neighboursCalled = 0;
	int N = 0; 												// Size for N x N board
	
	int[][] solutionBoard = {{1,2,3},{4,5,6},{7,8,0}};		// Board to reach
	int[][] board;											// Board we have
	int[][] iterableBoard;
	Queue<Board> allBoards = new Queue<>();
	
    public Board(int [][] tiles){
        // YOUR CODE HERE
    	N = tiles.length;						// Set the value of N, the dimension of the board
       
    	//System.out.println("Board N: " + N);
        
    	board = new int[N][N];					// Initialise board (causes error otherwise)
    	
	    for (int i = 0; i < N; i++){
	    	for (int j = 0; j < N; j++) {
	    		board[i][j] = tiles[i][j];		// Set board array equal to tiles
	    	}
	    }
    }
    
    public int hamming(){
        // YOUR CODE HERE
    	//int[][] solutionBoard = {{1,2,3},{4,5,6},{7,8,0}};	// All tiles in correct position
    	int outOfPosition = 0;								// Number of blocks in the wrong position
    	//int movesSoFar = 0;									// Number of moves made so far
    	
	    for (int i = 0; i < N; i++){
	    	for (int j = 0; j < N; j++) {
	    		//if(solutionBoard[i][j] == boardToCheck[i][j]) System.out.println("board[" + i + "][" + j + "] equal");
	    		//else System.out.println("board[" + i + "][" + j + "] not equal");	    
	    		if (solutionBoard[i][j] != board[i][j] && board[i][j] != 0) outOfPosition++;											// Increment the number of tiles out of position
	    	}	    	
	    }
	    
        return outOfPosition;
    }
    
    public int manhattan(){
        // YOUR CODE HERE
    	int x = 0, y = 0, manhattanTotal = 0;				// Init variables
    	
	    for (int i = 0; i < N; i++){
	    	for (int j = 0; j < N; j++) {
	    		//  		
	    	    for (int k = 0; k < N; k++){
	    	    	for (int l = 0; l < N; l++) {
    	    			if(solutionBoard[i][j] != board[i][j]){
		    	    		if(solutionBoard[i][j] == board[k][l] && solutionBoard[i][j] != 0) {
	    	    				//System.out.println("board[" + i + "][" + j + "] equal");

	    	    				//x = abs(k - i);
	    	    				//y = abs(l - j);
	    	    				x = Math.abs(l - j);	// Number of moves left or right
	    	    				y = Math.abs(k - i);	// Number of moves up or down
	    	    				
	    	    				manhattanTotal += x + y;	    	    				
	    	    				
	    	    				//System.out.println(solutionBoard[i][j] + " X: " + x + " Y: " + y);
	    	    			}
		    	    		//else System.out.println("board[" + i + "][" + j + "] not equal");
    	    			}
	    	    	}
	    	    }	    	    
	    	}
	    }    	
	    
        return manhattanTotal;
    }
    
    public boolean boardsAreEqual(int[][] boardA, int[][] boardB){
	    for (int i = 0; i < N; i++){
	    	for (int j = 0; j < N; j++) {
	    		if(boardA[i][j] != boardB[i][j]) return false;	// If one any tile is out of place the layout is not the same
	    		//System.out.println("Boards are equal");
	    	}
    		//System.out.println("Boards are NOT equal");
	    }
	    
	    return true;											// Otherwise the boards have the same tile layout
    }
    
    public boolean boardIsSolution(){
    	
	    for (int i = 0; i < N; i++){
	    	for (int j = 0; j < N; j++) {
	    		if(board[i][j] != solutionBoard[i][j]) return false;	// If one any tile is out of place the layout is not the same
	    	}
	    }
	    
    	//System.out.println("Board is solution");
	    
	    return true;											// Otherwise the boards have the same tile layout
    }
    
    public boolean equals( Object o){
        // YOUR CODE HERE
    	/*
    	// Date and Transaction examples off Princeton FAQ
    	
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Transaction that = (Transaction) other;
        return (this.amount == that.amount) && (this.who.equals(that.who))
                                            && (this.when.equals(that.when));
    	

        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Date that = (Date) other;
        return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
        */

    	
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        Board that = (Board) o;
        return (this.N == that.N) && boardsAreEqual(this.board, that.board);	// if boards have the same dimensions, and tile layout
    	
        //return false; // Unreachable
    }
        
    // Make board A the same as board B
    //public void copyBoard(Board boardA, Board boardB){
    public void copyBoard(int[][] boardA, int[][] boardB) {
        for (int i = 0; i < N; i++){
        	for (int j = 0; j < N; j++) {
        		//boardA.board[i][j] = boardB.board[i][j];	// make boards the same
        		boardA[i][j] = boardB[i][j];
        	}
        }
    }

    //public Iterable<Board> neighbours(Queue<Board> q){  
    public Iterable<Board> neighbours(){  
    	neighboursCalled++;
    	System.out.println("Neighbours: " + neighboursCalled);
    //public Iterable<Board> neighbours(){    	
        // YOUR CODE HERE   
		//MaxPQ<Board> boardList = new MaxPQ();
    	//MaxPQ<Board> boardList = new MaxPQ<Board>();
    	//MinPQ<Board> boardQueue = new MinPQ<Board>(10);							// Need to set size for priority queue
    	//MinPQ<State> stateQueue = new MinPQ<State>();
    	//System.out.println("size of boardQueue: " + boardQueue.size(10));
    	//private Key[] key;													// Set a key for the priority queue (hamming or manahattan)
    	
    	//Board tempBoard = new Board();
    	//int[][] temp = 
    	// Make iterable board = board
    	/*
        for (int i = 0; i < 3; i++){
        	for (int j = 0; j < 3; j++) {
        		iterableBoard[i][j] = board[i][j];	// make boards the same
        	}
        }
        */
    	//System.out.println("Iterable Board:\n");
    	//copyBoard(iterableBoard, board);
    	//System.out.println(iterableBoard.toString());
    	
    	Queue<Board> boardQueue = new Queue<>();
    	
    	int temp;																// Hold blank tiles old board position  	
    	
	    for (int y = 0; y < N; y++){
	    	for (int x = 0; x < N; x++) {
	    		if (board[y][x] == 0){	// Find the blank tile
	    			// First Check for able to move blank tile left or right	
	    			//int[][] tempLayout = new int[N][N];	    			
	    			int[][] b1 = new int[N][N];
	    			int[][] b2 = new int[N][N];
	    			int[][] b3 = new int[N][N];
	    			int[][] b4 = new int[N][N];
	    			
	    			copyBoard(b1, board);
	    			copyBoard(b2, board);
	    			copyBoard(b3, board);
	    			copyBoard(b4, board);
	    			
	    			//System.out.println("N: " + N);	    			
	    			
	    			if (x > 0) {												// Can move blank tile left
	    				//System.out.println("\nMove Left:");
	    				temp = b1[y][x];										// Swap the tiles position
	    				b1[y][x] = b1[y][x - 1];
	    				b1[y][x - 1] = temp;
	    				
	    				Board board1 = new Board(b1);
	    				//if(checkBoard(board1, q)) {
		    			if(checkBoard(board1)) {
	    					boardQueue.enqueue(board1);
		    				System.out.println("\nMove Left:");
		    				System.out.println(board1.toString());
	    				}

	    				//System.out.println("board1 hamming: " + board1.hamming());
	    				//System.out.println("board1 manhattan: " + board1.manhattan());

	    		        //key = (Key[]) new Comparable[board1.manhattan()];
	    		        
	    				//boardQueue.insert(board1);
	    		    	//System.out.println("size of boardQueue: " + boardQueue.size());
	    				//System.out.println(board1.toString());
	    				
	    				//System.out.println("test");
	    				//System.out.println(boardQueue[0].toString());

	    				//System.out.println("delMin() index: " + boardQueue.delMin());
	    				//System.out.println(boardQueue.delMin().toString());		// Dequeue the board with the minimum priority

	    				//boardQueue.enqueue(board1);
	    				//System.out.println("boardQueue size: " + boardQueue.size());
	    			}	    			
	    			if (x < N - 1) {												// Can move blank tile right (change to N-1, out of bounds error)
	    				//System.out.println("\nMove Right:");
	    				temp = b2[y][x];
	    				b2[y][x] = b2[y][x + 1];
	    				b2[y][x + 1] = temp;

	    				//Board board0 = new Board(board);
	    				Board board2 = new Board(b2);
	    		    	//System.out.println("size of boardQueue: " + boardQueue.size());
	    				//System.out.println("Board 0");
	    				//System.out.println(board2.toString());

	    				//if(checkBoard(board2, q)) {
		    			if(checkBoard(board2)) {
	    					boardQueue.enqueue(board2);
		    				System.out.println("\nMove Right:");
		    				System.out.println(board2.toString());
	    				}
	    			}	    			
	    			if (y > 0) {												// Can move blank tile up
	    				//System.out.println("\nMove Up:");
	    				temp = b3[y][x];
	    				b3[y][x] = b3[y - 1][x];
	    				b3[y - 1][x] = temp;
	    				
	    				Board board3 = new Board(b3);
	    				//System.out.println(board3.toString());
	    				//if(checkBoard(board3, q)) {
		    			if(checkBoard(board3)) {
	    					boardQueue.enqueue(board3);
		    				System.out.println("\nMove Up:");
		    				System.out.println(board3.toString());
	    				}  				
	    			}	    			
	    			if (y < N - 1) {												// Can move blank tile down (change to N-1, out of bounds error)
	    				//System.out.println("\nMove Down:");
	    				//System.out.println("Tile Down - y: " + y + " x: " + x);
	    				temp = b4[y][x];
	    				b4[y][x] = b4[y + 1][x];
	    				b4[y + 1][x] = temp;
	    				
	    				Board board4 = new Board(b4);
	    				//System.out.println(board4.toString());
	    				
	    				//testBoard(board4);
	    				//if(checkBoard(board4, q)) {
		    			if(checkBoard(board4)) {
	    					boardQueue.enqueue(board4);
		    				System.out.println("\nMove Down:");
		    				System.out.println(board4.toString());
	    				}
	    			}
	    			
    				System.out.println("size of boardQueue " + boardQueue.size());
	    		}
	    	}
	    }
	    
	    
	    //System.out.println(boardQueue.dequeue().toString());

        return boardQueue;
    }

    //public boolean checkBoard(Board b, Queue<Board> allBoards){
    public boolean checkBoard(Board b){
    	//boolean putOnQueue = false;
		if (allBoards.isEmpty()) {							// If the queue of all previous boards is empty, board can be added
			return true;
		}
		else {
	    	//Queue<Board> checkBoards = allBoards;
    		Queue<Board> checkBoards = new Queue<>();
    		Iterator<Board> it = allBoards.iterator();
    		while(it.hasNext()){
    			checkBoards.enqueue(it.next());		   		// Deep copy the queue     			
    		}
    		
    		
	    	while (!checkBoards.isEmpty()){
	    		Board checkThis = checkBoards.dequeue();	// check each board on the queue
	    		
	    		if(boardsAreEqual(checkThis.board, b.board)) {// if they are equal (already on the queue)
	    			//putOnQueue = false;
	    			return false;							// return false, and don't bother adding it	    			
	    		}
	    		//else putOnQueue = true;
	    	}
			//if (putOnQueue) 
				allBoards.enqueue(b);						// Add the board to the previously visited boards queue
		}
		
    	return true;										// if the board is not already on the boards list, return true to add it
    }
    
    public void testBoard(Board b){
		System.out.println("Current board hamming: " + b.hamming());
		System.out.println("Current board manhattan: " + b.manhattan());
		System.out.println(b.toString());
    }
    
    public String toString(){
        // YOUR CODE HERE    
    	String boardStr = "";									// Convert the board to a string for output
    	
    	for (int i = 0; i < N; i++){
	    	for (int j = 0; j < N; j++){
	    		//System.out.print(" " + board[i][j]);
	    		//if (j<2) System.out.print(" |");
	    		boardStr += " " + board[i][j];
	    		//if (j < N - 1) boardStr += (" |");
	    	}
	    	/*
			//if (i<2) System.out.println("\n---|---|---");
			//else System.out.println("\n   |   |   ");
			//if (i<2) boardStr += "\n---|---|---\n";
	    	if (i < N - 1) {
		    	for (int k = 0; k < N - 1; k++){
		    		if (k == 0) boardStr += "\n---|";
		    		if (k < N-2) boardStr += "---|";
		    		else if (k < N-1) boardStr += "---";
		    	}
	    	}
	    	*/
	    	if (i < N - 1) boardStr += "\n";
    	}
    	//System.out.println("\n");
    	boardStr += "\n";
    	    	
        return boardStr;										// String representation of the board
    }
    
    public static void main(String [] args){
        
        int[][] test1 = {{8,1,3},{4,0,2},{7,6,5}};	// 5 out of position (blank tile not included)

        int[][] test2 = {{1,2,3},{4,5,6},{7,8,0}};	// 0 out of position
        
        //System.out.println(" x = 3, y = 2 test1[2][3]: " + test1[1][2]); // Test board[y][x] positions for x and y, i = y, j = x

      //  MinPQ<Board> pq = new MinPQ<Board>();
        
		Board board1 = new Board(test1);
		Board board2 = new Board(test2);
		
		//pq.insert(board1);
		//pq.insert(board2);
		
		//int size = pq.size();	// error
		
		//System.out.println("MinPQ size: " + size);	// error
		
		/*
		//Board board3 = new Board({{1,2,3},{4,5,6},{7,8,0}});
		//Board board3 = new Board({1,2,3,4,5,6,7,8,0});
		//Board board3 = new Board(1,2,3,4,5,6,7,8,0);
		
       // board1.hamming(test1);
        //board2.hamming(test2);    
        //board1.toString();
        //board2.toString();
		*/
		
        System.out.println(board1.toString());
        System.out.println(board2.toString());
        //System.out.println("Board 1 Hamming: " + board1.hamming());
        //System.out.println("Board 2 Hamming: " + board2.hamming());
        
        //System.out.println("Board 1 Manhattan Distance: " + board1.manhattan());
        
        //board1.copyboard(board1,board2);
       // System.out.println(board1.toString());
        //System.out.println(board2.toString());
        
    }
}

        