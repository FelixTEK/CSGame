package application;

public enum CardType {
	//Name(ATK,DEF,HP,ImagePath)
	PALADIN(100,100,50,"/Resources/c01.png"),
	GUARDUAN(80,150,60,"/Resources/c02.png"),
	MONK(120,80,30,"/Resources/c03.png"),
	BARBARIAN(90,90,40,"/Resources/c04.png"),
	ASSASSIN(180,80,20,"/Resources/c05.png"),
	BARD(50,60,30,"/Resources/c06.png"),
	SWASHBUCKLER(150,90,40,"/Resources/c07.png"),
	RANGER(150,60,30,"/Resources/c08.png"),
	CLERIC(80,50,50,"/Resources/c09.png"),
	DRUID(100,50,40,"/Resources/c10.png"),
	SHAMAN(100,50,50,"/Resources/c11.png"),
	ALCHEMIST(130,50,40,"/Resources/c12.png"),
	MAGE(200,50,40,"/Resources/c13.png"),
	ENCHANTER(120,50,40,"/Resources/c14.png"),
	SUMMONER(100,50,30,"/Resources/c15.png"),
	NECROMANCER(80,50,30,"/Resources/c16.png");
	
	private final int attack;
    private final int defense;
    private final int hp;
    private final String imagePath;
    private final static String backImagePath = "/Resources/c00.png";  // shared back image

    CardType(int attack, int defense, int hp, String imagePath) {
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.imagePath = imagePath;
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getHp() { return hp; }
    public String getImagePath() { return imagePath; }
    public static String getBackImagePath() { return backImagePath; }
}
