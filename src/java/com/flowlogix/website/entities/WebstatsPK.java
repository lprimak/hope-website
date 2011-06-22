/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lprimak
 */
@Embeddable
public class WebstatsPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "sdate")
    private String sdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "sName")
    private String sName;

    public WebstatsPK()
    {
    }

    public WebstatsPK(String sdate, String sName)
    {
        this.sdate = sdate;
        this.sName = sName;
    }

    public String getSdate()
    {
        return sdate;
    }

    public void setSdate(String sdate)
    {
        this.sdate = sdate;
    }

    public String getSName()
    {
        return sName;
    }

    public void setSName(String sName)
    {
        this.sName = sName;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (sdate != null ? sdate.hashCode() : 0);
        hash += (sName != null ? sName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebstatsPK))
        {
            return false;
        }
        WebstatsPK other = (WebstatsPK) object;
        if ((this.sdate == null && other.sdate != null) || (this.sdate != null && !this.sdate.equals(other.sdate)))
        {
            return false;
        }
        if ((this.sName == null && other.sName != null) || (this.sName != null && !this.sName.equals(other.sName)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.flowlogix.website.entities.WebstatsPK[ sdate=" + sdate + ", sName=" + sName + " ]";
    }
    
}
