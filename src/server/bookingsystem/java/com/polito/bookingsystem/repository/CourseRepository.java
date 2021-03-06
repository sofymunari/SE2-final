package com.polito.bookingsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polito.bookingsystem.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{
   Course findByCode(String code);
   Course findByName(String name);
}
