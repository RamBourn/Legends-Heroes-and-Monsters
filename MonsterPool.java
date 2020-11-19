import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * A class representing three types of monsters
 * @author Chuyang Zhou
 *
 */
public class MonsterPool {
	private ArrayList<ArrayList<Monster>> monsters;

	public MonsterPool()
	{
		monsters=new ArrayList<>();
		init();
	}
	public void init()
	{
		//level 1
		ArrayList<Monster> level_1=new ArrayList<>();
		level_1.add(new Monster("Natsunomeryu",1,100,200,10));
		level_1.add(new Monster("BigBad-Wolf",1,150,250,15));
		level_1.add(new Monster("Aim-Haborym",1,450,350,5));
		level_1.add(new Monster("Casper",1,100,100,50));
		monsters.add(level_1);
		//level 2
		ArrayList<Monster> level_2=new ArrayList<>();
		level_2.add(new Monster("Chrysophylax",2,200,500,20));
		level_2.add(new Monster("WickedWitch",2,250,350,25));
		level_2.add(new Monster("Andrealphus",2,600,500,40));
		monsters.add(level_2);
		//level 3
		ArrayList<Monster> level_3=new ArrayList<>();
		level_3.add(new Monster("Desghidorrah",3,300,400,35));
		level_3.add(new Monster("Brandobaris",3,350,450,30));
		level_3.add(new Monster("Andromalius",3,550,450,25));
		monsters.add(level_3);
		//level 4
		ArrayList<Monster> level_4=new ArrayList<>();
		level_4.add(new Monster("BunsenBurner",4,400,500,45));
		level_4.add(new Monster("Aasterinian",4,400,500,45));
		level_4.add(new Monster("Chiang-shih",4,700,600,40));
		monsters.add(level_4);
		//level 5
		ArrayList<Monster> level_5=new ArrayList<>();
		level_5.add(new Monster("Kas-Ethelinh",5,600,500,60));
		level_5.add(new Monster("St-Shargaas",5,550,650,55));
		level_5.add(new Monster("FallenAngel",5,800,700,50));
		monsters.add(level_5);
		//level 6
		ArrayList<Monster> level_6=new ArrayList<>();
		level_6.add(new Monster("Phaarthurnax",6,600,700,60));
		level_6.add(new Monster("Igneel",6,600,400,60));
		level_6.add(new Monster("DocOck",6,600,600,55));
		level_6.add(new Monster("Ereshkigall",6,950,450,35));
		monsters.add(level_6);
		//level 7
		ArrayList<Monster> level_7=new ArrayList<>();
		level_7.add(new Monster("TheScaleless",7,700,600,75));
		level_7.add(new Monster("Cyrrollalee",7,700,800,75));
		level_7.add(new Monster("Melchiresas",7,350,150,75));
		monsters.add(level_7);
		//level 8
		ArrayList<Monster> level_8=new ArrayList<>();
		level_8.add(new Monster("TheWeatherbe",8,800,900,80));
		level_8.add(new Monster("Kiaransalee",8,850,950,85));
		level_8.add(new Monster("Jormunngand",8,600,900,20));
		monsters.add(level_8);
		//level 9
		ArrayList<Monster> level_9=new ArrayList<>();
		level_9.add(new Monster("D-Maleficent",9,900,950,85));
		level_9.add(new Monster("BlueEyesWhite",9,900,600,75));
		level_9.add(new Monster("St-Yeenoghu",9,950,850,90));
		level_9.add(new Monster("Rakkshasass",9,550,600,35));
		monsters.add(level_9);
		//level 10
		ArrayList<Monster> level_10=new ArrayList<>();
		level_10.add(new Monster("Alexstraszan",10,1000,9000,55));
		level_10.add(new Monster("Exodia",10,1000,1000,50));
		level_10.add(new Monster("Taltecuhtli",10,300,200,50));
		monsters.add(level_10);
	}
	public String toString()
	{
		String pool="";
		for(ArrayList<Monster> array:monsters)
			for(Monster m:array)
				pool+=m;
		return pool;
	}
	public ArrayList<Monster> generate(int num,int level)
	{
		Random random=new Random();
		int i;
		HashSet<Integer> set=new HashSet<>();
		ArrayList<Monster> tmp=monsters.get(level-1);
		ArrayList<Monster> res=new ArrayList<>();
		while(set.size()<num)
		{
			i=random.nextInt(tmp.size());
			set.add(i);
		}
		for(int j:set)
			res.add(tmp.get(j));
		return res;
	}
}
