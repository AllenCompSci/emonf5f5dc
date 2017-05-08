package classes;

import java.util.ArrayList;

public class PC {
    private static ArrayList<Pokemon> pc[] = new ArrayList[16];
    private static Pokemon placeholder = new Pokemon();
    private static Pokemon handling = new Pokemon();

    public static Pokemon getPlaceholder(){
        return placeholder;
    }

    public static void resetPlaceholder(){
        placeholder = new Pokemon();
    }

    public static Pokemon getHandling(){
        return handling;
    }

    public static void resetHandling(){
        handling = new Pokemon();
    }

    public static int getBoxSize(int boxNum){
        return pc[boxNum - 1].size();
    }

    public static void insertPokemon(int boxNum, int slot){
        if(getBoxSize(boxNum - 1) < slot){
            pc[boxNum - 1].add(handling);
            resetHandling();
        }
        else{
            switchPokemon(boxNum, slot);
        }
    }

    public static void takePokemon(int boxNum, int slot){
        handling = pc[boxNum - 1].remove(slot - 1);
    }

    public static void switchPokemon(int boxNum, int slot){
        placeholder = pc[boxNum - 1].get(slot - 1);
        pc[boxNum - 1].set(slot - 1, handling);
        handling = placeholder;
        resetPlaceholder();
    }



}
