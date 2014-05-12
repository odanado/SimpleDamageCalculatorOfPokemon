/*
 * CalculatePokemonStats
 * 
 * Copyright (c) 2013 odan(@poke_odan)
 * 
 * This software is released under the MIT License.
 * 
 */
package com.odanado.pokemon.lib;

/**
 * ポケモンの性格関連のクラスです
 * 
 * @author odan
 *
 */
public class Natures {
    
    /**
     * [プラスの性格の位置][マイナスの性格の位置] で日本語の性格を生成します
     */
    public static final String[][] NATURE_MATRIX_JP = {
	{"N/A","N/A","N/A","N/A","N/A","N/A"},
	{"N/A","N/A","さみしがり","いじっぱり","やんちゃ","ゆうかん"},
	{"N/A","ずぶとい","N/A","わんぱく","のうてんき","のんき"},
	{"N/A","ひかえめ","おっとり","N/A","うっかりや","れいせい"},
	{"N/A","おだやか","おとなしい","しんちょう","N/A","なまいき"},
	{"N/A","おくびょう","せっかち","ようき","むじゃき","N/A"}
	};
	
    /**
     * 性格の倍率を保持します
     */
    public static final double[][] NATURE_MATRIX_DOUBLE = {
	{1,1,1,1,1},
	{1.1,0.9,1,1,1},
	{1.1,1,1,1,0.9},
	{1.1,1,0.9,1,1},
	{1.1,1,1,0.9,1},
	{0.9,1.1,1,1,1},
	{1,1,1,1,1},
	{1,1.1,1,1,0.9},
	{1,1.1,0.9,1,1},
	{1,1.1,1,0.9,1},
	{0.9,1,1,1,1.1},
	{1,0.9,1,1,1.1},
	{1,1,1,1,1},
	{1,1,0.9,1,1.1},
	{1,1,1,0.9,1.1},
	{0.9,1,1.1,1,1},
	{1,0.9,1.1,1,1},
	{1,1,1.1,1,0.9},
	{1,1,1,1,1},
	{1,1,1.1,0.9,1},
	{0.9,1,1,1.1,1},
	{1,0.9,1,1.1,1},
	{1,1,1,1.1,0.9},
	{1,1,0.9,1.1,1},
	{1,1,1,1,1}
	};
    public static final int HARDY = 0;
    public static final int LONELY = 1;
    public static final int BRAVE = 2;
    public static final int ADAMANT = 3;
    public static final int NAUGHTY = 4;
    public static final int BOLD = 5;
    public static final int DOCILE = 6;
    public static final int RELAXED = 7;
    public static final int IMPISH = 8;
    public static final int LAX = 9;
    public static final int TIMID = 10;
    public static final int HASTY = 11;
    public static final int SERIOUS = 12;
    public static final int JOLLY = 13;
    public static final int NAIVE = 14;
    public static final int MODEST = 15;
    public static final int MILD = 16;
    public static final int QUIET = 17;
    public static final int BASHFUL = 18;
    public static final int RASH = 19;
    public static final int CALM = 20;
    public static final int GENTLE = 21;
    public static final int SASSY = 22;
    public static final int CAREFUL = 23;
    public static final int QUIRKY = 24;
    
    /**
     * 
     * @param natureName 日本語の性格
     * @return それに対応する数値
     */    
    public static int toInt(String natureName) {
    	if(natureName == "がんばりや") return HARDY;
    	if(natureName == "さみしがり") return LONELY;
    	if(natureName == "ゆうかん") return BRAVE;
    	if(natureName == "いじっぱり") return ADAMANT;
    	if(natureName == "やんちゃ") return NAUGHTY;
    	if(natureName == "ずぶとい") return BOLD;
    	if(natureName == "すなお") return DOCILE;
    	if(natureName == "のんき") return RELAXED;
    	if(natureName == "わんぱく") return IMPISH;
    	if(natureName == "のうてんき") return LAX;
    	if(natureName == "おくびょう") return TIMID;
    	if(natureName == "せっかち") return HASTY;
    	if(natureName == "まじめ") return SERIOUS;
    	if(natureName == "ようき") return JOLLY;
    	if(natureName == "むじゃき") return NAIVE;
    	if(natureName == "ひかえめ") return MODEST;
    	if(natureName == "おっとり") return MILD;
    	if(natureName == "れいせい") return QUIET;
    	if(natureName == "てれや") return BASHFUL;
    	if(natureName == "うっかりや") return RASH;
    	if(natureName == "おだやか") return CALM;
    	if(natureName == "おとなしい") return GENTLE;
    	if(natureName == "なまいき") return SASSY;
    	if(natureName == "しんちょう") return CAREFUL;
    	if(natureName == "きまぐれ") return QUIRKY;
    	if(natureName == "N/A") return HARDY;
    	return -1;
    }
    
}
