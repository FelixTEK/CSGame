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
    WhoGetDamage who = WhoGetDamage.draw; 
    private List<String> imgListPlayer = new ArrayList<>();
    private List<String> imgListEnemy = new ArrayList<>();
    private List<String> sPlayerName = new ArrayList<>();
    private List<String> sEnemyName = new ArrayList<>();
    private final Random random = new Random();
  
    public GameManagement() {
    	
    	imgListPlayer.add("/Resources/hero01.png");
    	imgListPlayer.add("/Resources/hero02.png");
    	imgListPlayer.add("/Resources/hero03.png");
    	imgListPlayer.add("/Resources/hero04.png");
    	imgListPlayer.add("/Resources/hero05.png");
    	imgListPlayer.add("/Resources/hero06.png");
    	imgListPlayer.add("/Resources/hero07.png");
    	imgListPlayer.add("/Resources/hero08.png");
    	
    	imgListEnemy.add("/Resources/enemy01.png");
    	imgListEnemy.add("/Resources/enemy02.png");
    	imgListEnemy.add("/Resources/enemy03.png");
    	imgListEnemy.add("/Resources/enemy04.png");
    	imgListEnemy.add("/Resources/enemy05.png");
    	imgListEnemy.add("/Resources/enemy06.png");
    	imgListEnemy.add("/Resources/enemy07.png");
    	imgListEnemy.add("/Resources/enemy08.png");
    	imgListEnemy.add("/Resources/enemy09.png");
    	
    	//Number Name = Number of image
    	sPlayerName.add("Babara");
    	sPlayerName.add("Yomi");
    	sPlayerName.add("Aqua");
    	sPlayerName.add("Venus");
    	sPlayerName.add("Yuki");
    	sPlayerName.add("Maria");
    	sPlayerName.add("Fubugi");
    	sPlayerName.add("Aoi");
    	
    	sEnemyName.add("Lucifer");
    	sEnemyName.add("Mamon");
    	sEnemyName.add("Leviathan");
    	sEnemyName.add("Asmodeus");
    	sEnemyName.add("Belphegor");
    	sEnemyName.add("Nightseeker");
    	sEnemyName.add("Corpsescream");
    	sEnemyName.add("Hollowling");
    	sEnemyName.add("Cloudtooth");
    	
    	audioMusic = new audio();
    	audioDamage = new audio();
    	audioWin = new audio();
    	setNewGame();
    }
    
    public void setNewGame() {    	
    	
    	getPlayerImage();
    	getEnemyImage();
    	this.isRunning = true;
        this.msg="BATTLE BEGIN!!!";
        audioMusic.stopAudio();
        audioMusic.dispose();
        audioMusic.playAudio(true, 0.3, "/Resources/music.mp3");             
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
    	audioDamage.dispose();
    	if(damagePlayer > damageEnemy) {
    		enemy.takeDamage(damagePlayer-damageEnemy);
    		this.msg="Enemy get damage "+(damagePlayer-damageEnemy)+" points!!!";
    		this.who = WhoGetDamage.enemy;
    		audioDamage.playAudio(false, 1,"/Resources/sfx_sword.mp3");
    	}else if (damagePlayer == damageEnemy) {
    		this.msg="No damage!!!";
    		this.who = WhoGetDamage.draw;
    		audioDamage.playAudio(false, 1,"/Resources/sfx_shield.mp3");
    		//Draw
    	}else {
    		player.takeDamage(damageEnemy-damagePlayer);
    		this.msg="Player get damage "+(damageEnemy-damagePlayer)+" points!!!";
    		this.who = WhoGetDamage.player;
    		audioDamage.playAudio(false, 1,"/Resources/sfx_hit.wav");
    	}
    	
    	
    	//Check HP = 0 End Game
    	audioWin.dispose();
    	if(!enemy.isAlive()) {
    		this.msg="****** ^ ^ YOU WIN ^ ^ ********";
    		gameStop();
    		audioWin.playAudio(false, 1,"/Resources/sfx_playerwin.wav");
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
    	x = random.nextInt(imgListPlayer.size());
    	System.out.println(x);
    	this.deckPlayer = new Deck();
    	this.player = new GameCharecter(sPlayerName.get(x), imgListPlayer.get(x), 500);    	
    }
    private void getEnemyImage() {
    	int x;
    	x = random.nextInt(imgListEnemy.size());
    	System.out.println(x);
    	this.deckEnemy = new Deck();
    	this.enemy = new GameCharecter(sEnemyName.get(x), imgListEnemy.get(x), 500);
    }
    
}
