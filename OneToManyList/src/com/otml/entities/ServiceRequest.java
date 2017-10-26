package com.otml.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_REQUEST")
public class ServiceRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "SERVICE_REQUEST_NO")
	protected int serviceRequestNo;
	@Column(name = "PROBLEM_DESCR")
	protected String problemDescription;
	@Column(name = "SERVICE_REQUEST_DT")
	protected Date serviceRequestDate;
	protected int priority;
	protected int eta;
	protected String status;

	public int getServiceRequestNo() {
		return serviceRequestNo;
	}

	public void setServiceRequestNo(int serviceRequestNo) {
		this.serviceRequestNo = serviceRequestNo;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public Date getServiceRequestDate() {
		return serviceRequestDate;
	}

	public void setServiceRequestDate(Date serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
