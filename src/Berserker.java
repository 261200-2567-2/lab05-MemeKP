public class Berserker implements CharacterType {
    private int manaCost = 35;

    @Override
    public String getName() {
        return "Berserker";
    }

    @Override
    public double getMaxHP() {
        return 200;
    }

    @Override
    public double getMaxMana() {
        return 50;
    }

    @Override
    public String specialSkill() {
        return "Special Skill: Berserk Mode! Attack power+50 Defense-20";
    }

    @Override
    public void applyTypeBonus(Player player) {
        // Apply bonuses specific to the Berserk class
        player.setStrength(player.getStrength() + 20);  // Increase strength by 20
        player.setDefense(player.getDefense() - 10);   // Decrease defense by 10
    }

    @Override
    public void removeTypeBonus(Player player) {
        // Remove the bonuses applied earlier
        player.setStrength(player.getStrength() - 20);  // Revert strength bonus
        player.setDefense(player.getDefense() + 10);    // Revert defense penalty
    }

    @Override
    public void useSkill(Player player) {
        if (player.getMana() < manaCost){
            System.out.println("You do not have enough mana!");
            return;
        }
        player.setStrength(player.getStrength() + 50);
        player.setDefense(player.getDefense() - 20);
        player.setMana(player.getMana() - manaCost);

        if (player instanceof Enemy) {
            Enemy enemy = (Enemy) player;
            double damage = player.getStrength() - enemy.getDefense();
            damage = Math.max(0, damage);
            enemy.setCurrentHP(enemy.getCurrentHP() - damage);
            System.out.println("Berserker's special skill dealt " + damage + " damage to the enemy!");
        }
    }

    public void endSkillEffects(Player player) {
        System.out.println("Berserk Mode has ended!");
        player.setStrength(player.getStrength() - 50); // Revert strength boost
        player.setDefense(player.getDefense() + 20);  // Revert defense penalty
    }
}
