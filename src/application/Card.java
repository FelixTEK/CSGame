package application;

import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//Interface สำหรับการ์ด
interface ICard {
	BattleRole getRole();
	String getName(); 
	boolean isFaceUp();
	void flip();
	void highlight();  // เน้นการ์ดที่เลือก
	void reset();  // reset สถานะการ์ด
}

public class Card extends ImageView implements ICard {
	private BattleRole role;
	private String cardName;
    private boolean isFaceUp;
    private boolean isHighlighted;
    
    @Override
    public BattleRole getRole() {
        return role;
    }
    @Override
    public String getName() {    	    	
    	return cardName;
    }    
    @Override
    public boolean isFaceUp() {
        return isFaceUp;
    }
    @Override
    public void flip() {
        return;
    }
    @Override
    public void highlight() {
    	isHighlighted = true;
        setEffect(new Glow(0.8));
        setScaleX(1.2);
        setScaleY(1.2);
    }
    @Override
    public void reset() {
    	isHighlighted = false;
        setEffect(null);
        setScaleX(1.0);
        setScaleY(1.0);
        //setTranslateY(0);
    }
}
