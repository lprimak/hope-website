/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lprimak
 */
@Entity
@Table(name = "webstats")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Webstats.findAll", query = "SELECT w FROM Webstats w"),
    @NamedQuery(name = "Webstats.findBySdate", query = "SELECT w FROM Webstats w WHERE w.webstatsPK.sdate = :sdate"),
    @NamedQuery(name = "Webstats.findBySName", query = "SELECT w FROM Webstats w WHERE w.webstatsPK.sName = :sName"),
    @NamedQuery(name = "Webstats.findByNvisit", query = "SELECT w FROM Webstats w WHERE w.nvisit = :nvisit"),
    @NamedQuery(name = "Webstats.findByNcarrier", query = "SELECT w FROM Webstats w WHERE w.ncarrier = :ncarrier"),
    @NamedQuery(name = "Webstats.findByNtrack", query = "SELECT w FROM Webstats w WHERE w.ntrack = :ntrack"),
    @NamedQuery(name = "Webstats.findByNadmin", query = "SELECT w FROM Webstats w WHERE w.nadmin = :nadmin"),
    @NamedQuery(name = "Webstats.findByNother", query = "SELECT w FROM Webstats w WHERE w.nother = :nother")
})
public class Webstats implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WebstatsPK webstatsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nvisit")
    private long nvisit;
    @Column(name = "ncarrier")
    private Long ncarrier;
    @Column(name = "ntrack")
    private Long ntrack;
    @Column(name = "nadmin")
    private Long nadmin;
    @Column(name = "nother")
    private Long nother;

    public Webstats()
    {
    }

    public Webstats(WebstatsPK webstatsPK)
    {
        this.webstatsPK = webstatsPK;
    }

    public Webstats(WebstatsPK webstatsPK, long nvisit)
    {
        this.webstatsPK = webstatsPK;
        this.nvisit = nvisit;
    }

    public Webstats(String sdate, String sName)
    {
        this.webstatsPK = new WebstatsPK(sdate, sName);
    }

    public WebstatsPK getWebstatsPK()
    {
        return webstatsPK;
    }

    public void setWebstatsPK(WebstatsPK webstatsPK)
    {
        this.webstatsPK = webstatsPK;
    }

    public long getNvisit()
    {
        return nvisit;
    }

    public void setNvisit(long nvisit)
    {
        this.nvisit = nvisit;
    }

    public Long getNcarrier()
    {
        return ncarrier;
    }

    public void setNcarrier(Long ncarrier)
    {
        this.ncarrier = ncarrier;
    }

    public Long getNtrack()
    {
        return ntrack;
    }

    public void setNtrack(Long ntrack)
    {
        this.ntrack = ntrack;
    }

    public Long getNadmin()
    {
        return nadmin;
    }

    public void setNadmin(Long nadmin)
    {
        this.nadmin = nadmin;
    }

    public Long getNother()
    {
        return nother;
    }

    public void setNother(Long nother)
    {
        this.nother = nother;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (webstatsPK != null ? webstatsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Webstats))
        {
            return false;
        }
        Webstats other = (Webstats) object;
        if ((this.webstatsPK == null && other.webstatsPK != null) || (this.webstatsPK != null && !this.webstatsPK.equals(other.webstatsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.flowlogix.website.entities.Webstats[ webstatsPK=" + webstatsPK + " ]";
    }
    
}
