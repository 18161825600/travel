package com.example.travel.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scenic_spot_id")
    private Long scenicSpotId;

    @Column(name = "ticket_name")
    private String ticketName;

    @Column(name = "ticket_describe")
    private String ticketDescribe;

    @Column(name = "adult_ticket_price")
    private Double adultTicketPrice;

    @Column(name = "children_ticket_price")
    private Double childrenTicketPrice;

    @Column(name = "adult_number")
    private Integer adultNumber;

    @Column(name = "children_number")
    private Integer childrenNumber;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return scenic_spot_id
     */
    public Long getScenicSpotId() {
        return scenicSpotId;
    }

    /**
     * @param scenicSpotId
     */
    public void setScenicSpotId(Long scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    /**
     * @return ticket_name
     */
    public String getTicketName() {
        return ticketName;
    }

    /**
     * @param ticketName
     */
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName == null ? null : ticketName.trim();
    }

    /**
     * @return ticket_describe
     */
    public String getTicketDescribe() {
        return ticketDescribe;
    }

    /**
     * @param ticketDescribe
     */
    public void setTicketDescribe(String ticketDescribe) {
        this.ticketDescribe = ticketDescribe == null ? null : ticketDescribe.trim();
    }

    /**
     * @return adult_ticket_price
     */
    public Double getAdultTicketPrice() {
        return adultTicketPrice;
    }

    /**
     * @param adultTicketPrice
     */
    public void setAdultTicketPrice(Double adultTicketPrice) {
        this.adultTicketPrice = adultTicketPrice;
    }

    /**
     * @return children_ticket_price
     */
    public Double getChildrenTicketPrice() {
        return childrenTicketPrice;
    }

    /**
     * @param childrenTicketPrice
     */
    public void setChildrenTicketPrice(Double childrenTicketPrice) {
        this.childrenTicketPrice = childrenTicketPrice;
    }

    /**
     * @return adult_number
     */
    public Integer getAdultNumber() {
        return adultNumber;
    }

    /**
     * @param adultNumber
     */
    public void setAdultNumber(Integer adultNumber) {
        this.adultNumber = adultNumber;
    }

    /**
     * @return children_number
     */
    public Integer getChildrenNumber() {
        return childrenNumber;
    }

    /**
     * @param childrenNumber
     */
    public void setChildrenNumber(Integer childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}