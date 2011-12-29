/*
	File Name: crear_login-db_user.sql
	Desc.: Script sql para la creacion de login para el motor de base de datos y un usuario para la base de datos
	By: Edwin Bratini
	Date: 29-12-2011
*/

-- For MS SQL Server Database Engine

-- creando el login para db engine
USE [master]
GO
CREATE LOGIN [PsychSysLgn] WITH PASSWORD=N'psychp@ss00', DEFAULT_DATABASE=[PsychSysDB], CHECK_EXPIRATION=OFF, CHECK_POLICY=ON
GO

-- creando el usuario de base de datos
USE [PsychSysDB]
GO
CREATE USER [PsychSysUsr] FOR LOGIN [PsychSysLgn]
GO
USE [PsychSysDB]
GO
 ALTER AUTHORIZATION ON SCHEMA::[db_owner] TO [PsychSysUsr]
GO
USE [PsychSysDB]
GO
EXEC sp_addrolemember N'db_owner', N'PsychSysUsr'
GO