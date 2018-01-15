import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class HelloWorldTest {
    @Test
    fun exampleTest() {
        val i = 0L

        assertThat(i, `is`(0L))
    }
}