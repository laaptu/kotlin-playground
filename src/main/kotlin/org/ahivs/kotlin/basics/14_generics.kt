package org.ahivs.kotlin.basics.generics

interface Repository<T> {
    fun getById(id: Int): T
    fun getAll(): List<T>
}

class GenericRepo<T> : Repository<T> {
    override fun getById(id: Int): T {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<T> {
        TODO("Not yet implemented")
    }
}

class Customer

interface NetworkRepo {
    fun <T> getById(id: Int): T
    fun <R> getAll(): List<R>
}

class NetworkRepository : NetworkRepo {
    override fun <T> getById(id: Int): T {
        TODO("Not yet implemented")
    }

    override fun <R> getAll(): List<R> {
        TODO("Not yet implemented")
    }

}

fun main() {
    val genericRepo = GenericRepo<Customer>()
    val customer: Customer = genericRepo.getById(20)
    val customers: List<Customer> = genericRepo.getAll()

    val networkRepository = NetworkRepository()
    val customer1 = networkRepository.getById<Customer>(20)
    val customers1: List<Customer> = networkRepository.getAll()
}
