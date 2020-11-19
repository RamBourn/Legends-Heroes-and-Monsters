/**
 * A class representing potions
 * @author Chuyang Zhou
 *
 */
public class Potion extends Item{
	private int increase;
	private String attributes;
	public Potion(String name,int cost,int level,int increase,String attributes)
	{
		super(name,cost,level);
		this.increase=increase;
		this.attributes=attributes;
	}
	public String toString()
	{
		return super.toString()+increase+"     "+attributes+"\n";
	}
	/**
	 * 
	 * @param hero the hero who will use the potion
	 */
	public void use(Hero hero)
	{
		if(attributes.indexOf("Health")!=-1)
			hero.setHp(hero.getHp()+increase);
		if(attributes.indexOf("Mana")!=-1)
			hero.setMana(hero.getMana()+increase);
		if(attributes.indexOf("Strength")!=-1)
			hero.setStrength(hero.getStrength()+increase);
		if(attributes.indexOf("Agility")!=-1)
			hero.setAgility(hero.getAgility()+increase);
		if(attributes.indexOf("Dexterity")!=-1)
			hero.setDexterity(hero.getDexterity()+increase);
	}
}
