package com.example.shain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shain.entity.DepartmentEntity;

// 部署マスタテーブル（m_department）のリポジトリ
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity ,Integer>{
	
	/* 部署リスト取得 */
	// 部署マスタテーブルで、削除フラグが0のデータを取得
	List<DepartmentEntity> findByFlagOrderById(String flag);
	
}
