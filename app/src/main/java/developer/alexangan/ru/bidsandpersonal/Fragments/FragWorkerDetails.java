package developer.alexangan.ru.bidsandpersonal.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import developer.alexangan.ru.bidsandpersonal.Interfaces.StaffCommunicator;
import developer.alexangan.ru.bidsandpersonal.Models.ClientInfoItem;
import developer.alexangan.ru.bidsandpersonal.R;
import developer.alexangan.ru.bidsandpersonal.Utils.ViewUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants.mSettings;

public class FragWorkerDetails extends Fragment implements View.OnClickListener, View.OnTouchListener, Callback
{
    private StaffCommunicator mCommunicator;
    Activity activity;
    private ClientInfoItem clientInfoItem;
    private TextView tvAddress;
    private TextView tvPhone;
    private TextView tvMail;
    private TextView tvMobilePhone;
    private ProgressDialog requestServerDialog;
    AlertDialog alert;
    private ImageView ivAttachmentIcon;
    private String strPhone;
    private String strMobilePhone;
    private String strMail;
    private TextView tvReturnPageTitle;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        mCommunicator = (StaffCommunicator) getActivity();

        if (getArguments() != null)
        {
            clientInfoItem = getArguments().getParcelable("clientInfoItem");
        }

        requestServerDialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
        requestServerDialog.setTitle("");
        requestServerDialog.setMessage(getString(R.string.DownloadingDataPleaseWait));
        requestServerDialog.setIndeterminate(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.worker_layout, container, false);

        LinearLayout llReturn = (LinearLayout) rootView.findViewById(R.id.llReturn);
        llReturn.setOnClickListener(this);

        tvReturnPageTitle = (TextView) rootView.findViewById(R.id.tvReturnPageTitle);

        ivAttachmentIcon = (ImageView) rootView.findViewById(R.id.ivAttachmentIcon);

        tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);
        tvMail = (TextView) rootView.findViewById(R.id.tvMail);
        tvMail.setOnClickListener(this);
        tvPhone = (TextView) rootView.findViewById(R.id.tvPhone);
        tvPhone.setOnClickListener(this);
        tvMobilePhone = (TextView) rootView.findViewById(R.id.tvMobilePhone);
        tvMobilePhone.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        boolean searchMode = mSettings.getBoolean("SearchMode", false);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

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

        if (view.getId() == R.id.tvPhone)
        {
            String phoneNumber = "tel:" + strPhone;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(phoneNumber));
            startActivity(intent);
        }

        if (view.getId() == R.id.tvMobilePhone)
        {
            String phoneNumber = "tel:" + strMobilePhone;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(phoneNumber));
            startActivity(intent);
        }

        if (view.getId() == R.id.tvMail)
        {
            String [] recipients = new String[1];
            recipients[0] = strMail;

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, recipients);
            i.putExtra(Intent.EXTRA_SUBJECT, "Тема письма");
            i.putExtra(Intent.EXTRA_TEXT, "Содержимое письма");

            try
            {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex)
            {
                ViewUtils.showToastMessage(activity, getString(R.string.NoMailClientInstalled));
            }
        }
    }

    private void alertDialogRelogin(String title, String message)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);

        builder.setTitle(title)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                mCommunicator.onLogoutCommand();
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                alert.dismiss();
                            }
                        });

        alert = builder.create();

        alert.show();
    }

    @Override
    public void onFailure(Call call, IOException e)
    {

            requestServerDialog.dismiss();

            ViewUtils.showToastMessage(activity, getString(R.string.ServerAnswerNotReceived));

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException
    {

    }

    @Override
    public boolean onTouch(View view, MotionEvent event)
    {

        return false;
    }
}
