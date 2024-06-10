package com.repaso.repaso.persistence.repository;

import com.repaso.repaso.persistence.model.Follows;
import com.repaso.repaso.persistence.model.Subscripcion;
import com.repaso.repaso.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio para la entidad Subscripcion.
 */
public interface SubscriptionsRepositoryI extends JpaRepository<Subscripcion, Long> {
    /**
     * Encuentra una subscripción por el suscriptor y el subscriptor.
     *
     * @param Subscriber Usuario que realiza la subscripción.
     * @param subscripted Usuario que es subscripto.
     * @return Subscripcion encontrada.
     */
    Subscripcion findBySubscriberAndSubscripted(User Subscriber, User subscripted);

}
