package android.serry.task022.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import java.util.Locale;

public class Constants {

    public static final String URL_BASE = "http://souq.hardtask.co/app/app.asmx/";
    public static final String GET_URL_COUNTRIES_CODES = URL_BASE + "GetCountries";
    public static final String GET_URL_CITIES = URL_BASE + "GetCities";

    private static final String APP_LANGUAGE = "language";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Constants(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        editor.apply();
    }

    public void setAppLanguage(String language) {
        editor.putString(APP_LANGUAGE, language);
        editor.apply();
    }

    public String getAppLanguage() {
        return preferences.getString(APP_LANGUAGE, "en");
    }

    public void setAppDefaultLanguage() {
        if (getAppLanguage() != null) {
            Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration newConfig = new Configuration(res.getConfiguration());
            Locale locale = new Locale(getAppLanguage());
            Locale.setDefault(locale);
            newConfig.locale = locale;
            newConfig.setLocale(locale);
            newConfig.setLayoutDirection(locale);
            res.updateConfiguration(newConfig, dm);
        } else if (Locale.getDefault().getLanguage().equals("ar")) {
            setAppLanguage("ar");
        } else {
            setAppLanguage("en");
        }
    }
}