import org.junit.*;
import static org.junit.Assert.*;

public class TamagotchiTest {

  @Test
  public void tamagotchi_checkThatInitiatesCorrectly() {
    Tamagotchi tama = new Tamagotchi("fifi");
    assertEquals(true, tama instanceof Tamagotchi);
  }

  @Test
  public void tamagotchi_ReturnsFoodLevel() {
    Tamagotchi tama = new Tamagotchi("bob");
    assertEquals(100, tama.getFoodLevel());
  }
  @Test
  public void setSleepLevel_returnsCorrectSleepLevel() {
    Tamagotchi tama = new Tamagotchi("oak");
    tama.setSleepLevel(40);
    assertEquals(40, tama.getSleepLevel());
  }
  @Test
  public void decrementLevels_decrementsCorrectly() {
    Tamagotchi tama = new Tamagotchi("Shelly");
    tama.decrementLevels();
    assertEquals(90, tama.getFoodLevel());
  }

}
