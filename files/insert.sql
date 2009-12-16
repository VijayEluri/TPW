
SELECT pg_catalog.setval('evento_id_seq', 2, true);
SELECT pg_catalog.setval('minicurso_id_seq', 2, true);
SELECT pg_catalog.setval('seq_post_id', 3, true);
SELECT pg_catalog.setval('seq_teste', 1, false);

--
-- Data for Name: evento; Type: TABLE DATA; Schema: public; Owner: tpwuser
--

COPY evento (id, qtvagas, qtinscritos, nome, data, descricao, responsavel) FROM stdin;
2	50	0	Porquê JAVA?	2009-12-07 08:00:00	Esta palestra conta com os integrantes da empresa Soft. Development Ltda. onde contam suas conquistas com a linguagem JAVA e o desenvolvimento de componentes livres.	Maria J. A. e José A. N.
1	200	2	Palestra sobre desenvolvimento em Software Livre nas Empresas	2010-01-20 16:00:00	Esta palestra conta com toda a experiência do Dr. Richard em desenvolvimentos de softwares de código aberto que são usadas atualmente no ambiente empresarial	Dr. Richard
\.


--
-- Data for Name: evento_usuario; Type: TABLE DATA; Schema: public; Owner: tpwuser
--

--
-- Data for Name: minicurso; Type: TABLE DATA; Schema: public; Owner: tpwuser
--

COPY minicurso (id, qtvagas, qtinscritos, nome, data, descricao, responsavel) FROM stdin;
2	50	0	Recompilando o kernel	2009-12-01 09:00:00	Este minicurso aborda a necessidade de recompilar o kernel no linux.<br>\r\nDurante o minicurso o aluno irá recompilar seu kernel de acordo com as necessidades desejadas.	Arthur Nascimento
1	50	2	Desenvolvimento em SHELL	2010-01-15 11:30:00	Este minicurso aborda o desenvolvimento de shell-scripts.<br>\r\nConteúdo abordado:<br>\r\n- Variáveis<br>\r\n- Desenvolvimento	Thiago José de Campos
\.

--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: tpwuser
--

