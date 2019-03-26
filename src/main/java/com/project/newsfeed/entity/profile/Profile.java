package com.project.newsfeed.entity.profile;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name= "profile")
public class Profile {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Id
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(max = 100)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(name = "email", unique = true)
    private String email;

    @Column(name= "profile_picture")
    @Lob
    private Byte[] profilePicture;

    @Column(name = "bio")
    private String bio;

    // from child to parent
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    public Profile(){}

    public Profile(@NotNull @Size(max = 100) String firstName,
                   @NotNull @Size(max = 100) String lastName,
                   @NotNull @Email @Size(max = 100) String email,
                   Byte[] profilePicture,
                   String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePicture = profilePicture;
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) &&
                Objects.equals(firstName, profile.firstName) &&
                Objects.equals(lastName, profile.lastName) &&
                Objects.equals(email, profile.email) &&
                Arrays.equals(profilePicture, profile.profilePicture) &&
                Objects.equals(bio, profile.bio);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, lastName, email, bio);
        result = 31 * result + Arrays.hashCode(profilePicture);
        return result;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", profilePicture=" + Arrays.toString(profilePicture) +
                ", bio='" + bio + '\'' +
                '}';
    }
}
