package daos;

import java.util.List;

import noticias.SiteRSS;

public class SiteRSSDAO {

	private DAO<SiteRSS, String> dao;

	public List<SiteRSS> selectAll() {
		return dao.list();
	}

	public void remove(SiteRSS site) {
		dao.remove(site);
	}

	public SiteRSS save(SiteRSS site) {
		if (site.getLink() != null)
			return dao.merge(site);
		dao.persist(site);
		return site;
	}

	public SiteRSS selectByLink(String link) {
		return dao.find(link);
	}

	public DAO<SiteRSS, String> getDao() {
		return dao;
	}

	public void setDao(DAO<SiteRSS, String> dao) {
		this.dao = dao;
	}	
	
}
