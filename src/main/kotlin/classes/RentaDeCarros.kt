package classes

import java.util.*

class RentaDeCarros{

    val ADMIN_CORREO: String = "admin@example.com"
    val ADMIN_CONTRASENA: String = "contraseña123"

    var adminActivo:Boolean = false

    // Lista de carros disponibles para rentar
    private val carrosDisponibles = mutableListOf<Carro>()

    // Lista de clientes registrados
    private val clientesRegistrados = mutableListOf<Cliente>()

    // Lista de rentas activas
    val rentasActivas = mutableListOf<RentaActiva>()

    // Cliente que ha iniciado sesión en la aplicación
    var clienteActivo: Cliente? = null

    // Scanner para leer la entrada del usuario
    val scanner = Scanner(System.`in`)

    // Constructor de la clase
    init {
        // Inicializar la lista de carros disponibles
        carrosDisponibles.add(Carro("Toyota", "Corolla", 25.0, true))
        carrosDisponibles.add(Carro("Honda", "Civic", 30.0, true))
        carrosDisponibles.add(Carro("Ford", "Mustang", 50.0, true))
    }

    // Método para mostrar los carros disponibles
    fun mostrarCarrosDisponibles() {
        println("Carros disponibles:")
        for (carro in carrosDisponibles) {
            if (carro.disponible) {
                println("${carro.marca} ${carro.modelo} - \$${carro.precio}/día")
            }
        }
    }

    fun mostrarCarrosDisponibles(marca: String, modelo: String): Carro?{
        println("Carros disponibles:")
        for (carro in carrosDisponibles) {
            if (carro.disponible) {
                println("${carro.marca} ${carro.modelo} - \$${carro.precio}/día")
                return carro
            }
        }
        return null
    }

    // Método para agregar un cliente nuevo
    fun agregarCliente() {
        println("Ingresa los datos del nuevo cliente:")
        print("Nombre completo: ")
        val nombre = scanner.nextLine()
        print("Correo electrónico: ")
        val correo = scanner.nextLine()
        print("Contraseña: ")
        val contrasena = scanner.nextLine()
        clientesRegistrados.add(Cliente(nombre, correo, contrasena))
        println("Cliente registrado exitosamente.")
    }

    // Método para rentar un carro
    fun rentarCarro():Boolean {
        println("Ingresa los datos de la renta:")
        print("Marca del carro: ")
        val marca = scanner.nextLine()
        print("Modelo del carro: ")
        val modelo = scanner.nextLine()
        val carro = carrosDisponibles.find { it.marca == marca && it.modelo == modelo }
        if (carro == null) {
            println("No se encontró un carro con esas características.")
            return false
        }
        if (!carro.disponible) {
            println("El carro seleccionado no está disponible.")
            return false
        }
        print("Fecha de inicio (yyyy-mm-dd): ")
        val fechaInicio = scanner.nextLine()
        print("Fecha de fin (yyyy-mm-dd): ")
        val fechaFin = scanner.nextLine()
        rentasActivas.add(RentaActiva(carro, fechaInicio, fechaFin))
        carro.disponible = false
        println("Renta realizada exitosamente.")

        return true
    }

    // Método para mostrar las rentas activas
    fun mostrarRentasActivas() {
        println("Rentas activas:")
        for (renta in rentasActivas) {
            println("${renta.carro.marca} ${renta.carro.modelo} - ${renta.fechaInicio} a ${renta.fechaFin} por \$${renta.carro.precio}/día")
        }
    }

    // Método para cerrar sesión del cliente activo
    fun cerrarSesionCliente() {
        clienteActivo = null
        println("Sesión cerrada exitosamente.")
    }

    // Método para devolver Carro una renta activa
    fun devolverCarro() {
        println("Devolver carro")
        println("Ingrese la marca del carro:")
        val marca = readLine()
        println("Ingrese el modelo del carro:")
        val modelo = readLine()

        for (renta in rentasActivas) {
            if(renta.carro.marca == marca && renta.carro.modelo == modelo){
                rentasActivas.remove(renta)
                renta.carro.disponible = true
                println("Renta devuelta exitosamente.")
                break
            }else {
                println("No se pudo devolver el carro. Verifique la marca y modelo e inténtelo de nuevo")
            }
        }
    }

    // Método para iniciar sesión como cliente
    fun iniciarSesionCliente():Boolean {
        println("Ingresa tu correo electrónico y contraseña:")
        print("Correo electrónico: ")
        val correo = scanner.nextLine()
        print("Contraseña: ")
        val contrasena = scanner.nextLine()
        val cliente = clientesRegistrados.find { it.correo == correo && it.contrasena == contrasena }
        if (cliente == null) {
            println("Correo electrónico o contraseña incorrectos.")
            return false
        } else {
            clienteActivo = cliente
            println("Inicio de sesión exitoso.")
            return true
        }
    }

    // Método para iniciar sesión como administrador
    fun iniciarSesionAdmin() {
        println("Ingresa el correo electrónico y contraseña del administrador:")
        print("Correo electrónico: ")
        val correo = scanner.nextLine()
        print("Contraseña: ")
        val contrasena = scanner.nextLine()
        if (correo == ADMIN_CORREO && contrasena == ADMIN_CONTRASENA) {
            adminActivo = true
            println("Inicio de sesión exitoso.")
        } else {
            println("Correo electrónico o contraseña incorrectos.")
        }
    }

    // Método para cerrar sesión del administrador activo
    fun cerrarSesionAdmin() {
        adminActivo = false
        println("Sesión cerrada exitosamente.")
    }

    // Método para agregar un carro nuevo
    fun agregarCarro() {
        println("Ingresa los datos del nuevo carro:")
        print("Marca: ")
        val marca = scanner.nextLine()
        print("Modelo: ")
        val modelo = scanner.nextLine()
        print("Precio por día: ")
        val precio = scanner.nextDouble()
        scanner.nextLine() // Limpiar el buffer
        carrosDisponibles.add(Carro(marca, modelo, precio, disponible = true))
        println("Carro agregado exitosamente.")
    }

    // Método para remover un carro existente
    fun removerCarro() {
        println("Ingresa los datos del carro a remover:")
        print("Marca: ")
        val marca = scanner.nextLine()
        print("Modelo: ")
        val modelo = scanner.nextLine()
        val carro = carrosDisponibles.find { it.marca == marca && it.modelo == modelo }
        if (carro == null) {
            println("No se encontró un carro con esas características.")
            return
        }
        if (!carro.disponible) {
            println("No se puede remover un carro que está rentado actualmente.")
            return
        }
        carrosDisponibles.remove(carro)
        println("Carro removido exitosamente.")
    }

    // Método para mostrar los clientes registrados
    fun mostrarClientesRegistrados() {
        println("Clientes registrados:")
        for (cliente in clientesRegistrados) {
            println(cliente.nombre)
        }
    }
}