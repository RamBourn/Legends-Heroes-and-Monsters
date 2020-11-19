/**
 * A class representing a single monster
 * @author Chuyang Zhou
 *
 */
public class Monster extends Living {
	private String name;
	private int damage;
	private int defense;
	private int dodge;
	private boolean dead;
	public Monster(String name,int level,int damage,int defense,int dodge)
	{
		super(name,level);
		this.name=name;
		this.damage=damage;
		this.defense=defense;
		this.dodge=dodge;
		dead=false;
	}
	public String toString()
	{
		String monster=name+"     "+hp+"     "+level+"     "+damage+"     "+defense+"     "+dodge+"\n";
		return monster;
	}
	/**
	 * the monster will attack a hero
	 */
	public void attack(Living hero)
	{
		int res=hero.defense((int)(damage*0.1));
		if(res>0)
			System.out.println(name+"(monster) made "+res+" damage to "+hero.getName()+"(hero)");
	}
	/**
	 * the monster will defend itself from attack from a hero
	 * return the real damage after defense
	 */
	public int defense(int damage)
	{
		int total=0;
		if(Math.random()<=dodge*0.01)
		{
			System.out.println(name+"(monster) dodged this attack form heroes");
			return total;
		}
		else
		{
			total=(int)(damage*0.1);
			hp=hp-total;
		}
		if(hp<=0)
		{
			dead=true;
			System.out.println(name+"(monster) is dead");
		}
		return total;
	}
	public boolean isDead()
	{
		return dead;
	}
	public int getDamage()
	{
		return damage;
	}
	public void setDamage(int damage)
	{
		this.damage=damage;
	}
	public int getDefense()
	{
		return defense;
	}
	public void setDefense(int defense)
	{
		this.defense=defense;
	}
	public int getDodge()
	{
		return dodge; 
	}
	public void setDodge(int dodge)
	{
		this.dodge=dodge;
	}
}
