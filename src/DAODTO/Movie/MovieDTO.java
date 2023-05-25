package DAODTO.Movie;

public class MovieDTO {
    private int Movie_Num;
    private String Title;
    private String genre;
    private String director;
    private String Cast;
    private String Running_Time;
    private String Description;
    private String Poster;


    public MovieDTO() {
        super();
    }

    public MovieDTO(int Movie_Num, String Title, String genre, String director, String Cast, String Running_Time, String Description){
        super();
        this.Movie_Num = Movie_Num;
        this.Title = Title;
        this.genre = genre;
        this.director = director;
        this.Cast = Cast;
        this.Running_Time = Running_Time;
        this.Description = Description;
    }

    public int getMovie_Num() {
        return Movie_Num;
    }

    public void setMovie_Num(int movie_Num) {
        Movie_Num = movie_Num;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return Cast;
    }

    public void setCast(String cast) {
        Cast = cast;
    }

    public String getRunning_Time() {
        return Running_Time;
    }

    public void setRunning_Time(String running_Time) {
        Running_Time = running_Time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

}
