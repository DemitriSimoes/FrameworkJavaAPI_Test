import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TesteClienteTest {

    //private static final String enderecoLocal = "http://localhost:8080";
    private static final String caminhoCliente = "/cliente";
    private static final String apagaTodosClientes = "/apagaTodos";
    private static final String respostaVazia = "{}";
    Cliente novoCliente = new Cliente("Tio Patinhas", 200, 101);

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://localhost:8080";
    }


    @Test
    //@Order(1)
    @DisplayName("When get a list about all of clients, the list must be empty")
    public void quandoPegarTodosClientes_ListaRetornaVazio() {

        // final String respostaVazia = "{}";

        apagaTodosClientes();

        given()
                .contentType(ContentType.JSON)
        .when()
                .get()
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(new IsEqual<>(respostaVazia));

    }

    @Test
    //@Order(2)
    @DisplayName("When post a list to new client, then show de new list")
    public void quandoAdicionarCliente_RetornaClienteCriado(){

       postCliente();

       given().when().then()
               .statusCode(HttpStatus.SC_CREATED)
               .body("200.idade", equalTo(200))
               .body("Tio Patinhas.nome", equalTo("Tio Patinhas"))
               .body("101.id", equalTo(101));
    }

    @Test
    //@Order(3)
    @DisplayName("When put new information, then the information must be updated")
    public void quandoAtualizarCliente_RetornaClienteAtualizado(){

        postCliente();

        novoCliente.setIdade(80);

        String atualizada = "{\"101\":{\"nome\":\"Tio Patinhas\",\"idade\":80,\"id\":101,\"risco\":0}}";

        given()
                .contentType(ContentType.JSON)
                .body(novoCliente)
                .when()
                .put(caminhoCliente)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString(atualizada));
    }

    @Test
    //@Order(4)
    @DisplayName("When delete the client by id, then return removed client")
    public void quandoDeletaClientePorId_RetornaClienteRemovido(){

        postCliente();

        String idCliente = "/101";
        String respostaVazia = "CLIENTE REMOVIDO: { NOME: Tio Patinhas, IDADE: 200, ID: 101 }";


        given()
                .contentType(ContentType.JSON)
        .when()
                .delete(caminhoCliente+idCliente)
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString(respostaVazia));


    }

    public void postCliente() {

        String respostaNovoCliente = "{\"101\":{\"nome\":\"Tio Patinhas\",\"idade\":200,\"id\":101,\"risco\":0}}";

        given()
                .contentType(ContentType.JSON)
                .body(novoCliente)
                .when()
                .post(caminhoCliente)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("Tio Patinha.nome", equalTo("nome"))
                .body("200.idade", equalTo(200))
                .body(containsString(respostaNovoCliente));
    }

    //ValidatableResponse - return

    public void apagaTodosClientes(){

        given()
                .contentType(ContentType.JSON)
        .when()
                .delete(caminhoCliente+apagaTodosClientes)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body(new IsEqual<>(respostaVazia));
    }

    @Test
    @DisplayName("Quando colocar dois valores no método soma, então retornar o resultado da soma")
    public void testConta(){

        Calc calc = new Calc();
        int result = calc.soma(5, 3);
        assertEquals(8, result);

    }
}
