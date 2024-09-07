package it.jgiem.myapps.voucher;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import it.jgiem.myapps.databinding.ActivityMainVoucherBinding;


public class MainActivityVoucher extends AppCompatActivity {


    private ActivityMainVoucherBinding binding;
    private List<Voucher> voucherList;
    private static final int ADD_VOUCHER_CODE = 123;
    private static final int UPDATE_VOUCHER_CODE = 124;

    private VoucherAdapter voucherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initRecyclerView();
        initListeners();
    }

    private void initRecyclerView() {
        voucherList = new ArrayList<>();
        voucherAdapter = new VoucherAdapter();
        binding.rvVouncher.setAdapter(voucherAdapter);
        binding.rvVouncher.setLayoutManager(new LinearLayoutManager(this));
        voucherAdapter.setVoucherList(voucherList);

        voucherAdapter.setOnClickListener(new OnVoucherClickListener() {
            @Override
            public void onVoucherClicked(Voucher voucher) {
                Intent intent = new Intent(MainActivityVoucher.this, AddActivity.class);
                intent.putExtra("voucher", voucher);
                startActivityForResult(intent, UPDATE_VOUCHER_CODE);
            }

            @Override
            public void onDeleteVoucher(Voucher voucher) {
                voucherList.remove(voucher);
                updateVoucherList();
            }



        });
    }

    private void initListeners() {
        binding.fabAdd.setOnClickListener(v->{
            var intent = new Intent(this, AddActivity.class);
            startActivityForResult(intent, ADD_VOUCHER_CODE);
        });
    }

    private void updateVoucherList(){
        voucherAdapter.setVoucherList(voucherList);
        int totalQuantity = voucherList.stream().map(voucher -> voucher.quantity()).reduce((v1, v2) -> v1+ v2).get();
        int totalAmount = voucherList.stream().map(voucher -> voucher.amount()).reduce((v1, v2) -> v1+ v2).get();
        binding.tvQuantity.setText("" + totalQuantity);
        binding.tvAmount.setText(""+ totalAmount);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_VOUCHER_CODE && resultCode == RESULT_OK) {
            Voucher voucher = (Voucher) data.getSerializableExtra("voucher");
            voucherList.add(voucher);
            updateVoucherList();
        } else if(requestCode == UPDATE_VOUCHER_CODE && resultCode == RESULT_OK){
            Voucher updatedVoucher = (Voucher) data.getSerializableExtra("voucher");
            if(updatedVoucher != null){
                Voucher existingVoucher = voucherList.stream().filter(voucher1 -> voucher1.id() == updatedVoucher.id()).findFirst().get();
                int index = voucherList.indexOf(existingVoucher);
                voucherList.set(index, updatedVoucher);
                updateVoucherList();
            }
        }
    }
}