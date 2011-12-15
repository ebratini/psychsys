/*
	File Name: insertar_registros.sql
	Desc.: Script sql para la insercion de registros en las tablas de la base de datos PsychSysDB
	By: Edwin Bratini, Ariel Novas
	Date: 18-11-2011
*/

USE PsychSysDB --> Usando la Base de Datos PsychSysDB
GO

-- Insercion de registros

--------------------------1. Tutores ---------------------------

INSERT INTO Tutores
VALUES ('201112345678','Cedula','Novas','Heredia','Ariel','Arismendys','8092450123','C/ Baltazar Alvares, No. 20, Villa Consuelo, DN','ariel.arismendys@gmail.com','Dominicana','M','Soltero(a)','A')

INSERT INTO Tutores
VALUES ('201112345671','Pasaporte','Cabrera','Peña','Jorge','Luis','8092450100','C/ Baltazar Alvares, No. 10, Villa Consuelo, DN','jorge@gmail.com','Dominicana','M','Casado(a)','A')

INSERT INTO Tutores
VALUES ('201112345672','Pasaporte','Beltre','Marte','Jose','Antonio','8092450111','C/ Baltazar Alvares, No. 05, Villa Consuelo, DN','jose@gmail.com','Cubano','M','Soltero(a)','A')

INSERT INTO Tutores
VALUES ('201112345673','Cedula','Franco','Gonzalez','Estalman','Anthony','8092450108','C/ Baltazar Alvares, No. 03, Villa Consuelo, DN','estalman@gmail.com','Dominicana','M','Viudo(a)','A')

INSERT INTO Tutores
VALUES ('201112345675','Cedula','Heredia','Espejo','Angela','Maria','8092450141','C/ Baltazar Alvares, No. 85, Villa Consuelo, DN','angela01@gmail.com','Dominicana','F','Soltero(a)','A')

---------------------------- 2. Estudiantes ----------------------------

INSERT INTO Estudiantes
VALUES ('201101234567','Cedula','Bratini','Gonzalez','Edwin','Alexander','Master','8095555555','C/Duarte, No.10, Almirante','Dominicana','M','01-01-1989','Santo Domingo',30,45.4,'Media',4, 'Liceo Experimental',4,1,'A')

INSERT INTO Estudiantes
VALUES ('201101234561','Cedula','Marrero','Gonzalez','Patricia','Isabelle','Paty','8095555551','C/Duarte, No.05, Almirante','Dominicana','F','01-01-1990','Barahona',28,35.4,'Media',4, 'Liceo San Jose',4,3,'A')

INSERT INTO Estudiantes
VALUES ('201101234562','Pasaporte','Martini','Armani','Carlos','Estevan','Carlitos','8095555552','C/Duarte, No.01, Almirante','Brazil','M','01-01-1992','Sau Paulo',30,45.4,'Media',4, 'Liceo Espinal',4,1,'A')

INSERT INTO Estudiantes
VALUES ('201101234563','Cedula','Pineda','Lopez','Elizabeth','Patricia','Elili','8095555553','C/Duarte, No.23, Almirante','Dominicana','F','01-01-1989','Santo Domingo',25,45.4,'Basica',3, 'Liceo Experimental',3,3,'A')

INSERT INTO Estudiantes
VALUES ('201101234564','NSS','Rodriguez','Malmolejos','Luis','Alberto','Luisito','8095555554','C/Duarte, No.45, Almirante','Dominicana','M','01-01-1989','Santo Domingo',30,45.4,'Media',4, 'Colegio Don Bosco',4,1,'I')

------------------------- 3. Tutores_Estudiantes ---------------------------

INSERT INTO Tutores_Estudiantes
VALUES (1,1,'Mamá')

INSERT INTO Tutores_Estudiantes
VALUES (2,2,'Papá')

INSERT INTO Tutores_Estudiantes
VALUES (3,3,'Hermano')

INSERT INTO Tutores_Estudiantes
VALUES (4,4,'Tio')

