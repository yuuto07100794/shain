// クリアボタン押下時に、社員情報の入力フォームに入力されている値をクリアする関数
function clearForm() {
	
	// 社員情報の入力フォームに設定された値を取得する
	var shain_info = document.getElementById("shain_info");
	
	// フォームの値をリセットする
	shain_info.reset();
	
	return;
}

// 確認ダイアログの選択結果を、返す関数（登録処理用）
function RegistrationProcessCheck() {
	
	// 社員情報の入力フォームのregistration_process_checkの値を取得する
	var registration_process_check = document.getElementById("registration_process_check");
	
	// 確認ダイアログの選択結果を判定
	if(confirm('入力された内容で変更します。よろしいですか？')) {
		// 確認ダイアログでOKを選択した場合、registration_process_checkに、「true」を設定する
		registration_process_check.value = true;
	} else {
		// 確認ダイアログでキャンセルを選択した場合、registration_process_checkに、「false」を設定する
		registration_process_check.value = false;
	}
	
	return;
}


// 確認ダイアログの選択結果を、返す関数（変更処理用）
function ChangeProcessCheck() {
	
	// 社員情報の入力フォームのchange_process_checkの値を取得する
	var change_process_check = document.getElementById("change_process_check");
	
	// 確認ダイアログの選択結果を判定
	if(confirm('入力された内容で変更します。よろしいですか？')) {
		// 確認ダイアログでOKを選択した場合、change_process_checkに、「true」を設定する
		change_process_check.value = true;
	} else {
		// 確認ダイアログでキャンセルを選択した場合、change_process_checkに、「false」を設定する
		change_process_check.value = false;
	}
	
	return;
}

// 確認ダイアログの選択結果を、返す関数（削除処理用）
function DeleteProcessCheck() {
	
	// 社員情報の入力フォームのdelete_process_checkの値を取得する
	var delete_process_check = document.getElementById("delete_process_check");
	
	// 確認ダイアログの選択結果を判定
	if(confirm('入力された内容で削除します。よろしいですか？')) {
		// 確認ダイアログでOKを選択した場合、delete_process_checkに、「true」を設定する
		delete_process_check.value = true;
	} else {
		// 確認ダイアログでキャンセルを選択した場合、delete_process_checkに、「false」を設定する
		delete_process_check.value = false;
	}
	
	return;
}

// 登録ボタンがクリックされたときの処理
function RegistrationProcess() {
	
    // 社員情報登録フォームのデータを取得
	
    const form = new FormData(document.getElementById("shain_info"));
    
    // サーバに入力値のチェックをリクエスト
    fetch('/shain/shain/registration/input/check', {
        method: 'POST',
        body: form
    })
    .then(response => response.json())
    .then(data => {
        if (data.isValid) {
            // 入力が有効なら確認ダイアログを表示
            const userConfirmed = confirm("入力された内容で登録します。よろしいですか？");
            if (userConfirmed) {
                // ユーザーが「はい」を選択したら、登録を実行
                document.getElementById("shain_form").submit();
            }
        } else {
            
			// エラーメッセージを表示させるため、returnする
			return;
        }
    });
}