
public class Solver {
	private boolean solvable;
	private boolean twinSolvable;
	
	private SearchNode initialNode;
	private SearchNode twinNode;
	private SearchNode endNode;
	private MinPQ<SearchNode> pq;
	private MinPQ<SearchNode> twinPq;
	
	private class SearchNode implements Comparable<SearchNode> {
		public Board currentBoard;
		public int movesToReach;
		SearchNode parent;
		
		
		public SearchNode(Board currentBoard, SearchNode parent) {
			super();
			this.currentBoard = currentBoard;
			this.parent = parent;
		}


		@Override
		public int compareTo(SearchNode o) {
			// TODO Auto-generated method stub
			if(this.currentBoard.manhattan() < o.currentBoard.manhattan())
				return -1;
			else if (this.currentBoard.manhattan() > o.currentBoard.manhattan())
				return 1;
			else
				return 0;
		}
		
	}
	
    public Solver(Board initial) {       
    	initialNode = new SearchNode(initial,null);
    	twinNode = new SearchNode(initial.twin(), null);
    	pq = new MinPQ<Solver.SearchNode>();
    	twinPq = new MinPQ<Solver.SearchNode>();
    	
    	pq.insert(initialNode);
    	twinPq.insert(twinNode);
    	solvable = false;
    	twinSolvable= false;
    	while(!solvable && !twinSolvable) {
    		solvable = solve(pq);
    		twinSolvable = solve(twinPq);
    	}
    }
    
    private boolean solve(MinPQ<SearchNode> q) {
    	SearchNode current = q.delMin();
    	
    	if(current.currentBoard.isGoal()) {
    		endNode = current;
    		return true;
    	}
    
    	for (Board board : current.currentBoard.neighbors()) {
    		if(current.parent == null || !(board.equals(current.parent.currentBoard))) {
    			q.insert(new SearchNode(board, current));
    		}
    	}
    	
    	return false;
	}

	public boolean isSolvable() {
		return solvable;
    }
    
    public int moves() {
    	if(isSolvable()) {
    		int moves = 0;
    		SearchNode current = endNode;
    	
	    	while(current.parent != null) {
	    		moves++;
	    		current = current.parent;
	    	}
	    		return moves;
    	}
    	else
    		return -1;
    }
    
    public Iterable<Board> solution() {
    	if (isSolvable()) {
    		Stack<Board> boardStack = new Stack<Board>();
    		SearchNode current = endNode;
    		boardStack.push(current.currentBoard);
    		
    		while(current.parent != null) {
    			boardStack.push(current.parent.currentBoard);
    			current = current.parent;
    		}
    		
    		return boardStack;
    	} else {
    		return null;
    	}
    	
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }	
}
}