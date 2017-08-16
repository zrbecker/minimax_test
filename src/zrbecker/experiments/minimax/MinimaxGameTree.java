package zrbecker.experiments.minimax;

public interface MinimaxGameTree {
	Iterable<Integer> getActions(int node);
	
	int getNextState(int node, int action);
	
	boolean isTerminal(int node);
	
	int getValue(int node);
}
