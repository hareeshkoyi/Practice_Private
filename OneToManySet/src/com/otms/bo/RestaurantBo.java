package com.otms.bo;

public class RestaurantBo {
	private String restaurantName;
	private String location;
	private String contactNo;

	public RestaurantBo(String restaurantName, String location, String contactNo) {
		this.restaurantName = restaurantName;
		this.location = location;
		this.contactNo = contactNo;
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

	@Override
	public String toString() {
		return "RestaurantBo [restaurantName=" + restaurantName + ", location="
				+ location + ", contactNo=" + contactNo + "]";
	}

}
