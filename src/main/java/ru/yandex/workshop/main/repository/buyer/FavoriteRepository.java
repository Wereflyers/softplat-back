package ru.yandex.workshop.main.repository.buyer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.workshop.main.model.buyer.Favorite;

import java.util.Set;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Set<Favorite> findAllByBuyerEmail(String buyerEmail);

    boolean existsByBuyerEmailAndProductId(String buyerEmail, Long productId);
}

