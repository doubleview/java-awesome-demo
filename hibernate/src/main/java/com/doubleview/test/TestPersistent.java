package com.doubleview.test;

import com.doubleview.model.Emp;
import com.doubleview.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 测试对象持久化
 */
public class TestPersistent {

    /**
     * 持久化对象存在于一级缓存中
     */
    @Test
    public void test1() {
        Emp e = new Emp();
        e.setName("唐僧");
        e.setAge(29);
        e.setMarry(false);
        e.setSalary(12000.0);
        e.setBirthday(Date.valueOf("1983-10-20"));
        e.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        Session session = HibernateUtil.getSession();
        Transaction ts = session.beginTransaction();
        try {
            session.save(e);
            ts.commit();
        } catch (HibernateException e1) {
            e1.printStackTrace();
            ts.rollback();
        }
        Emp emp = (Emp) session.get(Emp.class, e.getId());
        System.out.println(emp);
        session.close();
    }


    /**
     * 持久化对象可以自动更新到数据库
     */
    @Test
    public void test2() {
        Emp e = new Emp();
        e.setName("孙悟空");
        e.setAge(29);
        e.setMarry(false);
        e.setBirthday(Date.valueOf("1943-12-12"));
        e.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        Session session = HibernateUtil.getSession();
        Transaction ts = session.beginTransaction();
        try {
            session.save(e);
            e.setName("猪八戒");
            ts.commit();
        } catch (HibernateException e1) {
            e1.printStackTrace();
            ts.rollback();
        }
    }


    /**
     * 持久化对象自动更新到数据库的时机
     */
    @Test
    public void test3() {
        Session session = HibernateUtil.getSession();
        Emp e = (Emp) session.load(Emp.class, 8);
        e.setName("台上老君");
        session.flush();//同步但未提交事务
        session.close();
    }

}
