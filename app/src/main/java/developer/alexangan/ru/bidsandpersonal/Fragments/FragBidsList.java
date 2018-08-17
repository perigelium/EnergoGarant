package developer.alexangan.ru.bidsandpersonal.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import developer.alexangan.ru.bidsandpersonal.Adapters.BidsListAdapter;
import developer.alexangan.ru.bidsandpersonal.Interfaces.BidsCommunicator;
import developer.alexangan.ru.bidsandpersonal.Models.BidInfoItem;
import developer.alexangan.ru.bidsandpersonal.R;

public class FragBidsList extends ListFragment implements View.OnClickListener
{
    private BidsCommunicator mCommunicator;
    private Activity activity;
    List<BidInfoItem> l_LeadsInfoItems;
    private Button btnClients;
    private FrameLayout flNewLead;
    private Button btnLeads;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        mCommunicator = (BidsCommunicator) getActivity();

/*        if (getArguments() != null)
        {
            l_clientLeadsInfoItems = getArguments().getParcelableArrayList("ListClientPraticheInfoItems");
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.frag_bids_list_layout, container, false);

        LinearLayout llReturn = (LinearLayout) rootView.findViewById(R.id.llReturn);
        llReturn.setOnClickListener(this);

        LinearLayout llOpenLeadsSearch = (LinearLayout) rootView.findViewById(R.id.llOpenLeadsSearch);
        llOpenLeadsSearch.setOnClickListener(this);

        btnClients = (Button) rootView.findViewById(R.id.btnClients);
        btnClients.setOnClickListener(this);
        btnLeads = (Button) rootView.findViewById(R.id.btnLeads);
        btnLeads.setOnClickListener(this);


        flNewLead = (FrameLayout) rootView.findViewById(R.id.flNewLead);
        flNewLead.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        l_LeadsInfoItems = new ArrayList<>();
        l_LeadsInfoItems.add(new BidInfoItem(l_LeadsInfoItems.size()+1, "17.11.2017", "15:35", -2));
        l_LeadsInfoItems.add(new BidInfoItem(l_LeadsInfoItems.size()+1, "18.11.2017", "10:05", 1));
        l_LeadsInfoItems.add(new BidInfoItem(l_LeadsInfoItems.size()+1, "19.11.2017", "12:45", 0));

        if (l_LeadsInfoItems != null && l_LeadsInfoItems.size() != 0)
        {
            showLeadsList();
        }
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.llReturn)
        {
            mCommunicator.onClose();
            return;
        }

        if (view.getId() == R.id.btnClients)
        {
            //mCommunicator.popFragmentsBackStack();

            btnClients.setTextColor(activity.getResources().getColor(R.color.white));
            btnClients.setBackground(activity.getResources().getDrawable(R.drawable.button_blue_left));
            btnLeads.setBackground(activity.getResources().getDrawable(R.drawable.button_white_right));
            btnLeads.setTextColor(activity.getResources().getColor(R.color.blueDark));


            return;
        }

        if (view.getId() == R.id.btnLeads)
        {
            //mCommunicator.popFragmentsBackStack();
            btnLeads.setBackground(activity.getResources().getDrawable(R.drawable.button_blue_right));
            btnLeads.setTextColor(activity.getResources().getColor(R.color.white));
            btnClients.setBackground(activity.getResources().getDrawable(R.drawable.button_white_left));
            btnClients.setTextColor(activity.getResources().getColor(R.color.blueDark));
            return;
        }

        if (view.getId() == R.id.flNewLead)
        {
            mCommunicator.openLeadDetails(null, true);
            return;
        }

        if (view.getId() == R.id.llOpenLeadsSearch)
        {
            //mCommunicator.onOpenClientsSearch(FragBidsList.this);
            return;
        }
    }

    private void showLeadsList()
    {
        BidsListAdapter bidsListAdapter = new BidsListAdapter(getActivity(), R.layout.bids_list_row, l_LeadsInfoItems);
        setListAdapter(bidsListAdapter);

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                view.setSelected(true);
                mCommunicator.onClientsLeadsListItemSelected(l_LeadsInfoItems.get(position));
            }
        });
    }
}
