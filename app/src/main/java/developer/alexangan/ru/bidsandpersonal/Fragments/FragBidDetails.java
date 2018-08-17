package developer.alexangan.ru.bidsandpersonal.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import developer.alexangan.ru.bidsandpersonal.Interfaces.BidsCommunicator;
import developer.alexangan.ru.bidsandpersonal.Models.BidInfoItem;
import developer.alexangan.ru.bidsandpersonal.R;

public class FragBidDetails extends Fragment implements View.OnClickListener
{
    private BidsCommunicator mCommunicator;
    private Activity activity;
    BidInfoItem bidInfoItem;
    private boolean editable;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        mCommunicator = (BidsCommunicator) getActivity();

        if (getArguments() != null)
        {
            bidInfoItem = getArguments().getParcelable("leadInfoItem");
            editable = getArguments().getBoolean("editable");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.bid_materials, container, false);

        LinearLayout llReturn = (LinearLayout) rootView.findViewById(R.id.llReturn);
        llReturn.setOnClickListener(this);

/*        TextView tvDateInserted = (TextView) rootView.findViewById(R.id.tvDateInserted);
        tvDateInserted.setText(clientPraticheInfoItem.getDate_insert());

        TextView tvDateCreated = (TextView) rootView.findViewById(R.id.tvDateCreated);
        tvDateCreated.setText(clientPraticheInfoItem.getData_creazione());

        TextView tvProductType = (TextView) rootView.findViewById(R.id.tvProductType);
        tvProductType.setText(clientPraticheInfoItem.getProduct_type());

        TextView tvProduct = (TextView) rootView.findViewById(R.id.tvProduct);
        tvProduct.setText(clientPraticheInfoItem.getProduct());

        TextView tvProductQuantity = (TextView) rootView.findViewById(R.id.tvProductQuantity);
        tvProductQuantity.setText(clientPraticheInfoItem.getQuantity());

        TextView tvProductQuantityActive = (TextView) rootView.findViewById(R.id.tvProductQuantityActive);

        String quantity_active = clientPraticheInfoItem.getQuantity_active();

        if (quantity_active == null)
        {
            quantity_active = "0";
        }

        tvProductQuantityActive.setText(quantity_active);

        TextView tvSerial = (TextView) rootView.findViewById(R.id.tvSerial);
        tvSerial.setText(clientPraticheInfoItem.getSerial());

        TextView tvLines = (TextView) rootView.findViewById(R.id.tvLines);
        tvLines.setText(clientPraticheInfoItem.getLinee());

        TextView tvDateActivated = (TextView) rootView.findViewById(R.id.tvDateActivated);
        tvDateActivated.setText(clientPraticheInfoItem.getForce_date_active());

        TextView tvRoute = (TextView) rootView.findViewById(R.id.tvRoute);
        tvRoute.setText(clientPraticheInfoItem.getRouting());

        TextView tvNote = (TextView) rootView.findViewById(R.id.tvNote);
        tvNote.setText(clientPraticheInfoItem.getNotes());*/

        return rootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.llReturn)
        {
            mCommunicator.popFragmentsBackStack();
        }
    }
}
