/**
 * 
 */
package com.odanado.pokemon.lib;

/**
 * @author odan
 *
 */
public enum Abilities {
    NONE(0x1000),
    
    /** てつのこぶし <br> 威力に1.2倍(0x1333) */
    IRON_FIST(0x1333),
    
    /** すてみ <br> 威力に1.2倍(0x1333) */
    RECKLESS(0x1333),
    
    /** アナライズ <br> 威力に1.3倍(0x14CD) */
    ANALYTIC(0x14CD),
    
    /** すなのちから <br> 威力に1.3倍(0x14CD) */
    SAND_FORCE(0x14CD),

    /** ちからずく <br> 威力に1.3倍(0x14CD) */
    SHEER_FORCE(0x14CD),

    /** スカイスキン <br> 威力に1.3倍(0x14CD) */
    AERILATE(0x14CD),
    
    /** フェアリースキン <br> 威力に1.3倍(0x14CD) */
    PIXILATE(0x14CD),
    
    /** フリーズスキン <br> 威力に1.3倍(0x14CD) */
    REFRIGERATE(0x14CD),
    
    /** かたいつめ <br> 威力に1.33倍?(0x1547) */
    TOUGH_CLAWS(0x1547),
    
    /** テクニシャン <br> 威力に1.5倍 (0x1800) */
    TECHNICIAN(0x1800),

    /** メガランチャー <br> 威力に1.5倍 (0x1800) */
    MEGA_LAUNCHER(0x1800),

    /** がんじょうあご <br> 威力に1.5倍 (0x1800) */
    STRONG_JAW(0x1800),
    
    /** ねつぼうそう <br> 威力に1.5倍 (0x1800) */
    FLARE_BOOST(0x1800),

    /** どくぼうそう <br> 威力に1.5倍 (0x1800) */
    TOXIC_BOOST(0x1800),

    /** もうか <br> 攻撃に1.5倍 (0x1800) */
    BLAZE(0x1800),

    /** こんじょう <br> 攻撃に1.5倍 (0x1800) */
    GUTS(0x1800),

    /** プラス <br> 攻撃に1.5倍 (0x1800) */
    PLUS(0x1800),
    
    /** マイナス <br> 攻撃に1.5倍 (0x1800) */
    MINUS(0x1800),
    
    /** サンパワー <br> 攻撃に1.5倍 (0x1800) */
    SOLAR_POWER(0x1800),
    
    /** フラワーギフト <br> 攻撃に1.5倍 (0x1800) <br> 防御に1.5倍 (0x1800) */
    FLOWER_GIFT(0x1800),
    
    /** ちからもち <br> 攻撃に2倍 (0x2000) */
    HUGE_POWER(0x2000),
    
    /** ヨガパワー <br> 攻撃に2倍 (0x2000) */
    PURE_POWER(0x2000),
    
    /** スナイパー <br> ダメージ(急所)に1.5倍 (0x1800) */
    SNIPER(0x1800),

    /** てきおうりょく <br> ダメージに2倍 (0x2000) */
    ADAPTABILITY(0x2000),
    
    /** いろめがね <br> ダメージに2倍 (0x2000) */
    TINTED_LENS(0x2000),
    
    /** ダークオーラ <br> 威力に1.33倍? (0x1555) */
    DARK_AURA(0x1555),
    
    /** フェアリーオーラ <br> 威力に1.33倍 (0x1555) */
    FAIRY_AURA(0x1555),
    
    /** オーラブレイク <br> 威力に0.66倍 (0xAAA) */
    AURA_BREAK(0xAAA),

    /** はりきり <br> 攻撃に直接1.5倍 (0x1800) */
    HUSTLE(0x1800),
    
    /** おやこあい <br> ダメージに直接0.5倍 (0x800) */
    PARENTAL_BOND(0x1800),
    
    /* -------------- 攻撃側の特性終わり ------------ */
    /* -------------- 防御側の特性始まり ------------ */
    

    /** たいねつ  <br> 威力に0.5倍 (0x800) */
    HEATPROOF(0x800),
    
    /** ファーコート <br> ダメージに0.5倍 (0x800) */
    FUR_COAT(0x800),

    /** かんそうはだ <br> 威力に1.25倍 (0x1400) */
    DRY_SKIN(0x1400),
    
    /** あついしぼう <br> 相手の攻撃?に0.5倍 (0x800) */
    THICK_FAT(0x800),
    
    /** よわき <br> 攻撃に0.5倍 (0x800) */
    DEFEATIST(0x800),

    /** スロースタート <br> 攻撃に0.5倍 (0x800) */
    SLOW_START(0x800),

    /** ふしぎなウロコ <br> 防御に1.5倍 (0x1800) */
    MARVEL_SCALE(0x1800),

    /** マルチスケイル <br> ダメージ0.5倍 (0x800) */
    MULTISCALE(0x800),

    /** フレンドガード <br> ダメージに0.75倍 (0xC00) */
    FRIEND_GUARD(0xC00),
    
    /** フィルター <br> ダメージに0.75倍 (0xC00) */
    FILTER(0xC00),

    /** ハードロック <br> ダメージに0.75倍 (0xC00) */
    SOLID_ROCK(0xC00),

    /* -------------- 攻撃側の特性終わり ------------ */
    /* -------------- わからない ------------ */

    /** ぼうだん <br> ダメージに0.5倍?(わかんない) */
    BULLETPROOF(0x1800),

    
    /** くさのけがわ <br> 防御に1.5倍?(わかんない) */
    GRASS_PELT(0x1800);
    
    private int modVal;
    private Abilities(int n) {
        this.modVal = n;
    }
    public int getModVal() {
        return this.modVal;
    }
}
