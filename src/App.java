import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        ValidacionArchivos.verificarArchivos();
        Scanner scanner = new Scanner(System.in);
        Administrador admin = new Administrador();

        if (admin.verificarCredenciales(scanner)) {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n--- Sistema de administración de citas ---");
                System.out.println("1. Alta de Doctor");
                System.out.println("2. Alta de Paciente");
                System.out.println("3. Crear Cita");
                System.out.println("4. Consultar Citas");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpieza del buffer

                switch (opcion) {
                    case 1:
                        Doctor.altaDoctor(scanner);
                        break;
                    case 2:
                        Paciente.altaPaciente(scanner);
                        break;
                    case 3:
                        Cita.crearCita(scanner);
                        break;
                    case 4:
                        Cita.consultarCitas();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            }
        } else {
            System.out.println("Acceso denegado. Credenciales incorrectas.");
        }
        scanner.close();
    }
}

class Doctor {
    private String idDoctor;
    public String nombre;
    private String especialidad;

    public Doctor(String idDoctor, String nombre, String especialidad) {
        this.idDoctor = idDoctor;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public static void altaDoctor(Scanner scanner) {
        System.out.println("Ingrese el ID del doctor: ");
        String id = scanner.nextLine();
        System.out.println("Ingrese el nombre del doctor: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la especialidad del doctor: ");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(id, nombre, especialidad);
        doctor.guardarDoctor();
    }

    public void guardarDoctor() {
        try (FileWriter writer = new FileWriter("db/doctores.csv", true)) {
            writer.append(idDoctor).append(",").append(nombre).append(",").append(especialidad).append("\n");
            System.out.println("Doctor guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el doctor: " + e.getMessage());
        }
    }

    public String getIdDoctor() {
        return idDoctor;
    }
}

class Paciente {
    private String idPaciente;
    public String nombre;

    public Paciente(String idPaciente, String nombre) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
    }

    public static void altaPaciente(Scanner scanner) {
        System.out.println("Ingrese el ID del paciente: ");
        String id = scanner.nextLine();
        System.out.println("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre);
        paciente.guardarPaciente();
    }

    public void guardarPaciente() {
        try (FileWriter writer = new FileWriter("db/pacientes.csv", true)) {
            writer.append(idPaciente).append(",").append(nombre).append("\n");
            System.out.println("Paciente guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el paciente: " + e.getMessage());
        }
    }

    public String getIdPaciente() {
        return idPaciente;
    }
}

class Cita {
    private String idCita;
    private String fecha;
    private String hora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String idCita, String fecha, String hora, String motivo, Doctor doctor, Paciente paciente) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public static void crearCita(Scanner scanner) {
        System.out.println("Ingrese el ID de la cita: ");
        String idCita = scanner.nextLine();
        System.out.println("Ingrese la fecha de la cita (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese la hora de la cita (HH:MM): ");
        String hora = scanner.nextLine();
        System.out.println("Ingrese el motivo de la cita: ");
        String motivo = scanner.nextLine();

        System.out.println("Ingrese el ID del doctor para esta cita: ");
        String idDoctor = scanner.nextLine();
        Doctor doctor = buscarDoctorPorId(idDoctor);

        if (doctor == null) {
            System.out.println("Error: Doctor no encontrado.");
            return;
        }

        System.out.println("Ingrese el ID del paciente para esta cita: ");
        String idPaciente = scanner.nextLine();
        Paciente paciente = buscarPacientePorId(idPaciente);

        if (paciente == null) {
            System.out.println("Error: Paciente no encontrado.");
            return;
        }

        Cita cita = new Cita(idCita, fecha, hora, motivo, doctor, paciente);
        cita.guardarCita();
    }

    public void guardarCita() {
        try (FileWriter writer = new FileWriter("db/citas.csv", true)) {
            writer.append(idCita).append(",")
                    .append(fecha).append(",")
                    .append(hora).append(",")
                    .append(motivo).append(",")
                    .append(doctor.getIdDoctor()).append(",")
                    .append(paciente.getIdPaciente()).append("\n");
            System.out.println("Cita guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la cita: " + e.getMessage());
        }
    }

    public static void consultarCitas() {
        try (BufferedReader reader = new BufferedReader(new FileReader("db/citas.csv"))) {
            String linea;
            System.out.println("\n--- Listado de Citas ---");

            while ((linea = reader.readLine()) != null) {
                String[] datosCita = linea.split(",");

                String idCita = datosCita[0];
                String fecha = datosCita[1];
                String hora = datosCita[2];
                String motivo = datosCita[3];
                String idDoctor = datosCita[4];
                String idPaciente = datosCita[5];

                Doctor doctor = buscarDoctorPorId(idDoctor);
                Paciente paciente = buscarPacientePorId(idPaciente);

                if (doctor != null && paciente != null) {
                    System.out.printf("Cita ID: %s\nFecha: %s\nHora: %s\nMotivo: %s\n", idCita, fecha, hora, motivo);
                    System.out.printf("Doctor: %s (ID: %s)\nPaciente: %s (ID: %s)\n\n",
                            doctor.nombre, doctor.getIdDoctor(), paciente.nombre, paciente.getIdPaciente());
                } else {
                    System.out.println("Error al recuperar la información del doctor o paciente.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer las citas: " + e.getMessage());
        }
    }

    public static Doctor buscarDoctorPorId(String idDoctor) {
        try (BufferedReader reader = new BufferedReader(new FileReader("db/doctores.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(idDoctor)) {
                    return new Doctor(datos[0], datos[1], datos[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar el doctor: " + e.getMessage());
        }
        return null;
    }

    public static Paciente buscarPacientePorId(String idPaciente) {
        try (BufferedReader reader = new BufferedReader(new FileReader("db/pacientes.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(idPaciente)) {
                    return new Paciente(datos[0], datos[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar el paciente: " + e.getMessage());
        }
        return null;
    }
}

class Administrador {
    private static final String USUARIO = "admin";
    private static final String CONTRASENA = "1234";

    public boolean verificarCredenciales(Scanner scanner) {
        System.out.println("Ingrese su usuario: ");
        String usuario = scanner.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        return USUARIO.equals(usuario) && CONTRASENA.equals(contrasena);
    }
}

class ValidacionArchivos {
    public static void verificarArchivos() {
        verificarYCrearArchivo("db/doctores.csv");
        verificarYCrearArchivo("db/pacientes.csv");
        verificarYCrearArchivo("db/citas.csv");
    }

    public static void verificarYCrearArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs(); // Crear directorio si no existe
                archivo.createNewFile(); // Crear archivo si no existe
                System.out.println("Archivo creado: " + rutaArchivo);
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }
}
