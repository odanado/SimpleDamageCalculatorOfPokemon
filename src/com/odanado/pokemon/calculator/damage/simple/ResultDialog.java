/**
 * 
 */
package com.odanado.pokemon.calculator.damage.simple;

import com.odanado.pokemon.lib.DamageCalculator;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author odan
 * 
 */
public class ResultDialog extends DialogFragment {

    private Dialog dialog;
    private LinearLayout resultLayoutChild1;
    private LinearLayout resultLayoutChild2;
    private LinearLayout resultLayoutChild3;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_result);

        return dialog;
    }
    
    public void onStart() {
        super.onStart();
        
        int[] rand = new int[16];
        for(int i=0; i<16; i++) {
            rand[i] = i + 85;
        }

        Bundle bundle = getArguments();
        DamageCalculator calculator = (DamageCalculator) bundle.getSerializable("DamageCalculator");

        resultLayoutChild1 = (LinearLayout) dialog.findViewById(R.id.resultLayoutChild1);
        resultLayoutChild2 = (LinearLayout) dialog.findViewById(R.id.resultLayoutChild2);
        resultLayoutChild3 = (LinearLayout) dialog.findViewById(R.id.resultLayoutChild3);
        
        addTextView(resultLayoutChild1,getString(R.string.random),rand,0,16);
        addTextView(resultLayoutChild2,getString(R.string.damage),calculator.getDamage(),0,16);
        addTextView(resultLayoutChild3,getString(R.string.crit),calculator.getDamage(),16,32);

    }
    
    void addTextView(LinearLayout layout, String title, int[] array, int begin, int end) {
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 0, 10, 0);
        
        TextView titleView = new TextView(getActivity());
        titleView.setText(title);
        titleView.setGravity(Gravity.RIGHT);
        
        layout.addView(titleView);
        
        for(int i=begin; i<end; i++) {
            TextView textView1 = new TextView(getActivity());
            textView1.setText(String.format(" %3d ",array[i]));

            textView1.setGravity(Gravity.RIGHT);
            layout.addView(textView1);
        }
    }
}
