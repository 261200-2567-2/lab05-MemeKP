public class Enemy extends Player {
    private final double attackPower;

    public Enemy(String name) {
        super(name); // call constructor of Player
        this.attackPower = 30;
        this.setMaxHP(200);
        this.setCurrentHP(200);
    }

    public void attackPlayer(Player player) {
        double damage = attackPower;
        player.takeDamage((int)damage);
        System.out.println("Enemy dealt " + damage + " damage to " + player.name);
    }

    public double getAttackPower(){
        return attackPower;
    }
}
