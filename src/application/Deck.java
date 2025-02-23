package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	
	private final List<Card> cards = new ArrayList<>();
	private final Random random = new Random();
	private final static int maxDeckSize = 5;
	
	public Deck() {
        createDeck();
    }
	
    //Random from ENUM CardType
    private CardType getRandomCardType() {
    	//get All CardType
        CardType[] types = CardType.values();
        // types.length = 16 and generate random 0 - 15        
        return types[random.nextInt(types.length)]; 
    }
    
    private void createDeck() {
    	if (maxDeckSize > 0) {
    		cards.clear();
            for (int i = 0; i < maxDeckSize; i++) { // Random Card 5 times
            	CardType randomCardType = this.getRandomCardType();
                cards.add(new Card(randomCardType));
            }
        }
    }
    
    public int getMaxDeckSize() {return maxDeckSize; }
    
    public Card getCard(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.get(index);
        }
        return null; 
    }
    
    // Reload the deck if it's empty
    public void reloadDeck() {        
        createDeck();  // Recreate deck with random cards
    }

}
