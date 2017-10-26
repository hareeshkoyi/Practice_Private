package com.otmm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOAD")
public class Load {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "LOAD_NO")
	protected int loadNo;
	@Column(name = "DESCR")
	protected String description;
	@Column(name = "LOAD_DT")
	protected Date loadDate;
	protected String owner;
	protected int weight;

	public int getLoadNo() {
		return loadNo;
	}

	public void setLoadNo(int loadNo) {
		this.loadNo = loadNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
