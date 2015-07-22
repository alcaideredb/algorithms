import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private int N;
    private int[][] blocks;
    private int zeroX;
    private int zeroY;
    

	public Board(int[][] blocks) {
        N = blocks.length;
        this.blocks = new int[N][N];

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                this.blocks[i] = blocks[i];
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
        int count = 0;
        
        int inc = 1;
        int nsquared = N * N;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(blocks[i][j] != 0) 
                if(blocks[i][j] != ((i*N + j + 1) % (nsquared))) {
                    count++;
                }
            }
        }

        return count;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0)
                    continue;
                int row = (blocks[i][j] - 1) / N;
                int col = (blocks[i][j] - 1) % N;
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
		return null;
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

	public int[][] copyBoard() {
		int[][] newBlocks = new int[N][N];
		
		for(int i = 0;i < N; i++) {
			for(int j = 0; j < N; j++) {
				newBlocks[i][j] = blocks[i][j];
			}
		}
		
		return newBlocks;
	}
	
	public Iterable<Board> neighbors() {
        List<Board> newBoard = new ArrayList<>();
        
        if(this.zeroX > 0) {
        	int[][] leftBoard = copyBoard();
        	leftBoard[this.zeroY][this.zeroX] = leftBoard[this.zeroY][this.zeroX - 1];
        	leftBoard[this.zeroY][this.zeroX - 1] = 0;
        	newBoard.add(new Board(leftBoard));
        }
        
        if(this.zeroY > 0) {
        	int[][] upBoard = copyBoard();
        	upBoard[this.zeroY][this.zeroX] = upBoard[this.zeroY - 1][this.zeroX];
        	upBoard[this.zeroY - 1][this.zeroX] = 0;
        	newBoard.add(new Board(upBoard));
        }
        
        if(this.zeroX < N - 1) {
        	int[][] rightBoard = copyBoard();
        	rightBoard[this.zeroY][this.zeroX] = rightBoard[this.zeroY][this.zeroX + 1];
        	rightBoard[this.zeroY][this.zeroX + 1] = 0;
        	newBoard.add(new Board(rightBoard));
        }
        
        if(this.zeroY < N - 1) {
        	int[][] downBoard = copyBoard();
        	downBoard[this.zeroY][this.zeroX] = downBoard[this.zeroY + 1][this.zeroX];
        	downBoard[this.zeroY + 1][this.zeroX] = 0;
        	newBoard.add(new Board(downBoard));
        }
        
        return newBoard;
    }

    public String toString() {
        return null;
    }

    public static void main(String[] args) {
        int[][] sample = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board b = new Board(sample);
        System.out.println(b.hamming());

    }
}