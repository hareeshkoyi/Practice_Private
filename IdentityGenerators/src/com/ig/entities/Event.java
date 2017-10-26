package com.ig.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "EVENT")
// @TableGenerator(name="pms_gen", table="pms_ids", pkColumnName="PK_COL",
// valueColumnName="PK_VAL")
public class Event {
	@Id
	@Column(name = "EVENT_ID")
	// @SequenceGenerator(name = "event_seq_gen", sequenceName = "event_id_seq",
	// initialValue = 1, allocationSize = 1)
	@GenericGenerator(name = "event_seq_hilo_gen", strategy = "hilo", parameters = {
			@Parameter(name = "max_lo", value = "10") })
	@GeneratedValue(generator = "event_seq_hilo_gen")
	private int eventId;
	private String title;
	@Column(name = "DESCR")
	private String description;
	@Column(name = "EVENT_DT")
	private Date eventDate;
	private String place;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
