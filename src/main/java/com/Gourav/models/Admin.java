package com.Gourav.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin 
{
	@Id
	@Email
	@Size(max=50,min=10,message="Min 10 & Max 50 chars")
    private String email;
    
	@Size(max=20,min=4,message="Min 4 & Max 10 chars")
	private String password;
}
