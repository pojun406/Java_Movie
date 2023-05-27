package DAODTO.Member;

public class User {
    private static User instance;
    private int UID;
    private String USER_Name;
    private String USER_CallNum;
    private int USER_Pay;
    private String USER_Watched;

    private User() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getUSER_Name() {
        return USER_Name;
    }

    public void setUSER_Name(String USER_Name) {
        this.USER_Name = USER_Name;
    }

    public String getUSER_CallNum() {
        return USER_CallNum;
    }

    public void setUSER_CallNum(String USER_CallNum) {
        this.USER_CallNum = USER_CallNum;
    }

    public int getUSER_Pay() {
        return USER_Pay;
    }

    public void setUSER_Pay(int USER_Pay) {
        this.USER_Pay = USER_Pay;
    }

    public String getUSER_Watched() {
        return USER_Watched;
    }

    public void setUSER_Watched(String USER_Watched) {
        this.USER_Watched = USER_Watched;
    }
}
