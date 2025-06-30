package com.example.shain.gmn03.form;

import java.io.Serializable;

//社員情報（変更用）の入力情報を管理するフォームクラス
public class ShainChangeForm implements Serializable{
	
	// 社員番号
	private String shain_no;
	
	// 部署ID
	private int department_id;
	
	// 氏名（漢字）
	private String shain_name;
	
	// 氏名（カナ）
	private String shain_kana;
	

	/**
	 * 社員番号を取得するメソッド
	 * 
	 * @return shain_no		社員番号
	 */	
	public String getShain_no() {
		return shain_no;
	}

	/**
	 * 社員番号を設定するメソッド
	 * 
	 * @param shain_no		社員番号
	 */	
	public void setShain_no(String shain_no) {
		this.shain_no = shain_no;
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
	
}
