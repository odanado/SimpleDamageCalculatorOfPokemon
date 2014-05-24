/**
 * 
 */
package com.odanado.pokemon.calculator.damage.simple;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Iterator;
import java.util.List;

import com.odanado.pokemon.lib.BaseStats;
import com.odanado.pokemon.lib.EffortValues;
import com.odanado.pokemon.lib.IndividualValues;
import com.odanado.pokemon.lib.StatsCalculator;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

/**
 * @author odan
 * 
 */
public class StatsCalculatorDialog extends DialogFragment {

    private Dialog dialog;

    private EditText editTextPokemonName;
    private EditText editTextLevel;
    private TextView textViewBaseStatsValues;
    private TextView textViewStatsValues;
    

    private EditText[] editTextIndividualValues = new EditText[6];
    private EditText[] editTextEffortValues = new EditText[6];

    private CheckBox[] checkBoxNaturesPlus = new CheckBox[5];
    private CheckBox[] checkBoxNaturesMinus = new CheckBox[5];

    private IndividualValues individualValues;
    private EffortValues effortValues;
    private BaseStats baseStats;
    private StatsCalculator statsCalculator;
    private double[] naturesBonus = new double[5];
    private int level;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setStyle(STYLE_NORMAL, R.style.StatsCalculatorDialogTheme);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        dialog = new Dialog(getActivity(), R.style.StatsCalculatorDialogTheme);
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setContentView(R.layout.dialog_calculator_stats);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        initView();
        setDatabase();

        editTextPokemonName.setText(getString(R.string.garchomp));
        /* 文字は多言語対応のためにリソース管理するのはわかるけど、数字はどうするべきなんだろう... */
        editTextLevel.setText(getString(R.string.toString, 50));

        for (EditText editText : editTextIndividualValues) {
            editText.setText(getString(R.string.toString, 31));
        }

        for (EditText editText : editTextEffortValues) {
            editText.setText(getString(R.string.toString, 0));
        }

        naturesBonus = new double[] {
                1.0, 1.0, 1.0, 1.0, 1.0
        };      
        
        setLevel();
        setIndividualValues();
        setEffortValues();
        setBaseStats("Garchomp");
        calcStats();
        
