package application;

public class GameManagement {
	private GameCharecter player;
    private GameCharecter enemy;
    private Deck deckPlayer;
    private Deck deckEnemy;
    private Card cardPlayer;
    private Card cardEnemy;
    private boolean isRunning;
    private String msg;
  
    public GameManagement() {
    	setNewGame();
    }
    
    public void setNewGame() {
    	this.deckPlayer = new Deck();
    	this.player = new GameCharecter("Barbara", "/Resources/hero03.png", 200);
    	this.deckEnemy = new Deck();
    	this.enemy = new GameCharecter("Xeon", "/Resources/enemy04.png", 200);
    	this.isRunning = true;
        this.msg="BATTLE BEGIN!!!";
    }
    
    public void gameStart() {
    	System.out.println("game start");
    	this.isRunning=true;
    }

    public void gameStop() {
        System.out.println("game stop");
        this.isRunning=false;
    }
    
    public void exit() {
    	System.exit(0);
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
    
    public void DamageCalculate() {
    	int damageEnemy;
    	int damagePlayer;
    	int damageAdjust;
    	if(cardPlayer==null || cardEnemy==null ) return;    	
    	// Attack - (Defense + HP)
    	if (cardPlayer.getAttack()<cardEnemy.getDefense()) {
    		damageAdjust =0;
    	}else {
    		damageAdjust = cardPlayer.getAttack()-cardEnemy.getDefense();
    		if(damageAdjust < cardEnemy.getHp()) {
    			damageAdjust=0;
    		}else {
    			damageAdjust=damageAdjust - cardEnemy.getHp();
    		}
    	}
    	damagePlayer = damageAdjust;
    	
    	if (cardEnemy.getAttack()<cardPlayer.getDefense()) {
    		damageAdjust =0;
    	}else {
    		damageAdjust = cardEnemy.getAttack()-cardPlayer.getDefense();
    		if(damageAdjust < cardPlayer.getHp()) {
    			damageAdjust=0;
    		}else {
    			damageAdjust=damageAdjust - cardPlayer.getHp();
    		}
    	}
    	damageEnemy = damageAdjust;
    	System.out.println("Player: "+damagePlayer+" Atk: "+cardPlayer.getAttack()+" Def: "+cardPlayer.getDefense()+" HP: "+cardPlayer.getHp());  
    	System.out.println("Enemy: "+damageEnemy+" Atk: "+cardEnemy.getAttack()+" Def: "+cardEnemy.getDefense()+" HP: "+cardEnemy.getHp());
    	//Damage Positive >> Decrease HP Enemy
    	//Damage <=0   >> No Damage
    	if(damagePlayer > damageEnemy) {
    		enemy.takeDamage(damagePlayer-damageEnemy);
    		this.msg="Enemy get damage "+(damagePlayer-damageEnemy)+" points!!!";
    	}else if (damagePlayer == damageEnemy) {
    		this.msg="No damage!!!";
    		//Draw
    	}else {
    		player.takeDamage(damageEnemy-damagePlayer);
    		this.msg="Player get damage "+(damageEnemy-damagePlayer)+" points!!!";
    	}
    	//Check HP = 0 End Game
    	if(!enemy.isAlive()) {
    		this.msg="****** ^ ^ YOU WIN ^ ^ ********";
    		gameStop();
    	}
    	if(!player.isAlive()) {
    		this.msg="****** T T  YOU LOSE T T ********";
    		gameStop();
    	}
    }
    
    
    public void resetDeck() {
    	deckPlayer.reloadDeck();
    	deckEnemy.reloadDeck();
    }
    
    public boolean getIsRunning() {return this.isRunning;}
    
    public String getMessage() {return this.msg;}
    
}
