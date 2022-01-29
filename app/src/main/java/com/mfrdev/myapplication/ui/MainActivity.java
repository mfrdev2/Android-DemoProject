package com.mfrdev.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mfrdev.myapplication.R;
import com.mfrdev.myapplication.databinding.MainActivityDataBinding;
import com.mfrdev.myapplication.model.Cloth;
import com.mfrdev.myapplication.model.GlossaryRequestBody;
import com.mfrdev.myapplication.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private MainActivityDataBinding binding;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        progressDialog = getProgressDialog(this,"Please Wait...");
        binding.setViewModel(viewModel);
        binding.btn.setOnClickListener(v->{
       //     collectInput();
            viewModel.sendClothRequest();
        });

        setUpInsertRequestObserver();
        setClothResponse();

    }

    private void collectInput() {
        String isAvailable = viewModel.getIsAvailable();
        GlossaryRequestBody requestBody = new GlossaryRequestBody(
                Integer.valueOf(viewModel.getV_id()),
                (isAvailable.equals("true"))?true:false,
                viewModel.getGname(),
                viewModel.getGdesc(),
                viewModel.getGqty(),
                viewModel.getGprice()
        );
        viewModel.sendInsertRequest(requestBody);
    }

    private void setUpInsertRequestObserver() {
        viewModel.getGlossaryInsertResponse().removeObservers(this);
        viewModel.getGlossaryInsertResponse().observe(this, response -> {
            if (response != null) {
                switch (response.status) {
                    case LOADING:
                        progressDialog.show();
                        break;
                    case SUCCESS:
                        progressDialog.dismiss();

                        break;
                    case ERROR:
                        progressDialog.dismiss();
                        break;
                }
            }
        });
    }


    private void setClothResponse() {
        viewModel.getGetCloth().removeObservers(this);
        viewModel.getGetCloth().observe(this, response -> {
            if (response != null) {
                switch (response.status) {
                    case LOADING:
                        progressDialog.show();
                        break;
                    case SUCCESS:
                        progressDialog.dismiss();
                        List<Cloth> data = response.data;
                        Log.i("Cloth",""+data);
                        Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR:
                        progressDialog.dismiss();
                        break;
                }
            }
        });
    }

    public static ProgressDialog getProgressDialog(Context context, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}