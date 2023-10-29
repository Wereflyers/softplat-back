package ru.yandex.workshop.main.model.buyer;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @DateTimeFormat
    @Column(name = "production_time")
    LocalDateTime productionTime;
    @OneToOne
    @JoinColumn(name = "buyer_id")
    Buyer buyer;
    @OneToMany
    @JoinColumn(name = "order_id")
    List<ProductOrder> productsOrdered = new ArrayList<>();
    //стоимость всего заказа
    @Column(name = "amount")
    Float orderAmount;
}
