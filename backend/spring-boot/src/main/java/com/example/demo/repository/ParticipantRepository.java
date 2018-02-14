package com.example.demo.repository;

import com.example.demo.entity.Participant;
import com.example.demo.entity.ParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by whilemouse on 17. 10. 13.
 */
public interface ParticipantRepository extends JpaRepository<Participant, ParticipantId>, JpaSpecificationExecutor {
}
