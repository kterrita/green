package com.greendata.bank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"bic", "name"})
)
public class Bank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long bic;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "bank",
            cascade = CascadeType.ALL
    )
    private Set<Deposit> deposits = new HashSet<>();

    protected Bank() {
    }

    public Bank(String name, Long bic) {
        this.name = name;
        this.bic = bic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBic() {
        return bic;
    }

    public void setBic(Long bic) {
        this.bic = bic;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id.equals(bank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bic=" + bic +
                '}';
    }
}
