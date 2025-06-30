package com.example.shain.dto;

import java.io.Serializable;
import java.util.Date;

// 社員管理マスタDTOクラス
public class ShainManagementDto implements Serializable {
	
	// 社員番号
	private String shainNo;
	
	// 氏名（漢字）
	private String shain_name;
	
	// 氏名（カナ）
	private String shain_kana;
	
	// 部署ID
	private int department_id;
	
	// 備考
	private String bikou;
	
	// 作成日時
	private Date cr_date;
	
	// 作成者
	private String cr_usr;
	
	// 更新日時
	private Date up_date;
	
	// 更新者
	private String up_usr;
	
	// 削除フラグ
	private String flag;
	
	
	// デフォルトコンストラクタ（引数なし）
	public ShainManagementDto() {
		
	}
	
	/**
	 * フィールドをまとめて初期化するコンストラクタ
	 * 
	 * @param shainNo			社員番号
	 * @param shain_name		氏名（漢字）
	 * @param shain_kana		氏名（カナ）
	 * @param department_id		部署ID
	 * @param bikou				備考
	 * @param cr_date			作成日時
	 * @param cr_usr			作成者
	 * @param up_date			更新日時
	 * @param up_usr			更新者
	 * @param flag				削除フラグ
	 */
	public ShainManagementDto(String shainNo, String shain_name, String shain_kana, int department_id, String bikou, Date cr_date, String cr_usr, Date up_date, String up_usr, String flag) {
		
		this.shainNo = shainNo;
		this.shain_name = shain_name;
		this.shain_kana = shain_kana;
		this.department_id = department_id;
		this.bikou = bikou;
		this.cr_date = cr_date;
		this.cr_usr = cr_usr;
		this.up_date = up_date;
		this.up_usr = up_usr;
		this.flag = flag;
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
	 * 備考を取得するメソッド
	 * 
	 * @return bikou	備考
	 */
	public String getBikou() {
		return bikou;
	}

	/**
	 * 備考を設定するメソッド
	 * 
	 * @param bikou		備考
	 */
	public void setBikou(String bikou) {
		this.bikou = bikou;
	}

	/**
	 * 作成日時を取得するメソッド
	 * 
	 * @return cr_date	作成日時
	 */
	public Date getCr_date() {
		return cr_date;
	}

	/**
	 * 作成日時を設定するメソッド
	 * 
	 * @param cr_date	作成日時
	 */
	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	/**
	 * 作成者を取得するメソッド
	 * 
	 * @return cr_usr	作成者
	 */
	public String getCr_usr() {
		return cr_usr;
	}

	/**
	 * 作成者を設定するメソッド
	 * 
	 * @param cr_usr	作成者
	 */
	public void setCr_usr(String cr_usr) {
		this.cr_usr = cr_usr;
	}

	/**
	 * 更新日時を取得するメソッド
	 * 
	 * @return up_date	更新日時
	 */
	public Date getUp_date() {
		return up_date;
	}

	/**
	 * 更新日時を設定するメソッド
	 * 
	 * @param up_date	更新日時
	 */
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}

	/**
	 * 更新者を取得するメソッド
	 * 
	 * @return up_usr	更新者
	 */
	public String getUp_usr() {
		return up_usr;
	}

	/**
	 * 更新者を設定するメソッド
	 * 
	 * @param up_usr	更新者
	 */
	public void setUp_usr(String up_usr) {
		this.up_usr = up_usr;
	}

	/**
	 * 削除フラグを取得するメソッド
	 * 
	 * @return flag		削除フラグ
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 削除フラグを設定するメソッド
	 * 
	 * @param flag		削除フラグ
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}	

}
