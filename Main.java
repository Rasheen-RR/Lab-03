
import javax.management.InvalidAttributeValueException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static Dice dice = null;
    private static boolean restart = true;
    private static int coldYahtzeeNumber = 5;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(true){
            try{
                printMenu();
                int userSelection = sc.nextInt();
                menu(userSelection,sc);
                if(restart){
                    continue;
                }else{
                    return;
                }
            }catch (Exception ex){
                sc.nextLine();
            }
        }
    }

    public static void menu(int userSelection, Scanner sc){
        switch (userSelection){
            case 0:
                restart = false;
                break;
            case 1:
                menuItem1();
                System.out.println(dice);
                restart = true;
                break;
            case 2:
                menuItem2(sc);
                restart = true;
                break;
            case 3:
                menuItem3(sc);
                restart = true;
                break;
            case 4:
                rollDice(dice);
                restart = true;
                System.out.println(dice);
                break;
            case 5:
                setSideUp(sc);
                restart = true;
                break;
            case 6:
                coldYahtzee();
                restart = true;
                break;

        }
    }

    public static Dice menuItem1(){
        dice = new Dice();
        return dice;
    }

    public static Dice menuItem2(Scanner sc){
        System.out.println("Enter required number of sides");

        while(true){
            try{
                int numSides = sc.nextInt();
                sc.nextLine();
                dice = new Dice(numSides);
                System.out.println(dice);
                return dice;
            }catch(Exception ex){
                System.out.println("Enter a valid number!");
                sc.nextLine();
            }
        }
    }

    public static Dice menuItem3(Scanner sc){
        sc.nextLine();
        System.out.println("Enter dice type");
        String diceType = sc.nextLine();

        System.out.println("Enter required number of sides");

        while(true){
            try{
                int numSides = sc.nextInt();
                sc.nextLine();
                dice = new Dice(diceType,numSides);
                System.out.println(dice);
                return dice;
            }catch(Exception ex){
                System.out.println("Enter a valid number!");
                sc.nextLine();
            }
        }
    }

    public static void rollDice(Dice dice){
        if(dice == null){
            System.out.println("Create a dice first!");
        }else{
            Random random = new Random();
            int randomNum =  ThreadLocalRandom.current().nextInt(1, dice.getNumSides() + 1);
            dice.setSideUp(randomNum);
            dice.setDiceType(String.format("d%s",randomNum));
            System.out.println(String.format("You rolled: %s", randomNum));
        }
    }

    public static void setSideUp(Scanner sc){
        if(dice == null){
            System.out.println("Create a dice first!");
        }else{
            System.out.println("Enter which side is required to be up");

            while(true){
                try{
                    int sideUp = sc.nextInt();
                    sc.nextLine();
                    if(sideUp > dice.getNumSides()){
                        throw new InvalidAttributeValueException();
                    }else{
                        dice.setSideUp(sideUp);
                        System.out.println(dice);
                        return;
                    }
                }catch (InvalidAttributeValueException ex){
                    System.out.println("Enter a valid side!");
                    continue;
                }
            }
        }
    }

    public static void coldYahtzee(){
        boolean isCold = false;
        int count = 0;

        Dice[] diceArray = new Dice[coldYahtzeeNumber];
        for(int i = 0; i < coldYahtzeeNumber; i++){
            diceArray[i] = new Dice();
        }

        for(int i = 0; i < coldYahtzeeNumber; i++){
            System.out.println(diceArray[i]);
        }

        while(!isCold){
            for(int i = 0; i < coldYahtzeeNumber; i++){
                rollDice(diceArray[i]);
            }

            count++;

//            if(diceArray[0].getSideUp() == diceArray[1].getSideUp() &&
//                    diceArray[1].getSideUp() == diceArray[2].getSideUp()&&
//                    diceArray[2].getSideUp() == diceArray[3].getSideUp() &&
//                    diceArray[3].getSideUp() == diceArray[4].getSideUp()){
//                isCold = true;
//
//                System.out.println(count);
//
//                for(int i = 0; i < coldYahtzeeNumber; i++){
//                    System.out.println(diceArray[i]);
//                }
//            }

            for(int i = 0; i < coldYahtzeeNumber - 1; i++){
                if(diceArray[i].getSideUp() == diceArray[i+1].getSideUp()){
                    isCold = true;
                }else{
                    isCold = false;
                    break;
                }
            }

            if(isCold){
                System.out.println(count);
                for(int i = 0; i < coldYahtzeeNumber; i++){
                    System.out.println(diceArray[i]);
                }
            }
        }
    }


    public static void printMenu(){
        System.out.println("\n=========================== Dice Game ===========================");
        System.out.println("Enter 1 to create new dice with 6 sides");
        System.out.println("Enter 2 to create new dice with n sides");
        System.out.println("Enter 3 to create new dice with n sides and Name");
        System.out.println("Enter 4 to roll dice");
        System.out.println("Enter 5 to set side up");
        System.out.println("Enter 6 to cold-Yahtzee ");
        System.out.println("Enter 0 to quit ");
        System.out.println("=========================== Dice Game ===========================");
        System.out.println("Select : ");
    }


}
