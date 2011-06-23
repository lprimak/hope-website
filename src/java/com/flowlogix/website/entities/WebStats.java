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
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author lprimak
 */
@Entity
@Table(name = "webstats")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "WebStats.findAll", query = "SELECT w FROM WebStats w"),
    @NamedQuery(name = "WebStats.findBySdate", query = "SELECT w FROM WebStats w WHERE w.webStatsPK.sdate = :sdate"),
    @NamedQuery(name = "WebStats.findBySName", query = "SELECT w FROM WebStats w WHERE w.webStatsPK.sName = :sName"),
    @NamedQuery(name = "WebStats.findByNvisit", query = "SELECT w FROM WebStats w WHERE w.nvisit = :nvisit"),
    @NamedQuery(name = "WebStats.findByNcarrier", query = "SELECT w FROM WebStats w WHERE w.ncarrier = :ncarrier"),
    @NamedQuery(name = "WebStats.findByNtrack", query = "SELECT w FROM WebStats w WHERE w.ntrack = :ntrack"),
    @NamedQuery(name = "WebStats.findByNadmin", query = "SELECT w FROM WebStats w WHERE w.nadmin = :nadmin"),
    @NamedQuery(name = "WebStats.findByNother", query = "SELECT w FROM WebStats w WHERE w.nother = :nother")
})


@Data
public class WebStats implements Serializable
{
    public String getDate()
    {
        return webStatsPK.getSdate();
    }

    
    public String getStatisticName()
    {
        return webStatsPK.getSName();
    }

    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WebStatsPK webStatsPK;
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

    public WebStats()
    {
    }

    public WebStats(WebStatsPK webstatsPK)
    {
        this.webStatsPK = webstatsPK;
    }

    public WebStats(WebStatsPK webstatsPK, long nvisit)
    {
        this.webStatsPK = webstatsPK;
        this.nvisit = nvisit;
    }

    public WebStats(String sdate, String sName)
    {
        this.webStatsPK = new WebStatsPK(sdate, sName);
    }
}
