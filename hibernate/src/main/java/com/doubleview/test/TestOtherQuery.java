package com.doubleview.test;

import com.doubleview.model.Service;
import com.doubleview.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

/**
 * 其他查询方式
 */
public class TestOtherQuery {


    /**
     * 使用SQL查询
     */
    @Test
    public void test1() {
        String sql = "select * from service where unix_host = ?";
        Session session = HibernateUtil.getSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.setString(0, "222.222.222.222");
        query.addEntity(Service.class);
        List<Service> list = query.list();
        for (Service service : list) {
            System.out.println(service);
        }
        session.close();
    }
}
