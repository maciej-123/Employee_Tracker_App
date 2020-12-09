import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import javax.swing.border.*;


public class Interface {

    //main panel with 2 extra panels
    private JPanel mainPanel;
    private JPanel functionsPanel;
    private JPanel titlePanel;
    //sub-labels for title and warning panel
    private JLabel title;
    //sub-panels for function panel
    private JPanel salesRevenueProfit;
    private JPanel branchesList; //%%% may possibly be removed
    private JPanel searchForDrug;

    //Sales and Revenue function panel elements:
    private JLabel soldItem;
    private JTextField inputSoldItem;
    private JButton enterItem;
    private JLabel profitCostTitle;
    private JTextField revenue;
    private JTextField dailyProfit;
    private JButton calculateProfit;

    //Branches and Drug List panel elements:
    private JLabel branchesTitle;
    private JPanel branches;
    private JButton greenPark;
    private JButton mileEnd;
    private JButton Paddington;
    private JTextArea drugList;
    private JLabel warning; // the background of this text will turn red if the stock is depleted to below 20%

    //
    private JLabel searchTitle;
    private JTextField drugName;
    private JButton searchButton;
    private JLabel itemDetails;
    private JTextField drugDetails;
    private JLabel locationTitle;
    private JTextField Location;

    //main constructor for interface
    Interface()
    {
        // ** make variables private outside of constructor - delete me later

        //declare panels


        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Color mainBorderColor = new Color(234, 240, 242);
        Border border = new LineBorder(mainBorderColor, 10, false);
        mainPanel.setBorder(border);




        c.fill = GridBagConstraints.VERTICAL;

        titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(1024, 100));

        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(titlePanel, c);

        functionsPanel = new JPanel();
        functionsPanel.setLayout(new GridLayout(1,3,10,10));


        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 8;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(functionsPanel, c);

        //add and create title and warning for app
        addTitle();

        //Create and add panel containing functions for inputting sold items and calculating revenue and profit
        addSalesRevProf();
        addBranchesList();
        addSearchForDrug();


        //add all panels to main panel:
//        mainPanel.add(titlePanel);
//        mainPanel.add(functionsPanel);
    }

    private void addTitle()
    {
        title = new JLabel("PHAB Pharmacy Stock Tracker");//create title
        title.setForeground(Color.BLUE);//set font colour
        title.setFont(title.getFont().deriveFont(40.0f));//set font size
        titlePanel.add(title);

    }

    private void addSalesRevProf()
    {
        salesRevenueProfit = new JPanel();
        salesRevenueProfit.setLayout(new GridLayout(7,1,10,0));
        soldItem = new JLabel("Input Sold Item");//create title
        salesRevenueProfit.add(soldItem);
        inputSoldItem = new JTextField("Input Item Here");
        salesRevenueProfit.add(inputSoldItem);
        enterItem = new JButton("Enter Item");
        salesRevenueProfit.add(enterItem);

        profitCostTitle = new JLabel("Find Daily Profit and Costs"); //create title
        salesRevenueProfit.add(profitCostTitle);
        revenue = new JTextField("%%Display Revenue Here");
        revenue.setEditable(false);
        revenue.setBackground(Color.LIGHT_GRAY);
        salesRevenueProfit.add(revenue);
        dailyProfit = new JTextField("%%Display Daily Profit Here");
        dailyProfit.setEditable(false);
        dailyProfit.setBackground(Color.LIGHT_GRAY);
        salesRevenueProfit.add(dailyProfit);
        calculateProfit = new JButton("Calculate Profit");
        salesRevenueProfit.add(calculateProfit);

        functionsPanel.add(salesRevenueProfit);//add to functions panel
    }

    private void addBranchesList()
    {
        branchesList = new JPanel();
        branchesList.setLayout(new GridLayout(4,1,10,10));
        branchesTitle = new JLabel("Drug List and Branches");//create title
        branchesList.add(branchesTitle);

        branches = new JPanel();
        branches.setLayout(new GridLayout(1,3,10,10));
        greenPark = new JButton("Green Park");
        branches.add(greenPark);
        mileEnd = new JButton("Mile End");
        branches.add(mileEnd);
        Paddington = new JButton("Paddington");
        branches.add(Paddington);
        branchesList.add(branches);

        drugList = new JTextArea("%%Drug list will display here");
        drugList.setEditable(false);
        drugList.setBackground(Color.LIGHT_GRAY);
        branchesList.add(drugList);

        warning = new JLabel("RED Warning Below 20%");//create warning
        warning.setForeground(Color.RED);//set font colour
        warning.setFont(warning.getFont().deriveFont(24.0f));//set font size
        branchesList.add(warning);

        functionsPanel.add(branchesList);//add to functions panel
    }

    private void addSearchForDrug()
    {
        searchForDrug = new JPanel();
        searchForDrug.setLayout(new GridLayout(7,1,10,10));
        searchTitle = new JLabel("Search for Drug");//create title
        searchForDrug.add(searchTitle);
        drugName = new JTextField("Enter Drug Name");
        searchForDrug.add(drugName);
        searchButton = new JButton("Search");
        searchForDrug.add(searchButton);
        itemDetails = new JLabel("Item Details:");//create title
        searchForDrug.add(itemDetails);
        drugDetails = new JTextField("%%Details will show here");
        drugDetails.setEditable(false);
        drugDetails.setBackground(Color.LIGHT_GRAY);
        searchForDrug.add(drugDetails);
        locationTitle = new JLabel("Location");//create title
        searchForDrug.add(locationTitle);
        Location = new JTextField("%%Location will display here");
        Location.setEditable(false);
        Location.setBackground(Color.LIGHT_GRAY);
        searchForDrug.add(Location);

        functionsPanel.add(searchForDrug);//add to functions panel
    }

    public JPanel returnMainPanel()
    {
        return mainPanel;
    }
}
