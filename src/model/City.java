package model;

public class City {

	private int id ;
	private String cityName;
	private String cityCode;
	private int provinceId;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getCityName(){
		return cityName;
	}
	
	public void setCityCode(String cityCode){
		this.cityCode = cityCode;
	}
	
	public String getCityCode(){
		return cityCode;
	}
	
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	
	public int getProvinceId(){
		return provinceId;
	}
	
	public void setProvinceId(int id){
		this.provinceId = id;
	}
}
