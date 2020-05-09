/**
 * @file: EndByTime.java
 * @Author: Bill Song - songb10
 * @Date: March.31th, 2020
 * @Description: module implementing EndCondition interface
 */

package src;

public class EndByTime implements EndCondition{

    private int timeLeft;
    private int redTargert;
    private int orangeTargert;
    private Thread t;

    public EndByTime(){
        this.timeLeft = 60;
        this.redTargert = 5;
        this.orangeTargert = 5;
        t = new Thread(new StartTimer(this));
    }

    class StartTimer implements Runnable {
        private EndByTime t;
        public StartTimer(EndByTime timer){
            this.t = timer;
        }
        public void run() {
            for (int i = 60; i >= 0; i--) {
                t.setTime(i);
                try {
                    // thread to sleep for 1000 milliseconds
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println();
            System.out.println("Time Up. Enter b to start a new game");
        }
    }

    /**
     * @brief changes the game status
     * @param num - the number of dots eliminated during this one move
     * @param color - the color of the eliminated dot
     * @param moves - the number of moves used already
     */
    @Override
    public boolean gameStatus(int num, CellType color, int moves){
        if(color == CellType.R)
            redTargert = redTargert - num;
        else if(color == CellType.O)
            orangeTargert = orangeTargert - num;
        return !((redTargert <= 0 && orangeTargert <= 0)|| (timeLeft) <= 0);
    }

    /**
     * @brief the string representing the game objective and amount of dots and time left until game terminates
     * @param moves - the number of moves used already
     */
    @Override
    public String message(int moves){
        return String.format("Target: # of Red Dots left: %d , # of Orange Dots left: %d\n Time left(sec): %d", redTargert, orangeTargert, timeLeft);
    }

    /**
     * @brief sets the time for the count down clock
     * @param time - the preset time
     */
    public void setTime(int time){
        this.timeLeft = time;
    }

    public void startCountDown(){
        t.start();
    }

}