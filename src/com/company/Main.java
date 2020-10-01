package com.company;
//heap:application's memory that all threads share
//stack: each thread has a stack and it means only that thread can access
//local variable's are stored in each thread's stack21
//we can also synchronize a block of statements rather than an entire method,so every java object has what's called an intrinsic lock
//so we can synchronize a block of statements that work with an obj by forcing threads to acquire the objects lock before they execute the statement
//block.Only one thread can hold the lock at a time .So other threads that want the lock will be suspended until the running thread releases it then
//only one of the waiting threads can get the lock & continue executing .So in our code the only part that we have to synchronize is the for loop

public class Main {

    public static void main(String[] args) {
        Countdown countdown=new Countdown();
        CountdownThread t1=new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2=new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

    }

}
class Countdown{
    private int i;
    public void doCountdown(){
        String color;
        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color=ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color=ThreadColor.ANSI_PURPLE;
                break;
            default:
                color=ThreadColor.ANSI_GREEN;
        }
        synchronized (this){
            for(i=10;i>0;i--){
                System.out.println(color+Thread.currentThread().getName()+": i ="+i);
            }
        }

    }
}
class CountdownThread extends Thread{
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown){
        threadCountdown=countdown;
    }
    public void run(){
        threadCountdown.doCountdown();
    }
}

