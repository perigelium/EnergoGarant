package developer.alexangan.ru.bidsandpersonal.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import developer.alexangan.ru.bidsandpersonal.Fragments.FragBidDetails;
import developer.alexangan.ru.bidsandpersonal.Fragments.FragBidsList;
import developer.alexangan.ru.bidsandpersonal.Fragments.FragClientsSearch;
import developer.alexangan.ru.bidsandpersonal.Interfaces.BidsCommunicator;
import developer.alexangan.ru.bidsandpersonal.Models.BidInfoItem;
import developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants;
import developer.alexangan.ru.bidsandpersonal.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BidsActivity extends Activity implements BidsCommunicator
{
    private FragmentManager mFragmentManager;

    FragBidsList fragBidsList;
    private Fragment fragLeadDetails;
    private Fragment fragClientsSearch;

    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bids_layout);

        fragBidsList = new FragBidsList();
        fragLeadDetails = new FragBidDetails();
        fragClientsSearch = new FragClientsSearch();

        mFragmentManager = getFragmentManager();

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.add(R.id.BidsFragContainer, fragBidsList);

        mFragmentTransaction.commit();
    }

    @Override
    public void onBackPressed()
    {
        if (mFragmentManager.getBackStackEntryCount() == 0)
        {
            this.finish();
        }
        else
        {
            super.onBackPressed();

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onClose()
    {
        this.finish();
    }

    @Override
    public void openLeadDetails(BidInfoItem bidInfoItem, boolean editable)
    {
        if (!fragLeadDetails.isAdded())
        {
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            Bundle args = fragLeadDetails.getArguments() != null ? fragLeadDetails.getArguments() : new Bundle();

            //args.putParcelable("leadInfoItem", bidInfoItem);
            args.putBoolean("editable", editable);

            fragLeadDetails.setArguments(args);

            mFragmentTransaction.remove(fragBidsList);
            mFragmentTransaction.add(R.id.BidsFragContainer, fragLeadDetails);
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
    }

    @Override
    public void onClientsLeadsListItemSelected(BidInfoItem bidInfoItem)
    {
/*        if (!fragClientsLeadDetails.isAdded())
        {
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            Bundle args = fragClientsLeadDetails.getArguments() != null ? fragClientsLeadDetails.getArguments() : new Bundle();

            args.putParcelable("clientsLeadInfoItem", clientsLeadInfoItem);

            fragClientPracticeDetails.setArguments(args);

            mFragmentTransaction.remove(fragClientsLeadsList);
            mFragmentTransaction.add(R.id.BidsFragContainer, fragClientsLeadDetails);
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }*/
    }

    @Override
    public void popFragmentsBackStack()
    {
        mFragmentManager.popBackStack();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onLogoutCommand()
    {
        GlobalConstants.logoutInProgress = true;
        this.finish();
    }

    @Override
    public void onOpenClientsSearch(Fragment frag)
    {
        if (!fragClientsSearch.isAdded())
        {
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            Bundle args = fragClientsSearch.getArguments() != null ? fragClientsSearch.getArguments() : new Bundle();

            String search_mode = frag.getClass().equals(FragBidsList.class) ? "leads" : "clients";

            args.putString("search_mode", search_mode);

            fragClientsSearch.setArguments(args);

            if(frag.isAdded())
            {
                mFragmentTransaction.remove(frag);
            }

            mFragmentTransaction.add(R.id.clientsFragContainer, fragClientsSearch);
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
    }

 /*   @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.llReturn)
        {
            this.finish();
        }

        if (view.getId() == R.id.flBonus)
        {
            view.setBackgroundColor(Color.parseColor("#ffffffff"));
            ViewUtils.showToastMessage(this, "flBonus");
        }

        if (view.getId() == R.id.flCallToInvoice)
        {
            view.setBackgroundColor(Color.parseColor("#ffffffff"));
            ViewUtils.showToastMessage(this, "flCallToInvoice");
        }

        if (view.getId() == R.id.flRaceRank)
        {
            view.setBackgroundColor(Color.parseColor("#ffffffff"));
            ViewUtils.showToastMessage(this, "flRaceRank");
        }
    }*/

/*    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
        if (view.getId() == R.id.flBonus)
        {
            view.setBackgroundColor(Color.parseColor("#ffcde6f9"));
        }

        if (view.getId() == R.id.flCallToInvoice)
        {
            view.setBackgroundColor(Color.parseColor("#ffcde6f9"));
        }

        if (view.getId() == R.id.flRaceRank)
        {
            view.setBackgroundColor(Color.parseColor("#ffcde6f9"));
        }

        return false;
    }*/
}
