/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ng.adplus.bl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;
import ng.adplus.ent.Account;
import ng.adplus.ent.Tweets;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author johnson3yo
 */
@Stateless
public class AdplusEJB {

    @PersistenceContext
    EntityManager em;

    public Account login(Account act) throws AdplusException {
        Query q = em.createQuery("select a from Account a where a.accountUsername =:accountUsername and a.accountPassword =:accountPassword ");
        q.setParameter("accountUsername", act.getAccountUsername());
        q.setParameter("accountPassword", act.getAccountPassword());
        Account a = null;
        try {
            a = (Account) q.getSingleResult();

            return a;
        } catch (NoResultException no) {
            throw new AdplusException("No such user");
        }
    }

    public List<Tweets> getTweets() {

        return em.createQuery("select t from Tweets t order by t.timeCreated desc ").getResultList();

    }

    public void postTweet(Tweets twt, Account act) {
        twt.setAccountId(act);
        twt.setTimeCreated(new Date());
        em.merge(twt);

    }

    public void createAccount(Account acct) {
        em.persist(acct);
    }

    public void editTweet(Tweets twt) {
        twt.setTimeCreated(new Date());
        em.merge(twt);
    }

    public void deleteTweet(Tweets twt) {

        Tweets t = em.merge(twt);

        em.remove(t);
    }

    public void changePhoto(Part file, Account a) throws Exception {
        InputStream is = file.getInputStream();
        byte[] value = IOUtils.toByteArray(is);
        a.setPhoto(value);
        em.merge(a);

    }

    public byte[] fetchImage(int parseInt) {
        Account acct = em.find(Account.class, parseInt);
        return acct.getPhoto();
    }
}
