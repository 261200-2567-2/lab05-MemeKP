public interface Accessory {
    String getName();
    String getEffects();
    void applyEffect(Player player);
    void removeEffect(Player player);
}
