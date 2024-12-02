public class Mage implements CharacterType{
    @Override
    public String getName() {
        return "Mage";
    }

    @Override
    public double getMaxMana() {
        return 150;
    }

    @Override
    public double getMaxHP() {
        return 80;
    }

    @Override
    public void removeTypeBonus(Player player) {
        double manaBonus = player.getIntelligence() * 2;
        player.setMaxMana(player.getMaxMana() - manaBonus);
        player.setMana(player.getMana() - manaBonus);
        player.setCurrentHP(Math.min(player.getCurrentHP(), player.getMaxHP()));
    }


    @Override
    public void applyTypeBonus(Player player) {
        double manaBonus = player.getIntelligence() * 2;
        player.setMaxMana(player.getMaxMana() + (player.getIntelligence() * 2)); // increase Max Mana by Intelligence
        player.setCurrentHP(player.getMaxHP());
        player.setMana(player.getMana() + manaBonus);
        System.out.println("Mage bonus applied: Max Mana increased by " + (player.getIntelligence() * 2));
    }


    @Override
    public String specialSkill() {
        return "Special Skill: Arcane Blast! Deals massive damage to the enemy!";
    }

    //Use intelligence to increase the strength.
    @Override
    public void useSkill(Player player) {
        if(player.getMana() >= 20){
            double skillDamage = 30 + (player.getIntelligence() * 1.5);
            System.out.println("Mage casts a powerful fireball, dealing " + skillDamage + " damage!");
            player.setMana(player.getMana() - 20);
            if(player instanceof Enemy) {
                Enemy enemy = (Enemy) player;
                enemy.setCurrentHP(enemy.getCurrentHP() - skillDamage);
                System.out.println("Enemy's HP after skill: " + enemy.getCurrentHP());
            }

        } else {
            System.out.println("Not enough mana to cast this skill!");
        }
    }
}
