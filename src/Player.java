import java.util.Scanner;

public class Player {
    protected String name;
    private CharacterType selectedCharacter;
    private Accessory accessory;
    private double strength;
    private double intelligence;
    private double defense;
    private double currentHP;
    private double maxHP;
    private double currentMana;
    private double maxMana;
    private int level;
    private double critRate; //(%)
    private double evasion; //(%)
    private boolean isAlive = true;
    private double mana; ////

    static Scanner sc = new Scanner(System.in);

    // array store character type
    public String[] c = {"Berserker", "Mage", "Assassin", "Archer"};

    public Player(String name) {
        this.name = name;
        this.maxHP = 100;
        this.currentHP = maxHP;
        this.maxMana = 40;
        this.currentMana = maxMana;
        this.strength = 20;
        this.intelligence = 5;
        this.defense = 5;
        this.level = 1;
    }

    public String getName() {
        return name;
    }

    // let player choose a character
    public void chooseCharacter() {
        boolean validInput = false;

        while (!validInput) {
            Game.clearConsole();
            System.out.println("Choose a character:");
            //show each character details
            for (int i = 0; i < c.length; i++) {
                CharacterType tempCha = null;
                switch (i+1){
                    case 1 -> tempCha = new Berserker();
                    case 2 -> tempCha = new Mage();
                    case 3 -> tempCha = new Assassin();
                    case 4 -> tempCha = new Archer();
                }

                // print details
                if (tempCha != null) {
                    System.out.println((i + 1) + ". " + tempCha.getName());
                    System.out.println("   - " + tempCha.specialSkill());
                }
            }

            //player choose the character
            int choice = Game.readInt("-> ", c.length);
            switch (choice) {
                case 1 -> selectedCharacter = new Berserker();
                case 2 -> selectedCharacter = new Mage();
                case 3 -> selectedCharacter = new Assassin();
                case 4 -> selectedCharacter = new Archer();
                default -> {
                    System.out.println("Invalid choice! try again.");
                    continue;
                }
            }
            validInput = true;
            System.out.println("You have selected: " + selectedCharacter.getName());
        }
        //Apply the bonus of the select character
        selectedCharacter.applyTypeBonus(this);
        System.out.println(selectedCharacter.getName() + " bonuses have been applied!");
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
        //apply the accessory
        this.accessory.applyEffect(this);
        System.out.println(accessory.getName() + " applied!");
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public CharacterType getSelectedCharacter() {
        return selectedCharacter;
    }

    public double getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(double currentHP) {
        this.currentHP = Math.min(currentHP, this.maxHP);
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = Math.max(0, maxHP); //
    }

    public double getMana() {
        return currentMana;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(double maxMana) {
        this.maxMana = Math.max(0, maxMana);
        if (this.currentMana > this.maxMana) {
            this.currentMana = this.maxMana;
        }
    }

    public void setMana(double currentMana) {
        this.currentMana = Math.min(maxMana, Math.max(0, currentMana));
    }

    public double getStrength(){
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = Math.max(0, strength);
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = Math.max(0, defense);
    }

    public double getCritRate(){
        return critRate;
    }

    public void setCritRate(double critRate) {
        this.critRate = Math.max(0, critRate);
    }

    public double getEvasion() {
        return evasion;
    }

    public void setEvasion(double evasion) {
        this.evasion = Math.max(0, evasion);
    }

    public void attack(Player player) {
        double critChance = Math.random() * 100;
        boolean isCrit = critChance <= this.critRate;

        double evasionChance = Math.random() * 100;
        boolean evaded = evasionChance <= player.getEvasion();

        if (evaded) {
            System.out.println(player.name + " evaded the attack!");
            return;
        }

        int baseDamage = (int) this.strength;
        int damage = isCrit ? (int) (baseDamage * 1.5) : baseDamage;

        System.out.println(isCrit ? "Critical Hit!" : "Normal Hit!");
    }

    public void takeDamage(int attackPower) {
        int damage = attackPower - (int) this.defense; // Ensure proper integer casting
        damage = Math.max(0, damage); // Prevent negative damage
        this.currentHP -= damage;

        System.out.println("You took " + damage + " damage!");

        if (this.currentHP <= 0) {
            this.currentHP = 0;
            System.out.println("You are dead!");
            System.out.println("GAME OVER!");
        }
    }

    public int getLevel() {
        return level;
    }

    // reset stats
    public static void resetStats(Player player) {
        player.currentHP = player.getMaxHP();
        player.currentMana = player.getMaxMana();
        player.strength = 10 + (player.level - 1) * 15;
        player.defense = 5 + (player.level - 1) * 8;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = Math.max(0, intelligence);
    }

}




