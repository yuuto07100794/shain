package com.example.shain.gmn03.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shain.constants.Constant;
import com.example.shain.constants.Message;
import com.example.shain.dto.ShainManagementDto;
import com.example.shain.gmn03.form.ShainChangeForm;
import com.example.shain.gmn03.service.ShainChangeService;

// 社員情報変更画面（Gmn03）のコントローラ
@Controller
public class ShainChangeController {
	
	@Autowired
	ShainChangeService service;
	
	// 社員情報変更画面（Gmn03）を表示する
	@GetMapping(value = "/change/shain/page")
	public String DisplayChangeShainPage(ShainChangeForm form, Model model) {
		
		// リクエストスコープに設定した値を取得し、社員情報変更フォームに値を設定する
		form.setShain_no((String) model.getAttribute(Constant.SHAIN_NO));
		form.setShain_name((String) model.getAttribute(Constant.SHAIN_NAME));
		form.setShain_kana((String) model.getAttribute(Constant.SHAIN_KANA));
		form.setDepartment_id((int) model.getAttribute(Constant.DEPARTMENT_ID));
		
		// 社員情報変更フォームに設定した値を、リクエストスコープに設定する
		model.addAttribute(Constant.FORM, form);
		
		// 部署リスト取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
		model.addAttribute(Constant.DEPARTMENT_LIST, service.getDepartmentListService());
		
		return Constant.SHAIN_CHANGE_PAGE;
	}
	
	// 社員情報入力フォームに、入力された社員情報で、社員管理マスタテーブルのレコードを更新する
	@PostMapping(value = "/shain/change")
	public String ChangeShain(@ModelAttribute(Constant.FORM) ShainChangeForm form, boolean change_process_check, Model model,HttpSession session, RedirectAttributes redirectAttributes) {
		
		// 確認ダイアログの選択結果の判定
		if(change_process_check == false) {
			// 確認ダイアログで、キャンセルを選択した場合、社員情報の変更処理は行わない。
			
			// 社員情報変更フォームに設定した値を、リクエストスコープに設定する
			model.addAttribute(Constant.FORM, form);
			
			// 部署リスト取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
			model.addAttribute(Constant.DEPARTMENT_LIST, service.getDepartmentListService());
			
			return Constant.SHAIN_CHANGE_PAGE;
		}
		
		// エラーメッセージを格納する配列の初期化
		List<String> errorList = new ArrayList<String>();
		
		// 社員情報の変更値設定
		
		// 社員管理マスタDTOクラスをインスタンス化
		ShainManagementDto shain_manage = new ShainManagementDto();
		
		// 社員情報入力フォームに設定された情報を、社員管理マスタDTOクラスに設定する
		BeanUtils.copyProperties(form, shain_manage);
		
		// 社員管理マスタDTOクラスに、社員番号を設定する
		shain_manage.setShainNo(form.getShain_no());

		// 更新日時に、現在のシステム日時を設定するため、現在日時を取得し、変数 timestamp に設定する
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		// 更新日時に、現在のシステム日時を設定する
		shain_manage.setUp_date(timestamp);
		
		// 更新者に、「更新ユーザ」を設定する
		shain_manage.setUp_usr(Constant.UPDATE_USR);
		
		// 社員情報変更サービスを呼び出し、変更結果を、boolean型で受け取る
		boolean ShainChangeCheck = service.ChangeShainInfoService(shain_manage);
		
		// 社員情報変更結果判定
		if(!ShainChangeCheck) {
			
			// 社員情報変更処理でエラーとなった場合
			
			// 変更処理でエラーとなった場合、画面に、「社員情報の変更ができませんでした。」のメッセージを表示する
			errorList.add(Message.ERROR_SHAIN_CHANGE_FAILED);
			
	        // リクエストスコープに、取得したエラーメッセージを設定する
	        model.addAttribute(Constant.ERROR_MESSAGES, errorList);
	        
			// 社員情報変更フォームに設定した値を、リクエストスコープに設定する
			model.addAttribute(Constant.FORM, form);
			
			// 部署リスト取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
			model.addAttribute(Constant.DEPARTMENT_LIST, service.getDepartmentListService());
			
			return Constant.SHAIN_CHANGE_PAGE;
		}
		
		// リダイレクト先のControllerに、社員番号の値を渡すため、セッションスコープに、社員番号の値を設定する
		session.setAttribute(Constant.SHAIN_NO, shain_manage.getShainNo());
		
		return "redirect:/change/finish";
	}
	
