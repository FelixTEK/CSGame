package application;

public interface ICard {
	int getAttack();
    int getDefense();    
    int getHp();
    boolean isFaceUp();
    String getImagePath();
    void flipCard();
}