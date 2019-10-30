package com.Gourav.models;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
	@Id
	@NotNull(message="Not Null")
	private String productId;
	
	@Pattern(regexp="(?=.{5,50}$)^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$", message="Min 5 & Max 50 chars and contains (0-9,a-z,A-Z,-, ,_)")
	private String productName;
	
    @NotNull(message="Select cateogory")
	private String productCategory;
    
    @Min(value=1,message="Minimum 49 Rs")
    @Max(value=100000,message="Maximum 1 Lakh")
    private int productPrice;
    
    @Min(value=0,message="Minimum 0 Pic")
    @Max(value=500,message="Maximum 500 Pics")
    private int picsRequired;
    
    @Min(value=1,message="Minimum 1 Day")
    @Max(value=30,message="Maximum 30 Days")
    private int daysRequired;
    
    @Pattern(regexp="(?=.{30,150}$)^[a-zA-Z0-9.-]+([_ -]?[a-zA-Z0-9.-])*$", message="Min 30 & Max 150 chars and contains (0-9,a-z,A-Z,-, ,_)")
    private String productDesc;
    
    private Blob productPicture;
}
