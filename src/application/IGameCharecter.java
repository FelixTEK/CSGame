package application;

public interface IGameCharecter {
    void takeDamage(int damage);
    void heal(int amount);
    int getHp();
    int getMaxHp();
    boolean isAlive();
}
