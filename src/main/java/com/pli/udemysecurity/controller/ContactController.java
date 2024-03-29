package com.pli.udemysecurity.controller;

import com.pli.udemysecurity.model.Contact;
import com.pli.udemysecurity.repository.ContactRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

  private final ContactRepository contactRepository;

  @GetMapping("/contact")
  //  @PreFilter("filterObject.contactName != 'Test'")
  @PostFilter("filterObject.contactName != 'Test'")
  public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
    Contact contact = contacts.get(0);
    contact.setContactId(getServiceReqNumber());
    contact.setCreateDt(new Date(System.currentTimeMillis()));
    contactRepository.save(contact);
    List<Contact> returnContact = new ArrayList<>();
    returnContact.add(contact);
    return returnContact;
  }

  public String getServiceReqNumber() {
    Random random = new Random();
    int ranNum = random.nextInt(999999999 - 9999) + 9999;
    return "SR" + ranNum;
  }
}
