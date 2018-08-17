package developer.alexangan.ru.bidsandpersonal.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import developer.alexangan.ru.bidsandpersonal.Interfaces.ClientsCommunicator;
import developer.alexangan.ru.bidsandpersonal.R;

import static developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants.mSettings;

public class FragClientsMapLegendAndFilter extends Fragment implements View.OnClickListener
{

    private ClientsCommunicator mCommunicator;
    Activity activity;
    private boolean gpsIsActive;
    private CheckBox chkClientsDeact;
    private TextView tvSelectMapFiltersAllNone;
    private boolean checkAll;
    private TextView tvLegendAndFilterTitle;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        mCommunicator = (ClientsCommunicator) getActivity();

        if (getArguments() != null)
        {
            gpsIsActive = getArguments().getBoolean("gpsIsActive");
        }

        checkAll = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.frag_clients_map_legend_and_filter_layout, container, false);

        LinearLayout llReturn = (LinearLayout) rootView.findViewById(R.id.llReturn);
        llReturn.setOnClickListener(this);

        tvLegendAndFilterTitle = (TextView) rootView.findViewById(R.id.tvLegendAndFilterTitle);

        tvSelectMapFiltersAllNone = (TextView) rootView.findViewById(R.id.tvSelectMapFiltersAllNone);
        tvSelectMapFiltersAllNone.setOnClickListener(this);

        Button btnApplyMapFilter = (Button) rootView.findViewById(R.id.btnApplyMapFilter);
        btnApplyMapFilter.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mSettings.edit().putBoolean("mapFiltersChanged", false).apply();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.llReturn)
        {
            mCommunicator.popFragmentsBackStack();
            return;
        }

        if (view.getId() == R.id.tvSelectMapFiltersAllNone)
        {
            checkAll = !checkAll;

            if ( ! checkAll)
            {
                tvSelectMapFiltersAllNone.setText("Все");
            } else
            {
                tvSelectMapFiltersAllNone.setText("Ничего");
            }

            chkClientsDeact.setChecked(checkAll);


            return;
        }

        if (view.getId() == R.id.btnApplyMapFilter)
        {
            //mSettings.edit().putBoolean("clientiDeactDisabled", !chkClientsDeact.isChecked()).commit();

            mSettings.edit().putBoolean("mapFiltersChanged", true).commit();

            mCommunicator.popFragmentsBackStack();
            return;
        }
    }
}
