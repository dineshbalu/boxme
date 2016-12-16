package android.db.boxme.model;

/**
 * Created by Caritor on 12/16/2016.
 */
public class GitEventPayload {
    public long push_id;// 1452732603,
    public int size;// 1" +
    public int distinct_size;// 1, " +
    public String action;// "started"
    public String ref;// "refs/heads/master",
    public String head;// "c1fe4df8b895557e1068693924186284558e0043",
    public String before;// "cd0620a503c44770ebf8c39612197eec95d14145",
    public String ref_type;// "branch",
    public String master_branch;// "master",
    public String description;// "Thank you for applying to Android Developer position at Boxme.  Just to have a sense of your technical expertise and the quality of your work, we would like you to complete a simple assignment initially. Post which we will schedule a meeting to review the same with you. Please find the details of the assignment below.   Objective:  Develop an Android application that consumes the GitHub REST API.  Requirements:  The application should have the following features: A landing screen that displays a form to input a GitHub username.  A home screen that displays the above user's GitHub activities as feeds/cards similar to Facebook.  Display the associated repository details for the above event in a new activity upon tapping the feeds.  Use animated transitions similar to the attached GIF image (https://www.dropbox.com/s/06rf5h8rsgg6bn0/preview.gif?dl=0), while navigating between activities.  Should you have any other queries regarding the assignment, feel free to reach out.  Thanks and Regards,  Akhil Mohanan Founder  M: 9611699488  E: akhil@goboxme.com",
    public String pusher_type;// "user"
    public GitCommit[] commits;
    public GitForkee forkee;
    public GitIssueEvent issue;
    public GitIssueComment comment;
}
