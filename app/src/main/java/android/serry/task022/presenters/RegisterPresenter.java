package android.serry.task022.presenters;

import android.content.Context;
import android.serry.task022.views.RegisterFragment;
import android.view.View;

public interface RegisterPresenter {
    void onCreate(View view);

    void changeLanguage(Context context);

    void loadCodesCountries(Context context);

    void loadCities(Context context, String id);

    boolean isAppLanguageEnglish();
}
