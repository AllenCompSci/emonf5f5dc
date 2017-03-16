package com.center.pokemon.desktop;


import java.util.ArrayList;

public class Party {
        private static ArrayList<Pokemon> party = new ArrayList<Pokemon>();
        private static Pokemon placeholder = new Pokemon();

        public static int getSize(){
            return party.size();
        }

        public static Pokemon getPlaceholder(){
            return placeholder;
        }

        public static void resetPlaceholder(){
            placeholder = new Pokemon();
        }

        public static void insert(Pokemon partyMember, int slot){
            if(Party.getSize() < slot){
                party.add(partyMember);
            }
            else{
                switchMember(partyMember, slot);
            }
        }

        public static void switchMember(Pokemon partyMember, int slot){
            placeholder = party.get(slot - 1);
            party.set(slot - 1, partyMember);
        }

        public static void removeFromParty(int slot){
            placeholder = party.remove(slot - 1);
        }

        public static Pokemon moveToPC(int slot){
            return party.remove(slot - 1);
        }

}
