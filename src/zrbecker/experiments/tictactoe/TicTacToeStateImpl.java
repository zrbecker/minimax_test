package zrbecker.experiments.tictactoe;

public class TicTacToeStateImpl implements TicTacToeState {
	// TODO(zrbecker): Board cell values should be an enumerable or something better than an int.
	// TODO(zrbecker): This representation can be made more efficient by making a TicTacToe state a list of
	// moves that have occurred up to this point. Then when a new state is created, we do not need to copy the
	// board. Should use efficient data structure for querying cells and determining winner.
	private int[] board;
	private int player;
	
	public TicTacToeStateImpl() {
		this(new int[9], 1);
	}
	
	public TicTacToeStateImpl(int[] board, int player) {
		// TODO(zrbecker): Ensure board is correct length.
		this.board = board;
		this.player = player;
	}
	
	public TicTacToeStateImpl update(TicTacToeMove move) {
		int index = 3 * move.getRow() + move.getCol();
		// TODO(zrbecker): Check move is valid.
		int[] newBoard = board.clone();
		newBoard[index] = move.getPlayer();
		return new TicTacToeStateImpl(newBoard, -player);
	}
	
	public int getCell(int row, int col) {
		return board[3 * row + col];
	}
	
	public int getPlayer() {
		return player;
	}
	
	public int getWinner() {
		int rowWinner = checkRows();
		if (rowWinner != 0) {
			return rowWinner;
		}
		int colWinner = checkCols();
		if (colWinner != 0) {
			return colWinner;
		}
		int diagWinner = checkDiags();
		if (diagWinner != 0) {
			return diagWinner;
		}
		// Check ties
		for (int index = 0; index < 9; ++index) {
			if (board[index] == 0) {
				return 2; // Not a tie, game is undecided.
			}
		}
		return 0;
	}
	
	private int checkRows() {
		for (int row = 0; row < 3; ++row) {
			if (board[3 * row] == board[3 * row + 1] && board[3 * row] == board[3 * row + 2]) {
				if (board[3 * row] == 1) {
					return 1;
				} else if (board[3 * row] == -1) {
					return -1;
				}
			}
		}
		return 0;
	}
	
	private int checkCols() {
		for (int col = 0; col < 3; ++col) {
			if (board[0 + col] == board[3 + col] && board[0 + col] == board[6 + col]) {
				if (board[0 + col] == 1) {
					return 1;
				} else if (board[0 + col] == -1) {
					return -1;
				}
			}
		}
		return 0;
	}
	
	private int checkDiags() {
		if (board[3 * 0 + 0] == board[3 * 1 + 1] && board[3 * 0 + 0] == board[3 * 2 + 2]) {
			if (board[3 * 0 + 0] == 1) {
				return 1;
			} else if (board[3 * 0 + 0] == 1) {
				return -1;
			}
		}
		if (board[3 * 0 + 2] == board[3 * 1 + 1] && board[3 * 0 + 2] == board[3 * 2 + 0]) {
			if (board[3 * 0 + 0] == 1) {
				return 1;
			} else if (board[3 * 0 + 0] == 1) {
				return -1;
			}
		}
		return 0;
	}
}
