package android.serry.task022.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.serry.task022.connection.ApiClient;
import android.serry.task022.connection.Apis;
import android.serry.task022.listeners.OnCityListener;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class City {
    private String Id;
    private String TitleEN;
    private String TitleAR;
    private String CountryId;

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

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public void getCities(Context context, final OnCityListener onCityListener, String countryId) {
        final ProgressDialog pd = ProgressDialog.show(context, null, "Please wait");
        Apis apis = ApiClient.getApiClient().create(Apis.class);
        Call<List<City>> responseCall = apis.getCities(countryId);
        responseCall.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(@NonNull Call<List<City>> call, @NonNull Response<List<City>> response) {
                Log.w("response ", new Gson().toJson(response));
                pd.dismiss();
                if (response.body() != null)
                    onCityListener.onCitySuccessLoaded(response.body());
                else
                    onCityListener.onError();
            }

            @Override
            public void onFailure(@NonNull Call<List<City>> call, @NonNull Throwable t) {
                pd.dismiss();
            }
        });
    }
}
