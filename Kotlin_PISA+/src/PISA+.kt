import kotlin.random.Random

open class BasicTest(val readingScore : Int, val mathScore : Int, val id: String?){

    constructor():this(
        Random.nextInt(0,100),
        Random.nextInt(0,100),
        Random.nextInt(0,1000).toString())

    open fun getTestScore(){
        print( "Results of the test $id:\n" +
                "      -> Reading score : $readingScore \n" +
                "      -> Math score :  $mathScore\n");
    }
}

class PlusTest(readingScore: Int, mathScore: Int, id: String?, val testTime: Double?, val creativity:Boolean? )
    :BasicTest(readingScore, mathScore, id){

    constructor():this(
        Random.nextInt(0,100),
        Random.nextInt(0,100),
        Random.nextInt(0,1000).toString(),
        Random.nextDouble(5.0, 60.0),
        Random.nextBoolean())

    override fun getTestScore() {
        print( "Results of the Plus-test $id:\n" +
                "      -> Reading score : $readingScore \n" +
                "      -> Math score :  $mathScore \n" +
                "      -> Test time :  $testTime \n");
        when(creativity)
        {
            true -> println("       -> Was creative");
            else -> println("       -> Was not creative");

        }
    }

}

fun main() {
    var numberOfPlusTests : Int = 0;
    var allTests = arrayOf( BasicTest(50,60, "A"),
                                BasicTest(75,93, "B"),
                                BasicTest(57,66, "C"),
                                PlusTest(57,66, "C", 25.03, true),
                                PlusTest(75,93, "B", 34.54, false))
    for(test in allTests){
        test.getTestScore();
        if(test is PlusTest)
        {
            numberOfPlusTests++;
        }
    }
    println("There were $numberOfPlusTests Plust-Tests");

}