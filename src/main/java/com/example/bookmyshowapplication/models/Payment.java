package com.example.bookmyshowapplication.models;

import com.example.bookmyshowapplication.models.enums.PaymentMode;
import com.example.bookmyshowapplication.models.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private String referenceId; //get it from third party payment gateway
    private double amount;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

}
/*
Here, we won't do cardinality between Payment and Payment Mode,
                                      Payment and Payment Status because Payment Mode, Payment Status is enum not a class

 */