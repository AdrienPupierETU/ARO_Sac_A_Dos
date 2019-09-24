package algorithm;

public class Item implements Comparable{


    public double weight;
    public double value;
    public double ratio;
    /* 0 : Pending decision (not taken)
       1 : taken
       2 : not taken and decision was made
     */
    private int taken;

    public Item(double weight, double value) {
        this.weight = weight;
        this.value = value;
        this.ratio = value/weight;
        this.taken=0;
    }

    public double getValue(){
        return value;
    }

    public void setTaken(int taken) {
        if(taken >2 || taken<0){
            System.out.println("value not between 0 and 2");
            return;
        }
        this.taken = taken;
    }

    public int getTaken() {
        return taken;
    }

    public int compareTo(Object o) {
        Item comparedTo= (Item) o;
        double result=(this.ratio-comparedTo.ratio);
        if(result==0){
            return 0;
        }else if(result>0){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", value=" + value +
                ", taken=" + taken +
                ", ratio=" + ratio +
                '}';
    }
}
