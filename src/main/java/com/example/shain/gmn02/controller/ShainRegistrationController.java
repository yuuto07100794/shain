package com.example.shain.gmn02.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shain.constants.Constant;
import com.example.shain.constants.Message;
import com.example.shain.dto.ShainManagementDto;
import com.example.shain.gmn02.form.ShainRegistrationForm;
import com.example.shain.gmn02.service.ShainRegistrationService;

// 社員登録画面（Gmn02）のコントローラ
@Controller
public class ShainRegistrationController {
	
	@Autowired
	ShainRegistrationService service;
	
	// 社員登録画面（Gmn02）を表示
	@GetMapping(value = "/add/shain/page")
	public String DisplayRegisterShainPage(@ModelAttribute(Constant.FORM) ShainRegistrationForm form, Model model) {
		
		// 部署リスト取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
		model.addAttribute(Constant.DEPARTMENT_LIST, service.getDepartmentListService());
		
		return Constant.SHAIN_REGISTRATION_PAGE;
	}
	
	// 社員情報入力フォームで入力された値の、入力値チェックを行う
	@PostMapping(value = "/shain/registration/input/check")
	public ResponseEntity<Map<String, Object>> RegisterShainInputCheck(@Validated @ModelAttribute(Constant.FORM) ShainRegistrationForm form, BindingResult result, Model model) {
		
		// javaScriptへ結果を返却するための変数 response
		Map<String, Object> response = new HashMap<>();
		
		// エラーメッセージを格納する配列の初期化
		List<String> errorList = new ArrayList<String>();
		
		// 入力値チェック
		if(result.hasErrors()) {
			// 入力値チェックでエラーがあった場合、登録処理は行わない。
			
			// エラーメッセージの数分処理を繰り返す
	        for (ObjectError error : result.getAllErrors()) {
	            // エラーメッセージを配列に格納する
	            errorList.add(error.getDefaultMessage());
	        }
	        
		}
		
		// 社員番号重複チェックサービスを呼び出し、取得した値を、変数 shain_duplicate に、設定する
		boolean shain_duplicate = service.duplicateCheckShainNoService(form.getShain_no());
		
		// 重複チェック結果判定
		if(shain_duplicate) {
			// 社員番号が重複していた場合、社員番号が重複している旨のメッセージを画面に表示する
			
			// エラーメッセージリストに、「入力された社員番号は既に存在しています」のメッセージを追加する
			errorList.add(Message.ERROR_SHAIN_NO_EXISTS);
		}
		
		// エラーメッセージリストの件数判定
		if(errorList.size() > 0) {
			
	        // エラーメッセージリストの件数が0件より多い場合、responseに、errorListを設定する
	        response.put(Constant.ERROR_MSG, errorList);
	        
	        // エラーメッセージリストの件数が0件より多い場合、responseに、falseを設定する
	        response.put(Constant.IS_VALID, false);
	        
		} else {
			
			// エラーメッセージリストの件数が0件の場合、responseに、trueを設定する
			response.put(Constant.IS_VALID, true);
			
		}
		
		// 社員情報登録フォームに設定した値を、リクエストスコープに設定する
		model.addAttribute(Constant.FORM, form);
		
		// 部署リスト取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
		model.addAttribute(Constant.DEPARTMENT_LIST, service.getDepartmentListService());
        
		return ResponseEntity.ok(response);
		
	}
	
	// 社員情報入力フォームで入力された社員情報を、社員管理マスタテーブルに登録する
	@PostMapping(value = "/shain/registration")
	public String RegisterShain(@Validated @ModelAttribute(Constant.FORM) ShainRegistrationForm form, BindingResult result, Model model,HttpSession session) {

		// エラーメッセージを格納する配列の初期化
		List<String> errorList = new ArrayList<String>();
		
		// 社員情報の登録値設定
		
		// 社員管理マスタDTOクラスをインスタンス化
		ShainManagementDto shain_manage = new ShainManagementDto();
		
		// 社員情報入力フォームに設定された情報を、社員管理マスタDTOクラスに設定する
		BeanUtils.copyProperties(form, shain_manage);
		
		// 社員管理マスタDTOクラスに、社員番号を設定する
		shain_manage.setShainNo(form.getShain_no());
		
		// 作成日時、更新日時に、現在のシステム日時を設定するため、現在日時を取得し、変数 timestamp に設定する
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		// 作成日時、更新日時に、現在のシステム日時を設定する
		shain_manage.setCr_date(timestamp);
		shain_manage.setUp_date(timestamp);
		
		// 作成者、更新者に、「登録ユーザ」を設定する
		shain_manage.setCr_usr(Constant.REGISTRATION_USR);
		shain_manage.setUp_usr(Constant.REGISTRATION_USR);
		
		// 削除フラグに「0」を設定する
		shain_manage.setFlag(Constant.DEL_FG_NOT_DELETED);
		
		// 社員情報登録サービスを呼び出し、登録結果を、boolean型で受け取る
		boolean ShainRegistrationCheck = service.registrationShainService(shain_manage);
		
		// 登録結果判定
		if(!ShainRegistrationCheck) {
			
			// 社員情報登録処理でエラーとなった場合
			
			// 画面に、「社員情報の登録ができませんでした。」のメッセージを表示する
			errorList.add(Message.ERROR_SHAIN_REGISTRATION_FAILED);

	        // リクエストスコープに、取得したエラーメッセージを設定する
	        model.addAttribute(Constant.ERROR_MESSAGES, errorList);
	        
			// 社員情報登録フォームに設定した値を、リクエストスコープに設定する
			model.addAttribute(Constant.FORM, form);
			
			// 部署リスト取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
			model.addAttribute(Constant.DEPARTMENT_LIST, service.getDepartmentListService());
			
			return Constant.SHAIN_REGISTRATION_PAGE;		
		}

		// リダイレクト先のControllerに、社員番号の値を渡すため、セッションスコープに、社員番号の値を設定する
		session.setAttribute(Constant.SHAIN_NO, shain_manage.getShainNo());
		
		return "redirect:/registration/finish";
	}	
	
	// 社員登録画面（Gmn02）から社員検索画面（Gmn01）への画面遷移
	@GetMapping(value = "/shain/registration/back")
	public String RegistrationShainPageBack() {
		
		return "redirect:/";
	}
	
	// 社員登録画面（Gmn02）から処理終了画面（Gmn04）への画面遷移
	@GetMapping(value = "/registration/finish")
	public String CompleteProcessRegistration(RedirectAttributes redirectAttributes) {
		
		// 登録処理を行ったため、「process」のリクエストスコープに、登録を設定する
		redirectAttributes.addFlashAttribute(Constant.PROCESS, Constant.REGISTRATION);
		
		return "redirect:/process/finish";
	}

}
