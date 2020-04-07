package com.techieonthenet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Order_Comment")
@SequenceGenerator(name = "order_comment_generator",
        sequenceName = "order_comment_seq", allocationSize = 1 ,
        initialValue = 500)
public class OrderComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "order_comment_generator")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
