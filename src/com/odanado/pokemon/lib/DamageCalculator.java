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
    private Abilities attackAbility;
    private Abilities defenseAbility;
    private Items attackItem;
    private Items defenseItem;
    private Field field;

    private int[] damage = new int[32];

    public DamageCalculator(int movePower, int attackPower, int defensePower, double attackBonus, double typeMatchUp, int attackLevel, 
            Abilities attackAbility, Abilities defenseAbility, Items attackItem, Items defenseItem, Field field) {
        super();
        this.movePower = movePower;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.attackBonus = attackBonus;
        this.typeMatchUp = typeMatchUp;
        this.attackLevel = attackLevel;
        this.attackAbility = attackAbility;
        this.defenseAbility = defenseAbility;
        this.attackItem = attackItem;
        this.defenseItem = defenseItem;
        this.field = field;
        this.damage = damage;

        calcDamage();

    }

    public void calcDamage() {
        /*
         * ダメージ = ( ( ( ( ( ( 攻撃側のレベル * 2 ) / 5 + 2 ) * 威力 * 攻撃力 ) / 防御力 ) / 50
         * + 2 ) * マルチ対象※ * 天候※ * 急所 * 乱数幅(16分率) * タイプ一致※ * タイプ相性 * 火傷 * ダメージ補正※
         * )
         */
        modMovePower();
        modAttackPower();
        modDefensePower();
        
        int baseDamage;
        int mod = 0x1000;
        
        baseDamage = (attackLevel * 2) / 5 + 2;

        baseDamage *= movePower;

        baseDamage *= attackPower;

        baseDamage /= defensePower;

        baseDamage = baseDamage / 50 + 2;
        
        if(field.isDouble) {
            baseDamage = calcRoundHalfDown(1.0 * baseDamage * 0xC00 / 0x1000);
        }
        if(field.isPlusWeather) {
            baseDamage = calcRoundHalfDown(1.0 * baseDamage * 0x1800 / 0x1000);
        }
        if(field.isMinusWeather) {
            baseDamage = calcRoundHalfDown(1.0 * baseDamage * 0x800 / 0x1000);
        }
        
        mod = modBaseDamage();
        

        for (int i = 0; i < 16; i++) {
            damage[i + 16] = baseDamage * 3 / 2;
            damage[i + 16] = (damage[i + 16] * (85 + i)) / 100;
            damage[i] = (baseDamage * (85 + i)) / 100;
        }

        for (int i = 0; i < 32; i++) {
            damage[i] = (int) (damage[i] * attackBonus);
            damage[i] = (int) Math.floor( damage[i] * typeMatchUp);
            
            if(field.isBurn) {
                damage[i] /= 2;
            }
            
            damage[i] = Math.max(1, damage[i]);
            
            damage[i] = calcRoundHalfDown(1.0 * damage[i] * mod / 0x1000);
            
            /* 親子愛 */
        }

    }
    
    private int modBaseDamage() {
        int mod = 0x1000;

        if(field.isReflect) {
            int tmp = field.isDouble ? 0xA8F : 0x800;
            mod = calcMod(mod, tmp);
        }
        
        if(defenseAbility == Abilities.MULTISCALE) {
            mod = calcMod(mod, 0x800);
        }
        switch (attackAbility) {
        case TINTED_LENS:
            mod = calcMod(mod, 0x2000);
            break;
        case SNIPER:
            mod = calcMod(mod, 0x1800);
            break;

        default:
            break;
        }
        
        switch (defenseAbility) {
        case SOLID_ROCK:
            mod = calcMod(mod, 0xC00);
            break;
        case FILTER:
            mod = calcMod(mod, 0xC00);
            break;

        default:
            break;
        }
        
        switch (attackItem) {
        case EXPERT_BELT:
            mod = calcMod(mod, 0x1333);
            break;
        case LIFE_ORB:
            mod = calcMod(mod, 0x14CC);
            break;

        default:
            break;
        }
        
        if(defenseItem == Items.BERRIES) {
            mod = calcMod(mod, 0x800);
        }
        if(defenseAbility == Abilities.FUR_COAT) {
            mod = calcMod(mod, 0x800);
        }
        
        return mod;
    }
    
    private void modDefensePower() {
        int mod = 0x1000;
        
        if(field.isBenefitSandstorm) {
            defensePower = calcRoundHalfDown(defensePower * 3 / 2);
        }
        
        switch (defenseAbility) {
        case MARVEL_SCALE:
            mod = calcMod(mod, 0x1800);
            break;
        case FLOWER_GIFT:
            mod = calcMod(mod, 0x1800);
            break;
        default:
            break;
        }
        
      //しんかいの鱗 メタルパウダー 心の雫
        switch (defenseItem) {
        case ASSAULT_VEST:
            mod = calcMod(mod, 0x1800);
            break;
        case EVOLUTION_STONE:
            mod = calcMod(mod, 0x1800);
            break;
        default:
            break;
        }
        
        defensePower = calcRoundHalfDown(1.0 * defensePower * mod / 0x1000);
    }
    
    private void modAttackPower() {
        int mod = 0x1000;
        /* Hustle はりきりは例外 */
        
        //Overgrow,Torrent,Swarm,
        //FlashFire(もらいび)
        switch (attackAbility) {
        case GUTS:
            mod = calcMod(mod, 0x1800);
            break;
        case BLAZE:
            mod = calcMod(mod, 0x1800);
            break;
        case FLOWER_GIFT:
            mod = calcMod(mod, 0x1800);
            break;
        case DEFEATIST:
            mod = calcMod(mod, 0x800);
            break;
        case SLOW_START:
            mod = calcMod(mod , 0x800);
            break;
        case HUGE_POWER:
            mod = calcMod(mod, 0x2000);
            break;

        default:
            break;
        }
        
        //deeep sea tooth(しんかいのきば)
        //Light Ball(電気球) Soul Dew(心の雫)
        switch (attackItem) {
        case THICK_BONE:
            mod = calcMod(mod, 0x2000);
            break;
        case CHOICE_BAND:
            mod = calcMod(mod, 0x1800);
            break;
        case CHOICE_SPECS:
            mod = calcMod(mod, 0x1800);
            break;

        default:
            break;
        }
        attackPower = calcRoundHalfDown(1.0 * attackPower * mod / 0x1000);
    }

    private void modMovePower() {
        int mod = 0x1000;

        switch (attackAbility) {
        case TECHNICIAN:
            mod = calcMod(mod, 0x1800);
            break;
        case ANALYTIC:
            mod = calcMod(mod, 0x14CD);
            break;
        case SAND_FORCE:
            mod = calcMod(mod, 0x14CD);
            break;
        case RECKLESS:
            mod = calcMod(mod, 0x1333);
            break;
        case IRON_FIST:
            mod = calcMod(mod, 0x1333);
            break;
        case SHEER_FORCE:
            mod = calcMod(mod, 0x14CD);
            break;
        case AERILATE:
            mod = calcMod(mod, 0x14CD);
            break;
        case PIXILATE:
            mod = calcMod(mod, 0x14CD);
            break;
        case REFRIGERATE:
            mod = calcMod(mod, 0x14CD);
            break;
        case MEGA_LAUNCHER:
            mod = calcMod(mod, 0x1800);
            break;
        case TOUGH_CLAWS:
            mod = calcMod(mod, 0x1547);
            break;
        case AURA_BREAK:
            mod = calcMod(mod, 0xAAA);
            break;
        case FAIRY_AURA:
            mod = calcMod(mod, 0x1555);
            break;
        case DARK_AURA:
            mod = calcMod(mod, 0x1555);
            break;

        default:
            break;
        }

        switch (defenseAbility) {
        case DRY_SKIN:
            mod = calcMod(mod, 0x1400);
            break;
        case HEATPROOF:
            mod = calcMod(mod, 0x800);
            break;
        case FUR_COAT:
            mod = calcMod(mod, 0x800);
            break;
        default:
            break;
        }
        
        //Adamant Orb,Lustrous Orb,Griseous Orb
        switch (attackItem) {
        case PLATES:
            mod = calcMod(mod, 0x1333);
            break;
        case MUSCLE_BAND:
            mod = calcMod(mod, 0x1199);
            break;
        case WISE_GLASSES:
            mod = calcMod(mod, 0x1199);
            break;
        case JEWELS:
            mod = calcMod(mod, 0x14CD);
            break;
            
        default:
            break;
        }
        
        if(field.isHelpingHand) {
            mod = calcMod(mod, 0x1800);
        }
        
        movePower = calcRoundHalfDown(1.0 * movePower * mod / 0x1000);
    }

    private int calcMod(int mod1, int mod2) {
        mod1 = (mod1 * mod2 + 0x800) >> 12;
        return mod1;
    }
    
    private int calcRoundHalfDown(double a) {
        return (int) Math.floor(a + 0.5);
    }
}
