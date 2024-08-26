import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("getLocalNumber возвращает не число 14", 14, this.getLocalNumber());
    }
}
