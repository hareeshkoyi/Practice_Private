package com.oto.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIBER")
public class Subscriber {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "SUBSCRIBER_NO")
	protected int subscriberNo;
	@Column(name = "SUBSCRIBER_NM")
	protected String subscriberName;
	@Column(name = "SUBSCRIPTION_DT")
	protected Date subscriptionDate;
	@Column(name = "MOBILE_NO")
	protected String mobileNo;
	protected String plan;
	@Column(name = "NETWORK_TYPE")
	protected String networkType;

	public int getSubscriberNo() {
		return subscriberNo;
	}

	public void setSubscriberNo(int subscriberNo) {
		this.subscriberNo = subscriberNo;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

}
