•	Instalación y configuración.
-Descargar java JDK https://www.oracle.com/java/technologies/downloads/
-Descargar archivo Entrega-finalV1.0.jar
-Abrir cmd 
-Escribir lo siguiente: java -jar C:\Users\*TU USUARIO*\Descargas\Entrega-finalV1.0.jar
-Iniciar sesion como administrador: 
user: admin
pass:123




//USO DEL PROGRAMA
Interfaz de Usuario: Al iniciar el programa, se te pedirá ingresar las credenciales del administrador:

Usuario: admin
Contraseña: 1234
Opciones disponibles: Después de ingresar las credenciales correctamente, verás un menú con las siguientes opciones:

1. Alta de Doctor:

Esta opción permite agregar un nuevo doctor al sistema.
Cuando selecciones esta opción, se te pedirá que ingreses:
ID del Doctor: Un identificador único para el doctor.
Nombre del Doctor: El nombre completo del doctor.
Especialidad: La especialidad del doctor (por ejemplo, cardiología, pediatría, etc.).
Después de ingresar estos datos, el doctor se guardará en el archivo doctores.csv.
2. Alta de Paciente:

Esta opción permite agregar un nuevo paciente al sistema.
Al seleccionar esta opción, se te solicitará que ingreses:
ID del Paciente: Un identificador único para el paciente.
Nombre del Paciente: El nombre completo del paciente.
Una vez ingresados, el paciente se guardará en el archivo pacientes.csv.
3. Crear Cita:

Esta opción te permite crear una nueva cita para un paciente con un doctor.
Al seleccionar esta opción, deberás ingresar:
ID de la Cita: Un identificador único para la cita.
Fecha de la Cita: La fecha en formato YYYY-MM-DD.
Hora de la Cita: La hora en formato HH:MM.
Motivo de la Cita: Una breve descripción del motivo de la cita.
ID del Doctor: Debes ingresar el ID del doctor con quien se realizará la cita. El programa buscará automáticamente al doctor en el archivo doctores.csv.
ID del Paciente: Debes ingresar el ID del paciente que tiene la cita. El programa buscará al paciente en el archivo pacientes.csv.
Si los ID ingresados son válidos, la cita se guardará en el archivo citas.csv.
4. Consultar Citas:

Esta opción muestra una lista de todas las citas registradas en el sistema.
Al seleccionar esta opción, el programa leerá el archivo citas.csv y mostrará los detalles de cada cita, incluyendo:
ID de la cita
Fecha y hora
Motivo
Nombre y ID del doctor
Nombre y ID del paciente
Si hay algún problema al recuperar la información del doctor o paciente, se mostrará un mensaje de error.
5. Salir:

Selecciona esta opción para cerrar el programa de forma segura.
Notas adicionales:

Asegúrate de que los archivos doctores.csv, pacientes.csv y citas.csv estén en la carpeta db dentro del directorio del proyecto. Si no existen, se crearán automáticamente la primera vez que se ejecute el programa.
Siempre puedes reiniciar el programa para hacer más operaciones.





•	Créditos.
Este proyecto fue desarrollado por Oscar Garcia
