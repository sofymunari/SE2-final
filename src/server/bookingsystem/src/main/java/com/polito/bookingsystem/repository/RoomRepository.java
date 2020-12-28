package com.polito.bookingsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polito.bookingsystem.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer>{
   Room findByName(String name);
   Room findByRoomId(Integer roomId);
}
