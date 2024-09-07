package it.jgiem.myapps.voucher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import it.jgiem.myapps.databinding.ActivityVoucherAddBinding;


public class AddActivity extends AppCompatActivity {

    private ActivityVoucherAddBinding binding;
    //private boolean updated;
    private Voucher voucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoucherAddBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        initUI();
        initListeners();

    }

    private void initUI() {
        Intent intent = getIntent();
        if(intent != null){
            voucher = (Voucher) intent.getSerializableExtra("voucher");
            if(voucher!= null){
                binding.etTitle.setText(voucher.title());
                binding.etQuantity.setText(String.valueOf(voucher.quantity()));
                binding.etAmount.setText(String.valueOf(voucher.amount()));
                binding.btAdd.setText("Update");
            }

        }
    }

    private void initListeners() {
        binding.btCancle.setOnClickListener(v->{
            finish();
        });

        binding.btAdd.setOnClickListener(v->{

            if(voucher != null){
                try{
                    String title = binding.etTitle.getText().toString();
                    int quantity = Integer.parseInt(binding.etQuantity.getText().toString());
                    int amount = Integer.parseInt(binding.etAmount.getText().toString());
                    if(!title.isEmpty() && quantity > 0 && amount > 0){
                        Voucher voucher = new Voucher(this.voucher.id(), title, quantity, amount);
                        if(this.voucher.equals(voucher)){
                            Toast.makeText(this, "No Updated Data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = new Intent();
                        intent.putExtra("voucher", voucher);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            } else{
                try{
                    String title = binding.etTitle.getText().toString();
                    int quantity = Integer.parseInt(binding.etQuantity.getText().toString());
                    int amount = Integer.parseInt(binding.etAmount.getText().toString());
                    if(!title.isEmpty() && quantity > 0 && amount > 0){
                        Voucher voucher = new Voucher(new Random().nextInt(), title, quantity, amount);
                        Intent intent = new Intent();
                        intent.putExtra("voucher", voucher);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}