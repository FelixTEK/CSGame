package application;

public interface IGameCharecter {
    void takeDamage(int damage);
    int getHp();
    int getMaxHp();
    boolean isAlive();
}
