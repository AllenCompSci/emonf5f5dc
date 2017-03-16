package com.center.pokemon.desktop;

public class Battlefield {
    private Pokemon[] opponentParty;

    public Battlefield(String[][] oParty){
        opponentParty = new Pokemon[oParty.length];
        for(int i = 0; i < opponentParty.length; i++){
            opponentParty[i] = new Pokemon(oParty[i][0], Integer.valueOf(oParty[i][1]));
        }
    }
    
}
