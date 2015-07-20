import WeightedQuickUnionUf;

public class Percolation {
	private boolean grid[][];
	private WeightedQuickUnionUf uf;
	
	public Percolation(int N) {
		grid = new boolean[N][N];
		uf = new WeightedQuickUnionUf(N * N);
	}

	public void open(int i, int j) {
		grid[i][j] = TRUE;
	}

	public boolean isOpen(int i, int j) {
		return grid[i][j];
	}

	public boolean isFull(int i, int j) {

	}
}
