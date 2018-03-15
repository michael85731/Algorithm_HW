class Timer{
    private long startTime = 0;

    public Timer(){
        this.startTime = 0;
    }

    public void start(){
        this.startTime = System.currentTimeMillis();
    }

    public long stop(){
        long now = System.currentTimeMillis();
        return now - this.startTime;
    }
}
