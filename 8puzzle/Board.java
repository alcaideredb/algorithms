import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private int N;
    private int[][] blocks;
    private int zeroX;
    private int zeroY;
    private int moves;
    
	public Board(int[][] blocks) {
		this(blocks,0);
    }       

	private Board(int[][] blocks, int moves) {
		this.moves = moves;
		N = blocks.length;
		this.blocks = new int[N][N];

	    for (int i = 0; i < N; i++) {
	        for(int j = 0; j < N; j++) {
	            this.blocks[i][j] = blocks[i][j];
	            if(blocks[i][j] == 0) {
	              	this.zeroY = i;
	               	this.zeroX = j;
	            }
	         }
	    }		
	}
	
    public int dimension() {
        return N;
    }
   
    public int hamming() {
        int count = moves;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (blocks[i][j] != 0 && blocks[i][j] != goalValue(i, j))  
            		count++;
            }
        }

        return count;
    }
    
    private int goalValue(int i, int j) {
    	if(i == dimension()-1 && j == dimension()-1)
    		return 0;
    	else
    		return (i*dimension()) + j + 1;
    }

    public int manhattan() {
        int manhattan = moves;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0)
                    continue;
                int row = (blocks[i][j] - 1) / N;
                int col = (blocks[i][j] - 1) - (row*N);
                manhattan += (Math.abs(i - row) + Math.abs(j - col));
            }
        return manhattan;
    }   

    public boolean isGoal() {
    	int nsquared = N*N;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(blocks[i][j] != ((i*N + j + 1) % (nsquared))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin() {
    	int[][] twinBoard = new int[N][N];
    	boolean flag = false;
    	
    	for (int i = 0; i < N; i++) {    		
    		for (int j = 0; j < N; j++) {
    			twinBoard[i][j] = this.blocks[i][j];
    		}
    	}
    	
    	for (int i = 0; i < N; i++) {    		
    		if(flag) break;
    		for (int j = 1; j < N; j++) {
    			if(twinBoard[i][j] != 0 && twinBoard[i][j-1] != 0) {
    				int temp = twinBoard[i][j];
    				twinBoard[i][j] = twinBoard[i][j-1];
    				twinBoard[i][j-1] = temp;
    				flag = true;
    				break;
    			}
    		}
    	}
    	
    	return new Board(twinBoard, moves);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(blocks, other.blocks))
			return false;
		return true;
	}

	private int[][] copyBoard() {
		int[][] newBlocks = new int[N][N];
		
		for(int i = 0;i < N; i++) {
			for(int j = 0; j < N; j++) {
				newBlocks[i][j] = blocks[i][j];
			}
		}
		
		return newBlocks;
	}
	
	public Iterable<Board> neighbors() {
        Queue<Board> newBoard = new Queue<Board>();
        if(this.zeroX > 0) {
        	int[][] leftBoard = copyBoard();
        	leftBoard[this.zeroY][this.zeroX] = leftBoard[this.zeroY][this.zeroX - 1];
        	leftBoard[this.zeroY][this.zeroX - 1] = 0;
        	newBoard.enqueue(new Board(leftBoard, moves+1));
        }
        
        if(this.zeroY > 0) {
        	int[][] upBoard = copyBoard();
        	upBoard[this.zeroY][this.zeroX] = upBoard[this.zeroY - 1][this.zeroX];
        	upBoard[this.zeroY - 1][this.zeroX] = 0;
        	newBoard.enqueue(new Board(upBoard, moves+1));
        }
        
        if(this.zeroX < N - 1) {
        	int[][] rightBoard = copyBoard();
        	rightBoard[this.zeroY][this.zeroX] = rightBoard[this.zeroY][this.zeroX + 1];
        	rightBoard[this.zeroY][this.zeroX + 1] = 0;
        	newBoard.enqueue(new Board(rightBoard, moves+1));
        }
        
        if(this.zeroY < N - 1) {
        	int[][] downBoard = copyBoard();
        	downBoard[this.zeroY][this.zeroX] = downBoard[this.zeroY + 1][this.zeroX];
        	downBoard[this.zeroY + 1][this.zeroX] = 0;
        	newBoard.enqueue(new Board(downBoard, moves+1));
        }
        
        return newBoard;
    }

	public String toString() {
	    StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            s.append(String.format("%2d ", blocks[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}

    public static void main(String[] args) {}
}