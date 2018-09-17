package android.serry.task022.views;

import android.serry.task022.models.City;
import android.serry.task022.models.CountryCodeModel;
import android.view.View;

import java.util.List;

public interface RegisterView {
    void initViews(View view);

    void setViews(List<CountryCodeModel> countryCodeModelList);

    void setCitiesView(List<City> cities);

    void refreshApp();
}
