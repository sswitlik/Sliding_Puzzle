
import sac.State;
import sac.StateFunction;

public class HFunctionMisplacedTiles extends StateFunction {

	@Override
    public double calculate(State state) 
	{
		Puzzle puz = (Puzzle) state;
		double h = 0.0;
		
		int k = 0;
		for (int i= 0; i < puz.n; i++) {
			for (int j = 0; j < puz.n; j++) {
				if (puz.board[j][i] != k)
					h += 1.0;
				k++;
			}
		}
		return h;
	}
}
