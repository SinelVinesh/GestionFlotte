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
-- Name: kilometrage; Type: TABLE; Schema: public; Owner: flotte
--

CREATE TABLE public.kilometrage (
    id integer NOT NULL,
    date date NOT NULL,
    start integer NOT NULL,
    "end" integer NOT NULL,
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
-- Name: kilometrage id; Type: DEFAULT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.kilometrage ALTER COLUMN id SET DEFAULT nextval('public.kilometrage_id_seq'::regclass);


--
-- Name: vehicle id; Type: DEFAULT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.vehicle ALTER COLUMN id SET DEFAULT nextval('public.vehicle_id_seq'::regclass);


--
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: flotte
--

COPY public.app_user (id, username, password) FROM stdin;
\.


--
-- Data for Name: kilometrage; Type: TABLE DATA; Schema: public; Owner: flotte
--

COPY public.kilometrage (id, date, start, "end", vehicle_id) FROM stdin;
\.


--
-- Data for Name: vehicle; Type: TABLE DATA; Schema: public; Owner: flotte
--

COPY public.vehicle (id, license_plate) FROM stdin;
\.


--
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: flotte
--

SELECT pg_catalog.setval('public.app_user_id_seq', 1, false);


--
-- Name: kilometrage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: flotte
--

SELECT pg_catalog.setval('public.kilometrage_id_seq', 1, false);


--
-- Name: vehicle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: flotte
--

SELECT pg_catalog.setval('public.vehicle_id_seq', 1, false);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);


--
-- Name: kilometrage kilometrage_pkey; Type: CONSTRAINT; Schema: public; Owner: flotte
--

ALTER TABLE ONLY public.kilometrage
    ADD CONSTRAINT kilometrage_pkey PRIMARY KEY (id);


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
-- PostgreSQL database dump complete
--

