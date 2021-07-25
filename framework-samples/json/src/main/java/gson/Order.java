package gson;

import java.util.Date;

/**
 * Created by doubleview on 2017/5/21.
 */
public class Order {

    private  Integer id;

    private  String no;

    private Date orderTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}
