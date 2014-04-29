package com.odanado.pokemon.calculator.damage.simple;

import java.util.Arrays;
import java.util.List;

import com.odanado.pokemon.lib.Abilities;
import com.odanado.pokemon.lib.DamageCalculator;
import com.odanado.pokemon.lib.Field;
import com.odanado.pokemon.lib.Items;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
 * TODO 
 * ダメージ計算の特性補正(特に厚い脂肪とかよわき) の条件について精査しないとダメ
 */

public class MainActivity extends Activity {

    private EditText currentEditText;
    private EditText editTextMovePower;
    private EditText editTextAttackPower;
    private EditText editTextDefensePower;
    private EditText editTextAttackBonus;
    private EditText editTextTypeMatchUp;

    private Button buttonAttackAbility;
    private Button buttonDefenseAbility;
    private Button buttonAttackItem;
    private Button buttonDefenseItem;

    private CheckBox checkBoxHelpingHand;
    private CheckBox checkBoxBurn;
    private CheckBox checkBoxPlusWeather;
    private CheckBox checkBoxReflect;
    private CheckBox checkBoxDoubleDamage;
    private CheckBox checkBoxMinusWeather;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);
        
        activity = this;
        
        initView();
        
        if(!(this.getCurrentFocus() == null)) {
            currentEditText = (EditText) this.getCurrentFocus();        
        }
        else {
            currentEditText = editTextMovePower;
        }
                
    }
    
    private void initView() {
        editTextAttackBonus  = (EditText) this.findViewById(R.id.editTextAttackBonus);
        editTextAttackPower  = (EditText) this.findViewById(R.id.editTextAttackPower);
        editTextDefensePower = (EditText) this.findViewById(R.id.editTextDefensePower);
        editTextMovePower    = (EditText) this.findViewById(R.id.editTextMovePower);
        editTextTypeMatchUp  = (EditText) this.findViewById(R.id.editTextTypeMatchUp);

        buttonAttackAbility  = (Button) this.findViewById(R.id.buttonAttackAbility);
        buttonDefenseAbility = (Button) this.findViewById(R.id.buttonDefenseAbility);
        buttonAttackItem     = (Button) this.findViewById(R.id.buttonAttackItem);
        buttonDefenseItem    = (Button) this.findViewById(R.id.buttonDefenseItem);

        checkBoxHelpingHand  = (CheckBox) this.findViewById(R.id.checkBoxHelpingHand);
        checkBoxBurn         = (CheckBox) this.findViewById(R.id.checkBoxBurn);
        checkBoxPlusWeather  = (CheckBox) this.findViewById(R.id.checkBoxPlusWeather);
        checkBoxReflect      = (CheckBox) this.findViewById(R.id.checkBoxReflect);
        checkBoxDoubleDamage = (CheckBox) this.findViewById(R.id.checkBoxDoubleDamage);
        checkBoxMinusWeather = (CheckBox) this.findViewById(R.id.checkBoxMinusWeather);
    }
    
    public void onClickListButton(View view) {
        
        final Button button;
        final String[] array;
        final String title;
        
        switch (view.getId()) {
        case R.id.buttonAttackAbility:
            button = (Button) this.findViewById(R.id.buttonAttackAbility);
            array  = getResources().getStringArray(R.array.attackAbilities);
            title  = getResources().getString(R.string.ability);
            
            break;
        case R.id.buttonDefenseAbility:
            button = (Button) this.findViewById(R.id.buttonDefenseAbility);
            array  = getResources().getStringArray(R.array.defenseAbilities);
            title  = getResources().getString(R.string.ability);
            
            break;
            
        case R.id.buttonAttackItem:
            button = (Button) this.findViewById(R.id.buttonAttackItem);
            array  = getResources().getStringArray(R.array.attackItems);
            title  = getResources().getString(R.string.item);
            
            break;
            
        case R.id.buttonDefenseItem:
            button = (Button) this.findViewById(R.id.buttonDefenseItem);
            array  = getResources().getStringArray(R.array.defenseItems);
            title  = getResources().getString(R.string.item);
            
            break;
            
        default:
            button = null;
            array  = null;
            title  = null;
            break;
        }
        
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setItems(array, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                
                    button.setText(array[which]);
            }
        });
        builder.show();

    
    }
    
    public void onClickTenKey(View view) {
        
        String currentStirng = "";
        String addString;
        /* 選択されている部分を削除 */
        for(int i=0;i<currentEditText.getText().length(); i++) {
            if(!(currentEditText.getSelectionStart() <= i && i<= currentEditText.getSelectionEnd())) {
                currentStirng += currentEditText.getText().toString().charAt(i);
            }
        }
        
        switch (view.getId()) {
        case R.id.button0:
            addString = "0";
            break;
        case R.id.button1:
            addString = "1";
            break;
        case R.id.button2:
            addString = "2";
            break;
        case R.id.button3:
            addString = "3";
            break;
        case R.id.button4:
            addString = "4";
            break;
        case R.id.button5:
            addString = "5";
            break;
        case R.id.button6:
            addString = "6";
            break;
        case R.id.button7:
            addString = "7";
            break;
        case R.id.button8:
            addString = "8";
            break;
        case R.id.button9:
            addString = "9";
            
            break;
        case R.id.buttonPeriod:
            addString = ".";
            break;

        default:
            addString = "";
            break;
        }
                
        if(currentStirng.length() <= 2) {
            currentStirng += addString;
        }
        
        currentEditText.setText(currentStirng);
        currentEditText.setSelection(currentEditText.getText().length());
        
    }
       
    public void onClickNext(View v) {

        switch (currentEditText.getId()) {
        case R.id.editTextMovePower:
            currentEditText = (EditText) this.findViewById(R.id.editTextAttackPower);
            break;
        case R.id.editTextAttackPower:
            currentEditText = (EditText) this.findViewById(R.id.editTextDefensePower);
            break;
        case R.id.editTextDefensePower:
            currentEditText = (EditText) this.findViewById(R.id.editTextAttackBonus);
            break;
        case R.id.editTextAttackBonus:
            currentEditText = (EditText) this.findViewById(R.id.editTextTypeMatchUp);
            break;
        case R.id.editTextTypeMatchUp:
            currentEditText = (EditText) this.findViewById(R.id.editTextMovePower);
            calcDamage();
            break;

        default:
            break;
        }
        currentEditText.requestFocus();
        currentEditText.selectAll();
    }
    
    
    private void calcDamage() {
        int[] damageList;
        
        int movePower       = Integer.parseInt(editTextMovePower.getText().toString());
        int attackPower     = Integer.parseInt(editTextAttackPower.getText().toString());
        int defensePower    = Integer.parseInt(editTextDefensePower.getText().toString());
        double attackBonus  = Double.parseDouble(editTextAttackBonus.getText().toString());
        double typeMatchUp  = Double.parseDouble(editTextTypeMatchUp.getText().toString());
        int attackLevel     = 50;

        Abilities attackAbility  = toAbility(buttonAttackAbility.getText().toString());
        Abilities defenseAbility  = toAbility(buttonDefenseAbility.getText().toString());
        Items attackItem = toItem(buttonAttackItem.getText().toString());
        Items defenseItem = toItem(buttonDefenseItem.getText().toString());
        /* 岩タイプの砂嵐補正、Dに直接1.5倍だから暗算させよう */
        Field field = new Field(checkBoxPlusWeather.isChecked(), checkBoxMinusWeather.isChecked(), 
                false, checkBoxBurn.isChecked(), false, checkBoxHelpingHand.isChecked(), 
                checkBoxDoubleDamage.isChecked(), checkBoxReflect.isChecked(), false);
        
        DamageCalculator calculator = new DamageCalculator(movePower, attackPower, defensePower, attackBonus, typeMatchUp, attackLevel,
                attackAbility, defenseAbility, attackItem, defenseItem, field);
        
        damageList = calculator.getDamage();
        
        Toast.makeText(this, String.format("%d %d %d %d %d %d"
                , damageList[10], damageList[11], damageList[12], damageList[13], damageList[14], damageList[15]), 1).show();
    }
    
    private Items toItem(String itemName) {
        Items item = Items.NONE;
        
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.attackItems));
        int index = list.indexOf(itemName);
        
        switch (index) {
        case 0:
            item = Items.NONE;
            break;
        case 1:
            item = Items.MUSCLE_BAND;
            break;
        case 2:
            item = Items.PLATES;
            break;
        case 3:
            item = Items.JEWELS;
            break;
        case 4:
            item = Items.CHOICE_BAND;
            break;
        case 5:
            item = Items.THICK_BONE;
            break;
        case 6:
            item = Items.EXPERT_BELT;
            break;
        case 7:
            item = Items.LIFE_ORB;
            break;

        default:
            break;
        }

        list = Arrays.asList(getResources().getStringArray(R.array.defenseItems));
        index = list.indexOf(itemName);
        
        switch (index) {
        case 0:
            item = Items.NONE;
            break;
        case 1:
            item = Items.EVOLUTION_STONE;
            break;
        case 2:
            item = Items.ASSAULT_VEST;
            break;
        case 3:
            item = Items.BERRIES;
            break;

        default:
            break;
        }
        
        return item;
        
    }
    
    private Abilities toAbility(String abilityName) {
        /* この辺の処理、うまい方法思いつかない...(特性を1つずつ選ばせて、配列にアクセス？) */
        Abilities abilities = Abilities.NONE;
        
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.attackAbilities));
        int index = list.indexOf(abilityName);

        switch (index) {
        case 0:
            abilities = Abilities.NONE;
            break;
        case 1:
            abilities = Abilities.IRON_FIST;
            break;
        case 2:
            abilities = Abilities.SHEER_FORCE;
            break;
        case 3:
            abilities = Abilities.TECHNICIAN;
            break;
        case 4:
            abilities = Abilities.BLAZE;
            break;
        case 5:
            abilities = Abilities.PURE_POWER;
            break;
        case 6:
            abilities = Abilities.SNIPER;
            break;
        case 7:
            abilities = Abilities.TINTED_LENS;
            break;

        default:
            break;
        }
            
        list = Arrays.asList(getResources().getStringArray(R.array.defenseAbilities));
        index = list.indexOf(abilityName);
        
        switch (index) {
        case 0:
            abilities = Abilities.NONE;
            break;
        case 1:
            abilities = Abilities.HEATPROOF;
            break;
        case 2:
            abilities = Abilities.DRY_SKIN;
            break;
        case 3:
            abilities = Abilities.THICK_FAT;
        case 4:
            abilities = Abilities.MARVEL_SCALE;
            break;
        case 5:
            abilities = Abilities.MULTISCALE;
            break;
        case 6:
            abilities = Abilities.SOLID_ROCK;
            break;

        default:
            break;
        }
        
        return abilities;
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
