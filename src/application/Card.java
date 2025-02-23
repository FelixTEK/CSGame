package application;

public class Card implements ICard {
	private final CardType type;
    private boolean isFaceUp = false;

    public Card(CardType type) {
        this.type =type;
    }

    @Override
    public int getAttack() { return type.getAttack(); }
    @Override
    public int getDefense() { return type.getDefense(); }
    @Override
    public int getHp() { return type.getHp(); }
    @Override
    public String getImagePath() { return type.getImagePath(); }

    @Override
    public boolean isFaceUp() { return isFaceUp; }  
    @Override
    public void flipCard() { isFaceUp = !isFaceUp; }      
    
}
