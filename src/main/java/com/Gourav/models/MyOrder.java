package com.Gourav.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class MyOrder
{
	@Id
	private long orderId;
	
	private String productId;
	
	@Min(value=1,message="Minimum 1 Quantity")
	@Max(value=25,message="Maximum 25 Quantity")
	private int quantity;
    
	private String email;
    
    @Pattern(regexp="(?=.{5,30}$)^[a-zA-Z]+([_ -]?[a-zA-Z])*$", message="Min 5 & Max 30 chars and contains (a-z,A-Z,-, ,_)")
    private String recipientName;
    
    @Size(max=50,min=5,message="Min 5 & Max 30 chars")
    private String recipientAddress;
    
	@NotNull(message="Select a City")
    private String recipientCity;
    
	@NotNull(message="Select a State")
    private String recipientState;
	
	@NotNull(message="Select a OrderStatus")
    private String orderStatus;
    
	@Min(value=1000000000,message="Must contain 10 digits")
	@Max(value=9999999999l,message="Must contain 10 digits")
    private long mobileNumber;
    
	@NotNull(message="Select a Payment Mode")
    private String paymentMode;
    
    private long total;
   
    private Timestamp dateAndTime;
}
