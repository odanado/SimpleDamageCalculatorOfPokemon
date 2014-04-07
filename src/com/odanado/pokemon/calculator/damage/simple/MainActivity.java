package com.odanado.pokemon.calculator.damage.simple;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/*
 * TODO 
 * カーソルを移動させて、現在フォーカスなビューを表す
 * テンキー入力の際、カーソルの位置を一番後ろに
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
            currentEditText.setFocusable(true);
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
        
        switch (view.getId()) {
        case R.id.button0:
            currentEditText.setText(currentStirng + "0");
            break;
        case R.id.button1:
            currentEditText.setText(currentStirng + "1");
            break;
        case R.id.button2:
            currentEditText.setText(currentStirng + "2");
            break;
        case R.id.button3:
            currentEditText.setText(currentStirng + "3");
            break;
        case R.id.button4:
            currentEditText.setText(currentStirng + "4");
            break;
        case R.id.button5:
            currentEditText.setText(currentStirng + "5");
            break;
        case R.id.button6:
            currentEditText.setText(currentStirng + "6");
            break;
        case R.id.button7:
            currentEditText.setText(currentStirng + "7");
            break;
        case R.id.button8:
            currentEditText.setText(currentStirng + "8");
            break;
        case R.id.button9:
            currentEditText.setText(currentStirng + "9");
            
            break;
        case R.id.buttonNext:
            nextEditText();
            break;
        case R.id.buttonPeriod:
            
            break;

        default:
            break;
        }
    }
    
    private void nextEditText() {
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
            break;

        default:
            break;
        }
        currentEditText.setFocusable(true);
        currentEditText.setCursorVisible(true);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
