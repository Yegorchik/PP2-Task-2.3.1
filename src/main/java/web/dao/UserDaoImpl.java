package web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    @Transactional
    @Override
    public void addUser(User user){
        entityManager.persist(user);
        entityManager.flush();
    }
    @Transactional
    @Override
    public User getUser(int id){
        return entityManager.find(User.class, id);
    }
    @Transactional
    @Override
    public void updateUser(User user){
        entityManager.merge(user);
        entityManager.flush();
    }
    @Transactional
    @Override
    public void deleteUser(User user){
        User attachedUser = entityManager.merge(user);
        entityManager.remove(attachedUser);
        entityManager.flush();
    }
}