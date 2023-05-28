package DAODTO.Booking;

public class Seat {
    private String seatNumber;
    private boolean selected;

    public Seat(String seatNumber, boolean selected) {
        this.seatNumber = seatNumber;
        this.selected = selected;
    }

    public void setSeatNumber(String seatNumber){
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}