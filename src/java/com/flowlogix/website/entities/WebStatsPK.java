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
import lombok.Data;

/**
 *
 * @author lprimak
 */
@Embeddable
@Data
public class WebStatsPK implements Serializable
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

    public WebStatsPK()
    {
    }

    public WebStatsPK(String sdate, String sName)
    {
        this.sdate = sdate;
        this.sName = sName;
    }    
}
