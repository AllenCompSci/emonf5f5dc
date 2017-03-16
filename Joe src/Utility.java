package com.center.pokemon.desktop;

import com.sun.org.apache.xerces.internal.jaxp.validation.ErrorHandlerAdaptor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Utility {

    public enum ExperienceGroup {ERRATIC, FAST, MEDIUMFAST, MEDIUMSLOW, SLOW, FLUCTUATING}

    public enum CATEGORY{PHYSICAL, SPECIAL, STATUS}

    public enum CONTEST{COOL, BEAUTIFUL, CUTE, CLEVER, TOUGH}

    public enum STATS{HP, ATTACK, DEFENSE, SP_ATTACK, SP_DEFENSE, SPEED, TOTAL, AVG}

    public enum TYPE{NORMAL, FIRE, WATER, GRASS, ELECTRIC, ICE, FIGHTING, POISON, HUDSON, GROUND, FLYING, PSYCHIC, BUG, ROCK, GHOST, DRAGON, DARK, STEEL, FAIRY, GLITCH, QMARK, SHADOW, TYPELESS, NONE }

    public enum NATURE{HARDY, LONELY, BRAVE, ADAMANT, NAUGHTY, BOLD, DOCILE, RELAXED, IMPISH, LAX, TIMID, HASTY, SERIOUS, JOLLY, NAIVE, MODEST, MILD, QUIET, BASHFUL, RASH, CALM, GENTLE, SASSY, CAREFUL, QUIRKY}

    public enum FLAVOR{SPICY, DRY, SWEET, BITTER, SOUR}

    public static ArrayList<Long> Erratic = new ArrayList<Long>(){
        {
            add(0L); // Erratic.get(0);
            for(int i = 2; i <= 100; i++){
                Long xp;
                if(i <= 50){
                    xp = (long)(Math.pow(i,3)*(100-i)/50);
                    add(xp);
                }
                else if(i <= 68){
                    xp = (long)(Math.pow(i,3)*(150-i)/100);
                    add(xp);
                }
                else if(i <= 98){
                    xp = (long)(Math.pow(i,3)*Math.floor((1911-(10*i))/3)/100);
                    add(xp);
                }
                else {
                    xp = (long)(Math.pow(i,3)*Math.floor((1911-(10*i))/3)/100);
                    add(xp);
                }
            }
            add(Erratic.get(99));
        }
    };

    public static ArrayList<Long> Fast = new ArrayList<Long>() {
        {
            add(0L); // *.get(0);

            for (int i = 2; i <= 100; i++) {
                Long xp;
                xp = (long)((int)(4 * Math.pow(i,3))/5);
                add(xp);
            }
            add(Fast.get(99));
        }
    };

    public static ArrayList<Long> MediumFast = new ArrayList<Long>() {
        {
            add(0L); // *.get(0);

            for (int i = 2; i <= 100; i++) {
                Long xp;
                xp = (long)((Math.pow(i,3)));
                add(xp);
            }
            add(MediumFast.get(99));
        }
    };

    public static ArrayList<Long> MediumSlow = new ArrayList<Long>() {
        {
            add(0L); // *.get(0);

            for (int i = 2; i <= 100; i++) {
                Long xp;
                xp = (long)((int)((6*Math.pow(i,3))/5 - (15*Math.pow(i,2)) + (100*i) - 140));
                add(xp);
            }
            add(MediumSlow.get(99));
        }
    };

    public static ArrayList<Long> Slow = new ArrayList<Long>() {
        {
            add(0L); // *.get(0);

            for (int i = 2; i <= 100; i++) {
                Long xp;
                xp = (long)((int)(5 * Math.pow(i,3))/4);
                add(xp);
            }
            add(Slow.get(99));
        }
    };

    public static ArrayList<Long> Fluctuating = new ArrayList<Long>(){
        {
            add(0L); // *.get(0);

            for(int i = 2; i <= 100; i++){
                Long xp;
                if(i <= 15){
                    xp = (long)(Math.pow(i,3)*(Math.floor((i+1)/3)+24)/50);
                    add(xp);
                }
                else if(i <= 36){
                    xp = (long)(Math.pow(i,3)*(i+14)/50);
                    add(xp);
                }
                else {
                    xp = (long)(Math.pow(i,3)* (((Math.floor(i/2))+32)/50));
                    add(xp);
                }
            }
            add(Fluctuating.get(99));
        }
    };

    public static Long TNL(int currentLevel, ExperienceGroup classification){
        if(classification == ExperienceGroup.ERRATIC) {
            return Erratic.get(currentLevel);
        }
        else if(classification == ExperienceGroup.FAST){
            return Fast.get(currentLevel);
        }
        else if(classification == ExperienceGroup.MEDIUMFAST){
            return MediumFast.get(currentLevel);
        }
        else if(classification == ExperienceGroup.MEDIUMSLOW) {
            return MediumSlow.get(currentLevel);
        }
        else if(classification == ExperienceGroup.SLOW){
            return Slow.get(currentLevel);
        }
        if(classification == ExperienceGroup.FLUCTUATING){}

        return Fluctuating.get(currentLevel);
    }

    public static ExperienceGroup expGroup(String xpGroup){
        ExperienceGroup returnGroup;
        int group;
        if(xpGroup.equals("ER")) group = 1;
        else if(xpGroup.equals("FA")) group = 2;
        else if(xpGroup.equals("MF")) group = 3;
        else if(xpGroup.equals("MS")) group = 4;
        else if(xpGroup.equals("SL")) group = 5;
        else group = 6;

        switch(group){
            case 1: returnGroup = ExperienceGroup.ERRATIC;
                break;
            case 2: returnGroup = ExperienceGroup.FAST;
                break;
            case 3: returnGroup = ExperienceGroup.MEDIUMFAST;
                break;
            case 4: returnGroup = ExperienceGroup.MEDIUMSLOW;
                break;
            case 5: returnGroup = ExperienceGroup.SLOW;
                break;
            case 6: returnGroup = ExperienceGroup.FLUCTUATING;
                break;
            default: returnGroup = null;
        }

        return returnGroup;
    }

    public static HashMap<Integer, LinkedHashMap<Integer, Move>> Learnsets = new HashMap<Integer, LinkedHashMap<Integer, Move>>(){
        {
            try{
                BufferedReader learnSets = new BufferedReader(new FileReader("Pokemon Beige/core/assets/Database/LearnSet.csv"));
                String line1;
                String line2;
                while((line1 = learnSets.readLine()) != null){
                    line2 = learnSets.readLine();
                    ArrayList<String> learns = new ArrayList<String>(Arrays.asList(line1.split(",")));
                    Integer id = Integer.valueOf(learns.get(0).substring(1, learns.get(0).indexOf(']')));
                    learns.remove(0);
                    final String learn[] = (String[])learns.toArray();
                    final ArrayList<Move> HatchSet = new ArrayList<Move>();
                    LinkedHashMap<Integer, Move> learnSet = new LinkedHashMap<Integer, Move>(){
                        {
                            for(int i = 0; i < learn.length; i += 2){
                                if(Integer.valueOf(learn[i]) == 1) HatchSet.add(movelist.get(MoveNumbers.get(learn[i + 1])));
                                else put(Integer.valueOf(learn[i]), movelist.get(MoveNumbers.get(learn[i + 1])));
                            }

                        }
                    };
                    put(id, learnSet);
                    HatchSets.put(id, HatchSet);

                    if(line2 != null){
                        EggMoveSets.put(id, line2.substring(line2.indexOf(',') + 1).split(","));
                        learnSets.readLine();
                    }
                }
            }catch(Exception e){

            }
        }
    };

    public static HashMap<Integer, ArrayList<Move>> HatchSets = new HashMap<Integer, ArrayList<Move>>();

    public static HashMap<Integer, String[]> EggMoveSets = new HashMap<Integer, String[]>();

    public static HashMap<Integer, Move> movelist = new HashMap<Integer, Move>(){
        {
               /*
               access "resource/Database/movelist.csv"
               each line pass through Move
                */
            try{
                BufferedReader move = new BufferedReader(new FileReader("Pokemon Beige/core/assets/Database/movelist.csv"));
                String line;
                while((line = move.readLine())!=null){
                    String moveInfo[] = line.split(",");
                    put(Integer.valueOf(moveInfo[0]), new Move(moveInfo));
                    MoveNumbers.put(line.split(",")[2], Integer.valueOf(moveInfo[0]));
                }
                move.close();
            }catch(Exception e){

            }
        }
    };

    public static HashMap<String, Integer> MoveNumbers = new HashMap<String, Integer>();

    public static HashMap<String, String[]> baseInfo = new HashMap<String, String[]>(){
        {
            try{
                BufferedReader move = new BufferedReader(new FileReader("Pokemon Beige/core/assets/Database/Pokemon.csv"));
                String line;
                move.readLine(); // First Line of Pokemon.csv
                while((line = move.readLine())!=null){
                    String data[] = line.split(",");
                    put(data[1], data);
                }
                move.close();
            }catch(Exception e){

            }
        }
    };

    public static float EFFECTIVE(TYPE Move, TYPE DefMain, TYPE DefSub){
        return chart(Move, DefMain) * chart(Move, DefSub);
    }

    public static float chart(TYPE Atk, TYPE Def){

        switch(Atk){
            case NORMAL:
                if(Def == TYPE.GHOST)
                    return 0f;
                else if (Def == TYPE.ROCK || Def == TYPE.ROCK)
                    return .5f;
                break;
            case FIGHTING:
                if(Def == TYPE.NORMAL || Def == TYPE.ROCK || Def == TYPE.STEEL || Def == TYPE.ICE || Def == TYPE.DARK){
                    return 2f;
                }
                else if(Def == TYPE.FLYING || Def == TYPE.POISON || Def == TYPE.BUG || Def == TYPE.PSYCHIC || Def == TYPE.FAIRY){
                    return .5f;
                }
                else if(Def == TYPE.GHOST)
                    return 0f;
                break;
            case FLYING:
                if(Def == TYPE.FIGHTING || Def == TYPE.BUG || Def == TYPE.GRASS)
                    return 2f;
                if(Def == TYPE.ROCK || Def == TYPE.STEEL || Def == TYPE.ELECTRIC)
                    return .5f;
                break;
            case POISON:
                if(Def == TYPE.POISON || Def == TYPE.GROUND || Def == TYPE.ROCK || Def == TYPE.GHOST)
                    return .5f;
                if(Def == TYPE.STEEL)
                    return 0f;
                if(Def == TYPE.GRASS || Def == TYPE.FAIRY)
                    return 2f;
                break;
            case GROUND:
                if(Def == TYPE.FLYING)
                    return 0f;
                if(Def == TYPE.POISON || Def == TYPE.ROCK || Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.ELECTRIC)
                    return 2f;
                if(Def == TYPE.BUG || Def == TYPE.GRASS)
                    return .5f;
                break;
            case ROCK:
                if(Def == TYPE.FIGHTING || Def == TYPE.GROUND || Def == TYPE.STEEL)
                    return .5f;
                if(Def == TYPE.FLYING || Def == TYPE.BUG || Def == TYPE.ICE || Def == TYPE.FIRE)
                    return 2f;
                break;
            case BUG:
                if(Def == TYPE.FIGHTING || Def == TYPE.FLYING || Def == TYPE.POISON || Def == TYPE.GHOST || Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.FAIRY)
                    return .5f;
                if(Def == TYPE.GRASS|| Def == TYPE.PSYCHIC || Def == TYPE.DARK)
                    return 2f;
                break;
            case GHOST:
                if(Def == TYPE.NORMAL)
                    return 0f;
                if(Def == TYPE.GHOST || Def == TYPE.PSYCHIC)
                    return 2f;
                if(Def == TYPE.DARK)
                    return .5f;
                break;
            case STEEL:
                if(Def == TYPE.ROCK || Def == TYPE.ICE || Def == TYPE.FAIRY)
                    return 2f;
                if(Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.WATER || Def == TYPE.ELECTRIC)
                    return .5f;
                break;
            case FIRE:
                if(Def == TYPE.BUG || Def == TYPE.STEEL || Def == TYPE.GRASS)
                    return 2f;
                if(Def == TYPE.ROCK || Def == TYPE.FIRE || Def == TYPE.WATER || Def == TYPE.DRAGON)
                    return .5f;
                break;
            case WATER:
                if(Def == TYPE.GROUND || Def == TYPE.ROCK || Def == TYPE.FIRE)
                    return 2f;
                if(Def == TYPE.WATER || Def == TYPE.GRASS || Def == TYPE.DRAGON)
                    return .5f;
                break;
            case GRASS:
                if(Def == TYPE.FLYING || Def == TYPE.POISON || Def == TYPE.BUG || Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.GRASS || Def == TYPE.DRAGON)
                    return .5f;
                if(Def == TYPE.GROUND || Def == TYPE.ROCK || Def == TYPE.WATER)
                    return 2f;
                break;
            case ELECTRIC:
                if(Def == TYPE.ROCK)
                    return 0f;
                if(Def == TYPE.FLYING || Def == TYPE.WATER)
                    return 2f;
                if(Def == TYPE.GRASS || Def == TYPE.ELECTRIC || Def == TYPE.DRAGON)
                    return .5f;
                break;
            case PSYCHIC:
                if(Def == TYPE.FIGHTING || Def == TYPE.POISON)
                    return 2f;
                if(Def == TYPE.STEEL || Def == TYPE.PSYCHIC)
                    return .5f;
                if(Def == TYPE.DARK)
                    return 0f;
                break;
            case ICE:
                if(Def == TYPE.FLYING || Def == TYPE.GROUND || Def == TYPE.GRASS || Def == TYPE.DRAGON)
                    return 2f;
                if(Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.WATER || Def == TYPE.ICE)
                    return .5f;
                break;
            case DRAGON:
                if(Def == TYPE.DRAGON)
                    return 2f;
                if(Def == TYPE.STEEL)
                    return .5f;
                if(Def == TYPE.FAIRY)
                    return 0f;
                break;
            case DARK:
                if(Def == TYPE.FIGHTING || Def == TYPE.DARK || Def == TYPE.FAIRY)
                    return .5f;
                if(Def == TYPE.GHOST || Def == TYPE.PSYCHIC)
                    return 2f;
                break;
            case HUDSON:
                return 2f;
            case FAIRY:
                if(Def == TYPE.FIGHTING || Def == TYPE.DRAGON || Def == TYPE.DARK)
                    return 2f;
                if(Def == TYPE.POISON || Def == TYPE.STEEL || Def == TYPE.FIRE)
                    return .5f;
                break;
            case SHADOW:
                if(Def == TYPE.SHADOW)
                    return .5f;
                return 2f;
        }
        if(Def == TYPE.HUDSON)
            return 0f;
        return 1f;

    }

    public static float nature(STATS statCAT, NATURE pokeNATURE){
        if(pokeNATURE == NATURE.HARDY || pokeNATURE == NATURE.DOCILE || pokeNATURE == NATURE.BASHFUL || pokeNATURE == NATURE.SERIOUS || pokeNATURE == NATURE.QUIRKY)
            return 1f;
        if(statCAT == STATS.ATTACK){
            // Increase first
            if(pokeNATURE == NATURE.LONELY || pokeNATURE == NATURE.BRAVE || pokeNATURE == NATURE.ADAMANT || pokeNATURE == NATURE.NAUGHTY){
                return 1.1f;
            }
            else if(pokeNATURE == NATURE.BOLD || pokeNATURE == NATURE.TIMID || pokeNATURE == NATURE.MODEST || pokeNATURE == NATURE.CALM){
                return .9f;
            }
        }
        else if(statCAT == STATS.DEFENSE){
            if(pokeNATURE == NATURE.BOLD || pokeNATURE == NATURE.RELAXED || pokeNATURE == NATURE.IMPISH || pokeNATURE == NATURE.LAX){
                return 1.1f;
            }
            else if(pokeNATURE == NATURE.LONELY || pokeNATURE == NATURE.HASTY || pokeNATURE == NATURE.MILD || pokeNATURE == NATURE.GENTLE){
                return .9f;
            }
        }
        else if(statCAT == STATS.SPEED){
            if(pokeNATURE == NATURE.TIMID || pokeNATURE == NATURE.HASTY || pokeNATURE == NATURE.JOLLY || pokeNATURE == NATURE.NAIVE){
                return 1.1f;
            }
            else if(pokeNATURE == NATURE.BRAVE || pokeNATURE == NATURE.RELAXED || pokeNATURE == NATURE.QUIET || pokeNATURE == NATURE.SASSY){
                return .9f;
            }
        }
        else if(statCAT == STATS.SP_ATTACK){
            if(pokeNATURE == NATURE.MODEST || pokeNATURE == NATURE.MILD || pokeNATURE == NATURE.QUIET || pokeNATURE == NATURE.RASH){
                return 1.1f;
            }
            else if(pokeNATURE == NATURE.ADAMANT || pokeNATURE == NATURE.IMPISH || pokeNATURE == NATURE.JOLLY || pokeNATURE == NATURE.CAREFUL){
                return .9f;
            }
        }
        else if(statCAT == STATS.SP_DEFENSE){
            if(pokeNATURE == NATURE.CALM || pokeNATURE == NATURE.GENTLE || pokeNATURE == NATURE.SASSY || pokeNATURE == NATURE.CAREFUL){
                return 1.1f;
            }
            else if(pokeNATURE == NATURE.NAUGHTY || pokeNATURE == NATURE.LAX || pokeNATURE == NATURE.NAIVE || pokeNATURE == NATURE.RASH){
                return .9f;
            }
        }

        return 1f;
    }

    public static NATURE randomNature(int num){
        switch(num){
            case 0: return NATURE.ADAMANT;
            case 1: return NATURE.BASHFUL;
            case 2: return NATURE.BOLD;
            case 3: return NATURE.BRAVE;
            case 4: return NATURE.CALM;
            case 5: return NATURE.CAREFUL;
            case 6: return NATURE.DOCILE;
            case 7: return NATURE.GENTLE;
            case 8: return NATURE.HARDY;
            case 9: return NATURE.HASTY;
            case 10: return NATURE.IMPISH;
            case 11: return NATURE.JOLLY;
            case 12: return NATURE.LAX;
            case 13: return NATURE.LONELY;
            case 14: return NATURE.MILD;
            case 15: return NATURE.MODEST;
            case 16: return NATURE.NAIVE;
            case 17: return NATURE.NAUGHTY;
            case 18: return NATURE.QUIET;
            case 19: return NATURE.QUIRKY;
            case 20: return NATURE.RASH;
            case 21: return NATURE.RELAXED;
            case 22: return NATURE.SASSY;
            case 23: return NATURE.SERIOUS;
            case 24: return NATURE.TIMID;
            default: return null;
        }
    }

    public static TYPE getType(String type){
        if(type.equals("Bug")) return TYPE.BUG;
        if(type.equals("Dark")) return TYPE.DARK;
        if(type.equals("Dragon")) return TYPE.DRAGON;
        if(type.equals("Electric")) return TYPE.ELECTRIC;
        if(type.equals("Fairy")) return TYPE.FAIRY;
        if(type.equals("Fighting")) return TYPE.FIGHTING;
        if(type.equals("Fire")) return TYPE.FIRE;
        if(type.equals("Flying")) return TYPE.FLYING;
        if(type.equals("Ghost")) return TYPE.GHOST;
        if(type.equals("Glitch")) return TYPE.GLITCH;
        if(type.equals("Grass")) return TYPE.GRASS;
        if(type.equals("Ground")) return TYPE.GROUND;
        if(type.equals("Hudson")) return TYPE.HUDSON;
        if(type.equals("Ics")) return TYPE.ICE;
        if(type.equals("None")) return TYPE.NONE;
        if(type.equals("Normal")) return TYPE.NORMAL;
        if(type.equals("Poison")) return TYPE.POISON;
        if(type.equals("Psychic")) return TYPE.PSYCHIC;
        if(type.equals("Qmark")) return TYPE.QMARK;
        if(type.equals("Rock")) return TYPE.ROCK;
        if(type.equals("Shadow")) return TYPE.SHADOW;
        if(type.equals("Steel")) return TYPE.STEEL;
        if(type.equals("Typeless")) return TYPE.TYPELESS;
        if(type.equals("Water")) return TYPE.WATER;
        return null;
    }
}
