/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ng.adplus.ent;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "tweets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tweets.findAll", query = "SELECT t FROM Tweets t"),
    @NamedQuery(name = "Tweets.findByTweetId", query = "SELECT t FROM Tweets t WHERE t.tweetId = :tweetId"),
    @NamedQuery(name = "Tweets.findByTweetMessage", query = "SELECT t FROM Tweets t WHERE t.tweetMessage = :tweetMessage"),
    @NamedQuery(name = "Tweets.findByTimeCreated", query = "SELECT t FROM Tweets t WHERE t.timeCreated = :timeCreated")})
public class Tweets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "tweet_id")
    private Integer tweetId;
    @Size(max = 64)
    @Column(name = "tweet_message")
    private String tweetMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @ManyToOne
    private Account accountId;

    public Tweets() {
    }

    public Tweets(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public Tweets(Integer tweetId, Date timeCreated) {
        this.tweetId = tweetId;
        this.timeCreated = timeCreated;
    }

    public Integer getTweetId() {
        return tweetId;
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public String getTweetMessage() {
        return tweetMessage;
    }

    public void setTweetMessage(String tweetMessage) {
        this.tweetMessage = tweetMessage;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tweetId != null ? tweetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tweets)) {
            return false;
        }
        Tweets other = (Tweets) object;
        if ((this.tweetId == null && other.tweetId != null) || (this.tweetId != null && !this.tweetId.equals(other.tweetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ng.adplus.ent.Tweets[ tweetId=" + tweetId + " ]";
    }
    
}
