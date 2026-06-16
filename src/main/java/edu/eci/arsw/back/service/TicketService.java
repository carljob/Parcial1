package banco_concurrente.services;

import banco_concurrente.model.Cuenta;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Mi servicio de banco. ConcurrentHashMap para las cuentas,
 * AtomicInteger para generar IDs sin sincronizar.
 *
 * La transferencia es atomica: si depositar en destino falla,
 * devuelvo el dinero al origen (rollback). Sin esto, el dinero
 * desaparece si algo sale mal entre el retiro y el deposito.
 */
@Service
public class TicketService {
    private final Map<String, Cuenta> cuentas = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public Cuenta crearTicket(String titular, double saldoInicial) {
        String id = "CTA-" + idCounter.getAndIncrement();
        Cuenta cuenta = new Cuenta(id, titular, saldoInicial);
        cuentas.put(id, cuenta);
        return cuenta;
    }

    public List<Cuenta> listarCuentas() { return new ArrayList<>(cuentas.values()); }
    public Cuenta obtenerCuenta(String id) { return cuentas.get(id); }

    public boolean depositar(String id, double monto) {
        Cuenta c = cuentas.get(id);
        return c != null && c.depositar(monto);
    }

    public boolean retirar(String id, double monto) {
        Cuenta c = cuentas.get(id);
        return c != null && c.retirar(monto);
    }
}