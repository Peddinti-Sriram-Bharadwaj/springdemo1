package com.sriram9217.demo1.service;

import com.sriram9217.demo1.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

@Component
public class userDaoService {
    public static int usersCount = 5;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"Sriram", new Date()));
        users.add(new User(2,"Sarat", new Date()));
        users.add(new User(3,"Optimus", new Date()));
        users.add(new User(4,"Ironhide", new Date()));
        users.add(new User(5,"Predaking", new Date()));

    }
    public List<User> findAll() {
        return users;
    }

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findById(int id){
        for(User user : users){
            if(user.getId() == id)
                return user;
        }

        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
