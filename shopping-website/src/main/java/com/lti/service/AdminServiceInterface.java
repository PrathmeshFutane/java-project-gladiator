package com.lti.service;

import com.lti.entity.Admin;

public interface AdminServiceInterface {

	public int register(Admin admin);

	public Admin login(String email, String password);
	
	public Admin getAdminProfile(int id);

}
