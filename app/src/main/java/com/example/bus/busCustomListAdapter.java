package com.example.bus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class busCustomListAdapter extends ArrayAdapter<busList> {

    Context mCtx;
    int resource;
    List<busList> busLists;

    public busCustomListAdapter(Context mCtx, int resource, List<busList> busLists) {
        super(mCtx, resource, busLists);

        this.mCtx = mCtx;
        this.resource = resource;
        this.busLists = busLists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(resource, null);

        TextView busTypeTxt = view.findViewById(R.id.busTypeTxt);
        TextView busNameTxt = view.findViewById(R.id.busNameTxt);
        TextView busLocationTxt = view.findViewById(R.id.busLocationTxt);
        TextView busSeatTxt = view.findViewById(R.id.busSeatTxt);
        TextView busPriceTxt = view.findViewById(R.id.busPriceTxt);
        TextView busTimeTxt = view.findViewById(R.id.busTimeTxt);
        ImageView busImage = view.findViewById(R.id.busImage);

        busList busList = busLists.get(position);

        busTypeTxt.setText(busList.getBusType());
        busNameTxt.setText(busList.getBusName());
        busLocationTxt.setText(busList.getBusLocation());
        busSeatTxt.setText(busList.getBusSeat());
        busPriceTxt.setText(busList.getBusPrice());
        busTimeTxt.setText(busList.getBusTime());
        busImage.setImageDrawable(mCtx.getResources().getDrawable(busList.getBusImage()));


        return view;
    }
}
