import java.text.DecimalFormat;
import java.util.Scanner;

public class Game{
    static Player player;
    private CharacterType selectedCharacter;
    static Scanner sc = new Scanner(System.in);
    public static boolean isRunning;

    // read user input
    public static int readInt(String prompt, int userChoice) {
        int input;
        do{
            System.out.print(prompt);
            try{
                input = Integer.parseInt(sc.nextLine());
            }catch (Exception e){
                input = -1;
                System.out.println("please enter an integer.");
            }
        } while (input < 1 || input > userChoice);
        return input;
    }

    public static void clearConsole() {
        for(int i = 0; i < 100; i++){
            System.out.println();
        }
    }

    public static void continueTheGame(){
        System.out.println("Enter anything to continue...");
        sc.next();
    }

    //create center text
    public static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text + " ".repeat(Math.max(0, padding));
    }
    //print heading
    public static void printHeading(){
        String border = "=".repeat(60);
        String title = "★ A Text-Based RPG Game ★";

        System.out.println("\n" + border);
        System.out.printf("║%s║%n", centerText(title, 58));
        System.out.println(border);
        System.out.println(centerText("WELCOME TO THE GAME!", 60));
        continueTheGame();
    }

    //method to start the game
    public static void startGame(){
        boolean nameSet = false;
        String name;
        //print screen
        printHeading();

        //getting the player name
        do {
            System.out.print("What is your name? ");
            name = sc.next();
            clearConsole();
            System.out.print("Your name is " + name + ".\nIs that correct? ");
            System.out.println("\n(1) YES");
            System.out.println("(2) NO, I want to change my name");
            int input = readInt("->", 2);
            if(input == 1){
                nameSet = true;
            }
        } while(!nameSet);

        // create new player
        player = new Player(name);

        player.chooseCharacter();

        //choose an accessories
        System.out.println("Choose an accessories: ");
        System.out.println("(1) Totem\n(2) Compass");
        int input = readInt("->", 2);

        Accessory chosenAccessory = null;
        switch(input){
            case 1 -> {
                chosenAccessory = new Totem();
            }
            case 2 -> {
                chosenAccessory = new Compass();
            }
        }

        // Set the chosen accessory to the player
        assert chosenAccessory != null;
        chosenAccessory.applyEffect(player);
        player.setAccessory(chosenAccessory);

        //setting isRunning to true, so the game loop can continue
        isRunning = true;

        //main game loop
        battle();
    }

    //method to continue the game
    public static void continueGame(){
        while(isRunning){
            //print some character's information
            characterInfo();
            //player choose an action
            System.out.println("\nYou win!");
            System.out.println("\n(1) Next stage");
            System.out.println("(2) Quit");

            int choice = readInt("->", 2);

            if(choice == 1){
                battle(); //is this correct?
            } else if(choice == 2){
                // quit the game
                isRunning = false;
                System.out.println("\nEND GAME");
                break;
            }
        }
    }

    //print the information about player character
    public static void characterInfo() {
        System.out.println("=== CHARACTER INFO ===");
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Name: " + player.name);
        System.out.println("Class: " + player.getSelectedCharacter().getClass().getSimpleName());
        System.out.println("Level: " + player.getLevel());
        System.out.println("HP: " + df.format(player.getCurrentHP()) + " / " + df.format(player.getMaxHP()));
        System.out.println("Attack: " + df.format(player.getStrength()));
        System.out.println("Defense: " + df.format(player.getDefense()));
        System.out.println("======================");
    }

    // main game loop
    public static void battle(){
        // Create an enemy
        Enemy enemy = new Enemy("Enemy");

        clearConsole();
        System.out.println("Enemy is here! Fight for your life!");
        while(enemy.getCurrentHP() > 0 && player.getCurrentHP() > 0){
            //print enemy information name,Hp/maxHp
            System.out.println("==== ENEMY INFO ====");
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println("Enemy HP: " + df.format(enemy.getCurrentHP()) + " / " + df.format(enemy.getMaxHP()));
            System.out.println("Enemy Attack: " + df.format(enemy.getAttackPower()));
            System.out.println("=====================");

            // Print player's information
            characterInfo();
            //player choose an action
            System.out.println("Choose an action: ");
            System.out.println("(1) Fight!\n(2) Use special skill\n(3) Defend");
            int input = readInt("->", 3);

            switch(input){
                case 1 -> {
                    //fight the enemy
                    clearConsole();
                    player.attack(enemy);
                    System.out.println("You dealt " + player.getStrength() + " damage to the enemy!");
                    enemy.setCurrentHP(enemy.getCurrentHP() - player.getStrength());
                }

                case 2 -> {
                    //use character special skill
//                    clearConsole();
//                    player.getSelectedCharacter().useSkill(player);
                    clearConsole();
                    clearConsole();
                    if (player.getSelectedCharacter() instanceof Assassin) {
                        Assassin assassin = (Assassin) player.getSelectedCharacter();
                        assassin.useSkill(enemy);  // Assassin specific skill
                    } else if (player.getSelectedCharacter() instanceof Berserker) {
                        Berserker berserker = (Berserker) player.getSelectedCharacter();
                        berserker.useSkill(enemy);  // Berserker specific skill
                    } else if (player.getSelectedCharacter() instanceof Mage) {
                        Mage mage = (Mage) player.getSelectedCharacter();
                        mage.useSkill(enemy);  // Mage specific skill
                    } else if (player.getSelectedCharacter() instanceof Archer) {
                        Archer archer = (Archer) player.getSelectedCharacter();
                        archer.useSkill(enemy);  // Archer specific skill
                    } else {
                        System.out.println("This character cannot use a special skill.");
                    }
                }
                case 3 -> {
                    //defend
                    clearConsole();
                    double reducedDamage = Math.max(0, enemy.getAttackPower() - player.getDefense());
                    System.out.println("You reduced the enemy's attack to " + reducedDamage);
                    player.setCurrentHP(player.getCurrentHP() - reducedDamage);
                }
                default -> System.out.println("Invalid action! Try again.");
            }

            //enemy attacks player after player's turn
            if (enemy.getCurrentHP() > 0){
                System.out.println("\nThe enemy attack!!");
                enemy.attackPlayer(player);
            }
        }
        //check battle outcome
        if(player.getCurrentHP() <= 0){
            System.out.println("\nYou lose!");
            isRunning = false;
            endGameorRetry();
        } else if(enemy.getCurrentHP() <= 0){
            System.out.println("\nYou win!");
            showWinScreen();
            continueGame();
        }
    }

    // player choose to replay or quit the game
    public static void endGameorRetry(){
        showEndGameScreen();
        System.out.println("\nDo you want to:");
        System.out.println("(1) Replay \n(2) Exit");
        int choice = readInt("->", 2);
        if(choice == 1){
            //Reset player stats
            Player.resetStats(player);
            //restart the battle
            battle();
        } else if(choice == 2){
            //Quit the game
            isRunning = false;
        }
    }

    public static void showEndGameScreen() {
        //Border
        String border = "*".repeat(60);

        // Centered Text
        String title = "★ GAME OVER ★";
        String replayPrompt = "Do you want to try again?";

        System.out.println(border);
        System.out.printf("║%s║%n", centerText(title, 58));
        System.out.println(border);

        // Add some space
        System.out.println();
        System.out.println(centerText(replayPrompt, 60));
        System.out.println();
    }

    public static void showWinScreen() {
        // Border
        String border = "*".repeat(60);

        // Centered Text
        String title = "★ YOU WIN ★";
        String rewardPrompt = "Congratulations! You have defeated the enemy.";

        System.out.println(border);
        System.out.printf("║%s║%n", centerText(title, 58));
        System.out.println(border);

        // Add some space
        System.out.println();
        System.out.println(centerText(rewardPrompt, 60));
        System.out.println();
    }



}
