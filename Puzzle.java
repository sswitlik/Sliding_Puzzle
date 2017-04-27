
import sac.graph.AStar;
import sac.graph.GraphState;
import sac.graph.GraphStateImpl;

import java.util.List;

	class Point
	{
		byte x;
		byte y;
		public Point()
		{
			x = 0;
			y = 0;
		}
	}
	
	enum Ruch
	{
		UP,
		DOWN,
		RIGHT,
		LEFT
	}
	
    public class Puzzle extends GraphStateImpl {
    	
    public static final int n = 3;
    public static final int n2 = n * n;
    public byte[][] board = null;
    private Point zero = new Point();  //EMPTY POINT LOCATION

    public Puzzle() {
        this.board = new byte[3][3];
        byte k = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                this.board[i][j] = k++;
            }
        }
    }

    public String toString() {
        String result = new String();
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                result = result + this.board[i][j] + " ";
            }
            result = result + "\n";
        }
        return result;
    }

        public static void main(String[] arg)
        {
            Puzzle P1 = new Puzzle();
            System.out.println(P1.toString());
            
            P1.move(Ruch.DOWN);
            System.out.println(P1.toString());
            
            P1.move(Ruch.RIGHT);
            System.out.println(P1.toString());

            P1.move(Ruch.UP);
            System.out.println(P1.toString());

            P1.move(Ruch.LEFT);
            System.out.println(P1.toString());

        }

   
    public void move(Ruch r)
    {
    	switch(r)
    	{
    	case RIGHT:
    		if(zero.y != n-1)
    		{
    			byte temp = board[zero.x][zero.y];
    			board[zero.x][zero.y] = board[zero.x][zero.y+1];
    			board[zero.x][zero.y+1] = temp;
    			zero.y++;
    		}
    		break;
    	case LEFT:
    		if(zero.y != 0)
    		{
    			byte temp = board[zero.x][zero.y];
    			board[zero.x][zero.y] = board[zero.x][zero.y-1];
    			board[zero.x][zero.y-1] = temp;
    			zero.y--;
    		}
    		break;
    	case DOWN:
    		if(zero.x != n-1)
    		{
    			byte temp = board[zero.x][zero.y];
    			board[zero.x][zero.y] = board[zero.x+1][zero.y];
    			board[zero.x+1][zero.y] = temp;
    			zero.x++;
    		}
    		break;
    	case UP:
    		if(zero.x != 0)
    		{
    			byte temp = board[zero.x][zero.y];
    			board[zero.x][zero.y] = board[zero.x-1][zero.y];
    			board[zero.x-1][zero.y] = temp;
    			zero.x--;
    		}
    		break;
    	}
    }
	    
    public void MixUp(int h_many)
    {
	Random generator = new Random();
 
	for(int i=0; i<h_many; i++) 
	{
   		move(generator.nextInt(4))); 		
	}
    }
	    
    //@Override
    public List<GraphState> generateChildren() {
        return null;
    }

    //@Override
    public boolean isSolution() {
    	int k=0;
    	for (int i=0; i<n; i++)
    		for (int j=0; j<n; j++)
    			if (k++ != board[i][j])
    				return false;
        return true;
    }
}
