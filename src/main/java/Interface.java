import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.border.*;


public class Interface {

    //main panel with 2 extra panels
    private JPanel mainPanel;
    private JPanel functionsPanel;
    private JPanel topPanel;
    //sub-labels for title and warning panel
    private JLabel title;
    //sub-panels for function panel
    private JPanel branchesList; //%%% may possibly be removed
    private JPanel searchForDrug;
    private JPanel Sales;

    //panels for profit in profit tab
    private JPanel profitPanel;
    private JPanel Profit;

    //Sales and Revenue function panel elements:
    private JLabel soldItem;
    private JTextField inputSoldItem;
    private JButton enterItem;
    private JLabel profitCostTitle;
    private JTextField revenue;
    private JTextField dailyProfit;
    private JButton calculateProfit;
    private JButton calculateRevenue;

    //Branches and Drug List panel elements:
    private JLabel branchesTitle;
    private JPanel branches;
    private JButton greenPark;
    private JButton mileEnd;
    private JButton Paddington;
    private JTextArea drugList;
    private JLabel warning; // the background of this text will turn red if the stock is depleted to below 20%
    private JButton restock;
    private JButton checkStock;
    private JTextField stockStatus;

    //
    private JLabel searchTitle;
    private JTextField drugName;
    private JButton searchButton;
    private JLabel itemDetails;
    private JTextField drugDetails;
    private JLabel locationTitle;
    private JTextField Location;
    private JTextField itemQuantity;

    //test tabbed pane
    private JTabbedPane Screen;

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

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,2,25,2));

        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(topPanel, c);

        functionsPanel = new JPanel();
        functionsPanel.setLayout(new GridLayout(1,3,10,10));


        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 8;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(functionsPanel, c);

        profitPanel = new JPanel();
        profitPanel.setLayout(new GridLayout(1,3,10,10));


        Screen = new JTabbedPane();
        Screen.addTab("Main Screen", mainPanel);
        Screen.addTab("Check Profit",profitPanel);

        //add and create title and warning for app
        addTitle();

        //Create and add panel containing functions for inputting sold items and calculating revenue and profit
        addBranchButtons();
        addSales();
        addSearchForDrug();
        addDrugList();
        addProf();



        //add all panels to main panel:
