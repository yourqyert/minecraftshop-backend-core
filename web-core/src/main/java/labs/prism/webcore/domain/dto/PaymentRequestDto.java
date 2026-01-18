package labs.prism.webcore.domain.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRequestDto {
    private String nickname;
    private Long productId;
}