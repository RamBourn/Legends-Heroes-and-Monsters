/**
 * A class representing the weapon heroes hold
 * @author Chuyang Zhou
 *
 */
public class Weapon extends Item{
	private int damage; 
	private int hands;
	public Weapon(String name,int cost,int level,int damage,int hands)
	{
		super(name,cost,level);
		this.damage=damage;
		this.hands=hands;
	}
	public String toString()
	{
		return super.toString()+damage+"     "+hands+"\n";
	}
	public int getDamage()
	{
		return damage;
	}
}
