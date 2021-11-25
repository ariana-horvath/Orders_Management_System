package presentation.controller;

import presentation.view.*;

/**
 * The type Main controller - opens the principal frame for the application and initializes listeners for the buttons.
 * @author Ariana Horvath
 */
public class MainController {

    private MainScreen mainScreen;

    /**
     * Instantiates a new Main controller.
     */
    public MainController() {
        mainScreen = new MainScreen();
        initializeListeners();
    }

    /**
     * Initialize listeners.
     */
    public void initializeListeners(){
        mainScreen.getManageClientsButton().addActionListener(e->{
            new ClientController();
        });

        mainScreen.getManageProductsButton().addActionListener(e-> {
            new ProductController();
        });

        mainScreen.getManageOrdersButton().addActionListener(e->{
            new OrderController();
        });

        mainScreen.getExitButton().addActionListener(e->{
            System.exit(0);
        });
    }
}
