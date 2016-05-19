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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Event;
import model.Event3;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {


    public static class EventViewHolder extends RecyclerView.ViewHolder {

        CardView eventCV;
        TextView title;
        TextView eventDesc;
        TextView eventDate;
        TextView eventTime;
        ImageView eventImage;

        TextView eventLocation;

        EventViewHolder(View itemView) {
            super(itemView);
            eventCV = (CardView)itemView.findViewById(R.id.cv_event);
            title = (TextView)itemView.findViewById(R.id.EventTitle);
            eventDesc = (TextView)itemView.findViewById(R.id.EventDescription);
            eventDate = (TextView)itemView.findViewById(R.id.EventDate);
            eventTime = (TextView)itemView.findViewById(R.id.EventTime);
            eventImage = (ImageView) itemView.findViewById(R.id.randomPicture);
            eventLocation = (TextView)itemView.findViewById(R.id.EventLocation);





        }
    }
   public List<Event> event3s;

    public EventAdapter(List<Event> event3s){
        this.event3s = event3s;
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


        eventViewHolder.title.setText(event3s.get(i).getTitle());
      //  eventViewHolder.eventDesc.setText(event3s.get(i).getDescription());

        Date date=new Date(event3s.get(i).getDate());

        SimpleDateFormat year = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat month = new SimpleDateFormat("dd-MM");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        String dateText = year.format(date);
        String timeText = time.format(date);
        eventViewHolder.eventDate.setText(dateText +" \u2022 "+ timeText);
       // eventViewHolder.eventTime.setText(timeText);
      //  eventViewHolder.eventLocation.setText(event3s.get(i).getLocation());
       eventViewHolder.eventImage.setBackgroundResource(event3s.get(i).getPictureTemp());


    }

    @Override
    public int getItemCount() {
        return event3s.size();
    }
}