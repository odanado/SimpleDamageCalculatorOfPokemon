/**
 * 
 */
package com.odanado.pokemon.lib;

/**
 * @author odan
 *
 */
public enum Items {
    /** 手ぶら...(0x1000) */
    NONE(0x1000),

    /** ちからのハチマキ  <br> 威力に1.1倍 (0x1199) */
    MUSCLE_BAND(0x1199),
    
    /** ものしりメガネ  <br> 威力に1.1倍 (0x1199) */
    WISE_GLASSES(0x1199),

    /** プレート系 <br> 威力に1.2倍 (0x1333) */
    PLATES(0x1333),
    
    /** ジュエル系 <br> 威力に1.3倍 (0x14CD) */
    JEWELS(0x14CD),
    
    /** こだわりハチマキ <br> 攻撃に1.5倍 (0x1800) */
    CHOICE_BAND(0x1800),
    
    /** こだわりメガネ <br> 攻撃に1.5倍 (0x1800) */
    CHOICE_SPECS(0x1800),
    
    /** ふといホネ <br> 攻撃に2.0倍 (0x2000)  */
    THICK_BONE(0x2000),

    /** たつじんのおび <br> ダメージに1.2倍 (0x1333) */
    EXPERT_BELT(0x1333),
    
    /** いのちのたま <br> ダメージに1.3倍 (0x14CC) */
    LIFE_ORB(0x14CC),
    

    /* -------------- 攻撃側の持物終わり ------------ */
    /* -------------- 防御側の持物始まり ------------ */
    
    /** しんかのきせき <br> 防御に1.5倍 (0x1800) */
    EVOLUTION_STONE(0x1800),
    
    /** とつげきチョッキ <br> 防御に1.5倍 (0x1800) */
    ASSAULT_VEST(0x1800),
    
    /** 半減の実 <br> ダメージに0.5倍 (0x800) */
    BERRIES(0x800);
    
    private int modVal;
    private Items(int n) {
        this.modVal = n;
    }
    public int getModVal() {
        return this.modVal;
    }
}
