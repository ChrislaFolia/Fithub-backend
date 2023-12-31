package com.fithub.model.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fithub.model.order.Order;
import com.fithub.model.rentorder.RentOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity 
@Table(name = "member")
public class Member {

	@Id  @Column(name="MEMBERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberid;
	private String memberphoneno;
	private String memberpassword;
	private String membername;
	private String membergender;
	private String memberemail;
	private String membercity;
	private String memberzone;
	private String memberaddress;
	private String memberbirthday;
	private String memberaccountsince;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
	private List<RentOrder> rentOrders = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

}
