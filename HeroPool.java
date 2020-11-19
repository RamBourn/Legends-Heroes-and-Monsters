import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A class representing a pool which consists of three types of heroes
 * @author Chuyang Zhou
 *
 */
public class HeroPool {
	private ArrayList<Hero> warriors;
	private ArrayList<Hero> paladins;
	private ArrayList<Hero> sorcerers;
	public HeroPool()
	{
		init();
	}
	public void init()
	{
		warriors=new ArrayList<>();
		sorcerers=new ArrayList<>();
		paladins=new ArrayList<>();
		String w="warrior";
		String p="paladin";
		String s="sorcere";
		//warriors
		warriors.add(new Hero(w,"Gaerdal_Ironhand",100,700,500,600,1354,7));
		warriors.add(new Hero(w,"Sehanine_Monnbow",600,700,800,500,2500,8));
		warriors.add(new Hero(w,"Muamman_Duathall",300,900,500,750,2546,6));
		warriors.add(new Hero(w,"Flandal_Steelskin",200,750,650,700,2500,7));
		warriors.add(new Hero(w,"Undefeated_Yoj",400,800,400,700,2500,7));
		warriors.add(new Hero(w,"Eunoia_Cyn",400,700,800,600,2500,6));
		//paladins
		paladins.add(new Hero(p,"Solonor_Thelandira",300,750,650,700,2500,7));
		paladins.add(new Hero(p,"Sehanine_Moonbow",300,750,700,700,2500,7));
		paladins.add(new Hero(p,"Skoraeus_Stonebones",250,650,600,350,2500,4));
		paladins.add(new Hero(p,"Garl_Glittergold",100,600,500,400,2500,5));
		paladins.add(new Hero(p,"Amaryllis_Astra",500,500,500,500,2500,5));
		paladins.add(new Hero(p,"Caliber_Heist",400,00,400,400,2500,8));
		//sorcerers
		sorcerers.add(new Hero(s,"Rillifane_Rallathil",1300,750,450,500,2500,9));
		sorcerers.add(new Hero(s,"Segojan_Earthcaller",900,800,500,650,2500,5));
		sorcerers.add(new Hero(s,"Reign_Havoc",800,800,800,800,2500,8));
		sorcerers.add(new Hero(s,"Reverie_Ashels",900,800,700,400,2500,7));
		sorcerers.add(new Hero(s,"Radiant_Ash",800,850,400,600,2500,6));

	}
	
	public String toString()
	{
		int num=1;
		String pool="";
		pool+="\nWarriors Available:\n";
		pool+="*****************\n";
		pool+="No.   Name   Mana   Strength   Agility   Dexterity   Money   Experience\n";
		pool+="---------------------------------------------------------------------\n";
		for(Hero h:warriors)
			pool+=num+++"     "+h;
		pool+="Sorcerers Available:\n";
		pool+="*****************\n";
		pool+="No.   Name   Mana   Strength   Agility   Dexterity   Money   Experience\n";
		pool+="---------------------------------------------------------------------\n";
		for(Hero h:sorcerers)
			pool+=num+++"     "+h;
		pool+="Paladins Available:\n";
		pool+="*****************\n";
		pool+="No.   Name   Mana   Strength   Agility   Dexterity   Money   Experience\n";
		pool+="---------------------------------------------------------------------\n";
		for(Hero h:paladins)
			pool+=num+++"     "+h;
		return pool;
	}
	public void chooseHero(int num,ArrayList<Hero> heroes)
	{
		Scanner in=new Scanner(System.in);
		ArrayList<Hero> pool=new ArrayList<>();
		pool.addAll(warriors);
		pool.addAll(sorcerers);
		pool.addAll(paladins);
		System.out.print(this);
		int choose;
		for(int i=0;i<num;i++)
		{
			int m=i+1;
			System.out.print("Choose the "+m+"-th hero(Enter the number):");
			choose=in.nextInt();
			heroes.add(pool.get(choose-1));
		}
	}
}
