import java.util.ArrayList;

/**
 * An interface implemented by entities which need to show items they own such as markets and heroes
 * @author Chuyang Zhou
 *
 */
public interface ItemHolder {
	//show weapon list in a readable way
	public default void showWeapons(ArrayList<Weapon> weapons)
	{
		int num=1;
		String view="Weapons\n";
		view+="******************\n";
		view+="No.   Name   Cost   Level   Damage   Hands\n";
		view+="---------------------------------------------------------------\n";
		for(Weapon w:weapons)
			view+=num+++"     "+w;
		System.out.println(view);
	}
	//show armour list in a readable way
	public default void showArmours(ArrayList<Armour> armours)
	{
		int num=1;
		String view="Armours\n";
		view+="******************\n";
		view+="No.   Name   Cost   Level   Reduction\n";
		view+="---------------------------------------------------------------\n";
		for(Armour a:armours)
			view+=num+++"     "+a;
		System.out.println(view);
	}
	// show potion list in a readable way
	public default void showPotions(ArrayList<Potion> potions)
	{
		int num=1;
		String view="Potions\n";
		view+="******************\n";
		view+="No.   Name   Cost   Level   Increase   Affect\n";
		view+="---------------------------------------------------------------\n";
		for(Potion p:potions)
			view+=num+++"     "+p;
		System.out.println(view);
	}
	// show spell list in a readable way
	public default void showSpells(ArrayList<Spell> spells)
	{
		int num=1;
		String view="Spells\n";
		view+="******************\n";
		view+="No.   Name   Cost   Level   Damage   ManaCost\n";
		view+="---------------------------------------------------------------\n";
		for(Spell s:spells)
			view+=num+++"     "+s;
		System.out.println(view);
	}
}
