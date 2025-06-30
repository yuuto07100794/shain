package com.example.shain.dto;

import java.io.Serializable;

// 社員情報DTOクラス
public class ShainInfoDto implements Serializable {
	
	// 社員番号
	private String shainNo;
	
	// 氏名（漢字）
	private String shain_name;
	
	// 氏名（カナ）
	private String shain_kana;
	
	// 部署ID
	private int department_id;
	
	// 部署名
	private String department_name;
	
	
	// デフォルトコンストラクタ（引数なし）
	public ShainInfoDto() {
		
	}
	
	/**
	 * フィールドをまとめて初期化するコンストラクタ
	 * 
	 * @param shainNo			社員番号
	 * @param shain_name		氏名（漢字）
	 * @param shain_kana		氏名（カナ）
	 * @param department_id		部署ID
	 * @param department_name	部署名
	 */
	public ShainInfoDto(String shainNo, String shain_name, String shain_kana, int department_id, String department_name) {
		
		this.shainNo = shainNo;
		this.shain_name = shain_name;
		this.shain_kana = shain_kana;
		this.department_id = department_id;
		this.department_name = department_name;
		
	}
	
	/**
	 * 社員番号を取得するメソッド
	 * 
	 * @return shainNo	社員番号
	 */
	public String getShainNo() {
		return shainNo;
	}

	/**
	 * 社員番号を設定するメソッド
	 * 
	 * @param shainNo	社員番号
	 */
	public void setShainNo(String shainNo) {
		this.shainNo = shainNo;
	}
	
	/**
	 * 氏名（漢字）を取得するメソッド
	 * 
	 * @return shain_name	氏名（漢字）
	 */
	public String getShain_name() {
		return shain_name;
	}

	/**
	 * 氏名（漢字）を設定するメソッド
	 * 
	 * @param shain_name	氏名（漢字）
	 */
	public void setShain_name(String shain_name) {
		this.shain_name = shain_name;
	}

	/**
	 * 氏名（カナ）を取得するメソッド
	 * 
	 * @return shain_kana	氏名（カナ）
	 */
	public String getShain_kana() {
		return shain_kana;
	}

	/**
	 * 氏名（カナ）を設定するメソッド
	 * 
	 * @param shain_kana	氏名（カナ）
	 */
	public void setShain_kana(String shain_kana) {
		this.shain_kana = shain_kana;
	}

	/**
	 * 部署IDを取得するメソッド
	 * 
	 * @return department_id	部署ID
	 */
	public int getDepartment_id() {
		return department_id;
	}

	/**
	 * 部署IDを設定するメソッド
	 * 
	 * @param department_id		部署ID
	 */
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
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
