package com.oyo1.HotelManagement2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "aadhaar_details")
public class Aadhaar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String url;

    String dob;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    Customer customer;
}
