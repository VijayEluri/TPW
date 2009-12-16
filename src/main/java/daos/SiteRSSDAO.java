package daos;

import java.util.List;

import noticias.SiteRSS;

/**
 * Dao do SiteRSS (Acesso aos links com RSS)
 * @author vendra
 *
 */
public class SiteRSSDAO {

	//dao
	private DAO<SiteRSS, String> dao;

	/**
	 * Seleciona todos os LinksRSS
	 * @return Lista de SiteRSS
	 */
	public List<SiteRSS> selectAll() {
		return dao.list();
	}

	/**
	 * Remove um Link
	 * @param site
	 */
	public void remove(SiteRSS site) {
		dao.remove(site);
	}

	/**
	 * Salva um Link
	 * @param site
	 * @return SiteRSS
	 */
	public SiteRSS save(SiteRSS site) {
		if (site.getLink() != null)
			return dao.merge(site);
		dao.persist(site);
		return site;
	}

	/**
	 * Seleciona o Link pelo Link
	 * @param link
	 * @return SiteRSS
	 */
	public SiteRSS selectByLink(String link) {
		return dao.find(link);
	}

	/**
	 * Pega o dao
	 * @return DAO
	 */
	public DAO<SiteRSS, String> getDao() {
		return dao;
	}

	/**
	 * Seta o dao
	 * @param dao
	 */
	public void setDao(DAO<SiteRSS, String> dao) {
		this.dao = dao;
	}	
	
}
