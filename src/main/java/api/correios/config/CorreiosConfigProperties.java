package api.correios.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "correios")
public class CorreiosConfigProperties {
    private String url;
    private String usuario;
    private String codigoAcesso;
    private String cartaoPostagem;
    private Contrato contrato;

    @Getter
    @Setter
    public static class Contrato {
        private String numero;
        private Integer dr;
    }
}