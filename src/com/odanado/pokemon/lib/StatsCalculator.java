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
 * ポケモンのステータスを計算します
 * 
 * @author odan
 * 
 * @version 1.0
 * 
 */

public class StatsCalculator {
	protected BaseStats baseStats;
	protected EffortValues effortValues;
	protected IndividualValues individualValues;
	
	protected int HP;
	protected int Attack;
	protected int Defense;
	protected int SpAttack;
	protected int SpDefense;
	protected int Speed;
	protected int level;
	
	protected int nature;
	
	protected double[] naturesBonus ;

	/**
	 * コンストラクタ
	 * 引数から能力値を計算しメンバ変数に格納します。
	 * 
	 * @param BaseStat 種族値
	 * @param EffortValue 努力値
	 * @param IndividualValue 個体値
	 * @param level レベル
	 * @param naturesBonus  性格補正の倍率
	 *  
	 * */
	
	public StatsCalculator(BaseStats baseStats, EffortValues effortValues, IndividualValues individualValues, int level, double naturesBonus []) {
	    this.baseStats = baseStats;
	    this.effortValues = effortValues;
	    this.individualValues = individualValues;
	    
	    this.level = level;
	    this.naturesBonus  = naturesBonus ;

	    this.HP = calcStats(baseStats.HP, effortValues.HP, individualValues.HP);
	    this.Attack = calcStats(baseStats.Attack, effortValues.Attack, individualValues.Attack,naturesBonus , 0);
	    this.Defense = calcStats(baseStats.Defense, effortValues.Defense, individualValues.Defense, naturesBonus , 1);
	    this.SpAttack = calcStats(baseStats.SpAttack, effortValues.SpAttack, individualValues.SpAttack, naturesBonus , 2);
	    this.SpDefense = calcStats(baseStats.SpDefense, effortValues.SpDefense, individualValues.SpDefense, naturesBonus , 3);
	    this.Speed = calcStats(baseStats.Speed, effortValues.Speed, individualValues.Speed, naturesBonus , 4);
	}

	/**
	 * コンストラクタ
	 * 引数から能力値を計算しメンバ変数に格納します。
	 * 
	 * @param BaseStat 種族値
	 * @param EffortValue 努力値
	 * @param IndividualValue 個体値
	 * @param level レベル
	 * @param nature 性格
	 * 
	 * */
	public StatsCalculator(BaseStats baseStats, EffortValues effortValues, IndividualValues individualValues, int level, int nature) {
		
		this(baseStats,effortValues,individualValues,level,Natures.NATURE_MATRIX_DOUBLE[nature]);
		this.nature = nature;
		
	}
	/**
	 * コンストラクタ
	 * 引数から能力値を計算しメンバ変数に格納します。
	 * 
	 * @param BaseStat 種族値
	 * @param EffortValue 努力値
	 * @param IndividualValue 個体値
	 * @param nature 性格
	 * 
	 * */
	public StatsCalculator(BaseStats baseStats, EffortValues effortValues, IndividualValues individualValues, int nature) {
		this(baseStats,effortValues,individualValues,50,nature);
	}
	
	/**
	 * コンストラクタ
	 * 引数から能力値を計算しメンバ変数に格納します。
	 * 
	 * @param BaseStat 種族値
	 * @param EffortValue 努力値
	 * @param IndividualValue 個体値
	 * @param natureName 性格(日本語)
	 */
	
	public StatsCalculator(BaseStats baseStats, EffortValues effortValues, IndividualValues individualValues, String natureName) {
		this(baseStats,effortValues,individualValues,50,Natures.toInt(natureName));
	}
	
	/**
     * levelを取得します
     * @return level
     */
    public int getLevel() {
        return this.level;
    }

    /**
	 * コンストラクタ</br>
	 * 引数から能力値を計算しメンバ変数に格納します。
	 * @param baseStats
	 * @param effortValues
	 * @param individualValues
	 * @param level
	 * @param natureName
	 */
	public StatsCalculator(BaseStats baseStats, EffortValues effortValues, IndividualValues individualValues, String level, String natureName) {
		this(baseStats,effortValues,individualValues,Integer.parseInt(level),Natures.toInt(natureName));
	}
	
	/**
	 * コンストラクタ</br>
	 * 引数から能力値を計算しメンバ変数に格納します。
	 * @param baseStats
	 * @param effortValues
	 * @param individualValues
	 * @param level
	 * @param naturesBonus
	 */
	public StatsCalculator(BaseStats baseStats, EffortValues effortValues, IndividualValues individualValues, String level, double[] naturesBonus) {
		this(baseStats,effortValues,individualValues,Integer.parseInt(level), naturesBonus);
	}
	

	/**
	 * 
	 * 各パラメータと性格から能力値を計算します。
	 * 
	 * @param baseStat 種族値
	 * @param effortValue 努力値
	 * @param individualValue 個体値
	 * @param natureColumn 性格の種類
	 * @param natureRow 計算するステータス
	 * 
	 * */
	protected int calcStats(int baseStat,int effortValue, int individualValue, double[] naturesBonus , int natureRow) {
		int result = 0;
		
		result = ((baseStat * 2 + individualValue + effortValue / 4) * this.level / 100) + 5;
		
		result *= naturesBonus [natureRow];
		
		return result;
	}

	/**
	 * 
	 * 各パラメータからHPを計算します。
	 * 
	 * @param baseStat 種族値
	 * @param effortValue 努力値
	 * @param individualValue 個体値
	 * 
	 * */
	protected int calcStats(int baseStat,int effortValue, int individualValue) {
		int result = 0;
		
		result = ((baseStat * 2 + individualValue + effortValue / 4) * this.level / 100) + 5;
		
		result += 5 + this.level;
		
		return result;
	}

	/**
	 * hPを取得します
	 * @return hP
	 */
	public int getHP() {
		return this.HP;
	}

	/**
	 * attackを取得します
	 * @return attack
	 */
	public int getAttack() {
		return this.Attack;
	}

	/**
	 * defenseを取得します
	 * @return defense
	 */
	public int getDefense() {
		return this.Defense;
	}

	/**
	 * spAttackを取得します
	 * @return spAttack
	 */
	public int getSpAttack() {
		return this.SpAttack;
	}

	/**
	 * spDefenseを取得します
	 * @return spDefense
	 */
	public int getSpDefense() {
		return this.SpDefense;
	}

	/**
	 * speedを取得します
	 * @return speed
	 */
	public int getSpeed() {
		return this.Speed;
	}

	



}
