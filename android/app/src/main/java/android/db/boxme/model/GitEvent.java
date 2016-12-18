package android.db.boxme.model;

import android.db.boxme.utils.GitConstant;

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


    public String getMessage( ){

        String msg = "";

        if( type.equalsIgnoreCase( GitConstant.EVENT_CREATE ) ) {
            msg = String.format("Created a new %s '%s'",payload.ref_type,payload.ref);
        }
        else if( type.equalsIgnoreCase( GitConstant.EVENT_DELETE )) {
            msg = String.format("Deleted a %s '%s'",payload.ref_type,payload.ref);
        }
        else if( type.equalsIgnoreCase( GitConstant.EVENT_FORK )) {
            msg = String.format("Forked a repository '%s'",repo.name);
        }
        else if( type.equalsIgnoreCase( GitConstant.EVENT_PUSH )) {
            msg = String.format("Pushed %d commit(s) to '%s'\n%s",payload.size,payload.ref,payload.head);
        }
        else{
            msg = String.format("#%s, Event not recognized",type);
        }

        return msg;
    }

    public String getRepositoryName( ){


        if( type.equalsIgnoreCase( GitConstant.EVENT_FORK ) ) {
            return payload.forkee.full_name;
        }

        return repo.name;
    }
}
