package com.cg.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;

    @NotNull(message = "The transaction amount is required")
    @DecimalMin(value = "50", message = "Transaction Amount must be greater than or equal to 50")
    @DecimalMax(value = "10000000000", message = "Transaction Amount must be less than or equal to 10.000.000.000")
    private BigDecimal totalBill;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "created_at", updatable = false)
    private Date createAt;
}
