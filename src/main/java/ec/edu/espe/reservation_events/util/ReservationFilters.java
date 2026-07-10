package ec.edu.espe.reservation_events.util;

import ec.edu.espe.reservation_events.model.ReservationEvent;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ReservationFilters {

    // El constructor es privado para que nadie intente hacer un "new ReservationFilters()"
    private ReservationFilters() {
    }

    // PREDICATE: Es una regla que devuelve true o false.
    // Aquí valida que el precio sea mayor a 0 Y que la lista de correos no esté vacía.
    public static final Predicate<ReservationEvent> VALID_RESERVATION =
            reservation ->
                    reservation.getPrice() > 0 &&
                            !reservation.getEmails().isEmpty();

    // CONSUMER: Es una acción que recibe algo y lo procesa (no devuelve nada).
    // Aquí simplemente imprime en la consola de IntelliJ el evento procesado.
    public static final Consumer<ReservationEvent> PRINT_RESERVATION =
            reservation ->
                    System.out.println("Evento procesado: " + reservation);
}