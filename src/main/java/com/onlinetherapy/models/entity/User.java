package com.onlinetherapy.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String address;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> userRoles = new ArrayList<> ();

    @ManyToMany
    private List<PurchasedAppointments> purchaseAppointments;

    @ManyToMany
    private List<ChoseAppointments> choseAppointments;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Request> requests;

    @OneToMany
    private List<Message> messages;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public User setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public List<PurchasedAppointments> getPurchaseAppointments() {
        return purchaseAppointments;
    }

    public User setPurchaseAppointments(List<PurchasedAppointments> purchaseAppointments) {
        this.purchaseAppointments = purchaseAppointments;
        return this;
    }

    public List<ChoseAppointments> getChoseAppointments() {
        return choseAppointments;
    }

    public User setChoseAppointments(List<ChoseAppointments> choseAppointments) {
        this.choseAppointments = choseAppointments;
        return this;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public User setRequests(List<Request> requests) {
        this.requests = requests;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public User setMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }

    public void removeAppointmentFromChoseList(Long appointmentId) {
        this.choseAppointments.removeIf(p-> p.getId().equals(appointmentId));
    }

    public ChoseAppointments findByImg(String img){
       return this.choseAppointments.stream().filter(a-> a.getImg().equals(img)).findFirst().orElseThrow (NoSuchElementException::new);
    }
    public PurchasedAppointments findPurchaseAppointmentByImg(String img){
        return this.purchaseAppointments.stream().filter(a-> a.getImg().equals(img)).findFirst().orElseThrow (NoSuchElementException::new);
    }

    public void addAppointmentToPurchaseList(PurchasedAppointments appointment){
        if(this.purchaseAppointments.contains (appointment)){
            PurchasedAppointments appointments = findPurchaseAppointmentByImg(appointment.getImg());
            appointments.setCount(appointment.getCount() + appointment.getCount());
        }else {
            this.purchaseAppointments.add (appointment);
        }
    }

    public Request findRequestById(Long requestId){
       return this.requests.stream().filter(r -> r.getId().equals(requestId)).findFirst().orElseThrow (NoSuchElementException::new);
    }

    public String getUserFullName(Long id){
       return this.firstName.concat(this.lastName);
    }

}
