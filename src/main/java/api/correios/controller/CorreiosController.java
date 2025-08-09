package api.correios.controller;

import api.correios.service.CorreiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/correios")
public class CorreiosController {

    @Autowired
    CorreiosService correiosService;

    @GetMapping("/rastreio/{codigo}")
    public ResponseEntity<String> rastrear(@PathVariable String codigo) {
        String resposta = correiosService.rastrearObjeto(codigo);
        return ResponseEntity.ok(resposta);
    }
}
