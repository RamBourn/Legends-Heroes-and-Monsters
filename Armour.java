/**
 * A class representing armor
 * @author Chuyang Zhou
 *
 */
public class Armour extends Item {
	private int reduction;
	public Armour(String name,int cost,int level,int reduction)
	{	
		super(name,cost,level);
		this.reduction=reduction;
	}
	public String toString()
	{
		return super.toString()+reduction+"\n";
	}
	public int getReduction()
	{
		return reduction;
	}
}
