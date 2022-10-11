package com.example.moneytransfer.domain;

import lombok.*;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long accountId;
    private String accountType;
    private String accountNumber;
    private BigDecimal balance;
    private Date createdAt;
    private StatusEnum statusEnum;
    private String currency;
    @OneToOne
    @JoinColumn(name = "user_user_id")
    private User user;

//    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL)
//    private List<Statement> statements;
}
