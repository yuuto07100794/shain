package com.example.shain.gmn01.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shain.constants.Constant;
import com.example.shain.constants.Message;
import com.example.shain.dto.ShainInfoDto;
import com.example.shain.gmn01.form.ShainKensakuForm;
import com.example.shain.gmn01.service.ShainKensakuService;

// 社員検索画面（Gmn01）のコントローラ
@Controller
public class ShainKensakuController {
	
	
	@Autowired
	ShainKensakuService service;
	
	// 全社員の社員情報を取得し、社員検索画面（Gmn01）を表示（★画面初期表示★）
	@GetMapping(value = "/")
	public String DisplaySearchShainPage(@ModelAttribute(Constant.FORM) ShainKensakuForm form, Model model) {
		
		// 部署リスト取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
		model.addAttribute(Constant.DEPARTMENT_LIST, service.getDepartmentListService());
		
		// 初期表示社員情報取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
		model.addAttribute(Constant.SHAIN_LIST, service.getShainInfoService());
		
		return Constant.SHAIN_KENSAKU_PAGE;
	}
	
	// 社員情報の検索を行い、検索結果を、社員検索画面（Gmn01）に表示する
	@PostMapping(value = "/shain/kensaku")
	public String SearchShain(@Validated @ModelAttribute(Constant.FORM) ShainKensakuForm form, BindingResult result, Model model) {
        
		// 部署リスト取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
		model.addAttribute(Constant.DEPARTMENT_LIST, service.getDepartmentListService());		
		
		// エラーメッセージを格納する配列の初期化
		List<String> errorList = new ArrayList<String>();

		// 入力値チェック
		if(result.hasErrors()) {
			// 入力チェックでエラーがあった場合、エラーメッセージを、エラーメッセージリストに格納する
			
			// エラーメッセージの数分処理を繰り返す
	        for (ObjectError error : result.getAllErrors()) {
	            // エラーメッセージを配列に格納する
	            errorList.add(error.getDefaultMessage());
	        }
	        
		}
		
		// エラーメッセージリストの件数判定
		if(errorList.size() > 0) {
			// エラーメッセージリストの件数が0件より多い場合、エラーメッセージを表示し、検索処理を中断する
			
	        // リクエストスコープに、取得したエラーメッセージを設定する
	        model.addAttribute(Constant.ERROR_MESSAGES, errorList);
			
			// 初期表示社員情報取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
			model.addAttribute(Constant.SHAIN_LIST, service.getShainInfoService());
			
			return Constant.SHAIN_KENSAKU_PAGE;
			
		}
		
		// 社員情報検索サービス処理を呼び出し、取得した値をList<社員情報DTO>型のshainListに、設定する
		List<ShainInfoDto> shainList = service.searchShainInfoService(form.getShain_no(), form.getShain_name(), form.getShain_kana(), form.getDepartment_id());
		
		// 社員情報検索結果リストの件数判定
		if(shainList.size() == 0) {
			// 社員情報の検索結果が0件の場合、該当する社員が存在しなかった旨のメッセージを画面に表示する
			
			// エラーメッセージを格納する配列に、「該当する社員が存在しません。」のメッセージを追加する
			errorList.add(Message.ERROR_SHAIN_NOT_FOUND);
		
	        // リクエストスコープに、取得したエラーメッセージを設定する
	        model.addAttribute(Constant.ERROR_MESSAGES, errorList);
	        
		}
		
		// リクエストスコープに、社員情報検索結果リストを設定する
		model.addAttribute(Constant.SHAIN_LIST, shainList);
		
		return Constant.SHAIN_KENSAKU_PAGE;
	}
	
	// 社員検索画面（Gmn01）から社員登録画面（Gmn02）への画面遷移
	@GetMapping(value = "/add")
	public String RegisterShain() {
		
		return "redirect:/add/shain/page";
	}
	
	// 社員検索画面（Gmn01）から社員情報変更画面（Gmn03）への画面遷移
	@GetMapping(value = "/change")
	public String ChangeShain(@RequestParam String shain_no,@RequestParam String shain_name, @RequestParam String shain_kana, @RequestParam int id,RedirectAttributes redirectAttributes) {
		
		// リダイレクト先のControllerに値を渡すため、リクエストスコープに値を設定する
		redirectAttributes.addFlashAttribute(Constant.SHAIN_NO, shain_no);
		redirectAttributes.addFlashAttribute(Constant.SHAIN_NAME, shain_name);
		redirectAttributes.addFlashAttribute(Constant.SHAIN_KANA, shain_kana);
		redirectAttributes.addFlashAttribute(Constant.DEPARTMENT_ID, id);
		
		return "redirect:/change/shain/page";
	}
	
}
