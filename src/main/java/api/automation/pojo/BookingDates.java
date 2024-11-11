package api.automation.pojo;

import java.util.Date;

public class BookingDates {
    private Date checkin;
    private Date checkout;

    public BookingDates(Date checkin,Date checkout){
        this.checkin = checkin;
        this.checkout = checkout;

    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }
}
