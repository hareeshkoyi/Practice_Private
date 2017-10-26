package com.otml.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ListIndexBase;

@Entity
@Table(name = "TECHNICIAN")
public class Technician {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "TECHNICIAN_NO")
	protected int technicianNo;
	@Column(name = "TECHNICIAN_NM")
	protected String name;
	@Column(name = "DESGN")
	protected String designation;
	@Column(name = "CONTACT_NO")
	protected String contactNo;
	@Column(name = "EMAIL_ADDR")
	protected String emailAddress;
	@OneToMany
	@JoinColumn(name = "ASSIGNED_TECHNICIAN_NO")
	@OrderColumn(name = "SR_ASSIGNED_ORDER", nullable = true)
	@ListIndexBase(value = 1)
	protected List<ServiceRequest> serviceRequests;

	public int getTechnicianNo() {
		return technicianNo;
	}

	public void setTechnicianNo(int technicianNo) {
		this.technicianNo = technicianNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public List<ServiceRequest> getServiceRequests() {
		return serviceRequests;
	}

	public void setServiceRequests(List<ServiceRequest> serviceRequests) {
		this.serviceRequests = serviceRequests;
	}

}
