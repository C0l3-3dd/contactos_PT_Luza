package com.example.contactcrud.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Modelo para el proyecto de conatctos
 * Mapeo de tablas de DB
 */
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String telefono;

    @Column(name="email", nullable=false, unique = true)
    private String email;

    @Column
    private String direccion;

    @Lob
    @Column
    private byte[] fotografia;

    public Contact() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public byte[] getFotografia() {
        return fotografia;
    }
    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }


    @Transient
    public StreamedContent getStreamedFoto() {
        try {
            if (fotografia == null || fotografia.length == 0) {
                InputStream is = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getResourceAsStream("/resources/images/default-user.png");

                return DefaultStreamedContent.builder()
                        .contentType("image/png")
                        .stream(() -> is)
                        .build();
            } else {
                return DefaultStreamedContent.builder()
                        .contentType("image/png") 
                        .stream(() -> new ByteArrayInputStream(fotografia))
                        .build();
            }
        } catch (Exception e) {
            InputStream is = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getResourceAsStream("/resources/images/default-user.png");

            return DefaultStreamedContent.builder()
                    .contentType("image/png")
                    .stream(() -> is)
                    .build();
        }
    }
}