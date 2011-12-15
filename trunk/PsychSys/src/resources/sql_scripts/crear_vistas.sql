/*
	File Name: crear_vistas.sql
	Desc.: Script sql para la creacion de las vistas para reportes
	By: Edwin Bratini
	Date: 15-12-2011
*/

USE PsychSysDB;
GO

-- Vista vEstudiantes


-- Vista vReferimientos
CREATE VIEW vReferimientos AS
SELECT r.*, e.est_primer_nombre + ' ' + e.est_primer_apellido AS 'Nombre Estudiante'
FROM Referimientos r
INNER JOIN Estudiantes e ON r.est_id = e.est_id;
GO

-- Vista vCasos

CREATE VIEW vCasos AS
SELECT     cso.*, ref.usr_id, ref.ref_fecha, ref.ref_anio_escolar, ref.ref_nombre_referidor, ref.ref_motivo, ref.ref_acciones_referidor, 
                      ref.ref_observaciones_orientador, ref.ref_estado_referimiento, pps.pps_id, pps.pps_fecha_aplicacion, pps.pps_nombre_prueba, pps.pps_resultados, 
                      pps.pps_interpretacion, pps.pps_correcion_automatica, est.*, est.est_primer_apellido + ' ' + est.est_segundo_apellido AS apellidos, 
                      est.est_primer_nombre + ' ' + est.est_segundo_nombre AS nombres
FROM         dbo.Referimientos ref INNER JOIN
                      dbo.Casos cso ON ref.ref_id = cso.ref_id LEFT OUTER JOIN
                      dbo.Pruebas_Psicologicas pps ON cso.cso_id = pps.cso_id INNER JOIN
                      dbo.Estudiantes est ON est.est_id = pps.est_id;
					 
GO