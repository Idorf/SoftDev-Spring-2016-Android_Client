package model;

/**
 * Created by Idorf on 17/04/2016.
 */

public class Event {
   public String venue;
    public String eventDesc;
    public int eventPhotoId;
    public String eventDistance;

   public Event(String venue, String eventDesc, String eventDistance, int eventPhotoId ) {
        this.venue = venue;
        this.eventDesc = eventDesc;
        this.eventPhotoId = eventPhotoId;
        this.eventDesc = eventDesc;
    }
}