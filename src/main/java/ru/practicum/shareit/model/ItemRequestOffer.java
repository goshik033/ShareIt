package ru.practicum.shareit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "item_request_offers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"request_id", "item_id"}))
@Getter
@Setter
public class ItemRequestOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_id")
    private ItemRequest request;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "added_by_id")
    private User addedBy;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    @Column(columnDefinition = "text")
    private String comment;
    @Enumerated(EnumType.STRING)
    @Column(name = "offer_status", nullable = false, length = 32)
    private OfferStatus offerStatus = OfferStatus.PROPOSED;
}