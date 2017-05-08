package classes;

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
    private int statModifiers[] = {0,0,0,0,0}; // Attack, Defense, Sp Attack, Sp Defense, Speed
    private Utility.ExperienceGroup XPMODE;
    private Utility.TYPE MAIN;//
    private Utility.TYPE SUB;//
    private Utility.NATURE Nature;//
    private int natureBuff[];
    private boolean fainted;

    public Pokemon(){}

    public Pokemon(String ID, int lv){
//        System.out.println(ID);
        String info[] = Utility.baseInfo.get(ID);
//        for(String str : info){
//            System.out.print(str + " : ");
//        }
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
        if(!info[43].equals("-1"))
        EvolveCondition = Utility.baseInfo.get(info[43])[42];
        else{
            EvolveCondition = "None";
        }
        LearnSet = Utility.Learnsets.get(Integer.valueOf(id));
        HatchSet = Utility.HatchSets.get(Integer.valueOf(id));
        System.out.print(Utility.Learnsets.get(Integer.valueOf(id)));
        for(Integer i = level; KnownMoves.size() < 4 && i > 1; i--){
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
        fainted = false;
        natureBuff = getNatureBuffs();
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
            else{
                XP += xp;
                TNLxp = Utility.TNL(level, XPMODE) - XP;
            }
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

    public int getStat(Utility.STATS stat){
        return pokeSTAT.get(stat).getStatValue();
    }

    public Integer getLevel(){
        return level;
    }

    public Utility.TYPE getMAIN(){
        return MAIN;
    }
    public Utility.TYPE getSUB(){
        return SUB;
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

    public String attacked(Move move, Pokemon oppPoke){
        Utility.TYPE type = move.getType();
        Utility.CATEGORY category = move.getCategory();
        int power = move.getPower();
        Integer level = oppPoke.getLevel();
        int oppStat, selfStat;
        if(category == Utility.CATEGORY.PHYSICAL){
            oppStat = oppPoke.getStat(Utility.STATS.ATTACK) * statModifiers[0];
            selfStat = getStat(Utility.STATS.DEFENSE) * statModifiers[1];

        }
        else {
            oppStat = oppPoke.getStat(Utility.STATS.SP_ATTACK) * statModifiers[2];
            selfStat = getStat(Utility.STATS.SP_DEFENSE) * statModifiers[3];
        }
        int weather = 1;
        int crit = 1;
        double range = 0;
        while(!(range <= 1 && range >= .85)){
            range = (((Math.random() * 16) + 85) / 100.0);
        }
        double STAB = 1;
        if(type == oppPoke.getMAIN() || type == oppPoke.getSUB()){
            STAB = 1.5;
        }
        double typing = (double)(Utility.chart(type, getMAIN()) * Utility.chart(type, getSUB()));
        long modifier = (long)(weather * crit * range * STAB * typing);
        decreaseHP((((((2 * level / 5) + 2) * power * oppStat / selfStat) / 50) + 2) * modifier);
        if(typing == 1){
            return "It was effective";
        }
        else if(typing == 0){
            return "It had no effect";
        }
        else if(typing > 1){
            return "It was super effective";
        }
        else return "It was not very effective";
    }

    public boolean getFainted(){
        return fainted;
    }

    public int[] getNatureBuffs(){
        int[] natureBuffs = new int[6];
        Utility.STATS stat;
        float currentStat;
        for(int i = 0; i < 6; i++){
            switch(i){
                case 0: stat = Utility.STATS.HP;
                    break;
                case 1: stat = Utility.STATS.ATTACK;
                    break;
                case 2: stat = Utility.STATS.DEFENSE;
                    break;
                case 3: stat = Utility.STATS.SP_ATTACK;
                    break;
                case 4: stat = Utility.STATS.SP_DEFENSE;
                    break;
                default: stat = Utility.STATS.SPEED;
            }

            currentStat = Utility.nature(stat,Nature);
            if(currentStat == 1.1) natureBuffs[i] = 1;
            else if(currentStat == 0) natureBuffs[i] = 0;
            else natureBuffs[i] = -1;
        }

        return natureBuffs;
    }

    public ArrayList<Move> getKnownMoves(){
        return KnownMoves;
    }

    private void splitUP(){
        String binary = "";
        binary = Integer.toBinaryString(BIN1);


        binary = Integer.toBinaryString(BIN2);

    }

}
