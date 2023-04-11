//package com.example.simplechess;
//
//import android.graphics.Canvas;
//import android.view.SurfaceHolder;
//
//public class ChessFieldLoop extends Thread{
//    public static final double MAX_UPS = 30.0;
//    public static final double UPS_PERIOD = 1E+3/MAX_UPS;
//    private Game game;
//    private SurfaceHolder surfaceHolder;
//
//    private boolean isRunning = false;
//    private double averrageUPS;
//    private double averrageFPS;
//
//    public ChessFieldLoop(Game game, SurfaceHolder surfaceHolder) {
//        this.game = game;
//        this.surfaceHolder = surfaceHolder;
//    }
//
//    public double getAverrageUPS() {
//        return averrageUPS;
//    }
//
//    public double getAverrageFPS(){
//        return averrageFPS;
//    }
//
//    public void startLoop(){
//        isRunning = true;
//        start();
//    }
//
//    @Override
//    public void run() {
//        super.run();
//
//        int updateCount = 0;
//        int frameCount = 0;
//
//        long startTime;
//        long elapsedTime;
//        long sleepTime;
//
//        Canvas canvas;
//        startTime = System.currentTimeMillis();
//        while (isRunning){
//
//            try {
//                canvas = surfaceHolder.lockCanvas();
//                game.update();
//                updateCount++;
//
//                game.draw(canvas);
//
//
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } finally {
//                if (canvas != null) {
//                    try {
//                        surfaceHolder.unlockCanvasAndPost(canvas);
//                        frameCount++;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            elapsedTime = System.currentTimeMillis() - startTime;
//            sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
//            if (sleepTime > 0){
//                try {
//                    sleep(sleepTime);
//                }catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            while (sleepTime < 0 && updateCount < MAX_UPS-1){
//                game.update();
//                updateCount++;
//                elapsedTime = System.currentTimeMillis() - startTime;
//                sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
//            }
//
//
//
//        }
//    }
//
//
//}
