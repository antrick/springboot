package com.example.curso;


import com.example.curso.models.Producto;
import com.example.curso.models.UserData;
import com.example.curso.models.Usuario;
import com.example.curso.servicios.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Rutas {

    private OrderService orderService = new OrderService();
    private final Logger logger = LoggerFactory.getLogger(Rutas.class);
    @GetMapping("/hola")
    String primeraRuta(){
        return "Esta es mi primera ruta";
    }

    @GetMapping("/usuario/{id}")
    String leerUsuario(@PathVariable int id){
        return "Leer usuario id: "+id;
    }

    // una forma de enviar mas de un parametro
    @GetMapping("/usuario/{id}/detalle/{name}")
    String detalleUsuario(@PathVariable int id,@PathVariable String name){
        return "Leer usuario id: "+ id +" de nombre: "+ name;
    }

    // otra forma de enviar parametros (especificar cuales recibe o fallara si no se incluyen en la url)
    @GetMapping("/usuario2/{id}")
    String leerUsuario2(@PathVariable int id, @RequestParam String nombre, @RequestParam String apellido){
        return "Leer usuario id: "+id+" nombre: "+nombre + " apellido: "+apellido;
    }

    @PostMapping("/usuario")
    String guardarUsuario(@RequestBody Map<String, Object> usuario){
        usuario.keySet().forEach(key -> {
            logger.debug("Llave {} valor {}",key, usuario.get(key));
        });
        return "libro guardado";
    }

    @PostMapping("/usuario_model")
    String guardarUsuarioModel(@RequestBody Usuario usuario){
        logger.debug("Persona {} Apellido {} Pais {}", usuario.nombre, usuario.apellido, usuario.pais);
        return "libro guardado";
    }

    @GetMapping("/status")
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY, reason = "Fue removida a la v2 de la API")
    String ruta_status(){ return "Mi ruta con status"; }

    @GetMapping("/animales/{lugar}")
    public ResponseEntity<String> getAnimals(@PathVariable String lugar){
        if(lugar.equals("granja")){
            return ResponseEntity.status(HttpStatus.OK).body("Caballo, Borrego, Oveja, Gallina, Vaca");
        } else if (lugar.equals("selva")) {
            return ResponseEntity.status(HttpStatus.OK).body("Gorila, Jaguar, Puma");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existen lugares");
        }
    }

    @GetMapping("/calcular/{numero}")
    public int calcular(@PathVariable int numero){
        throw new NullPointerException("esta excepcion porque existe informacion no relevante para el cliente");
    }

    @GetMapping("/userData")
    public ResponseEntity<String> getUserData(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type","application/json")
                .body("{\"name\": \"andres\"}");
    }


    @GetMapping("/userData/v2")
    public Map<String, Map<String, Object>> getUserv2(){
        return Map.of("user",Map.of(
                "name","andy",
                "age",30,
                "country","Oaxaca"
        ));
    }

    @GetMapping("/userData/v3")
    public UserData getUserv3(){
        var user = new UserData("Andres",30, "Mexico", "Oaxaca");
        return user;
    }

    @PostMapping("/order")
    public String crearOrden(@RequestBody List<Producto> products){
        orderService.storeOrder(products);
        return "Productos Guardados!";
    }

}
