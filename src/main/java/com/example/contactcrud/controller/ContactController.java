package com.example.contactcrud.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.contactcrud.model.Contact;
import com.example.contactcrud.service.ContactService;

/**
 * Controlador para el manejo de las acciones CRUD
 */
@Component("contactController")
@Scope("session")
public class ContactController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ContactService contactService;

    private List<Contact> contactList;
    private Contact contact;
    private String searchKeyword;
    private boolean editMode;

    private UploadedFile uploadedFile;

    @PostConstruct
    public void init() {
        contact = new Contact();
        editMode = false;
        loadContacts();
    }

    public void loadContacts() {
        contactList = contactService.getAllContacts();
    }

    public void searchContacts() {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            contactList = contactService.searchContacts(searchKeyword);
        } else {
            loadContacts();
        }
    }


    public void handleFileUpload(FileUploadEvent event) {
        this.uploadedFile = event.getFile();
        if (uploadedFile != null) {
            contact.setFotografia(uploadedFile.getContent());
        }
    }


    public void edit(Long id) {
        contact = contactService.getContactById(id);
        editMode = true;
    }

    public void save() {
        if (uploadedFile != null) {
            contact.setFotografia(uploadedFile.getContent());
        }
        contactService.saveContact(contact);
        contact = new Contact();
        editMode = false;
        loadContacts();
    }

    public void update() {
        contactService.saveContact(contact);
        contact = new Contact();
        editMode = false;
        loadContacts();
    }

    public void delete(Contact c) {
        contactService.deleteContact(c);
        contact = new Contact();
        editMode = false;
        loadContacts();
    }

    public List<Contact> getContactList() {
        
        return contactList;
    }
    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public boolean isEditMode() {
        return editMode;
    }
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
