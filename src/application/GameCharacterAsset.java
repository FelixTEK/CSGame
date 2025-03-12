package application;

public enum GameCharacterAsset {
	enemy01("Suntanios OOPBoss","/Resources/enemy01.png",Integer.MAX_VALUE,Type.ENEMY,0.01),
	enemy02("Asmodeus","/Resources/enemy02.png",300,Type.ENEMY,0.01),
	enemy03("Istaroth","/Resources/enemy03.png",500,Type.ENEMY,0.01),
	enemy04("Makarova","/Resources/enemy04.png",400,Type.ENEMY,0.01),
	enemy05("Miklos","/Resources/enemy05.png",800,Type.ENEMY,0.01),
	enemy06("Beelzebub","/Resources/enemy06.png",500,Type.ENEMY,0.01),
	enemy07("Andreus","/Resources/enemy07.png",700,Type.ENEMY,0.01),
	enemy08("Barbatos","/Resources/enemy08.png",400,Type.ENEMY,0.01),
	enemy09("Paimon","/Resources/enemy09.png",500,Type.ENEMY,0.01),
	
	player01("Jeanne","/Resources/hero01.png",500,Type.PLAYER,0.2),
	player02("Devika","/Resources/hero02.png",400,Type.PLAYER,0.2),
	player03("Arthuria","/Resources/hero03.png",600,Type.PLAYER,0.2),
	player04("Clementine","/Resources/hero04.png",500,Type.PLAYER,0.2),
	player05("Seraphina","/Resources/hero05.png",300,Type.PLAYER,0.3),
	player06("Mariana","/Resources/hero06.png",300,Type.PLAYER,0.3),
	player07("Lyudochka","/Resources/hero07.png",400,Type.PLAYER,0.2),
	player08("Meihua","/Resources/hero08.png",400,Type.PLAYER,0.2);
	
	public enum Type { ENEMY, PLAYER }
	
	private final String name;
	private final String imagePath;
	private final int maxHp;
	private final Type type;
	private final double probability;
	
	GameCharacterAsset(String name,String path,int hp, Type type,double probability){
		this.name=name;
		this.imagePath=path;
		this.maxHp=hp;
		this.type = type;
		this.probability= probability;
	}
	
	public String getName() {return name;}
	public String getImagePath() {return imagePath;}
	public int getMaxHp() {return maxHp;}
	public Type getType() {return type;}
	public double getProbability() {return probability;}
	
}
