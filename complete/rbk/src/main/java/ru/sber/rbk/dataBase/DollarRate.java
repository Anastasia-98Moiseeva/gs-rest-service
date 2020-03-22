package ru.sber.rbk.dataBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class DollarRate {

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Double rate;
    private String date;

    protected DollarRate() {}

    public DollarRate(Double rate, Date date) {
        this.rate = rate;
        this.date = DATE_FORMAT.format(date);
    }

    public static String getDateFormat(Date date) {
        return DATE_FORMAT.format(date);
    }

    public Double getRate() {
        return rate;
    }

    public String getDate() {
        return date;
    }
}
