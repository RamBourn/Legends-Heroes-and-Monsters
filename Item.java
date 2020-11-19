/**
 * An abstract class representing things sold in the market
 * @author Administrator
 *
 */
public abstract class Item {
	protected String name;
	protected int cost;
	protected int level;
	public Item(String name,int cost,int level)
	{
		this.name=name;
		this.cost=cost;
		this.level=level;
	}
	public String toString()
	{
		return name+"     "+cost+"     "+level+"     ";
	}
	public int getCost()
	{
		return cost;
	}
	public int getLevel()
	{
		return level;
	}
	public String getName()
	{
		return name;
	}
}
