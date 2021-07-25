package com.doubleview.test;

import com.doubleview.model.Account;
import com.doubleview.model.Service;
import com.doubleview.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.sql.Date;
import java.util.Set;

/**
 * 测试级联操作
 */
public class TestCascade {

    /**
     * 级联添加
     */
    @Test
    public void test1() {
        Session session = HibernateUtil.getSession();
        Account account = getAccount();
        Transaction ts = session.beginTransaction();
        Service service1 = getService1();
        Service service2 = getService2();
        account.getServices().add(service1);
        account.getServices().add(service2);

        session.save(account);
        ts.commit();
        System.out.println("保存成功");

        session.close();
    }

    /**
     * 级联修改
     */
    @Test
    public void test2() {
        Session session = HibernateUtil.getSession();
        Account account = (Account) session.get(Account.class, 2);
        account.setLoginPassword("pp");
        Set<Service> services = account.getServices();
        for (Service service : services) {
            service.setLoginPassword("ppp");
        }

        Transaction ts = session.beginTransaction();
        try {
            session.update(account);
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            ts.rollback();
        }
    }


    /**
     * ]
     * 级联删除
     */
    @Test
    public void test3() {
        Session session = HibernateUtil.getSession();
        Account account = (Account) session.get(Account.class, 3);
        Transaction ts = session.beginTransaction();

        try {
            session.delete(account);
            System.out.println("----------");
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            ts.rollback();
        } finally {
            session.close();
        }
    }


    public Account getAccount() {
        Account account = new Account();
        account.setBirthdate(Date.valueOf("1994-12-2"));
        account.setGender("男");
        account.setEmail("2426775354@qq.com");
        account.setIdcardNo("111111111");
        account.setCloseDate(Date.valueOf("1992-12-12"));
        account.setLastLoginIp("192.168.6.2");
        account.setLoginName("hcc");
        account.setLoginPassword("123456");
        account.setQq("123124234");
        account.setStatus("1");
        return account;
    }

    public Service getService1() {
        Service service1 = new Service();
        service1.setStatus("1");
        service1.setOsUserName("hcc");
        service1.setLoginPassword("123456");
        return service1;
    }


    public Service getService2() {
        Service service2 = new Service();
        service2.setStatus("0");
        service2.setOsUserName("hcc2");
        service2.setLoginPassword("123456");
        return service2;
    }
}
