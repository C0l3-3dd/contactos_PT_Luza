package com.example.contactcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.contactcrud.model.Contact;
import com.example.contactcrud.repository.ContactRepository;
/**
* operaciones de la capa de negocios
 */
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public List<Contact> searchContacts(String keyword) {
        return contactRepository.search(keyword);
    }
}
