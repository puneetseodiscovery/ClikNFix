package com.cliknfix.submitProblem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.R;
import com.cliknfix.homeScreen.HomeScreenActivity;
import com.cliknfix.search.SearchActivity;
import com.cliknfix.signUp.SignUpActivity;
import com.cliknfix.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubmitProblemFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    RelativeLayout ivBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    Context context;

    public static SubmitProblemFragment newInstance(String param1, String param2) {
        SubmitProblemFragment fragment = new SubmitProblemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SubmitProblemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submit_problem, container, false);
        ButterKnife.bind(this, view);
        this.context = getActivity();
        init();
        return view;
    }

    public void init() {
        ivBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        tvTitle.setText(getArguments().getString("category"));
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        btnSubmit.setTypeface(Utility.typeFaceForBoldText(getContext()));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.iv_back:
                ((HomeScreenActivity) getActivity()).getSupportFragmentManager().popBackStack(null, ((HomeScreenActivity) getActivity()).getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                break;
            case R.id.btn_submit:
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("category", getArguments().getString("category"));
                context.startActivity(intent);
                //startActivity(new Intent(getContext(), SearchActivity.class));
                break;
        }
    }
}
