package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.Mocks.MockColleccion;
import com.javatechie.crud.example.service.Impl.LocalidadServiceImpl;
import com.javatechie.crud.example.utils.complete.CompleteCamposLocalidad;
import com.javatechie.crud.example.utils.mapperDto.MapperLocalidadesDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LocalidadControllerTest {

    private final LocalidadServiceImpl localidadServiceImpl = mock(LocalidadServiceImpl.class);
    private final MapperLocalidadesDTO mapperLocalidadesDTO = mock(MapperLocalidadesDTO.class);
    private final CompleteCamposLocalidad completeCampos = mock(CompleteCamposLocalidad.class);
    private LocalidadController localidadController;
    private TestInfo testInfo;
    private TestReporter testReporter;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicia la instancia de la primer prueba unitaria");
    }

    @BeforeEach
    void setUp(TestInfo testInfo, TestReporter testReporter) {
        localidadController = new LocalidadController(this.localidadServiceImpl,
                this.mapperLocalidadesDTO, this.completeCampos);
        this.testInfo = testInfo;
        this.testReporter = testReporter;
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizo todas las pruebas unitarias");
    }

    @AfterEach
    void tearDown() {
        System.out.println("El test unitario finalizo!");
    }

    /**
     * PRACTICA DE TEST REPETIDOS
     *
     * @throws Exception
     */
    @RepeatedTest(value = 3, name = "{displayName} = Repeticion numero {currentRepetition} de un total de {totalRepetitions}")
    @DisplayName("listando todas las localidades")
    @Tag("Creacion")
    void getAllLocalidades(RepetitionInfo info) throws Exception {

        if (info.getCurrentRepetition() == 2) {
            System.out.println("estamos en el segundo test repetido");
            testReporter.publishEntry(
                    "\nSe utiliza "
                            + testInfo.getTestClass().orElse(null).getName() + "\n,"
                            + testInfo.getTestMethod().orElse(null).getName() + "\n,"
                            + testInfo.getDisplayName() + "\n,"
                            + testInfo.getTags().toString());
        }

        List<String> localidades;

        when(localidadServiceImpl.listLocalidades(1)).thenReturn(MockColleccion.STRING_LIST);
        localidades = localidadController.getAllLocalidades(1);

        assertAll(() -> assertEquals(localidades.get(0), "prueba",
                        () -> "El elemento de la ista en la pocicion 0 no es igual a 'prueba'!"),
                () -> assertTrue(localidades.size() > 0,
                        () -> "La assertions dio 'FALSE' porque la lista es 'EMPTY'"),
                () -> assertNotNull(localidades,
                        () -> "La lista esta NULL"),
                () -> assertFalse(localidades.isEmpty(),
                        () -> "La lista no esta 'EMPTY' porque tiene valores!"));
    }

    /**
     * Practica de parametros en test
     *
     * @param valor
     */
    @ParameterizedTest(name = "Test con parametros numero {index} en valor {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void getAllLocalidadesForValueSource(Integer valor) throws Exception {

        List<String> localidades;

        when(localidadServiceImpl.listLocalidades(anyInt())).thenReturn(MockColleccion.STRING_LIST);

        if (valor.equals(2)) System.out.println("estamos en el segundo test con el valor " + valor);
        localidades = localidadController.getAllLocalidades(valor);

        assertAll(() -> assertEquals(localidades.get(0), "prueba",
                        () -> "El elemento de la ista en la pocicion 0 no es igual a 'prueba'!"),
                () -> assertTrue(localidades.size() > 0,
                        () -> "La assertions dio 'FALSE' porque la lista es 'EMPTY'"),
                () -> assertNotNull(localidades,
                        () -> "La lista esta NULL"),
                () -> assertFalse(localidades.isEmpty(),
                        () -> "La lista no esta 'EMPTY' porque tiene valores!"));
    }

    /**
     * practica de test con valores
     *
     * @param valor
     * @throws Exception
     */
    @ParameterizedTest(name = "Test con parametros numero {index} en valor {0}")
    @CsvSource(value = {"1,1", "2,2", "3,3", "4,4", "5,5"})
    void getAllLocalidadesForCvsSource(String index, String valor) throws Exception {

        List<String> localidades;

        when(localidadServiceImpl.listLocalidades(anyInt())).thenReturn(MockColleccion.STRING_LIST);

        if (index.equals("2"))
            System.out.println("estamos en el segundo test con el valor " + valor + " indice " + index);
        localidades = localidadController.getAllLocalidades(Integer.valueOf(valor));

        assertAll(() -> assertEquals(localidades.get(0), "prueba",
                        () -> "El elemento de la ista en la pocicion 0 no es igual a 'prueba'!"),
                () -> assertTrue(localidades.size() > 0,
                        () -> "La assertions dio 'FALSE' porque la lista es 'EMPTY'"),
                () -> assertNotNull(localidades,
                        () -> "La lista esta NULL"),
                () -> assertFalse(localidades.isEmpty(),
                        () -> "La lista no esta 'EMPTY' porque tiene valores!"));
    }

    /**
     * practica de test con valores
     *
     * @param valor
     * @throws Exception
     */
    @ParameterizedTest(name = "Test con parametros numero {index} en valor {0}")
    @CsvFileSource(resources = "/valores.csv")
    void getAllLocalidadesForFileSource(String valor) throws Exception {

        List<String> localidades;

        when(localidadServiceImpl.listLocalidades(anyInt())).thenReturn(MockColleccion.STRING_LIST);

        if (valor.equals("2")) System.out.println("estamos en el segundo test con el valor " + valor);
        localidades = localidadController.getAllLocalidades(Integer.valueOf(valor));

        assertAll(() -> assertEquals(localidades.get(0), "prueba",
                        () -> "El elemento de la ista en la pocicion 0 no es igual a 'prueba'!"),
                () -> assertTrue(localidades.size() > 0,
                        () -> "La assertions dio 'FALSE' porque la lista es 'EMPTY'"),
                () -> assertNotNull(localidades,
                        () -> "La lista esta NULL"),
                () -> assertFalse(localidades.isEmpty(),
                        () -> "La lista no esta 'EMPTY' porque tiene valores!"));
    }

    /**
     * probando test con parametros
     *
     * @param valor
     * @throws Exception
     */
    @ParameterizedTest(name = "Test con parametros numero {index} en valor {0}")
    @MethodSource("createList")
    void getAllLocalidadesForMethodSource(String valor) throws Exception {

        List<String> localidades;

        when(localidadServiceImpl.listLocalidades(anyInt())).thenReturn(MockColleccion.STRING_LIST);

        if (valor.equals("2")) System.out.println("estamos en el segundo test con el valor " + valor);
        localidades = localidadController.getAllLocalidades(Integer.valueOf(valor));

        assertAll(() -> assertEquals(localidades.get(0), "prueba",
                        () -> "El elemento de la ista en la pocicion 0 no es igual a 'prueba'!"),
                () -> assertTrue(localidades.size() > 0,
                        () -> "La assertions dio 'FALSE' porque la lista es 'EMPTY'"),
                () -> assertNotNull(localidades,
                        () -> "La lista esta NULL"),
                () -> assertFalse(localidades.isEmpty(),
                        () -> "La lista no esta 'EMPTY' porque tiene valores!"));
    }

    @Test
    @DisplayName("creando una localidad")
    void createLocalidad() {

    }

    @Test
    void bajaLocalidad() {
    }

    @Test
    void getLocalidad() {
    }

    @Test
    void getNombreLocalidad() {
    }

    @Test
    void updateLocalidad() {
    }

    @Test
    void listLocalidadesActivas() {
    }

    @Test
    void listLocalidadesActivasInactivas() {
    }

    @Nested
    @Tag("Exception")
    @DisplayName("Clase interna para test de excepciones!")
    class LocalidadControllerExceptionTest {
        private LocalidadServiceImpl localidadServiceImpl = mock(LocalidadServiceImpl.class);
        private MapperLocalidadesDTO mapperLocalidadesDTO = mock(MapperLocalidadesDTO.class);
        private CompleteCamposLocalidad completeCampos = mock(CompleteCamposLocalidad.class);
        private LocalidadController localidadController;

        @BeforeEach
        void setUp() {
            localidadController = new LocalidadController(this.localidadServiceImpl, this.mapperLocalidadesDTO, this.completeCampos);
        }

        //TODO: AVERIGUAR SI ES NECESARIO EL BEFOREEACH Y MOCKEAR DE NUEVO LAS CLASES EN UNA CLASE HIJA ANIDADA COMO ESTA.
        @Test
        @Disabled
        @DisplayName("listando todas las localidades")
        void getAllLocalidadesException() throws Exception {
            List<String> localidades;

            when(localidadServiceImpl.listLocalidades(1)).thenReturn(MockColleccion.EMPTY_STRING_LIST);
            localidades = localidadController.getAllLocalidades(1);

            Exception exception = assertThrows(Exception.class, () -> {
                localidadController.getAllLocalidades(1);
            }, () -> "No se pudo generar el mensaje de excepcion esperado!");

            assertAll(() -> assertFalse(localidades.size() > 0, () -> "La lista no esta vacia"),
                    () -> assertNull(localidades, () -> "la lista no es 'NULL'"),
                    () -> assertEquals("RunTime", exception.getMessage(), () -> "La excepcion obtenida no coincide con la que se esperaba!"));
        }

        @Test
        @DisplayName("creando una localidad")
        void createLocalidadException() {

        }

        @Test
        void bajaLocalidadException() {
        }

        @Test
        void getLocalidadException() {
        }

        @Test
        void getNombreLocalidadException() {
        }

        @Test
        void updateLocalidadException() {
        }

        @Test
        void listLocalidadesActivasException() {
        }

        @Test
        void listLocalidadesActivasInactivasException() {
        }
    }

    @Nested
    @DisplayName("Ejemplo de test con tiempo de ejecucion")
    @Tag("TimeOut")
    class TimeOutTestExample {

        @Test
        @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
        void getAllLocalidadesTimeOut() throws InterruptedException {
            TimeUnit.MILLISECONDS.sleep(1000);
        }

        @Test
        @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
        void getAllLocalidadesTimeOut2() throws InterruptedException {
            assertTimeout(Duration.ofSeconds(1000), () -> {
                TimeUnit.MILLISECONDS.sleep(1000);
            });
        }
    }


}