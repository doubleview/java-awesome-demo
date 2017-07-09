package com.doubleview.test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.doubleview.model.Emp;
import com.doubleview.util.HibernateUtil;


/**
 * 对员工进行增删改查
 */
@SuppressWarnings("JpaQlInspection")
public class TestEmp {

    @Test
    public void add() {
        Emp e = new Emp();
        e.setName("hcc");
        e.setAge(23);
        e.setMarry(false);
        e.setSalary(8000.0);
        e.setBirthday(Date.valueOf("1983-10-20"));
        e.setLastLoginTime(new Timestamp(System.currentTimeMillis()));

        Session session = HibernateUtil.getSession();
        Transaction ts = session.beginTransaction();
        try {
            session.save(e);
            ts.commit();
            System.out.println(e);
            System.out.println("添加成功");
        } catch (Exception e2) {
            e2.printStackTrace();
            ts.rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void update() {
        Session session = HibernateUtil.getSession();

        Emp emp = (Emp) session.get(Emp.class, 4);
        System.out.println(emp);
        Transaction ts = session.beginTransaction();
        try {
            emp.setName("hcc");
            emp.setAge(20);
            session.update(emp);
            ts.commit();
            System.out.println("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
        } finally {
            session.close();
        }

    }

    @Test
    public void delete() {
        Session session = HibernateUtil.getSession();
        Emp emp = (Emp) session.get(Emp.class, 6);
        Transaction ts = session.beginTransaction();
        try {
            emp.setAge(99);
            session.delete(emp);
            System.out.println(emp);
            ts.commit();
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void get() {
        Session session = HibernateUtil.getSession();
        Emp emp = (Emp) session.get(Emp.class, 4);
        emp.setAge(21);
        Transaction ts = session.beginTransaction();
        System.out.println(emp);
        ts.commit();
        System.out.println(emp);
        session.close();
    }

    @Test
    public void findAll() {
        String hql = "from Emp";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        List<Emp> emps = query.list();
        for (Emp e : emps) {
            System.out.println(e.getName());
        }

        session.close();
    }
}
