package ec.edu.espe.reservation_events.model;

import java.util.List;

public final class ReservationEvent {

    private final String id;
    private final String passengerName;
    private final Double price;
    private final List<String> emails;

    // Constructor único con Copia Defensiva
    public ReservationEvent(String id,
                            String passengerName,
                            Double price,
                            List<String> emails) {
        this.id = id;
        this.passengerName = passengerName;
        this.price = price;
        // Al usar List.copyOf, si alguien modifica la lista original afuera, este objeto no cambia
        this.emails = List.copyOf(emails);
    }

    // Getters seguros (sin Setters)
    public String getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Double getPrice() {
        return price;
    }

    public List<String> getEmails() {
        // Devolvemos otra copia para que nadie pueda modificar la lista interna desde fuera
        return List.copyOf(emails);
    }
}