package developer.alexangan.ru.bidsandpersonal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import developer.alexangan.ru.bidsandpersonal.Models.WorkerItem;
import developer.alexangan.ru.bidsandpersonal.R;

public class WorkersListAdapter extends BaseAdapter
{
    private Context mContext;
    private ArrayList<WorkerItem> l_workersItems;
    private int layout_id;
    //ViewHolder holder;

    public WorkersListAdapter(Context context, int layout_id, ArrayList<WorkerItem> l_workersItems)
    {
        //super(context, textViewResourceId, objects);
        mContext = context;
        this.l_workersItems = l_workersItems;
        this.layout_id = layout_id;
    }

    @Override
    public int getCount()
    {
        return l_workersItems.size();
    }

    @Override
    public Object getItem(int i)
    {
        return i;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(layout_id, parent, false);


        TextView tvFIO = (TextView) row.findViewById(R.id.tvFIO);

        String strFIO = l_workersItems.get(position).getName() + " " + l_workersItems.get(position).getMiddlename()
        + " "  + l_workersItems.get(position).getSurname();
        tvFIO.setText(strFIO);

        TextView tvWorkerPosition = (TextView) row.findViewById(R.id.tvWorkerPosition);
        tvWorkerPosition.setText(l_workersItems.get(position).getPosition());

        TextView tvSubcontractorMark = (TextView) row.findViewById(R.id.tvSubcontractorMark);

        if(l_workersItems.get(position).isSubcontractor())
        {
            tvSubcontractorMark.setText("субподрядчик");
        }

/*        TextView tvTitleFromMonth = (TextView) row.findViewById(R.id.tvTitleFromMonth);
        TextView tvAmount = (TextView) row.findViewById(R.id.tvAmount);
        TextView tvChecked = (TextView) row.findViewById(R.id.tvChecked);

        String monthPeriod = String.valueOf(l_workersItems.get(position).getPeriod());
        int monthNumber = Integer.valueOf(monthPeriod.substring(4)) - 1;

        if (monthNumber == -1)
        {
            monthNumber = 11;
        }

        String periodDisplayed = "Fattura " + ItalianMonths.numToLongString(monthNumber) + " " + monthPeriod.substring(0, 4);

        tvTitleFromMonth.setText(periodDisplayed);

        String strAmount = String.valueOf(l_workersItems.get(position).getTotal());

        strAmount = MyTextUtils.reformatCurrencyString(strAmount);

        tvAmount.setText(strAmount);

        String strChecked = l_workersItems.get(position).getChecked();
        String strDisplayedChecked = "";

        if (strChecked != null)
        {
            strDisplayedChecked = "CONFERMATO";
        } else
        {
            strDisplayedChecked = "SOSPESO";
        }
        tvChecked.setText(strDisplayedChecked);*/

        return row;
    }
}
