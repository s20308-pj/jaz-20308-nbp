package jaz.pjwstk.jaz20308nbp.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class EntryToDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Number ID", required = true, dataType = "Long")
    private Long id;
    @ApiModelProperty(notes = "Currency name", required = true, dataType = "String")
    private String currency;
    @ApiModelProperty(notes = "Starting Date: format 2020-01-01", required = true, dataType = "String")
    private String startDate;
    @ApiModelProperty(notes = "Ending Date: format 2020-01-01", required = true, dataType = "String")
    private String endDate;
    @ApiModelProperty(notes = "Average prise from number of days", required = true, dataType = "Double")
    private Double averagePrice;
    @ApiModelProperty(notes = "Date and hour when average price was taken", required = true, dataType = "String")
    private final String createdDate = createDate();

    public EntryToDatabase() {

    }

    public EntryToDatabase(String currency,String startDate, String endDate, Double averagePrice) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.averagePrice = averagePrice;
    }

    private String createDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd HH");
        return dateFormat.format(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getCreatedDate() {
        return createdDate;
    }
}
