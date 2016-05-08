package adapter;

/**
 * Created by Idorf on 17/04/2016.
 */
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.idorf.materialdesign2.R;

import java.util.List;

import model.Event;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        CardView eventCV;
        TextView venue;
        TextView eventDesc;
        TextView eventDistance;
        ImageView eventPhotoId;

        EventViewHolder(View itemView) {
            super(itemView);
            eventCV = (CardView)itemView.findViewById(R.id.cv);
            venue = (TextView)itemView.findViewById(R.id.venue);
            eventDesc = (TextView)itemView.findViewById(R.id.event_desc);
            eventDistance = (TextView)itemView.findViewById(R.id.event_distance);
            eventPhotoId = (ImageView)itemView.findViewById(R.id.event_photo);
        }
    }
   public List<Event> events;

    public EventAdapter(List<Event> events){
        this.events = events;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.eventitem, viewGroup, false);
        EventViewHolder pvh = new EventViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(EventViewHolder eventViewHolder, int i) {

        eventViewHolder.venue.setText(events.get(i).venue);
        eventViewHolder.eventDesc.setText(events.get(i).eventDesc);
        eventViewHolder.eventDistance.setText(events.get(i).eventDistance);

        eventViewHolder.eventPhotoId.setImageResource(events.get(i).eventPhotoId);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}