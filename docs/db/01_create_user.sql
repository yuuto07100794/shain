-- Role: user1
-- 実行前に、パスワードは任意のものに書き換えてください。

-- DROP ROLE IF EXISTS user1;

CREATE ROLE user1 WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  CREATEDB
  NOCREATEROLE
  NOREPLICATION
  NOBYPASSRLS
  PASSWORD 'この部分を、設定したいパスワードに書き換えてください';
