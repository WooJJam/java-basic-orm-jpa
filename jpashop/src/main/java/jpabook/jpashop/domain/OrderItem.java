package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private Long id;
    @Column(name = "ORDER_ID")
    private Long orderID;

    @Column(name = "ITEM_ID")
    private Long itemID;

    private int orderPrice;
    private int count;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderID() {
        return orderID;
    }

    public Long getItemID() {
        return itemID;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public int getCount() {
        return count;
    }
}
