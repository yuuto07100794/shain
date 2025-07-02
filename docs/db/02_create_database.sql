-- Database: ShainDB

-- DROP DATABASE IF EXISTS "ShainDB";

CREATE DATABASE "ShainDB"
    WITH
    OWNER = user1
    ENCODING = 'UTF8'
    LC_COLLATE = 'ja-JP'
    LC_CTYPE = 'ja-JP'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
