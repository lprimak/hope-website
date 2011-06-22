/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website;

import lombok.Data;

/**
 *
 * @author lprimak
 */
@Data
public class WebStats
{
    private String Date;
    private String StatisticName;
    private int numVisits;
    private int numTrackTrace;
}
