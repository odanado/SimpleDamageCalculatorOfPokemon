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
 * ポケモンの個体値を保持します
 * 
 * @author odan
 * 
 * @version 1.0
 * 
 */

public class IndividualValues {
	protected int HP;
	protected int Attack;
	protected int Defense;
	protected int SpAttack;
	protected int SpDefense;
	protected int Speed;
	
	/**
	 * コンストラクタ</br>
	 * 引数をメンバ変数に格納します
	 * @param HP
	 * @param Attack
	 * @param Defense
	 * @param SpAttack
	 * @param SpDefense
	 * @param Speed
	 */
	public IndividualValues(int HP, int Attack, int Defense, int SpAttack,int SpDefense, int Speed) {
		this.HP = HP;
		this.Attack = Attack;
		this.Defense = Defense;
		this.SpAttack = SpAttack;
		this.SpDefense = SpDefense;
		this.Speed = Speed;
	}
	
	/**
	 * コンストラクタ</br>
	 * 引数をメンバ変数に格納します
	 * @param HP
	 * @param Attack
	 * @param Defense
	 * @param SpAttack
	 * @param SpDefense
	 * @param Speed
	 */
	public IndividualValues(String HP, String Attack, String Defense, String SpAttack,String SpDefense, String Speed) {
		this(Integer.parseInt(HP), Integer.parseInt(Attack), Integer.parseInt(Defense), 
				Integer.parseInt(SpAttack), Integer.parseInt(SpDefense), Integer.parseInt(Speed));
	}

	/**
	 * hPを取得します
	 * @return hP
	 */
	public int getHP() {
		return this.HP;
	}

	/**
	 * hPを設定します
	 * @param hP
	 */
	public void setHP(int hP) {
		this.HP = hP;
	}

	/**
	 * attackを取得します
	 * @return attack
	 */
	public int getAttack() {
		return this.Attack;
	}

	/**
	 * attackを設定します
	 * @param attack
	 */
	public void setAttack(int attack) {
		this.Attack = attack;
	}

	/**
	 * defenseを取得します
	 * @return defense
	 */
	public int getDefense() {
		return this.Defense;
	}

	/**
	 * defenseを設定します
	 * @param defense
	 */
	public void setDefense(int defense) {
		this.Defense = defense;
	}

	/**
	 * spAttackを取得します
	 * @return spAttack
	 */
	public int getSpAttack() {
		return this.SpAttack;
	}

	/**
	 * spAttackを設定します
	 * @param spAttack
	 */
	public void setSpAttack(int spAttack) {
		this.SpAttack = spAttack;
	}

	/**
	 * spDefenseを取得します
	 * @return spDefense
	 */
	public int getSpDefense() {
		return this.SpDefense;
	}

	/**
	 * spDefenseを設定します
	 * @param spDefense
	 */
	public void setSpDefense(int spDefense) {
		this.SpDefense = spDefense;
	}

	/**
	 * speedを取得します
	 * @return speed
	 */
	public int getSpeed() {
		return this.Speed;
	}

	/**
	 * speedを設定します
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.Speed = speed;
	}

}
