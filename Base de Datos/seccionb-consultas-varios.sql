SELECT r.nombre, 'Roles' 
FROM usuarios u INNER JOIN rel_usuarios_roles ur ON ur.usuario_id=u.usuario_id 
INNER JOIN cat_roles r ON ur.rol_id=r.rol_id WHERE u.usuario="joseperez" AND u.estatus=1;

SELECT r.nombre, 'Roles' 
FROM usuarios u INNER JOIN rel_usuarios_roles ur ON ur.usuario_id=u.usuario_id 
INNER JOIN cat_roles r ON ur.rol_id=r.rol_id WHERE u.usuario=? AND u.estatus=1

SELECT u.contrasenia FROM usuarios u WHERE u.usuario = ?