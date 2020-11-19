import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing a market
 * @author Chuyang Zhou
 *
 */
public class Market implements ItemHolder{
	private ArrayList<Weapon> weapons;       //weapons sold in the market
	private ArrayList<Potion> potions;       //potions sold in the market
	private ArrayList<Armour> armours;       //armours sold in the market
	private ArrayList<Spell> fireSpells;     //spells sold in the market
	private ArrayList<Spell> iceSpells;
	private ArrayList<Spell> lightningSpells;
	public Market()
	{
		weapons=new ArrayList<>();
		potions=new ArrayList<>();
		armours=new ArrayList<>();
		fireSpells=new ArrayList<>();
		iceSpells=new ArrayList<>();
		lightningSpells=new ArrayList<>();
		init();
	}
	public void init()
	{
		//weapons
		weapons.add(new Weapon("Sword",500,1,800,1));
		weapons.add(new Weapon("Bow",300,2,500,2));
		weapons.add(new Weapon("Scythe",1000,6,1100,2));
		weapons.add(new Weapon("Axe",550,5,850,1));
		weapons.add(new Weapon("TSwords",1400,8,1600,2));
		weapons.add(new Weapon("Dagger",200,1,250,1));
		//potions
		potions.add(new Potion("Healing_Potion",250,1,100,"Health"));
		potions.add(new Potion("Strength_Potion",200,1,75,"Strength"));
		potions.add(new Potion("Magic_Potion",350,2,100,"Mana"));
		potions.add(new Potion("Luck_Elixir",500,4,65,"Agility"));
		potions.add(new Potion("Mermaid_Tears",850,5,100,"Health/Mana/Strength/Agility"));
		potions.add(new Potion("Ambrosia",1000,8,150,"All Health/Mana/Strength/Dexterity/Defense/Agility"));
		//armour
		armours.add(new Armour("Platinum_Shield",150,1,200));
		armours.add(new Armour("Breastplate",350,3,600));
		armours.add(new Armour("Full_Body_Armor",1000,8,1100));
		armours.add(new Armour("Wizard_Shield",1200,10,1500));
		armours.add(new Armour("Speed_Boots",550,4,600));
		armours.add(new Armour("Guardian_Angel",1000,10,1000));
		//spells
		String f="fire";
		String i="ice";
		String l="lightning";
		fireSpells.add(new Spell(f,"Flame_Tornado",700,4,850,300));
		fireSpells.add(new Spell(f,"Breath_of_Fire",350,1,450,100));
		fireSpells.add(new Spell(f,"Heat_Wave",450,2,600,150));
		fireSpells.add(new Spell(f,"Lava_Comet",800,7,1000,550));
		fireSpells.add(new Spell(f,"Hell_Storm",600,3,950,600));
		iceSpells.add(new Spell(i,"Snow_Cannon",500,2,650,250));
		iceSpells.add(new Spell(i,"Ice_Blade",250,1,450,100));
		iceSpells.add(new Spell(i,"Frost_Blizzard",750,5,850,350));
		iceSpells.add(new Spell(i,"Arctic_Storm",700,6,800,300));
		lightningSpells.add(new Spell(l,"Lightning_Dagger",400,1,500,150));
		lightningSpells.add(new Spell(l,"Thunder_Blast",750,4,950,400));
		lightningSpells.add(new Spell(l,"Electric_Arrows",550,5,650,200));
		lightningSpells.add(new Spell(l,"Spark_Needles",500,2,600,200));
	}
	public void showFireSpells()
	{
		int num=1;
		String view="Fire Spells\n";
		view+="******************\n";
		view+="No.   Name   Cost   Level   Damage   ManaCost\n";
		view+="---------------------------------------------------------------\n";
		for(Spell s:fireSpells)
			view+=num+++"     "+s;
		System.out.println(view);
	}
	public void showIceSpells()
	{
		int num=fireSpells.size()+1;
		String view="Ice Spells\n";
		view+="******************\n";
		view+="Name   Cost   Level   Damage   ManaCost\n";
		view+="---------------------------------------------------------------\n";
		for(Spell s:iceSpells)
			view+=num+++"     "+s;
		System.out.println(view);
	}
	public void showLightningSpells()
	{
		int num=fireSpells.size()+iceSpells.size()+1;
		String view="Lightning Spells\n";
		view+="******************\n";
		view+="No.   Name   Cost   Level   Damage   ManaCost\n";
		view+="---------------------------------------------------------------\n";
		for(Spell s:lightningSpells)
			view+=num+++"     "+s;
		System.out.println(view);
	}
	public void showSpells()
	{
		showFireSpells();
		showIceSpells();
		showLightningSpells();
	}
	// make all heroes in the team to purchase what they need
	public void market(ArrayList<Hero> heroes)
	{
		int num=1;
		String view="";
		Scanner in=new Scanner(System.in);
		System.out.println("\n**********\n**MARKET**\n**********\n\nWelcome to Market!\n");
		view+="Heroes\n********************\n";
		view+="No.   Name   Money\n";
		for(Hero h:heroes)
			view+=num+++"     "+h;
		int choose;
		do
		{
			Legend.showInfo(heroes);
			System.out.print("Choose which hero to buy or sell(Number:hero  0:leave the market):");
			choose=in.nextInt();
			if(choose==0)
				break;
			if(choose<0||choose>heroes.size())
			{
				System.out.println("This input is wrong, enter again");
				continue;
			}
			else
				market(heroes.get(choose-1));
		}while(choose!=0);
	}
	//each hero makes his purchase
	public void market(Hero hero)
	{
		System.out.println("\n"+hero.getName()+", it is your turn to buy or sell\n");
		Scanner in=new Scanner(System.in);
		int choose;
		int choose_;
		String view="";
		view+="Items        No.\n--------------------------------\n";
		view+="Weapons     1\n";
		view+="Armours     2\n";
		view+="Potions     3\n";
		view+="Spells     4\n";
		do
		{
			System.out.println(view+"\nChoose what to buy or sell(Number:kind  0:quit):");
			choose=in.nextInt();
			if(choose<0||choose>4)
			{
				System.out.println("\nThis input is wrong! Enter again\n");
				continue;
			}
			else if(choose==1)
			{
				System.out.print("\nDo you want to buy or sell(1:buy 2:sell):");
				choose_=in.nextInt();
				if(choose_==1)
				{
					showWeapons(weapons);
					System.out.print("\nChoose which weapon to buy(Number:weapon  0:quit):");
					int num=in.nextInt();
					if(num==0)
						continue;
					else
					{
						if(hero.getMoney()>=weapons.get(num-1).getCost())
						{
							hero.setMoney(hero.getMoney()-weapons.get(num-1).getCost());
							hero.getWeapons().add(weapons.get(num-1));
							System.out.println("\n"+hero.getName()+", you have bought "+weapons.get(num-1).getName()+"\n");
						}
						else
							System.out.println("\n"+hero.getName()+", your money is not enough!"+"\n");
					}
				}
				else
				{
					if(hero.getWeapons().size()==0)
					{
						System.out.println("\n"+hero.getName()+", no weapon availble!\n");
						continue;
					}
					showWeapons(hero.getWeapons());
					System.out.print("\nChoose which weapon to sell(Number:weapon  0:quit):");
					int num=in.nextInt();
					if(num==0)
						continue;
					else
					{
						Weapon w=hero.getWeapons().remove(num-1);
						hero.setMoney(hero.getMoney()+w.getCost()/2);
						System.out.println("\n"+hero.getName()+", you have sold "+w.getName()+"\n");
					}
				}
			}
			else if(choose==2)
			{
				System.out.print("\nDo you want to buy or sell(1:buy 2:sell):");
				choose_=in.nextInt();
				if(choose_==1)
				{
					showArmours(armours);
					System.out.print("\nChoose which weapon to buy(Number:armour  0:quit):");
					int num=in.nextInt();
					if(num==0)
						continue;
					else
					{
						if(hero.getMoney()>=armours.get(num-1).getCost())
						{
							hero.setMoney(hero.getMoney()-armours.get(num-1).getCost());
							hero.getArmours().add(armours.get(num-1));
							System.out.println("\n"+hero.getName()+", you have bought "+armours.get(num-1).getName()+"\n");
						}
						else
							System.out.println("\n"+hero.getName()+", your money is not enough!\n");
					}
				}
				else
				{
					if(hero.getArmours().size()==0)
					{
						System.out.println("\n"+hero.getName()+", no armour availble!\n");
						continue;
					}
					showArmours(hero.getArmours());
					System.out.print("\nChoose which armour to sell(Number:armour  0:quit):");
					int num=in.nextInt();
					if(num==0)
						continue;
					else
					{
						Armour a=hero.getArmours().remove(num-1);
						hero.setMoney(hero.getMoney()+a.getCost()/2);
						System.out.println("\n"+hero.getName()+", you have sold "+a.getName()+"\n");
					}
				}
			}
			else if(choose==3)
			{
				
				System.out.print("\nDo you want to buy or sell(1:buy 2:sell):");
				choose_=in.nextInt();
				if(choose_==1)
				{
					showArmours(armours);
					System.out.print("\nChoose which weapon to buy(Number:armour  0:quit):");
					int num=in.nextInt();
					if(num==0)
						continue;
					else
					{
						if(hero.getMoney()>=potions.get(num-1).getCost())
						{
							hero.setMoney(hero.getMoney()-potions.get(num-1).getCost());
							hero.getPotions().add(potions.get(num-1));
							System.out.println("\n"+hero.getName()+", you have bought "+potions.get(num-1).getName()+"\n");
						}
						else
							System.out.println("\n"+hero.getName()+", your money is not enough!\n");
					}
				}
				else
				{
					if(hero.getPotions().size()==0)
					{
						System.out.println("\n"+hero.getName()+", no potion availble!\n");
						continue;
					}
					showPotions(hero.getPotions());
					System.out.print("\nChoose which potion to sell(Number:potion  0:quit):");
					int num=in.nextInt();
					if(num==0)
						continue;
					else
					{
						Potion p=hero.getPotions().remove(num-1);
						hero.setMoney(hero.getMoney()+p.getCost()/2);
						System.out.println("\n"+hero.getName()+", you have sold "+p.getName()+"\n");
					}
				}
			}
			else if(choose==4)
			{
				System.out.print("\nDo you want to buy or sell(1:buy 2:sell):");
				choose_=in.nextInt();
				if(choose_==1)
				{
					showSpells();
					System.out.print("\nChoose which weapon to buy(Number:spell  0:quit):");
					int num=in.nextInt();
					if(num==0)
						continue;
					else
					{	
						ArrayList<Spell> spells=new ArrayList<>();
						spells.addAll(fireSpells);
						spells.addAll(iceSpells);
						spells.addAll(lightningSpells);
						if(hero.getMoney()>=spells.get(num-1).getCost())
						{
							hero.setMoney(hero.getMoney()-spells.get(num-1).getCost());
							hero.getSpells().add(spells.get(num-1));
							System.out.println("\n"+hero.getName()+", you have bought "+spells.get(num-1).getName()+"\n");
						}
						else
							System.out.println("\n"+hero.getName()+", your money is not enough!\n");
					}
				}
				else
				{
					if(hero.getSpells().size()==0)
					{
						System.out.println("\n"+hero.getName()+", no spell availble!\n");
						continue;
					}
					showPotions(hero.getPotions());
					System.out.print("\nChoose which spell to sell(Number:spell  0:quit):");
					int num=in.nextInt();
					if(num==0)
						continue;
					else
					{
						Spell s=hero.getSpells().remove(num-1);
						hero.setMoney(hero.getMoney()+s.getCost()/2);
						System.out.println("\n"+hero.getName()+", you have sold "+s.getName()+"\n");
					}
				}
			}
		}while(choose!=0);
	}
}
