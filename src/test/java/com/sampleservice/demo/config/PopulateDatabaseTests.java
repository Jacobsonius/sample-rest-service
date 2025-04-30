package com.sampleservice.demo.config;

import com.sampleservice.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PopulateDatabaseTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testStudentTableCreation() {
        String sql = "SELECT COUNT(*) FROM STUDENT";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        assertThat(count).isNotNull();
    }

    @Test
    public void testInsertStudentData() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john.doe@example.com");

        String sql = "INSERT INTO STUDENT (FIRST_NAME, LAST_NAME, CLASS, EMAIL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getKlass(), student.getEmail());

        String query = "SELECT COUNT(*) FROM STUDENT WHERE FIRST_NAME = ? AND LAST_NAME = ? AND CLASS = ? AND EMAIL = ?";
        Long count = jdbcTemplate.queryForObject(query, Long.class, student.getFirstName(), student.getLastName(), student.getKlass(), student.getEmail());
        assertThat(count).isEqualTo(1);
    }
}