public class Board {
    private int N;
    private int[][] blocks;

    public Board(int[][] blocks) {
        N = blocks.length;
        this.blocks = new int[N][N];

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                this.blocks[i] = blocks[i];
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
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0)
                    continue;
                int row = (blocks[i][j] - 1) / n;
                int col = (blocks[i][j] - 1) % n;
                manhattan += (Math.abs(i - row) + Math.abs(j - col));
            }
        return manhattan;
    }   

    public boolean isGoal() {

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

    public boolean equals(Object y) {
        return false;
    }

    public Iterable<Board> neighbors() {
        return null;
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