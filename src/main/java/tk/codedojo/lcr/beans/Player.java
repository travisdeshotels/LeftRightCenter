package tk.codedojo.lcr.beans;

public class Player {
    private int coins;
    private String name;

    public Player(String name){
        coins = 3;
        this.name = name;
    }

    public void addCoins(int coins){
        this.coins += coins;
    }

    public void removeCoins(int coins){
        this.coins -= coins;
    }

    public int getCoins(){
        return this.coins;
    }

    public String getName(){
        return this.name;
    }
}
