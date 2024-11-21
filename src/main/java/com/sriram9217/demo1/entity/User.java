package com.sriram9217.demo1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User
{
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=5, message="Name should have least 5 characters")
    private String name;
    @Past
    private Date dob;
    //default constructor
    protected User()
    {

    }

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(Integer id, String name, Date dob)
    {
        super();
        this.id = id;
        this.name = name;
        this.dob = dob;
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Date getDob()
    {
        return dob;
    }
    public void setDob(Date dob)
    {
        this.dob = dob;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString()
    {
//return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
        return String.format("User [id=%s, name=%s, dob=%s]", id, name, dob);
    }
}