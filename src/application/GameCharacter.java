package application;

public class GameCharacter implements  IGameCharacter {
	private String name;
	private int hp;
	private final int maxHp;
	private String imagePath;
	private double probability;

    public GameCharacter(String name,String imagePath,int maxHp,double probability) {    	
        this.name = name;
        this.imagePath=imagePath;
        this.maxHp = maxHp;
        this.hp = maxHp; // Start HP = Max HP
        this.probability=probability;
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
    
    public void heal(int value) {
    	this.hp += value;
    	if(this.hp > this.maxHp) this.hp=this.maxHp; //Prevent Overflow HP
    }
    
    public double getProbability() {return probability;}
    public void  setProbability(double Value) {
    	this.probability=Value;
    }

    public void debug() {
    	String x;
    	x="Name : "+getName()+" Image Path: "+getImagePath()+" HP: "+getHp()+"/"+getMaxHp()+" Prop: "+getProbability();
    	System.out.println(x);
    }
}
