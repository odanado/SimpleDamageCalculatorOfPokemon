package com.odanado.pokemon.calculator.damage.simple;

import com.odanado.pokemon.lib.DamageCalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*
 * TODO 
 * 
 */

public class MainActivity extends Activity {

    private EditText currentEditText;
    private EditText editTextMovePower;
    private EditText editTextAttackPower;
    private EditText editTextDefensePower;
    private EditText editTextAttackBonus;
    private EditText editTextTypeMatchUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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
    }
    
    public void onClick(View view) {
        
        String currentStirng = currentEditText.getText().toString();
        
        int maxi = 999;
        
        switch (view.getId()) {
        case R.id.button0:
            currentStirng += "0";
            break;
        case R.id.button1:
            currentStirng += "1";
            break;
        case R.id.button2:
            currentStirng += "2";
            break;
        case R.id.button3:
            currentStirng += "3";
            break;
        case R.id.button4:
            currentStirng += "4";
            break;
        case R.id.button5:
            currentStirng += "5";
            break;
        case R.id.button6:
            currentStirng += "6";
            break;
        case R.id.button7:
            currentStirng += "7";
            break;
        case R.id.button8:
            currentStirng += "8";
            break;
        case R.id.button9:
            currentStirng += "9";
            
            break;
        case R.id.buttonPeriod:
            currentStirng += ".";
            break;

        default:
            break;
        }
                
        if(isDouble(currentStirng) && maxi < Double.parseDouble(currentStirng)) {
            currentStirng = "999";
        }
        
        currentEditText.setText(currentStirng);
        currentEditText.setSelection(currentEditText.getText().length());
    }
    
    private boolean isDouble(String val) {
        try {
            Double.parseDouble(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
    }
    
    private void calcDamage() {
        int[] damageList;
        int movePower;
        int attackPower;
        int defensePower;
        double attackBonus;
        double typeMatchUp;
        int attackLevel;

        movePower    = Integer.parseInt(editTextMovePower.getText().toString());
        attackPower  = Integer.parseInt(editTextAttackPower.getText().toString());
        defensePower = Integer.parseInt(editTextDefensePower.getText().toString());
        attackBonus  = Double.parseDouble(editTextAttackBonus.getText().toString());
        typeMatchUp  = Double.parseDouble(editTextTypeMatchUp.getText().toString());
        attackLevel  = 50;
        
        DamageCalculator calculator = new DamageCalculator(movePower, attackPower, defensePower, attackBonus, typeMatchUp, attackLevel);
        
        damageList = calculator.getDamageList();
        
        Toast.makeText(this, String.format("%d", damageList[15]), 0).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
