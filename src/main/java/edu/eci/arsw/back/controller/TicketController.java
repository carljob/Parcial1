package edu.eci.arsw.back.controller;

import edu.eci.arsw.back.model.Ticket;
import edu.eci.arsw.back.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class Tikectcontroller {

    private final Tikectcontroller servicio;
    public Tikectcontroller(Tikectcontroller servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<Ticket> crearTurno(@RequestBody Map<String, String> datos) {

        String tipoServicio = datos.get("servicio");

        if (tipoServicio == null || tipoServicio.isBlank()) {
            tipoServicio = "General";
        }

        Ticket ticket = servicio.generarTicket(tipoServicio);

        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/siguiente")
    public ResponseEntity<Ticket> siguienteTurno() {

        Ticket ticket = servicio.obtenerSiguiente();

        if (ticket == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/estado")
    public String estadoServidor() {
        return "Sistema de turnos activo";
    }
}