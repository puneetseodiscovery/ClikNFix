package com.cliknfix.homeScreen.bottomFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cliknfix.R;
import com.cliknfix.homeScreen.bottomFragments.adapter.HomeAdapter;
import com.cliknfix.homeScreen.bottomFragments.model.BeanHomeFragment;
import com.cliknfix.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    ArrayList<BeanHomeFragment> categoryArrayList ;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    Context context;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        init();
        return view;
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        categoryArrayList=new ArrayList<>();

        categoryArrayList.add(new BeanHomeFragment(R.mipmap.carpentry_icon,"Carpentry"));
        categoryArrayList.add(new BeanHomeFragment(R.mipmap.computer_repair_icon,"Computer Repair"));
        categoryArrayList.add(new BeanHomeFragment(R.mipmap.electrical_icon,"Electrical"));
        categoryArrayList.add(new BeanHomeFragment(R.mipmap.plumbing,"Plumbing"));
        categoryArrayList.add(new BeanHomeFragment(R.mipmap.painting_icon,"Painting"));


        rvCategory.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));
        HomeAdapter adapter = new HomeAdapter(context, categoryArrayList);
        rvCategory.setNestedScrollingEnabled(false);
        rvCategory.setAdapter(adapter);
    }
}
