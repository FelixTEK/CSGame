package application;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

import application.GameManagement.WhoGetDamage;

public class TableController {

    @FXML
    private ImageView imgCard01;

    @FXML
    private ImageView imgCard02;

    @FXML
    private ImageView imgCard03;

    @FXML
    private ImageView imgCard04;

    @FXML
    private ImageView imgCard05;

    @FXML
    private ImageView imgCard06;

    @FXML
    private ImageView imgCard07;

    @FXML
    private ImageView imgCard08;

    @FXML
    private ImageView imgCard09;

    @FXML
    private ImageView imgCard10;

    @FXML
    private ImageView imgEnemy;

    @FXML
    private ImageView imgPlayer;

    @FXML
    private Label txtEnemyHp;

    @FXML
    private Label txtEnemyName;

    @FXML
    private Label txtPlayerHp;

    @FXML
    private Label txtPlayerName;
    
    @FXML
    private Label txtMessage;
    
    @FXML
    private ProgressBar pgbEnemyHp;
    
    @FXML
    private ProgressBar pgbPlayerHp;
    
    @FXML
    private Button btnReDeck;
    
    @FXML
    private Button btnNewGame;
    
    @FXML
    private Button btnExit;
    
    @FXML
    private ImageView imgPlayerShield;
    
    @FXML
    private ImageView imgEnemyShield;
      

    private final Map<ImageView, Card> cardMap = new HashMap<>();
    private final static Image backImage = new Image(CardType.getBackImagePath());  // รูปหลังไพ่
    private final static Image shieldImage = new Image("/Resources/shield.png");
    private GameManagement game = new GameManagement();
    private int EnemyFlipTime = 0; //Enemy AutoFlip 1-5 time
   
    /**
     * 
     */
    @FXML
    public void initialize() {
    	//imgCard01 - imgCard05 = Enemy Card
    	//imgCard06 - imgCard10 = Player Card    	
        game.gameStart(); 
        // สร้างไพ่และผูกกับ ImageView
        

        setPlayerCharacter();
        setEnemyCharacter();
        setAllBackImage();
        setPlayerDeck();
        setEnemyDeck();
        setReDeckVisible();
        setReDeckEvent();
        setNewGameVisible();
        setNewGameEvent();
        setExitVisible();
        setExitEvent();

        setPlayerClick(true);
        
        //setShieldImage();
                
        imgCard06.setOnMouseEntered(event -> MouseOver(imgCard06));
        imgCard07.setOnMouseEntered(event -> MouseOver(imgCard07));
        imgCard08.setOnMouseEntered(event -> MouseOver(imgCard08));
        imgCard09.setOnMouseEntered(event -> MouseOver(imgCard09));
        imgCard10.setOnMouseEntered(event -> MouseOver(imgCard10));
        
        imgCard06.setOnMouseExited(event -> MouseExit(imgCard06));
        imgCard07.setOnMouseExited(event -> MouseExit(imgCard07));
        imgCard08.setOnMouseExited(event -> MouseExit(imgCard08));
        imgCard09.setOnMouseExited(event -> MouseExit(imgCard09));
        imgCard10.setOnMouseExited(event -> MouseExit(imgCard10));

    }
    
    public void setAllBackImage(){
    	imgCard01.setImage(backImage);
    	imgCard02.setImage(backImage);
    	imgCard03.setImage(backImage);
    	imgCard04.setImage(backImage);
    	imgCard05.setImage(backImage);
    	imgCard06.setImage(backImage);
    	imgCard07.setImage(backImage);
    	imgCard08.setImage(backImage);
    	imgCard09.setImage(backImage);
    	imgCard10.setImage(backImage);
    }
    
    public void setShieldImage() {
    	imgPlayerShield.setImage(shieldImage);
    	imgEnemyShield.setImage(shieldImage);
    }
    
