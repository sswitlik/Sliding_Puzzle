
import sac.State;
import sac.StateFunction;

public class HFunctionMisplacedTiles extends StateFunction {

	@Override
    public double calculate (State state) {
		Puzzle slidingPuzzle = ( Puzzle ) state;
		double h = 0.0;
		for (int i = 0; i < slidingPuzzle .board.length; i++) {
			if ((i != slidingPuzzle.emptyIndex ) && ( slidingPuzzle .board[i] != i))
				h += 1.0;
		}
		return h;
    }
}
