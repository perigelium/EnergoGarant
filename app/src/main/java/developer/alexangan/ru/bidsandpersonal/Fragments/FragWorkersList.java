package developer.alexangan.ru.bidsandpersonal.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import developer.alexangan.ru.bidsandpersonal.Adapters.WorkersListAdapter;
import developer.alexangan.ru.bidsandpersonal.Interfaces.StaffCommunicator;
import developer.alexangan.ru.bidsandpersonal.Models.WorkerItem;
import developer.alexangan.ru.bidsandpersonal.R;
import developer.alexangan.ru.bidsandpersonal.Utils.ViewUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragWorkersList extends ListFragment implements View.OnClickListener, Callback<List<WorkerItem>>
{
    private StaffCommunicator mCommunicator;
    private Activity activity;
    /*    private Handler handler;
        private Runnable runnable;*/
    //private Call callGetWorkerList;
    private ProgressDialog requestServerDialog;
    AlertDialog alert;
    private int WorkerItemId;
    private WorkerItem WorkerItem;
    private ProgressDialog downloadingDialog;
    private ArrayList<WorkerItem> l_workersItems;
    private Button btnShowWorkersList;
    private Button btnShowWorkersMap;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        mCommunicator = (StaffCommunicator) getActivity();
        l_workersItems = new ArrayList<>();

        if (getArguments() != null)
        {
            WorkerItemId = getArguments().getInt("id");
        }

        downloadingDialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
        downloadingDialog.setTitle("");
        downloadingDialog.setMessage(getString(R.string.DownloadingDataPleaseWait));
        downloadingDialog.setIndeterminate(true);

        l_workersItems = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.frag_workers_list, container, false);

        LinearLayout llReturn = (LinearLayout) rootView.findViewById(R.id.llReturn);
        llReturn.setOnClickListener(this);

        btnShowWorkersList = (Button) rootView.findViewById(R.id.btnShowWorkersList);
        btnShowWorkersList.setOnClickListener(this);

        btnShowWorkersMap = (Button) rootView.findViewById(R.id.btnShowWorkersMap);
        btnShowWorkersMap.setOnClickListener(this);

        TextView tvPreviousPageTitle = (TextView) rootView.findViewById(R.id.tvPreviousPageTitle);
        //tvPreviousPageTitle.setText(R.string.News);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        l_workersItems = new ArrayList<>();
        l_workersItems.add(new WorkerItem(l_workersItems.size()+1, "Иванов", "Владимир", "Васильевич", "прораб", false));
        l_workersItems.add(new WorkerItem(l_workersItems.size()+1, "Петров", "Иван", "Григорьевич", "разнорабочий", false));
        l_workersItems.add(new WorkerItem(l_workersItems.size()+1, "Сидоров", "Илья", "Федорович", "маляр", true));

        if (l_workersItems != null && l_workersItems.size() != 0)
        {
            showWorkersList();
        }

/*        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        RealmResults<WorkerItem> rrWorkerItems = realm.where(WorkerItem.class).findAll();
        realm.commitTransaction();

        realm.beginTransaction();
        for (WorkerItem WorkerItem : rrWorkerItems)
        {
            WorkerItem WorkerItemEx = realm.copyFromRealm(WorkerItem);
            l_workersItems.add(WorkerItemEx);
        }
        realm.commitTransaction();
        realm.close();

        //WorkerItem = l_workersItems.get(WorkerItemId);
        //final int id_Worker = WorkerItem.getId_Worker();

        if (NetworkUtils.isNetworkAvailable(activity))
        {
            if (tokenStr == null)
            {
                alertDialog("Info", getString(R.string.OfflineModeShowLoginScreenQuestion));
            } else
            {
                l_workersItems.clear();
                updateWorkerListView();

                downloadingDialog.show();

*//*                NetworkUtils networkUtils = new NetworkUtils();
                callGetWorkerList = networkUtils.getWorkerDetailed(this, API_GETDETAILEDWorker_URL, tokenStr, id_Worker);*//*

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        RetrofitAPI retrofitService = RetrofitAPI.retrofit.create(RetrofitAPI.class);

                        Call<List<WorkerItem>> callWorkerList =
                                retrofitService.getWorkersList(GlobalConstants.tokenStr);

                        callWorkerList.enqueue(FragWorkersList.this);
                    }
                }, 100);
            }
        }*/
    }

    private void showWorkersList()
    {
        WorkersListAdapter bidsListAdapter = new WorkersListAdapter(getActivity(), R.layout.worker_row, l_workersItems);
        setListAdapter(bidsListAdapter);

        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                view.setSelected(true);
                mCommunicator.onStaffListItemSelected(l_workersItems.get(position));
            }
        });
    }

    @Override
    public void onResponse(@NonNull Call<List<WorkerItem>> call, @NonNull Response<List<WorkerItem>> response)
    {
        if (response.isSuccessful())
        {
            l_workersItems = (ArrayList)response.body();

            downloadingDialog.dismiss();

            if (l_workersItems.size() != 0)
            {
                updateWorkerListView();

                return;
            }
        }
        else
        {
            //int statusCode = response.code();
            ResponseBody errorBody = response.errorBody();

            try
            {
                Log.d("DEBUG", errorBody.string());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        downloadingDialog.dismiss();

        ViewUtils.showToastMessage(activity, getString(R.string.ServerError));

        mCommunicator.popFragmentsBackStack();
    }

    @Override
    public void onFailure(@NonNull Call<List<WorkerItem>> call, @NonNull Throwable t)
    {
        downloadingDialog.dismiss();

        Log.d("DEBUG", t.getMessage());

        ViewUtils.showToastMessage(activity, getString(R.string.ServerAnswerNotReceived));

        mCommunicator.popFragmentsBackStack();
    }

    @Override
    public View getView()
    {
        return super.getView();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        //searchViewReports.setQuery("", false);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.llReturn)
        {
            mCommunicator.onClose();
        }

        if (view.getId() == R.id.btnShowWorkersMap)
        {
            mCommunicator.openWorkersMap(FragWorkersList.this);
        }
    }

    private void updateWorkerListView()
    {
        WorkersListAdapter WorkerListAdapter = new WorkersListAdapter(getActivity(), R.layout.worker_row, l_workersItems);
        setListAdapter(WorkerListAdapter);
    }

    private void alertDialog(String title, String message)
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
}
