package com.example.shain.gmn03.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shain.constants.Constant;
import com.example.shain.dto.DepartmentDto;
import com.example.shain.dto.ShainManagementDto;
import com.example.shain.entity.DepartmentEntity;
import com.example.shain.entity.ShainEntity;
import com.example.shain.repository.DepartmentRepository;
import com.example.shain.repository.ShainRepository;

//社員情報変更画面（Gmn03）のサービスクラス
@Service
public class ShainChangeService {

	@Autowired
	DepartmentRepository department_repository;

	@Autowired
	ShainRepository shain_repository;
	
	
	/**
	 * 部署リスト作成サービス
	 * 
	 * @return departmentList	部署リスト（List<DepartmentDto>）
	 */
	public List<DepartmentDto> getDepartmentListService() {
		
		// ModelMapperクラスインスタンス化
		ModelMapper modelMapper = new ModelMapper();
		
		// 部署リスト取得処理（DAO）を呼び出し、List<DepartmentEntity>型で受け取る
		List<DepartmentEntity> departmentList = department_repository.findByFlagOrderById(Constant.DEL_FG_NOT_DELETED);
		
		// 部署リストを、List<DepartmentDto>型に変換して、値を返却する
        return departmentList.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
	}
	
	/**
	 * 社員情報変更サービス
	 * 
	 * @param	shain_manage	社員情報変更値（ShainManagementDto）
	 * 
	 * @return	true/false		変更結果（boolean）
	 */
	public boolean ChangeShainInfoService(ShainManagementDto shain_manage) {
		
		// 変更対象の社員の社員情報を取得
		ShainEntity shain = shain_repository.findByShainNo(shain_manage.getShainNo());
		
		// 社員情報入力フォームに設定された情報を、社員管理マスタテーブルのエンティティに設定する
		BeanUtils.copyProperties(shain_manage, shain, Constant.CR_DATE, Constant.CR_USR, Constant.FLAG);
		
		try {
			
			// 社員情報変更処理（DAO）を呼び出し、社員情報の変更処理を行う
			shain_repository.save(shain);
			
		}catch (Exception e) {
			
			// 変更失敗のため、falseを返却する
			return false;
			
		}
		
		// 変更成功のため、trueを返却する
		return true;
	}

	/**
	 * 社員情報削除サービス
	 * 
	 * @param	shain_manage	社員情報更新値（ShainManagementDto）
	 * 
	 * @return	true/false		削除結果（boolean）
	 */
	public boolean DeleteShainInfoService(ShainManagementDto shain_manage) {
		
		// 削除対象の社員の社員情報を取得
		ShainEntity shain = shain_repository.findByShainNo(shain_manage.getShainNo());

		// 社員情報入力フォームに設定された情報を、社員管理マスタテーブルのエンティティに設定する
		BeanUtils.copyProperties(shain_manage, shain, Constant.CR_DATE, Constant.CR_USR);
		
		try {
			
			// 社員情報削除処理（DAO）を呼び出し、社員情報の削除処理を行う
			shain_repository.save(shain);
			
		}catch (Exception e) {
			
			// 削除失敗のため、falseを返却する
			return false;
			
		}
		
		// 削除成功のため、trueを返却する
		return true;
	}
}