INSERT INTO Tutores_Estudiantes
VALUES (5,5,'Abuela')

---------------------------- 4. Roles ------------------------------

INSERT INTO Roles (Rol_Nombre, Rol_Descripcion, Rol_Update_By, Rol_Status)
VALUES ('db_owner','Realiza las actividades de configuración y mantenimiento.','Ariel Novas','A')

INSERT INTO Roles (Rol_Nombre, Rol_Descripcion, Rol_Update_By, Rol_Status)
VALUES ('db_securityadmin','modifican la pertenencia a roles .','Edwin Bratini','A')

INSERT INTO Roles (Rol_Nombre, Rol_Descripcion, Rol_Update_By, Rol_Status)
VALUES ('db_backupoperator','Crean copias de seguridad de la base de datos.','Elizabeth Gutierrez','I')

INSERT INTO Roles (Rol_Nombre, Rol_Descripcion, Rol_Update_By, Rol_Status)
VALUES ('db_bacckupoperator','Crean copias de seguridad de la base de datos.','Jorge Posada','A')

INSERT INTO Roles (Rol_Nombre, Rol_Descripcion, Rol_Update_By, Rol_Status)
VALUES ('db_ownerr','Realiza las actividades de configuración y mantenimiento.','Jose Beltre','I')

----------------------------- 5. Usuarios -------------------------------

INSERT INTO Usuarios (Rol_Id,Usr_Login,Usr_Password,usr_fecha_creacion,usr_verificado,usr_update_by,Usr_Status)
VALUES (1,'usr01','usr20111','04-12-2009','S','Ariel Novas','A')

INSERT INTO Usuarios (Rol_Id,Usr_Login,Usr_Password,usr_fecha_creacion,usr_verificado,usr_update_by,Usr_Status)
VALUES (2,'usr02','usr20112','04-12-2010','S','Edwin Bratini','A')

INSERT INTO Usuarios (Rol_Id,Usr_Login,Usr_Password,usr_fecha_creacion,usr_verificado,usr_update_by,Usr_Status)
VALUES (3,'usr03','usr20113','04-12-2009','S','Jose Beltre','A')

INSERT INTO Usuarios (Rol_Id,Usr_Login,Usr_Password,usr_fecha_creacion,usr_verificado,usr_update_by,Usr_Status)
VALUES (4,'usr04','usr20114','04-12-2011','S','Albert Perez','I')

INSERT INTO Usuarios (Rol_Id,Usr_Login,Usr_Password,usr_fecha_creacion,usr_verificado,usr_update_by,Usr_Status)
VALUES (5,'usr05','usr20115','04-12-2008','S','Bronny Campusano','A')

---------------------------- 6. Referimientos ----------------------------

INSERT INTO Referimientos
VALUES (1,1,'08-01-2009','2009-2010','Celeste Garcia','Conducta Anormal','Charlar','Problemas Familiares','A')

INSERT INTO Referimientos
VALUES (2,2,'08-01-2008','2009-2010','Celeste Garcia','Poco Interes','Reunion Familiar','Depresion','A')

INSERT INTO Referimientos
VALUES (3,3,'08-01-2010','2010-2011','Andrea Gutierrez','Problema de aprendisaje','Observar','Ansiedad','A')

INSERT INTO Referimientos
VALUES (4,4,'08-01-2009','2009-2010','Ana Gonzalez','Conducta Anormal','Charlar','Problemas Familiares','A')

INSERT INTO Referimientos
VALUES (5,5,'08-01-2010','2010-2011','Celeste Garcia','Conducta Anormal','Charlar','Problemas Familiares','A')

-------------------------------- 7. Casos ---------------------------------

INSERT INTO Casos
VALUES (1, '08-08-2009','2009-2010','Muy Bien','Dificultad de expresión. No de comprensión.','Alteración del patrón de sueño r/c patrón de actividad diurna y asincronía circadiana y humedad m/p despertarse tres o más veces por la noche.','S','Regular: actividad - descanso - reposo - sueño. ','Evolucion Satisfactoria','Mantener el tratamiento','A')

