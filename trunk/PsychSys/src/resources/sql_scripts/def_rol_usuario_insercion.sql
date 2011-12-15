/*
	File Name: def_rol_usuario_insercion.sql
	Desc.: Script sql para la insercion de rol y usuario por defecto
	By: Edwin Bratini
	Date: 15-12-2011
*/


INSERT INTO [PsychSysDB].[dbo].[Roles]
           ([rol_nombre]
           ,[rol_descripcion]
           ,[rol_update_by]
           ,[rol_update_date]
           ,[rol_status])
     VALUES
           ('Administrator'
           ,'Administrador General'
           ,'Development Team'
           ,GETDATE()
           ,'A')
GO

INSERT INTO [PsychSysDB].[dbo].[Usuarios]
           ([rol_id]
           ,[usr_login]
           ,[usr_password]
           ,[usr_fecha_creacion]
           ,[usr_verificado]
           ,[usr_update_by]
           ,[usr_update_date]
           ,[usr_status])
     VALUES
           (1
           ,'Edwin Bratini'
           ,'eb88..1'
           ,GETDATE()
           ,'S'
           ,'Development Team'
           ,GETDATE()
           ,'A')
GO