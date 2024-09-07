package it.jgiem.myapps.voucher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.jgiem.myapps.R;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    private List<Voucher> voucherList = new ArrayList<>();
    private OnVoucherClickListener listener;

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_voucher, null, false);
        return new VoucherViewHolder(view);
    }

    public void setVoucherList(List<Voucher> voucherList) {
        this.voucherList = voucherList;
        this.notifyDataSetChanged();
    }

    public void setOnClickListener(OnVoucherClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        Voucher voucher = voucherList.get(position);
        holder.tvTitle.setText(voucher.title());
        holder.tvQuantity.setText("" + voucher.quantity());
        holder.tvAmount.setText("" + voucher.amount());
        holder.itemView.setOnClickListener(v->{
            listener.onVoucherClicked(voucher);
        });
        holder.itemView.setOnLongClickListener(v->{
            listener.onDeleteVoucher(voucher);
            return true;
        });
    }

    @Override
    public int getItemCount() {

        return voucherList.size();
    }


    class VoucherViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvQuantity, tvAmount;

        public VoucherViewHolder(View itemView){
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            tvAmount = itemView.findViewById(R.id.tv_amount);
        }

    }
}
