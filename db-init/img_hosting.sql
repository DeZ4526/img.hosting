--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-08-25 21:18:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 226 (class 1259 OID 24661)
-- Name: image_commentaries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image_commentaries (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    image_id bigint,
    commentary_id bigint,
    text character varying NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.image_commentaries OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24660)
-- Name: image_commentaries_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.image_commentaries_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.image_commentaries_id_seq OWNER TO postgres;

--
-- TOC entry 4952 (class 0 OID 0)
-- Dependencies: 225
-- Name: image_commentaries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.image_commentaries_id_seq OWNED BY public.image_commentaries.id;


--
-- TOC entry 224 (class 1259 OID 24643)
-- Name: image_dislikes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image_dislikes (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    image_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.image_dislikes OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24642)
-- Name: image_dislike_data_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.image_dislike_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.image_dislike_data_id_seq OWNER TO postgres;

--
-- TOC entry 4953 (class 0 OID 0)
-- Dependencies: 223
-- Name: image_dislike_data_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.image_dislike_data_id_seq OWNED BY public.image_dislikes.id;


--
-- TOC entry 222 (class 1259 OID 24615)
-- Name: image_likes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image_likes (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    image_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT now()
);


ALTER TABLE public.image_likes OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24614)
-- Name: image_likes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.image_likes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.image_likes_id_seq OWNER TO postgres;

--
-- TOC entry 4954 (class 0 OID 0)
-- Dependencies: 221
-- Name: image_likes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.image_likes_id_seq OWNED BY public.image_likes.id;


--
-- TOC entry 220 (class 1259 OID 24601)
-- Name: images; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.images (
    id bigint NOT NULL,
    url character varying NOT NULL,
    id_user_uploader bigint NOT NULL,
    title character varying NOT NULL,
    description character varying,
    is_adult boolean
);


ALTER TABLE public.images OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24600)
-- Name: images_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.images_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.images_id_seq OWNER TO postgres;

--
-- TOC entry 4955 (class 0 OID 0)
-- Dependencies: 219
-- Name: images_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.images_id_seq OWNED BY public.images.id;


--
-- TOC entry 218 (class 1259 OID 24578)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24577)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 4956 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 4768 (class 2604 OID 24664)
-- Name: image_commentaries id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_commentaries ALTER COLUMN id SET DEFAULT nextval('public.image_commentaries_id_seq'::regclass);


--
-- TOC entry 4766 (class 2604 OID 24646)
-- Name: image_dislikes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_dislikes ALTER COLUMN id SET DEFAULT nextval('public.image_dislike_data_id_seq'::regclass);


--
-- TOC entry 4764 (class 2604 OID 24618)
-- Name: image_likes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_likes ALTER COLUMN id SET DEFAULT nextval('public.image_likes_id_seq'::regclass);


--
-- TOC entry 4763 (class 2604 OID 24604)
-- Name: images id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images ALTER COLUMN id SET DEFAULT nextval('public.images_id_seq'::regclass);


--
-- TOC entry 4762 (class 2604 OID 24581)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 4946 (class 0 OID 24661)
-- Dependencies: 226
-- Data for Name: image_commentaries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image_commentaries (id, user_id, image_id, commentary_id, text, created_at) FROM stdin;
2	8	1	\N	Первый родительский комментарий	2025-08-21 19:39:15.121057
3	8	1	\N	Второй родительский комментарий	2025-08-21 19:39:17.736716
5	8	1	2	Дочерний комментарий под первым родителем	2025-08-21 19:39:45.927781
6	8	2	\N	qasdsad	2025-08-23 17:13:05.839519
7	8	1	\N	Test	2025-08-23 17:19:58.223094
8	8	1	2	Test	2025-08-23 17:20:07.643029
\.


--
-- TOC entry 4944 (class 0 OID 24643)
-- Dependencies: 224
-- Data for Name: image_dislikes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image_dislikes (id, user_id, image_id, created_at) FROM stdin;
3	8	1	2025-08-20 20:56:51.301753
\.


--
-- TOC entry 4942 (class 0 OID 24615)
-- Dependencies: 222
-- Data for Name: image_likes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image_likes (id, user_id, image_id, created_at) FROM stdin;
4	8	2	2025-08-18 11:50:10.365477
\.


