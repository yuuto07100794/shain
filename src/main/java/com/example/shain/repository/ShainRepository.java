package com.example.shain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.shain.entity.ShainEntity;

// 社員管理マスタテーブル（m_shain）のリポジトリ
@Repository
public interface ShainRepository extends JpaRepository<ShainEntity, String>{

	/* 初期表示社員情報リスト取得 */
	// 社員管理マスタテーブルで、削除フラグが0のデータを取得
	@Query("SELECT s FROM ShainEntity s WHERE s.flag = '0' AND s.department.flag = '0' ORDER BY s.shainNo")
	List<ShainEntity> findActiveShainWithActiveDepartment();
	
	/* 社員情報取得 （変更・削除対象の特定） */
	// 社員管理マスタテーブルで、指定した社員番号のデータを取得
	ShainEntity findByShainNo(String shainNo);
	
	/* 社員番号検索処理 */
	// 社員管理マスタテーブルで、指定した社員番号のレコード数を取得
	int countByShainNo(String shainNo);
	
	
	/**
	 * 社員情報検索SQL文字列作成処理
	 * 
	 * @param shain_no			社員番号（String）
	 * @param shain_name		氏名（漢字）（String）
	 * @param shain_kana		氏名（カナ）（String）
	 * @param department_id		部署ID（int）
	 * 
	 * @return query	社員情報の検索用SQL文字列（String）
	 */	
	public static String SearchShainCreateSQL(String shain_no, String shain_name, String shain_kana, int department_id) {
		
		// 作成したSQLを格納する変数 query を定義する
		String query = "SELECT * FROM shain.m_shain AS s INNER JOIN shain.m_department AS d ON s.department_id = d.department_id WHERE 1 = 1";
		
		// 社員番号が未入力かチェック
		if(!shain_no.isEmpty()) {
			// 社員番号が入力されていた場合、「入力された社員番号を含む」条件をWHERE句に指定する
			query += " AND shain_no LIKE '%" + shain_no + "%'";
		}
		
		// 氏名（漢字）が未入力かチェック
		if(!shain_name.isEmpty()) {
			// 氏名（漢字）が入力されていた場合、「入力された氏名（漢字）を含む」条件をWHERE句に指定する
			query += " AND shain_name LIKE '%" + shain_name + "%'";
		}
		
		// 氏名（カナ）が未入力かチェック
		if(!shain_kana.isEmpty()) {
			// 氏名（カナ）が入力されていた場合、「入力された氏名（カナ）を含む」条件をWHERE句に指定する
			query += " AND shain_kana LIKE '%" + shain_kana + "%'";
		}
		
		// 部署IDが選択されていないかチェック
		if(department_id != 0) {
			// 部署IDが選択されていた場合、「指定した部署ID」の条件をWHERE句に指定する
			query += " AND s.department_id = " + department_id;
		}
		
		// 作成したSQL文に、「削除フラグが0」の条件をWHERE句に指定する
		query += " AND s.del_fg = '0' AND d.del_fg = '0'";
		
		// 作成したSQL文に、「社員番号の昇順」の条件をORDER BY句に指定する
		query += " ORDER BY s.shain_no";
		
		// 作成したSQL文の末尾に「;」を設定する
		query += ";";
		
		return query;
	}
}
