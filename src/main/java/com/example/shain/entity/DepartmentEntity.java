package com.example.shain.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.shain.constants.Constant;

// 部署マスタテーブル（m_department）エンティティクラス
@Entity
@Table(name = Constant.DEPARTMENT_TB,schema = Constant.SCHEMA)
public class DepartmentEntity {
	
	// 部署ID
	@Id
	@Column(name = Constant.DEPARTMENT_ID)
	private int id;
	
	// 部署名
	private String department_name;
	
	// 作成日時
	private Date cr_date;
	
	// 作成者
	private String cr_usr;
	
	// 更新日時
	private Date up_date;
	
	// 更新者
	private String up_usr;
	
	// 削除フラグ
	@Column(name = Constant.DEL_FG)
	private String flag;
	

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
