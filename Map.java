import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * A class representing the map of the game
 * @author Chuyang Zhou
 *
 */
public class Map {
	private String[][] map;
	private String view;               //the view of the map
	private int size;                  //the row and column of the map
	private Set<Integer> MPoints;      //set of market points to avoid repetition
	private Set<Integer> XPoints;      //set of inaccessible points to avoid repetition
	private double MProb;              //probability of market points in the map
	private double XProb;              //probability of inaccessible points in the map
	private int curX;                  //current row of the heroes
	private int curY;                  //current column of the heroes
	private double FProb;              //probability of confronting monsters in a common area
	private boolean buy;               //check if heroes just pass a market
	public Map()
	{
		MProb=0.3;
		XProb=0.2;
		FProb=0.3;
		view="";
		size=10;
		curX=0;
		curY=0;
		buy=false;
		map=new String[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				map[i][j]="   ";
		MPoints=new HashSet<>();
		XPoints=new HashSet<>();
		init();
	}
	/**
	 * the map of the game can be customized
	 * @param size      number of row
	 * @param MProb     probability of market points
	 * @param XProb     probability of inaccessible points
	 * @param FProb     probability of confronting monsters in common areas
	 */
	public Map(int size,double MProb,double XProb,double FProb)
	{
		this.MProb=MProb;
		this.XProb=XProb;
		this.FProb=FProb;
		view="";
		this.size=size;
		curX=0;
		curY=0;
		buy=false;
		map=new String[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				map[i][j]="   ";
		MPoints=new HashSet<>();
		XPoints=new HashSet<>();
		init();
	}
	public void init()
	{
		int M=(int) (size*size*MProb);
		int X=(int) (size*size*XProb);
		int i=0;
		int total=size*size;
		Random random=new Random();
		while(MPoints.size()<M)
		{
			i=random.nextInt(total);
			if(XPoints.contains(i))
				continue;
			else
				MPoints.add(i);
		}
		while(XPoints.size()<X)
		{
			i=random.nextInt(total);
			if(MPoints.contains(i))
				continue;
			else
				XPoints.add(i);
		}
		while(true)
		{
			curX=random.nextInt(size);
			curY=random.nextInt(size);
			if(MPoints.contains(curX*size+curY)||XPoints.contains(curX*size+curY))
				continue;
			else
				break;
		}
		int m=0;
		int n=0;
		for(int j:MPoints)
		{
			m=j/size;
			n=j%size;
			map[m][n]=" M ";
		}
		for(int j:XPoints)
		{
			m=j/size;
			n=j%size;
			map[m][n]=" X ";
		}
		map[curX][curY]=" O ";
		update();
	}
	//update the map and print it
	public void update() 
	{	
		view="";
		String line="";
		for(int i=0;i<size;i++)
		{
			line+="*---";
		}
		line+="*\n";
		for(int i=0;i<size;i++)
		{
			view+=line;
			for(int j=0;j<size;j++)
			{
				view+="|";
				view+=map[i][j];
			}
			view+="|\n";
		}
		view+=line;
		int row=curX+1;
		int column=curY+1;
		System.out.println("\nMap: Heroes are in ("+row+","+column+")\n"+view);
	}
	public String toString()
	{
		return view;
	}
	/**
	 * heroes make their move in the map
	 * @return  the result of the move
	 */
	public String move()
	{
		Scanner in=new Scanner(System.in);
		String choose="";
		String res="";
		Random random=new Random();
		do
		{
			System.out.print("\nW/w:Move Up\nS/s:Move Down\nA/a:Move Left\nD/d:Move Right\nQ/q:Quit\nMake your move:");
			choose=in.next();
			if(choose.equals("W")||choose.equals("w"))
			{
				if(curX==0||map[curX-1][curY].equals(" X "))
				{
					System.out.println("\nThis move is inaccessible! Move again\n");
					continue;
				}
				else if(map[curX-1][curY].equals("   "))
				{
					map[curX-1][curY]=" O ";
					checkBuy();
					curX=curX-1;
					if(random.nextInt(10)<=(int)(FProb*10)) //there is a probability that heroes are confronted with monsters
						res="fight";
					else
						res="ok";
				}
				else
				{
					map[curX-1][curY]="M*O";       //heroes enter a market
					checkBuy();
					buy=true;
					curX=curX-1;
					res="market";
				}
				update();
				return res;
			}
			else if(choose.equals("S")||choose.equals("s"))
			{
				if(curX==size-1||map[curX+1][curY].equals(" X "))
				{
					System.out.println("\\nThis move is inaccessible! Move again\\n");
					continue;
				}
				else if(map[curX+1][curY].equals("   "))
				{
					map[curX+1][curY]=" O ";
					checkBuy();
					curX=curX+1;
					if(random.nextInt(10)<=(int)(FProb*10))
						res="fight";
					else
						res="ok";
				}
				else
				{
					map[curX+1][curY]="M*O";
					checkBuy();
					buy=true;
					curX=curX+1;
					res="market";
				}
				update();
				return res;
			}
			else if(choose.equals("A")||choose.equals("a"))
			{
				if(curY==0||map[curX][curY-1].equals(" X "))
				{
					System.out.println("\\nThis move is inaccessible! Move again\\n");
					continue;
				}
				else if(map[curX][curY-1].equals("   "))
				{
					map[curX][curY-1]=" O ";
					checkBuy();
					curY=curY-1;
					if(random.nextInt(10)<=(int)(FProb*10))
						res="fight";
					else
						res="ok";
				}
				else
				{
					map[curX][curY-1]="M*O";
					checkBuy();
					buy=true;
					curY=curY-1;
					res="market";
				}
				update();
				return res;
			}
			else if(choose.equals("D")||choose.equals("d"))
			{
				if(curY==size-1||map[curX][curY+1].equals(" X "))
				{
					System.out.println("\\nThis move is inaccessible! Move again\\n");
					continue;
				}
				else if(map[curX][curY+1].equals("   "))
				{
					map[curX][curY+1]=" O ";
					checkBuy();
					curY=curY+1;
					if(random.nextInt(10)<=(int)(FProb*10))
						res="fight";
					else
						res="ok";
				}
				else
				{
					map[curX][curY+1]="M*O";
					checkBuy();
					buy=true;
					curY=curY+1;
					res="market";
				}
				update();
				return res;
			}
			else
			{
				return "quit";
			}	
		}while(!choose.equals("Q")||choose.equals("q")||choose.equals("w")||choose.equals("W")||
				choose.equals("S")||choose.equals("s")||choose.equals("A")||choose.equals("a")||
				choose.equals("d")||choose.equals("D"));
		return "ok";
	}
	//check if heroes just passes a market
	public void checkBuy()
	{
		if(buy)
			map[curX][curY]=" M ";
		else
		{
			map[curX][curY]="   ";
			buy=false;
		}
	}
}
