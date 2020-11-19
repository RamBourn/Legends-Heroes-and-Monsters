/**
 * A class representing spells
 * @author Chuyang Zhou
 *
 */
public class Spell extends Item{
	private String type;
	private int damage;
	private int manaCost;
	public Spell(String type,String name,int cost,int level,int damage,int manaCost)
	{
		super(name,cost,level);
		this.type=type;
		this.damage=damage;
		this.manaCost=manaCost;
	}
	public String toString()
	{
		return super.toString()+damage+"     "+manaCost+"\n";
	}
	/**
	 * 
	 * @param hero the hero who will use the spell
	 * @param monster  the target of the spell
	 */
	public void use(Hero hero,Monster monster)
	{
		if(hero.getMana()<manaCost)
		{
			System.out.println(hero.getName()+", your mana is not enough");
			return;
		}
		else
			hero.setMana(hero.getMana()-manaCost);
		int total=(int)(damage+hero.getDexterity()/10000*damage);
		monster.defense(total);
		if(type.equals("fire"))
			monster.setDamage((int)(monster.getDamage()*0.9));
		else if(type.equals("ice"))
			monster.setDefense((int)(monster.getDefense()*0.9));
		else
			monster.setDodge((int)(monster.getDodge()*0.9));
	}
}
