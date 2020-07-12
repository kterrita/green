package com.greendata.bank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String shortName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private BusinessEntity businessType;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "client",
            cascade = CascadeType.ALL
    )
    private Set<Deposit> deposits = new HashSet<>();

    protected Client() {
    }

    public Client(String name, String shortName, String address, BusinessEntity businessType) {
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.businessType = businessType;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BusinessEntity getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessEntity businessType) {
        this.businessType = businessType;
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
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", address='" + address + '\'' +
                ", businessType=" + businessType +
                '}';
    }
}
