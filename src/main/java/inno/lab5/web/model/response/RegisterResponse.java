package inno.lab5.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private Long    id;
    private Long    productid;
    private String  type;
    private Long    account;
    private String  currencyCode;
    private String  state;
    private String  accountNumber;
}
