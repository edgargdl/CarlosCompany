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
import org.springframework.stereotype.Service;

import model.Movimiento;

/**
 * Home object for domain model class Movimientos.
 * @see .Movimientos
 * @author Hibernate Tools
 */

@Service
public class MovimientosService {

	private static final Log log = LogFactory.getLog(MovimientosService.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Movimiento transientInstance) {
		log.debug("persisting Movimientos instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Movimiento instance) {
		log.debug("attaching dirty Movimientos instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Movimiento instance) {
		log.debug("attaching clean Movimientos instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Movimiento persistentInstance) {
		log.debug("deleting Movimientos instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Movimiento merge(Movimiento detachedInstance) {
		log.debug("merging Movimientos instance");
		try {
			Movimiento result = (Movimiento) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Movimiento findById(int id) {
		log.debug("getting Movimientos instance with id: " + id);
		try {
			Movimiento instance = (Movimiento) sessionFactory.getCurrentSession().get("Movimientos", id);
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
	
	
	
	public List getAllMovimientos()
	{
		log.debug("finding Movimientos instance by example");
		try {
			List<Movimiento> list = sessionFactory.getCurrentSession().createCriteria(Movimiento.class).list();
			log.debug("find all Movimientos result size: " + list.size());
			return list;
			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	   }
	
	
	

	public List findByExample(Movimiento instance) {
		log.debug("finding Movimientos instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("Movimientos")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
