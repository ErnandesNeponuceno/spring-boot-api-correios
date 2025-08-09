package api.correios.service;

import api.correios.config.CorreiosConfigProperties;
import api.correios.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final CorreiosConfigProperties config;
    private final RestTemplate restTemplate = new RestTemplate();


    public TokenResponseDTO solicitarNovoToken() {
        String auth = Base64.getEncoder().encodeToString(
                (config.getUsuario() + ":" + config.getCodigoAcesso()).getBytes()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + auth);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("numero", config.getContrato().getNumero());
        body.put("dr", config.getContrato().getDr());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        String url = config.getUrl() + "/token/v1/autentica/contrato";

        try {
            ResponseEntity<TokenResponseDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    TokenResponseDTO.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new RuntimeException("Falha na autenticação com os Correios (401)", e);
            } else if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                throw new RuntimeException("Limite de requisições excedido (429)", e);
            } else if (e.getStatusCode() == HttpStatus.PRECONDITION_FAILED) {
                throw new RuntimeException("Token 'bearer' não foi enviado na requisição (412)", e);
            } else {
                throw new RuntimeException("Erro ao solicitar token dos Correios", e);
            }
        }
    }
}
