package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.HashMap;
import java.util.Map;

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
    

    private final Map<ImageView, Card> cardMap = new HashMap<>();
    private final static Image backImage = new Image(CardType.getBackImagePath());  // รูปหลังไพ่
    private GameManagement game = new GameManagement();
   
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

        // ตั้งค่าให้คลิกเพื่อพลิกไพ่
        imgCard06.setOnMouseClicked(event -> flipCard(imgCard06));
        imgCard07.setOnMouseClicked(event -> flipCard(imgCard07));
        imgCard08.setOnMouseClicked(event -> flipCard(imgCard08));
        imgCard09.setOnMouseClicked(event -> flipCard(imgCard09));
        imgCard10.setOnMouseClicked(event -> flipCard(imgCard10));
        
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
    
    private void flipCard(ImageView cardView) {
    	System.out.println(cardView.toString());    	
        Card card = cardMap.get(cardView);
        if(card.isFaceUp()) return;
        System.out.println(card.getImagePath());               
        cardView.setImage(new Image(card.getImagePath()));
        card.flipCard();
        game.playerDraw(card);
    }
    
    private void flipEnemyCard(ImageView cardView) {
    	System.out.println(cardView.toString());    	
        Card card = cardMap.get(cardView);
        if(card.isFaceUp()) return;
        System.out.println(card.getImagePath());               
        cardView.setImage(new Image(card.getImagePath()));
        card.flipCard();
        game.playerDraw(card);
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
    
}
