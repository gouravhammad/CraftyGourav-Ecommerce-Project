package com.Gourav.DAOs;

import org.springframework.stereotype.Component;

import com.Gourav.models.Admin;

@Component
public interface AdminDAO
{
	public boolean verifyAdmin(Admin admin);
}
