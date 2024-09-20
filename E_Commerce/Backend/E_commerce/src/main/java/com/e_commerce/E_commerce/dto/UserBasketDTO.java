package com.e_commerce.E_commerce.dto;

public class UserBasketDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Long basketId;
    private Double totalPrice;
    private String basketTitle;

    // Getter ve Setter'lar
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBasketTitle() {
        return basketTitle;
    }

    public void setBasketTitle(String basketTitle) {
        this.basketTitle = basketTitle;
    }
}
