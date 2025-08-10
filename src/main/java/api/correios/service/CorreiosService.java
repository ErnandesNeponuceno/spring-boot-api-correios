package api.correios.service;

import api.correios.config.CorreiosConfigProperties;
import api.correios.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CorreiosService {
    private final CorreiosConfigProperties config;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    TokenService tokenService;

    private String token;
    private LocalDateTime expiracaoToken;

    public String rastrearObjeto(String codigoRastreio) {

        if (codigoRastreio == null || codigoRastreio.isBlank()) {
            throw new IllegalArgumentException("Código de rastreio não pode ser vazio");
        }

        String tokenValido = obterTokenValido();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenValido);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = config.getUrl() + "/srorastro/v1/objetos/" + codigoRastreio + "?resultado=U";

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }

    private String obterTokenValido() {
        if (token == null || expiracaoToken == null || expiracaoToken.isBefore(LocalDateTime.now().plusMinutes(30))) {
            TokenResponseDTO response = tokenService.solicitarNovoToken();
            token = response.getToken();
            expiracaoToken = response.getExpiraEm();
        }
        return token;
    }
}