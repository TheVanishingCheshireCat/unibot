/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author mb459
 */
public class Model implements ActionListener {
    
    double x,y,velocity,theta,vDiff;
    double r = 0.22;//m
    double stepSize = 0.01;

    public Model() {
        theta = 0; vDiff = 0;
        x = 0; y = 0;
        velocity = 0;
    }
    
    void step() {
        EulerStep();
    }
    
    //Timer t = new Timer(10);
    
    void EulerStep() {
        x = x + stepSize * velocity * Math.sin(theta);
        y = y - stepSize * velocity * Math.cos(theta);
        theta = theta + stepSize * vDiff / r;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.blue);
        g2.fill(new Ellipse2D.Double(x,y,r,r));
    }
    
}
