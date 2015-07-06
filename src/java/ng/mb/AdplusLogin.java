package ng.mb;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import ng.adplus.bl.AdplusEJB;
import ng.adplus.bl.AdplusException;
import ng.adplus.ent.Account;
import ng.adplus.ent.Tweets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author johnson3yo
 */
@ManagedBean
@SessionScoped
public class AdplusLogin {

    @EJB
    AdplusEJB adp;
    private Account act = new Account();
    private UIComponent component;
    private FacesContext context;
    private String message;
    private Tweets twt = new Tweets();
    private Part file;
    private Boolean logged;

    @PostConstruct
    public void initializeData() {

        message = null;
    }

    public Account getAct() {
        return act;
    }

    public void setAct(Account act) {
        this.act = act;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tweets getTwt() {
        return twt;
    }

    public void setTwt(Tweets twt) {
        this.twt = twt;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Boolean isLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }
    
    
    

    public String login() {

        try {
            act = adp.login(act);
            logged = true;
            return "adplus_home?faces-redirect=true";
        } catch (AdplusException no) {
            context = FacesContext.getCurrentInstance();
            context.addMessage(component.getClientId(), new FacesMessage(no.getMessage()));
            logged = false;
            return "index?faces-redirect=true";
        }
    }

    public String editTweetScreen(Tweets twt) {

        this.twt = twt;

        return "edit_tweet?faces-redirect=true";
    }

    public String deleteTweet(Tweets twt) {

        adp.deleteTweet(twt);

        return "adplus_home";
    }

    public String viewUser(Tweets twt) {
        this.twt = twt;

        return "view_user?faces-redirect=true";
    }
    
    public String changePhoto(){
       try{
        adp.changePhoto(file,act); 
       return "adplus_home?faces-redirect=true"; 
       }
       catch(Exception no){
        return "adplus_home?faces-redirect=true";  
       }
    }
}
