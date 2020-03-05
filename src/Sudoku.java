import java.util.HashSet;
import java.util.Set;

public class Sudoku {
    /**
     * 求解结果
     * @param model
     */
    public void deal(Model model) {
        while(true){
            int f = removePoss(model);
            if(f == -1)
                return;

            if(f == 1)
                break;
        }

        if(check(model)){
            output(model);
            return;
        }

        //存在不确定情况，进行假设
        int xx = -1;
        for(int i = 0 ; i < 81 ; i++){
            if(model.getData()[i].getDef() < 1){
                xx = i;
                break;
            }
        }
        if(xx == -1)
            return;

        for(int n : model.getData()[xx].getPoss()){
            Model model1 = model.copy();
            model1.getData()[xx].setDef(n);
            Set<Integer> set = new HashSet<>();
            set.add(n);
            model1.getData()[xx].setPoss(set);
            deal(model1);
        }
    }

    /**
     * 去掉所有可能性,确定唯一数据
     */
    public int removePoss(Model model) {
        int f = 1;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (model.getDef(x, y) > 0)
                    continue;

                for (int i = 0; i < 9; i++) {
                    if (model.getDef(x, i) > 0)
                        model.removePoss(x, y, model.getDef(x, i));
                    if (model.getDef(i, y) > 0)
                        model.removePoss(x, y, model.getDef(i, y));
                }

                int x1 = x / 3 * 3;
                int x2 = y / 3 * 3;
                for (int k1 = x1; k1 < x1 + 3; k1++) {
                    for (int k2 = x2; k2 < x2 + 3; k2++) {
                        if (model.getDef(k1, k2) > 0)
                            model.removePoss(x, y, model.getDef(k1, k2));
                    }
                }

                if (model.getPoss(x, y).size() < 1) {
                    return -1;
                } else if (model.getPoss(x, y).size() == 1) {
                    for(Integer n : model.getPoss(x, y)){
                        model.setDef(x, y, n);
                    }
                    f = 0;
                }
            }
        }

        return f;
    }

    /**
     * 检测是否确定唯一解
     * @param model
     * @return
     */
    public boolean check(Model model){
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if(model.getDef(x, y) < 1){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 打印结果
     * @param model
     */
    public void output(Model model){
        for (int i = 0; i < 9; i++) {
            System.out.print(" ");
            for (int j = 0; j < 9; j++) {
                System.out.print(model.getDef(i, j) + " ");
                if(j % 3 == 2){
                    System.out.print("| ");
                }
            }
            System.out.println();
            if(i % 3 == 2){
                System.out.println("------------------------");
            }
        }
        System.out.println("**********************************************************");
    }
}
