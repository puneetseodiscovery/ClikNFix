package com.cliknfix.user.homeScreen.bottomFragments.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
import com.cliknfix.user.homeScreen.bottomFragments.IPastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.model.BeanPastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.IPPastJobsAdapter;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.IPPastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.PPastJobsAdapter;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.PPastJobsFragment;
import com.cliknfix.user.responseModels.PastJobsResponseModel;
import com.cliknfix.user.responseModels.TechDetailResponseModel;
import com.cliknfix.user.technicianDetail.PastTechDetailActivity;
import com.cliknfix.user.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastJobsAdapter extends RecyclerView.Adapter<PastJobsAdapter.viewHolder> implements IPastJobsAdapter {

    Context context;
    List<PastJobsResponseModel.Datum> list;

    IPPastJobsAdapter ipPastJobsAdapter;
    ProgressDialog progressDialog;
    String jobDate,jobType,payableAmt;

    public PastJobsAdapter(Context context, List<PastJobsResponseModel.Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_past_jobs,parent,false);
        ipPastJobsAdapter = new PPastJobsAdapter(this);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        holder.tvTechText.setText(list.get(position).getName());
        holder.tvDate.setText(list.get(position).getCreatedAt());
        holder.tvCategory.setText(list.get(position).getCategory());
        holder.btnMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = Utility.showLoader(context);
                ipPastJobsAdapter.getTechDetail(list.get(position).getId(),Utility.getToken());
                jobDate = list.get(position).getCreatedAt();
                jobType = list.get(position).getCategory();
                payableAmt = list.get(position).getServicePrice();
            }
        });
        //holder.ivTechImg.setImageResource(list.get(position).getTechImg());
        //holder.tvStatus.setText(list.get(position).getStatus());
//        holder.tvDate.setText(list.get(position).getDate());
//        holder.tvCategory.setText(list.get(position).getCategory());
/*//        holder.btnMoreDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent((HomeScreenActivity)context, PastTechDetailActivity.class));
//            }
//        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void getTechDetailSuccessFromPresenter(TechDetailResponseModel techDetailResponseModel) {
        progressDialog.dismiss();
        Intent intent = new Intent((HomeScreenActivity)context, PastTechDetailActivity.class);
        intent.putExtra("username",techDetailResponseModel.getData().get(0).getName());
        intent.putExtra("email",techDetailResponseModel.getData().get(0).getEmail());
        intent.putExtra("phone",techDetailResponseModel.getData().get(0).getPhone());
        intent.putExtra("jobType",jobType);
        intent.putExtra("jobDate",jobDate);
        intent.putExtra("payableAmt",payableAmt);
        intent.putExtra("address",techDetailResponseModel.getData().get(0).getAddress());
        context.startActivity(intent);
        //context.startActivity(new Intent((HomeScreenActivity)context, PastTechDetailActivity.class));
    }

    @Override
    public void getTechDetailFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
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
