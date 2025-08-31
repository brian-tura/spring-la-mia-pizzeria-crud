package org.excercise.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Il nome non può essere ne null, ne vuoto")
    private String nome;

    @Lob
    @NotBlank(message = "La descrizione non può essere ne null, ne vuota")
    private String descrizione;

    @NotBlank(message = "L'Url dell'immagine non può essere ne null, ne vuoto")
    private String fotoUrl;

    @NotNull(message = "Il prezzo non può essere null")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0")
    private BigDecimal prezzo;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offerta> offerte = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public List<Offerta> getOfferte() {
        return offerte;
    }

    public void setOfferte(List<Offerta> offerte) {
        this.offerte = offerte;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s prezzo: %.2f€", this.nome, this.descrizione, this.fotoUrl, this.prezzo);
    }
}