//        mainPanel.add(titlePanel);
//        mainPanel.add(functionsPanel);
    }

    private void addTitle()
    {
        title = new JLabel("PHAB Pharmacy Stock Tracker");//create title
        title.setForeground(Color.BLUE);//set font colour
        title.setFont(title.getFont().deriveFont(30.0f));//set font size
        topPanel.add(title);

    }

    private void addBranchButtons()
    {
        String Branch1 = "Paddington";
        String Branch2 = "MileEnd";
        String Branch3 = "GreenPark";
        branches = new JPanel();
        branches.setLayout(new GridLayout(1,3,3,0));
        greenPark = new JButton("Green Park");

        ActionListener greenParkAL=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Green Park selected"+e.getActionCommand());
                POST_Requests P = new POST_Requests(Branch3,"https://phabservlet1.herokuapp.com/inputB");
            }
        };

        greenPark.addActionListener(greenParkAL);
        branches.add(greenPark);
        mileEnd = new JButton("Mile End");

        ActionListener mileEndAL=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mile End selected"+e.getActionCommand());
                POST_Requests P = new POST_Requests(Branch2,"https://phabservlet1.herokuapp.com/inputB");
            }
        };

        mileEnd.addActionListener(mileEndAL);

        branches.add(mileEnd);
        Paddington = new JButton("Paddington");

        ActionListener paddingtonAL=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Paddington selected"+e.getActionCommand());
                POST_Requests P = new POST_Requests(Branch1,"https://phabservlet1.herokuapp.com/inputB");
            }
        };

        Paddington.addActionListener(paddingtonAL);
        branches.add(Paddington);
        topPanel.add(branches);
    }

    private void addProf()
    {
        Profit = new JPanel();
        Profit.setLayout(new GridLayout(8,1,10,0));


        profitCostTitle = new JLabel("Find Daily Profit"); //create title
        Profit.add(profitCostTitle);
        revenue = new JTextField("%%Display Revenue Here");
        revenue.setEditable(false);
        revenue.setBackground(Color.LIGHT_GRAY);
        Profit.add(revenue);
        dailyProfit = new JTextField("%%Display Daily Profit Here");
        dailyProfit.setEditable(false);
        dailyProfit.setBackground(Color.LIGHT_GRAY);
        Profit.add(dailyProfit);
        calculateProfit = new JButton("Calculate Profit");
        calculateRevenue = new JButton("Calculate Revenue");

        ActionListener profitAL=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Profit calculated");
                GET_Requests g = new GET_Requests("https://phabservlet1.herokuapp.com/calculateProfit");
                //String prof=calculateProfit.getActionCommand();

                Gson gson = new Gson();
                String jsonString = gson.toJson(g);
                int length2=jsonString.length()-2;
                String jsonString2 = jsonString.substring(18,length2);
                dailyProfit.setText(jsonString2);
            }
        };

        ActionListener revAL=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Revenue calculated"+e.getActionCommand());
                GET_Requests g = new GET_Requests("https://phabservlet1.herokuapp.com/calculateRevenue");
                //String rev=calculateRevenue.getActionCommand();

                Gson gson = new Gson();
                String jsonString = gson.toJson(g);
                int length2=jsonString.length()-2;
                String jsonString2 = jsonString.substring(18,length2);
                revenue.setText(jsonString2);
            }
        };

        calculateProfit.addActionListener(profitAL);
        calculateRevenue.addActionListener(revAL);

        Profit.add(calculateProfit);
        Profit.add(calculateRevenue);

        profitPanel.add(Profit);
    }

    private void addSales(){
        Sales = new JPanel();
        Sales.setLayout(new GridLayout(8,1,10,10));
        soldItem = new JLabel("Input Sold Item");//create title
        Sales.add(soldItem);

        String[] soldItemManu=new String[]{"Vicks","Gsk","Lemsip","Sudafed","Benylin","E45","Eurax",
                "Eucerin","Dermalex","Cetaphil","Nurofen","Cuprofen","Solpadeine","Anadin","Disprin",
                "Dioralyte","Gaviscon","Senokot","Benadryl","Piriteze","Beconase","Dettol",
                "Elastoplast","TCP"};
        final JComboBox sold_ItemManu=new JComboBox(soldItemManu);
        sold_ItemManu.setEditable(false); // idk if it should be editable
        AutoCompletion.enable(sold_ItemManu);
        Sales.add(sold_ItemManu);

        String[] soldItemName=new String[]{
                // Cold and fllu
                "Vaporub","First Defence","Night Nurse","Night Nurse","Max",
                "Standard","Day and Night","Max","Mucus relief","4 flu",
                //Skincare
                "Psoriasis cream","Skin cream",
                "Skin relief cream","Face scrub","Psoriasis cream","Repair and Restore","Eczema cream",
                "Eczema cream","Moisturising cream","Exfoliating cleanser",
                //Headaches and pain relief
                "Meltlets","Express","Max strength",
                "Standard","Max strength","Headache","Extra","Triple action","Original","Soluble",
                //Digestion
                "Blackcurrant","Lemon","Chewable","Max","Advance",
                //Allergy
                "Relief","tabs","Relief",
                //First aid
                "Antiseptic","Hand sanitizer","plasters","Liquid"
        };
        final JComboBox sold_ItemName=new JComboBox(soldItemName);
        sold_ItemName.setEditable(false); // idk if it should be editable
        AutoCompletion.enable(sold_ItemName);
        Sales.add(sold_ItemName);

        itemQuantity = new JTextField("Input Item Quantity");
        Sales.add(itemQuantity);
        enterItem = new JButton("Enter Item");

        ActionListener soldItemAL=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String quantity=itemQuantity.getText();
                int int_quan=Integer.parseInt(quantity);

                String manu=sold_ItemManu.getSelectedItem().toString().toLowerCase();
                String name=sold_ItemName.getSelectedItem().toString().toLowerCase();
                String message2=manu+"@"+name;

                POST_Requests p2 = new POST_Requests(message2,"https://phabservlet1.herokuapp.com/inputMN");

                for(int i=1;i<=int_quan;++i) {
                    GET_Requests g = new GET_Requests("https://phabservlet1.herokuapp.com/_decreaseStock");
                }

                System.out.println("Stock Updated");
            }
        };
        enterItem.addActionListener(soldItemAL);

        Sales.add(enterItem);

        functionsPanel.add(Sales);

    }

    private void addDrugList()
    {
        branchesList = new JPanel();
        branchesList.setLayout(new GridLayout(8,1,10,0));
        branchesTitle = new JLabel("Drug List");//create title
        branchesList.add(branchesTitle);

        drugList = new JTextArea("Cold and flu\nVicks, Vaporub\nVicks, First defence");
        drugList.setEditable(false);
        drugList.setBackground(Color.LIGHT_GRAY);
        branchesList.add(drugList);

        warning = new JLabel("RED Warning Below 20%");//create warning
        warning.setForeground(Color.RED);//set font colour
        warning.setFont(warning.getFont().deriveFont(24.0f));//set font size
        branchesList.add(warning);

        restock = new JButton("Restock item(s)");

        ActionListener restockAL=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Restock button pressed"+e.getActionCommand());
                GET_Requests g = new GET_Requests("https://phabservlet1.herokuapp.com/replenishStock");
            }
        };

        restock.addActionListener(restockAL);

        branchesList.add(restock);

        checkStock = new JButton("Check stock");
        stockStatus = new JTextField("%%Stock status will show here");
        stockStatus.setEditable(false);
        stockStatus.setBackground(Color.LIGHT_GRAY);



        ActionListener checkStockAL=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("check button pressed"+e.getActionCommand());
                GET_Requests g = new GET_Requests("https://phabservlet1.herokuapp.com/_checkStock");
                Gson gson = new Gson();
                String jsonString = gson.toJson(g);

                int length2=jsonString.length()-2;
                String jsonString2 = jsonString.substring(17,length2);
                stockStatus.setText(jsonString2);

            }
        };

        checkStock.addActionListener(checkStockAL);
        branchesList.add(checkStock);
        branchesList.add(stockStatus);


        functionsPanel.add(branchesList);//add to functions panel
    }

    private void addSearchForDrug()
    {
        searchForDrug = new JPanel();
        searchForDrug.setLayout(new GridLayout(8,1,10,10));
        searchTitle = new JLabel("Search for Drug");//create title
        searchForDrug.add(searchTitle);
        // drugName = new JTextField("Enter Drug Name");

        String[] drugSearch=new String[]{"Vicks","Gsk","Lemsip","Sudafed","Benylin","E45","Eurax",
                "Eucerin","Dermalex","Cetaphil","Nurofen","Cuprofen","Solpadeine","Anadin","Disprin",
                "Dioralyte","Gaviscon","Senokot","Benadryl","Piriteze","Beconase","Dettol",
                "Elastoplast","TCP"};
        final JComboBox search_drug=new JComboBox(drugSearch);
        search_drug.setEditable(false); // idk if it should be editable
        AutoCompletion.enable(search_drug);
        searchForDrug.add(search_drug);

        String[] drugnameSearch=new String[]{
                // Cold and fllu
                "Vaporub","First Defence","Night Nurse","Night Nurse","Max",
                "Standard","Day and Night","Max","Mucus relief","4 flu",
                //Skincare
                "Psoriasis cream","Skin cream",
                "Skin relief cream","Face scrub","Psoriasis cream","Repair and Restore","Eczema cream",
                "Eczema cream","Moisturising cream","Exfoliating cleanser",
                //Headaches and pain relief
                "Meltlets","Express","Max strength",
                "Standard","Max strength","Headache","Extra","Triple action","Original","Soluble",
                //Digestion
                "Blackcurrant","Lemon","Chewable","Max","Advance",
                //Allergy
                "Relief","tabs","Relief",
                //First aid
                "Antiseptic","Hand sanitizer","plasters","Liquid"
        };

        final JComboBox search_drugname=new JComboBox(drugnameSearch);
        search_drugname.setEditable(false); // idk if it should be editable
        AutoCompletion.enable(search_drugname);
        searchForDrug.add(search_drugname);

        // searchForDrug.add(drugName); test
        searchButton = new JButton("Select Item");
        searchForDrug.add(searchButton);
        ActionListener searchButtonAL=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String manu=search_drug.getSelectedItem().toString().toLowerCase();
                String name=search_drugname.getSelectedItem().toString().toLowerCase();
                String message2=manu+"@"+name;

                POST_Requests p2 = new POST_Requests(message2,"https://phabservlet1.herokuapp.com/inputMN");
                GET_Requests g = new GET_Requests("https://phabservlet1.herokuapp.com/searchForDrug");
                System.out.println("searchButton pressed ");
                Gson gson = new Gson();
                String jsonString = gson.toJson(g);
                int length2=jsonString.length()-2;
                String jsonString2 = jsonString.substring(17,length2);
                drugDetails.setText(jsonString2);
                System.out.println(jsonString);
            }
        };
        searchButton.addActionListener(searchButtonAL);

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

    public JTabbedPane returnScreen()
    {
        return Screen;
    }
}