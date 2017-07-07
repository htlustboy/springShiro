package com.spring.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


public class JdbcRealm extends AuthorizingRealm{
	
	/* 
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		System.out.println(userName);
		return null;
	}

	/* 
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		String userName = token.getUsername();
		
		if("hutao".equals(userName)){
			throw new UnknownAccountException("未找到该用户");
		}
		
		if("admin".equals(userName)){
			throw new LockedAccountException("账号被锁定");
		}
		
		//密码，应该是从数据库中获取
		Object credentials = "b8d63fc060e2b5651e8cb4e71ba61e6f";
		
		//盐值
		ByteSource salt = ByteSource.Util.bytes(userName);
		return new SimpleAuthenticationInfo(userName, credentials, salt, getName());
	}
	
	public static void main(String[] args) {
		String str = "123456"; //需要加密的密码
		String hashAlgorithmName = "MD5"; //加密方式
		int hashIterations = 1024; //加密次数
		Object salt = "aaa";
		System.out.println(new SimpleHash(hashAlgorithmName, str, salt,hashIterations));
		
	}

}