INSERT INTO Casos
VALUES (2, '08-08-2008','2009-2010','Muy Mal','Dificultad de expresión. No de comprensión.','Alteración del patrón de sueño r/c patrón de actividad diurna y asincronía circadiana y humedad m/p despertarse tres o más veces por la noche.','S','Regular: actividad - descanso - reposo - sueño. ','Evolucion Satisfactoria','Mantener el tratamiento','C')

INSERT INTO Casos
VALUES (3, '08-08-2010','2010-2011','Muy Bien','Dificultad de expresión. No de comprensión.','Alteración del patrón de sueño r/c patrón de actividad diurna y asincronía circadiana y humedad m/p despertarse tres o más veces por la noche.','N','Regular: actividad - descanso - reposo - sueño. ','Evolucion Satisfactoria','Mantener el tratamiento','P')

INSERT INTO Casos
VALUES (4, '08-08-2009','2009-2010','Muy Bien','Dificultad de expresión. No de comprensión.','Alteración del patrón de sueño r/c patrón de actividad diurna y asincronía circadiana y humedad m/p despertarse tres o más veces por la noche.','S','Regular: actividad - descanso - reposo - sueño. ','Evolucion Satisfactoria','Mantener el tratamiento','A')

INSERT INTO Casos
VALUES (5, '08-08-2009','2009-2010','Normal','Dificultad de expresión. No de comprensión.','Alteración del patrón de sueño r/c patrón de actividad diurna y asincronía circadiana y humedad m/p despertarse tres o más veces por la noche.','S','Regular: actividad - descanso - reposo - sueño. ','Evolucion Satisfactoria','Mantener el tratamiento','A')

--------------------------- 8. Pruebas_Psicologicas ----------------------------

INSERT INTO Pruebas_Psicologicas
VALUES (1, 1, '08-08-2011','Alteraciones de aprendizaje y memoria','Resultado Positivios','Buen desenvolvimiento','S')

INSERT INTO Pruebas_Psicologicas
VALUES (2, 2, '01-07-2011','Desarrollo Escolar','Resultados Negativos','Bajo Rendimiento Escolar','S')

INSERT INTO Pruebas_Psicologicas
VALUES (3, 3, '01-06-2011','Áreas académica, social y familiar	','Resultado Positivios','Buen desenvolvimiento','S')

INSERT INTO Pruebas_Psicologicas
VALUES (4, 4, '01-06-2011','Áreas académica, social y familiar	','Resultado Positivios','Buen desenvolvimiento','N')

INSERT INTO Pruebas_Psicologicas
VALUES (5, NULL, '01-06-2011','Áreas académica, social y familiar	','Resultado Positivios','Buen desenvolvimiento','N')

-------------------------- 9. Ubicacion_Pruebas -------------------------

INSERT INTO Ubicacion_Pruebas (pps_id,ubp_url)
VALUES (1, 'asdfsdf')

INSERT INTO Ubicacion_Pruebas (pps_id,ubp_url)
VALUES (2, ' dfasdf')

INSERT INTO Ubicacion_Pruebas (pps_id,ubp_url)
VALUES (3, ' dafasd')

INSERT INTO Ubicacion_Pruebas (pps_id,ubp_url)
VALUES (4, ' adsfdfadf')

INSERT INTO Ubicacion_Pruebas (pps_id,ubp_url)
VALUES (5, ' asdf')

---------------------- 10. Historia_Clinica ----------------------

INSERT INTO Historia_Clinica 
VALUES (1, '08-06-2009','Ariel Novas','08-08-2010 09:04:09','A')

INSERT INTO Historia_Clinica 
VALUES (2, '01-01-2010','Edwin Bratini','09-10-2010 08:04:08','A')

