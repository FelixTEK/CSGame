package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManagement {
	
	enum WhoGetDamage{
		player,
		enemy,
		draw
	}
	
	private GameCharecter player;
    private GameCharecter enemy;
    private Deck deckPlayer;
    private Deck deckEnemy;
    private Card cardPlayer;
    private Card cardEnemy;
    private boolean isRunning;
    private String msg;
    private audio audioMusic;
    private audio audioDamage;
    private audio audioWin;
    public WhoGetDamage who = WhoGetDamage.draw; 
    public AttackType winnerAttackType;
    private final Random random = new Random();
  
    public GameManagement() {
    	    	
    	audioMusic = new audio();
    	audioDamage = new audio();
    	audioWin = new audio();
    	setNewGame();
    }
    
    public void setNewGame() {    	
    	int x =0 ;
    	getPlayerImage();
    	getEnemyImage();
    	this.isRunning = true;
        this.msg="BATTLE BEGIN!!!";
        audioMusic.stopAudio();
        audioMusic.dispose();
        x = random.nextInt(3);
        switch (x) {
        case 0:
        	audioMusic.playAudio(true, 0.5, "/Resources/music01.mp3");
        	break;
        case 1:
        	audioMusic.playAudio(true, 0.5, "/Resources/music02.mp3");
        	break;
        case 2:
        	audioMusic.playAudio(true, 0.5, "/Resources/music03.mp3");
        	break;
        }
                     
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
    	
    	//null to exit
    	if(cardPlayer==null || cardEnemy==null ) return;    	
    	// Attack - (Defense + HP)
    	// Prevent Attack < Defense = No Damage = 0    	
    	if (cardPlayer.getAttack()<cardEnemy.getDefense()) {
    		damageAdjust =0;
    	}else {
    		//x = attack - defense
    		//x < HP card ==> No damage(Attack remain < HP card) ==> x=0
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
    	System.out.println("Player: "+damagePlayer+" Atk: "+cardPlayer.getAttack()+" Def: "+cardPlayer.getDefense()+
    			" HP: "+cardPlayer.getHp()+ " img: "+cardPlayer.getImagePath());  
    	System.out.println("Enemy: "+damageEnemy+" Atk: "+cardEnemy.getAttack()+" Def: "+cardEnemy.getDefense()+
    			" HP: "+cardEnemy.getHp()+ " img: "+cardEnemy.getImagePath());
    	//Damage Positive >> Decrease HP Enemy
    	//Damage <=0   >> No Damage
    	audioDamage.dispose();
    	
    	//Damage Player > Enemy
    	//Check attack type for sound effect
    	//if attack type = HEAL => calculate point to heal(Difference damage Higher - damage Lesser)
    	//WhoGetDamage use at Controller for choose imagePlaer/Enemy Shack
    	if(damagePlayer > damageEnemy) {    		
    		if(cardPlayer.getAttackType()==AttackType.HEAL) {
    			player.heal(damagePlayer-damageEnemy);
    			this.msg="Player Heal "+(damagePlayer-damageEnemy)+" points!!!";
        		this.who = WhoGetDamage.draw;        		
    		}else {
    			enemy.takeDamage(damagePlayer-damageEnemy);
    			this.msg="Enemy get damage "+(damagePlayer-damageEnemy)+" points!!!";
        		this.who = WhoGetDamage.enemy;

    		}
    		this.winnerAttackType=cardPlayer.getAttackType(); //for effect    		    		    		
    	}else if (damagePlayer == damageEnemy) {
    		this.msg="No damage!!!";
    		this.who = WhoGetDamage.draw;
    		this.winnerAttackType = AttackType.NONE;
    		//Draw
    	}else { //Damage Enemy > Player 
    		if(cardEnemy.getAttackType()==AttackType.HEAL) {
    			enemy.heal(damageEnemy-damagePlayer);
    			this.msg="Enemy Heal "+(damageEnemy-damagePlayer)+" points!!!";
        		this.who = WhoGetDamage.draw;
    		}else {    			
    			player.takeDamage(damageEnemy-damagePlayer);
        		this.msg="Player get damage "+(damageEnemy-damagePlayer)+" points!!!";
        		this.who = WhoGetDamage.player;	
    		}
    		this.winnerAttackType=cardEnemy.getAttackType(); //for effect
    	}
    	System.out.println(this.winnerAttackType);
    	audioDamage.playAudio(false, 1,getSoundAttack(this.winnerAttackType));
    	
    	
    	//Check HP = 0 End Game
    	audioWin.dispose(); //Stop audio Win for new play
    	if(!enemy.isAlive()) {
    		this.msg="****** ^ ^ YOU WIN ^ ^ ********";
    		gameStop();
    		audioWin.playAudio(false, 1,"/Resources/sfx_playerwin.mp3");
    	}
    	if(!player.isAlive()) {
    		this.msg="****** T T  YOU LOSE T T ********";
    		gameStop();
    		audioWin.playAudio(false, 1,"/Resources/sfx_enemywin.mp3");
    	}
    }
    
    
    public void resetDeck() {
    	deckPlayer.reloadDeck();
    	deckEnemy.reloadDeck();
    }
    
    public boolean getIsRunning() {return this.isRunning;}
    
    public String getMessage() {return this.msg;}
    
    private void getPlayerImage() {
    	int x;
    	// .values = get all data from enum
    	GameCharecterAsset[] asset = GameCharecterAsset.values();
    	//Number player = 8 (Asset player in GameCharecterAsset)
    	//+9 = number's enemy asset
    	//random 8[0..7]   
    	//min=0  >> 0+9 = 9
    	//max=7 >> 7+9 = 16
    	x = random.nextInt(8)+9;
    	System.out.println(x);
    	this.deckPlayer = new Deck();
    	this.player = new GameCharecter(asset[x].getName(),asset[x].getImagePath(),asset[x].getMaxHp());    	
    }
    private void getEnemyImage() {
    	int x;
    	GameCharecterAsset[] asset = GameCharecterAsset.values();
    	x = random.nextInt(9); //[0..8]
    	System.out.println(x);
    	this.deckEnemy = new Deck();
    	this.enemy = new GameCharecter(asset[x].getName(),asset[x].getImagePath(),asset[x].getMaxHp());
    }
    private String getSoundAttack(AttackType type) {
    	String path = "";
    	switch (type){
    	case AttackType.NONE:
    		path = "/Resources/sfx_draw.mp3";
    		break;
    	case AttackType.PHYSICAL:
    		path = "/Resources/sfx_sword.mp3";
    		break;
    	case AttackType.MAGIC:
    		path = "/Resources/sfx_magic.mp3";
    		break;
    	case AttackType.HEAL:
    		path = "/Resources/sfx_heal.mp3";
    		break;
    	case AttackType.SHIELD:
    		path = "/Resources/sfx_shield.mp3";
    		break;
    	}
    	return path;
    }
    
}
