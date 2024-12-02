public class Totem implements Accessory{
    @Override
    public String getName() {
        return "Totem";
    }

    @Override
    public String getEffects() {
        return "HP: +45, Attack: +15%, Defense: +5";
    }

    @Override
    public void applyEffect(Player player) {
        //HP++
        player.setCurrentHP(player.getCurrentHP() + 45);
        player.setMaxHP(player.getMaxHP() + 45);
        //ATK++
        double newAtk = player.getStrength() * 1.15;
        player.setStrength(newAtk);
        //Def++
        double newDefense = player.getDefense() + 5;
        player.setDefense(newDefense);
        System.out.println("Totem equipped! " + getEffects());
    }

    @Override
    public void removeEffect(Player player) {
        player.setMaxHP(player.getMaxHP() - 45);
        player.setCurrentHP(Math.min(player.getCurrentHP(), player.getMaxHP()));
        double originalAttack = player.getStrength() / 1.15;
        player.setStrength(originalAttack);
        double originalDefense = player.getDefense() - 5;
        player.setDefense(originalDefense);
        System.out.println("Totem removed! Effects removed.");
    }
}
