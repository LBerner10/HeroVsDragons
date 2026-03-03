import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("""
                \s
                **************************************
                      Welcome to Heroes Vs Dragons
                **************************************
                """);
        System.out.println("""
                What difficulty would you like to play.
                1. Easy
                2. Normal
                3. Hard
                Please enter the difficulty you wish to play:""");
        String mode = scanner.nextLine();

        //Player//
        boolean PlayerWins = false;
        int PlayerHP;
        int PlayerStrength;

        //Dragons//
        int Dragon1HP = 100;
        int Dragon2HP = 120;
        int Dragon3HP = 200;
        int Dragon1Strength = 25;
        int Dragon2Strength = 20;
        int Dragon3Strength = 5;

        if (mode.equalsIgnoreCase("Easy")){
            PlayerHP = 200;
            PlayerStrength = 15;
        } else if (mode.equalsIgnoreCase("Normal")){
            PlayerHP = 150;
            PlayerStrength = 10;
        } else if (mode.equalsIgnoreCase("Hard")){
            PlayerHP = 100;
            PlayerStrength = 5;
        } else {
            System.out.println("please try again");
            return;
        }

        while(true) {

            //Game Status//
            if(PlayerHP < 1) {
                break;
            }
            if ((Dragon1HP < 1) && (Dragon2HP < 1) && (Dragon3HP <1)) {
                PlayerWins = true;
                break;
            }

            //Player Turn//
            for(int PlayerTurns = 3; PlayerTurns > 0; PlayerTurns--) {
                System.out.println("\nPlayers turn to attack. Which dragon would you like to attack (1, 2, or 3) i.e. '3'. You have " +
                        String.valueOf(PlayerTurns) + " turn(s) left.");
                int Dragon = Integer.parseInt(scanner.nextLine());
                if (Dragon > 3){
                    System.out.println("Incorrect dragon was entered, you lost a turn. Please try again");
                } else {
                    System.out.println("""
                            \s
                            What attack would like to do.
                            1. Ultimate Attack (can only be used once per game)
                            2. Melee (Can be used as many times wanted)
                            3. Special Attack (can be used once per 3 turns)
                            Enter the attack you wish to perform i.e. "Special Attack" :""");
                    String AttackType = scanner.nextLine();
                    if (Dragon == 1 && Dragon1HP > 0) {
                        if (AttackType.equalsIgnoreCase("Ultimate Attack")) {
                            Dragon1HP = 0;
                        } else if (AttackType.equalsIgnoreCase("Special Attack")) {
                            Dragon1HP = Dragon1HP - (PlayerStrength * 25);
                        } else if (AttackType.equalsIgnoreCase("Melee")) {
                            Dragon1HP = Dragon1HP - (PlayerStrength * 5);
                        } else {
                            System.out.println("Incorrect type of attack, please try again");
                        }
                    } else if (Dragon == 2 && Dragon2HP > 0) {
                        if (AttackType.equalsIgnoreCase("Ultimate Attack")) {
                            Dragon2HP = 0;
                        } else if (AttackType.equalsIgnoreCase("Special Attack")) {
                            Dragon2HP = Dragon2HP - (PlayerStrength * 25);
                        } else if (AttackType.equalsIgnoreCase("Melee")) {
                            Dragon2HP = Dragon2HP - (PlayerStrength * 5);
                        } else {
                            System.out.println("Incorrect type of attack, please try again");
                        }
                    } else if (Dragon == 3 && Dragon3HP > 0) {
                        if (AttackType.equalsIgnoreCase("Ultimate Attack")) {
                            Dragon3HP = 0;
                        } else if (AttackType.equalsIgnoreCase("Special Attack")) {
                            Dragon3HP = Dragon3HP - (PlayerStrength * 5);
                        } else if (AttackType.equalsIgnoreCase("Melee")) {
                            Dragon3HP = Dragon3HP - (PlayerStrength);
                        } else {
                            System.out.println("Incorrect type of attack, please try again");
                        }
                    } else {
                        System.out.println("The dragon was already dead, you lost a turn. Please try again");
                    }
                }
            }

            //Dragons Turn//
            System.out.println();
            for(int DragonTurns = 3; DragonTurns > 0; DragonTurns--){
                int DragonAttack = random.nextInt(10);
                if (DragonTurns == 3 && Dragon1HP > 0){
                    if (DragonAttack < 8){
                        Thread.sleep(3000);
                        System.out.println("Dragon 1 has hit you with a melee attack -" + String.valueOf(Dragon1Strength) + "HP");
                        PlayerHP = PlayerHP - Dragon1Strength;
                    } else {
                        Thread.sleep(3000);
                        System.out.println("Dragon 1 has hit you with a fire breath attack -" + String.valueOf(Dragon1Strength*2) + "HP");
                        PlayerHP = PlayerHP - Dragon1Strength*2;
                    }
                } else if (DragonTurns == 2 && Dragon2HP > 0){
                    if (DragonAttack < 8){
                        Thread.sleep(3000);
                        System.out.println("Dragon 2 has hit you with a melee attack -" + String.valueOf(Dragon2Strength) + "HP");
                        PlayerHP = PlayerHP - Dragon2Strength;
                    } else {
                        Thread.sleep(3000);
                        System.out.println("Dragon 2 has hit you with a tail sweep attack -" + String.valueOf(Dragon2Strength*2) + "HP");
                        PlayerHP = PlayerHP - Dragon2Strength*2;
                    }
                } else if (DragonTurns == 1 && Dragon3HP > 0){
                    if (DragonAttack < 8){
                        Thread.sleep(3000);
                        System.out.println("Dragon 3 has hit you with a melee attack -" + String.valueOf(Dragon3Strength) + "HP");
                        PlayerHP = PlayerHP - Dragon3Strength;
                    } else {
                        Thread.sleep(3000);
                        System.out.println("Dragon 3 has hit you with a fireball attack -" + String.valueOf(Dragon3Strength*3) + "HP");
                        PlayerHP = PlayerHP - Dragon3Strength*3;
                    }
                }

            }

            //Displays Health/ Make sure health is never below 0//
            if(Dragon1HP < 0)
            {Dragon1HP = 0;}
            if(Dragon2HP < 0)
            {Dragon2HP = 0;}
            if(Dragon3HP < 0)
            {Dragon3HP = 0;}
            if(PlayerHP < 0)
            {PlayerHP =0;}

            System.out.println("\nDragon1HP: " + Dragon1HP + " | Dragon2HP: " + Dragon2HP + " | Dragon3HP; " + Dragon3HP);
            System.out.println("PlayerHP: " + PlayerHP);
        }

        System.out.print("Battle Outcome: ");
        if(PlayerWins == true) {
            System.out.println("WIN!");
        } else {
            System.out.println("LOSE!");
        }

    }
}