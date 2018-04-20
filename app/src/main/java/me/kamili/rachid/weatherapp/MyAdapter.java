package me.kamili.rachid.weatherapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import me.kamili.rachid.weatherapp.model.ListItem;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> mDataset;

    public MyAdapter(List<ListItem> myDataset) {
        this.mDataset = myDataset;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_item_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem data = mDataset.get(position);

        String temp = (int)((data.getMain().getTemp()*9/5)- 459.67) + "°F";
        String humedity = Double.toString(data.getMain().getHumidity()) + "%";
        String wind = Double.toString(data.getWind().getSpeed()) + " mph";
        String desc = data.getWeather().get(0).getMain();
        String time = data.getDtTxt();

        holder.tvDesc.setText(desc);
        holder.tvHum.setText(humedity);
        holder.tvTemp.setText(temp);
        holder.tvTime.setText(time);
        holder.tvWind.setText(wind);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDesc;
        public TextView tvHum;
        public TextView tvTemp;
        public TextView tvTime;
        public TextView tvWind;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvHum = itemView.findViewById(R.id.tvHum);
            tvTemp = itemView.findViewById(R.id.tvTemp);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvWind = itemView.findViewById(R.id.tvWind);
        }
    }
}
