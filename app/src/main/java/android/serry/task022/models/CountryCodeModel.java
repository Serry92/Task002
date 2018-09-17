package android.serry.task022.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.serry.task022.connection.ApiClient;
import android.serry.task022.connection.Apis;
import android.serry.task022.listeners.OnCountryCodeListener;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryCodeModel {
    private String Id;
    private String TitleEN;
    private String TitleAR;
    private String CurrencyId;
    private String CurrencyEN;
    private String CurrencyAR;
    private String CodeEN;
    private String CodeAR;
    private String Code;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitleEN() {
        return TitleEN;
    }

    public void setTitleEN(String titleEN) {
        TitleEN = titleEN;
    }

    public String getTitleAR() {
        return TitleAR;
    }

    public void setTitleAR(String titleAR) {
        TitleAR = titleAR;
    }

    public String getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(String currencyId) {
        CurrencyId = currencyId;
    }

    public String getCurrencyEN() {
        return CurrencyEN;
    }

    public void setCurrencyEN(String currencyEN) {
        CurrencyEN = currencyEN;
    }

    public String getCurrencyAR() {
        return CurrencyAR;
    }

    public void setCurrencyAR(String currencyAR) {
        CurrencyAR = currencyAR;
    }

    public String getCodeEN() {
        return CodeEN;
    }

    public void setCodeEN(String codeEN) {
        CodeEN = codeEN;
    }

    public String getCodeAR() {
        return CodeAR;
    }

    public void setCodeAR(String codeAR) {
        CodeAR = codeAR;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void getCountriesCodes(final OnCountryCodeListener onCountryCodeListener, Context context) {
        final ProgressDialog pd = ProgressDialog.show(context, null, "Please wait");
        Apis apis = ApiClient.getApiClient().create(Apis.class);
        Call<List<CountryCodeModel>> responseCall = apis.getCountriesCodes();
        responseCall.enqueue(new Callback<List<CountryCodeModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CountryCodeModel>> call, @NonNull Response<List<CountryCodeModel>> response) {
                Log.w("response ", new Gson().toJson(response));

                pd.dismiss();
                if (response.body() != null)
                    onCountryCodeListener.onSuccess(response.body());
                else
                    onCountryCodeListener.onError();
            }

            @Override
            public void onFailure(@NonNull Call<List<CountryCodeModel>> call, @NonNull Throwable t) {
                pd.dismiss();
            }
        });
    }


}

