package com.example.travel.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "adult_number")
    private Integer adultNumber;

    @Column(name = "children_number")
    private Integer childrenNumber;

    @Column(name = "total_money")
    private Double totalMoney;

    /**
     * 0余额不足等待付款1交易成功2已退款3门票已过期无法退款4购物车内未付款
     */
    @Column(name = "order_state")
    private Short orderState;

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
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return ticket_id
     */
    public Long getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId
     */
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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
     * @return total_money
     */
    public Double getTotalMoney() {
        return totalMoney;
    }

    /**
     * @param totalMoney
     */
    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 获取0未付款1交易成功2已退款3门票已过期无法退款
     *
     * @return order_state - 0未付款1交易成功2已退款3门票已过期无法退款
     */
    public Short getOrderState() {
        return orderState;
    }

    /**
     * 设置0未付款1交易成功2已退款3门票已过期无法退款
     *
     * @param orderState 0未付款1交易成功2已退款3门票已过期无法退款
     */
    public void setOrderState(Short orderState) {
        this.orderState = orderState;
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