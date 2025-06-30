package com.example.shain.dto;

import java.io.Serializable;

// 部署DTOクラス
public class DepartmentDto implements Serializable {
	
	// 部署ID
	private int id;
	
	// 部署名
	private String department_name;
	
	
	// デフォルトコンストラクタ（引数なし）
	public DepartmentDto() {
		
	}
	
	/**
	 * フィールドをまとめて初期化するコンストラクタ
	 * 
	 * @param id				部署ID
	 * @param department_name	部署名
	 */
	public DepartmentDto(int id, String department_name) {
		
		this.id = id;
		this.department_name = department_name;
		
	}
	
	/**
	 * 部署IDを取得するメソッド
	 * 
	 * @return id	部署ID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 部署IDを設定するメソッド
	 * 
	 * @param id	部署ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 部署名を取得するメソッド
	 * 
	 * @return department_name	部署名
	 */
	public String getDepartment_name() {
		return department_name;
	}
	
	/**
	 * 部署名を設定するメソッド
	 * 
	 * @param department_name	部署名
	 */
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	
}
