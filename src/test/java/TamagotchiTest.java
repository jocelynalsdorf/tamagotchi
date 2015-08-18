import org.junit.*;
import static org.junit.Assert.*;

public class TamagotchiTest {

  @Test
  public void tamagotchi_checkThatInitiatesCorrectly() {
    Tamagotchi tama = new Tamagotchi("fifi");
    assertEquals(true, tama instanceof Tamagotchi);
  }
}
