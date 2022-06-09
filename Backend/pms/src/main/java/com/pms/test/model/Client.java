package com.pms.test.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity 
public class Client {
	    
	    @Id
        @Column
        private Long id;
	    
	    @Column
        private String name;
        
        @Column(unique = true)
        private String cpf;
        
        @Column
        private Date birthDate;
        
        @Column
        private ArrayList<String> address;

        
        

		// getters and setters
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public Date getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}

		public ArrayList<String> getAddress() {
			return address;
		}

		public void setAddress(ArrayList<String> address) {
			this.address = address;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Client other = (Client) obj;
			return Objects.equals(id, other.id);
		}
        
        
		
		
        
        
        
}
