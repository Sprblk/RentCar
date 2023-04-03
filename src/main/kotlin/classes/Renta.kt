package classes

class Renta(
    val fechaInicio: String,
    val fechaFin: String,
    val carro: Carro,
    val cliente: Cliente
)

fun <E> MutableList<E>.add(element: Renta) {

}