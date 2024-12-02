public class Compass implements Accessory{

    @Override
    public String getName() {
        return "Compass";
    }

    @Override
    public String getEffects() {
        return "Critical Rate: +20%, Evasion: +15%";
    }

    @Override
    public void applyEffect(Player player) {
        player.setCritRate(player.getCritRate() + 20.0);
        player.setEvasion(player.getEvasion() + 15.0);
        System.out.println("Compass equipped! " + getEffects());
    }

    @Override
    public void removeEffect(Player player) {
        player.setCritRate(player.getCritRate() + 20.0);
        player.setEvasion(player.getEvasion() + 15.0);
        System.out.println("Compass unequipped! Effects removed.");
    }
}
