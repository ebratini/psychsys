/*
	File Name: crear_tablas.sql
	Desc.: Script sql para la creacion de las tablas de la base de datos del proyecto de sistemas PsychSys
	By: Edwin Bratini, Ariel Novas
	Date: 18-11-2011
*/

-- For MS SQL Server Database Engine
-- Creacion de tablas

USE PsychSysDB --> Usando la Base de Datos PsychSysDB
GO

---1. Tutores --------------------------------

CREATE TABLE Tutores
(
	tut_id INT NOT NULL IDENTITY,
	tut_dni CHAR(12) NOT NULL,
	tut_tipo_dni CHAR(20) NOT NULL,
	tut_primer_apellido VARCHAR(60) NOT NULL,
	tut_segundo_apellido VARCHAR(60) NOT NULL,
	tut_primer_nombre VARCHAR(60) NOT NULL,
	tut_segundo_nombre VARCHAR(60) NULL,
	tut_telefono VARCHAR(10) NULL,
	tut_direccion VARCHAR(80) NOT NULL,
	tut_email VARCHAR(60) NULL,
	tut_nacionalidad VARCHAR(20) NOT NULL,
	tut_genero CHAR(1) NOT NULL,
	tut_estado_civil CHAR(20) NOT NULL,
	tut_status CHAR(1) NOT NULL,
	
	-- Restricciones CHECK
	CHECK (tut_tipo_dni IN ('Cedula', 'Pasaporte', 'NSS')),
	CHECK (tut_genero IN ('M', 'F')),
	CHECK (tut_estado_civil IN ('Soltero(a)','Casado(a)','Viudo(a)','Divorciado(a)')),
	CHECK (tut_status IN ('A', 'I')),
	
	--> Restricciones PK
	CONSTRAINT PK_Tutores PRIMARY KEY(tut_id)
);
GO

-->Creacion de indice no agrupado en el campo Tut_Dni

CREATE UNIQUE NONCLUSTERED INDEX I_Tut_DNI
ON Tutores(tut_dni)
GO

--> Creacion de indice NO agrupado en el campo Tut_Primer_Apellido

CREATE NONCLUSTERED INDEX I_Tut_Primer_Apellido
ON Tutores(tut_primer_apellido)
GO


----------------------------------------------


-- 2. Estudiantes ----------------------------

CREATE TABLE Estudiantes
(
	est_id INT NOT NULL IDENTITY,
	est_dni CHAR(12) NULL,
	est_tipo_dni VARCHAR(20) NULL,
	est_primer_apellido VARCHAR(60) NOT NULL,
	est_segundo_apellido VARCHAR(60) NOT NULL,
	est_primer_nombre VARCHAR(60) NOT NULL,
	est_segundo_nombre VARCHAR(60) NULL,
	est_apodo VARCHAR(60) NULL,	
	est_telefono VARCHAR(10) NULL,
	est_direccion VARCHAR(80) NOT NULL,
	est_nacionalidad VARCHAR(20) NOT NULL,
	est_genero CHAR(1) NOT NULL,
	est_fecha_nacimiento DATE NOT NULL,
	est_lugar_nacimiento VARCHAR(60) NOT NULL,
	est_talla DECIMAL(10,2) NULL,
	est_peso DECIMAL(10,2) NULL,
	est_nivel_escolar VARCHAR(20) NOT NULL,
	est_grado_escolar INT NOT NULL,
	est_escuela_procedencia VARCHAR(60) NULL,
	est_cant_hermanos INT NOT NULL,
	est_orden_hermanos INT NOT NULL,
	est_status CHAR(1) NOT NULL,
	
	--> Restricciones CHECK
	CHECK (est_tipo_dni IN ('Cedula', 'Pasaporte', 'NSS')),
	CHECK (est_genero IN ('M','F')),
	CHECK (est_nivel_escolar IN ('Basica', 'Media', 'Inicial')),
	CHECK (est_status IN ('A', 'I')),
	CHECK (est_grado_escolar >= 1 AND est_grado_escolar <= 8),
	
	--> Restriccion PK
	CONSTRAINT PK_Estudiantes PRIMARY KEY (est_id)
);
GO

CREATE UNIQUE NONCLUSTERED INDEX I_Est_DNI
ON Estudiantes(est_dni)
GO

CREATE NONCLUSTERED INDEX I_Est_Primer_Apellido
ON Estudiantes(est_primer_apellido)
GO

----------------------------------------------


