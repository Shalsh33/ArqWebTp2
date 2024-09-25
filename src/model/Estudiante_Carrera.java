package model;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estudiante_Carrera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_estudiante", foreignKey = @ForeignKey(name= "FK_estudiante",foreignKeyDefinition = "FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`libreta`) ON DELETE CASCADE ON UPDATE CASCADE",value = ConstraintMode.CONSTRAINT))
	private Estudiante estudiante;
	
	@ManyToOne
	@JoinColumn(name="id_carrera", foreignKey = @ForeignKey(name= "FK_carrera", foreignKeyDefinition= "FOREIGN KEY(`id_carrera`) REFERENCES `carrera` (`id`) ON DELETE CASCADE ON UPDATE CASCADE",value = ConstraintMode.CONSTRAINT))
	private Carrera carrera;
	
	@Column
	private int antiguedad;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean graduado;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public boolean isGraduado() {
		return graduado;
	}

	public void setGraduado(boolean graduado) {
		this.graduado = graduado;
	}

	@Override
	public String toString() {
		return "Estudiante_Carrera [id=" + id + ", estudiante=" + estudiante + ", carrera=" + carrera + ", antiguedad="
				+ antiguedad + ", graduado=" + graduado + "]";
	}
	
	
	
}
