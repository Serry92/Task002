package android.serry.task022.connection;

import android.serry.task022.models.City;
import android.serry.task022.models.CountryCodeModel;
import android.serry.task022.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Apis {
    @GET(Constants.GET_URL_COUNTRIES_CODES)
    Call<List<CountryCodeModel>> getCountriesCodes();

    @GET(Constants.GET_URL_CITIES)
    Call<List<City>> getCities(@Query("countryId") String countryId);
}
