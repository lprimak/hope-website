/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website.services.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jvnet.libpam.PAM;
import org.jvnet.libpam.PAMException;
import org.jvnet.libpam.UnixUser;

/**
 *
 * @author lprimak
 */
public class UnixRealm extends AuthorizingRealm
{
    public UnixRealm(String serviceName)
    {
        super(new MemoryConstrainedCacheManager());
        if(getPermissionResolver() == null)
        {
            setPermissionResolver(new WildcardPermissionResolver());
        }
        
        this.serviceName = serviceName;
    }

    
    @Override
    @SneakyThrows(PAMException.class)
    protected void onInit()
    {
        super.onInit();
        getPam();
    }
    
    
    protected PAM getPam() throws PAMException
    {
        // PAM instances are not reusable.
        return new PAM(serviceName);
    }

    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        final UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        final String password = String.valueOf(upToken.getPassword());
        UnixUser unixUser = null;
        try
        {
            unixUser = getPam().authenticate(upToken.getUsername(), password);
        } catch (PAMException ex)
        {
            throw new AuthenticationException(ex);
        }
        return new SimpleAuthenticationInfo(new UserAuth(unixUser.getUserName(), password, unixUser.getGroups()), upToken.getPassword(), getName());
    }

    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        Set<String> roles = new HashSet<String>();
        Set<Permission> permissions = new HashSet<Permission>();
        permissions.add(new WildcardPermission("junkmail:erase"));
        Collection<UserAuth> principalsList = principals.byType(UserAuth.class);

        if (principalsList.isEmpty())
        {
            throw new AuthorizationException("Empty principals list!");
        }

        for (UserAuth userPrincipal : principalsList)
        {
            roles.addAll(userPrincipal.getRoles());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.setRoles(roles); 
        info.setObjectPermissions(permissions); 

        return info;
    }
    
    
    private final String serviceName;
}