    private void flipPlayerCard(ImageView cardView) {
    	System.out.println(cardView.toString());    	
        Card card = cardMap.get(cardView);
        if(card.isFaceUp()) return;
        System.out.println(card.getImagePath());               
        cardView.setImage(new Image(card.getImagePath()));        
        imageFlipEffect(cardView);
        
        card.flipCard();
        game.playerDraw(card);
        
        flipEnemyCard();
    }
    
    private void flipEnemyCard() {
    	Card card;
    	ImageView iv = null;
    	switch (EnemyFlipTime) {
    	case 0:    		
    		iv=imgCard01;
    		break;
    	case 1:
    		iv=imgCard02;
    		break;
    	case 2:
    		iv=imgCard03;
    		break;
    	case 3:
    		iv=imgCard04;
    		break;
    	case 4:
    		iv=imgCard05;
    		break;
    	}
    	EnemyFlipTime++;
    	if (EnemyFlipTime>4) {
    		EnemyFlipTime=0;
    		setReDeckVisible();
    	}
    	card = cardMap.get(iv);
        System.out.println("Image path:"+card.getImagePath());               
        iv.setImage(new Image(card.getImagePath()));
        card.flipCard();
        
        imageFlipEffect(iv);
        
        game.EnemyDraw(card);
        
        game.DamageCalculate();
        updatePlayerHp();
        updateEnemyHp();
        setMessage(game.getMessage());
        
        if(game.who==WhoGetDamage.enemy) {imageShakeEffect(imgEnemy);}
        if(game.who==WhoGetDamage.player) {imageShakeEffect(imgPlayer);}
        
        
        if(!game.getIsRunning()) {setEndGame();}        
    }
    
    private void MouseOver(ImageView cardView) {
    	cardView.setEffect(new Glow(0.8));
    	cardView.setScaleX(1.2);
    	cardView.setScaleY(1.2);;    	
    }
    
    private void MouseExit(ImageView cardView) {
    	cardView.setEffect(null);
    	cardView.setScaleX(1.0);
    	cardView.setScaleY(1.0);    	
    }
    
 // ฟังก์ชั่นสำหรับตั้งค่าตัวละคร
    public void setPlayerCharacter() {
    	txtPlayerName.setText(game.getPlayer().getName());
        imgPlayer.setImage(new Image(game.getPlayer().getImagePath()));
        //Prevent Shack made lost position (Check Position at Scene Builder)
        imgEnemy.setLayoutX(14);
        imgEnemy.setLayoutY(486);
        // อัปเดตค่า HP
        updatePlayerHp();
    }

    // ฟังก์ชั่นอัปเดตค่า HP ของผู้เล่นใน UI
    public void updatePlayerHp() {
        int hp = game.getPlayer().getHp();
        int maxHp= game.getPlayer().getMaxHp();
        pgbPlayerHp.setProgress((double) hp / maxHp);  // อัปเดต ProgressBar
        txtPlayerHp.setText("HP: " + hp + "/" + maxHp);  // อัปเดต Text        
    }

    public void setEnemyCharacter() {
    	txtEnemyName.setText(game.getEnemy().getName());
        imgEnemy.setImage(new Image(game.getEnemy().getImagePath()));
        imgEnemy.setLayoutX(14);
        imgEnemy.setLayoutY(14);
        // อัปเดตค่า HP
        updateEnemyHp();
    }

    // ฟังก์ชั่นอัปเดตค่า HP ของผู้เล่นใน UI
    public void updateEnemyHp() {
	   int hp = game.getEnemy().getHp();
       int maxHp= game.getEnemy().getMaxHp();
       pgbEnemyHp.setProgress((double) hp / maxHp);  // อัปเดต ProgressBar
       txtEnemyHp.setText("HP: " + hp + "/" + maxHp);  // อัปเดต Text
    }
    
    public void setPlayerDeck() {
    	cardMap.put(imgCard06, game.getDeckPlayer().getCard(0));
        cardMap.put(imgCard07, game.getDeckPlayer().getCard(1));
        cardMap.put(imgCard08, game.getDeckPlayer().getCard(2));
        cardMap.put(imgCard09, game.getDeckPlayer().getCard(3));
        cardMap.put(imgCard10, game.getDeckPlayer().getCard(4));       
    }
    
