package developer.alexangan.ru.bidsandpersonal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import developer.alexangan.ru.bidsandpersonal.Models.BidInfoItem;
import developer.alexangan.ru.bidsandpersonal.R;

public class BidsListAdapter extends BaseAdapter
{
    private Context mContext;
    private List<BidInfoItem> l_BidsListItems;
    private int layout_id;
    //ViewHolder holder;

    public BidsListAdapter(Context context, int layout_id, List<BidInfoItem> l_BidsListItems)
    {
        //super(context, textViewResourceId, objects);
        mContext = context;
        this.l_BidsListItems = l_BidsListItems;
        this.layout_id = layout_id;
    }

    @Override
    public int getCount()
    {
        return l_BidsListItems.size();
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

        TextView tvBidSerialNumber = (TextView) row.findViewById(R.id.tvBidSerialNumber);
        ImageView ivBidStatus = (ImageView) row.findViewById(R.id.ivBidStatus);
        TextView tvBidStatus = (TextView) row.findViewById(R.id.tvBidStatus);
        TextView tvBidDay = (TextView) row.findViewById(R.id.tvBidDDMMYYYY);
        TextView tvBidTime = (TextView) row.findViewById(R.id.tvBidTime);

        int status = l_BidsListItems.get(position).getStatus();
        String strStatus = "Заявка " + String.valueOf(l_BidsListItems.get(position).getSerialNumber());

        tvBidSerialNumber.setText(strStatus);
        tvBidDay.setText(l_BidsListItems.get(position).getYYMMDDDD());
        tvBidTime.setText(l_BidsListItems.get(position).getHHMM());


        if (status == -2)
        {
            ivBidStatus.setBackgroundResource(R.drawable.red_oval_shape);
            tvBidStatus.setText("Отказано");
        } else if(status == 1)
        {
            ivBidStatus.setBackgroundResource(R.drawable.green_oval_shape);
            tvBidStatus.setText("Утверждено");
        }
        else if(status == 0)
        {
            ivBidStatus.setBackgroundResource(R.drawable.yellow_oval_shape);
            tvBidStatus.setText("На рассмотрении");
        }

        return row;
    }


}
