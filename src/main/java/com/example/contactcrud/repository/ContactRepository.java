package com.example.contactcrud.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.contactcrud.model.Contact;





@Repository
@Transactional
public class ContactRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Contact contact) {
        if (contact.getId() == null) {
            entityManager.persist(contact);
        } else {
            entityManager.merge(contact);
        }
    }

    public Contact findById(Long id) {
        return entityManager.find(Contact.class, id);
    }

    public void delete(Contact contact) {
        if (!entityManager.contains(contact)) {
            contact = entityManager.merge(contact);
        }
        entityManager.remove(contact);
    }

    public List<Contact> findAll() {
        return entityManager.createQuery("SELECT c FROM Contact c", Contact.class)
                .getResultList();
    }

    public List<Contact> search(String keyword) {
        return entityManager.createQuery("SELECT c FROM Contact c " +
                "WHERE c.nombre LIKE :kw OR c.email LIKE :kw", Contact.class)
                .setParameter("kw", "%" + keyword + "%")
                .getResultList();
    }
}
