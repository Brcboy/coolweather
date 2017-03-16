package db;

import java.util.ArrayList;
import java.util.List;

import model.City;
import model.County;
import model.Province;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {

	//���ݿ���
	public static final String DB_NAME = "cool_weather";
	
	//���ݿ�汾
	public static final int VERSION = 1;
	
	private static CoolWeatherDB coolweatherDB;
	
	private SQLiteDatabase db;
	
	//˽�л����췽��
	
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbhelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbhelper.getWritableDatabase();
	}
	
	//��ȡCoolWeatherDB��ʵ��
	
	public synchronized static CoolWeatherDB  getInstance(Context context){
		if(coolweatherDB == null){
			coolweatherDB = new CoolWeatherDB(context);
		}
		return coolweatherDB;
	}
	
	//�洢provinceʵ�������ݿ�
	
	public void saveProvince (Province province){
		if(province != null){
			ContentValues values = new ContentValues();
			values.put("province_name",province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province",null, values);
		}
	}
	
	//���ݿ��ȡȫ������ʡ����Ϣ
	public List<Province> loadProvinces(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			} while(cursor.moveToNext());
			
			if(cursor != null){
				cursor.close();
			}
			return list;
		}
		return list;
	}
	
	//�洢cityʵ�������ݿ�
	public void saveCity(City city){
		if(city!=null){
			ContentValues values = new ContentValues();
			values.put("city_name",city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	
	//��ȡĳʡ���г��е���Ϣ
	public List<City> loadCitise(int provinceId){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City",null, "province_id = ?", new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()){
			City city = new City();
			city.setId(cursor.getInt(cursor.getColumnIndex("id")));
			city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
			city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
			city.setProvinceId(provinceId);
			list.add(city);
		}while(cursor.moveToNext());
		if(cursor!=null){
			cursor.close();
		}
		return list;
	}
	
	//�洢countyʵ�������ݿ�
	
	public void saveCounty(County county){
		if(county!= null){
			ContentValues values = new ContentValues();
			values.put("county_name",county.getCountyName());
			values.put("county_code",county.getCountyCode());
			values.put("city_id",county.getCityId());
			db.insert("County",null, values);
		}
	}
	
	//���ݿ��л�ȡĳ���е��߳���Ϣ
	
	public List<County> loadCounties(int cityId){
		List<County> list = new ArrayList<County>();
		Cursor  cursor = db.query("County",null, "city_id = ?", new String[]{
				String.valueOf(cityId)
		}, null, null, null);
		
		if(cursor.moveToFirst()){
			do{
				County  county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("cursor_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
			}while(cursor.moveToNext());
		}
		
		if(cursor != null){
			cursor.close();
		}
		return list;
	}
	
}