package com.oto.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SUBSCRIBER_PREFS")
public class SubscriberPreferences {
	@Id
	@GenericGenerator(name = "sub_no_gen", strategy = "foreign", parameters = { @Parameter(value = "subscriber", name = "property") })
	@GeneratedValue(generator = "sub_no_gen")
	@Column(name = "SUBSCRIBER_NO")
	protected int subscriberNo;
	protected boolean dnd;
	@Column(name = "CALL_WAITING")
	protected boolean callWaiting;
	@Column(name = "CALL_FORWARDING")
	protected boolean callForwarding;
	@Column(name = "CALLER_ID")
	protected boolean callerIdentification;
	@OneToOne
	@PrimaryKeyJoinColumn
	protected Subscriber subscriber;

	public int getSubscriberNo() {
		return subscriberNo;
	}

	public void setSubscriberNo(int subscriberNo) {
		this.subscriberNo = subscriberNo;
	}

	public boolean getDnd() {
		return dnd;
	}

	public void setDnd(boolean dnd) {
		this.dnd = dnd;
	}

	public boolean getCallWaiting() {
		return callWaiting;
	}

	public void setCallWaiting(boolean callWaiting) {
		this.callWaiting = callWaiting;
	}

	public boolean getCallForwarding() {
		return callForwarding;
	}

	public void setCallForwarding(boolean callForwarding) {
		this.callForwarding = callForwarding;
	}

	public boolean getCallerIdentification() {
		return callerIdentification;
	}

	public void setCallerIdentification(boolean callerIdentification) {
		this.callerIdentification = callerIdentification;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

}
