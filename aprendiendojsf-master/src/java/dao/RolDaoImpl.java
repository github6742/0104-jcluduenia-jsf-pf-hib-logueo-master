/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Rol;
import org.hibernate.Session;
import util.HibernateUtil;

public class RolDaoImpl implements RolDao{

    @Override
    public List<Rol> selectItems() {
        List<Rol> listado = null;
        String metodo = "RolDaoImpl.selectItems()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Rol";
        try {
            sesion.beginTransaction();
            listado =  sesion.createQuery(sql).list();
            sesion.getTransaction().commit();
            //sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        } finally {
            System.out.println(metodo + "-050-close");
            sesion.close();
        }
        return listado;
    }
}
