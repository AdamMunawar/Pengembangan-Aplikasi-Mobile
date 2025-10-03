class Aquarium (var length: Int = 100, var width: Int = 20, var height: Int = 40) {
    init {
        println("aquarium initializing")
    }
    init {
        // 1 liter = 1000 cm^3
        println("Volume: ${width * length * height / 1000} liters")
    }
    
}

fun main() {
        val aquarium1 = Aquarium()
        // default height and length
        val aquarium2 = Aquarium(width = 25)
        // default width
        val aquarium3 = Aquarium(height = 35, length = 110)
        // everything custom
        val aquarium4 = Aquarium(width = 25, height = 35, length = 110)
}
