package com.doubleview.test;

import com.doubleview.model.Service;
import com.doubleview.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 * 测试多对一关联
 */
public class TestManyToOne {


    @Test
    public void test1() {
        Session session = HibernateUtil.getSession();
        Service service = (Service) session.get(Service.class, 1);
        System.out.println(service);
        System.out.println("---------");
        System.out.println(service.getAccount());
    }

}