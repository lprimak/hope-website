/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website.pages;

import com.flowlogix.website.WebStats;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author lprimak
 */
public class DbTable
{
    @Getter private List<WebStats> webStats = new ArrayList<WebStats>();
}
