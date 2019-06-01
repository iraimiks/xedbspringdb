package lv.backendherv1.backendherv1.dao;

import lv.backendherv1.backendherv1.model.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<DAOUser,Integer> {
    DAOUser findByUsername(String username);
}
