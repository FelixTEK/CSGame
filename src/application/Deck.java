package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	
	private final List<Card> cards = new ArrayList<>();
	private final Random random = new Random();
	private final static int maxDeckSize = 5;
	private double probabilityHeal;
	
	public Deck(double prob) {
		setProbability(prob);
        createDeck();
    }
	
    //Random from ENUM CardType
    private CardType getRandomCardType() {
    	int x;
    	double y;
    	//get All CardType
        CardType[] types = CardType.values();
        // types.length = 16 and generate random 0 - 15       
        x=random.nextInt(types.length);
        //generate number double >> 0.0-1.0
        //if generate number < probability get heal card then get heal card
        System.out.println("Random Int(x) : "+x);
        
        y=random.nextDouble();
        if(y<this.probabilityHeal) {
        	x=8; //CLERIC = Card No 8 in Card type
        }
        System.out.println(" Random Double(y) : "+y+" Probability : "+this.probabilityHeal+" Last x: "+x);
        
        return types[x]; 
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
    
    public void setProbability(double Values) {
    	this.probabilityHeal=Values;
    }
}
