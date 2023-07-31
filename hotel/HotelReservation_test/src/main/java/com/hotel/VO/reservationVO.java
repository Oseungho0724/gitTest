package com.hotel.VO;

public class reservationVO {
	private String id_res;
	private String detailAddr_roomNum_res;
	private String checkIn_res;
	private String checkOut_res;
	private String checkInDate;
	private String price_res;
	private String hotelname;

	
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public String getPrice_res() {
		return price_res;
	}
	public void setPrice_res(String price_res) {
		this.price_res = price_res;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getId_res() {
		return id_res;
	}
	public void setId_res(String id_res) {
		this.id_res = id_res;
	}
	public String getDetailAddr_roomNum_res() {
		return detailAddr_roomNum_res;
	}
	public void setDetailAddr_roomNum_res(String detailAddr_roomNum_res) {
		this.detailAddr_roomNum_res = detailAddr_roomNum_res;
	}
	public String getCheckIn_res() {
		return checkIn_res;
	}
	public void setCheckIn_res(String checkIn_res) {
		this.checkIn_res = checkIn_res;
	}
	public String getCheckOut_res() {
		return checkOut_res;
	}
	public void setCheckOut_res(String checkOut_res) {
		this.checkOut_res = checkOut_res;
	}
}
