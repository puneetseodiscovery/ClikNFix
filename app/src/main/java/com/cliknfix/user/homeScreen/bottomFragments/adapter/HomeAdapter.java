package com.cliknfix.user.homeScreen.bottomFragments.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cliknfix.user.R;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.responseModels.CategoriesListResponseModel;
import com.cliknfix.user.submitProblem.SubmitProblemFragmentt;
import com.cliknfix.user.util.Utility;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    List<CategoriesListResponseModel.Datum> list;

    public HomeAdapter(Context context, List<CategoriesListResponseModel.Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.get().load(list.get(position).getServiceImage()).into(holder.ivCat);
       // holder.ivCat.setImageResource(list.get(position).getServiceImage());
        holder.tvCatName.setText(list.get(position).getServiceName());
        holder.llCatItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            loadFragment(list.get(position).getServiceName(),list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_cat_view_item)
        LinearLayout llCatItem;
        @BindView(R.id.iv_category)
        ImageView ivCat;
        @BindView(R.id.iv_next)
        ImageView ivNext;
        @BindView(R.id.tv_category)
        TextView tvCatName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            init();
        }

        public void init() {
            tvCatName.setTypeface(Utility.typeFaceForSemiBoldText(context));
        }
    }

    public void loadFragment(String category,int categoryId) {
        FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
        SubmitProblemFragmentt fragment = new SubmitProblemFragmentt();
        Bundle args = new Bundle();
        args.putString("category", category);
        args.putInt("categoryId", categoryId);
        fragment.setArguments(args);
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
