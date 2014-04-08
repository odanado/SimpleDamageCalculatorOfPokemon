/**
 * 
 */
package com.odanado.pokemon.lib;

/**
 * @author odan
 *
 */
public class Field {
    /** 攻撃に有利な天候かどうか <br> ダメージに1.5倍 (0x1800) */
    protected boolean isPlusWeather;
    
    /** 攻撃に不利な天候かどうか <br> ダメージに0.5倍 (0x800) */
    protected boolean isMinusWeather;
    
    /** みずあそび状態かどうか(どろあそびなども含む) <br> 威力に1/3(0x548) */
    protected boolean isWaterSport;
    
    /** やけど状態かどうか <br> 攻撃に0.5倍 /2 */
    protected boolean isBurn;

    /** 砂嵐の恩恵を受けているかどうか <br> 防御に直接1.5倍 */
    protected boolean isBenefitSandstorm;

    /** てだすけを受けているか <br> 威力に1.5倍 (0x1800) */
    protected boolean isHelpingHand;

    /** シングルか */
    protected boolean isSingle;

    /** リフレクターか */
    protected boolean isReflect;

    /** フレンドガードか (0xC00) */
    protected boolean isFriendGuard;

    public Field(boolean isPlusWeather, boolean isMinusWeather, boolean isWaterSport, boolean isBurn, 
            boolean isBenefitSandstorm, boolean isHelpingHand, boolean isSingle, boolean isReflect, boolean isFriendGuard) {
        super();
        this.isPlusWeather = isPlusWeather;
        this.isMinusWeather = isMinusWeather;
        this.isWaterSport = isWaterSport;
        this.isBurn = isBurn;
        this.isBenefitSandstorm = isBenefitSandstorm;
        this.isHelpingHand = isHelpingHand;
        this.isSingle = isSingle;
        this.isReflect = isReflect;
        this.isFriendGuard = isFriendGuard;
    }
    

}