        updateStats();
        
    }

    void updateStats() {
        int[] bSValues = new int[6];
        int[] sValues = new int[6];

        bSValues[0] = baseStats.getHP();
        bSValues[1] = baseStats.getAttack();
        bSValues[2] = baseStats.getDefense();
        bSValues[3] = baseStats.getSpAttack();
        bSValues[4] = baseStats.getSpDefense();
        bSValues[5] = baseStats.getSpeed();
        
        sValues[0] = statsCalculator.getHP();
        sValues[1] = statsCalculator.getAttack();
        sValues[2] = statsCalculator.getDefense();
        sValues[3] = statsCalculator.getSpAttack();
        sValues[4] = statsCalculator.getSpDefense();
        sValues[5] = statsCalculator.getSpeed();
        
        textViewBaseStatsValues.setText(getString(R.string.textViewBaseStatsValues, 
                bSValues[0], bSValues[1], bSValues[2], bSValues[3], bSValues[4], bSValues[5]));
        
        textViewStatsValues.setText(getString(R.string.textViewStatsValues, 
                sValues[0], sValues[1], sValues[2], sValues[3], sValues[4], sValues[5]));
        
    }
    
    void calcStats() {
        setLevel();
        statsCalculator = new StatsCalculator(baseStats, effortValues, individualValues, level, naturesBonus);
    }
    
    void setLevel() {
        try {
            level = Integer.parseInt(editTextLevel.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            level = 50;
        }
    }

    void setIndividualValues() {
        int[] values = new int[6];
        try {
            for (int i = 0; i < values.length; i++) {
                values[i] = Integer.parseInt(editTextIndividualValues[i].getText().toString());
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "error!\n" + e.toString(), Toast.LENGTH_LONG).show();
            return;
        }
        individualValues = new IndividualValues(values);
    }

    void setEffortValues() {
        int[] values = new int[6];
        try {
            for (int i = 0; i < values.length; i++) {
                values[i] = Integer.parseInt(editTextEffortValues[i].getText().toString());
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "error!\n" + e.toString(), Toast.LENGTH_LONG).show();
            return;
        }
        effortValues = new EffortValues(values);
    }

    void initView() {

        textViewStatsValues = (TextView) dialog.findViewById(R.id.textViewStatsValues);
        textViewBaseStatsValues = (TextView) dialog.findViewById(R.id.textViewBaseStatsValues);
        
        editTextPokemonName = (EditText) dialog.findViewById(R.id.editTextPokemonName);
        editTextLevel = (EditText) dialog.findViewById(R.id.editTextLevel);

        editTextIndividualValues[0] = (EditText) dialog.findViewById(R.id.editTextIndividualValueH);
        editTextIndividualValues[1] = (EditText) dialog.findViewById(R.id.editTextIndividualValueA);
        editTextIndividualValues[2] = (EditText) dialog.findViewById(R.id.editTextIndividualValueB);
        editTextIndividualValues[3] = (EditText) dialog.findViewById(R.id.editTextIndividualValueC);
        editTextIndividualValues[4] = (EditText) dialog.findViewById(R.id.editTextIndividualValueD);
        editTextIndividualValues[5] = (EditText) dialog.findViewById(R.id.editTextIndividualValueS);

        editTextEffortValues[0] = (EditText) dialog.findViewById(R.id.editTextEffortValueH);
        editTextEffortValues[1] = (EditText) dialog.findViewById(R.id.editTextEffortValueA);
        editTextEffortValues[2] = (EditText) dialog.findViewById(R.id.editTextEffortValueB);
        editTextEffortValues[3] = (EditText) dialog.findViewById(R.id.editTextEffortValueC);
        editTextEffortValues[4] = (EditText) dialog.findViewById(R.id.editTextEffortValueD);
        editTextEffortValues[5] = (EditText) dialog.findViewById(R.id.editTextEffortValueS);

        checkBoxNaturesPlus[0] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureAPlus);
        checkBoxNaturesPlus[1] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureBPlus);
        checkBoxNaturesPlus[2] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureCPlus);
        checkBoxNaturesPlus[3] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureDPlus);
        checkBoxNaturesPlus[4] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureSPlus);

        checkBoxNaturesMinus[0] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureAMinus);
        checkBoxNaturesMinus[1] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureBMinus);
        checkBoxNaturesMinus[2] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureCMinus);
        checkBoxNaturesMinus[3] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureDMinus);
        checkBoxNaturesMinus[4] = (CheckBox) dialog.findViewById(R.id.checkBoxNatureSMinus);

        editTextPokemonName.setOnEditorActionListener(onEditorActionListener);
        editTextLevel.setOnEditorActionListener(onEditorActionListener);

        for(int i=0; i<6; i++) {
            editTextIndividualValues[i].setOnEditorActionListener(onEditorActionListener);
            editTextEffortValues[i].setOnEditorActionListener(onEditorActionListener);

            if(i != 5) {
                editTextIndividualValues[i].setNextFocusDownId(editTextIndividualValues[i+1].getId());
                editTextEffortValues[i].setNextFocusDownId(editTextEffortValues[i+1].getId());
            }
        }
        
        editTextPokemonName.setNextFocusDownId(editTextLevel.getId());
        editTextLevel.setNextFocusDownId(editTextIndividualValues[0].getId());
        editTextIndividualValues[5].setNextFocusDownId(editTextEffortValues[0].getId());
    }

    private void setDatabase() {
        databaseHelper = new DatabaseHelper(getActivity());
        try {
            databaseHelper.createEmptyDatabase();
            database = databaseHelper.openDatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    void setBaseStats(String name) {
        /*
         * rawQuery 以外使う理由ないと思ってたけど、間にカラム挿入されたらあれになるし
         * 難しいなあ、と思った
         * とりあえず、このdialog でbase_stats を呼び出すのはここだけだし、
         * カラム追加されることもないので、この方法で...
         */
        
        String query = "SELECT hp, attack, defense, sp_atk, sp_def, speed FROM base_stats WHERE name = '" + name + "'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        
        int[] values = new int[6];
        
        for(int i=0;i<6;i++) {
            values[i] = Integer.parseInt(cursor.getString(i));
        }

        baseStats = new BaseStats(values);
        
    }
    
    String toEnglishName(String name) {
        String query = "SELECT name FROM to_japanese WHERE japanese_name = '" + name +"'";
        Cursor cursor = database.rawQuery(query, null);
        
        if(cursor == null) {
            throw new NullPointerException(name + " is Not Found");
        }
        
        if(cursor.getCount() == 0) {
            throw new IndexOutOfBoundsException(name + " is Not Found");
        }
        
        
        cursor.moveToFirst();
        
        return cursor.getString(0);
        
    }

    private OnEditorActionListener onEditorActionListener = new OnEditorActionListener() {
        
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            if(event == null) {
                if(actionId == EditorInfo.IME_ACTION_NEXT) {
                    try {
                        String name = toEnglishName(editTextPokemonName.getText().toString());
                        setBaseStats(name);
                        setEffortValues();
                        setIndividualValues();
                        calcStats();
                        updateStats();
                        int id;

                        if((id = v.getNextFocusDownId()) != -1) {
                            EditText text = (EditText) dialog.findViewById(id);
                            text.requestFocus();
                        }
                        
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                else if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ((InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(v.getWindowToken(), 0);

                }
            }
            
            
            return true;
        }
    };
    
}
