package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;
@Repository
public class UserDaoImpl implements UserDao{
    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<User> getAllUsers(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    @Override
    public void addUser(User user){
        sessionFactory.getCurrentSession().save(user);
    }
    @Override
    public User getUser(int id){
        return sessionFactory.getCurrentSession().get(User.class, id);
    }
    @Override
    public void updateUser(User user){
        sessionFactory.getCurrentSession().update(user);
    }
    @Override
    public void deleteUser(User user){
        sessionFactory.getCurrentSession().delete(user);
    }
}