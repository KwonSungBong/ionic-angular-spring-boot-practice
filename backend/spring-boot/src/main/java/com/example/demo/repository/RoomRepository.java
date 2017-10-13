package com.example.demo.repository;

import com.example.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by whilemouse on 17. 10. 13.
 */
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor {
}
