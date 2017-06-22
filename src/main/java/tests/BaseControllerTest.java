package tests;

import javafx.event.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.controllers.LogController;

import java.awt.*;
import java.awt.Event;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Damian on 2017-06-22.
 */
public class BaseControllerTest {
    private pl.controllers.LogController logController;
    private javafx.event.Event event;

    @Before
    public void setUp() throws Exception {
        logController = new LogController();
        event = new javafx.event.Event(javafx.event.Event.ANY);
    }

    @Test(expected = NullPointerException.class)
    public void changeWindow() throws Exception {
        logController.changeWindow(event, "test");
    }

    @Test(expected = NullPointerException.class)
    public void openWindow() throws Exception {
        logController.openWindow("test");
    }

}