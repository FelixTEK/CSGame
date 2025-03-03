package application;

public enum GameCharecterAsset {
	enemy01("Leviathan","/Resources/enemy01.png",500,Type.ENEMY,0.01),
	enemy02("Corpsescream","/Resources/enemy02.png",300,Type.ENEMY,0.01),
	enemy03("Belphegor","/Resources/enemy03.png",500,Type.ENEMY,0.01),
	enemy04("Nightseeker","/Resources/enemy04.png",400,Type.ENEMY,0.01),
	enemy05("Lucifer","/Resources/enemy05.png",800,Type.ENEMY,0.01),
	enemy06("Asmodeus","/Resources/enemy06.png",500,Type.ENEMY,0.01),
	enemy07("Hollowling","/Resources/enemy07.png",700,Type.ENEMY,0.01),
	enemy08("Mamon","/Resources/enemy08.png",400,Type.ENEMY,0.01),
	enemy09("Cloudtooth","/Resources/enemy09.png",500,Type.ENEMY,0.01),
	
	player01("Persephone","/Resources/hero01.png",500,Type.PLAYER,0.2),
	player02("Flora","/Resources/hero02.png",400,Type.PLAYER,0.2),
	player03("Freya","/Resources/hero03.png",600,Type.PLAYER,0.2),
	player04("Juno","/Resources/hero04.png",500,Type.PLAYER,0.2),
	player05("Yuki","/Resources/hero05.png",300,Type.PLAYER,0.3),
	player06("Ariadne","/Resources/hero06.png",300,Type.PLAYER,0.3),
	player07("Victoria","/Resources/hero07.png",400,Type.PLAYER,0.2),
	player08("Diana","/Resources/hero08.png",400,Type.PLAYER,0.2);
	
	public enum Type { ENEMY, PLAYER }
	
	private final String name;
	private final String imagePath;
	private final int maxHp;
	private final Type type;
	private final double probability;
	
	GameCharecterAsset(String name,String path,int hp, Type type,double probability){
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
