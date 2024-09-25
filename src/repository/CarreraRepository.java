package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dto.DTO_Carrera;
import dto.DTO_Estudiante;
import model.Carrera;

public class CarreraRepository extends JpaRepository<Carrera, Integer> {

	
	public CarreraRepository(EntityManager em) {
		super(em);
	}
	
	@Override
	public Carrera findById(Integer id) {

		return em.find(Carrera.class,id);
	}

	@Override
	public List<Carrera> findAll() {
		String jpql = "SELECT c FROM Carrera c";
		TypedQuery<Carrera> query = em.createQuery(jpql,Carrera.class);
		List<Carrera> result = query.getResultList();
		
		em.close();
		return result;
	}

	@Override
	public Carrera save(Carrera entity) {
		
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

	public List<Carrera> findAllOrderByMatricula(){
		
		/*String jpql = "SELECT c.id AS id_carrera, c.nombre AS nombre_carrera, "
				+ "COUNT(ec) as matricula FROM Carrera c"
				+ "JOIN Estudiante_Carrera ec ON c.id = ec.id_carrera"
				+ "ORDER BY matricula DESC";*/
		String jpql = "SELECT c FROM Carrera c "
				+ "JOIN Estudiante_Carrera ec ON c.id = ec.id_carrera"
				+ "ORDER BY COUNT(ec) DESC"
				+ "GROUP BY c.id";
		TypedQuery<Carrera> query = em.createQuery(jpql,Carrera.class);
		List<Carrera> lista = query.getResultList();
		
		em.close();
		return lista;
	}
	
	public List<DTO_Carrera> reporteCarreras(){
		
		String jpql = "SELECT new dto.DTO_Carrera(c) FROM Carrera c "
				+ "ORDER BY nombre ASC";
		List<DTO_Carrera> lista = em.createQuery(jpql,DTO_Carrera.class).getResultList();
		
		for(DTO_Carrera dc: lista) {
			jpql = "SELECT new dto.DTO_Estudiante(e.libreta, e.nombres, e.apellido, ec.antiguedad) FROM Estudiante e "
					+ "JOIN Estudiante_Carrera ec ON e.id = ec.estudiante "
					+ "WHERE ec.carrera = :carrera "
					+ "ORDER BY antiguedad DESC";
			TypedQuery<DTO_Estudiante> query = em.createQuery(jpql,DTO_Estudiante.class);
			query.setParameter("carrera", dc.getCarrera());
			dc.setEstudiantes(query.getResultList());
		}
		
		em.close();
		return lista;
	}

}
