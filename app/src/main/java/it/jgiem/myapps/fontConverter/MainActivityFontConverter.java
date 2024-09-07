package it.jgiem.myapps.fontConverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import it.jgiem.myapps.R;

public class MainActivityFontConverter extends AppCompatActivity {

    private RadioGroup rg;
    private RadioButton rbZg2Uni, rbUni2Zg;
    private EditText etInput, etOutput;
    private Button btConvert, btClear, btCopy;
    private Typeface zgFont, uniFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fontconverter);
        zgFont = getResources().getFont(R.font.zawgyi_one);
        uniFont = getResources().getFont(R.font.pyidaungsu);
        initUi();
        initListeners();
    }

    private void initListeners() {


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_uni2zg){
                    etInput.setTypeface(uniFont);
                    etOutput.setTypeface(zgFont);
                } else{
                    etInput.setTypeface(zgFont);
                    etOutput.setTypeface(uniFont);
                }
            }
        });

        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etInput.getText().toString();
                String output;
                if (rbZg2Uni.isChecked()){
                    output = Rabbit.zg2uni(input);
                } else{
                    output = Rabbit.uni2zg(input);
                }
                etOutput.setText(output);
            }
        });

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInput.getText().clear();
                etOutput.getText().clear();
            }
        });

        btCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String output = etOutput.getText().toString();
                if (!output.isEmpty()){
                    ClipboardManager clipboardManager= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("font_converter", output);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(getApplicationContext(), "Output text Copied", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No output text to copy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initUi() {
        rg = findViewById(R.id.rg_fonts);
        rbZg2Uni = findViewById(R.id.rb_zg2uni);
        rbUni2Zg = findViewById(R.id.rb_uni2zg);
        etInput = findViewById(R.id.etInput);
        etOutput=findViewById(R.id.etOutput);
        btClear = findViewById(R.id.btClear);
        btCopy = findViewById(R.id.btCopy);
        btConvert = findViewById(R.id.btConvert);

    }
}