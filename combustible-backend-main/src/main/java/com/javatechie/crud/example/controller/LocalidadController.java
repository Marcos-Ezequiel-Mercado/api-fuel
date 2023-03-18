package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.dto.LocalidadConsultaDTO;
import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.utils.complete.CompleteCamposLocalidad;
import com.javatechie.crud.example.utils.complete.CompleteCamposUsuarios;
import com.javatechie.crud.example.entity.Localidad;
import com.javatechie.crud.example.service.Impl.LocalidadServiceImpl;
import com.javatechie.crud.example.utils.mapperDto.MapperLocalidadesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidad")
public class LocalidadController {
    private LocalidadServiceImpl localidadServiceImpl;
    private MapperLocalidadesDTO mapperLocalidadesDTO;
    private CompleteCamposLocalidad completeCampos;

    public LocalidadController(LocalidadServiceImpl localidadServiceImpl,
                               MapperLocalidadesDTO mapperLocalidadesDTO,
                               CompleteCamposLocalidad completeCampos) {
        this.localidadServiceImpl = localidadServiceImpl;
        this.mapperLocalidadesDTO = mapperLocalidadesDTO;
        this.completeCampos = completeCampos;
    }

    @GetMapping("/provincia/{id}")
    public List<String> getAllLocalidades(@PathVariable Integer id) throws Exception {
        try {
            return localidadServiceImpl.listLocalidades(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLocalidad(@RequestBody Localidad entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadServiceImpl.save(completeCampos.localidadCamposAlta(entity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<?> bajaLocalidad(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(localidadServiceImpl.bajaLocalidad(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public Localidad getLocalidad(@PathVariable Integer id) throws Exception {
        try {
            return localidadServiceImpl.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/nombre/{id}")
    public String getNombreLocalidad(@PathVariable Integer id) throws Exception {
        try {
            return localidadServiceImpl.buscarPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLocalidad(@PathVariable int id, @RequestBody Localidad entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadServiceImpl.update(id, completeCampos.localidadCamposMod(entity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @GetMapping("/active")
    public List<LocalidadDTO> listLocalidadesActivas() throws Exception {
        try {
            return mapperLocalidadesDTO.mapperDtoLocalidadActivo(localidadServiceImpl.ordenarLocalidades());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @GetMapping("/all")
    public List<LocalidadConsultaDTO> listLocalidadesActivasInactivas() throws Exception {
        try {
            return localidadServiceImpl.ordenarLocalidadesActivosInactivos();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}