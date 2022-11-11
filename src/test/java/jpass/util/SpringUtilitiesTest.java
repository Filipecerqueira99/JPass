package jpass.util;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SpringUtilitiesTest {

    private SpringLayout layout;

    @Test
    public void shouldNotWorkWithEmptyContainer() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Panel container = new Panel();
            container.setLayout(new SpringLayout());
            SpringUtilities.makeGrid(container, 0, 0, 0, 0, 0, 0);
        });
    }

    @Test
    public void shouldWorkWithAllPositiveContainer() {
        Panel container = new Panel();
        container.add(new Button("Button 1"));
        container.setLayout(new SpringLayout());
        SpringUtilities.makeGrid(container, 1, 1, 1, 1, 1, 1);
        SpringUtilities.makeGrid(container, 5, 5, 5, 1, 1, 1);
    }

    @Test
    public void shouldWorkWithEmptyCompactGrid() {
        Panel container = new Panel();
        container.setLayout(new SpringLayout());
        SpringUtilities.makeCompactGrid(container, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void shouldWorkWithNonEmptyCompactGrid() {
        Panel container = new Panel();
        container.setLayout(new SpringLayout());
        SpringUtilities.makeCompactGrid(container, 1, 1, 0, 0, 0, 0);
    }

    @Test
    public void shouldWorkWithAllPositiveCompactGrid() {
        Panel container = new Panel();
        container.setLayout(new SpringLayout());
        container.add(new Button("Button 1"));
        SpringUtilities.makeCompactGrid(container, 1, 1, 1, 1, 1, 1);
    }

    @Test
    public void shouldWorkWithPositiveValues() {
        Panel container = new Panel();
        container.setLayout(new SpringLayout());
        container.add(new Button("Button 1"));
        SpringUtilities.makeCompactGrid(container, 0, 0, 1, 1, 1, 1);
        Spring x = Spring.constant(1);

    }
}