	// 社員情報入力フォームに、入力された社員情報を、社員管理マスタテーブルから削除する（削除フラグを、「1」に更新する）
	@PostMapping(value = "/shain/delete")
	public String DeleteShain(@ModelAttribute(Constant.FORM) ShainChangeForm form, boolean delete_process_check, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		// 確認ダイアログの選択結果の判定
		if(delete_process_check == false) {
			// 確認ダイアログで、キャンセルを選択した場合、社員情報の変更処理は行わない。
			
			// フォームに設定した値をリダイレクト先に渡すため、リクエストスコープに、設定する
			redirectAttributes.addFlashAttribute(Constant.SHAIN_NO, form.getShain_no());
			redirectAttributes.addFlashAttribute(Constant.SHAIN_NAME, form.getShain_name());
			redirectAttributes.addFlashAttribute(Constant.SHAIN_KANA, form.getShain_kana());
			redirectAttributes.addFlashAttribute(Constant.DEPARTMENT_ID, form.getDepartment_id());
			
			return "redirect:/change/shain/page";
		}
		
		// エラーメッセージを格納する配列の初期化
		List<String> errorList = new ArrayList<String>();
		
		// 社員情報の更新値設定
		
		// 社員管理マスタDTOクラスをインスタンス化
		ShainManagementDto shain_manage = new ShainManagementDto();
		
		// 社員情報入力フォームに設定された情報を、社員管理マスタDTOクラスに設定する
		BeanUtils.copyProperties(form, shain_manage);
		
		// 社員管理マスタDTOクラスに、社員番号を設定する
		shain_manage.setShainNo(form.getShain_no());
		
		// 更新日時に、現在日時をTimestamp型で取得した値を設定する
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		// 更新日時に、現在のシステム日時を設定する
		shain_manage.setUp_date(timestamp);
		
		// 更新者に、「削除ユーザ」を設定する
		shain_manage.setUp_usr(Constant.DELETE_USR);
		
		// 削除フラグに、「1」を設定する
		shain_manage.setFlag(Constant.DEL_FG_DELETED);
		
		// 社員情報削除サービスを呼び出し、削除結果を、boolean型で受け取る
		boolean ShainDeleteCheck = service.DeleteShainInfoService(shain_manage);		
		
		// 社員情報削除結果判定
		if(!ShainDeleteCheck) {
			
			// 社員情報削除処理でエラーとなった場合
			
			// 削除処理でエラーとなった場合、画面に、「社員情報の削除ができませんでした。」のメッセージを表示する
			errorList.add(Message.ERROR_SHAIN_DELETE_FAILED);
			
	        // リクエストスコープに、取得したエラーメッセージを設定する
	        model.addAttribute(Constant.ERROR_MESSAGES, errorList);

			// 社員情報変更フォームに設定した値を、リクエストスコープに設定する
			model.addAttribute(Constant.FORM, form);
			
			// 部署リスト取得サービス処理を呼び出し、取得した値をリクエストスコープに、設定する
			model.addAttribute(Constant.DEPARTMENT_LIST, service.getDepartmentListService());
			
			return Constant.SHAIN_CHANGE_PAGE;
		}		
		
		// リダイレクト先のControllerに、社員番号の値を渡すため、セッションスコープに、社員番号の値を設定する
		session.setAttribute(Constant.SHAIN_NO, shain_manage.getShainNo());
		
		return "redirect:/delete/finish";
	}
	
	// 社員情報変更画面（Gmn03）から社員検索画面（Gmn01）への画面遷移
	@GetMapping(value = "/shain/change/back")
	public String ChangeShainPageBack() {
		
		return "redirect:/";
	}
	
	// 社員情報変更画面（Gmn02）から処理終了画面（Gmn04）への画面遷移（社員変更処理実施時）
	@GetMapping(value = "/change/finish")
	public String CompleteProcessChange(RedirectAttributes redirectAttributes) {
		
		// 変更処理を行ったため、「process」のリクエストスコープに、変更を設定する
		redirectAttributes.addFlashAttribute(Constant.PROCESS, Constant.CHANGE);
		
		return "redirect:/process/finish";
	}
	
	// 社員情報変更画面（Gmn02）から処理終了画面（Gmn04）への画面遷移（社員変更処理実施時）
	@GetMapping(value = "/delete/finish")
	public String CompleteProcessDelete(RedirectAttributes redirectAttributes) {
		
		// 削除処理を行ったため、「process」のリクエストスコープに、削除を設定する
		redirectAttributes.addFlashAttribute(Constant.PROCESS, Constant.DELETE);
		
		return "redirect:/process/finish";
	}
	
}
