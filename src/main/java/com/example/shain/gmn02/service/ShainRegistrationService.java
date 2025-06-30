package com.example.shain.gmn02.service;

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

// 社員登録画面（Gmn02）のサービスクラス
@Service
public class ShainRegistrationService {

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
	 * 社員番号重複チェックサービス
	 * 
	 * @param	shainNo		社員番号（String）
	 * 
	 * @return	true/false	重複有無（boolean）
	 */
	public boolean duplicateCheckShainNoService(String shainNo) {
		
		// 社員番号検索処理（DAO）を呼び出し、int型で受け取る
		int shainSearchCnt = shain_repository.countByShainNo(shainNo);
		
		// 社員番号重複有無判定
		if(shainSearchCnt > 0) {
			
			// 重複あり のため、trueを返却する
			return true;
			
		} else {
			
			// 重複なし のため、falseを返却する
			return false;
			
		}
		
	}
	
	/**
	 * 社員情報登録サービス
	 * 
	 * @param	shain_manage	社員情報登録値（ShainManagementDto）
	 * 
	 * @return	true/false		登録結果（boolean）
	 */
	public boolean registrationShainService(ShainManagementDto shain_manage) {
		
		// 社員管理マスタテーブルのエンティティクラスをインスタンス化
		ShainEntity shain = new ShainEntity();

		// 社員管理マスタDTOクラスに設定された情報を、社員管理マスタテーブルのエンティティクラスに設定する
		BeanUtils.copyProperties(shain_manage, shain);

		try {
			
			// 社員情報登録処理（DAO）を呼び出し、社員情報の登録処理を行う
			shain_repository.save(shain);
			
		}catch(Exception e) {
				
			// 登録失敗のため、falseを返却する
			return false;
			
		}
		
		// 登録成功のため、trueを返却する
		return true;
	}
}
