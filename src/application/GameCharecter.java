package application;

public class GameCharecter implements  IGameCharecter {
	private String name;
	private int hp;
	private final int maxHp;
	private String imagePath;

    public GameCharecter(String name,String imagePath,int maxHp) {
    	System.out.println("Name: "+name+" path:"+imagePath+" hp: "+maxHp);
        this.name = name;
        this.imagePath=imagePath;
        this.maxHp = maxHp;
        this.hp = maxHp; // Start HP = Max HP
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0; // Prevent Negative HP
    }
  
    public boolean isAlive() {
    	if (hp > 0) {return true;} else {return false;}

    }
    
    public String getName() { return name; }
    
    public String getImagePath() { return imagePath; }    
    
    public String setName(String newName) {return this.name=newName;}
    
    public String setImagePath(String newPath) {return this.imagePath=newPath;}
    
    @Override
    public int getHp() { return hp; }
    @Override
    public int getMaxHp() { return maxHp; }
}
