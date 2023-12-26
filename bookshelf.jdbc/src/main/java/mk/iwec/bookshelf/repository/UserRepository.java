package mk.iwec.bookshelf.repository;

import mk.iwec.bookshelf.domain.User;

import java.util.List;

public interface UserRepository {
    User findByUsername(String username);
    List<User> findAll();
    int insert(User user);
    int deleteByUsername(String username);
    int update(User user);
}
