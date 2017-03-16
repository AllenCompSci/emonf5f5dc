package com.center.pokemon.desktop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data {
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private static ArrayList<ArrayList<String>> pokemon = new ArrayList<ArrayList<String>>();
    private static ArrayList<ArrayList<String>> pokemonTMs = new ArrayList<ArrayList<String>>();
    private static ArrayList<ArrayList<String>> pokedex = new ArrayList<ArrayList<String>>();
    private static ArrayList<ArrayList<String>> movelist = new ArrayList<ArrayList<String>>();
    private static ArrayList<ArrayList<String>> LearnSet = new ArrayList<ArrayList<String>>();

    public static void data() throws Exception {
        pokemon = dataArrayListConstructor("Pokemon Beige/core/assets/Database/Pokemon.csv");
        pokemonTMs = dataArrayListConstructor("Pokemon Beige/core/assets/Database/Pokemon TMs.csv");
        pokedex = dataArrayListConstructor("Pokemon Beige/core/assets/Database/Pokedex.csv");
        movelist = dataArrayListConstructor("Pokemon Beige/core/assets/Database/movelist.csv");
        LearnSet = dataArrayListConstructor("Pokemon Beige/core/assets/Database/LearnSet.csv");
    }

    public static ArrayList<String> getPokemon(int id){
        return pokemon.get(id);
    }

    public static ArrayList<String> getTM(int TMNum){
        return pokemonTMs.get(TMNum);
    }

    public static ArrayList<String> getMove(int moveNum){
        return movelist.get(moveNum);
    }

    public static ArrayList<String> getPokedexEntry(int id){
        return pokedex.get(id);
    }

    public static ArrayList<String> getLearnset(int id){
        return LearnSet.get(id);
    }

    public static ArrayList<ArrayList<String>> dataArrayListConstructor(String csvFile) throws Exception{
        ArrayList<ArrayList<String>> currentCSV = new ArrayList<ArrayList<String>>();
        Scanner scanner = new Scanner(new File(csvFile));
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            ArrayList<String> lineArrayList = new ArrayList<String>(line);
            currentCSV.add(lineArrayList);
        }
        scanner.close();
        return currentCSV;
    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<String>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }
}
