package inno.lab5.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Long    id;
    private Long    productid;
    private String  type;
    private Long    account;
    private String  currencyCode;
    private String  state;
    private String  accountNumber;
}
