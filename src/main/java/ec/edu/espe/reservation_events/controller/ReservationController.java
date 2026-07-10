package ec.edu.espe.reservation_events.controller;

import ec.edu.espe.reservation_events.model.ReservationEvent;
import ec.edu.espe.reservation_events.util.ReservationFilters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @GetMapping("/stream")
    public Flux<ReservationEvent> streamReservations() {

        // 1. Reservas VÁLIDAS (Precio > 0 y tienen correos)
        ReservationEvent r1 = new ReservationEvent("1", "Ana", 150.0, List.of("ana@espe.edu.ec"));
        ReservationEvent r2 = new ReservationEvent("2", "Carlos", 230.50, List.of("carlos@espe.edu.ec"));
        ReservationEvent r3 = new ReservationEvent("3", "Saskia", 95.0, List.of("saskia@gmail.com"));

        // 2. Reservas INVÁLIDAS (Una con precio negativo, otra sin correos)
        ReservationEvent r4 = new ReservationEvent("4", "Juan", -10.0, List.of("juan@espe.edu.ec"));
        ReservationEvent r5 = new ReservationEvent("5", "Maria", 180.0, Collections.emptyList());

        // 3. Reserva por defecto (Por si el flujo se quedara vacío)
        ReservationEvent defaultReservation = new ReservationEvent("DEFAULT", "Sin reservas validas", 0.0, Collections.emptyList());

        // 4. El Corazón del Flujo Reactivo
        return Flux.just(r1, r2, r3, r4, r5)
                .filter(ReservationFilters.VALID_RESERVATION)     // Filtra y elimina las inválidas
                .doOnNext(ReservationFilters.PRINT_RESERVATION)   // Imprime en consola las que pasaron
                .defaultIfEmpty(defaultReservation);              // Si no pasó ninguna, muestra la de defecto
    }
}