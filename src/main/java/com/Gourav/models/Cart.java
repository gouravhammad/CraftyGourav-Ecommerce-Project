package com.Gourav.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable
{
   @Id
   private String email;
   
   @Id
   private String productId;
   
   private int quantity;
}
