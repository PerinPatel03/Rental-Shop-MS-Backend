package com.myproject.rentalshopms.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class DashboardSummaryDTO {
    private BigDecimal totalRevenue;    // (sum of totalAmount)
    private BigDecimal totalSecurityDepositHeld;    // (sum of keptSecurityDeposit)
    private BigDecimal totalDue;    // (remaining to pay totalAmount) -> (sum of totalAmount - ((totalCredit - totalDebit) - totalSecurityDepositHeld))
    private Long activeRentals;

    public DashboardSummaryDTO() {
    }

    public DashboardSummaryDTO(BigDecimal totalRevenue, BigDecimal totalSecurityDepositHeld, BigDecimal totalDue, Long activeRentals) {
        this.totalRevenue = totalRevenue;
        this.totalSecurityDepositHeld = totalSecurityDepositHeld;
        this.totalDue = totalDue;
        this.activeRentals = activeRentals;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getTotalSecurityDepositHeld() {
        return totalSecurityDepositHeld;
    }

    public void setTotalSecurityDepositHeld(BigDecimal totalSecurityDepositHeld) {
        this.totalSecurityDepositHeld = totalSecurityDepositHeld;
    }

    public BigDecimal getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(BigDecimal totalDue) {
        this.totalDue = totalDue;
    }

    public Long getActiveRentals() {
        return activeRentals;
    }

    public void setActiveRentals(Long activeRentals) {
        this.activeRentals = activeRentals;
    }
}
