import { useEffect, useState } from "react";

const API = "http://localhost:8080/api/tickets";

function App() {
    const [backend.service, setService] = useState("General");
    const [myTicket, setMyTicket] = useState(null);
    const [calledTicket, setCalledTicket] = useState(null);

    useEffect(() => {
        const evtSource = new EventSource(`${API}/stream`);
        evtSource.addEventListener("TICKET_CALLED", (e) => {
            setCalledTicket(JSON.parse(e.data));
        });
        return () => evtSource.close();
    }, []);

    const crearTurno = async () => {
        const res = await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ backend.service }),
        });
        setMyTicket(await res.json());
    };

    const llamarSiguiente = async () => {
        await fetch(`${API}/call-next`, { method: "POST" });
    };

    return (
        <div style={{ fontFamily: "sans-serif", padding: 20 }}>
            <h1>Sala de espera</h1>

            <h2>Paciente</h2>
            <select value={backend.service} onChange={(e) => setService(e.target.value)}>
                <option value="General">General</option>
                <option value="Urgencias">Urgencias</option>
            </select>
            <button onClick={crearTurno}>Tomar turno</button>
            {myTicket && <p>Tu turno: {myTicket.id} ({myTicket.status})</p>}

            <h2>Recepción</h2>
            <button onClick={llamarSiguiente}>Llamar siguiente</button>

            <h2>Turno llamado actualmente</h2>
            <p>{calledTicket ? `${calledTicket.id} - ${calledTicket.backend.service}` : "Ninguno"}</p>
        </div>
    );
}

export default App;