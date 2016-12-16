package android.db.boxme.model;

/**
 * Created by Caritor on 12/16/2016.
 */
public class GitEvent {

    public long id;// "5011240131",
    public String type;// "PushEvent",
    public GitEventActor actor;//
    public GitEventRepo repo;
    public GitEventPayload payload;
//    public boolean public;// true,
    public String created_at;// "2016-12-13T06:50:59Z"

}
