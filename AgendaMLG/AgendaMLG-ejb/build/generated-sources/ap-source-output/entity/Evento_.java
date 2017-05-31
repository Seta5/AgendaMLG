package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Evento.class)
public abstract class Evento_ {

	public static volatile SingularAttribute<Evento, String> descripcion;
	public static volatile SingularAttribute<Evento, String> ubicacion;
	public static volatile SingularAttribute<Evento, String> responsable;
	public static volatile SingularAttribute<Evento, Date> fechaInicio;
	public static volatile SingularAttribute<Evento, Boolean> permanente;
	public static volatile SingularAttribute<Evento, Date> fechaEntrada;
	public static volatile SingularAttribute<Evento, Boolean> validado;
	public static volatile SingularAttribute<Evento, Long> id;
	public static volatile SingularAttribute<Evento, String> nombre;
	public static volatile SingularAttribute<Evento, Date> fechaFin;

}

