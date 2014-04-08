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
    public int[] getDamage() {
        return this.damage;
    }
    private int attackPower;
    private int defensePower;
    private double attackBonus;
    private double typeMatchUp;
    private int attackLevel;
    
    private int[] damage;
    
    public DamageCalculator(int movePower, int attackPower, int defensePower, double attackBonus, double typeMatchUp, int attackLevel) {
        super();
        this.movePower = movePower;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.attackBonus = attackBonus;
        this.typeMatchUp = typeMatchUp;
        this.attackLevel = attackLevel;
        
        this.damage = new int[32];
        
        calcDamage();
    }
    
    public void calcDamage() {
        /*
         * ダメージ = ( ( ( ( ( ( 攻撃側のレベル * 2 ) / 5 + 2 ) * 威力 * 攻撃力 ) / 防御力 ) / 50 + 2 ) * 
         * マルチ対象※ * 天候※ * 急所 * 乱数幅(16分率) * タイプ一致※ * タイプ相性 * 火傷 * ダメージ補正※ )
         */
        
        int baseDamage;
        baseDamage = (attackLevel * 2) / 5 + 2;
        
        baseDamage *= movePower;
        
        baseDamage *= attackPower;
        
        baseDamage /= defensePower;
        
        baseDamage = baseDamage / 50 + 2;
        
        for(int i=0; i<16; i++) {
            damage[i + 16] = baseDamage * 3 / 2;
            damage[i + 16] = (damage[i + 16] * (85 + i)) / 100;
            damage[i] = (baseDamage * (85 + i)) / 100;        
        }
        
        for(int i=0; i<32; i++) {
            damage[i] = calcRoundHalfDown(attackBonus * damage[i]);
            damage[i] *= typeMatchUp;
        }
        
        
    }    
    private int calcRoundHalfDown(double a) {
        java.math.BigDecimal value = new java.math.BigDecimal(a);
        
        value = value.setScale(1, java.math.RoundingMode.HALF_DOWN);
        
        return value.intValue();
    }
}
