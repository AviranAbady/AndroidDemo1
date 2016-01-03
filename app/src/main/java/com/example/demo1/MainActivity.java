package com.example.demo1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private Button mButton;
    private TextView mTextView;
    private SeekBar mSeekBarRotation;
    private SeekBar mSeekBarSize;
    private SeekBar mSeekBarAlpha;
    private EditText mEditText;
    private float mOriginalTextSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = createLayoutManually();
        setContentView(R.layout.activity_main);

        setupViews();
    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView);
        mSeekBarRotation = (SeekBar) findViewById(R.id.seekBarRotation);
        mSeekBarSize = (SeekBar) findViewById(R.id.seekBarSize);
        mSeekBarAlpha = (SeekBar) findViewById(R.id.seekBarAlpha);
        mEditText = (EditText) findViewById(R.id.editText);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mEditText.getText().toString());
                hideKeyboard();
            }
        });

        mOriginalTextSize = mTextView.getTextSize();

        mSeekBarAlpha.setOnSeekBarChangeListener(this);
        mSeekBarRotation.setOnSeekBarChangeListener(this);
        mSeekBarSize.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch(seekBar.getId()) {
            case R.id.seekBarAlpha:
                changeTextAlpha(progress);
                return;
            case R.id.seekBarRotation:
                changeTextRotation(progress);
                return;
            case R.id.seekBarSize:
                changeTextSize(progress);
                return;
        }
    }

    private void changeTextSize(int progress) {
        mTextView.setTextSize(((float)progress/100) * mOriginalTextSize);
    }

    private void changeTextRotation(int progress) {
        mTextView.setRotationY((float)progress/100 * 180);
    }

    private void changeTextAlpha(int progress) {
        mTextView.setAlpha((float)progress/100);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if(view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private View createLayoutManually() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView1 = new TextView(this);
        TextView textView2 = new TextView(this);
        TextView textView3 = new TextView(this);
        TextView textView4 = new TextView(this);

        textView1.setText("This is TextView 1");
        textView2.setText("This is TextView 2");
        textView3.setText("This is TextView 3");
        textView4.setText("This is TextView 4");


        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);
        linearLayout.addView(textView4);

        return linearLayout;
    }


}
