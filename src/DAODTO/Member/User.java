package DAODTO.Member;

public class User {
    private static User instance;
    private String UID;
    private String User_ID;
    private String User_PW;
    private String User_Name;
    private String User_CallNum;
    private int User_Pay;
    private String User_Watch_list;

    private User(){
        super();
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public static void setInstance(User instance) {
        User.instance = instance;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getUser_PW() {
        return User_PW;
    }

    public void setUser_PW(String user_PW) {
        User_PW = user_PW;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_CallNum() {
        return User_CallNum;
    }

    public void setUser_CallNum(String user_CallNum) {
        User_CallNum = user_CallNum;
    }

    public int getUser_Pay() {
        return User_Pay;
    }

    public void setUser_Pay(int user_Pay) {
        User_Pay = user_Pay;
    }

    public String getUser_Watch_list() {
        return User_Watch_list;
    }

    public void setUser_Watch_list(String user_Watch_list) {
        User_Watch_list = user_Watch_list;
    }
}
