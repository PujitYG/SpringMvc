package com.learn.SpringHibernate.RolesPermission;

import java.util.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


public enum Roles {
	   USER(new String[]{"READ"}),
	   ADMIN(new String[]{"WRITE","READ"});

	
	   public String[] permission;
	
	   private Roles(String[] permission) {
	      this.permission = permission;
	   }
	
	   public List<SimpleGrantedAuthority> getPermission() {
		   List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
	      for(String s:this.permission) {
	    	  list.add(new SimpleGrantedAuthority(s));
	      }
	      list.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
	      System.out.println(list);
	      return list;
	   }
}
