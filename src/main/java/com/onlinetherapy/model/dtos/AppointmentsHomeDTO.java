package com.onlinetherapy.model.dtos;

import java.util.List;

public class AppointmentsHomeDTO {

    private List<CreateAppointmentDTO>  individualAppointments;
    private List<CreateAppointmentDTO> familyAppointments;
    private List<CreateAppointmentDTO> organizationalAppointments;
    private long totalCountAppointments;

    public AppointmentsHomeDTO(List<CreateAppointmentDTO> individualAppointments, List<CreateAppointmentDTO> familyAppointments,
                               List<CreateAppointmentDTO> organizationalAppointments, long totalCountAppointments) {
        this.individualAppointments = individualAppointments;
        this.familyAppointments = familyAppointments;
        this.organizationalAppointments = organizationalAppointments;
        this.totalCountAppointments = totalCountAppointments;
    }

    public List<CreateAppointmentDTO> getIndividualAppointments() {
        return individualAppointments;
    }

    public void setIndividualAppointments(List<CreateAppointmentDTO> individualAppointments) {
        this.individualAppointments = individualAppointments;
    }

    public List<CreateAppointmentDTO> getFamilyAppointments() {
        return familyAppointments;
    }

    public void setFamilyAppointments(List<CreateAppointmentDTO> familyAppointments) {
        this.familyAppointments = familyAppointments;
    }

    public List<CreateAppointmentDTO> getOrganizationalAppointments() {
        return organizationalAppointments;
    }

    public void setOrganizationalAppointments(List<CreateAppointmentDTO> organizationalAppointments) {
        this.organizationalAppointments = organizationalAppointments;
    }

    public long getTotalCountAppointments() {
        return totalCountAppointments;
    }

    public void setTotalCountAppointments(long totalCountAppointments) {
        this.totalCountAppointments = totalCountAppointments;
    }


//!!!!!!!!!!! MUST DO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//    Below the language sections is located a button
//    "Remove all words {total count of words}" with an info that shows the total count of all words.
//
//Below each word is located an info bar that shows the username of the user who added the word and the input date
// (in the format yyyy-MM-dd).
}