-- 3. Tutores_Estudiantes --------------------

CREATE TABLE Tutores_Estudiantes
(
	tut_id INT NOT NULL,
	est_id INT NOT NULL,
	tes_relacion_familiar VARCHAR(40) NOT NULL,
	
	--> Restriccion PK
	CONSTRAINT PK_Tutores_Estudiantes PRIMARY KEY(tut_id, est_id),
	
	--> Restriccion FK References TUTORES
	CONSTRAINT FK_Tutores_Estudiantes_Tutores FOREIGN KEY(tut_id) REFERENCES Tutores(tut_id),
	
	--> Restriccion FK References ESTUDIANTES
	CONSTRAINT FK_Tutores_Estudiantes_Estudiantes FOREIGN KEY(est_id) REFERENCES Estudiantes(est_id)
);
GO

---------------------------------------------- 


-- 4. Roles ----------------------------------

CREATE TABLE Roles
(
	rol_id INT NOT NULL IDENTITY,
	rol_nombre VARCHAR(20) NOT NULL,
	rol_descripcion VARCHAR(60) NOT NULL,
	rol_update_by VARCHAR(60) NOT NULL,
	rol_update_date DATE DEFAULT (GETDATE()) NOT NULL,
	rol_status CHAR(1) NOT NULL,
	
	-- Restricciones CHECK
	CHECK (rol_status IN ('A', 'I')),
	
	-- Restriccion PK
	CONSTRAINT PK_Roles PRIMARY KEY(rol_id)
);
GO

CREATE UNIQUE NONCLUSTERED INDEX I_Rol_Nombre
ON Roles(rol_nombre)
GO

----------------------------------------------


-- 5. Usuarios -------------------------------

CREATE TABLE Usuarios
(
	usr_id INT NOT NULL IDENTITY,
	rol_id INT NOT NULL,
	usr_login VARCHAR(60) NOT NULL,
	usr_password VARCHAR(60) NOT NULL,
	usr_fecha_creacion DATE NOT NULL,
	usr_verificado CHAR(1) NOT NULL,
	usr_update_by VARCHAR(60) NOT NULL,
	usr_update_date DATE DEFAULT (GETDATE()) NOT NULL,
	usr_status CHAR(1) NOT NULL,
	
	-- Restricciones CHECK
	CHECK (usr_verificado IN ('S', 'N')),
	CHECK (usr_status IN ('A', 'I')),
	CHECK (LEN(usr_password ) >= 7),
	
	-- Restriccion PK
	CONSTRAINT PK_Usuarios PRIMARY KEY(usr_id),
	
	-- Restricciones FK
	CONSTRAINT FK_Usuarios_Roles FOREIGN KEY(rol_id) REFERENCES Roles(rol_id)
);
GO

CREATE UNIQUE NONCLUSTERED INDEX I_Usr_Login
ON Usuarios(usr_login)
GO

----------------------------------------------


-- 6. Referimientos --------------------------

CREATE TABLE Referimientos
(
	ref_id INT NOT NULL IDENTITY,
	est_id INT NOT NULL,
	usr_id INT NOT NULL,
	ref_fecha DATE NOT NULL,
	ref_anio_escolar CHAR(10) NOT NULL,	
	ref_nombre_referidor VARCHAR(60) NOT NULL,
	ref_motivo TEXT NOT NULL,
	ref_acciones_referidor TEXT NULL,
	ref_observaciones_orientador TEXT NULL,
	ref_estado_referimiento CHAR(1) NOT NULL,
	
	--> Restricciones CHECK
	CHECK (ref_estado_referimiento IN ('A', 'C', 'P')), -- Abierto, Cerrado, Pendiente
	
	--> Restriccion PK
	CONSTRAINT PK_Referimientos PRIMARY KEY(ref_id),
	
	--> Restricciones FK
	CONSTRAINT FK_Referimientos_Estudiantes FOREIGN KEY(est_id) REFERENCES Estudiantes(est_id),
	CONSTRAINT FK_Referimientos_Usuarios FOREIGN KEY(usr_id) REFERENCES Usuarios(usr_id)
);

----------------------------------------------


-- 7. Casos ---------------------------------

