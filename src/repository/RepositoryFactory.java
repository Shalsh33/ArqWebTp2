package repository;


import utils.ConnectionFactory;
import utils.MotorDB;

public class RepositoryFactory {

	private static RepositoryFactory instance = new RepositoryFactory();
	
	
	private RepositoryFactory() {
		
	}
	
	public static RepositoryFactory getInstance() {
		return instance;
	}
	
	
	public CarreraRepository getCarreraRepository(MotorDB tipo) {
		return new CarreraRepository(ConnectionFactory.getInstance().connect(tipo));
	}
	
	public EstudianteRepository getEstudianteRepository(MotorDB tipo) {
		return new EstudianteRepository(ConnectionFactory.getInstance().connect(tipo));
	}
	
	public Estudiante_CarreraRepository getEstudiante_CarreraRepository(MotorDB tipo) {
		return new Estudiante_CarreraRepository(ConnectionFactory.getInstance().connect(tipo));
	}
}
