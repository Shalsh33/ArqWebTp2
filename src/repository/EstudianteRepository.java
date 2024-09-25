package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Estudiante;

public class EstudianteRepository extends JpaRepository<Estudiante, Integer> {

	public EstudianteRepository(EntityManager em) {
		super(em);
	}
	
	@Override
	public Estudiante findById(Integer id) {
		
		return em.find(Estudiante.class, id);
	}

	public Estudiante findByDNI(Integer dni) {
		
		String jpql = "SELECT e FROM Estudiante e WHERE e.dni = :dni";
		TypedQuery<Estudiante> query = em.createQuery(jpql,Estudiante.class);
		query.setParameter("dni", dni);
		Estudiante e = query.getSingleResult();
		
		em.close();
		return e;
		
	}
	
	@Override
	public List<Estudiante> findAll() {

		String jpql = "SELECT e FROM Estudiante e";
		TypedQuery<Estudiante> query = em.createQuery(jpql,Estudiante.class);
		List<Estudiante> result = query.getResultList();
		
		em.close();
		return result;
	}
	
	public List<Estudiante> findAllOrderBy (String criteria){
		
		String jpql = "SELECT e FROM Estudiante e "
				+ "ORDER BY :criteria";
		TypedQuery<Estudiante> query = em.createQuery(jpql,Estudiante.class);
		query.setParameter("criteria", criteria);
		List<Estudiante> result = query.getResultList();
		
		em.close();
		return result;
	}
	
	public List<Estudiante> findAllByGenero (char genero){
		
		String jpql = "SELECT e FROM Estudiante e "
				+ "WHERE e.genero LIKE :genero";
		TypedQuery<Estudiante> query = em.createQuery(jpql,Estudiante.class);
		query.setParameter("genero", genero);
		List<Estudiante> lista = query.getResultList();
		
		em.close();
		return lista;
	}

	@Override
	public Estudiante save(Estudiante entity) {
		
		em.getTransaction().begin();
		if(entity.getLibreta() == 0) {
			em.persist(entity);
		} else {
			entity = em.merge(entity);
		}
		em.getTransaction().commit();
		
		em.close();
		return entity;
	}

	public List<Estudiante> findByCarrera(int carrera){
		
		String jpql = "SELECT e FROM Estudiante e WHERE "
				+ "e.id IN (SELECT ec.id_estudiante FROM Estudiante_Carrera ec "
				+ "WHERE ec.id_carrera = :carrera )";
		TypedQuery<Estudiante> query = em.createQuery(jpql,Estudiante.class);
		query.setParameter("carrera", carrera);
		List<Estudiante> lista = query.getResultList();
		
		em.close();
		return lista;
	}
	
	public List<Estudiante> findByCarrera(String carrera){
		
		String jpql = "SELECT e FROM Estudiante e WHERE "
				+ "e.id IN (SELECT ec.id_estudiante FROM Estudiante_Carrera ec "
				+ "WHERE ec.id_carrera IN (SELECT c.id FROM Carrera c "
				+ "WHERE c.nombre = :carrera) )";
		TypedQuery<Estudiante> query = em.createQuery(jpql,Estudiante.class);
		query.setParameter("carrera", carrera);
		List<Estudiante> lista = query.getResultList();
		
		em.close();
		return lista;
	}
	
	public List<Estudiante> findByCarreraCiudad(String carrera, String ciudad){
		
		String jpql = "SELECT e FROM Estudiante e "
				+ "WHERE e.ciudad = :ciudad AND "
				+ "e.id IN (SELECT ec.id_estudiante FROM Estudiante_Carrera ec "
				+ "WHERE ec.id_carrera IN (SELECT c.id FROM Carrera c "
				+ "WHERE c.nombre = :carrera) )";
		TypedQuery<Estudiante> query = em.createQuery(jpql,Estudiante.class);
		query.setParameter("carrera", carrera);
		query.setParameter("ciudad", ciudad);
		List<Estudiante> lista = query.getResultList();
		
		em.close();
		return lista;
	}

}
