-- Table: shain.m_shain

-- DROP TABLE IF EXISTS shain.m_shain;

CREATE TABLE IF NOT EXISTS shain.m_shain
(
    shain_no character(6) COLLATE pg_catalog."default" NOT NULL,
    shain_name character varying(40) COLLATE pg_catalog."default",
    shain_kana character varying(40) COLLATE pg_catalog."default",
    department_id integer,
    bikou character varying(256) COLLATE pg_catalog."default",
    cr_date timestamp(6) without time zone,
    cr_usr character varying(7) COLLATE pg_catalog."default",
    up_date timestamp(6) without time zone,
    up_usr character varying(7) COLLATE pg_catalog."default",
    del_fg character varying(1) COLLATE pg_catalog."default" DEFAULT 0,
    CONSTRAINT "M_SHAIN_pkey" PRIMARY KEY (shain_no),
    CONSTRAINT m_shain_fkey FOREIGN KEY (department_id)
        REFERENCES shain.m_department (department_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS shain.m_shain
    OWNER to user1;

GRANT ALL ON TABLE shain.m_shain TO user1;