CREATE TABLE Casos
(
	cso_id INT NOT NULL IDENTITY,
	ref_id INT NOT NULL,
	cso_fecha DATE NOT NULL,
	cso_anio_escolar VARCHAR(9) NOT NULL,
	cso_analisis_resultados_pruebas TEXT NULL,
	cso_juicio_clinico TEXT NOT NULL,
	cso_diagnostico TEXT NULL,
	cso_diagnostico_definitivo CHAR(1) NOT NULL,
	cso_tratamiento TEXT NULL,
	cso_resumen_evolucion TEXT NULL,
	cso_recomendaciones TEXT NULL,
	cso_estado_caso CHAR(1) NOT NULL,
	
	--> Restricciones CHECK
	CHECK (cso_diagnostico_definitivo IN ('S', 'N')),
	CHECK (cso_estado_caso IN ('A', 'C', 'P')), -- Abierto, Cerrado, Pendiente
	
	--> Restriccion PK
	CONSTRAINT PK_Casos PRIMARY KEY(cso_id),
		
	--> Restriccion FK references Referimientos(ref_id)
	CONSTRAINT FK_Casos_Referimientos FOREIGN KEY(ref_id) REFERENCES Referimientos(ref_id)
);

----------------------------------------------


-- 8. Pruebas_Psicologicas -------------------

CREATE TABLE Pruebas_Psicologicas
(
	pps_id INT NOT NULL IDENTITY,
	est_id INT NOT NULL,
	cso_id INT NULL,
	pps_fecha_aplicacion DATE NOT NULL,
	pps_nombre_prueba VARCHAR(60) NOT NULL,
	pps_resultados TEXT NULL,
	pps_interpretacion TEXT NULL,
	pps_correcion_automatica CHAR(1) NOT NULL,
	
	--> Restricciones CHECK
	CHECK (pps_correcion_automatica IN ('S', 'N')),
	
	--> Restriccion PK
	CONSTRAINT PK_Pruebas_Psicologicas PRIMARY KEY(pps_id),
	
	-->Restriccion FK
	CONSTRAINT FK_Pruebas_Psicologicas_Estudiantes FOREIGN KEY(est_id) REFERENCES Estudiantes(est_id),
	CONSTRAINT FK_Pruebas_Psicologicas_Caso FOREIGN KEY(cso_id) REFERENCES Casos(cso_id)
);

----------------------------------------------


-- 9. Ubicacion_Pruebas ----------------------

CREATE TABLE Ubicacion_Pruebas
(
	ubp_id INT NOT NULL IDENTITY,
	pps_id INT NOT NULL,
	ubp_url VARCHAR(80) NOT NULL,
	
	-- Restriccion PK
	CONSTRAINT PK_Ubicacion_Pruebas PRIMARY KEY(ubp_id),
	
	-- Restricciones FK
	CONSTRAINT FK_Ubicacion_Pruebas_Pruebas_Psicologicas FOREIGN KEY(pps_id) REFERENCES Pruebas_Psicologicas(pps_id)
);

----------------------------------------------


-- 10. Historia_Clinica ----------------------

CREATE TABLE Historia_Clinica
(
	hic_id INT NOT NULL IDENTITY,
	est_id INT NOT NULL,
	hic_fecha_creacion DATE NOT NULL,
	hic_update_by VARCHAR(60) NOT NULL,
	hic_update_date DATE NOT NULL,
	hic_status CHAR(1) NOT NULL,
	
	-- Restricciones CHECK
	CHECK (hic_status IN ('A', 'I')),
	
	-- Restriccion PK
	CONSTRAINT PK_Historia_Clinica PRIMARY KEY(hic_id),
	
	-- Restricciones FK
	CONSTRAINT FK_Historia_Clinica_Estudiantes FOREIGN KEY(est_id) REFERENCES Estudiantes(est_id)
);

CREATE UNIQUE NONCLUSTERED INDEX I_Est_Id
ON Historia_Clinica(est_id)
GO

----------------------------------------------


-- 11. Ant_Pers_Madre ------------------------

