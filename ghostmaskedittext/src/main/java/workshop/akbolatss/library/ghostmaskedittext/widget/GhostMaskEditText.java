package workshop.akbolatss.library.ghostmaskedittext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import workshop.akbolatss.library.ghostmaskedittext.api.CommonNumberMask;

public class GhostMaskEditText extends AppCompatEditText implements TextWatcher {

    private String mask;
    private CommonNumberMask maskEnum;

    private boolean isMaskEnabled = true;
    private boolean isSettingNewMask = false;
    private boolean isEmpty = true;
    private boolean isRunning = false;
    private boolean isDeleting = false;

    private boolean forceClear = false;
    private boolean isGhostMaskVisible = true;

    public GhostMaskEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        onImplementListeners();
    }

    /**
     * Adding TextWatcher
     * P.S. Remember, EditText can contain more than one listener
     * So dont forget to remove unnecessary
     */
    private void onImplementListeners() {
        super.addTextChangedListener(this);
    }

    public void setMaskEnum(CommonNumberMask muskEnum) {
        isSettingNewMask = true;
        this.maskEnum = muskEnum;
        this.mask = muskEnum.getPhoneMask();
        setMask(mask);
        setText(muskEnum.getCountryCodeWithMisc());
        isSettingNewMask = false;
    }

    /**
     * Force clear text without triggering Mask
     */
    public void forceClear() {
        forceClear = true;
        isGhostMaskVisible = false;
        if (!TextUtils.isEmpty(getText()))
            getText().clear();
        forceClear = false;
    }

    public void setMaskEnabled(boolean enabled) {
        this.isMaskEnabled = enabled;
    }

    /**
     * True, mask will be drawn, otherwise not
     */
    public void setGhostMaskVisible(boolean ghostMaskVisible) {
        isGhostMaskVisible = ghostMaskVisible;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        isDeleting = count > after;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        if (mask == null)
            return;
        isEmpty = (count == 0);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!isMaskEnabled)
            return;
        if (mask == null || isSettingNewMask)
            return;
        // Prevents from deleting code, e.g. '+7 (' for KZ
        if (!editable.toString().startsWith(maskEnum.getCountryCodeWithMisc()) && !forceClear) {
            isRunning = true;
            int similarityEnd = getSimilarityEnd(maskEnum.getCountryCodeWithMisc(), editable.toString());
            editable.replace(0, similarityEnd, maskEnum.getCountryCodeWithMisc());
            isRunning = false;
            return;
        }
        if (isEmpty || isRunning || isDeleting)
            return;

        isRunning = true;

        StringBuilder txt = editTextLoopToNextChar(mask, editable.length() - 1);

        //Input Filter work
        InputFilter[] filters = editable.getFilters(); //get filter
        editable.setFilters(new InputFilter[]{}); //reset filter
        editable.insert(editable.length() - 1, txt);
        editable.setFilters(filters); //restore filter

        isRunning = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        drawMaskOnCanvas(canvas);
        canvas.restore();
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (maskEnum != null && !forceClear)
            if (selStart < maskEnum.getCountryCodeWithMisc().length())
                setSelection(maskEnum.getCountryCodeWithMisc().length());
    }

    private int getSimilarityEnd(String countryCode, String currentText) {
        int max = Math.max(countryCode.length(), currentText.length());
        for (int index = 0; index < max; index++) {
            try {
                if (countryCode.charAt(index) != currentText.charAt(index))
                    return index;
            } catch (StringIndexOutOfBoundsException e) {
                return index;
            }
        }
        return 0;
    }

    /**
     * If text is empty, draw full mask on canvas
     * Else, draw cut mask on canvas
     *
     * @param canvas canvas
     */
    private void drawMaskOnCanvas(Canvas canvas) {
        if (!isGhostMaskVisible || maskEnum == null)
            return;
        String currentText = getText().toString();
        float calculatedPadding = getCompoundPaddingStart() + calculateTextWidth(currentText);
        if (TextUtils.isEmpty(currentText)) {
            canvas.drawText(maskEnum.getPhoneMask(), calculatedPadding, getLineBounds(0, null), getPaint());
            return;
        }

        // Cut mask before draw
        String result = maskEnum.getPhoneMask().substring(currentText.length());
        canvas.drawText(result, calculatedPadding, getLineBounds(0, null), getPaint());
    }

    private float calculateTextWidth(@Nullable String text) {
        if (TextUtils.isEmpty(text))
            return 0;
        float[] widths = new float[text.length()];
        getPaint().getTextWidths(text, widths);
        float textWidth = 0;
        for (float w : widths) {
            textWidth += w;
        }
        return textWidth;
    }
    // call for setMask

    private void setMask(String mask) {
        super.setFilters(setLengthEditText(mask)); //Setting length
    }
    // call in afterTextChanged event

    private static StringBuilder editTextLoopToNextChar(String maskEdit, int position) {

        StringBuilder finalResult = new StringBuilder();
        for (int i = position; i < maskEdit.length(); i++) {
            if (maskEdit.charAt(i) != '_')
                finalResult.append(maskEdit.charAt(i));
            else
                break;
        }

        return finalResult;
    }

    // set mask length to InputFilter
    private static InputFilter[] setLengthEditText(String maskText) {
        return new InputFilter[]{new InputFilter.LengthFilter(maskText.length())};
    }

}
