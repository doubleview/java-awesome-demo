package com.doubleview.test;

import com.doubleview.model.Account;
import com.doubleview.model.Service;
import com.doubleview.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;


import java.util.Set;

/**
 * Created by ���ɳ� on 2016/7/5.
 */
public class TestOneToMany {


    @Test
    public void test1() {
        Session session = HibernateUtil.getSession();
        Account account = (Account) session.get(Account.class, 2);

        System.out.println(account);

        Set<Service> services = account.getServices();
        System.out.println(services.getClass().getName());
        for (Service service : services) {
            System.out.println(service.getOsUserName());
        }

        session.close();
    }


}
