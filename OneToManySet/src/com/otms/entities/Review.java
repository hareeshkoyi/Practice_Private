package com.otms.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEWS")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "REVIEW_NO")
	protected int reviewNo;
	@Column(name = "REVIEW_COMMENTS")
	protected String comments;
	protected int rating;
	@Column(name = "REVIEW_BY")
	protected String reviewBy;
	@Column(name = "REVIEW_DT")
	protected Date reviewDate;
	@ManyToOne
	@JoinColumn(name = "REVIEWED_RESTAURANT_NO", nullable = false)
	protected Restaurant restaurant;

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewBy() {
		return reviewBy;
	}

	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
