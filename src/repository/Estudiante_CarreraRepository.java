package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Estudiante_Carrera;

public class Estudiante_CarreraRepository extends JpaRepository<Estudiante_Carrera, Integer> {

	public Estudiante_CarreraRepository(EntityManager em) {
		super(em);
	}

	@Override
	public Estudiante_Carrera findById(Integer id) {
		return em.find(Estudiante_Carrera.class, id);
	}

	@Override
	public List<Estudiante_Carrera> findAll() {
		String jpql = "SELECT ec FROM Estudiante_Carrera ec";
		TypedQuery<Estudiante_Carrera> query = em.createQuery(jpql,Estudiante_Carrera.class);
		List<Estudiante_Carrera> result = query.getResultList();
		
		em.close();
		return result;
	}

	@Override
	public Estudiante_Carrera save(Estudiante_Carrera entity) {
		
		//falta comprobar que no existan duplicaciones (cambio del tipo de la clave primaria de la tabla)
		
		em.getTransaction().begin();
		if(entity.getId() == 0) {
			em.persist(entity);
		} else {
			entity = em.merge(entity);
		}
		em.getTransaction().commit();
		
		em.close();
		return entity;
	}

}
