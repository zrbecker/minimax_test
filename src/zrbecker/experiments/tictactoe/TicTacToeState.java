package zrbecker.experiments.tictactoe;

public interface TicTacToeState {
	TicTacToeState update(TicTacToeMove cell);
	
	int getCell(int row, int col);
	
	int getPlayer();

	// Returns 1 for X wins, 0 for Tie, -1 for O wins, 2 for undecided.
	int getWinner();
}
