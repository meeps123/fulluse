package com.fulluse;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Al Cheong on 8/12/2017.
 */

public class ImmutableEventDataAdapter extends ArrayAdapter<EventData> {

    private Context context;
    private List<EventData> eventData;
    private int CHARACTER_LIMIT = 14;

    public ImmutableEventDataAdapter(Context context, int resource, List<EventData> objects) {
        super(context, resource, objects);

        this.context = context;
        this.eventData = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("EVENTS", "adapter: " + String.valueOf(eventData.size()));
        EventData currentEvent = eventData.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_immutable_event, null);
        } else {
            view = convertView;
        }
        TextView eventNameTextView = (TextView) view.findViewById(R.id.tv_eventName);
        eventNameTextView.setText(currentEvent.getEventName());
        eventNameTextView.setTypeface(Typeface.DEFAULT_BOLD);
        if (currentEvent.getEventName().length() > CHARACTER_LIMIT) {
            String newName = currentEvent.getEventName().substring(0, Math.min(currentEvent.getEventName().length(), CHARACTER_LIMIT));
            newName = newName + "...";
            currentEvent.setEventTruncatedName(newName);
            eventNameTextView.setText(newName);
        }
        TextView eventTimingTextView = (TextView) view.findViewById(R.id.tv_eventTiming);
        eventTimingTextView.setText(currentEvent.getEventStartTiming() + " - " + currentEvent.getEventEndTiming());

        TextView eventStartDateTextView = (TextView) view.findViewById(R.id.tv_eventStartDate);
        eventStartDateTextView.setText(currentEvent.getEventStartDate());

        TextView eventEndDateTextView = (TextView) view.findViewById(R.id.tv_eventEndDate);
        eventEndDateTextView.setText("to " + currentEvent.getEventEndDate());

        return view;
    }

}
