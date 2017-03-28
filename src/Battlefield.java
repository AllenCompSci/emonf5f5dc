package classes;

public class Battlefield {
    private Pokemon[] opponentParty;

    private boolean endturn;
    private int turncount;

    //when pokemon completes an action in battle, endturn becomes true to keep the order of the battle turncount goes up one
    //turncount is counter which tracks how many turns it has completed
    //the pokemon with the lowest turn count will go first, and if both pokemon have the same turncount, the first pokemon goes  m
    public int turnOrder(boolean endturn, int turncount){

    }




    public Battlefield(String[][] oParty){
        opponentParty = new Pokemon[oParty.length];
        for(int i = 0; i < opponentParty.length; i++){
            opponentParty[i] = new Pokemon(oParty[i][0], Integer.valueOf(oParty[i][1]));
        }
    }



}