    public void setEnemyDeck() {
    	cardMap.put(imgCard01, game.getDeckEnemy().getCard(0));
        cardMap.put(imgCard02, game.getDeckEnemy().getCard(1));
        cardMap.put(imgCard03, game.getDeckEnemy().getCard(2));
        cardMap.put(imgCard04, game.getDeckEnemy().getCard(3));
        cardMap.put(imgCard05, game.getDeckEnemy().getCard(4));       
    }
    
    public void setReDeckVisible() {
    	btnReDeck.setVisible(!btnReDeck.isVisible());
    }
    
    public void setReDeckEvent() {
    	btnReDeck.setOnMouseClicked(event -> reDeck());
    }
    
    public void setNewGameVisible() {
    	btnNewGame.setVisible(!btnNewGame.isVisible());
    }
    
    public void setNewGameEvent() {
    	btnNewGame.setOnMouseClicked(event -> NewGame());    	
    	//Reset Hp to Max;
    }
    
    public void setExitVisible() {
    	btnExit.setVisible(!btnExit.isVisible());
    }
    
    public void setExitEvent() {
    	btnExit.setOnMouseClicked(event -> exit());
    }
    
    public void reDeck() {
    	setMessage("!!!REDECK!!!");
    	setAllBackImage();
    	cardMap.clear();
    	game.resetDeck();
    	setPlayerDeck();
    	setEnemyDeck();
    	setReDeckVisible();
    }
    
    public void exit() {
    	game.exit();
    }
    
    public void setEndGame() {
    	setPlayerClick(false);
    	if(btnReDeck.isVisible()) {setReDeckVisible();};
    	setNewGameVisible();
    	setExitVisible();
    }
    
    public void NewGame() {
    	EnemyFlipTime=0;
    	game.setNewGame();
    	setPlayerCharacter();
    	setEnemyCharacter();
    	reDeck();
    	if(btnReDeck.isVisible()) {setReDeckVisible();};
    	setPlayerClick(true);
    	setNewGameVisible();
    	setExitVisible();
    	updatePlayerHp();
        updateEnemyHp();
        setMessage("NEW GAME");
    }
    
    public void setPlayerClick(boolean canClicked) {
    	if (canClicked) {
    		imgCard06.setOnMouseClicked(event -> flipPlayerCard(imgCard06));
            imgCard07.setOnMouseClicked(event -> flipPlayerCard(imgCard07));
            imgCard08.setOnMouseClicked(event -> flipPlayerCard(imgCard08));
            imgCard09.setOnMouseClicked(event -> flipPlayerCard(imgCard09));
            imgCard10.setOnMouseClicked(event -> flipPlayerCard(imgCard10));    		
    	}else {
    		imgCard06.setOnMouseClicked(null);
        	imgCard07.setOnMouseClicked(null);
        	imgCard08.setOnMouseClicked(null);
        	imgCard09.setOnMouseClicked(null);
        	imgCard10.setOnMouseClicked(null);
    	}
    }
    
    public void setMessage(String msg) {
    	txtMessage.setText(msg);
    }
    
    public void imageShakeEffect(ImageView img) {
    	TranslateTransition translate = new TranslateTransition();
    	translate.setNode(img);
    	translate.setDuration(Duration.millis(80));
    	translate.setByY(-20);
    	translate.setAutoReverse(true);
    	translate.setCycleCount(4);
    	translate.play();
    }
    
    public void imageFlipEffect(ImageView img) {
    	RotateTransition translate = new RotateTransition();
    	translate.setNode(img);
    	translate.setDuration(Duration.millis(300));
    	translate.setCycleCount(1);
    	translate.setByAngle(180);
    	translate.setAxis(Rotate.Y_AXIS);
    	translate.play();
    }
    
}
