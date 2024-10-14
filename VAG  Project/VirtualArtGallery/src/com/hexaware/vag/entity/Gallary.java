package com.hexaware.vag.entity;

public class Gallary {
	
	private int gallartId;
	private String name;
	private String description;
	private String location;
	private String curator;
	private String openingHours;
	
	public Gallary(int gallartId, String name, String description, String location, String curator,
			String openingHours) {
		super();
		this.gallartId = gallartId;
		this.name = name;
		this.description = description;
		this.location = location;
		this.curator = curator;
		this.openingHours = openingHours;
	}

	public int getGallartId() {
		return gallartId;
	}

	public void setGallartId(int gallartId) {
		this.gallartId = gallartId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCurator() {
		return curator;
	}

	public void setCurator(String curator) {
		this.curator = curator;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	@Override
	public String toString() {
		return "Gallary [gallartId=" + gallartId + ", name=" + name + ", description=" + description + ", location="
				+ location + ", curator=" + curator + ", openingHours=" + openingHours + "]";
	}
	
	

}
