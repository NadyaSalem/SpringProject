package com.onlinetherapy.init;

import com.onlinetherapy.model.entity.Therapist;
import com.onlinetherapy.model.enums.TherapistType;
import com.onlinetherapy.repository.TherapistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TherapistSeeder implements CommandLineRunner {

    private final TherapistRepository therapistRepository;

    public TherapistSeeder(TherapistRepository therapistRepository) {
        this.therapistRepository = therapistRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (this.therapistRepository.count() == 0) {

            List<Therapist> therapists = new ArrayList<>();

            Arrays.stream(TherapistType.values())
                    .forEach(TherapistType -> {
                        Therapist therapist = new Therapist();
                        therapist.setTherapistType(TherapistType);
                        therapists.add(therapist);

                    });
            this.therapistRepository.saveAll(therapists);
        }
    }
}
