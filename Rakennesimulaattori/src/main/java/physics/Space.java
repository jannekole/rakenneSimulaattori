package physics;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *Space contains all Nodes and Beams, and stores universal constants that apply to them all.
 * @author janne
 */
public class Space {

    private double gravity;
    private double updateInterval;

    double time;
    
    ArrayList<Node> nodes;
    ArrayList<Beam> beams;
    
    private boolean stop;

    /**
     * Constructs a new Space with the default gravity and update interval.
     */
    public Space() {
        setGravity(9.81);
        setUpdateInterval(0.0001);

        time = 0;
        stop = false;
        
        setEmpty();
    }

    public final void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public final void setUpdateInterval(double updateInterval) {
        this.updateInterval = updateInterval;
    }

    /**
     * Adds a Node to Space.
     * @param node the node to be added
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * Adds a Beam to Space.
     * @param beam the Beam to be added
     */
    public void addBeam(Beam beam) {
        beams.add(beam);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Beam> getBeams() {
        return beams;
    }

    public double getGravity() {
        return gravity;
    }

    public double getUpdateInterval() {
        return updateInterval;
    }

    /**
     *Calculates the new state of every beam and node for the given number of times.
     * 
     * @param steps the amount of times to calculate the new state 
     */
    public void stepFor(int steps) {

        stepFor(steps, 60000); // 1 minute
    }
    
    public void stepFor(int steps, long maxTime) {
        
        stop = false;
        
        long startingTime = System.currentTimeMillis();
        
        for (int i = 0; i < steps; i++) {
            step();
            if (System.currentTimeMillis() > startingTime + maxTime) {
                return;
            } else if (stop == true) {
                return;
            }
        }
    }
    
    /**
     *Calculates the new state of every beam and node.
     */
    public void step() {

        for (Beam beam : beams) {
            beam.calculateNewState();
        }
        for (Node node : nodes) {
            node.calculateNewState();
        }
        
        time = time + updateInterval;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < getNodes().size(); i++) {
            Node node = getNodes().get(i);
            returnString = returnString + "__  Node " + (i + 1) + ": " + node;

        }
        return returnString;
    }

    /**
     *This method deletes all the components in Space. 
     */
    public final void setEmpty() {
        nodes = new ArrayList();
        beams = new ArrayList();
    }

    public double getTime() {
        return time;
    }
    public void pause() {
        stop = true;
    }
}
