package com.example.shain.gmn04.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.shain.constants.Constant;

// 処理完了画面（Gmn04）のコントローラ
@Controller
public class ProcessCompleteController {
	
	// 処理完了画面（Gmn04）に、処理結果を表示する
	@GetMapping(value = "/process/finish")
	public String DisplayCompleteProcessPage(Model model) {
		
		// 「process」のリクエストスコープに設定された値を取得する
		String process = (String) model.getAttribute(Constant.PROCESS);
		
		// リクエストスコープに、変数 process の値を設定する
		model.addAttribute(Constant.PROCESS, process);
		
		return Constant.PROCESS_COMPLETE_PAGE;
	}
	
	// 処理完了画面（Gmn04）から社員検索画面（Gmn01）への画面遷移
	@GetMapping(value = "/shain/kensaku/page")
	public String SearchShainPage(HttpSession session) {
		
		// セッションスコープ「shain_no」に設定された値を削除する
		session.removeAttribute(Constant.SHAIN_NO);
		
		return "redirect:/";
	}
	
	
	
}
