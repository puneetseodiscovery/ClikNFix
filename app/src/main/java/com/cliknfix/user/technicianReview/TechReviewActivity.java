package com.cliknfix.user.technicianReview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.congratulations.CongratulationsActivity;
import com.cliknfix.user.responseModels.SubmitTechReviewResponseModel;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TechReviewActivity extends BaseClass implements ITechReviewActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_technician_name)
    TextView tvTechName;
    @BindView(R.id.rating)
    RatingBar ratingBar;
    @BindView(R.id.et_review)
    EditText etReview;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    ProgressDialog progressDialog;
    IPTechReviewActivity ipTechReviewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_review);
        ButterKnife.bind(this);
        init();
        ipTechReviewActivity = new PTechReviewActivity(this);
    }

    public void onSubmitClicked(View view) {

        progressDialog = Utility.showLoader(this);
        ipTechReviewActivity.submitTechReview(String.valueOf(ratingBar.getNumStars()),etReview.getText().toString().trim(),28,Utility.getToken());
    }

    public void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvTechName.setTypeface(Utility.typeFaceForBoldText(this));
        btnSubmit.setTypeface(Utility.typeFaceForBoldText(this));
    }

    @Override
    public void submitTechReviewSuccessResponseFromPresenter(SubmitTechReviewResponseModel submitTechReviewResponseModel) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + submitTechReviewResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, CongratulationsActivity.class));
    }

    @Override
    public void submitTechReviewFailureResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }
}
