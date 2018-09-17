package android.serry.task022.views;

import android.content.Intent;
import android.os.Bundle;
import android.serry.task022.R;
import android.serry.task022.models.City;
import android.serry.task022.models.CountryCodeModel;
import android.serry.task022.presenters.RegisterPresenterImplementer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisterFragment extends Fragment implements RegisterView, View.OnClickListener {
    private static RegisterFragment instanceRegisterFragment;
    TextView tvChangeLanguage, tvTermsAndConditions;
    Spinner spCodes, spCountries, spCities;
    RegisterPresenterImplementer registerPresenterImplementer;

    public static synchronized RegisterFragment getInstance() {
        if (instanceRegisterFragment == null) {
            instanceRegisterFragment = new RegisterFragment();
        }
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPresenterImplementer = new RegisterPresenterImplementer(getActivity(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        registerPresenterImplementer.onCreate(view);
        registerPresenterImplementer.loadCodesCountries(getActivity());
        return view;

    }

    @Override
    public void initViews(View view) {
        tvChangeLanguage = view.findViewById(R.id.tv_change_lang);
        tvChangeLanguage.setOnClickListener(this);
        spCodes = view.findViewById(R.id.spinner_code);
        spCountries = view.findViewById(R.id.spinner_country);
        spCities = view.findViewById(R.id.spinner_city);
        tvTermsAndConditions = view.findViewById(R.id.tv_terms);
        tvTermsAndConditions.setOnClickListener(this);
    }

    @Override
    public void setViews(final List<CountryCodeModel> countryCodeModelList) {
        List<String> listCodes = new ArrayList<>();
        List<String> listCountries = new ArrayList<>();
        for (CountryCodeModel countryCodeModel : countryCodeModelList) {
            listCodes.add(countryCodeModel.getCode());
            if (registerPresenterImplementer.isAppLanguageEnglish())
                listCountries.add(countryCodeModel.getTitleEN());
            else
                listCountries.add(countryCodeModel.getTitleAR());
        }
        ArrayAdapter<String> dataAdapterCodes = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                android.R.layout.simple_spinner_item, listCodes);
        dataAdapterCodes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCodes.setAdapter(dataAdapterCodes);

        ArrayAdapter<String> dataAdapterCountries = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listCountries);
        dataAdapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountries.setAdapter(dataAdapterCountries);
        spCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                registerPresenterImplementer.loadCities(getActivity(), countryCodeModelList.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setCitiesView(List<City> cities) {
        List<String> listCities = new ArrayList<>();
        for (City city : cities) {
            if (registerPresenterImplementer.isAppLanguageEnglish())
                listCities.add(city.getTitleEN());
            else
                listCities.add(city.getTitleAR());
        }
        ArrayAdapter<String> dataAdapterCodes = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                android.R.layout.simple_spinner_item, listCities);
        dataAdapterCodes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCities.setAdapter(dataAdapterCodes);
    }

    @Override
    public void refreshApp() {
        Intent refresh = new Intent(getActivity(), MainActivity.class);
        startActivity(refresh);
        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change_lang:
                registerPresenterImplementer.changeLanguage(getActivity());
                break;
            case R.id.tv_terms:
                FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, TermsFragment.getInstance());
                ft.addToBackStack("categories");
                ft.commit();
                break;
        }
    }

}
