public interface CharacterType {
    String getName();
    String specialSkill();
    void useSkill(Player player);
    void applyTypeBonus(Player player);
    void removeTypeBonus(Player player);
    double getMaxHP();
    double getMaxMana();
}

