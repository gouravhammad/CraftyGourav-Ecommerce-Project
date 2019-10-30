package com.Gourav.models;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer
{
	@Id
	@Email
	@Size(max=50,min=10,message="Min 10 & Max 50 chars")
	private String email;

	@Pattern(regexp="(?=.{5,30}$)^[a-zA-Z]+([_ -]?[a-zA-Z])*$", message="Min 5 & Max 30 chars and contains (a-z,A-Z,-, ,_)")
    private String userName;
	
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$", message="Min 4 & Max 8 chars and must include atleast 1 Uppercase alphabet and 1 Lowercase alphabet and 1 Numeric digit")
    private String password;
    
	@Min(value=1000000000,message="Must contain 10 digits")
	@Max(value=9999999999l,message="Must contain 10 digits")
    private long mobileNumber;
    
	@Size(max=50,min=5,message="Min 5 & Max 30 chars")
    private String address;
    
	@NotNull(message="Select a State")
    private String state;
    
	@NotNull(message="Select a City")
    private String city;
    
    private Blob profilePicture;
}
