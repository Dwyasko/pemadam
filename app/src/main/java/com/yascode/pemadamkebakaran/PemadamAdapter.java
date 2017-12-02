package com.yascode.pemadamkebakaran;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by caksono21 on 02/12/17.
 */

public class PemadamAdapter extends RecyclerView.Adapter<PemadamViewHolder> {

    List<Data> Datas;
    ClickHandler clickHandler;

    interface ClickHandler {
        void onClick(Data data);
    }

    public PemadamAdapter(List<Data> datas, ClickHandler clickHandler) {
        Datas = datas;
        this.clickHandler = clickHandler;
    }

    @Override
    public PemadamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);

        return new PemadamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PemadamViewHolder holder, int position) {
        Data data = Datas.get(position);

        bind(holder, data);
    }

    private void bind(PemadamViewHolder holder, final Data data) {
        holder.txtPos.setText(data.getPOS_PEMADAM());
        holder.txtAlamat.setText(data.getALAMAT());

        holder.holderClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickHandler.onClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Datas.size();
    }
}
