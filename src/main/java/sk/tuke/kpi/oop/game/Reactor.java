package sk.tuke.kpi.oop.game;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {

    private boolean isTurnedOn;
    private int temperature;
    private int damage;
    private double increaseCoefficient = 1;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation offAnimation;
    private Light light;

    public Reactor(){
        turnOn();
        this.temperature = 0;
        this.damage = 0;
        this.normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG
        );

        this.hotAnimation = new Animation(
            "sprites/reactor_hot.png",
            80,
            80,
            0.05f,
            Animation.PlayMode.LOOP_PINGPONG
        );

        this.brokenAnimation = new Animation(
            "sprites/reactor_broken.png",
            80,
            80,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG
        );

        this.offAnimation = new Animation(
            "sprites/reactor.png"
        );

        setAnimation(normalAnimation);
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setNormalAnimation(Animation animation){
        this.normalAnimation = animation;
    }

    private void updateAnimation(){
        if(this.temperature < 4000){
            setAnimation(this.normalAnimation);
        }else if(this.temperature > 4000 && this.temperature <6000){
            setAnimation(this.hotAnimation);
        }else{
            setAnimation(this.brokenAnimation);
        }
    }

    public void increaseTemperature(int increment){
        if (increment <= 0)
            return;

        this.temperature += Math.ceil(increment * this.increaseCoefficient);

        if(this.temperature > 2000){
            handleDamage();
        }


    }

    public void decreaseTemperature(int decrement){
        if(damage < 100 && decrement > 0) {
            this.temperature -= (decrement * ((this.damage > 50) ? 0.5 : 1));
            handleDamage();
        }

    }

    public void handleDamage(){
        int damage = (int) Math.round(((float)(this.temperature - 2000)/(float)4000) * (float) 100);
        this.damage = Math.max(damage, this.damage);

        if(this.isRunning() && this.light != null){
            this.light.setElectricityFlow(true);
        }

        if(this.damage <= 66 && this.damage >= 33){
            this.increaseCoefficient = 1.5;
        }else if(this.damage > 66){
            this.increaseCoefficient = 2;
            if(this.damage == 100){
                this.turnOff();
            }
        }
        updateAnimation();
    }

    public void repairWith(Hammer hammer){
        if(hammer == null || this.damage == 0 || this.damage == 100)
            return;

        this.damage -= 50;
        int helperDamage = this.damage;
        if(this.damage < 0){
            this.damage = 0;
        }
        hammer.use();
        if(helperDamage > 0){
            int helperTemperature = ((helperDamage * 4000) / 100) + 2000;
            this.temperature = Math.min(helperTemperature, this.temperature);
        } else {
            this.temperature = 0;
        }

        handleDamage();
    }

    public void turnOn(){
        this.isTurnedOn = true;
        this.setAnimation(this.normalAnimation);
    }

    public void turnOff(){
        this.isTurnedOn = false;
        this.light.setElectricityFlow(false);
        if(this.damage < 100)
            this.setAnimation(this.offAnimation);
    }

    public boolean isRunning(){
        return this.isTurnedOn;
    }

    public void addLight(Light light){
        this.light = light;
    }

    public void removeLight(){
        this.light = null;
    }

}
