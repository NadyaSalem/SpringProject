package com.onlinetherapy.repository;


import com.onlinetherapy.models.entity.Request;
import com.onlinetherapy.models.enums.RequestStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findRequestByRequestStatusEnum(RequestStatusEnum requestStatusEnum);

    List<Request> findRequestByDateRequestedBefore(LocalDate twoYearBack);
}
