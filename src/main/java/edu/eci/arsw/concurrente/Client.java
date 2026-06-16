package edu.eci.arsw.concurrente;

/*
* cliente Java Concurrente: - Simular creación concurrente de N turnos - Reportar tickets creados, errores y tiempo promedio
 * */


public class Client {
    public static void main(String[] args) {
        Turnos turnos = new Turnos();
        TurnosThread pacienteThread = new TurnosThread(turnos, "Paciente");
        TurnosThread recepcionistaThread = new TurnosThread(turnos, "Recepcionista");

        pacienteThread.start();
        recepcionistaThread.start();

        try {
            pacienteThread.join();
            recepcionistaThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}