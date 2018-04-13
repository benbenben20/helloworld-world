package com.example.springboot.drools.model;

import java.util.Date;

public class Order {

	private Date bookingDate;//下单日期
	private int amount;//订单原价金额
	private User user;//下单人
	private int score;//积分
	
	public Order(){}
	
	public Order(Date bookingDate,int amount,User user,int score){
		this.bookingDate = bookingDate;
		this.amount = amount;
		this.user = user;
		this.score = score;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}


