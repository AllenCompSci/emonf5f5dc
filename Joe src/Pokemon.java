package com.center.pokemon.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;

public class Pokemon {
    private String id;
    private String Poke;
    private String NickName;
    private long maxHealth;//
    private long currentHealth;//
    private int BIN1, BIN2;
    // private BufferedImage img;
    private Integer level;
    private Long XP;
    private Long TNLxp;
    private Integer EvolveLevel;
    private String EvolveCondition;
    private ArrayList<Move> KnownMoves = new ArrayList<Move>();//
    private LinkedHashMap<Integer, Move> LearnSet = new LinkedHashMap<Integer, Move>();
    private ArrayList<Move> HatchSet = new ArrayList<Move>();
    private HashMap<Utility.STATS, Base> pokeSTAT = new HashMap<Utility.STATS, Base>();//
    private Utility.ExperienceGroup XPMODE;
    private Utility.TYPE MAIN;//
    private Utility.TYPE SUB;//
    private Utility.NATURE Nature;//

    public Pokemon(){}

    public Pokemon(String ID, int lv){
        String info[] = Utility.baseInfo.get(id);
        id = ID;
        level = lv;
        Poke = info[2];
        NickName = info[2];
        XPMODE = Utility.expGroup(info[18]);
        XP = Utility.TNL(level - 1, XPMODE);
        TNLxp = Utility.TNL(level, XPMODE) - XP;
        if(Integer.valueOf(info[43]) != -1) {
            if(!Utility.baseInfo.get(info[43])[41].equals("Special") && !Utility.baseInfo.get(info[43])[41].equals("MEGA")){
                EvolveLevel = Integer.valueOf(Utility.baseInfo.get(info[43])[41]);
            }
            else EvolveLevel = 102;
        }
        else EvolveLevel = 101;
        EvolveCondition = Utility.baseInfo.get(info[43])[42];
        LearnSet = Utility.Learnsets.get(Integer.valueOf(id));
        HatchSet = Utility.HatchSets.get(Integer.valueOf(id));
        for(int i = level; KnownMoves.size() < 4 && i > 1; i--){
            if(LearnSet.containsKey(i)) KnownMoves.add(LearnSet.get(i));
        }
        for(int i = HatchSet.size(); KnownMoves.size() < 4 && i >= 0; i--){
            KnownMoves.add(HatchSet.get(i));
        }
        Nature = Utility.randomNature((int)(Math.random() * 25));
        pokeSTAT.put(Utility.STATS.HP, new Base(Utility.STATS.HP, Integer.valueOf(info[10]),level, Nature));
        pokeSTAT.put(Utility.STATS.ATTACK, new Base(Utility.STATS.ATTACK, Integer.valueOf(info[11]),level, Nature));
        pokeSTAT.put(Utility.STATS.DEFENSE, new Base(Utility.STATS.DEFENSE, Integer.valueOf(info[12]),level, Nature));
        pokeSTAT.put(Utility.STATS.SP_ATTACK, new Base(Utility.STATS.SP_ATTACK, Integer.valueOf(info[13]),level, Nature));
        pokeSTAT.put(Utility.STATS.SP_DEFENSE, new Base(Utility.STATS.SP_DEFENSE, Integer.valueOf(info[14]),level, Nature));
        pokeSTAT.put(Utility.STATS.SPEED, new Base(Utility.STATS.SPEED, Integer.valueOf(info[15]),level, Nature));
        pokeSTAT.put(Utility.STATS.TOTAL, new Base());
        pokeSTAT.get(Utility.STATS.TOTAL).increaseBase_Stat(Integer.valueOf(info[10]) + Integer.valueOf(info[11]) + Integer.valueOf(info[12]) + Integer.valueOf(info[13]) + Integer.valueOf(info[14]) + Integer.valueOf(info[15]));
        pokeSTAT.get(Utility.STATS.TOTAL).increaseIV(pokeSTAT.get(Utility.STATS.HP).getIV() + pokeSTAT.get(Utility.STATS.ATTACK).getIV() + pokeSTAT.get(Utility.STATS.DEFENSE).getIV() + pokeSTAT.get(Utility.STATS.SP_ATTACK).getIV() + pokeSTAT.get(Utility.STATS.SP_DEFENSE).getIV() + pokeSTAT.get(Utility.STATS.SPEED).getIV());
        maxHealth = (long)pokeSTAT.get(Utility.STATS.HP).getStatValue();
        currentHealth = (long)pokeSTAT.get(Utility.STATS.HP).getStatValue();
        MAIN = Utility.getType(info[7]);
        SUB = Utility.getType(info[8]);
    }


    public int randomdistrubte(int x, int y, int r){
        int ranRange = 0;

        return ranRange;
    }

    public void gainXP(Long xp){
        if(level != 100){
            if(xp.compareTo(TNLxp) >= 0){
                XP += TNLxp;
                LevelUp(xp - TNLxp);
            }
            else XP += xp;
        }
    }

    private void LevelUp(Long xp){
        level = level + 1;
        TNLxp = Utility.TNL(level, XPMODE) - XP;
        setStats();
        // increase all elements of pokeSTAT with *.setLVL(level)
        gainXP(xp);
    }

    private void setStats(){
        pokeSTAT.get(Utility.STATS.HP).setLVL(level);
        pokeSTAT.get(Utility.STATS.ATTACK).setLVL(level);
        pokeSTAT.get(Utility.STATS.DEFENSE).setLVL(level);
        pokeSTAT.get(Utility.STATS.SP_ATTACK).setLVL(level);
        pokeSTAT.get(Utility.STATS.SP_DEFENSE).setLVL(level);
        pokeSTAT.get(Utility.STATS.SPEED).setLVL(level);
    }

    public void gainEV(Utility.STATS stat, int increase){
        if(pokeSTAT.get(Utility.STATS.TOTAL).getEV() + increase > 510){
            increase = 510 - pokeSTAT.get(Utility.STATS.TOTAL).getEV();
        }
        if(stat != Utility.STATS.TOTAL && pokeSTAT.get(stat).getEV() + increase > 252){
            increase = 252 - pokeSTAT.get(stat).getEV();
        }
        pokeSTAT.get(stat).increaseEV(increase);
        if(stat != Utility.STATS.TOTAL) gainEV(Utility.STATS.TOTAL, increase);
        if(stat == Utility.STATS.HP){
            currentHealth = pokeSTAT.get(stat).getStatValue() - (maxHealth - currentHealth);
            maxHealth = pokeSTAT.get(stat).getStatValue();
        }
    }

    public String displayXPNEED(){
        return TNLxp.toString();
    }

    public long getCurrentHealth(){
        return currentHealth;
    }

    public long getMaxHealth(){
        return maxHealth;
    }

    public void decreaseHP(long lostHP){
        if(currentHealth - lostHP < 0){
            lostHP = currentHealth;
        }
        currentHealth = currentHealth - lostHP;
    }

    public void increaseHP(long gainedHP){
        if(currentHealth + gainedHP > maxHealth){
            gainedHP = maxHealth - currentHealth;
        }
        currentHealth += gainedHP;
    }

    private void splitUP(){
        String binary = "";
        binary = Integer.toBinaryString(BIN1);


        binary = Integer.toBinaryString(BIN2);

    }

}
