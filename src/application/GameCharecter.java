package application;

public class GameCharecter implements  IGameCharecter {
	private final String name;
	private int hp;
	private final int maxHp;
	private final String imagePath;

    public GameCharecter(String name,String imagePath,int maxHp) {
        this.name = name;
        this.imagePath=imagePath;
        this.maxHp = maxHp;
        this.hp = maxHp; // Start HP = Max HP
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0; // Prevent Negative HP
    }

    public void heal(int amount) {
        this.hp += amount;
        if (this.hp > maxHp) this.hp=maxHp; //Prevent Over HP
    }
    
    public boolean isAlive() {
    	if (hp > 0) {return true;} else {return false;}

    }
    
    public String getName() { return name; }
    
    public String getImagePath() { return imagePath; }       
    
    @Override
    public int getHp() { return hp; }
    @Override
    public int getMaxHp() { return maxHp; }
}
