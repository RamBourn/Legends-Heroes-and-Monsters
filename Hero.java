import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing a single hero
 * @author Chuyang zhou
 *
 */
public class Hero extends Living implements ItemHolder {
	private String type;
	private int mana;
	private int strength;
	private int agility;
	private int dexterity;
	private int money;
	private int exp;
	private ArrayList<Weapon> weapons;     //weapon owned by the hero
	private ArrayList<Potion> potions;     //potions owned by the hero
	private ArrayList<Spell> spells;       // spells owned by the hero
	private ArrayList<Armour> armours;     // armours owned by the hero
	private Weapon curW;                  //current weapon held by the hero
	private Armour curA;				  //current armour wore by the hero
	private boolean faint;
	private int hp_;                      //the hp of the hero before a fight
	private boolean lose;                 //if the hero is lost
	public Hero(String type,String name,int mana,int strength,int agility,int dexterity,int money,int exp)
	{
		super(name,1);
		this.type=type;
		this.mana=mana;
		this.strength=strength;
		this.agility=agility;
		this.dexterity=dexterity;
		this.money=money;
		this.exp=exp;
		weapons=new ArrayList<>();
		potions=new ArrayList<>();
		spells=new ArrayList<>();
		armours=new ArrayList<>();
		curW=null;
		curA=null;
		faint=false;
		hp_=hp;
		lose=false;
	}
	public String toString()
	{
		String hero=name+"    "+mana+"    "+strength+"    "+agility+"    "+dexterity+"    "+money+"    "+exp+"\n";
		return hero;
	}
	//attributes will boost when a hero levels up
	public void levelUp()
	{
		mana=(int)(mana*1.1);
		exp=0;
		if(type.equals("warrior"))
		{
			strength=(int)(strength*1.1);
			agility=(int)(agility*1.05);
			dexterity=(int)(dexterity*1.05);
		}
		else if(type.equals("sorcerer"))
		{
			strength=(int)(strength*1.05);
			agility=(int)(agility*1.05);
			dexterity=(int)(dexterity*1.1);
		}
		else
		{
			strength=(int)(strength*1.05);
			agility=(int)(agility*1.1);
			dexterity=(int)(dexterity*1.05);
		}
	}
	// attack made by the hero to a monster
	public void attack(Living monster)
	{
		int damage=0;
		if(curW==null)	
			damage=(int)((strength)*0.05);
		else
			damage=(int)((strength+curW.getDamage())*0.05);
		int res=monster.defense(damage);
		if(res>0)
			System.out.println(name+"(hero) made "+res+" damage to "+monster.getName()+"(monster)");
	}
	//defense made by the hero from an attack of a monster
	public int defense(int damage)
	{
		int total=0;
		if(Math.random()<=agility*0.002)
		{
			System.out.println(name+"(hero) dodged this attack from monsters");
			return total;
		}
		else
			{
				if(curA==null)
					total=damage;
				else
					total=(int)(damage-curA.getReduction()*0.01);
				hp=hp-total;
			}
		if(hp<=0)
		{
			faint=true;
			System.out.println(name+"(hero) is faint");
		}
		return total;
	}
	public boolean isFaint()
	{
		return faint;
	}
	//some attributes will change after a fight
	public void afterFight(int high)
	{
		if(lose&&faint)
		{
			money/=2;
			hp=hp_/2;
			hp_=hp;
		}
		else if(!lose&&faint)
		{
			hp=hp_/2;
			hp_=hp;
		}
		else
		{
			exp+=2;
			money+=level*100;
			if(exp>=level*10)
				levelUp();
		}
	}
	public int getMoney()
	{
		return money;
	}
	public void setMoney(int money)
	{
		this.money=money;
	}
	public int getMana()
	{
		return mana;
	}
	public void setMana(int mana)
	{
		this.mana=mana;
	}
	public int getStrength()
	{
		return strength;
	}
	public void setStrength(int strength)
	{
		this.strength=strength;
	}
	public int getAgility()
	{
		return agility;
	}
	public void setAgility(int agility)
	{
		this.agility=agility;
	}
	public int getDexterity()
	{
		return dexterity;
	}
	public void setDexterity(int dexterity)
	{
		this.dexterity=dexterity;
	}
	public ArrayList<Weapon> getWeapons()
	{
		return weapons;
	}
	public ArrayList<Armour> getArmours()
	{
		return armours;
	}
	public ArrayList<Potion> getPotions()
	{
		return potions;
	}
	public ArrayList<Spell> getSpells()
	{
		return spells;
	}
	public void lose()
	{
		lose=true;
	}
	//out put the information of a hero in a readable way
	public String info()
	{
		String view="";
		view+=name+"    "+hp+"     "+level+"     "+mana+"    "+strength+"    "+agility+"    "+dexterity+"    "+money+"    "+exp+"     ";
		for(Weapon w:weapons)
			view+=w.getName()+"/";
		view+="     ";
		for(Armour a:armours)
			view+=a.getName()+"/";
		view+="     ";
		for(Potion p:potions)
			view+=p.getName()+"/";
		view+="     ";
		for(Spell s:spells)
			view+=s.getName()+"/";
		view+="\n";
		return view;
	}
	//change the current weapon of the hero
	public void changeWeapon()
	{
		Scanner in=new Scanner(System.in);
		showWeapons(weapons);
		if(weapons.size()==0)
		{
			System.out.println("\n"+name+", No weapon available!\n");
			return;
		}
		System.out.print("\n"+name+", choose the weapon you want:");
		int choose=in.nextInt();
		curW=weapons.get(choose-1);
		System.out.println("\nYou have changed your weapon to "+weapons.get(choose-1).getName()+"\n");
	}
	//change the current armour of the hero
	public void changeArmour()
	{
		Scanner in=new Scanner(System.in);
		showArmours(armours);
		if(armours.size()==0)
		{
			System.out.println("\n"+name+", No armour available!\n");
			return;
		}
		System.out.print("\n"+name+", choose the armour you want:");
		int choose=in.nextInt();
		curA=armours.get(choose-1);
		System.out.println("\nYou have changed your armour to "+armours.get(choose-1).getName()+"\n");
	}
	// a hero uses his potion to improve his attributes
	public void usePotion()
	{
		Scanner in=new Scanner(System.in);
		showPotions(potions);
		if(potions.size()==0)
		{
			System.out.println("\n"+name+", No potion available!\n");
			return;
		}
		System.out.print("\n"+name+", choose the potion you want to use:");
		int choose=in.nextInt();
		Potion p=potions.remove(choose-1);
		p.use(this);
		System.out.println("\nYou have used potion:"+p.getName()+"\n");
	}
	// a hero choose to uses his spells to attack a monster
	public void usrSpell(Monster monster)
	{
		Scanner in=new Scanner(System.in);
		showSpells(spells);
		if(spells.size()==0)
		{
			System.out.println("\n"+name+", No spell available!\n");
			return;
		}
		System.out.print("\n"+name+", choose the spell you want to use:");
		int choose=in.nextInt();
		Spell s=spells.remove(choose-1);
		s.use(this,monster);
		System.out.println("\nYou have used spell:"+s.getName()+"\n");
	}
	
}
