package algorithmClass;



public interface xiaoxiaoleModel {

    void showOnestepMaxScore(exchangePoint exchangePoint);  //展示一步操作的最大得分
    void outMap(int map[][]);  //输出矩阵
    void setMapTemp(int[][]map,int[][]mapTemp); //数组间的赋值
    exchangePoint MaxScoreOneStep(int map[][]); //查找一步得最大得分
    void changeTwoPoint(int map[][],int x1,int y1,int x2,int y2); //交换两个点
    int getGoal(int map[][],int x,int y);  //获得该点得分数
    void chageMap(int map[][],int x1,int y1,int x2,int y2 ,int row);   //改变矩阵
    void initialMap();
    void inspectInitialMap(int map[][]);
}

