/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ng.adplus.test;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import ng.adplus.ent.Tweets;
import org.junit.Test;

/**
 *
 * @author johnson3yo
 */
@Stateless
public class TestEJB {

    @PersistenceContext
    EntityManager em;

    public List findAllTweets() {
        return em.createQuery("select t from Tweets t").getResultList();
    }

    @Test
    public void testFindAllTweets() {
        List<Tweets> all = findAllTweets();
        Assert.assertEquals(4, all.size());
    }
}
