INSERT INTO agendabd.usuario (cuenta, email, password, rol) VALUES ('administrador','yosoyroot@dominio.es','administrador',0);
INSERT INTO agendabd.usuario (cuenta, email, password, rol) VALUES ('profesional','EL@diario.sur','profesional',1);
INSERT INTO agendabd.usuario (cuenta, email, password, rol) VALUES ('autorizado','elpizzero@dodo.minio','autorizado',2);
INSERT INTO agendabd.usuario (cuenta, email, password, rol) VALUES ('limitado','potato@huerto.es','limitado',3);
INSERT INTO agendabd.evento (id, nombre, responsable, permanente, validado, ubicacion, fechaEntrada, fechaInicio, fechaFin) VALUES (0,'Feria del Libro','Universidad de Malaga',false,true,'Plaza de la Merced','2017-05-31','2017-06-02','2017-06-11');
INSERT INTO agendabd.evento (id, nombre, responsable, permanente, validado, ubicacion, fechaEntrada) VALUES (10,'Cine Albeniz','Europa Cinemas',true,true,'C/Alcazabilla 4','2017-05-31');
INSERT INTO agendabd.evento (id, nombre, responsable, permanente, validado, ubicacion, fechaEntrada) VALUES (20,'No validado TEST','Lugar',true,false,'Lugar','2017-05-31');
COMMIT;