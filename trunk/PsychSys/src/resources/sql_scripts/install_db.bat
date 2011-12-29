rem ...................
rem	File Name: install_db.bat
rem	Desc.: Script bat para instalar base de datos
rem	By: Edwin Bratini
rem	Date: 29-12-2011
rem ..................

sqlcmd -i crear_bd.sql
sqlcmd -i crear_login-db_user.sql
sqlcmd -i crear_tablas.sql
slqcmd -i crear_vistas.sql
sqlcmd -i def_rol_usuario_insercion.sql
sqlcmd -i insertar_registros.sql
pause