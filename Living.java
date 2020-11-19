/**
 * A class representing living creatures such as heroes and monsters
 * @author Administrator
 *
 */
public abstract class Living {
	protected int hp;
	protected int level;
	protected String name;
	/**
	 * 
	 * @param name name the living
	 * @param level  level of the living
	 */
	public Living(String name,int level)
	{
		this.name=name;
		this.level=level;
		this.hp=level*100;
	}
	public void setHp(int hp)
	{
		this.hp=hp;
	}
	public int getHp()
	{
		return hp;
	}
	public int getLevel()
	{
		return level;
	}
	public String getName()
	{
		return name;
	}
	/**
	 * 
	 * @param enemy the enemy the living will attack
	 */
	public abstract void attack(Living enemy);
	/**
	 * the living will defend itself from attack
	 * @param damage incoming damage
	 * @return return real damage after defense
	 */
	public abstract int defense(int damage);

}
