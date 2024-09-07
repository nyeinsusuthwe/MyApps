package it.jgiem.myapps.dateCounter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import it.jgiem.myapps.databinding.ActivityMainDatecounterBinding;
import it.jgiem.myapps.databinding.DialogInputDatecounterBinding;


public class MainActivityDateCounter extends AppCompatActivity {

    private ActivityMainDatecounterBinding binding;
    private DatePickerDialog datePickerDialog;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private AlertDialog alertDialog;
    private String dialogName;
    private static final String TOM = "Tom";
    private static final String JERRY = "Jerry";

    private SharedPreferences sp;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDatecounterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();
        
        initDatePickerDialog();
        initInputDialog();
        initListener();

    }

    private void initInputDialog() {
        var dialogBinding = DialogInputDatecounterBinding.inflate(getLayoutInflater());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog = builder
                .setView(dialogBinding.getRoot())
                .setCancelable(false)
                .create();

        dialogBinding.btConfirm.setOnClickListener(v->{
            String text = dialogBinding.etInput.getText().toString();
            switch (dialogName){
                case TOM -> {
                    binding.tvTom.setText(text);
                    save(TOM, text);
                }
                case JERRY -> {
                    binding.tvJerry.setText(text);
                    save(JERRY, text);
                }
            }
            dialogBinding.etInput.getText().clear();
            alertDialog.cancel();
        });

        dialogBinding.btCancel.setOnClickListener(v-> alertDialog.cancel());
    }

    private void initUI() {
        sp = getSharedPreferences("date_counter", MODE_PRIVATE);
        binding.tvTom.setText(read(TOM));
        binding.tvJerry.setText(read(JERRY));
    }

    private void initDatePickerDialog() {
        //dd/MM/yyyy
        datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener((view, year, month, dayofMonth)->{
            var selectedDate = LocalDate.of(year, month+1, dayofMonth);
            binding.btDate.setText(formatter.format(selectedDate));
            updateDate();
        });
        updateDate();


    }


    private void updateDate(){
        var date = LocalDate.parse(binding.btDate.getText().toString(), formatter);
        var todayDate = LocalDate.now();
        var difference = todayDate.toEpochDay() - date.toEpochDay();
        binding.tvDate.setText(difference + "Days");
    }


    private void initListener() {
        binding.btDate.setOnClickListener(v-> datePickerDialog.show());
        binding.tvTom.setOnClickListener(v->showCustomDialog(TOM));
        binding.tvJerry.setOnClickListener(v->showCustomDialog(JERRY));

//        binding.btDate.setOnClickListener(v->{
//            datePickerDialog.show();
//        });
//
//        class OnTextClickedListener implements View.OnClickListener{
//            @Override
//            public void onClick(View v){
//                showCustomDialog();
//            }
//        }
//        binding.tvTom.setOnClickListener(new OnTextClickedListener());
//        binding.tvJerry.setOnClickListener(new OnTextClickedListener());

    }

    private void showCustomDialog(String name) {
        dialogName = name;
        alertDialog.show();


//
//        dialogBinding.btConfirm.setOnClickListener(v->{
//            binding.tvJerry.setText(dialogBinding.etInput.getText().toString());
//            dialog.cancel();
//        });
//
//        dialogBinding.btCancel.setOnClickListener(v->dialog.cancel());
//
//        dialog.show();
    }

    private void save(String key, String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String read(String key){
        return sp.getString(key, key);
    }
}