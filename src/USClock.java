public non-sealed class USClock extends Clock {

    private String periodIndicator;

    public void setHour(int hour) {
        if((hour > 12) && (hour <= 23)){
            setAfterMidDay();
            this.hour = hour - 12;
        }else if(hour >= 24){
            setBeforeMidDay();
            this.hour = 0;
        }else{
            this.hour = hour;   
        }
    }

    public String getPeriodIndicator() {
        return periodIndicator;
    }

    public void setPeriodIndicator(String periodIndicator) {
        this.periodIndicator = periodIndicator;
    }
    
    public void setBeforeMidDay(){
        this.periodIndicator = "AM";
    }

    public void setAfterMidDay(){
        this.periodIndicator = "PM";
    }

    @Override
    Clock convert(final Clock clock) {
        this.second = clock.getSecond();
        this.minute = clock.getMinute();
        switch (clock) {
            case USClock usClock:
                   this.hour = usClock.getHour();
                   this.periodIndicator = usClock.getPeriodIndicator();
                break;
            case BRLClock brlClock:
                    this.setHour(brlClock.getHour());
        }
        return this;
    }

    @Override
    public String getTime(){
        return super.getTime() + " " + this.periodIndicator;
    }
    
}
