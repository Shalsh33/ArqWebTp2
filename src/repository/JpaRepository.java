package repository;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class JpaRepository<T, ID> {

	protected EntityManager em;
	
	public JpaRepository(EntityManager em) {
		this.em = em;
	}
	
	abstract T findById(ID id);
	abstract List<T> findAll();
	abstract T save(T entity);
	public void delete(T entity) {
		
		if(em.contains(entity)) {
			em.remove(entity);
		}
		em.close();
	}
}
