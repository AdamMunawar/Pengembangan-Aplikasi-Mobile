class Aquarium (val length: Int = 100, val width: Int = 20, var height: Int = 40) {
    
    var volume: Int
    get() = width * height * length / 1000
    set(value) {
        height = (value * 1000) / (width * length)
    }
    
    init {
        println("aquarium initializing")
    }
    init {
        // 1 liter = 1000 cm^3
        println("Volume: ${width * length * height / 1000} liters")
    }
    
    constructor(numberOfFish: Int) : this() {
        // 2,000 cm^3 per fish + extra room so water doesn't spill
        val tank = numberOfFish * 2000 * 1.1
        println("Tank: $tank")
    }
    
    fun printSize() {
    	println("Width: $width cm " +
            "Length: $length cm " +
            "Height: $height cm ")
        
        // 1 liter = 1000 cm^3
        println("Volume: $volume liters")
    }
    
}
   
    fun main() {
        val aquarium1 = Aquarium()
        aquarium1.printSize()
        // default height and length
        val aquarium2 = Aquarium(width = 25)
        aquarium2.printSize()
        // default width
        val aquarium3 = Aquarium(height = 35, length = 110)
        aquarium3.printSize()
        // everything custom
        val aquarium4 = Aquarium(width = 25, height = 35, length = 110)
        aquarium4.printSize()
        val aquarium5 = Aquarium(numberOfFish = 29)
        aquarium5.printSize()
   }
