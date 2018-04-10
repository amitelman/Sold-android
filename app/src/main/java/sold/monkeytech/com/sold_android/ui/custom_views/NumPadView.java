package sold.monkeytech.com.sold_android.ui.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import sold.monkeytech.com.sold_android.R;


public class NumPadView extends LinearLayout {
    Context context;
    EditText inputEditText;


    public EditText getInputEditText() {
        return inputEditText;
    }
    public void setInputEditText(EditText inputEditText) {
        this.inputEditText = inputEditText;
    }

    public NumPadView(Context context) {
        super(context);
        init(context);
    }

    public NumPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    protected void init(final Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {
            inflater.inflate(R.layout.numpad_view, this);
        }

        inputEditText = new EditText(context);

        findViewById(R.id.numpad_btn_0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("0");
            }
        });
        findViewById(R.id.numpad_btn_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("1");
            }
        });
        findViewById(R.id.numpad_btn_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("2");
            }
        });
        findViewById(R.id.numpad_btn_3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("3");
            }
        });
        findViewById(R.id.numpad_btn_4).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("4");
            }
        });
        findViewById(R.id.numpad_btn_5).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("5");
            }
        });
        findViewById(R.id.numpad_btn_6).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("6");
            }
        });
        findViewById(R.id.numpad_btn_7).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("7");
            }
        });
        findViewById(R.id.numpad_btn_8).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("8");
            }
        });
        findViewById(R.id.numpad_btn_9).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.append("9");
            }
        });

        findViewById(R.id.numpad_btn_backspace).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputEditText.getText().toString().isEmpty())
                    return;
                inputEditText.setText(inputEditText.getText().toString().substring(0,inputEditText.getText().toString().length()-1));
            }
        });

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        findViewById(R.id.numpad_btn_0).setEnabled(enabled);
        findViewById(R.id.numpad_btn_1).setEnabled(enabled);
        findViewById(R.id.numpad_btn_2).setEnabled(enabled);
        findViewById(R.id.numpad_btn_3).setEnabled(enabled);
        findViewById(R.id.numpad_btn_4).setEnabled(enabled);
        findViewById(R.id.numpad_btn_5).setEnabled(enabled);
        findViewById(R.id.numpad_btn_6).setEnabled(enabled);
        findViewById(R.id.numpad_btn_7).setEnabled(enabled);
        findViewById(R.id.numpad_btn_8).setEnabled(enabled);
        findViewById(R.id.numpad_btn_9).setEnabled(enabled);
        findViewById(R.id.numpad_btn_backspace).setEnabled(enabled);
    }
}