CREATE TABLE Ant_Pers_Madre
(
	hic_id INT NOT NULL,
	apm_embarazo CHAR(1) NOT NULL,
	apm_duracion_embarazo INT NULL,
	apm_amenaza_aborto CHAR(1) NOT NULL,
	apm_intento_aborto CHAR(1) NOT NULL,
	apm_habitos_toxicos VARCHAR(60) NULL,
	apm_enfermedades_embarazo VARCHAR(60) NULL,
	apm_embarazo_deseado CHAR(1) NOT NULL,
	apm_sexo_preferido CHAR(1) NOT NULL,
	apm_alteraciones_psiquicas VARCHAR(60) NULL,
	
	-- Restricciones CHECK
	CHECK (apm_embarazo IN ('S', 'N')),
	CHECK (apm_amenaza_aborto IN ('S', 'N')),
	CHECK (apm_intento_aborto IN ('S', 'N')),
	CHECK (apm_embarazo_deseado IN ('S', 'N')),
	CHECK (apm_sexo_preferido IN ('M', 'F', 'I')), -- Masculino, Femenino, Indiferente
	CHECK (apm_duracion_embarazo >= 1 AND apm_duracion_embarazo <= 12),
	
	-- Restriccion PK
	CONSTRAINT PK_Ant_Pers_Madre PRIMARY KEY(hic_id),
	
	-- Restricciones FK
	CONSTRAINT FK_Ant_Pers_Madre_Historia_Clinica FOREIGN KEY(hic_id) REFERENCES Historia_Clinica(hic_id)
);

----------------------------------------------


-- 12. Ant_Neonatal --------------------------

CREATE TABLE Ant_Neonatal
(
	hic_id INT NOT NULL,
	ane_tipo_parto VARCHAR(20) NOT NULL, -- Prematuro, a Termino
	ane_peso DECIMAL(10,2) NULL,
	ane_lloro CHAR(1) NULL,
	ane_asistencia_medica CHAR(1) NOT NULL,
	ane_colaboracion CHAR(1),
	ane_estado_general TEXT,
	
	-- Restricciones CHECK
	CHECK (ane_tipo_parto IN('Prematuro', 'Termino')),
	CHECK (ane_lloro IN ('S', 'N')),
	CHECK (ane_asistencia_medica IN ('S', 'N')),
	CHECK (ane_colaboracion IN ('S', 'N')),
	
	-- Restriccion PK
	CONSTRAINT PK_Ant_Neonatal PRIMARY KEY(hic_id),
	
	-- Restricciones FK
	CONSTRAINT FK_Ant_Neonatal_Historia_Clinica FOREIGN KEY(hic_id) REFERENCES Historia_Clinica(hic_id)
);

----------------------------------------------


-- 13. Ant_Recien_Nacido ---------------------

CREATE TABLE Ant_Recien_Nacido
(
	hic_id INT NOT NULL,
	arn_lactancia_materna CHAR(1) NOT NULL,
	arn_tiempo_lactancia INT NULL,
	arn_problemas_especiales TEXT NULL,
	
	-- Restricciones CHECK
	CHECK (arn_lactancia_materna IN ('S', 'N')),
	
	--> Restriccion PK
	CONSTRAINT PK_Ant_Recien_Nacido PRIMARY KEY(hic_id),
	
	--> Restriccion FK
	CONSTRAINT FK_Ant_Recien_Nacido_Historia_Clinica FOREIGN KEY(hic_id) REFERENCES Historia_Clinica(hic_id)
);

----------------------------------------------


-- 14. Ant_Psicomotriz_Lenguaje ---------------

CREATE TABLE Ant_Psicomotriz_Lenguaje
(
	hic_id INT NOT NULL,
	apl_edad_inicio_marcha INT NOT NULL,
	apl_motricidad_actual TEXT NULL,
	apl_primeras_palabras TEXT NULL,
	apl_vocabulario_actual TEXT NULL,
	
	-- Restriccion CHECK
	CHECK (apl_edad_inicio_marcha >= 1 AND apl_edad_inicio_marcha <= 999),
	
	--> Restriccion PK
	CONSTRAINT PK_Ant_Psicomotriz_Lenguaje PRIMARY KEY(hic_id),
	
	--> Restriccion FK
	CONSTRAINT FK_Ant_Psicomotriz_Lenguaje_Historia_Clinica FOREIGN KEY(hic_id) REFERENCES Historia_Clinica(hic_id)
);

----------------------------------------------


-- 15. Ant_Psicosocial_Sexual -----------------

CREATE TABLE Ant_Psicosocial_Sexual
(
	hic_id INT NOT NULL,
	aps_esfinter_anal INT NULL,
	aps_esfinter_vecical INT NULL,
	aps_habitos_alimenticios TEXT NULL,
	aps_menarquia INT NULL,
	aps_eyaculacion INT NULL,
	aps_curiosidad_sexual_actual TEXT NULL,
	aps_suenio TEXT NULL,
	
	-- Restriccion CHECK
	CHECK (aps_esfinter_anal >= 1 AND aps_esfinter_anal <= 999),
	CHECK (aps_esfinter_vecical >= 1 AND aps_esfinter_vecical <= 999),
	
	--> Restriccion PK
	CONSTRAINT PK_Ant_Psicosocial_Sexual PRIMARY KEY(hic_id),
	
	--> Restriccion FK
	CONSTRAINT FK_Ant_Psicosocial_Sexual_Historia_Clinica FOREIGN KEY(hic_id) REFERENCES Historia_Clinica(hic_id)
);

