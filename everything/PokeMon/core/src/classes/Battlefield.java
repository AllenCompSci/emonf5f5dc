package classes;

import net.dermetfan.gdx.physics.box2d.PositionController;

public class Battlefield {
    private static Pokemon[] opponentParty;

    private static boolean endturn;
    private static boolean battling;
    private static int yourPoke;
    private static int oppPoke;
    private static boolean youFirst;
    public static int selectedMove = 0;

    public static void battlefield(String opponent){
        opponentParty = new Pokemon[6];
        String oppParty[] = Utility.OpponentParties.get(opponent);
//        System.out.println(Utility.OpponentParties);
        for(int i = 0; i < 6; i++){
//            opponentParty[i] = new Pokemon(oParty[i][0], Integer.valueOf(oParty[i][1]));
            opponentParty[i] = new Pokemon(oppParty[i], 50);
        }
        battling = false;
        yourPoke = 0;
        oppPoke = 0;
        youFirst = opponentParty[oppPoke].getStat(Utility.STATS.SPEED) <= Party.party.get(yourPoke).getStat(Utility.STATS.SPEED);

        if(Party.party.size() > 0){

        }
        else Party.insert(new Pokemon("6", 50), 0);
    }

    public static void turn(int move){
        System.out.print("Yes");
        if(youFirst){
            opponentParty[oppPoke].attacked(Party.party.get(yourPoke).getKnownMoves().get(move), Party.party.get(yourPoke));
            if(opponentParty[oppPoke].getStat(Utility.STATS.HP) == 0){
                oppFainted();
            }
            else{
                Party.party.get(yourPoke).attacked(opponentParty[oppPoke].getKnownMoves().get((int)(Math.random() * 4)), opponentParty[oppPoke]);
                if(Party.party.get(yourPoke).getStat(Utility.STATS.HP) == 0){
                    youFainted();
                }
            }
        }
        else{
            Party.party.get(yourPoke).attacked(opponentParty[oppPoke].getKnownMoves().get((int)(Math.random() * 4)), opponentParty[oppPoke]);
            if(Party.party.get(yourPoke).getStat(Utility.STATS.HP) == 0){
                youFainted();
            }
            else{
                opponentParty[oppPoke].attacked(Party.party.get(yourPoke).getKnownMoves().get(move), Party.party.get(yourPoke));
                if(opponentParty[oppPoke].getStat(Utility.STATS.HP) == 0){
                    oppFainted();
                }
            }
        }
    }

    public static void oppFainted(){

    }

    public static void youFainted(){

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