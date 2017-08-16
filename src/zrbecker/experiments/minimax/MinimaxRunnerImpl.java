package zrbecker.experiments.minimax;

public class MinimaxRunnerImpl implements MinimaxRunner {
	MinimaxGameTree game;
	
	public MinimaxRunnerImpl(MinimaxGameTree game) {
		this.game = game;
	}
	
	// Taken from Wikipedia
	public double minimax(int node, int depth, boolean maximizingPlayer) {
		if (depth == 0 || game.isTerminal(node)) {
			return game.getValue(node);
		}
		
		if (maximizingPlayer) {
			double bestValue = Double.NEGATIVE_INFINITY;
			Iterable<Integer> actions = game.getActions(node);
			for (int action : actions) {
				int child = game.getNextState(node, action);
				double value = minimax(child, depth - 1, false);
				bestValue = Math.max(bestValue, value);
			}
			return bestValue;
		} else {
			double bestValue = Double.POSITIVE_INFINITY;
			Iterable<Integer> actions = game.getActions(node);
			for (int action : actions) {
				int child = game.getNextState(node, action);
				double value = minimax(child, depth - 1, true);
				bestValue = Math.min(bestValue, value);
			}
			return bestValue;
		}
	}
}
