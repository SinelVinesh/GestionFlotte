--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app_user; Type: TABLE; Schema: public; Owner: flotte
--

CREATE TABLE public.app_user (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(30) NOT NULL
);


ALTER TABLE public.app_user OWNER TO flotte;

--
-- Name: app_user_id_seq; Type: SEQUENCE; Schema: public; Owner: flotte
--

CREATE SEQUENCE public.app_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_id_seq OWNER TO flotte;

--
-- Name: app_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: flotte
--

ALTER SEQUENCE public.app_user_id_seq OWNED BY public.app_user.id;


--
-- Name: configuration; Type: TABLE; Schema: public; Owner: flotte
--

CREATE TABLE public.configuration (
    id integer NOT NULL,
    key character varying(255) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.configuration OWNER TO flotte;

--
-- Name: configuration_id_seq; Type: SEQUENCE; Schema: public; Owner: flotte
--

CREATE SEQUENCE public.configuration_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.configuration_id_seq OWNER TO flotte;

--
-- Name: configuration_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: flotte
--

ALTER SEQUENCE public.configuration_id_seq OWNED BY public.configuration.id;


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: flotte
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO flotte;

--
-- Name: kilometrage; Type: TABLE; Schema: public; Owner: flotte
--

CREATE TABLE public.kilometrage (
    id integer NOT NULL,
    kilometrage_date date NOT NULL,
    counter_start integer NOT NULL,
    counter_end integer NOT NULL,
    vehicle_id integer NOT NULL
);


ALTER TABLE public.kilometrage OWNER TO flotte;

--
-- Name: kilometrage_id_seq; Type: SEQUENCE; Schema: public; Owner: flotte
--

CREATE SEQUENCE public.kilometrage_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.kilometrage_id_seq OWNER TO flotte;

--
-- Name: kilometrage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: flotte
--

ALTER SEQUENCE public.kilometrage_id_seq OWNED BY public.kilometrage.id;


--
-- Name: user_token; Type: TABLE; Schema: public; Owner: flotte
--

CREATE TABLE public.user_token (
    id integer NOT NULL,
    token character varying(255) NOT NULL,
    app_user_id integer NOT NULL,
    expiration timestamp with time zone NOT NULL
);


ALTER TABLE public.user_token OWNER TO flotte;

--
-- Name: user_token_id_seq; Type: SEQUENCE; Schema: public; Owner: flotte
--

CREATE SEQUENCE public.user_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_token_id_seq OWNER TO flotte;

--
-- Name: user_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: flotte
--

ALTER SEQUENCE public.user_token_id_seq OWNED BY public.user_token.id;


--
-- Name: vehicle; Type: TABLE; Schema: public; Owner: flotte
--

CREATE TABLE public.vehicle (
    id integer NOT NULL,
    license_plate character varying(255) NOT NULL
);


ALTER TABLE public.vehicle OWNER TO flotte;

--
-- Name: vehicle_id_seq; Type: SEQUENCE; Schema: public; Owner: flotte
--

CREATE SEQUENCE public.vehicle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vehicle_id_seq OWNER TO flotte;

--
-- Name: vehicle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: flotte
--

ALTER SEQUENCE public.vehicle_id_seq OWNED BY public.vehicle.id;


--
-- Name: app_user id; Type: DEFAULT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.app_user ALTER COLUMN id SET DEFAULT nextval('public.app_user_id_seq'::regclass);


--
-- Name: configuration id; Type: DEFAULT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.configuration ALTER COLUMN id SET DEFAULT nextval('public.configuration_id_seq'::regclass);


--
-- Name: kilometrage id; Type: DEFAULT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.kilometrage ALTER COLUMN id SET DEFAULT nextval('public.kilometrage_id_seq'::regclass);


--
-- Name: user_token id; Type: DEFAULT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.user_token ALTER COLUMN id SET DEFAULT nextval('public.user_token_id_seq'::regclass);


--
-- Name: vehicle id; Type: DEFAULT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.vehicle ALTER COLUMN id SET DEFAULT nextval('public.vehicle_id_seq'::regclass);


--
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: flotte
--

COPY public.app_user (id, username, password) FROM stdin;
1	Jean	123
\.


--
-- Data for Name: configuration; Type: TABLE DATA; Schema: public; Owner: flotte
--

COPY public.configuration (id, key, value) FROM stdin;
1	token_duration	3600
\.


--
-- Data for Name: kilometrage; Type: TABLE DATA; Schema: public; Owner: flotte
--

COPY public.kilometrage (id, kilometrage_date, counter_start, counter_end, vehicle_id) FROM stdin;
1	2022-11-15	400	525	1
2	2022-11-16	525	650	1
3	2022-11-17	650	720	1
17	2022-11-17	850	910	1
18	2022-11-17	850	910	1
19	2022-11-17	850	910	1
\.


--
-- Data for Name: user_token; Type: TABLE DATA; Schema: public; Owner: flotte
--

COPY public.user_token (id, token, app_user_id, expiration) FROM stdin;
31	f7283d2d669fff2f0d794895d1f4be4354f9963d	1	2022-12-13 10:09:55.818768+03
\.


--
-- Data for Name: vehicle; Type: TABLE DATA; Schema: public; Owner: flotte
--

COPY public.vehicle (id, license_plate) FROM stdin;
1	5142TBA
2	4856TBB
3	1254TBG
4	6942TAA
5	4215TBA
6	3125TV
7	6784TBR
8	3657TBE
\.


--
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: flotte
--

SELECT pg_catalog.setval('public.app_user_id_seq', 1, true);


--
-- Name: configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: flotte
--

SELECT pg_catalog.setval('public.configuration_id_seq', 1, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: flotte
--

SELECT pg_catalog.setval('public.hibernate_sequence', 31, true);


--
-- Name: kilometrage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: flotte
--

SELECT pg_catalog.setval('public.kilometrage_id_seq', 3, true);


--
-- Name: user_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: flotte
--

SELECT pg_catalog.setval('public.user_token_id_seq', 1, false);


--
-- Name: vehicle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: flotte
--

SELECT pg_catalog.setval('public.vehicle_id_seq', 8, true);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);


--
-- Name: configuration configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.configuration
    ADD CONSTRAINT configuration_pkey PRIMARY KEY (id);


--
-- Name: kilometrage kilometrage_pkey; Type: CONSTRAINT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.kilometrage
    ADD CONSTRAINT kilometrage_pkey PRIMARY KEY (id);


--
-- Name: user_token user_token_pkey; Type: CONSTRAINT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.user_token
    ADD CONSTRAINT user_token_pkey PRIMARY KEY (id);


--
-- Name: vehicle vehicle_pkey; Type: CONSTRAINT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_pkey PRIMARY KEY (id);


--
-- Name: app_user_id; Type: INDEX; Schema: public; Owner: flotte
--

CREATE INDEX app_user_id ON public.app_user USING btree (id);


--
-- Name: kilometrage_id; Type: INDEX; Schema: public; Owner: flotte
--

CREATE INDEX kilometrage_id ON public.kilometrage USING btree (id);


--
-- Name: vehicle_id; Type: INDEX; Schema: public; Owner: flotte
--

CREATE INDEX vehicle_id ON public.vehicle USING btree (id);


--
-- Name: kilometrage fkkilometrag760451; Type: FK CONSTRAINT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.kilometrage
    ADD CONSTRAINT fkkilometrag760451 FOREIGN KEY (vehicle_id) REFERENCES public.vehicle(id);


--
-- Name: user_token fkuser_token760451; Type: FK CONSTRAINT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.user_token
    ADD CONSTRAINT fkuser_token760451 FOREIGN KEY (app_user_id) REFERENCES public.app_user(id);


--
-- PostgreSQL database dump complete
--

