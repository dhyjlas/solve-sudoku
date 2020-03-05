import java.util.HashSet;
import java.util.Set;

/**
 * 每一位的内容
 */
public class Entity {
    /** 当前为数字 */
    private int def = 0;

    /** 当前位可能的情况 */
    private Set<Integer> poss = new HashSet<>();

    public Entity(){}

    public Entity(int def){
        this.def = def;
        for(int i = 1 ; i <= 9 ; i++){
            this.poss.add(i);
        }
    }

    public Entity(int def, Set<Integer> poss){
        this.def = def;
        this.poss.addAll(poss);
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public Set<Integer> getPoss() {
        return poss;
    }

    public void setPoss(Set<Integer> poss) {
        this.poss = poss;
    }
}
