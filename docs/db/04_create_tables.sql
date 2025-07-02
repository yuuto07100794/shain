-- Table: shain.m_department

-- DROP TABLE IF EXISTS shain.m_department;

CREATE TABLE IF NOT EXISTS shain.m_department
(
    department_id integer NOT NULL,
    department_name character varying(10) COLLATE pg_catalog."default",
    cr_date timestamp(6) without time zone,
    cr_usr character varying(7) COLLATE pg_catalog."default",
    up_date timestamp(6) without time zone,
    up_usr character varying(7) COLLATE pg_catalog."default",
    del_fg character varying(1) COLLATE pg_catalog."default" DEFAULT 0,
    CONSTRAINT m_department_pkey PRIMARY KEY (department_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS shain.m_department
    OWNER to user1;
