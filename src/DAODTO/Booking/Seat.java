package DAODTO.Booking;

public class Seat {
    private String seatNumber;
    private boolean selected;
    private boolean isReserved; // 추가된 변수


    public Seat(String seatNumber, boolean selected) {
        this.seatNumber = seatNumber;
        this.selected = selected;
    }
    public Seat(String seatNumber, boolean selected, boolean isReserved) {
        this.seatNumber = seatNumber;
        this.selected = selected;
        this.isReserved = isReserved;
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