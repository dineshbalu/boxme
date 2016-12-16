package android.db.boxme.model;

/**
 * Created by Caritor on 12/16/2016.
 */
public class GitIssueComment {

    public String url;//"https://api.github.com/repos/cepr/android-serialport-api/issues/comments/248520070",
    public String html_url;// "https://github.com/cepr/android-serialport-api/issues/1#issuecomment-248520070",
    public String issue_url;// "https://api.github.com/repos/cepr/android-serialport-api/issues/1",
    public long id;// 248520070,
    public User user;
    public String created_at;// "2016-09-21T06:06:18Z",
    public String updated_at;// "2016-09-21T06:06:18Z",
    public String body;// "@specialshoot , It is fixed with change in different hardware connector ( cable ) , I don't remember what is it exactly. Will have to check with the hardware team. There is nothing wrong with the code though.\r\n\r\nSry about late response , didn't notice.\r\n\r\nThanks"
}
