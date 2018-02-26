CREATE TABLE public."TABLE"
(
    "Table_ID" integer NOT NULL DEFAULT nextval('"TABLE_ID_seq"'::regclass),
    "Number_Of_Seats" smallint,
    "Value" text COLLATE pg_catalog."default",
    "Status" text COLLATE pg_catalog."default" DEFAULT 'ACTIVE'::text,
    "Reservation_Time" smallint,
    CONSTRAINT "TABLE_pkey" PRIMARY KEY ("Table_ID")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."TABLE"
    OWNER to postgres;

CREATE TABLE public."USER"
(
    "User_ID" integer NOT NULL DEFAULT nextval('"USER_User_ID_seq"'::regclass),
    "Login" text COLLATE pg_catalog."default",
    "Password" text COLLATE pg_catalog."default",
    "Email" text COLLATE pg_catalog."default",
    "Table_ID" integer DEFAULT 0,
    "Role" text COLLATE pg_catalog."default" NOT NULL DEFAULT 'USER'::text,
    "Phone_Number" text COLLATE pg_catalog."default",
    CONSTRAINT "USER_pkey" PRIMARY KEY ("User_ID"),
    CONSTRAINT "Table_IDs" FOREIGN KEY ("Table_ID")
        REFERENCES public."TABLE" ("Table_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."USER"
    OWNER to postgres;

CREATE TABLE public."DISH"
(
    "Dish_ID" integer NOT NULL DEFAULT nextval('"DISH_ID_seq"'::regclass),
    "Name" text COLLATE pg_catalog."default",
    "Description" text COLLATE pg_catalog."default",
    "Price" double precision,
    CONSTRAINT "DISH_pkey" PRIMARY KEY ("Dish_ID")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."DISH"
    OWNER to postgres;