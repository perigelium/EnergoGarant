package developer.alexangan.ru.bidsandpersonal.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import developer.alexangan.ru.bidsandpersonal.Fragments.FragStaffMap;
import developer.alexangan.ru.bidsandpersonal.Fragments.FragWorkerDetails;
import developer.alexangan.ru.bidsandpersonal.Fragments.FragWorkersList;
import developer.alexangan.ru.bidsandpersonal.Interfaces.StaffCommunicator;
import developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants;
import developer.alexangan.ru.bidsandpersonal.Models.WorkerItem;
import developer.alexangan.ru.bidsandpersonal.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants.mSettings;

public class StaffActivity extends Activity implements StaffCommunicator, View.OnClickListener
{
    private FragmentManager mFragmentManager;
    private FragWorkersList fragWorkersList;
    private FragStaffMap fragStaffMap;
    private FragWorkerDetails fragWorkerDetails;

    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_layout);

        fragWorkersList = new FragWorkersList();
        fragWorkerDetails = new FragWorkerDetails();
        fragStaffMap = new FragStaffMap();

        mSettings.edit().putString("StaffSearchLastQueryString", "").commit();

        mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.add(R.id.staffFragContainer, fragWorkersList);
        //mFragmentTransaction.addToBackStack(null);

        mFragmentTransaction.commit();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.llReturn)
        {
            this.finish();
        }
    }

    @Override
    public void onStaffListItemSelected(WorkerItem workerItem)
    {
        if (! fragWorkerDetails.isAdded())
        {
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            Bundle args = new Bundle();
            //args.putInt("id", position);
            fragWorkerDetails.setArguments(args);

            mFragmentTransaction.remove(fragWorkersList);
            mFragmentTransaction.add(R.id.staffFragContainer, fragWorkerDetails);
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
    }

    @Override
    public void popFragmentsBackStack()
    {
        mFragmentManager.popBackStack();
    }

    @Override
    public void openWorkersMap(Fragment frag)
    {
        if (!fragStaffMap.isAdded())
        {
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            //Bundle args = fragStaffMap.getArguments() != null ? fragStaffMap.getArguments() : new Bundle();

            //args.putBoolean("gpsIsActive", gpsIsActive);

            //fragStaffMap.setArguments(args);

            mFragmentTransaction.remove(frag);
            mFragmentTransaction.add(R.id.staffFragContainer, fragStaffMap);
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
    }

    @Override
    public void openWorkersList(Fragment frag)
    {
        if (!fragWorkersList.isAdded())
        {
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            ///Bundle args = fragStaffMap.getArguments() != null ? fragWorkersList.getArguments() : new Bundle();

            //args.putBoolean("gpsIsActive", gpsIsActive);

            //fragWorkersList.setArguments(args);

            mFragmentTransaction.remove(frag);
            mFragmentTransaction.add(R.id.staffFragContainer, fragWorkersList);
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
    }


    @Override
    public void onLogoutCommand()
    {
        GlobalConstants.logoutInProgress = true;
        this.finish();
    }

    @Override
    public void onBackPressed()
    {
        mSettings.edit().putString("StaffSearchLastQueryString", "").commit();

        if(mFragmentManager.getBackStackEntryCount() == 0)
        {
            this.finish();
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void onClose()
    {
        this.finish();
    }

}