COPY post (id, titulo, data, texto, login) FROM stdin;
1	Picotux: O melhor computador que usa Linux	2009-12-16 16:09:33.699	<p>&ldquo;Esse &eacute; Picotux 100, ele &eacute; o menor computador do mundo que utiliza o sistema operacional Linux (segundo seu site oficial), ele mede apenas 5mm &times; 19mm &times; 19mm (um pouco maior que um conector RJ45), no seu interior h&aacute; uma CPU ARM7 a 55 MHz, uma mem&oacute;ria flash de 2 MB e 8 MB de mem&oacute;ria SDRAM, executando o kernel 2.4.27 e uClinux Busybox 1,0.</p>\r\n<p style="text-align: center;"><img title="picotux.jpg - fonte: picotux.jpg (imagem JPEG, 300&times;200 pixels) (http://badsystem.files.wordpress.com/2009/12/picotux.jpg) " src="http://img.efetividade.net/img/xtra/picotux.jpg" alt="" /></p>\r\n<p>Possui duas interfaces de comunica&ccedil;&atilde;o, meia 10/100 Mbit / full duplex Ethernet e uma porta serial com at&eacute; 230,400 baud&hellip;&rdquo; [<a href="http://badsystem.wordpress.com/2009/12/15/picotux-100-o-menor-computador-do-mundo-com-linux/">refer&ecirc;ncia: badsystem.wordpress.com</a>]</p>	admin
2	Google investe para crescer em publicidade	2009-12-16 16:12:08.459	<table style="height: 384px;" border="0" cellspacing="0" cellpadding="6" width="461" align="center">\r\n<tbody>\r\n<tr>\r\n<td>\r\n<div><span style="font-family: arial; font-size: xx-small;"><br /><em>Reuters</em></span></div>\r\n</td>\r\n</tr>\r\n<tr>\r\n<td>\r\n<p style="text-align: center;"><img title="Google investe para crescer em publicidade" src="http://info.abril.com.br/aberto/infonews/fotos/google-20091216091729.jpg" alt="Google investe para crescer em publicidade" /></p>\r\n</td>\r\n</tr>\r\n<tr>\r\n<td>\r\n<div><span style="font-family: verdana; color: #333333; font-size: xx-small;"><strong>Gigante recentemente fez v&aacute;rias aquisi&ccedil;&otilde;es para aumentar seus neg&oacute;cios com a venda de an&uacute;ncios.</strong></span></div>\r\n</td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n<div id="entry-body"><!-- /FERRAMENTAS -->\r\n<div id="texto_link">\r\n<p>S&Atilde;O FRANCISCO - Executivos do Google afirmam que a empresa teve um bom crescimento em seus neg&oacute;cios com publicidade online nos primeiros tr&ecirc;s trimestres do ano e que ir&aacute; continuar a investir bastante em iniciativas no setor com v&iacute;deos e aparelhos m&oacute;veis.</p>\r\n<p>Em teleconfer&ecirc;ncia com investidores e analistas ontem, a gigante de internet esbo&ccedil;ou os diversos elementos de medidas para elevar sua receita com publicidade online como gr&aacute;ficos, v&iacute;deos e anima&ccedil;&otilde;es interativas.</p>\r\n<div id="entry-related"><span class="entry-related-tit">Leia tamb&eacute;m:</span> &nbsp; \r\n<ul>\r\n<li><a href="http://info.abril.com.br/noticias/mercado/yahoo-ve-forte-aumento-de-publicidade-movel-04122009-23.shl">Yahoo! v&ecirc; forte aumento de publicidade m&oacute;vel</a> <span class="related-data">(04/12/2009)</span><br /><a href="http://info.abril.com.br/noticias/mercado/google-adquire-empresa-de-publicidade-movel-09112009-35.shl">Google adquire empresa de publicidade m&oacute;vel</a> <span class="related-data">(09/11/2009)</span>\r\n<ul>\r\n</ul>\r\n</li>\r\n</ul>\r\n</div>\r\n<p>O diretor de vendas e opera&ccedil;&otilde;es online do Google, Tom Pickett, afirmou que a empresa vendeu 90% do estoque de an&uacute;ncios da homepage do YouTube, site de v&iacute;deos do Google, no terceiro trimestre.</p>\r\n<p>O Google, maior buscador do mundo, n&atilde;o divulgou dados financeiros atualizados para o atual trimestre.</p>\r\n<p>A empresa recentemente fez v&aacute;rias aquisi&ccedil;&otilde;es para aumentar seus neg&oacute;cios com a venda de an&uacute;ncios, buscando suplementos para sua receita com os an&uacute;ncios de texto que aparecem ao lado de resultados de buscas.</p>\r\n<p>A empresa comprou a empresa de publicidade online DoubleClick em 2008 por 3,1 bilh&otilde;es de d&oacute;lares. J&aacute; no m&ecirc;s passado, o Google anunciou que planeja pagar 750 milh&otilde;es de d&oacute;lares pela AdMob, que desenvolve tecnologias para publicar an&uacute;ncios nos aplicativos de smartphones como o iPhone, da Apple.</p>\r\n<p>O Google n&atilde;o divulgou quanto de sua receita, que chegou aos 22 bilh&otilde;es de d&oacute;lares em 2008, vem da venda de an&uacute;ncios.</p>\r\n<p>Executivos afirmaram que suas tentativas de convencer grandes marcas a gastarem dinheiro com campanhas publicit&aacute;rias online v&ecirc;m ganhando for&ccedil;a. E novos produtos do Google, como uma ferramenta para que anunciantes criem seus pr&oacute;prios an&uacute;ncios, vem atraindo empresas menores a experimentar campanhas publicit&aacute;rias na internet.</p>\r\n</div>\r\n</div>	admin
3	LeafWeb – XUL e Mozilla Gecko no estilo Chrome OS	2009-12-16 16:12:55.8	<p>&ldquo;Depois de estudar o funcionamento do t&atilde;o falado ChromeOS e para suprir a necessidade de instala&ccedil;&atilde;o de algo parecido para um projeto, venho mostrar o LeafWeb, que imita parte da interface do sistema do Google. Por&eacute;m, ele &eacute; feito totalmente com XUL &ndash; logo, ele utiliza o engine Gecko da Mozilla.</p>\r\n<p>\r\n<object width="425" height="344">\r\n<param name="movie" value="http://www.youtube.com/v/N2gQcbdHkuU&amp;rel=0&amp;color1=0x6699&amp;color2=0x54abd6&amp;hl=pt_BR&amp;feature=player_embedded&amp;fs=1" />\r\n<param name="allowFullScreen" value="true" />\r\n<param name="allowScriptAccess" value="always" /><embed type="application/x-shockwave-flash" width="425" height="344" src="http://www.youtube.com/v/N2gQcbdHkuU&amp;rel=0&amp;color1=0x6699&amp;color2=0x54abd6&amp;hl=pt_BR&amp;feature=player_embedded&amp;fs=1" allowfullscreen="true" allowscriptaccess="always"></embed>\r\n</object>\r\n</p>\r\n<p>A vers&atilde;o mostrada no v&iacute;deo &eacute; um beta teste com algumas funcionalidades que j&aacute; est&atilde;o 100% como o suporte a flash, java, v&iacute;deos e arquivos pdf. A quem interessar, visite o <a href="http://www.zendrael.com.br/leafweb">site</a> para mais informa&ccedil;&otilde;es. Se voc&ecirc; j&aacute; imaginou se isso seria poss&iacute;vel, tentei mostrar que simples id&eacute;ias produzem bons resultados.&rdquo; [<a href="http://www.zendrael.com.br/leafweb">refer&ecirc;ncia: zendrael.com.br</a>]</p>	admin
\.


--
-- Data for Name: sites_rss; Type: TABLE DATA; Schema: public; Owner: tpwuser
--

COPY sites_rss (link, site) FROM stdin;
http://br-linux.org/feed/	BrLinux
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: tpwuser
--

COPY usuario (login, password, nome, email, tipo) FROM stdin;
admin	21232f297a57a5a743894a0e4a801fc3	administrador	\N	ADMINISTRADOR
lucas	dc53fc4f621c80bdc2fa0329a6123708	Lucas Vendramin	vendra@grad.icmc.usp.br	NORMAL
tjcampos	b791ddc1c41d9500226b025e6314413f	Thiago José de Campos	tjcampos@gmail.com	NORMAL
stapait	c34dd5238451425aabca86062718aa33	Fábio Stapait	stapait@gmail.com	NORMAL
\.

--
-- Data for Name: minicurso_usuario; Type: TABLE DATA; Schema: public; Owner: tpwuser
--

COPY minicurso_usuario (id, login) FROM stdin;
1	lucas
1	stapait
\.

COPY evento_usuario (id, login) FROM stdin;
1	tjcampos
1	stapait
\.

