package entity;

import entity.Usuario.Rol;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile ListAttribute<Usuario, Evento> asistencia;
	public static volatile SingularAttribute<Usuario, String> cuenta;
	public static volatile SingularAttribute<Usuario, String> fecha_nacimiento;
	public static volatile SingularAttribute<Usuario, String> direccion;
	public static volatile SingularAttribute<Usuario, String> organizacion;
	public static volatile SingularAttribute<Usuario, String> telefono;
	public static volatile SingularAttribute<Usuario, String> nombre;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, Rol> rol;
	public static volatile SingularAttribute<Usuario, String> dni;

}

