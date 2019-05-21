package com.cliknfix.user.homeScreen.bottomFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.MyApp;
import com.cliknfix.user.homeScreen.bottomFragments.adapter.PastJobsAdapter;
import com.cliknfix.user.homeScreen.bottomFragments.model.BeanPastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.IPPastJobsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.PPastJobsFragment;
import com.cliknfix.user.responseModels.PastJobsResponseModel;
import com.cliknfix.user.util.PreferenceHandler;
import com.cliknfix.user.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastJobsFragment extends Fragment implements IPastJobsFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_past_jobs)
    RecyclerView rvPastJobs;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    ArrayList<BeanPastJobsFragment> pastJobsArrayList ;

    Context context;

    IPPastJobsFragment ipPastJobsFragment;
    ProgressDialog progressDialog;

    public PastJobsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastJobsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastJobsFragment newInstance(String param1, String param2) {
        PastJobsFragment fragment = new PastJobsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_past_jobs, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        ipPastJobsFragment = new PPastJobsFragment(this);
        init();
        return view;
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(context));
        pastJobsArrayList=new ArrayList<>();

       /* pastJobsArrayList.add(new BeanPastJobsFragment("Closed","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobsFragment("Open","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobsFragment("Pending","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobsFragment("Hold","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobsFragment("Active","Carpentry","19-March-2019",R.drawable.login_logo));


        rvPastJobs.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));
        PastJobsAdapter adapter = new PastJobsAdapter(context, pastJobsArrayList);
        rvPastJobs.setNestedScrollingEnabled(false);
        rvPastJobs.setAdapter(adapter);*/

        getPastJobsList();

    }

    private void getPastJobsList() {
        String token = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
        Log.e("token:++++++","" + token);
        progressDialog = Utility.showLoader(getContext());
        ipPastJobsFragment.getPastJobsList(Utility.getToken());
    }

    @Override
    public void getPastJobsListFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getPastJobsListSuccessFromPresenter(PastJobsResponseModel pastJobsResponseModel) {
        progressDialog.dismiss();
        tvNoData.setVisibility(View.GONE);
        rvPastJobs.setVisibility(View.VISIBLE);
        rvPastJobs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        PastJobsAdapter adapter = new PastJobsAdapter(context, pastJobsResponseModel.getData());
        rvPastJobs.setNestedScrollingEnabled(false);
        rvPastJobs.setAdapter(adapter);
    }

    @Override
    public void getPastJobsListNoDataFromPresenter(String msgg) {
        progressDialog.dismiss();
        tvNoData.setText(msgg);
        tvNoData.setVisibility(View.VISIBLE);
        rvPastJobs.setVisibility(View.GONE);
    }
}
