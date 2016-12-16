package android.db.boxme.model;

/**
 * Created by Caritor on 12/16/2016.
 */
public class GitIssueEvent {

    public String url;// "https://api.github.com/repos/cepr/android-serialport-api/issues/1",
    public String repository_url;// "https://api.github.com/repos/cepr/android-serialport-api",
    public String labels_url;// "https://api.github.com/repos/cepr/android-serialport-api/issues/1/labels{/name}",
    public String comments_url;// "https://api.github.com/repos/cepr/android-serialport-api/issues/1/comments",
    public String events_url;// "https://api.github.com/repos/cepr/android-serialport-api/issues/1/events",
    public String html_url;// "https://github.com/cepr/android-serialport-api/issues/1",
    public String id;// 154443490,
    public String number;// 1,
    public String title;// "Received data is encrypted", " +
    public User user;
    public String[] labels;// [],
    public String state;// "closed",
    public boolean locked;// false,
    public String assignee;// null,
    public String[] assignees;// [],
    public String milestone;// null,
    public String comments;// 2,
    public String created_at;// "2016-05-12T09:59:48Z",
    public String updated_at;// "2016-09-21T06:06:18Z",
    public String closed_at;// "2016-09-21T06:06:18Z",
    public String body;// "Hi,\r\nI have implemented this in my app , but when I was reading , the received data was different from what is sent.\r\nThere is no encryption done on sender end , it is just sending as it is ( verified it with windows terminal program )\r\n\r\nHere is the response details\r\n**Sent :** 954605E6\r\n**Received :** ceٲVVM\r\n\r\n**More conversions I tried:**\r\n**Sent:**\r\nHex: 57 53 52 54 48 53 69 54\r\nDecimal: 39 35 34 36 30 35 45 36\r\nBinary : 00111001 00110101 00110100 00110110 00110000 00110101 01000101 00110110\r\n\r\n**Received:**\r\nHex: 99 101 217 178 86 86 77 3\r\nDecimal: 63 65 d9 b2 56 56 4d 03\r\nBinary : 01100011 01100101 11011001 10110010 01010110 01010110 01001101 00000011\r\n\r\nI don't have experience with this kind of communications\r\n\r\nNOTE : Communication is via UART cable , app running in a custom device (HOwen , Hero)\r\n\r\nThank you"
}
