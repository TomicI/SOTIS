package com.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    protected String username;
    @Column
    protected String password;
    @Column
    protected String firstName;
    @Column
    protected String lastName;
    @Column
    protected String email;
    @Column
    protected String role;
    @OneToMany
    protected Set<Test> kreiraniTestovi;
    @OneToMany
    protected Set<Test> reseniTestovi;

    public User()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public void addKreiranTest (Test test)
    {
        if (kreiraniTestovi == null)
            kreiraniTestovi = new HashSet<Test>();

        kreiraniTestovi.add(test);
    }

    public Set<Test> getKreiraniTestovi() { return kreiraniTestovi; }

    public void addResenTest (Test test)
    {
        if (reseniTestovi == null)
            reseniTestovi = new HashSet<Test>();

        reseniTestovi.add(test);
    }

    public Set<Test> getReseniTestovi() { return reseniTestovi; }
}
