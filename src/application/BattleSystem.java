package application;

public class BattleSystem {
	public static String battle(Card cardEnemy, Card cardPlayer) {
        int damage1 = cardEnemy.getRole().getAttack() - cardPlayer.getRole().getDefense();
        int damage2 = cardPlayer.getRole().getAttack() - cardEnemy.getRole().getDefense();

        if (damage1 > damage2) return cardEnemy.getName() + " ชนะ!";
        if (damage2 > damage1) return cardPlayer.getName() + " ชนะ!";
        return "เสมอ!";
    }
}
