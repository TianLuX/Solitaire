package GUI;

import javax.swing.*;
import java.awt.*;

/*生成窗口的代码,在其他中的对话窗口
继承对话框窗口 JDialog
* */

public class AboutSolitaire extends JDialog {

    JPanel mainPanel = new JPanel();//面板

    JTabbedPane jTabbedPane = new JTabbedPane();//选项卡面板
    private JPanel jPanelRule = new JPanel();
    private JPanel jPanelDetails = new JPanel();

    private JTextArea jTextAreaRule = new JTextArea("游戏规则：七个牌堆可以移动牌，左上角排队可以翻牌，从小到大把相同花色的牌放入四个放牌框中，全部放完游戏胜利！");
    private JTextArea jTextAreaDetails = new JTextArea("制作人：202000300070谢天露");


    /*构造函数
    * */
    public AboutSolitaire() {

        setTitle("Solitaire Game");//设置窗口名字
        setSize(400,300);//设置窗口大小
        setResizable(false);//窗体是否可由用户调整大小
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//用户单击关闭按钮关闭窗口

        Container container = this.getContentPane();//内容面板

        jTextAreaRule.setSize(260,200);//内容面板大小
        jTextAreaDetails.setSize(260,200);

        jTextAreaRule.setEditable(false);//内容面板不可编辑
        jTextAreaDetails.setEditable(false);

        jTextAreaRule.setLineWrap(true);//文本区的换行策略
        jTextAreaDetails.setLineWrap(true);

        //设置字体和颜色
        jTextAreaRule.setFont(new Font("楷体_GB2312", Font.BOLD, 13));
        jTextAreaRule.setForeground(Color.black);
        jTextAreaDetails.setFont(new Font("楷体_GB2312", Font.BOLD, 13));
        jTextAreaDetails.setForeground(Color.black);

        jPanelRule.add(jTextAreaRule);
        jPanelDetails.add(jTextAreaDetails);

        jTabbedPane.setSize(300,200);
        jTabbedPane.addTab("游戏规则", null, jTextAreaRule, null);
        jTabbedPane.addTab("声明", null, jTextAreaDetails, null);

        mainPanel.add(jTabbedPane);
        container.add(mainPanel);

        pack();
        this.setVisible(true);


    }


}
