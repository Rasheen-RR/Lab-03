
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private String diceType;
    private int numSides ;
    private int sideUp;


    public Dice() {
        this.diceType = "d6";
        this.numSides = 6;
        this.sideUp  = randomize(1,6);
    }

    public Dice(int numSides) {
        this.diceType = "d"+numSides;
        this.numSides = numSides;
        this.sideUp = randomize(1,numSides);
    }

    public Dice(String diceType,int numSides) {
        this.diceType = diceType;
        this.numSides = numSides;
        this.sideUp = randomize(1,numSides);
    }

//    public String getDiceType() {
//        return diceType;
//    }

    public void setDiceType(String diceType) {
        this.diceType = diceType;
    }

    public int getNumSides() {
        return numSides;
    }

//    public void setNumSides(int numSides) {
//        this.numSides = numSides;
//    }

    public int getSideUp() {
        return sideUp;
    }

    public void setSideUp(int sideUp) {
        this.sideUp = sideUp;
    }

    public int randomize(int min, int max){
        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }


    public void rollDice(){
            int randomNum =  ThreadLocalRandom.current().nextInt(1, this.getNumSides() + 1);
            this.setSideUp(randomNum);
            System.out.println(String.format("You rolled: %s", randomNum));
    }

    @Override
    public String toString() {
        return "Dice : " +
                "diceType='" + diceType + '\'' +
                ", numSides=" + numSides +
                ", sideUp=" + sideUp;
    }
}
