package com.res.order.dao;

import org.springframework.web.multipart.MultipartFile;

public class Menu {
	private int menuId;
	private MultipartFile menuPhoto;
	private String menuName;
	private String menuPrice;
	private int menuCategory;
	private String menuDetail;
	private int statusOfStock;
	private String photoBase64String;
	public int getMenuId() {
		return menuId;
	}
	public String getPhotoBase64String() {
		return photoBase64String;
	}
	public void setPhotoBase64String(String photoBase64String) {
		this.photoBase64String = photoBase64String;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public MultipartFile getMenuPhoto() {
		return menuPhoto;
	}
	public void setMenuPhoto(MultipartFile menuPhoto) {
		this.menuPhoto = menuPhoto;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}
	public int getMenuCategory() {
		return menuCategory;
	}
	public void setMenuCategory(int menuCategory) {
		this.menuCategory = menuCategory;
	}
	public String getMenuDetail() {
		return menuDetail;
	}
	public void setMenuDetail(String menuDetail) {
		this.menuDetail = menuDetail;
	}
	public int getStatusOfStock() {
		return statusOfStock;
	}
	public void setStatusOfStock(int statusOfStock) {
		this.statusOfStock = statusOfStock;
	}


}
