package MainPage.LoginPage;

public class MemberDTO {
    private int UID;
    private String USER_ID;
    private String USER_PW;
    private String USER_Name;
    private String USER_CallNum;
    private String USER_Pay;
    private String USER_Watched;

    public MemberDTO(){
        super();
    }

    public MemberDTO(int UID, String USER_ID, String USER_PW, String USER_Name, String USER_CallNum, String USER_Pay, String USER_Watched){
        super();
        this.UID = UID;
        this.USER_ID = USER_ID;
        this.USER_PW = USER_PW;
        this.USER_Name = USER_Name;
        this.USER_CallNum = USER_CallNum;
        this.USER_Pay = USER_Pay;
        this.USER_Watched = USER_Watched;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
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

    public String getUSER_Pay() {
        return USER_Pay;
    }

    public void setUSER_Pay(String USER_Pay) {
        this.USER_Pay = USER_Pay;
    }

    public String getUSER_Watched() {
        return USER_Watched;
    }

    public void setUSER_Watched(String USER_Watched) {
        this.USER_Watched = USER_Watched;
    }

}
