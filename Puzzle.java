
import sac.graph.AStar;
import sac.graph.GraphState;
import sac.graph.GraphStateImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private Point zero = null;  //EMPTY POINT LOCATION

    public Puzzle() {
    	zero = new Point();
        this.board = new byte[3][3];
        byte k = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                this.board[i][j] = k++;
            }
        }
    }

    public Puzzle(Puzzle parent)
    {
    	//konstruktor kopiujacy
    	this.board = new byte[3][3];
        this.zero = new Point();
        	this.zero.x = parent.zero.x;
        	this.zero.y = parent.zero.y;
        
    	for(int i = 0; i < n; ++i) 
            for(int j = 0; j < n; ++j)
                this.board[i][j] = parent.board[i][j];
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
            
            //P1.generateChildren();
            
            P1.move(0);
            P1.move(2);
            
            System.out.println(P1.toString());
            P1.generateChildren();
            	
        }

   
    public void move(Ruch r)
    {
    	//zamiana miejscami dwoch wartosci board
    	//wartosci "zero" ustawiamy na miejsce znajdowania sie wartosci zero
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
	
    public void move(int r)
    {
    	//To samo co mow, ale podajemy kierunek int'em
    	switch(r)
    	{
    	case 0:		//RIGHT
    		if(zero.y != n-1)
    		{
    			byte temp = board[zero.x][zero.y];
    			board[zero.x][zero.y] = board[zero.x][zero.y+1];
    			board[zero.x][zero.y+1] = temp;
    			zero.y++;
    		}
    		break;
    	case 1:		//LEFT
    		if(zero.y != 0)
    		{
    			byte temp = board[zero.x][zero.y];
    			board[zero.x][zero.y] = board[zero.x][zero.y-1];
    			board[zero.x][zero.y-1] = temp;
    			zero.y--;
    		}
    		break;
    	case 2:		//DOWN
    		if(zero.x != n-1)
    		{
    			byte temp = board[zero.x][zero.y];
    			board[zero.x][zero.y] = board[zero.x+1][zero.y];
    			board[zero.x+1][zero.y] = temp;
    			zero.x++;
    		}
    		break;
    	case 3:		//UP
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
    
    private boolean CanMove(int r)
    {
    	//zwraca true jesli mozliwy jest ruch
    	//w zadanym kierunku
    	switch(r)
    	{
    	case 0:		//RIGHT
    		if(zero.y != n-1)
    			return true;
    		break;
    	case 1:		//LEFT
    		if(zero.y != 0)
    			return true;
    		break;
    	case 2:		//DOWN
    		if(zero.x != n-1)
    			return true;
    		break;
    	case 3:		//UP
    		if(zero.x != 0)
    			return true;
    		break;
    	}
    	return false;
    }
    
    public void MixUp(int h_many)
    {
    	//losowanie h_many ruchow
    	Random generator = new Random();
 
		for(int i=0; i<h_many; i++) 
		{
			move(generator.nextInt(4));
		}
    }
	
    //@Override
    public List<GraphState> generateChildren() 
    {
        return null;
    }

    //@Override
    public boolean isSolution() 
    {
    	//sprawdzam czy wartosci board sa poukladane rosnaco
    	int k=0;
    	for (int i=0; i<n; i++)
    		for (int j=0; j<n; j++)
    			if (k++ != board[i][j])
    				return false;
        return true;
    }
}