INSERT INTO Historia_Clinica 
VALUES (3, '04-05-2008','Angela Lopez','07-12-2010 04:05:09','I')

INSERT INTO Historia_Clinica 
VALUES (4, '01-12-2008','Angela Losada','08-12-2010 01:05:02','I')

INSERT INTO Historia_Clinica 
VALUES (5, '04-05-2008','Angela Lopez','07-12-2010 01:05:09','A')


------------------------ 11. Ant_Pers_Madre ------------------------

INSERT INTO Ant_Pers_Madre 
VALUES (1,'S',9, 'N','N','NINGUNO','Anemia','S','F','Ninguna')

INSERT INTO Ant_Pers_Madre 
VALUES (2,'S',8, 'S','N','ALCOHOL','Anemia','S','M', 'Cualquier cosa')

INSERT INTO Ant_Pers_Madre 
VALUES (3,'N',8, 'S','S','Cocaina','Anemia','S','M', 'no se que es')

INSERT INTO Ant_Pers_Madre 
VALUES (4,'N',9, 'N','N','TABACO','Precion Alta','S','I', 'no se que es')

INSERT INTO Ant_Pers_Madre 
VALUES (5,'N',7, 'S','S','Alcohol','Epatitis','S','M', 'no se que es')

------------------------- 12. Ant_Neonatal -----------------------------

INSERT INTO Ant_Neonatal 
VALUES (1,'Prematuro',6,'S','S','S','En buen estado')

INSERT INTO Ant_Neonatal
VALUES (2,'Prematuro',4,'S','S','N','En estado critico')

INSERT INTO Ant_Neonatal
VALUES (3,'Termino',3,'S','S','N','En estado critico')

INSERT INTO Ant_Neonatal
VALUES (4,'Termino',5,'S','N','N','En Buen estado')

INSERT INTO Ant_Neonatal
VALUES (5,'Prematuro',3,'N','N','N','En estado critico')

--------------------- 13. Ant_Recien_Nacido ------------------------

INSERT INTO Ant_Recien_Nacido
VALUES (1,'S', 2, 'No Vidente')

INSERT INTO Ant_Recien_Nacido
VALUES (2,'N', 0,'Problema para respirar')

INSERT INTO Ant_Recien_Nacido
VALUES (3,'N', 0,'Epileptico')

INSERT INTO Ant_Recien_Nacido
VALUES (4,'S', 3,'Ninguno')

INSERT INTO Ant_Recien_Nacido
VALUES (5,'N', 1,'Ninguno')

--------------------- 14. Ant_Psicomotriz_Lenguaje -------------------

SELECT * FROM Ant_Psicomotriz_Lenguaje
GO

INSERT INTO Ant_Psicomotriz_Lenguaje
VALUES(1, 3, 'Excelente', 'Mama, Papa, Agua, Leche', 'Excelente')

INSERT INTO Ant_Psicomotriz_Lenguaje
VALUES(2, 2, 'Buena', 'Mama, Papa, Leche', 'Excelente')

INSERT INTO Ant_Psicomotriz_Lenguaje
VALUES(3, 4, 'Buena', 'Mama, Papa, Leche, Jugo', 'Bien')

INSERT INTO Ant_Psicomotriz_Lenguaje
VALUES(4, 4, 'Muy bien', 'Mama, Papa, Leche, Jugo', 'Bien')

INSERT INTO Ant_Psicomotriz_Lenguaje
VALUES(5, 4, 'Buena', 'Mama, Papa, Leche, Jugo', 'Bien')

----------------------- 15. Ant_Psicosocial_Sexual ---------------------

INSERT INTO Ant_Psicosocial_Sexual
VALUES(1,1,1,'Buen Habito',13,12,'Hacer una orgia', 'Normal')

INSERT INTO Ant_Psicosocial_Sexual 
VALUES(2,1,2,'Malo',14,14,'Ninguno', 'Insomnio')

INSERT INTO Ant_Psicosocial_Sexual 
VALUES(3,1,3,'Malo',13,14, 'Ninguno','Insomnio')

