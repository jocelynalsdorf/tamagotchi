public class Tamagotchi {

  private int mFoodLevel = 100;
  private int mSleepLevel = 100;
  private int mActivityLevel = 100;


  private String mName;

  public Tamagotchi(String name) {
    mName = name;
  }
  public String getName() {
    return mName;
  }
   public int getFoodLevel() {
     return mFoodLevel;
   }
   public int getSleepLevel() {
     return mSleepLevel;
   }
   public int getActivityLevel() {
     return mActivityLevel;
   }

   public void setFoodLevel(int foodLevel) {
     mFoodLevel += foodLevel;
   }
   public void setSleepLevel(int sleepLevel) {
     mSleepLevel += sleepLevel;
   }
   public void setActivityLevel(int activityLevel) {
     mActivityLevel += activityLevel;
   }
   public void decrementLevels() {
     mFoodLevel -= 10;
     mSleepLevel -= 6;
     mActivityLevel -= 4;
   }
    
}