---------------------------------------------- 


-- 16. Escolaridad ----------------------------

CREATE TABLE Escolaridad
(
	hic_id INT NOT NULL,
	esc_edad_inicio_escolaridad INT NOT NULL,
	esc_nivel_escolar VARCHAR(20) NOT NULL,
	esc_grado_escolar_actual INT NOT NULL,
	esc_tipo_escuela_actual VARCHAR(20) NOT NULL,
	esc_recuperacion CHAR(1) NOT NULL,
	esc_ha_reprobado CHAR(1) NOT NULL,
	esc_cant_veces_reprobacion INT NOT NULL,
	esc_rendimiento_actual TEXT NULL,
	esc_cambio_escuela CHAR(1) NOT NULL,
	esc_razon_cambio_escuela TEXT NULL,
	
	-- Restricciones CHECK
	CHECK (esc_edad_inicio_escolaridad >= 1 AND esc_edad_inicio_escolaridad <= 999),
	CHECK (esc_nivel_escolar IN('Basica', 'Media', 'Inicial')),
	CHECK (esc_tipo_escuela_actual IN('Publica','Privada','Especial')),
	CHECK (esc_recuperacion IN('S','N')),
	CHECK (esc_ha_reprobado IN('S','N')),
	CHECK (esc_cambio_escuela IN ('S','N')),
	CHECK (esc_grado_escolar_actual >= 1 AND esc_grado_escolar_actual <= 8),
	
	--> Restriccion PK
	CONSTRAINT PK_Escolaridad PRIMARY KEY(hic_id),
	
	--> Restriccion FK
	CONSTRAINT FK_Escolaridad_Historia_Clinica FOREIGN KEY(hic_id) REFERENCES Historia_Clinica(hic_id)
);

----------------------------------------------


-- 17. Permisos -------------------------------

CREATE TABLE Permisos
(
	per_id INT NOT NULL IDENTITY,
	per_nombre VARCHAR(40) NOT NULL,
	per_descripcion VARCHAR(60) NOT NULL,
	per_update_by VARCHAR(60) NOT NULL,
	per_update_date DATE DEFAULT (GETDATE()) NOT NULL,
	per_status CHAR(1) NOT NULL,
	
	-- Restricciones CHECK
	CHECK (per_status IN ('A', 'I')),
	
	--> Restriccion PK
	CONSTRAINT PK_Permisos PRIMARY KEY(per_id)
);
GO

CREATE UNIQUE NONCLUSTERED INDEX I_Per_Nombre
ON Permisos(per_nombre)
GO

----------------------------------------------


-- 18. Roles_Permisos -------------------------

CREATE TABLE Roles_Permisos
(
	rol_id INT NOT NULL,
	per_id INT NOT NULL,
	
	--> Restriccion PK
	CONSTRAINT PK_Roles_Permisos PRIMARY KEY(rol_id, per_id),
	
	--> Restriccion FK
	CONSTRAINT FK_Roles_Permisos_Roles FOREIGN KEY(rol_id) REFERENCES Roles(rol_id),
	CONSTRAINT FK_Roles_Permisos_Permisos FOREIGN KEY(per_id) REFERENCES Permisos(per_id)
);

----------------------------------------------


-- 19. Bitacora -------------------------------

CREATE TABLE Bitacora
(
	bit_id INT NOT NULL IDENTITY,
	usr_id INT NOT NULL,
	bit_fecha DATE DEFAULT (GETDATE()) NOT NULL,
	bit_fuente VARCHAR(40) NOT NULL,
	bit_categoria VARCHAR(40) NOT NULL,
	bit_descripcion VARCHAR(60) NOT NULL,
	
	--> Restriccion PK
	CONSTRAINT PK_Bitacora PRIMARY KEY(bit_id),
	
	--> Restriccion FK
	CONSTRAINT FK_Bitacora_Usuarios FOREIGN KEY(usr_id) REFERENCES Usuarios(usr_id)
);

----------------------------------------------