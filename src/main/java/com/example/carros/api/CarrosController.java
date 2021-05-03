package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService service;

    @GetMapping()
    public ResponseEntity<List<CarroDTO>> get(){
        return ResponseEntity.ok(service.getCarros());
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id){
        Optional<CarroDTO> carro = service.getCarroById(id);
        return carro
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity get(@PathVariable("tipo") String tipo){
        List<CarroDTO> carros = service.getCarrosByTipo(tipo);

        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }
    @PostMapping
    public String post(@RequestBody Carro carro){
        Carro c = service.save(carro);

        return "Carro salvo com sucesso: " + carro.getId();
    }
//    @PutMapping("/{id}")
//    public String put(@PathVariable("id") Long id, @RequestBody Carro carro){
//        Carro c = service.save(carro, id);
//
//        return "Carro atualizado com sucesso: " + c.getId();
//    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        service.delete(id);

        return "Carro excluido com sucesso!";
    }
}
