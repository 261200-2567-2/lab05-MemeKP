public class Archer implements CharacterType{
    @Override
    public String getName() {
        return "Archer";
    }

    @Override
    public double getMaxMana() {
        return 80;
    }

    @Override
    public double getMaxHP() {
        return 120;
    }

    @Override
    public void removeTypeBonus(Player player) {
        player.setStrength(player.getStrength()-10);
        player.setDefense(player.getDefense()-5);
    }

    @Override
    public void applyTypeBonus(Player player) {
        player.setStrength(player.getStrength()+10);
        player.setDefense(player.getDefense()+5);
    }


    @Override
    public String specialSkill() {
        return "Special Skill: A storm of arrows!";
    }

    @Override
    public void useSkill(Player player) {
        if (player.getMana() < 20) {
            System.out.println("Your mana is too low. Cannot use special skill.");
            return;
        }
        player.setMana(player.getMana() - 20);

        double damage = 30 + player.getStrength();

        double evasionChance = player.getEvasion();
        double randomChance = Math.random() * 100;

        if (randomChance < evasionChance) {
            System.out.println("The attack has been evaded! No damage dealt.");
            return;
        }
        if(player instanceof Enemy) {
            Enemy enemy = (Enemy) player;
            System.out.println("Storm of arrows has been used! Deals " + damage + " damage to enemies!");
            enemy.setCurrentHP(enemy.getCurrentHP() - damage);
//            System.out.println("Enemy's HP after skill: " + enemy.getCurrentHP());
        }
    }

}
