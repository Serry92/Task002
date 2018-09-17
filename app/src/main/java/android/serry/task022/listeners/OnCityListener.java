package android.serry.task022.listeners;

import android.serry.task022.models.City;

import java.util.List;

public interface OnCityListener {
    void onCitySuccessLoaded(List<City> cities);

    void onError();
}
