package com.yascode.pemadamkebakaran;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by caksono21 on 02/12/17.
 */

public class PemadamAdapter extends RecyclerView.Adapter<PemadamViewHolder> {

    List<Data> Datas;
    ClickHandler clickHandler;

    Context mContext;

    String gambar1 = "http://www.beritajakarta.com/multimedia/photo/2014_508c75c8507a2ae5223dfd2faeb98122/91b46d69df71e4849f7f9f46f8612cbb.jpg";
    String gambar2 = "http://poskotanews.com/cms/wp-content/uploads/2015/05/mobilpemadam.jpg";

    interface ClickHandler {
        void onClick(Data data);
    }

    public PemadamAdapter(List<Data> datas, ClickHandler clickHandler, Context context) {
        mContext = context;
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
        String image = "";

        //jika posisi angka genap kita tampilkan gambar2 dan sebaliknya
        if (position % 2 == 0) {
            image = gambar2;
        } else {
            image = gambar1;
        }

        //menampilkan image yang diambil dari internet dengan Picasso. (Simple)^^
        Picasso.with(mContext).load(image).into(holder.imgIcon);

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
