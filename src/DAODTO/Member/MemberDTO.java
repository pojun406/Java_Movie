package DAODTO.Member;

public class MemberDTO {
    private String UID;
    private String USER_ID;
    private String USER_PW;
    private String USER_Name;
    private String USER_CallNum;
    private int USER_Pay;
    private String USER_Watched;
    private User user;

    public MemberDTO() {
        super();
    }

    public MemberDTO(String UID, String USER_ID, String USER_PW) {
        super();
        this.UID = UID;
        this.USER_ID = USER_ID;
        this.USER_PW = USER_PW;
        this.user = User.getInstance(); // User 인스턴스 생성
    }


    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_PW() {
        return USER_PW;
    }

    public void setUSER_PW(String USER_PW) {
        this.USER_PW = USER_PW;
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

    public User getUser() {
        return user;
    }

}
