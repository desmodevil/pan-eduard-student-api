package edu.pan;
import jakarta.persistence.*;
@Entity public class Student {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String name;
 @Column(nullable=false) public String groupName;
 @Column(nullable=false) public String email;
}
