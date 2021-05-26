/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author lude
 */
public class UsuarioDaoImpl implements UsuarioDao{

    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario model = null;
        String metodo = "UsuarioDaoImpl.findByUsuario()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Usuario WHERE usuario = '"+usuario.getUsuario()+"'";
        try {
            sesion.beginTransaction();
            model = (Usuario) sesion.createQuery(sql).uniqueResult();
            //sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        } finally {
            System.out.println(metodo + "-050-close");
            sesion.close();
        }
            System.out.println(metodo + "-060-");
        return model;
    }

    @Override
    public Usuario login(Usuario usuario) {
        String metodo = "UsuarioDaoImpl.login()";
        Usuario model = this.findByUsuario(usuario);
        if (model != null) {
            if (!usuario.getClave().equals(model.getClave())) {
                model = null;
            }
        }
            System.out.println(metodo + "-010-");
        return model;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> listado = null;
        String metodo = "UsuarioDaoImpl.findAll()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Usuario u left join fetch u.rol";
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
            System.out.println(metodo + "-060-");
        return listado;
    }

    @Override
    public boolean create(Usuario usuario) {
        boolean flag;
        String metodo = "UsuarioDaoImpl.create()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            sesion.beginTransaction();
            sesion.save(usuario);
            sesion.getTransaction().commit();
            //sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        
        } finally {
            System.out.println(metodo + "-050-close");
            sesion.close();
        }
            System.out.println(metodo + "-060-");
        return flag;
    }

    @Override
    public boolean update(Usuario usuario) {
        boolean flag;
        String metodo = "UsuarioDaoImpl.update()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            System.out.println(metodo + "-010-");
        Session sesion = HibernateUtil.getSessionFactory().openSession();
            System.out.println(metodo + "-020-");
        try {
            sesion.beginTransaction();
            System.out.println(metodo + "-030-usuario-clave:"+usuario.getClave());
            System.out.println(metodo + "-030-usuario-email:"+usuario.getEmail());
            System.out.println(metodo + "-030-usuario-usuario:"+usuario.getUsuario());
            System.out.println(metodo + "-030-usuario-id:"+usuario.getId());
            System.out.println(metodo + "-030-usuario-estado:"+usuario.getEstado());
            //Usuario usuariodb = (Usuario) sesion.load(Usuario.class, usuario.getId());
            System.out.println(metodo + "-040-");
            //usuariodb.setEmail(usuario.getEmail());
            System.out.println(metodo + "-042-");
            //usuariodb.setUsuario(usuario.getUsuario());
            System.out.println(metodo + "-045-");
            //usuariodb.setRol(usuario.getRol());
            System.out.println(metodo + "-050-");
            sesion.update(usuario);
            System.out.println(metodo + "-060-");
            sesion.getTransaction().commit();
            //sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            System.out.println(metodo + "-0710-");
            sesion.beginTransaction().rollback();
        
        } finally {
            System.out.println(metodo + "-080-close");
            sesion.close();
        }
            System.out.println(metodo + "-090-");
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        boolean flag;
        String metodo = "UsuarioDaoImpl.delete()";
            System.out.println(metodo + "-010-");
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
            System.out.println(metodo + "-020-");
        try {
            sesion.beginTransaction();
            System.out.println(metodo + "-030-id: "+id);
            Usuario usuario = (Usuario) sesion.load(Usuario.class, id);
            System.out.println(metodo + "-040-usuario.id:"+usuario.getId());
            System.out.println(metodo + "-040-usuario.rol:"+usuario.getRol().getId());
            sesion.delete(usuario);
            System.out.println(metodo + "-050-");
            sesion.getTransaction().commit();
            //sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            System.out.println(metodo + "-060-");
            sesion.beginTransaction().rollback();
        
        } finally {
            System.out.println(metodo + "-070-close");
            sesion.close();
        }
            System.out.println(metodo + "-080-");
        return flag;
    }
}