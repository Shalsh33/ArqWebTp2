package main.resources;

import dto.DTO_Carrera;
import model.Carrera;
import model.Estudiante;
import model.Estudiante_Carrera;
import repository.RepositoryFactory;
import utils.MotorDB;

public class TP2 {

	public static void main(String[] args) {
		RepositoryFactory rf = RepositoryFactory.getInstance();
		Estudiante e1 = new Estudiante(1,1,"asd","martinez",20,"ts as",'m');
		rf.getEstudianteRepository(MotorDB.MYSQL).save(e1);
		rf.getEstudianteRepository(MotorDB.MYSQL).save(new Estudiante(33333333,"asd","asddz",25,"ts as",'f'));
		rf.getEstudianteRepository(MotorDB.MYSQL).save(new Estudiante(3,44333333,"asd","asddz",25,"ts as",'f'));
		
		Carrera c1 = new Carrera();
		c1.setNombre("Tudai");
		rf.getCarreraRepository(MotorDB.MYSQL).save(c1);
		Carrera c2 = new Carrera();
		c2.setNombre("contador");
		rf.getCarreraRepository(MotorDB.MYSQL).save(c2);
		
		Estudiante_Carrera ec1 = new Estudiante_Carrera();
		ec1.setEstudiante(rf.getEstudianteRepository(MotorDB.MYSQL).findById(1));
		ec1.setCarrera(rf.getCarreraRepository(MotorDB.MYSQL).findById(2));
		ec1.setAntiguedad(2020);
		rf.getEstudiante_CarreraRepository(MotorDB.MYSQL).save(ec1);
		
		Estudiante_Carrera ec2 = new Estudiante_Carrera();
		ec2.setEstudiante(rf.getEstudianteRepository(MotorDB.MYSQL).findById(3));
		ec2.setCarrera(rf.getCarreraRepository(MotorDB.MYSQL).findById(2));
		ec2.setAntiguedad(2023);
		rf.getEstudiante_CarreraRepository(MotorDB.MYSQL).save(ec2);
		
		Estudiante_Carrera ec3 = new Estudiante_Carrera();
		ec3.setEstudiante(rf.getEstudianteRepository(MotorDB.MYSQL).findById(2));
		ec3.setCarrera(rf.getCarreraRepository(MotorDB.MYSQL).findById(1));
		ec3.setAntiguedad(2022);
		ec3.setGraduado(true);
		rf.getEstudiante_CarreraRepository(MotorDB.MYSQL).save(ec3);
		
		for(DTO_Carrera dc : rf.getCarreraRepository(MotorDB.MYSQL).reporteCarreras()) {
			System.out.println(dc);
		}
			
	}
}
