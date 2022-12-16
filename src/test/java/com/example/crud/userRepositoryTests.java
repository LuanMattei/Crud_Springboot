package com.example.crud;

import com.example.crud.entities.User;
import com.example.crud.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class userRepositoryTests {
    @Autowired
    private UserRepository rep;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("luan.s2.mattei@gmail.com");
        user.setPassword("mattei1234");
        user.setFirstName("Luan");
        user.setLastName("Mattei");
        User savedUser = rep.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
        System.out.println("User ID > "+ savedUser.getId());
    }

    @Test
    public void testListAll() {
        Iterable<User> userList = rep.findAll();
        Assertions.assertThat(userList).hasSizeGreaterThan(0);

        for (User user : userList
        ) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Long userId = 1L;
        Optional<User> optionalUser = rep.findById(userId);
        User user = optionalUser.get();
        user.setPassword("change");
        rep.save(user);

        User updateUser = rep.findById(userId).get();
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("change");
        System.out.println("Updated");
    }

    @Test
    public void  testget(){
        Long userId = 1L;
        Optional<User> optionalUser = rep.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }
    @Test
    public void testDelete(){
        Long userId = 5L;
        Optional<User> optionalUser = rep.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        rep.deleteById(userId);
        optionalUser = rep.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
        System.out.println("user deleted");
    }

}
