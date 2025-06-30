package com.example.shain.constants;

// 定数管理クラス
public class Constant {
	
	// スコープ設定用変数
	public static final String DEPARTMENT_LIST = "departmentList";
	public static final String SHAIN_LIST = "shainList";
	public static final String FORM = "form";
	public static final String ERROR_MESSAGES = "error_messages";
	public static final String ERROR_MSG = "errorMsg";
	public static final String IS_VALID = "isValid";
	public static final String PROCESS = "process";
	
	// 項目名
	public static final String SHAIN_NO = "shain_no";
	public static final String SHAIN_NAME = "shain_name";
	public static final String SHAIN_KANA = "shain_kana";
	public static final String DEPARTMENT_ID = "department_id";
	public static final String DEPARTMENT_NAME = "department_name";
	public static final String CR_DATE = "cr_date";
	public static final String CR_USR = "cr_usr";
	public static final String DEL_FG = "del_fg";
	public static final String FLAG = "flag";
	
	// DB情報
	public static final String SCHEMA = "shain";
	public static final String SHAIN_TB = "m_shain";
	public static final String DEPARTMENT_TB = "m_department";
	
	// 作成者、更新者に設定するユーザ
	public static final String REGISTRATION_USR = "登録ユーザ";
	public static final String UPDATE_USR = "更新ユーザ";
	public static final String DELETE_USR = "削除ユーザ";
	
	// 処理完了画面に表示させる処理名
	public static final String REGISTRATION = "登録";
	public static final String CHANGE = "変更";
	public static final String DELETE = "削除";
	
	// 正規表現（半角英数字チェック）
	public static final String ERROR_ALPHANUMERIC_ONLY = "^[0-9a-zA-Z]*$";
	
	// 削除フラグ
	public static final String DEL_FG_NOT_DELETED = "0";
	public static final String DEL_FG_DELETED = "1";
	
	// HTMLファイル名
	public static final String SHAIN_KENSAKU_PAGE = "ShainKensaku";
	public static final String SHAIN_REGISTRATION_PAGE = "ShainRegistration";
	public static final String SHAIN_CHANGE_PAGE = "ShainChange";
	public static final String PROCESS_COMPLETE_PAGE = "ProcessComplete";
	
	
}
