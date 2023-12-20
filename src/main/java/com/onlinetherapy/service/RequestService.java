package com.onlinetherapy.service;

import com.onlinetherapy.models.dto.viewModels.requests.CloseRequestToAdminPanelView;
import com.onlinetherapy.models.dto.viewModels.requests.OpenRequestToAdminPanelView;
import com.onlinetherapy.models.entity.Request;
import com.onlinetherapy.models.entity.User;
import com.onlinetherapy.models.enums.RequestStatusEnum;
import com.onlinetherapy.repository.RequestRepository;
import com.onlinetherapy.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<OpenRequestToAdminPanelView> GetAllOpenRequests() {
        return this.requestRepository.findRequestByRequestStatusEnum(RequestStatusEnum.OPEN)
                .stream ()
                .map (r -> modelMapper.map (r, OpenRequestToAdminPanelView.class)).toList ();
    }

    public List<CloseRequestToAdminPanelView> GetAllCloseRequests() {
        return this.requestRepository.findRequestByRequestStatusEnum(RequestStatusEnum.COMPLETED)
                .stream ()
                .map (r -> modelMapper.map (r, CloseRequestToAdminPanelView.class)).toList ();
    }

    public void closeRequest(Long requestId) {
        Request request = this.requestRepository.findById (requestId)
                .orElseThrow(() -> new Error("Request not found!"));
        request.setRequestStatusEnum(RequestStatusEnum.COMPLETED);
        this.requestRepository.save (request);
    }

    public Request findById(Long requestId) {
        return this.requestRepository.findById (requestId)
                .orElseThrow(() -> new Error("Request not found!"));
    }

    @Transactional
    public void deleteOldRequests() {
        LocalDate date = LocalDate.now ();
        LocalDate oldDate = date.minus (2, ChronoUnit.YEARS);
        List<Request> requestToDelete = this.requestRepository.findRequestByDateRequestedBefore(oldDate);

        requestToDelete.forEach (r -> {
            User user = this.userRepository.findByUsername (r.getClient ().getUsername ())
                    .orElseThrow(() -> new Error("User not found!"));
            user.getRequests().remove (r);

            this.requestRepository.deleteById (r.getId ());
        });
    }
}