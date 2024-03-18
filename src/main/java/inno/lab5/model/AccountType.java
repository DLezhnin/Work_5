package inno.lab5.model;

import jakarta.persistence.*;
import lombok.ToString;

public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    internalid;
    @OneToOne
    @JoinColumn(name = "accountType")
    @ToString.Exclude
    private String  value;
}
