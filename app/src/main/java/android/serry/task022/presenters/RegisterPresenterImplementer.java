package android.serry.task022.presenters;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.serry.task022.listeners.OnCityListener;
import android.serry.task022.listeners.OnCountryCodeListener;
import android.serry.task022.models.City;
import android.serry.task022.models.CountryCodeModel;
import android.serry.task022.utilities.Constants;
import android.serry.task022.views.RegisterFragment;
import android.serry.task022.views.RegisterView;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.List;
import java.util.Locale;

public class RegisterPresenterImplementer implements RegisterPresenter, OnCountryCodeListener, OnCityListener {
    private RegisterView registerView;
    private Constants constants;

    public RegisterPresenterImplementer(Context context, RegisterView registerView) {
        this.registerView = registerView;
        constants = new Constants(context);
        constants.setAppDefaultLanguage();
    }

    @Override
    public void onCreate(View view) {
        registerView.initViews(view);
    }

    @Override
    public void changeLanguage(Context context) {
        if (constants.getAppLanguage() != null) {
            Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration newConfig = new Configuration(res.getConfiguration());
            Locale locale;
            if (constants.getAppLanguage().equals("ar")) {
                locale = new Locale("en");
                constants.setAppLanguage("en");
            } else {
                locale = new Locale("ar");
                constants.setAppLanguage("ar");
            }
            Locale.setDefault(locale);
            newConfig.locale = locale;
            newConfig.setLocale(locale);
            newConfig.setLayoutDirection(locale);
            res.updateConfiguration(newConfig, dm);
            registerView.refreshApp();

        }
    }

    @Override
    public void loadCodesCountries(Context context) {
        CountryCodeModel countryCodeModel = new CountryCodeModel();
        countryCodeModel.getCountriesCodes(this, context);
    }

    @Override
    public void loadCities(Context context, String id) {
        City city = new City();
        city.getCities(context, this, id);
    }

    @Override
    public boolean isAppLanguageEnglish() {
        return !constants.getAppLanguage().equals("ar");
    }

    @Override
    public void onSuccess(List<CountryCodeModel> countryCodeModelList) {
        registerView.setViews(countryCodeModelList);

    }

    @Override
    public void onCitySuccessLoaded(List<City> cities) {
        registerView.setCitiesView(cities);
    }

    @Override
    public void onError() {

    }
}
