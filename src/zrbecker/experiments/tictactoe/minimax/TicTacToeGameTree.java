package zrbecker.experiments.tictactoe.minimax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zrbecker.experiments.minimax.MinimaxGameTree;
import zrbecker.experiments.tictactoe.TicTacToeMove;
import zrbecker.experiments.tictactoe.TicTacToeMoveImpl;
import zrbecker.experiments.tictactoe.TicTacToeState;

public class TicTacToeGameTree implements MinimaxGameTree {
	// Could possibly avoid this maps by doing an observer pattern or something nicer.
	private Map<Integer, TicTacToeState> states;
	private Map<Integer, TicTacToeMove> actions;
	
	// I think TicTacToe is small enough that an int is fine, but if we were worried
	// about running out of ids, we could switch to strings, or something more reliable.
	private int nextUniqueId = 0;
	
	public TicTacToeGameTree(TicTacToeState initialState) {
		states = new HashMap<Integer, TicTacToeState>();
		actions = new HashMap<Integer, TicTacToeMove>();
	}
	
	@Override
	public Iterable<Integer> getActions(int node) {
		TicTacToeState state = states.get(node);
		List<Integer> actions = new ArrayList<Integer>();
		int player = state.getPlayer();
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				if (state.getCell(row, col) == 0) {
					TicTacToeMove move = new TicTacToeMoveImpl(row, col, player);
					int id = addAction(move);
					actions.add(id);
				}
			}
		}
		return actions;
	}

	@Override
	public int getNextState(int node, int action) {
		TicTacToeState state = states.get(node);
		TicTacToeMove move = actions.get(action);
		TicTacToeState newState = state.update(move);
		return addState(newState);
	}

	@Override
	public boolean isTerminal(int node) {
		TicTacToeState state = states.get(node);
		return state.getWinner() != 2;
	}

	@Override
	public int getValue(int node) {
		TicTacToeState state = states.get(node);
		int value = state.getWinner();
		return (value == 2) ? 0 : value; // We shouldn't be asking for value if value is 2, but just in case.
	}
	
	private int getNextId() {
		int id = nextUniqueId;
		nextUniqueId += 1;
		return id;
	}
	
	// TODO(zrbecker); For these we could check the maps for old states and reuse ids.
	private int addState(TicTacToeState state) {
		int stateId = getNextId();
		states.put(stateId, state);
		return stateId;
	}
	
	private int addAction(TicTacToeMove move) {
		int moveId = getNextId();
		actions.put(moveId, move);
		return moveId;
	}

}
