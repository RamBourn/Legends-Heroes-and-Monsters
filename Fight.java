import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing session of a fight
 * @author Chuyang Zhou
 *
 */
public class Fight {
	private MonsterPool monsterPool;         //the monster to generate monsters which fight with heroes
	private ArrayList<Hero> heroes;           
	private ArrayList<Monster> monsters;     //monsters generated by pool to fight
	private int round;                      //number of round
	private String winner;                  //winner of the fight
	private int level;                      //the highest level of heroes
	private boolean over;                  //game is over
	/**
	 * 
	 * @param heroes    heroes which will fight with monsters
	 */
	public Fight(ArrayList<Hero> heroes)
	{
		this.heroes=heroes;
		monsterPool=new MonsterPool();
		monsters=new ArrayList<>();
		round=0;
		winner="";
		level=0;
		over=false;
		init();
	}
	//find the highest level of heroes
	public int highest()
	{
		int highest=0;
		for(Hero h:heroes)
		{
			if(h.getLevel()>highest)
				highest=h.getLevel();
		}
		return highest;
	}
	public void init()
	{
		level=highest();
		monsters=monsterPool.generate(heroes.size(),level);
		System.out.println("*********\n**FIGHT**\n*********\n\nTerrible! Heroes are confronted with monsters. Start Fighting!");
		startRound();
	}
	//start the fight
	public void startRound()
	{
		round++;
		String res="";
		System.out.println("\nRound "+round+":");
		showMonsters();
		Legend.showInfo(heroes);
		for(int i=0;i<heroes.size();i++)
			if(!heroes.get(i).isFaint())
				decide(i);
		res=check();
		end(res);
		if(over)
			return;
		for(int i=0;i<monsters.size();i++)
			if(!monsters.get(i).isDead())
				attack(i);
		res=check();
		end(res);
		if(over)
			return;
		else
		{
			for(Hero h:heroes)
			{
				if(!h.isFaint())                  //a hero will recover 10% after a round if he is not faint
				{
					h.setHp((int)(h.getHp()*1.1));
					h.setMana((int)(h.getMana()*1.1));
				}
			}
			startRound();
		}	
	}
	// show information of monsters
	public void showMonsters()
	{
		int num=1;
		String view="";
		view+="Monsters\n************************\n";
		view+="No.   Name   Hp   Level   Damage   Defense   Dodge\n";
		view+="---------------------------------------------------------\n";
		for(Monster m:monsters)
			view+=num+++"     "+m;
		System.out.println("\n"+view);
	}
	// decide what action each hero will take
	public void decide(int i)
	{
		Scanner in =new Scanner(System.in);
		int choose;
		Hero hero=heroes.get(i);
		int target=0;
		if(!monsters.get(i).isDead())
			target=i;
		else
		{
			for(int j=0;j<monsters.size();j++)
			{
				if(!monsters.get(j).isDead())
				{
					target=j;
					break;
				}		
			}
		}
		System.out.print(hero.getName()+", it is your turn to fight\n"
				+ "Option          No.\n"
				+ "Attack           1 \n"
				+ "Use Potion       2 \n"
				+ "Use Spell        3\n"
				+ "Change Weapon    4 \n"
				+ "Change Armour    5 \n"
				+ "Show Information 6\nMake your option:");
		choose=in.nextInt();
		if(choose!=6)
			System.out.println("\nThe result of option of hero "+hero.getName()+":");
		if(choose==1)
			hero.attack(monsters.get(target));
		else if(choose==2)
			hero.usePotion();
		else if(choose==3)
			hero.usrSpell(monsters.get(target));
		else if(choose==4)
			hero.changeWeapon();
		else if(choose==5)
			hero.changeArmour();
		else 
		{
			showMonsters();
			Legend.showInfo(heroes);
			decide(i);
		}
	}
	// monsters will automatically attack heroes in every round
	public void attack(int i)
	{
		System.out.println("\nThe results of attack by monsters:");
		Monster monster=monsters.get(i);
		int target=0;
		if(!heroes.get(i).isFaint())
			target=i;
		else
		{
			for(int j=0;j<heroes.size();j++)
			{
				if(!heroes.get(j).isFaint())
				{
					target=j;
					break;
				}
			}
		}
		monster.attack(heroes.get(target));
	}
	//check if there is a winner
	public String check()
	{
		boolean over=true;
		boolean lose=true;
		for(Hero h:heroes)
		{
			if(!h.isFaint())
			{
				lose=false;
			}
		}
		for(Monster m:monsters)
		{
			if(!m.isDead())
			{
				if(!lose)
				{
					over=false;
				}
			}
		}
		if(lose&&!over)
		{
			for(Hero h:heroes)
			{
				h.lose();
			}
			return "monster";
		}
		else if(!lose&&over)
			return "heroes";
		else if(!lose&&!over)
			return "ok";
		else return "tie";
	}
	// announcement after the fight
	public void end(String res)
	{
		if(res.equals("ok"))
			return;
		else if(res.equals("heroes"))
		{
			System.out.println("Heroes win!");
		}
		else 
		{
			System.out.println("Monsters win!");
		}
		for(Hero h:heroes)
			h.afterFight(level);
		over=true;
	}
}
