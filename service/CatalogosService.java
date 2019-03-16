package service;



// default package
// Generated 13-mar-2019 19:13:42 by Hibernate Tools 5.3.6.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import model.Catalogo;
import model.Movimiento;

/**
 * Home object for domain model class Catalogos.
 * @see .Catalogos
 * @author Hibernate Tools
 */
public class CatalogosService {

	private static final Log log = LogFactory.getLog(CatalogosService.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Catalogo transientInstance) {
		log.debug("persisting Catalogos instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Catalogo instance) {
		log.debug("attaching dirty Catalogos instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Catalogo instance) {
		log.debug("attaching clean Catalogos instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Catalogo persistentInstance) {
		log.debug("deleting Catalogos instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Catalogo merge(Catalogo detachedInstance) {
		log.debug("merging Catalogos instance");
		try {
			Catalogo result = (Catalogo) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Catalogo findById(int id) {
		log.debug("getting Catalogos instance with id: " + id);
		try {
			Catalogo instance = (Catalogo) sessionFactory.getCurrentSession().get("Catalogos", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	public List getAllCatalogos()
	{
		log.debug("finding All Movimeintos instance by example");
		try {
			List<Catalogo> list = sessionFactory.getCurrentSession().createCriteria(Catalogo.class).list();
			log.debug("find all Movimientos result size: " + list.size());
			return list;
			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	   }
	

	public List findByExample(Catalogo instance) {
		log.debug("finding Catalogos instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("Catalogos").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
