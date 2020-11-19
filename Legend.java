import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing the game flow
 * @author Chuyang Zhou
 *
 */
public class Legend {
	private Map map;
	private ArrayList<Hero> heroes;
	private HeroPool heroPool;
	private Market market;
	public Legend()
	{
		heroPool=new HeroPool();
		market=new Market();
		heroes=new ArrayList<>();
		init();
	}
	public void init()
	{
		Scanner in=new Scanner(System.in);
		int num=0;
		String introduction="Welcom to Legend-Heroes and Monsters!\n"
				+ "In the map:\n'O':Heroes\n'M':Market\n'X':Inaccessible Area\n'M*O':Heroes in Market\n"
				+ "When choose a hero or any item, you just need to enter the number\n";
		System.out.println(introduction);
		do
		{
			System.out.print("Enter the number of heroes(1~3):\n");
			num=in.nextInt();
			if(num>3||num<1)
				System.out.println("This input is wrong! Enter again");
		}while(num>3||num<1);

		heroPool.chooseHero(num,heroes);
		showInfo(heroes);
		map=new Map();
		start();
	}
	//show the information of a team of heroes in a readable way
	public static void showInfo(ArrayList<Hero> heroes)
	{
		int num=1;
		String view="";
		view+="\nHeroes Information\n********************************\n";
		view+="No.   Name   Hp   Level   Mana   Strength   Agility   Dexterity   Money   Experience   Weapons   "
				+ "Armours   Potions   Spells\n";
		view+="--------------------------------------------------------------------------------------------------------\n";
		for(Hero h:heroes)
		{
			view+=num+++"     "+h.info();
		}
		System.out.println(view);
	}
	// start the game
	public void start() 
	{
		Scanner in=new Scanner(System.in);
		int choose;
		int num;
		String res;
		String view="Option            No.\n";
		view+="Move On            1\n";
		view+="Change Weapons     2\n";
		view+="Change Armours     3\n";
		view+="Take Potions       4\n";
		view+="Show Information   5\n";
		view+="Quit               6\n";
		while(true)
		{
			System.out.println("\nThis place is safa, you can have a rest and make some options"
					+ "\n"+view+"Make your option:");
			choose=in.nextInt();
			if(choose==1)
			{
				res=map.move();
				if(res.equals("ok"))
					start();
				else if(res.equals("fight"))
					new Fight(heroes);
				else 
				{
					market.market(heroes);
					map.move();
				}
			}
			else if(choose==2)
				for(Hero h:heroes)
					h.changeWeapon();
			else if(choose==3)
				for(Hero h:heroes)
					h.changeArmour();
			else if(choose==4)
				for(Hero h:heroes)
					h.usePotion();
			else if(choose==5)
				showInfo(heroes);
			else
				break;
		}
	}
}
