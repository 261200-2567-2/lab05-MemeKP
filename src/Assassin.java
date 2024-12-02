public class Assassin implements CharacterType{

    @Override
    public String getName() {
        return "Assassin";
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
        return "Special Skill: Silent Kill... Instantly kills an enemy if their HP is below 30%.";
    }

    @Override
    public void useSkill(Player player) {
        if(player instanceof Enemy) {
            Enemy enemy = (Enemy) player;
            if(enemy.getCurrentHP() < enemy.getMaxHP() * 0.3){
                enemy.setCurrentHP(0);
                System.out.println("Assassin uses Silent Kill! Enemy is dead.");
            } else {
                System.out.println("Enemy's HP is too high. Can not use Silent Kill!");
            }

        }
    }
}
