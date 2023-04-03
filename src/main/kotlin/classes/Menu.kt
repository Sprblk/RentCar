package classes

class Menu {
    private val rentaCarros = RentaDeCarros()
    fun iniciar() {
        println("Bienvenido a la aplicación de renta de carros")
        while (true) {
            println("Seleccione una opción:")
            println("1. Iniciar sesión como cliente")
            println("2. Iniciar sesión como administrador")
            println("3. Registrar nuevo cliente")
            println("4. Salir")

            val opcion = readLine()?.toIntOrNull()

            when (opcion) {
                1 -> iniciarSesionCliente()
                2 -> iniciarSesionAdmin()
                3 -> registrarNuevoCliente()
                4 -> break
                else -> println("Opción inválida")
            }
        }
    }

    private fun iniciarSesionCliente() {
        if (rentaCarros.iniciarSesionCliente()) {
            menuCliente()
        } else {
            println("Nombre de usuario o contraseña incorrectos")
        }
    }

    private fun iniciarSesionAdmin() {
        rentaCarros.iniciarSesionAdmin()
        if (rentaCarros.adminActivo) {
            menuAdmin()
        } else {
            println("Nombre de usuario o contraseña incorrectos")
        }
    }

    private fun registrarNuevoCliente() {
        rentaCarros.agregarCliente()
        println("Cliente registrado exitosamente")
    }

    private fun menuCliente() {
        while (true) {
            println("Menú de cliente")
            println("Seleccione una opción:")
            println("1. Ver carros disponibles")
            println("2. Rentar carro")
            println("3. Ver mis carros rentados")
            println("4. Devolver carro")
            println("5. Cerrar sesión")

            val opcion = readLine()?.toIntOrNull()

            when (opcion) {
                1 -> rentaCarros.mostrarCarrosDisponibles()
                2 -> rentaCarros.rentarCarro()
                3 -> rentaCarros.mostrarRentasActivas()
                4 -> rentaCarros.devolverCarro()
                5 -> {
                    rentaCarros.cerrarSesionCliente()
                    return
                }
                else -> println("Opción inválida")
            }
        }
    }

     fun menuAdmin() {
        while (true) {
            println("Menú de administrador")
            println("Seleccione una opción:")
            println("1. Ver carros disponibles")
            println("2. Agregar nuevo carro")
            println("3. Eliminar carro")
            println("4. Ver clientes registrados")
            println("5. Cerrar sesión")

            val opcion = readLine()?.toIntOrNull()

            when (opcion) {
                1 -> rentaCarros.mostrarCarrosDisponibles()
                2 -> rentaCarros.agregarCarro()
                3 -> rentaCarros.removerCarro()
                4 -> rentaCarros.mostrarClientesRegistrados()
                5 -> {
                    rentaCarros.cerrarSesionAdmin()
                    return
                }
                else -> println("Opción inválida")
            }
        }
    }
}