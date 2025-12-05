package com.mystore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductsDTO {

	@JsonProperty(value = "id")
	private Long id;

	@JsonProperty(value = "title")
	private String title;

	@NotNull(message = "Price cannot be null")
	@JsonProperty(value = "price")
	private Float price;

	@NotEmpty(message = "Description cannot be empty")
	@NotNull(message = "Description cannot be null")
	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "category")
	private String category;

	@JsonProperty(value = "image")
	private String image;

	public ProductsDTO(Long id, String title, Float price, String description, String category, String image) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
	}

	public ProductsDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ProductsDTO [id=" + id + ", title=" + title + ", price=" + price + ", description=" + description
				+ ", category=" + category + ", image=" + image + "]";
	}

}
