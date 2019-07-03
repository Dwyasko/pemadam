package com.yascode.pemadamkebakaran;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by caksono21 on 02/12/17.
 */

public class PemadamViewHolder extends RecyclerView.ViewHolder {

    TextView txtAlamat, txtPos;
    ImageView imgIcon;
    RelativeLayout holderClick;

    public PemadamViewHolder(View itemView) {
        super(itemView);

        holderClick = itemView.findViewById(R.id.holder_click);
        txtAlamat = itemView.findViewById(R.id.txt_alamat);
        txtPos = itemView.findViewById(R.id.txt_pos);
        imgIcon = itemView.findViewById(R.id.img_icon);
    }
}
