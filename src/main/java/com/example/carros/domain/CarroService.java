package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    private CarroRepository rep;
    public List<CarroDTO> getCarros(){
        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }
    public Optional<CarroDTO> getCarroById(Long id) {

        return rep.findById(id).map(CarroDTO::create);
    }
    public List<CarroDTO> getCarrosByTipo(String tipo) {

        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public Carro save(Carro carro) {
        return rep.save(carro);
    }

//    public Carro insert(Carro carro) {
//        Assert.isNull(carro.getId(), "nao foi possivel inserir o registro");
//
//        return rep.save(carro);
//    }
//
//    public Carro update(Carro carro, Long id) {
//        Assert.notNull(id, "nao foi possivel atualizar o registro");
//
//        //busca o carro no banco de dadoss
//        Optional<CarroDTO> optional = getCarroById(id);
//        if(optional.isPresent()){
//            Carro db = optional.get();
//            db.setNome(carro.getNome());
//            db.setTipo(carro.getTipo());
//            System.out.println("Carro id " + db.getId());
//
//            //atualiza o carro
//            rep.save(db);
//
//            return db;
//        } else{
//            throw new RuntimeException("Nao foi possivel atualizar o registro");
//        }
//    }

    public void delete(Long id) {
        if(getCarroById(id).isPresent()){
            rep.deleteById(id);
        }
    }


//    public List<Carro> getCarros(){
//        List<Carro> carros =  new ArrayList<>();
//
//        carros.add(new Carro(1L,"Fusca"));
//        carros.add(new Carro(2L, "Chevete"));
//        carros.add(new Carro(3L, "Corola"));
//
//        return carros;
//    }
}
