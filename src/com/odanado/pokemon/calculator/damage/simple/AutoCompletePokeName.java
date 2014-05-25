/**
 * 
 */
package com.odanado.pokemon.calculator.damage.simple;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * @author odan
 * 
 */
public class AutoCompletePokeName extends AutoCompleteTextView {

    public AutoCompletePokeName(Context context) {
        super(context);
    }

    public AutoCompletePokeName(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCompletePokeName(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        text = toKana(text.toString());
        super.getFilter().filter(text, this);
    }

    private String toKana(String text) {
        StringBuffer sb = new StringBuffer(text);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'ぁ' && c <= 'ん') {
                sb.setCharAt(i, (char) (c - 'ぁ' + 'ァ'));
            }
        }
        return sb.toString();
    }

}
