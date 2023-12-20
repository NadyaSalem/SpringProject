package com.onlinetherapy.models.dto.viewModels.appointments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentViewInCart {
    private Long id;
    private String name;
    private BigDecimal price;
    private String img;
    private int count;
    private BigDecimal appointmentsSum;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getAppointmentsSum() {
        return appointmentsSum;
    }

    public void setAppointmentsSum(BigDecimal appointmentsSum) {
        this.appointmentsSum = appointmentsSum;
    }
}
