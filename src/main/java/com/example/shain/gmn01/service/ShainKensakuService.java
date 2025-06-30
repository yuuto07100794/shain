package com.example.shain.gmn01.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.shain.constants.Constant;
import com.example.shain.dto.DepartmentDto;
import com.example.shain.dto.ShainInfoDto;
import com.example.shain.entity.DepartmentEntity;
import com.example.shain.entity.ShainEntity;
import com.example.shain.repository.DepartmentRepository;
import com.example.shain.repository.ShainRepository;

// 社員検索画面（Gmn01）のサービスクラス
@Service
public class ShainKensakuService {
	
	@Autowired
	DepartmentRepository department_repository;
	
	@Autowired
	ShainRepository shain_repository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
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
	 * 初期表示社員情報取得サービス
	 * 
	 * @return shainList	初期表示社員情報リスト（List<ShainInfoDto>）
	 */
	public List<ShainInfoDto> getShainInfoService() {
		
		// ModelMapperクラスインスタンス化
		ModelMapper modelMapper = new ModelMapper();
		
		// 初期表示社員情報リスト取得処理（DAO）を呼び出し、List<ShainEntity>型で受け取る
		List<ShainEntity> shainList = shain_repository.findActiveShainWithActiveDepartment();
	
		// 初期表示社員情報リストを、List<ShainInfoDto>型に変換して、値を返却する
        return shainList.stream()
                .map(shain -> modelMapper.map(shain, ShainInfoDto.class))
                .collect(Collectors.toList());
	}

	/**
	 * 社員情報検索サービス
	 * 
	 * @param	shainNo			社員番号（String）
	 * @param	shain_name		氏名（漢字）（String）
	 * @param	shain_kana		氏名（カナ）（String）
	 * @param 	department_id	部署ID（int）
	 * 
	 * @return	shainList		社員情報検索結果リスト（List<ShainInfoDto>）
	 */
	public List<ShainInfoDto> searchShainInfoService(String shainNo, String shain_name, String shain_kana,int department_id) {
		
		// 社員情報検索SQL文字列作成処理（DAO）を呼び出し、作成した社員情報の検索用SQL文字列を、変数 query に、設定する
		String query = ShainRepository.SearchShainCreateSQL(shainNo, shain_name, shain_kana, department_id);
		
		// 社員情報検索結果一時リスト取得処理（DAO）を呼び出し、取得した値をList<Map<String, Object>>型で受け取る
		List<Map<String, Object>> shainListTmp = jdbcTemplate.queryForList(query);
		
		// 社員情報検索結果型変換サービスを呼び出し、取得した値をList<ShainInfoDto>型で受け取る
		List<ShainInfoDto> shainList = convertToTypeChangeListService(shainListTmp);
		
		// List<ShainInfoDto>型の shainList を返却する
		return shainList;
	}

	/**
	 * 社員情報検索結果型変換サービス
	 * 
	 * @param	shainListTmp	社員情報検索結果一時リスト（List<Map<String, Object>>）
	 * 
	 * @return	shainList		社員情報検索結果リスト（List<ShainInfoDto>）
	 */
	public List<ShainInfoDto> convertToTypeChangeListService(List<Map<String, Object>> shainListTmp) {
		// List<Map<String, Object>>型 から List<ShainInfoDto>型に変換する
		
		// List<ShainInfoDto>型の初期化
		List<ShainInfoDto> shainList = new ArrayList<ShainInfoDto>();
		
		// List<Map<String, Object>型の要素数分処理を繰り返す
		for(int i = 0;i < shainListTmp.size();i++) {
			
			// 社員管理DTOクラスのインスタンス化
			ShainInfoDto shain = new ShainInfoDto();
			
			// 社員管理DTOクラスに、社員番号を設定
			shain.setShainNo((String) shainListTmp.get(i).get(Constant.SHAIN_NO));
			
			// 社員管理DTOクラスに、氏名（漢字）を設定
			shain.setShain_name((String) shainListTmp.get(i).get(Constant.SHAIN_NAME));
			
			// 社員管理DTOクラスに、氏名（カナ）を設定
			shain.setShain_kana((String) shainListTmp.get(i).get(Constant.SHAIN_KANA));

			// 社員管理DTOクラスに、部署IDを設定
			shain.setDepartment_id((int) shainListTmp.get(i).get(Constant.DEPARTMENT_ID));
			
			// 社員管理DTOクラスに、部署名を設定
			shain.setDepartment_name((String) shainListTmp.get(i).get(Constant.DEPARTMENT_NAME));
			
			// List<ShainInfoDto> shainList に、社員管理DTOクラスに設定した値を追加
			shainList.add(shain);
			
		}
		
		// List<ShainInfoDto>型の shainList を返却する
		return shainList;		
	}
	
}
