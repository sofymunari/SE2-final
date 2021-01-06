package com.polito.bookingsystem.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polito.bookingsystem.entity.Lecture;
import com.polito.bookingsystem.entity.Professor;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Integer>{
  Lecture findByLectureId(Integer lectureId);
  List<Lecture> findByProfessor(Professor professor);
  Long deleteByLectureId(Integer lectureId);
}
