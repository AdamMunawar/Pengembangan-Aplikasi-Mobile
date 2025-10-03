fun main() {
    val school = listOf("mackerel", "trout", "halibut")
    
    val myList = mutableListOf("tuna", "salmon", "shark")
	myList.remove("shark");
    
    println(school)
    println(myList)
    println("Print data using Looping")
    
    var i=0
    for (element in school) {
        i++
    	println("$i: $element")
	}

 }
