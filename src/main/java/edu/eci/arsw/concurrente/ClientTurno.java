package edu.eci.arsw.concurrente;

/**
 * Mi hilo de cliente: cada instancia hace una consulta de turno
 * en su propio hilo. Guardo el resultado en ultimaConsulta para
 * leerlo despues del join() desde el main.
 */

public class ClientTurno extends Thread {
    private Turnos turnos;
    private String tipoCliente; // Paciente
    private String ultimaConsulta;

    public ClientTurno(Turnos turnos, String tipoCliente) {
        this.turnos = turnos;
        this.tipoCliente = tipoCliente;
    }

    @Override
    public void run() {
        if (tipoCliente.equals("Paciente")) {
            turnos.tomarTurno();
            ultimaConsulta = "Turno tomado por paciente";
        } else if (tipoCliente.equals("Recepcionista")) {
            turnos.llamarSiguienteTurno();

            ultimaConsulta = "Siguiente turno llamado por recepcionista";
        }
    }

    public String getUltimaConsulta() {
        return ultimaConsulta;
    }
}