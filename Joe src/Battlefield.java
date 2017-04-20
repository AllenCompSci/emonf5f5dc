package classes;

public class Battlefield {
    private static Pokemon[] opponentParty;

    private static boolean endturn;
    private static boolean battling;
    private static int yourPoke;
    private static int oppPoke;
    private static boolean youFirst;
    public static int selectedMove = 0;

    public void battlefield(String[][] oParty){
        opponentParty = new Pokemon[oParty.length];
        for(int i = 0; i < opponentParty.length; i++){
            opponentParty[i] = new Pokemon(oParty[i][0], Integer.valueOf(oParty[i][1]));
        }
        battling = false;
        yourPoke = 0;
        oppPoke = 0;
        youFirst = opponentParty[oppPoke].getStat(Utility.STATS.SPEED) <= Party.party.get(yourPoke).getStat(Utility.STATS.SPEED);


    }

    public void turn(int move){
        if(youFirst){
            opponentParty[oppPoke].attacked(Party.party.get(yourPoke).getKnownMoves().get(selectedMove - 1), Party.party.get(yourPoke));
            if(opponentParty[oppPoke].getStat(Utility.STATS.HP) == 0){

            }
        }
    }



    public int getYourPoke() {
        return yourPoke;
    }

    public int getOppPoke() {
        return oppPoke;
    }

    public boolean getBattling(){
        return battling;
    }



}