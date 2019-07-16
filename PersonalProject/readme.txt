1.To start the project you will need to have a table in your database like this:
+-------------------+------------------------------------+------+-----+-------------------+-------------------+
| Field             | Type                               | Null | Key | Default           | Extra             |
+-------------------+------------------------------------+------+-----+-------------------+-------------------+
| id                | int(11)                            | NO   | PRI | NULL              | auto_increment    |
| transfered_object | varchar(45)                        | NO   |     | NULL              |                   |
| time              | timestamp                          | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| operation_type    | enum('DOWNLOAD','UPLOAD','DELETE') | NO   |     | NULL              |                   |
| file_size         | int(11)                            | NO   |     | NULL              |                   |
+-------------------+------------------------------------+------+-----+-------------------+-------------------+
2.Then you have to configure the hibernate.cfg.xml with your database, username and password.
3.Start StartFtpServerApp.java file and choose a desired port.
4.Then start ClientApplication.java file and select the port you choose in StartFtpServerApp.java file. 
5.Then choose username and password.
6.The only created user is username: admin, password: admin.You can use this user to connnect or you can connect with anonymous login.
7.And then follow the instructions.