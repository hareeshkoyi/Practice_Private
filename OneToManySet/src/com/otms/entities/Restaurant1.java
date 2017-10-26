package com.otms.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant1 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "RESTAURANT_NO")
	protected int restaurantNo;
	@Column(name = "RES_NM")
	protected String restaurantName;
	protected String location;
	@Column(name = "CONTACT_NO")
	protected String contactNo;
	@Column(name = "EMAIL_ADDR")
	protected String emailAddress;
	@Column(name = "RES_TYPE")
	protected String restaurantType;

	@OneToMany(mappedBy = "restaurant", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE, CascadeType.ALL })
	protected Set<Review> reviews;

	public int getRestaurantNo() {
		return restaurantNo;
	}

	public void setRestaurantNo(int restaurantNo) {
		this.restaurantNo = restaurantNo;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getRestaurantType() {
		return restaurantType;
	}

	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantNo=" + restaurantNo + ", restaurantName="
				+ restaurantName + ", location=" + location + ", contactNo="
				+ contactNo + ", emailAddress=" + emailAddress
				+ ", restaurantType=" + restaurantType + "]";
	}

}
