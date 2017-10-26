package com.fh.entities;

public class Project {
	private int projectNo;
	private String title;
	private String description;
	private String domain;
	private int duration;
	private String clientName;
	private String status;

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Project [projectNo=" + projectNo + ", title=" + title
				+ ", description=" + description + ", domain=" + domain
				+ ", duration=" + duration + ", clientName=" + clientName
				+ ", status=" + status + "]";
	}

}
