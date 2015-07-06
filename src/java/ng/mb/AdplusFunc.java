package ng.mb;


import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import ng.adplus.bl.AdplusEJB;
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
@ViewScoped
public class AdplusFunc {
    
    @EJB AdplusEJB adp;
    
    private List<Tweets>aList;
    private Tweets twt = new Tweets();
    private Account acct = new Account();
    private FacesContext context;
    private UIComponent component;
    private String message;
    
    @ManagedProperty(value = "#{adplusLogin}")
    private AdplusLogin logc;
    
    @PostConstruct
    public void initializeList(){
        aList = adp.getTweets();
    }

    public List<Tweets> getTweets() {
        return aList;
    }

    public void setaList(List<Tweets> aList) {
        this.aList = aList;
    }

    public Tweets getTwt() {
        return twt;
    }

    public void setTwt(Tweets twt) {
        this.twt = twt;
    }

    public Account getAcct() {
        return acct;
    }

    public void setAcct(Account acct) {
        this.acct = acct;
    }

    public AdplusLogin getLogc() {
        return logc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
    public void setLogc(AdplusLogin logc) {
        this.logc = logc;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
   
    
    public String createAccount(){
        adp.createAccount(acct);
         context = FacesContext.getCurrentInstance();
         context.addMessage(component.getClientId(), new FacesMessage("Account successfully created , try to sign in"));
         message = "Account successfully created , you can sign in "; 
        return "index?faces-redirect=true";
    }
    
   
    public String postTweet(){
        Account act = logc.getAct();
        adp.postTweet(twt,act);
        
        return "adplus_home?faces-redirect=true";
    }
    
   
    
    public String editTweet(){
     Tweets t = logc.getTwt();
     this.adp.editTweet(t);
       return "adplus_home?faces-redirect=true";
    }
    
    public boolean ownsAccount(Tweets t){
        Account a = t.getAccountId();
       
        if(a.equals(logc.getAct())){
           return true;
        }else return false;
    }
}
