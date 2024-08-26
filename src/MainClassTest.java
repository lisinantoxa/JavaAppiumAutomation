import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassString() {
        Assert.assertTrue("getClassString возвращает не содержит Hello или hello",
                this.getClassString().contains("Hello") || this.getClassString().contains("hello"));
    }
}