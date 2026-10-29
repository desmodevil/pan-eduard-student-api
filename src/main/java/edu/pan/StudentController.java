package edu.pan;
import jakarta.validation.Valid; import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.*; import java.net.URI; import java.util.*;
@RestController public class StudentController {
 private final StudentRepository repo;
 public StudentController(StudentRepository r){repo=r;}
 public record Input(@NotBlank @Size(max=100) String name,@NotBlank @Size(max=50) String groupName,@NotBlank @Email @Size(max=200) String email){}
 @GetMapping("/") public Map<String,String> info(){return Map.of("student","Pan Eduard","group","IT2-2312","version","1.0-pan-eduard");}
 @GetMapping("/api/students") public List<Student> all(){return repo.findAll();}
 @GetMapping("/api/students/{id}") public Student one(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"));}
 private Student assign(Student s,Input i){s.name=i.name();s.groupName=i.groupName();s.email=i.email();return repo.save(s);}
 @PostMapping("/api/students") public ResponseEntity<Student> create(@Valid @RequestBody Input i){Student s=assign(new Student(),i);return ResponseEntity.created(URI.create("/api/students/"+s.id)).body(s);}
 @PutMapping("/api/students/{id}") public Student update(@PathVariable Long id,@Valid @RequestBody Input i){return assign(one(id),i);}
 @DeleteMapping("/api/students/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){repo.delete(one(id));}
}
