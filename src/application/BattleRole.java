package application;

//Atk,Def,Attack2HP   HP+(Damame * -1) if + = heal hp
public enum BattleRole {
	PALADIN(100,100,false),
	GUARDUAN(80,150,false),
	MONK(120,80,false),
	BARBARIAN(90,90,false),
	ASSASSIN(180,80,false),
	BARD(50,60,true),
	SWASHBACKLER(150,90,false),
	RANGER(150,60,false),
	CLERIC(80,50,true),
	DRUID(100,50,false),
	SHAMAN(100,50,false),
	ALCHEMIST(130,50,false),
	MAGE(200,50,false),
	ENCHANTER(120,50,false),
	SUMMONER(100,50,false),
	NECROMANCER(80,50,false);

	private final int attack;
    private final int defense;
    private final boolean heal;
    
	BattleRole(int attack, int defense, boolean heal) {
		// TODO Auto-generated constructor stub
		this.attack=attack;
		this.defense=defense;
		this.heal=heal;		
	}
	
	public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public boolean getHeal() {return heal; }
};
