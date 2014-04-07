/* 参考
 * http://d.hatena.ne.jp/Alain/20140403
 * 
 */
package com.odanado.pokemon.lib;

/**
 * @author odan
 *
 */
public class DamageCalculator {

    private int movePower;
    public int[] getDamageList() {
        return this.damageList;
    }
    private int attackPower;
    private int defensePower;
    private double attackBonus;
    private double typeMatchUp;
    private int attackLevel;
    
    private int[] damageList;
    
    public DamageCalculator(int movePower, int attackPower, int defensePower, double attackBonus, double typeMatchUp, int attackLevel) {
        super();
        this.movePower = movePower;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.attackBonus = attackBonus;
        this.typeMatchUp = typeMatchUp;
        this.attackLevel = attackLevel;
        
        this.damageList = new int[32];
        
        calcDamage();
    }
    
    public void calcDamage() {
        /*
         * ダメージ = ( ( ( ( ( ( 攻撃側のレベル * 2 ) / 5 + 2 ) * 威力 * 攻撃力 ) / 防御力 ) / 50 + 2 ) * 
         * マルチ対象※ * 天候※ * 急所 * 乱数幅(16分率) * タイプ一致※ * タイプ相性 * 火傷 * ダメージ補正※ )
         */
        
        int damage;
        damage = (attackLevel * 2) / 5 + 2;
        
        damage *= movePower;
        
        damage *= attackPower;
        
        damage /= defensePower;
        
        damage = damage / 50 + 2;
        
        for(int i=0; i<16; i++) {
            damageList[i + 16] = damage * 3 / 2;
            damageList[i + 16] = (damageList[i + 16] * (85 + i)) / 100;
            damageList[i] = (damage * (85 + i)) / 100;        
        }
        
        for(int i=0; i<32; i++) {
            damageList[i] = calcRoundHalfDown(attackBonus * damageList[i]);
            damageList[i] *= typeMatchUp;
        }
        
        
    }    
    private int calcRoundHalfDown(double a) {
        java.math.BigDecimal value = new java.math.BigDecimal(a);
        
        value = value.setScale(1, java.math.RoundingMode.HALF_DOWN);
        
        return value.intValue();
    }
}
