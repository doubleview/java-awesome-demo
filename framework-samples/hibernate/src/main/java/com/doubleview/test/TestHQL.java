package com.doubleview.test;

import com.doubleview.model.Service;
import com.doubleview.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * 测试HQL语句
 */
@SuppressWarnings("JpaQlInspection")
public class TestHQL {

    /**
     * 按条件查询
     */
    @Test
    public void test1() {
        String hql = "from Service where unixHost = ?";
        Session session = HibernateUtil.getSession();

        Query query = session.createQuery(hql);

        query.setString(0, "222.222.222.222");
        List<Service> services = query.list();
        for (Service service : services) {
            System.out.println(service);
        }
        session.close();
    }


    /**
     * 查询一部分字段
     */
    @Test
    public void test2() {
        String hql = "select id , unixHost , osUserName from Service where unixHost = ?";
        Session session = HibernateUtil.getSession();

        Query query = session.createQuery(hql);
        query.setString(0, "222.222.222.222");
        List<Object[]> services = query.list();
        for (Object[] service : services) {
            System.out.println(service[0] + " " + service[1] + " " + service[2]);
        }
        session.close();
    }


    /**
     * 分页查询
     */
    @Test
    public void test3() {
        int page = 1;
        int pageSize = 3;

        Session session = HibernateUtil.getSession();
        String hql = "from Service order by id";
        Query query = session.createQuery(hql);
        int from = (page - 1) * pageSize;
        query.setFirstResult(from);
        query.setMaxResults(pageSize);
        List<Service> services = query.list();
        for (Service service : services) {
            System.out.println(service);
        }
    }

    /**
     * 查询总页数
     */
    @Test
    public void test4() {
        int pageSize = 3;
        String hql = "select count(*) from Service";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        int rows = Integer.parseInt(query.uniqueResult().toString());

        int totalPages = 0;
        if (rows % pageSize == 0) {
            totalPages = rows / pageSize;
        } else {
            totalPages = rows / pageSize + 1;
        }
        System.out.println(totalPages);
        session.close();
    }


    /**
     * 多表联合查询-对象方式关联
     */
    @Test
    public void test5() {
        String hql = "select s.id , s.osUserName , s.unixHost , a.id , a.realName , a.idcardNo from Service s , Account a where s.account.id= a.id";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);

        List<Object[]> list = query.list();
        for (Object[] objs : list) {
            System.out.println(Arrays.toString(objs));
        }
        session.close();
    }


    /**
     * 多表联合查询 join方式关联
     */
    @Test
    public void test6() {
        String hql = "select s.id , s.osUserName , a.id , a.realName , a.idcardNo from Service s join s.account a";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        for (Object[] objs : list) {
            System.out.println(Arrays.toString(objs));
        }
        session.close();
    }

    /**
     * 多表联合查询-select子句关联
     */
    @Test
    public void test7() {
        String hql = "select id , osUserName , unixHost , account.id , account.realName, account.idcardNo from Service";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        for (Object[] objs : list) {
            System.out.println(Arrays.toString(objs));
        }

        session.close();
    }
}
