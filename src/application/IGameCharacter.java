package application;

public interface IGameCharacter {
    void takeDamage(int damage);
    int getHp();
    int getMaxHp();
    boolean isAlive();
}
