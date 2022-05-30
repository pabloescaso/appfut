

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepo repo;

    @Autowired
    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    public Student findStudent(Integer studentId) {
        return repo.findById(studentId).orElse(null);
    }

    public Student addStudent(Student student) {
        return repo.save(student);
    }

    public Student updateStudent(Student student) {
        return repo.save(student);
    }
}