--
-- TOC entry 4940 (class 0 OID 24601)
-- Dependencies: 220
-- Data for Name: images; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.images (id, url, id_user_uploader, title, description, is_adult) FROM stdin;
1	uploads\\1755176319693_20250804_2341_Закатный серый кот_remix_01k1vcw1v2f38a13078e1c1ckq(1).png	8	squrrel	test	f
2	uploads\\1755176584298_assets_task_01k2b1a3h9e0599g76qzgkabwh_1754864752_img_1.png	8	squrrel	test	f
\.


--
-- TOC entry 4938 (class 0 OID 24578)
-- Dependencies: 218
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, username, password, role) FROM stdin;
9	dan1	$2a$10$iFdN78GxOPwihr9uWVyIcu.4tpeY9f.rrsrDMjURPF/lnN67DPyje	ROLE_USER
8	dan	$2a$10$3dcPccmFNxnf2A653PUcOewCDVqGjMHIdgTiuuGKKLZzFT1AqN8M6	ROLE_ADMIN
\.


--
-- TOC entry 4957 (class 0 OID 0)
-- Dependencies: 225
-- Name: image_commentaries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_commentaries_id_seq', 8, true);


--
-- TOC entry 4958 (class 0 OID 0)
-- Dependencies: 223
-- Name: image_dislike_data_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_dislike_data_id_seq', 3, true);


--
-- TOC entry 4959 (class 0 OID 0)
-- Dependencies: 221
-- Name: image_likes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_likes_id_seq', 6, true);


--
-- TOC entry 4960 (class 0 OID 0)
-- Dependencies: 219
-- Name: images_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.images_id_seq', 2, true);


--
-- TOC entry 4961 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 9, true);


--
-- TOC entry 4783 (class 2606 OID 24669)
-- Name: image_commentaries image_commentaries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_commentaries
    ADD CONSTRAINT image_commentaries_pkey PRIMARY KEY (id);


--
-- TOC entry 4781 (class 2606 OID 24649)
-- Name: image_dislikes image_dislike_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_dislikes
    ADD CONSTRAINT image_dislike_data_pkey PRIMARY KEY (id);


--
-- TOC entry 4777 (class 2606 OID 24621)
-- Name: image_likes image_likes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_likes
    ADD CONSTRAINT image_likes_pkey PRIMARY KEY (id);


--
-- TOC entry 4779 (class 2606 OID 24623)
-- Name: image_likes image_likes_user_id_image_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_likes
    ADD CONSTRAINT image_likes_user_id_image_id_key UNIQUE (user_id, image_id);


--
-- TOC entry 4775 (class 2606 OID 24608)
-- Name: images images_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_pkey PRIMARY KEY (id);


--
-- TOC entry 4771 (class 2606 OID 24585)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4773 (class 2606 OID 24587)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 4789 (class 2606 OID 24675)
-- Name: image_commentaries fk_commentaries; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_commentaries
    ADD CONSTRAINT fk_commentaries FOREIGN KEY (commentary_id) REFERENCES public.image_commentaries(id);


--
-- TOC entry 4787 (class 2606 OID 24655)
-- Name: image_dislikes fk_image; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_dislikes
    ADD CONSTRAINT fk_image FOREIGN KEY (image_id) REFERENCES public.images(id);


--
-- TOC entry 4790 (class 2606 OID 24680)
-- Name: image_commentaries fk_image; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_commentaries
    ADD CONSTRAINT fk_image FOREIGN KEY (image_id) REFERENCES public.images(id);


--
-- TOC entry 4784 (class 2606 OID 24609)
-- Name: images fk_images_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images
    ADD CONSTRAINT fk_images_user FOREIGN KEY (id_user_uploader) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4788 (class 2606 OID 24650)
-- Name: image_dislikes fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_dislikes
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4791 (class 2606 OID 24670)
-- Name: image_commentaries fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_commentaries
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4785 (class 2606 OID 24629)
-- Name: image_likes image_likes_image_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_likes
    ADD CONSTRAINT image_likes_image_id_fkey FOREIGN KEY (image_id) REFERENCES public.images(id) ON DELETE CASCADE;


--
-- TOC entry 4786 (class 2606 OID 24624)
-- Name: image_likes image_likes_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image_likes
    ADD CONSTRAINT image_likes_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


-- Completed on 2025-08-25 21:18:03

--
-- PostgreSQL database dump complete
--

