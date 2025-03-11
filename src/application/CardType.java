package application;

public enum CardType {
		
	//Name(ATK,DEF,HP,ImagePath)
	PALADIN(100,100,50,"/Resources/c01.png",AttackType.PHYSICAL),
	GUARDIAN(80,150,60,"/Resources/c02.png",AttackType.PHYSICAL),
	MONK(120,80,30,"/Resources/c03.png",AttackType.PHYSICAL),
	BARBARIAN(90,90,40,"/Resources/c04.png",AttackType.PHYSICAL),
	ASSASSIN(180,80,20,"/Resources/c05.png",AttackType.PHYSICAL),
	BARD(50,60,30,"/Resources/c06.png",AttackType.MAGIC),
	SWASHBUCKLER(150,90,40,"/Resources/c07.png",AttackType.PHYSICAL),
	RANGER(150,60,30,"/Resources/c08.png",AttackType.PHYSICAL),
	CLERIC(200,50,50,"/Resources/c09.png",AttackType.HEAL),
	DRUID(100,50,40,"/Resources/c10.png",AttackType.MAGIC),
	SHAMAN(100,50,50,"/Resources/c11.png",AttackType.MAGIC),
	ALCHEMIST(130,50,40,"/Resources/c12.png",AttackType.MAGIC),
	MAGE(200,50,40,"/Resources/c13.png",AttackType.MAGIC),
	ENCHANTER(120,50,40,"/Resources/c14.png",AttackType.MAGIC),
	SUMMONER(100,50,30,"/Resources/c15.png",AttackType.MAGIC),
	NECROMANCER(80,50,30,"/Resources/c16.png",AttackType.MAGIC);
	
	private final int attack;
    private final int defense;
    private final int hp;
    private final String imagePath;
    private final static String backImagePath = "/Resources/c00.png";  // shared back image
    private final AttackType attackType;

    CardType(int attack, int defense, int hp, String imagePath, AttackType Type) {
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.imagePath = imagePath;
        this.attackType = Type;
        
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHp() { return hp; }
    public String getImagePath() { return imagePath; }
    public static String getBackImagePath() { return backImagePath; }
    public AttackType getAttackType() {return attackType;}
}
