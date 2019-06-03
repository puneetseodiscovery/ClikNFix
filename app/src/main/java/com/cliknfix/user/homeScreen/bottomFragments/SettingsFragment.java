package com.cliknfix.user.homeScreen.bottomFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.aboutUs.AboutUsActivity;
import com.cliknfix.user.base.MyApp;
import com.cliknfix.user.changePassword.ChangePasswordActivity;
import com.cliknfix.user.contact.ContactUsActivity;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.IPSettingsFragment;
import com.cliknfix.user.homeScreen.bottomFragments.presenter.PSettingsFragment;
import com.cliknfix.user.login.LoginActivity;
import com.cliknfix.user.paymentMethods.PaymentMethodsActivity;
import com.cliknfix.user.privacyPolicy.PrivacyPolicyActivity;
import com.cliknfix.user.responseModels.LogoutResponseModel;
import com.cliknfix.user.util.PreferenceHandler;
import com.cliknfix.user.util.Utility;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cliknfix.user.base.BaseClass.mGoogleSignInClient;

public class SettingsFragment extends Fragment implements View.OnClickListener,ISettingsFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;
    @BindView(R.id.tv_profile_settings)
    TextView tvProfileSettings;
    @BindView(R.id.tv_payments)
    TextView tvPayments;
    @BindView(R.id.tv_contact_us)
    TextView tvContactUs;
    @BindView(R.id.tv_privacy_policy)
    TextView tvPrivacyPolicy;
    @BindView(R.id.tv_change_password)
    TextView tvChangePassword;
    @BindView(R.id.tv_logout)
    TextView tvLogout;

    Context context;
    IPSettingsFragment ipSettingsFragment;
    ProgressDialog progressDialog;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View view =inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,view);
        context = getContext();
        ipSettingsFragment = new PSettingsFragment(this);
        init();
        return view;
    }

    public void init(){
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvAboutUs.setTypeface(Utility.typeFaceForText(getContext()));
        tvProfileSettings.setTypeface(Utility.typeFaceForText(getContext()));
        tvPayments.setTypeface(Utility.typeFaceForText(getContext()));
        tvContactUs.setTypeface(Utility.typeFaceForText(getContext()));
        tvPrivacyPolicy.setTypeface(Utility.typeFaceForText(getContext()));
        tvChangePassword.setTypeface(Utility.typeFaceForText(getContext()));
        tvLogout.setTypeface(Utility.typeFaceForText(getContext()));

        tvAboutUs.setOnClickListener(this);
        tvProfileSettings.setOnClickListener(this);
        tvPayments.setOnClickListener(this);
        tvContactUs.setOnClickListener(this);
        tvPrivacyPolicy.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tv_about_us:
                startActivity(new Intent((HomeScreenActivity)context, AboutUsActivity.class));
                break;
            case R.id.tv_profile_settings:
                FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
                UserProfileFragment fragment = new UserProfileFragment();
                transaction.replace(R.id.frame_container, fragment);
                transaction.commit();
                break;
            case R.id.tv_payments:
                startActivity(new Intent((HomeScreenActivity)context, PaymentMethodsActivity.class));
                break;
            case R.id.tv_contact_us:
                startActivity(new Intent((HomeScreenActivity)context, ContactUsActivity.class));
                break;
            case R.id.tv_privacy_policy:
                startActivity(new Intent((HomeScreenActivity)context, PrivacyPolicyActivity.class));
                break;
            case R.id.tv_change_password:
                startActivity(new Intent((HomeScreenActivity)context, ChangePasswordActivity.class));
                break;
            case R.id.tv_logout:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Logout");
                alertDialogBuilder
                    .setMessage("" +
                            "\nAre you sure you want to logout?")
                    .setCancelable(false)
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            progressDialog = Utility.showLoader(context);
                            ipSettingsFragment.doLogout(Utility.getUserId());
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                break;

        }

    }

    private void googlePlusSignOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener((HomeScreenActivity)getContext(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        //Toast.makeText((HomeScreenActivity)getContext(), "Signed out", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void facebookSignOut() {
        LoginManager.getInstance().logOut();
    }

    @Override
    public void logoutSuccessFromPresenter(LogoutResponseModel logoutResponseModel) {
        progressDialog.dismiss();
        facebookSignOut();
        googlePlusSignOut();
        new PreferenceHandler().clearSavedPrefrences(MyApp.getInstance().getApplicationContext());
        Intent intent = new Intent(context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void logoutFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}
