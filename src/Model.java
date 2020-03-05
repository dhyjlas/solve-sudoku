import java.util.Set;

/**
 * 数独模型
 */
public class Model {
    /** 数独内容 */
    Entity[] data;

    public Model(){
        this.data = new Entity[81];
    }

    public Model(String str){
        this.data = new Entity[81];

        int len = str.length();
        int x = 0;
        int y = 0;
        for (int i = 0; i < len; i++) {
            String p = str.substring(i, i + 1);
            if ("0".compareTo(p) <= 0 && "9".compareTo(p) >= 0) {
                this.data[x * 9 + y] = new Entity(Integer.valueOf(p));
                y++;
                if (y == 9) {
                    y = 0;
                    x++;
                }
            }
        }
    }

    public Entity[] getData(){
        return data;
    }

    public void setDef(int x, int y, int value){
        this.data[x * 9 + y].setDef(value);
    }

    public int getDef(int x, int y){
        return this.data[x * 9 + y].getDef();
    }

    public Set<Integer> getPoss(int x, int y){
        return this.data[x * 9 + y].getPoss();
    }

    public void removePoss(int x, int y, int value){
        this.data[x * 9 + y].getPoss().remove(value);
    }

    public Model copy(){
        Model model = new Model();
        for(int i = 0 ; i < 81 ; i++){
            Entity entity = new Entity(this.data[i].getDef(), this.data[i].getPoss());
            model.getData()[i] = entity;
        }

        return model;
    }
}
