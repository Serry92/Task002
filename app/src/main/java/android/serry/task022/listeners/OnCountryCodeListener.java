package android.serry.task022.listeners;

import android.serry.task022.models.CountryCodeModel;

import java.util.List;

public interface OnCountryCodeListener {
    void onSuccess(List<CountryCodeModel> countryCodeModelList);

    void onError();

}
