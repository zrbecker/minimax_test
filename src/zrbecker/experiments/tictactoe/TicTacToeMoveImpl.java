package zrbecker.experiments.tictactoe;

public class TicTacToeMoveImpl implements TicTacToeMove {
	private int row;
	private int col;
	private int player;
	
	public TicTacToeMoveImpl(int row, int col, int player) {
		// TODO(zrbecker): Ensure row and col is 0, 1, or 2.
		// TODO(zrbecker): Ensure player is -1 or 1.
		this.row = row;
		this.col = col;
		this.player = player;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getPlayer() {
		return player;
	}
}
