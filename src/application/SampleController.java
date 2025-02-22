package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Card;
import java.util.Random;

public class SampleController{

	//ชื่อตัวแปรต้องเป็นตัวเดียวกับ FX::ID ในไฟล์ FXML(ดูในFX::ID ของ Code Section)
	@FXML
	private ImageView imgEnermy;
	@FXML
	private ImageView imgPlayer;
	@FXML
	private Card card01;
	@FXML
	private Card card02;
	@FXML
	private Card card03;
	@FXML
	private Card card04;
	@FXML
	private Card card05;
	@FXML
	private Card card06;
	@FXML
	private Card card07;
	@FXML
	private Card card08;
	@FXML
	private Card card09;
	@FXML
	private Card card10;
	
	@FXML
	private List<Image> images;
	private int currentImageIndex = 0;
	
	@FXML
    public void initialize() {
		// สร้าง List เก็บรูปภาพ
	    images = new ArrayList<>();
	    images.add(new Image("/application/Resources/c00.png"));
	    images.add(new Image("/application/Resources/c01.png"));
	    images.add(new Image("/application/Resources/c02.png"));
	    images.add(new Image("/application/Resources/c03.png"));
	    images.add(new Image("/application/Resources/c04.png"));
	    images.add(new Image("/application/Resources/c05.png"));
	    images.add(new Image("/application/Resources/c06.png"));
	    images.add(new Image("/application/Resources/c07.png"));
	    images.add(new Image("/application/Resources/c08.png"));
	    images.add(new Image("/application/Resources/c09.png"));
	    images.add(new Image("/application/Resources/c10.png"));
	    images.add(new Image("/application/Resources/c11.png"));
	    images.add(new Image("/application/Resources/c12.png"));
	    images.add(new Image("/application/Resources/c13.png"));
	    images.add(new Image("/application/Resources/c14.png"));
	    images.add(new Image("/application/Resources/c15.png"));
	    images.add(new Image("/application/Resources/c16.png"));
	    images.add(new Image("/application/Resources/enermy01.jpeg"));
	    images.add(new Image("/application/Resources/hero01.jpeg"));
		
	    imgEnermy.setImage(images.get(17));
	    imgPlayer.setImage(images.get(18));
	    
	    //Enemy card
	    setupCardHide(card01);
	    setupCardHide(card02);
	    setupCardHide(card03);
	    setupCardHide(card04);
	    setupCardHide(card05);
	    //Player card
	    setupCardHide(card06);
	    setupCardHide(card07);
	    setupCardHide(card08);
	    setupCardHide(card09);
	    setupCardHide(card10);
	    
	    
	    
        // เพิ่ม event handlers ที่นี่
	    setupCardEvent(card01);
		setupCardEvent(card02);
		setupCardEvent(card03);
		setupCardEvent(card04);
		setupCardEvent(card05);
		setupCardEvent(card06);
		setupCardEvent(card07);
		setupCardEvent(card08);
		setupCardEvent(card09);
		setupCardEvent(card10);
		
    }
	
	private void setupCardEvent(Card card) {
        card.setOnMouseEntered(event -> {
            card.highlight();
        });

        card.setOnMouseExited(event -> {
            card.reset();
        });

        card.setOnMouseClicked(event -> {
            System.out.println("Clicked on: " + card.getName());
        });
    }
	
	private void setupCardHide(Card card) {
		card.setImage(images.get(0));
	}
	
	private void randomCard(Card card) {
		Random random = new Random();
		BattleRole[] roles = BattleRole.values(); //ดึงค่าทั้งหมดของ Enum		
		int randomNumber = random.nextInt(16);		
	}

}

