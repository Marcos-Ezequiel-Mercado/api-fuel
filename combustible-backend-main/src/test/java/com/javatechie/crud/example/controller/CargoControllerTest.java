package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.Mocks.MockColleccion;
import com.javatechie.crud.example.service.interfaz.CargoService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
//@ExtendWith(MockitoExtension.class)
class CargoControllerTest {
//    @Mock
    CargoService cargoService = mock(CargoService.class);
//    @InjectMocks
    CargoController cargoController;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeEach
    void setUp(TestInfo testInfo, TestReporter testReporter) {
        cargoController = new CargoController(this.cargoService);
        this.testInfo = testInfo;
        this.testReporter = testReporter;
    }

    @AfterEach
    void tearDown() {
        System.out.println("Termino el test!");
    }

    @Test
    @DisplayName("lista todos los cargos de la empresa")
    void getAllCargos() throws Exception {
        List<String> cargos;
        when(cargoService.listCargos()).thenReturn(MockColleccion.STRING_LIST);
        cargos = cargoController.getAllCargos();
        Assertions.assertAll(() -> assertEquals(cargos.get(0), "prueba"),
                () -> assertTrue(cargos.size() > 0));
    }

    @Test
    @DisplayName("exepcion al listar los cargos de la empresa")
    void getExceptionFindByCargos() throws Exception {

    }
}