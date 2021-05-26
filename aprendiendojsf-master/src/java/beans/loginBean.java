/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author lude
 */
@Named(value="loginBean")
@SessionScoped
public class loginBean implements Serializable{

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private String ruta1 = "";
    
    public loginBean() {
        this.usuarioDao = new UsuarioDaoImpl();
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
    }

    
    
    public String getRuta1() {
        return ruta1;
    }

    public void setRuta1(String ruta1) {
        this.ruta1 = ruta1;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  
    public void login(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg;  
        boolean loggedIn; 
        String ruta = "";
        this.usuario = this.usuarioDao.login(this.usuario);
        if(this.usuario != null) {  
            loggedIn = true; 
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUsuario());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUsuario());
            ruta = MyUtil.basepathlogin()+"views/inicio.xhtml";
        } else {  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario y/o Clave es incorrecto.");
            if (this.usuario == null) {
                this.usuario = new Usuario();
            }
        }  

        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);  
        context.addCallbackParam("ruta", ruta);
    }
  
    public String login2(ActionEvent actionEvent) {  
            System.out.println("loginBean.login2-01");
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        String ruta = "";
        this.usuario = this.usuarioDao.login(this.usuario);
            System.out.println("loginBean.login2-02");
        if (this.usuario != null) {
            System.out.println("loginBean.login2-03");
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUsuario());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUsuario());
            ruta = MyUtil.basepathlogin() + "views/inicio.xhtml";
        ruta="views/inicio.xhtml";
            System.out.println("loginBean-ruta: "+ruta);
            ruta1 = ruta;
            System.out.println("loginBean-ruta1: "+ruta);
            System.out.println("loginBean.login2-04");
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid Credentials");
            System.out.println("loginBean.login2-05");

            if (this.usuario == null) {
                this.usuario = new Usuario();
            }
            System.out.println("loginBean.login2-06");
        ruta="login.xhtml";
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
        
            System.out.println("loginBean.login2-ruta1: "+ruta);
        return ruta;
    }

    public void logout()
    {
        String ruta = MyUtil.basepathlogin()+"login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();

        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }
}