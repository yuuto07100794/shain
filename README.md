# システムの概要

社員情報の登録・変更・削除、社員検索処理が行えるWebアプリケーションです。


# 使用技術

・Java 21

・Spring Boot 3.5.3

・PostgreSQL

・HTML/CSS

・JavaScript

・Maven

・Thymeleaf（テンプレート）

・Eclipse IDE（推奨）

# セットアップ手順

## 1. Eclipse（日本語版）でリポジトリをクローンする

1-① Eclipse を起動する

1-② メニューから「ファイル」→「インポート」を選択する 

1-③ 「Git」→「Gitからプロジェクト」を選択して「次へ」を選択する  

1-④ 「クローンURI」を選択し「次へ」を選択する

1-⑤ 以下のリポジトリ URL を入力し「次へ」を選択する

　・https://github.com/yuuto07100794/shain.git

1-⑥ 使用するブランチ（通常は "main"）を確認し「次へ」を選択する 

1-⑦ 保存先フォルダを指定して「次へ」を選択する  

1-⑧ 「一般的なプロジェクトしてインポート」を選択し「完了」を選択する


## 2. データベースの準備（PostgreSQL）

以下の順番でSQLファイルを実行してください（"docs/db/" フォルダ内）

2-① 01_create_user.sql  // ユーザー作成  ★実行前に、パスワードの部分を任意の値に書き換えてください。★

2-② 02_create_database.sql  // データベース作成  

2-③ 03_create_schema.sql  // スキーマ作成  

2-④ 04_create_department_tb.sql  // 部署マスタテーブル作成 

2-⑤ 05_create_shain_tb.sql  // 社員マスタテーブル作成

※ SQLの実行にはpgAdminの使用を推奨します


----- pgAdminでのCSVインポート（テストデータの登録） -----

以下の順番でCSVファイルをテーブルに、インポートしてください（"docs/db/" フォルダ内）

2-⑥ 06_m_department.csv  // 部署マスタテーブルのテストデータ

2-⑦ 07_m_shain.csv  // 社員管理マスタテーブルのテストデータ

### pgAdminでのCSVインポート手順

a. pgAdminを起動し、該当のデータベース（ShainDB）に接続する。

b. pgAdminを起動し、該当のスキーマ（shain）を選択する。

c. 左側のナビゲーションツリーから対象のテーブル（m_department または m_shain）を右クリックし、  
　「データをインポート/エクスポート」を選択する。

d.「インポート」タブを選択し、以下を設定する

ファイル名：06_m_department.csv または 07_m_shain.csvのパスを設定する 

形式："CSV"  

ヘッダー：チェックを入れる（CSVにヘッダー行がある場合）  

e. 「OK」ボタンを押してインポートを行う。

f. エラーが表示されず、CSVのデータが、テーブルに取り込まれることを確認する。


## 3. application.properties の作成

以下の内容で "shain/src/main/resources/application.properties" を作成してください。  

※ セキュリティ上、GitHubには含めていません。

■ application.propertiesの内容（以下の内容をコピーして作成してください）

'''application.properties
spring.application.name=shain
server.servlet.context-path=/shain
server.port=7777
spring.thymeleaf.cache=false
spring.datasource.url=jdbc:postgresql://localhost:5432/ShainDB
spring.datasource.username=user1
spring.datasource.password=ここに、2. データベースの準備（PostgreSQL）の①で作成したユーザのパスワードを設定してください


## 4. アプリケーションを起動

4-① Eclipseで、プロジェクトを右クリックする

4-② 実行　→　Spring Boot アプリケーション を選択する

　【Maven認識されない場合の対処】

　　プロジェクトを右クリック　→　構成　→　Mavenプロジェクトへ変換


## 5. 動作確認

ブラウザで以下のURLにアクセスする

http://localhost:7777/shain