INSERT INTO Ant_Psicosocial_Sexual 
VALUES(4,3,3,'Malo',13,14, 'Ninguno','Insomnio')

INSERT INTO Ant_Psicosocial_Sexual 
VALUES(5,2,3,'Malo',13,14, 'Desconocido','Insomnio')

---------------------------- 16. Escolaridad ----------------------------

INSERT INTO Escolaridad
VALUES (1, 5, 'Media', 8,'Publica','N','N',0, 'Bueno','S', 'Situacion Economica')

INSERT INTO Escolaridad
VALUES (2, 4, 'Basica', 7,'Privada','N','N',0, 'Bueno','S', 'Mejores Estudios')

INSERT INTO Escolaridad
VALUES (3, 3, 'Media', 6,'Especial','S','S',2, 'Normal','S', 'Problema de Aprendisaje')

INSERT INTO Escolaridad
VALUES (4, 5, 'Media', 8,'Publica','S','N',0, 'Bueno','S', 'Situacion Economica')

INSERT INTO Escolaridad
VALUES (5, 3, 'Basica', 7,'Publica','N','N',0, 'Bueno','S', 'Situacion Economica')


-------------------------------- 17. Permisos ----------------------------------

INSERT INTO Permisos (Per_Nombre,Per_Descripcion,Per_Update_By,Per_Status)
VALUES ('ADMINISTER BULK OPERATIONS','Gestion de las operaciones en la base de datos','Andres Molla','A')

INSERT INTO Permisos (Per_Nombre,Per_Descripcion,Per_Update_By,Per_Status)
VALUES ('CREATE ANY DATABASE','Permiso para crear bases de datos','Pedro Lopez','A')

INSERT INTO Permisos (Per_Nombre,Per_Descripcion,Per_Update_By,Per_Status)
VALUES ('ALTER RESOURCES','Modifica los recursos','Pedro Lopez','I')

INSERT INTO Permisos (Per_Nombre,Per_Descripcion,Per_Update_By,Per_Status)
VALUES ('db_datareader','Modifica los datos','Pedro Lopez','I')

INSERT INTO Permisos (Per_Nombre,Per_Descripcion,Per_Update_By,Per_Status)
VALUES ('db_denydatawriter','quita permiso de escritura','Juan Campusano','A')

------------------------------ 18. Roles_Permisos ------------------------------

INSERT INTO Roles_Permisos
VALUES (1,1)

INSERT INTO Roles_Permisos
VALUES (2,2)

INSERT INTO Roles_Permisos
VALUES (3,3)

INSERT INTO Roles_Permisos
VALUES (4,4)

INSERT INTO Roles_Permisos
VALUES (5,5)

----------------------------- 19. Bitacora -------------------------------

INSERT INTO Bitacora (Usr_Id,bit_fecha,bit_fuente,bit_categoria,bit_descripcion)
VALUES(1,'01-01-2008','Fuente1','Categoria1','DESCRIPCION AQUI')

INSERT INTO Bitacora (Usr_Id,bit_fecha,bit_fuente,bit_categoria,bit_descripcion)
VALUES(2,'01-01-2009','Fuente2','Categoria2','DESCRIPCION AQUI')

INSERT INTO Bitacora (Usr_Id,bit_fecha,bit_fuente,bit_categoria,bit_descripcion)
VALUES(3,'01-01-2010','Fuente3','Categoria3','DESCRIPCION AQUI')

INSERT INTO Bitacora (Usr_Id,bit_fecha,bit_fuente,bit_categoria,bit_descripcion)
VALUES(4,'01-01-2011','Fuente4','Categoria4','DESCRIPCION AQUI')

INSERT INTO Bitacora (Usr_Id,bit_fecha,bit_fuente,bit_categoria,bit_descripcion)
VALUES(5,'01-01-2007','Fuente5','Categoria5','DESCRIPCION AQUI')