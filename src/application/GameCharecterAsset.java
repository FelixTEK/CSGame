package application;

public enum GameCharecterAsset {
	enemy01("Leviathan","/Resources/enemy01.png",500),
	enemy02("Corpsescream","/Resources/enemy02.png",300),
	enemy03("Belphegor","/Resources/enemy03.png",500),
	enemy04("Nightseeker","/Resources/enemy04.png",400),
	enemy05("Lucifer","/Resources/enemy05.png",800),
	enemy06("Asmodeus","/Resources/enemy06.png",500),
	enemy07("Hollowling","/Resources/enemy07.png",700),
	enemy08("Mamon","/Resources/enemy08.png",400),
	enemy09("Cloudtooth","/Resources/enemy09.png",500),
	player01("Persephone","/Resources/hero01.png",500),
	player02("Flora","/Resources/hero02.png",400),
	player03("Freya","/Resources/hero03.png",600),
	player04("Juno","/Resources/hero04.png",500),
	player05("Yuki","/Resources/hero05.png",300),
	player06("Ariadne","/Resources/hero06.png",300),
	player07("Victoria","/Resources/hero07.png",400),
	player08("Diana","/Resources/hero08.png",400);
	
	private final String name;
	private final String imagePath;
	private final int maxHp;
	
	GameCharecterAsset(String name,String path,int hp){
		this.name=name;
		this.imagePath=path;
		this.maxHp=hp;
	}
	
	public String getName() {return name;}
	public String getImagePath() {return imagePath;}
	public int getMaxHp() {return maxHp;}
	
}
