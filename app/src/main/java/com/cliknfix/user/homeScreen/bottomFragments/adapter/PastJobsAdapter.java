package com.cliknfix.user.homeScreen.bottomFragments.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.homeScreen.bottomFragments.model.BeanPastJobsFragment;
import com.cliknfix.user.responseModels.PastJobsResponseModel;
import com.cliknfix.user.technicianDetail.PastTechDetailActivity;
import com.cliknfix.user.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastJobsAdapter extends RecyclerView.Adapter<PastJobsAdapter.viewHolder>{

    Context context;
    List<PastJobsResponseModel.Datum> list;

    public PastJobsAdapter(Context context, List<PastJobsResponseModel.Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_past_jobs,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        //holder.ivTechImg.setImageResource(list.get(position).getTechImg());
        //holder.tvStatus.setText(list.get(position).getStatus());
//        holder.tvDate.setText(list.get(position).getDate());
//        holder.tvCategory.setText(list.get(position).getCategory());
//        holder.btnMoreDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent((HomeScreenActivity)context, PastTechDetailActivity.class));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_past_jobs_view_item)
        LinearLayout llPastJobsItam;
        /*@BindView(R.id.iv_tech_img)
        ImageView ivTechImg;*/
        @BindView(R.id.tv_status_text)
        TextView tvStatusText;
        @BindView(R.id.tv_technician_text)
        TextView tvTechText;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.btn_more_details)
        Button btnMoreDetails;
        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            init();
        }

        public void init(){
            tvTechText.setTypeface(Utility.typeFaceForText(context));
            tvStatusText.setTypeface(Utility.typeFaceForText(context));
            btnMoreDetails.setTypeface(Utility.typeFaceForText(context));
        }
    }

}
