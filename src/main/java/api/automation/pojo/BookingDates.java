package api.automation.pojo;

import java.text.SimpleDateFormat;
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

    public String getCheckinString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(checkin);
    }

    public String getCheckoutString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(checkout);
    }
}
