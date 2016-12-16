package android.db.boxme.model;

/**
 * Created by Caritor on 12/15/2016.
 */
public class User {
    private static User mSingleton;


    public static void registerInstance(User user) {
        mSingleton = user;
    }


    public static User getInstance() {

        if(mSingleton == null )
            mSingleton = new User();

        return mSingleton;
    }

    public String login;// "dineshbalu",
    public int id;//7804300,
    public String avatar_url;// "https://avatars.githubusercontent.com/u/7804300?v=3",
    public String gravatar_id;// "",
    public String url;//https://api.github.com/users/dineshbalu",
    public String html_url;// "https://github.com/dineshbalu",
    public String followers_url;// "https://api.github.com/users/dineshbalu/followers",
    public String following_url;// "https://api.github.com/users/dineshbalu/following{/other_user}",
    public String gists_url;// "https://api.github.com/users/dineshbalu/gists{/gist_id}",
    public String starred_url;// "https://api.github.com/users/dineshbalu/starred{/owner}{/repo}",
    public String subscriptions_url;// "https://api.github.com/users/dineshbalu/subscriptions",
    public String organizations_url;// "https://api.github.com/users/dineshbalu/orgs",
    public String repos_url;// "https://api.github.com/users/dineshbalu/repos",
    public String events_url;// "https://api.github.com/users/dineshbalu/events{/privacy}",
    public String received_events_url;// "https://api.github.com/users/dineshbalu/received_events",
    public String type;// "User",
    public String site_admin;// false,
    public String name;// "Dinesh Balu",
    public String company;// null,
    public String blog;// null,
    public String location;// "Bangalore , KA , India",
    public String email;// null,
    public String hireable;//null,
    public String bio;// "passionate about technology ,\r\nlove to code,\r\nofficially an android application developer in a small firm,\r\ndreaming about being part of something cool",
    public String public_repos;// 19,
    public String public_gists;// 0,
    public String followers;// 0,
    public String following;// 0,
    public String created_at;// "2014-06-05T10:44:49Z",
    public String updated_at;// "2016-11-28T07:15:37Z",
    public String private_gists;// 0,
    public String total_private_repos;// 0,
    public String owned_private_repos;// 0,
    public String disk_usage;// 6015,
    public String collaborators;// 0,

    public String getName() {
        return name;
    }

//            "plan": {
//        "name": "free",
//                "space": 976562499,
//                "collaborators": 0,
//                "private_repos": 0
//    }

}
