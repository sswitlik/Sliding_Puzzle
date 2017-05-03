import sac.graph.AStar;
import sac.graph.BestFirstSearch;
import sac.graph.GraphSearchAlgorithm;
import sac.graph.GraphSearchConfigurator;
import sac.graph.GraphState;
import sac.graph.GraphStateImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    //-------------------------------------------------------------------
        public static void main(String[] arg)
        {
            Puzzle P1 = new Puzzle();
            
            P1.FromString("015827436");
            //P1.MixUp(10);
            System.out.println(P1.toString());
            
            //
            GraphSearchConfigurator conf = new GraphSearchConfigurator();
    		conf.setWantedNumberOfSolutions(Integer.MAX_VALUE);
    		
    		GraphSearchAlgorithm a = new AStar(P1);
    		
    		
    		a.execute();
    		Puzzle solution = ( Puzzle ) a.getSolutions().get(0);

    		List<GraphState> sols = a.getSolutions();

    		System.out.println("Solutions: " + sols.size());
    		//for (GraphState sol : sols) {
    			//System.out.println(sol);
    		//}

    		System.out.println("Time: " + a.getDurationTime() );
    		System.out.println("Closed: " + a.getClosedStatesCount());
    		System.out.println("Open: " + a.getOpenSet().size());
    		System.out.println("Solution: \n" + solution);
    		
        }
    //---------------------------------------------------------------------------
   
    public void FromString(String txt) {
    	int k = 0;
        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < 3; j++) {
            	board[i][j] = Byte.valueOf(txt.substring(k, k + 1));
                k++;
            }
        }
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
    	//generujemy 4 potomkow powstalych w wyniku
    	//przesuniec w kazdym mozliwym kierunku
    	//
    	//jesli potomek nie moze wykonac przesuniecia
    	//nie dodajemy go do listy potomkow
    	
    	List<GraphState> children = new LinkedList<GraphState>();
    	
    	for(int i=0;i<4;i++)	//4 to liczba kierunkow
    	{
    		Puzzle child = new Puzzle(this);
    		if (child.CanMove(i))
    		{
    			child.move(i);
    			children.add(child);
    			System.out.println(child.toString());
    		}
    	}
    	
        return children;
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

    //@Override
    @Override
    public int hashCode () {
    	return Arrays.hashCode(board);
    }
}
