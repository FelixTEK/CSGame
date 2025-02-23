package application;

public class GameManagement {
	private final GameCharecter player;
    private final GameCharecter enemy;
    private Deck deckPlayer;
    private Deck deckEnemy;
    private Card cardPlayer;
    private Card cardEnemy;
    private boolean isGameRunning;
  
    public GameManagement() {
    	this.deckPlayer = new Deck();
    	this.player = new GameCharecter("Babara", "/Resources/hero01.jpeg", 100);
    	this.deckEnemy = new Deck();
    	this.enemy = new GameCharecter("Xeon", "/Resources/enermy01.jpeg", 100);	
    }
    
    public void gameStart() {
        isGameRunning = true;        
    }

    public void gameStop() {
        isGameRunning = false;        
    }
    
    public void playerDraw(Card card) {
    	this.cardPlayer = card;    	
    }
    
    public void EnemyDraw(Card card) {
        this.cardEnemy = card;
    }
       
    public GameCharecter getPlayer() {
        return player;
    }

    public GameCharecter getEnemy() {
        return enemy;
    }
    
    public Deck getDeckPlayer() {
    	return deckPlayer;
    }
    
    public Deck getDeckEnemy() {
    	return deckEnemy;
    }
    
    public void PlayerAttack() {
    	int Damage;
    	if(cardPlayer==null || cardEnemy==null ) return;    	
    	// Attack - (Defense + HP)    	
    	Damage = cardPlayer.getAttack() - (cardEnemy.getDefense()+cardEnemy.getHp());
    	//Damage Positive >> Decrease HP Enemy
    	//Damage <=0   >> No Damage
    	if(Damage > 0) {
    		enemy.takeDamage(Damage);
    	}
    }
}
