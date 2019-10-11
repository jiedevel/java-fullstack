package com.example.fullstack41.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@SuppressWarnings("deprecation")
@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsersDTO implements Serializable {
        @Id
        @GeneratedValue
        @Column(name = "USER_ID")
        private Long id;
        
        @NotEmpty(message ="error.name.empty")
        @Length(max = 50,message="error.name.length")
        @Column(name = "NAME")
        private String name;
        
        @NotEmpty(message="error.address.empty")
        @Length(max = 150, message="error.address.length")
        @Column(name = "ADDRESS")
        private String address;
        
        @Email(message="error.email.email")
        @NotEmpty(message="error.email.empty")
        @Length(max = 50,message="error.email.length")
        @Column(name = "EMAIL")
        private String email;
        
        
        public String getErrorMessage() {
            return null;
        }
	
        
}
     
